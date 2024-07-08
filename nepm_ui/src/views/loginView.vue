<template>
  <div id="container">
    <div id="head">
      <div class="logo">
        <img src="../assets/img/nepmLogo.gif" alt="logo">
      </div>
      <div class="title">
        <div class="h1">环保公众监督系统</div>
      </div>
    </div>

    <div id="body">
      <el-carousel height="600px" width="100%" indicator-position="outside">
        <el-carousel-item v-for="item in carouselImg" :key="item">
          <img :src="item.url" alt="" class="carouselImg">
        </el-carousel-item>
      </el-carousel>
      <div class="loginwin">
        <el-card class="box-card">
          <template #header>
            <div class="card-header">
              <h1 class="align-center">登录</h1>
            </div>
          </template>
          <div class="loginform">
            <el-form :model="ruleForm" ref="ruleFormRef" class="demo-ruleForm" :size="formSize" status-icon
              :rules="rules">
              <el-form-item prop="loginName">
                <el-input v-model="ruleForm.loginName" size="large" placeholder="请输入用户名或手机号" :prefix-icon="Avatar" />
              </el-form-item>
              <el-form-item prop="checkPass">
                <el-input v-model="ruleForm.password" size="large" placeholder="请输入密码" :prefix-icon="Lock" type="password"
                  show-password />
              </el-form-item>
              <el-form-item>
                <div class="btn">
                  <el-button type="primary" @click="submitForm(ruleFormRef)" :icon="Promotion">
                    立即登录
                  </el-button>
                  <el-button type="text" @click="register" style="margin-left: 20px;">注册</el-button>
                </div>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </div>
    </div>
    <div id="footer">
      <img src="../assets/img/footer.png" alt="" class="imgFooter">
    </div>
  </div>
</template>
  
<script setup>
import { Avatar, Lock, Promotion } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus';
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router';
import $axios from '../components/utils/axios'
import wetlands from "@/assets/img/wetlands.png";
import tree from "@/assets/img/tree.jpg";
import ocean from '@/assets/img/ocean.png';


const carouselImg = [
  { url: wetlands },
  { url: ocean },
  { url: tree }
]


const router = useRouter()

const formSize = ref('default')

//在vue3中通过ref属性获得对象的方法
const ruleFormRef = ref()
const ruleForm = ref({
  // loginName: '13111111111',
  // password: '33333'
})

/**
 *定义验证规则
 * @param {*} rule
 * @param {*} value 当前文本框返回的值
 * @param {*} callback 回调函数，通知Form验证结果
 * 返回Error失败  callback(new Error('Please input the password'))
 * new Error( msg--  提示信息 )
 * 返回null 成功  callback()
 */
const validatePass2 = (rule, value, callback) => {
  if (
    !ruleForm.value.password ||
    ruleForm.value.password.length == 0
  )
    callback(new Error('请输入密码'))
  // console.log(ruleForm.value.password)
  if (ruleForm.value.password === '')
    callback(new Error('请输入密码'))
  else callback()
}

const rules = reactive({
  loginName: [
    {
      required: true, //必填规格
      message: '请输入用户名或手机号', //违反规则提示的信息
      trigger: 'blur' //失去焦点时触发
    },
    {
      min: 4,
      max: 11,
      message: '4-11个字符',
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
    { validator: validatePass2, trigger: 'blur' }
  ]
})

const submitForm = async (formEl) => {
  if (!formEl) return
  // 在提交数据之前对所有的数据进行一次统一的验证
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      // 所以的数据已经通过验证
      console.log(ruleForm.value)
      const path = '/user/login'
      //  发生 普通的 表单格式的 Post 请求
      const { data: res } = await $axios.post(path, ruleForm.value, {
        headers: {
          //  调用axios，实现JavaScript对象转换为FormData数据
          'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',//将表单数据传递转化为form-data类型
          Authorization: sessionStorage.getItem('token')
        }
      }
      )
      console.log(res);
      if (res.code === 200) {
        let user = res.result
        let jsonstr = JSON.stringify(user)
        sessionStorage.setItem('user', jsonstr)
        sessionStorage.setItem('id', user.id + '')
        localStorage.setItem('id', user.id + '')
        sessionStorage.setItem('token', user.token)
        sessionStorage.setItem('roleId', user.roleId + '')
        sessionStorage.setItem('avatar', user.avatar)

        sessionStorage.setItem('isNotified', false)
        // 使用编程式路由实现页面跳转
        if (user.roleId === 3) {
          router.push('/IndividualCenter')
        }
        else if (user.roleId === 2) {
          router.push('/IndividualCenter')
        }
        else if (user.roleId === 1) {
          router.push('/ChinaCharts')
        }
        // router.push('/')
      }
      else if (res.code === 100) {
        ElMessage.error("密码错误！")
      }
    } else {
      console.log('error submit!', fields)
    }
  })
}

const register = () => {
  router.push('/register')
}

// const resetForm = (formEl) => {
//   formEl.resetFields()
//   if (!formEl) return
//   formEl.resetFields()
// }
</script>
  
<style lang="scss" scoped>
#container {
  display: flex;
  width: 100%;
  height: 150%;

  #head {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 10%;

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
      position: relative;
      display: flex;
      left: 230px;
      align-items: center;
      height: 100%;
      width: 100%;
      font-size: 25px;
      color: black;
    }
  }

  #body {
    position: absolute;
    margin-top: 75px;
    height: 90%;
    width: 100%;
    background-color: rgb(245, 245, 245);

    .carouselImg {
      border: 1px solid #ccc;
      border-radius: 4px;
      overflow: hidden;
      width: 100%;
      height: 100%;
    }

    .loginwin {
      width: 400px;
      height: 300px;
      position: absolute;
      left: 45%;
      top: 40%;
      transform: translate(-50%, -50%);
      border-radius: 10px;



      .box-card {
        width: 400px;
        height: 300px;
        margin: 0 auto;
        float: inline-start;
        text-align: center;
        border-radius: 15px;
        background: #F0F8FF;
        opacity: 0.85;
        align-items: center;

        .card-header {
          display: flex;

          .align-center {
            width: 100%;
            font-size: 25px;
            text-align: center;
            color: #4682B4;
          }
        }

        .loginform {
          padding: 30px;
        }
      }
    }
  }

  #footer {
    position: absolute;
    margin-top: 200px;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 20%;

    .imgFooter {
      position: relative;
      margin-top: 50px;
      display: flex;
      width: 100%;
      height: 300%;
    }
  }
}
</style>
  