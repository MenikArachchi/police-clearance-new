import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
import { readFile } from 'fs/promises';
import path from 'path';
import crypto from 'crypto';

const ALLOWED_EXT = new Set(['.jpg', '.jpeg', '.png', '.gif', '.bmp', '.pdf', '.tif', '.tiff']);

// AES/ECB/PKCS5 key matching the Java FileUtil
const AES_KEY = Buffer.from('x7QKe+D3cgrbf7rE9M6eTA==', 'base64');

function decryptFile(encrypted: Buffer): Buffer {
  const decipher = crypto.createDecipheriv('aes-128-ecb', AES_KEY, null);
  return Buffer.concat([decipher.update(encrypted), decipher.final()]);
}

export async function GET(
  _req: NextRequest,
  { params }: { params: Promise<{ filename: string }> }
) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });

  const { filename } = await params;

  // Sanitise — only allow simple filenames, no path traversal
  if (!filename || filename.includes('/') || filename.includes('\\') || filename.includes('..')) {
    return NextResponse.json({ error: 'Invalid filename' }, { status: 400 });
  }

  const ext = path.extname(filename).toLowerCase();
  if (!ALLOWED_EXT.has(ext)) {
    return NextResponse.json({ error: 'File type not allowed' }, { status: 400 });
  }

  const uploadDir = process.env.UPLOAD_FILE_PATH;
  if (!uploadDir) {
    return NextResponse.json({ error: 'Upload path not configured' }, { status: 500 });
  }

  const filePath = path.join(uploadDir, filename);

  try {
    const encrypted = await readFile(filePath);
    const decrypted = decryptFile(encrypted);

    const contentTypes: Record<string, string> = {
      '.jpg': 'image/jpeg', '.jpeg': 'image/jpeg', '.png': 'image/png',
      '.gif': 'image/gif', '.bmp': 'image/bmp',
      '.pdf': 'application/pdf',
      '.tif': 'image/tiff', '.tiff': 'image/tiff',
    };

    return new NextResponse(new Uint8Array(decrypted), {
      headers: {
        'Content-Type': contentTypes[ext] ?? 'application/octet-stream',
        'Cache-Control': 'private, max-age=3600',
      },
    });
  } catch {
    return NextResponse.json({ error: 'File not found' }, { status: 404 });
  }
}
