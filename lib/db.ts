import mysql from 'mysql2/promise';

// User/auth database: common_user_police (localhost)
const pool = mysql.createPool({
  host: process.env.DB_HOST,
  port: Number(process.env.DB_PORT) || 3306,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME,
  waitForConnections: true,
  connectionLimit: 10,
  timezone: '+00:00',
});

// Application database: police_db (remote server 10.250.5.130)
const appPool = mysql.createPool({
  host: process.env.APP_DB_HOST,
  port: Number(process.env.APP_DB_PORT) || 3306,
  user: process.env.APP_DB_USER,
  password: process.env.APP_DB_PASSWORD,
  database: process.env.APP_DB_NAME,
  waitForConnections: true,
  connectionLimit: 10,
  timezone: '+00:00',
});

export default pool;
export { appPool };
