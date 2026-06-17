import type { NextAuthOptions } from 'next-auth';
import CredentialsProvider from 'next-auth/providers/credentials';
import pool from '@/lib/db';
import { encryptPassword } from '@/lib/crypto';
import { EXT_DEPT_IDS, UserStatus } from '@/lib/constants';
import type { RowDataPacket } from 'mysql2';

interface UserRow extends RowDataPacket {
  id: number;
  user_full_name: string;
  email_id: string;
  user_name: string;
  user_type: number;
  dept_id: number;
  status: number;
}

export const authOptions: NextAuthOptions = {
  providers: [
    CredentialsProvider({
      name: 'credentials',
      credentials: {
        username: { label: 'Username', type: 'text' },
        password: { label: 'Password', type: 'password' },
      },
      async authorize(credentials) {
        try {
          console.log('[AUTH] authorize called, username:', credentials?.username);

          if (!credentials?.username || !credentials?.password) {
            console.log('[AUTH] missing credentials');
            return null;
          }

          const hashed = encryptPassword(credentials.password);
          console.log('[AUTH] hashed:', hashed);

          const [rows] = await pool.execute<UserRow[]>(
            `SELECT id, user_full_name, email_id, user_name, user_type, dept_id, status
             FROM t_user_registration
             WHERE user_name = ? AND password = ?`,
            [credentials.username, hashed]
          );

          console.log('[AUTH] rows found:', rows.length);

          if (rows.length === 0) {
            console.log('[AUTH] no user found');
            return null;
          }

          const user = rows[0];
          console.log('[AUTH] user status:', user.status, 'ACTIVE:', UserStatus.ACTIVE);

          if (user.status !== UserStatus.ACTIVE) {
            console.log('[AUTH] user inactive');
            return null;
          }

          const result = {
            id: String(user.id),
            name: user.user_full_name,
            email: user.email_id ?? '',
            username: user.user_name,
            userType: user.user_type,
            deptId: user.dept_id,
          };
          console.log('[AUTH] returning user:', result);
          return result;

        } catch (err) {
          console.error('[AUTH] authorize error:', err);
          return null;
        }
      },
    }),
  ],
  callbacks: {
    async jwt({ token, user }) {
      if (user) {
        token.userId = Number(user.id); // convert back to number for internal use
        token.username = user.username;
        token.userType = user.userType;
        token.deptId = user.deptId;
      }
      return token;
    },
    async session({ session, token }) {
      session.user.id = token.userId;
      session.user.username = token.username;
      session.user.userType = token.userType;
      session.user.deptId = token.deptId;
      return session;
    },
  },
  pages: {
    signIn: '/login',
    error: '/login',
  },
  session: {
    strategy: 'jwt',
    maxAge: 8 * 60 * 60,
  },
  debug: true,
};

export function getPortalForUser(userType: number, deptId: number): string {
  if (EXT_DEPT_IDS.includes(deptId)) return '/ext-dept';
  return '/dept';
}
