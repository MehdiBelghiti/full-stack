import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 4000,
    proxy: {
      '^/images': {
        target: 'http://localhost:8080' // Spring boot backend address
      }
    }
  },
  build: {
    outDir: 'target/dist',
    assetsDir: 'static'
  }
})
