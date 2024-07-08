import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 创建一个 pinia（根存储）
import { createPinia } from 'pinia'

const app = createApp(App)
// 将其传递给应用程序
app.use(router).use(ElementPlus).use(createPinia())
app.mount('#app')

// const express = require('express');
// const multer = require('multer');

// const app = express();
// const upload = multer({ dest: 'uploads/' });

// app.post('/upload', upload.single('image'), (req, res) => {
//     // 处理上传的文件，保存到数据库或其他操作
//     const imageUrl = `/uploads/${req.file.filename}`;

//     // 返回图片的 URL
//     res.json({ imageUrl });
// });

// app.listen(3000, () => {
//     console.log('Server is running on port 3000');
// });
