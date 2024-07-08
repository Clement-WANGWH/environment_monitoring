<template>
  <div id="container">
    <div id="header">
      <div class="logo">
        <img src="../assets/img/nepmLogo.gif" alt="logo">
      </div>
      <div class="title">
        <div class="h1">环保公众监督系统</div>
      </div>
      <div class="link">
        <el-button class="plain-button" text @click="toLogin" style="color: rgb(99 184 255) !important;"
          link>登录</el-button>
      </div>
    </div>
    <div id="body">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <h1 class="info">账户信息</h1>
          </div>
        </template>
        <div class="registerwin">
          <!-- 头像上传 -->
          <el-form :model="form" ref="ruleFormRef" :rules="rules" label-width="120px" :size="formSize" status-icon>
            <!-- <div class="touxiang">
              <el-form-item prop="imageUrl" label-width="0">
                <el-upload class="avatar-uploader" action="/upload/img" :value="fileList" :show-file-list="false"
                  :on-change="handleChange" :before-upload="beforeAvatarUpload" :on-success="handleAvatarSuccess">
                  <img v-if="imageUrl != ''" :src="imageUrl" class="avatar">
                  <el-icon v-else class="avatar-uploader-icon">
                    <Plus />
                  </el-icon>
                </el-upload>
              </el-form-item>
            </div> -->
            <div class="registerform">
              <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username" class="input" placeholder="请输入用户名" />
              </el-form-item>
              <el-form-item label="姓名" prop="name">
                <el-input v-model="form.name" class="input" placeholder="请输入真实姓名" />
              </el-form-item>
              <el-form-item label="手机号码" prop="telephone">
                <el-input v-model="form.telephone" class="input" placeholder="请输入11位手机号" />
              </el-form-item>
              <!-- <el-form-item label="出生日期" prop="birthday">
                <el-date-picker v-model="form.birthday" type="date" placeholder="选择出生日期" style="width: 300px" />
              </el-form-item>
              <el-form-item label="性别" prop="sex">
                <el-radio-group v-model="form.sex">
                  <el-radio label="0">男</el-radio>
                  <el-radio label="1">女</el-radio>
                </el-radio-group>
              </el-form-item> -->
              <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="form.password" placeholder="请输入密码" class="input" />
              </el-form-item>
              <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="form.checkPass" placeholder="确认密码" class="input" />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit(ruleFormRef)">注册</el-button>
                <el-button @click="resetForm(ruleFormRef)">重置</el-button>
              </el-form-item>

            </div>
          </el-form>
        </div>
      </el-card>
    </div>
    <div id="footer">
      <img src="../assets/img/footer.png" alt="" class="imgFooter">
    </div>
  </div>
</template>
  
<script setup>
import { ref, reactive, onBeforeUnmount } from 'vue'
//   import axios from 'axios'
import $axios from '../components/utils/axios'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router';
// import { Plus } from '@element-plus/icons-vue'

const router = useRouter()

const toLogin = () => {
  router.push("/login")
}

const formSize = ref('default')
const ruleFormRef = ref()
//表单 密码双重验证规则
const validatePass2 = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入确认密码！'))
  } else if (value !== form.value.password) {
    callback(new Error("确认密码与密码不匹配！"))
  } else {
    callback()
  }
}
//其他规则
const rules = reactive({
  username: [
    {
      required: true, //必填规格
      message: '请输入用户名', //违反规则提示的信息
      trigger: 'blur' //失去焦点时触发
    },
    {
      min: 5,
      max: 10,
      message: '5-10个数字或字母组合',
      trigger: 'blur'
    }
  ],
  name: [
    {
      required: true, //必填规格
      message: '请输入真实姓名', //违反规则提示的信息
      trigger: 'blur' //失去焦点时触发
    }
  ],
  telephone: [
    {
      required: true, //必填规格
      message: '请输入手机号', //违反规则提示的信息
      trigger: 'blur' //失去焦点时触发
    },
    {
      pattern: /^1[3456789]\d{9}$/,
      message: '手机号码格式不正确',
      trigger: 'blur'
    }
  ],
  password: [
    {
      required: true, //必填规格
      message: '请输入密码', //违反规则提示的信息
      trigger: 'blur' //失去焦点时触发
    },
    {
      min: 5,
      max: 10,
      message: '5-10个字符',
      trigger: 'blur'
    }
  ],
  checkPass: [
    { validator: validatePass2, required: true, trigger: 'blur' }
  ]
})

// do not use same name with ref
const form = ref({})

// const imageUrl = ref('');
// const fileList = ref([]);
// // 创建一个FormData对象来处理头像文件
// // const avatarFormData = new FormData();
// // 头像
// const beforeAvatarUpload = (file) => {
//   const isJPG = file.type === 'image/jpeg';
//   const isPNG = file.type === 'image/png';
//   const isLt2M = file.size / 1024 / 1024 < 2;

//   if (!isJPG && !isPNG) {
//     ElMessage.error('只能上传 JPG/PNG 格式的图片');
//     return false;
//   }
//   if (!isLt2M) {
//     ElMessage.error('图片大小不能超过 2MB');
//     return false;
//   }

//   // imageUrl.value = '';
//   // avatarFormData.value.delete('avatar');
//   // avatarFormData.value.append('avatar', file);
//   fileList.value = [file];
//   form.value.avatar = file.raw;

//   return false; // 返回 false 禁止自动上传
//   // return true; // 返回 true 允许自动上传
// };

// /**
//  * 
//  * @param { `file` 参数是当前要上传的文件对象，如果需要获取文件的二进制数据，可以通过 `file.raw` 来获取。} file 
//  * @param {*} fileList 
//  */
// const handleChange = (file, fileList) => {
//   fileList.value = [file]; // 更新 fileList 值
// };

// /**
//  * 
//  * @param {*`response` 参数应该是上传成功后的响应数据，如果响应数据中包含了头像图片的地址，可以通过 `response.data.url` 获取到图片的链接} response 
//  */
// const handleAvatarSuccess = (response) => {
//   imageUrl.value = response.data.url; // 根据后端接口返回的图片链接字段进行修改
// };

const countdown = ref(5)// 倒计时秒数，默认为 5
let timer; // 定时器
const onSubmit = async (formEl) => {
  if (!formEl) return
  // 在提交数据之前对所有的数据进行一次统一的验证
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      // 所以的数据已经通过验证
      console.log(form.value)
      const path = '/user/register'

      // 发送注册请求
      const response = await $axios.put(path, form.value);
      console.log(form.value);
      const res = response.data;

      if (res.code === 200) {
        // 倒计时逻辑
        countdown.value = 5; // 重置倒计时秒数
        timer = setInterval(() => {
          if (countdown.value > 1) {
            countdown.value--;
          } else {
            clearInterval(timer);
            router.push('/login');
          }
        }, 1000);
        ElMessage({
          showClose: true,
          message: `注册成功，${countdown.value}秒后跳转到登录页面。`,
          type: 'success',
        })

      } else if (res.code === 101) {
        ElMessage({
          showClose: true,
          message: '注册失败！',
          type: 'error',
        })
      } else if (res.code === 104) {
        ElMessage({
          showClose: true,
          message: '用户名已被注册！',
          type: 'error',
        })
      } else if (res.code === 105) {
        ElMessage({
          showClose: true,
          message: '手机号已被注册!',
          type: 'error',
        })
      } else if (res.code === 106) {
        ElMessage({
          showClose: true,
          message: '头像上传失败!',
          type: 'error',
        })
      }

    } else {
      console.log('error submit!', fields)
    }
  })
}

// 在组件销毁或离开时清除 timer
onBeforeUnmount(() => {
  clearInterval(timer);
});

const resetForm = (formEl) => {
  formEl.resetFields()
  if (!formEl) return
  formEl.resetFields()
}
</script>
<style lang="scss" scoped>
#container {
  display: flex;
  width: 100%;
  height: 150%;

  #header {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 10%;
    // background-color: rgb(196, 220, 114);

    .logo {
      position: absolute;
      top: 45%;
      left: 200px;
      transform: translate(-50%, -50%);

      img {
        width: 70%;
        height: 70%;
        margin-top: 10px;
      }
    }

    .title {
      position: absolute;
      display: flex;
      margin-left: 230px;
      align-items: center;
      height: 100%;
      width: 100%;
      font-size: 25px;
      color: black;
    }

    .link {
      position: absolute;
      display: flex;
      align-items: center;
      margin-left: 1400px;
      margin-top: 25px;

      .plain-button {
        background-color: transparent;
        font-size: 16px;

      }
    }
  }

  #body {
    position: absolute;
    margin-top: 75px;
    height: 90%;
    width: 100%;
    background-size: 100%;
    background-repeat: no-repeat;
    background-position-x: center;
    background-image: url("../assets/img/wetlands.png");


    .box-card {
      width: 600px;
      // height: 520px;
      margin: 0 auto;
      margin-top: 25px;
      overflow: hidden;
      border: 1px solid #b3e19d;
      border-top-left-radius: 7px;
      border-top-right-radius: 7px;
      font-size: 13px;

      .card-header {
        display: flex;

        .info {
          width: 100%;
          font-size: 20px;
          color: rgb(196, 220, 114);
        }

      }

      .registerwin {
        margin-left: 20px;
        padding: 20px;
        line-height: 35px;

        .touxiang {
          position: absolute;


          .avatar-uploader {
            // position: absolute;
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
            margin-left: 50px;


            .avatar {
              width: 100%;
              height: 100%;
              object-fit: cover;
            }

            .el-icon.avatar-uploader-icon {
              font-size: 28px;
              color: #8c939d;
              position: absolute;
              top: 50%;
              left: 50%;
              transform: translate(-50%, -50%);
            }
          }
        }

        .registerform {
          // position: absolute;
          // display: flex;
          // margin-left: 180px;


          .input {
            width: 300px;
          }
        }
      }
    }
  }

  #footer {
    position: absolute;
    margin-top: 100px;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 20%;

    .imgFooter {
      position: relative;
      margin-top: 30px;
      display: flex;
      width: 100%;
      height: 300%;
    }
  }
}
</style>
  