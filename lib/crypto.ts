import crypto from 'crypto';

// Matches Java: MessageDigest.getInstance("SHA") + Base64.encodeBase64String()
export function encryptPassword(plain: string): string {
  return crypto.createHash('sha1').update(plain, 'utf8').digest('base64');
}
