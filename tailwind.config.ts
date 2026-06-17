import type { Config } from 'tailwindcss';

const config: Config = {
  content: [
    './app/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      colors: {
        police: {
          50:  '#eef2ff',
          100: '#e0e7ff',
          300: '#93c5fd',
          500: '#1e40af',
          600: '#1e3a8a',
          700: '#1e3272',
          800: '#172554',
          900: '#1e3a5f',
        },
        banner: '#E0E1FC',
        gold: {
          400: '#d4a017',
          500: '#c5a028',
          600: '#b8860b',
        },
      },
    },
  },
  plugins: [],
};

export default config;
