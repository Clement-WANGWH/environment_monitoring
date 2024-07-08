<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <h1 class="info">头像设置</h1>
            </div>
        </template>
        <div class="tip">选择本地照片，上传编辑自己的头像</div>
        <div class="registerwin">
            <div class="touxiang">
                <el-upload class="avatar-uploader" :action="action" :show-file-list="false"
                    :before-upload="beforeAvatarUpload" :on-success="handleAvatarSuccess">
                    <img v-if="imageUrl != ''" :src="imageUrl ? imageUrl + '?t=' + (new Date().getTime()) : ''" class="avatar">
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
            </div>
            <el-button type="primary" :disabled="!imageUrl" @click="onSubmit(ruleFormRef)"
                style="margin-top: 20px;margin-left: 95px;">保存头像</el-button>
        </div>
    </el-card>
</template>
  
<script setup>
import { ref } from 'vue'
// import $axios from '../components/utils/axios'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import { useUserStore } from '../stores/user'
// import { storeToRefs } from 'pinia'
const userStore = useUserStore()

// const { form } = storeToRefs(userStore)

const imageUrl = ref('')

const action = 'http://127.0.0.1:18081/upload/imgggg/' + JSON.parse(sessionStorage.getItem('user')).username

const beforeAvatarUpload = (file) => {
    console.log(file);
    const isJPG = file.type === 'image/jpeg';
    // const isPNG = file.type === 'image/png';
    const isLt2M = file.size / 1024 / 1024 < 2;

    if (!isJPG) {
        ElMessage.error('只能上传 JPG/PNG 格式的图片');
        return false;
    }
    if (!isLt2M) {
        ElMessage.error('图片大小不能超过 2MB');
        return false;
    }
    return true
}

//图片回显
const handleAvatarSuccess = (res) => {
    // imageUrl.value = URL.createObjectURL(file.raw);
    // console.log(URL.createObjectURL(file.raw))
    // imageUrl.value = URL.createObjectURL(!uploadFile.raw)
    // setTimeout("upload(res)", 1000)
    // form.value.avatar = sessionStorage.getItem("imgUrl")
    // console.log(form.value.avatar);
    upload(res)
}

const upload = (res) => {
    console.log("success");
    imageUrl.value = res.result
    console.log(res);
    sessionStorage.setItem("imgUrl", imageUrl.value)
}

const onSubmit = () => {
    userStore.onSubmit()
}

/**
 *
 * @param { `file` 参数是当前要上传的文件对象，如果需要获取文件的二进制数据，可以通过 `file.raw` 来获取。} file
 * @param {*} fileList
 */
// const handleChange = (file, fileList) => {
//     fileList.value = [file]; // 更新 fileList 值
// };


</script>
<style scoped>
.box-card {
    width: 99%;
    height: 95%;
    margin-top: 12px;
    border-radius: 12px;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.tip {
    font-size: 15px;
    font-family: 'Times New Roman', Times, serif;
    margin-top: 20px;
    margin-left: 30px;
}

.avatar-uploader {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 120px;
    height: 120px;
    border-radius: 50%;
    border: 1px dashed #d9d9d9;
    overflow: hidden;
    position: relative;
    cursor: pointer;
    margin-left: 80px;
    margin-top: 30px;
}


.avatar {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}
</style>
  