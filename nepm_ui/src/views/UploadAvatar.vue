<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <h1 class="info">头像设置</h1>
            </div>
        </template>
        <div class="tip">选择本地照片，上传编辑自己的头像</div>
        <div class="registerwin">
            <!-- 头像上传 -->
            <!-- <el-form :model="form" ref="ruleFormRef" :rules="rules" label-width="120px" :size="formSize" status-icon> -->
            <div class="touxiang">
                <!-- <el-form-item prop="avatar" label-width="0"> -->
                <el-upload class="avatar-uploader" :action="action" :show-file-list="false"
                    :before-upload="beforeAvatarUpload" :on-success="handleAvatarSuccess">
                    <img v-if="imageUrl != ''" :src="imageUrl" class="avatar">
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
                <!-- </el-form-item> -->
            </div>
            <!-- <el-form-item> -->
            <el-button type="primary" :disabled="!imageUrl" @click="onSubmit(ruleFormRef)"
                style="margin-top: 20px;margin-left: 95px;">保存头像</el-button>
            <!-- <el-button @click="resetForm(ruleFormRef)">重置</el-button> -->
            <!-- </el-form-item> -->
            <!-- </el-form> -->
        </div>
    </el-card>
</template>
  
<script setup>
import { ref } from 'vue'
import $axios from '../components/utils/axios'
import { ElMessage, ElNotification } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'

const form = ref({})

const imageUrl = ref('');

const action = 'http://127.0.0.1:18081/upload/imgggg/' + JSON.parse(sessionStorage.getItem('user')).username

const beforeAvatarUpload = (file) => {
    console.log(file);
    // const isJPG = file.type === 'image/jpeg';
    // const isPNG = file.type === 'image/png';
    const isLt2M = file.size / 1024 / 1024 < 2;

    // if (!isJPG && !isPNG) {
    //     ElMessage.error('只能上传 JPG/PNG 格式的图片');
    //     return false;
    // }
    if (!isLt2M) {
        ElMessage.error('图片大小不能超过 2MB');
        // return false;
    }
    return isLt2M
}

//图片回显
const handleAvatarSuccess = (res) => {
    // imageUrl.value = URL.createObjectURL(!uploadFile.raw)
    imageUrl.value = res.result
    console.log(res);
    sessionStorage.setItem("imgUrl", imageUrl.value)
    // form.value.avatar = sessionStorage.getItem("imgUrl")
    // console.log(form.value.avatar);
}


const onSubmit = async () => {
    form.value.id = sessionStorage.getItem('id')
    form.value.username = JSON.parse(sessionStorage.getItem('user')).username
    console.log(form.value);
    const path = '/user/uploadAvatar'
    const { data: res } = await $axios.post(path, form.value);
    if (res.code === 200) {
        ElNotification({
            title: '成功',
            message: '头像已保存。',
            type: 'success',
        })
    } else if (res.code === 106) {
        ElNotification({
            title: '失败',
            message: '头像上传失败!',
            type: 'error',
        })
    }
}

// 创建一个FormData对象来处理头像文件
// const avatarFormData = new FormData();
// 头像
// const beforeAvatarUpload = (file) => {
//     const isJPG = file.type === 'image/jpeg';
//     const isPNG = file.type === 'image/png';
//     const isLt2M = file.size / 1024 / 1024 < 2;

//     if (!isJPG && !isPNG) {
//         ElMessage.error('只能上传 JPG/PNG 格式的图片');
//         return false;
//     }
//     if (!isLt2M) {
//         ElMessage.error('图片大小不能超过 2MB');
//         return false;
//     }

//     // imageUrl.value = '';
//     // avatarFormData.value.delete('avatar');
//     // avatarFormData.value.append('avatar', file);
//     fileList.value = [file];
//     form.value.avatar = file.raw;

//     return false; // 返回 false 禁止自动上传
//     // return true; // 返回 true 允许自动上传
// };

/**
 *
 * @param { `file` 参数是当前要上传的文件对象，如果需要获取文件的二进制数据，可以通过 `file.raw` 来获取。} file
 * @param {*} fileList
 */
// const handleChange = (file, fileList) => {
//     fileList.value = [file]; // 更新 fileList 值
// };

/**
 *
 * @param {*`response` 参数应该是上传成功后的响应数据，如果响应数据中包含了头像图片的地址，可以通过 `response.data.url` 获取到图片的链接} response
 */
// const handleAvatarSuccess = (response) => {
//     imageUrl.value = response.data.url; // 根据后端接口返回的图片链接字段进行修改
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
  