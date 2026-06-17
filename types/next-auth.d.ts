import 'next-auth';
import 'next-auth/jwt';

declare module 'next-auth' {
  interface User {
    id: string; // NextAuth requires string id
    username: string;
    userType: number;
    deptId: number;
  }

  interface Session {
    user: {
      id: number;
      name: string;
      email: string;
      username: string;
      userType: number;
      deptId: number;
    };
  }
}

declare module 'next-auth/jwt' {
  interface JWT {
    userId: number;
    username: string;
    userType: number;
    deptId: number;
  }
}
