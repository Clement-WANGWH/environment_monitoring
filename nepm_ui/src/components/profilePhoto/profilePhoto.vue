<template>
    <div>
        <input type="file" @change="uploadImage" accept="image/*" />
    </div>
</template>

<script>
export default {
    methods: {
        async uploadImage(event) {
            const file = event.target.files[0];
            const formData = new FormData();
            formData.append('image', file);

            // 发送请求将文件上传到服务器
            const response = await fetch('/upload', {
                method: 'POST',
                body: formData,
            });

            // 解析服务器返回的响应数据
            const data = await response.json();

            // 将图片的 URL 存储到数据库中或进行其他操作
            const imageUrl = data.imageUrl;
            // ...
        },
    },
};
</script>