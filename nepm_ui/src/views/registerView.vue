<template>
    <div class="registration-content">
		<div class="form-column">
			<div class="form-space">
				<el-form :model="form" ref="ruleFormRef" :rules="rules" class="registration-form" status-icon>
					<h1 class="form-title"><b>携手共建<span style="color: #558431;">绿色家园</span></b></h1>
					<div class="form-group">
						<label for="username" class="form-label">用户名</label>
						<div class="form-input">
							<el-input v-model="form.username" id="username" type="text" placeholder="输入注册名" />
						</div>
					</div>
					<div class="form-group">
						<label for="fullname" class="form-label">姓名</label>
						<div class="form-input" >
							<el-input v-model="form.name" id="fullname" type="text" placeholder="输入真实姓名" />
						</div>
					</div>
					<div class="form-group">
						<label for="phone" class="form-label">手机号码</label>
						<div class="form-input">
							<el-input v-model="form.telephone" id="phone" type="tel" placeholder="输入手机号码" />
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="form-label">密码</label>
						<el-input v-model="form.password" id="password" type="password" placeholder="输入密码" />
					</div>
					<div class="terms-agreement">
						<input type="checkbox" id="terms" class="terms-checkbox" />
						<label for="terms" class="terms-label">我同意相关<span style="text-decoration: underline;">条款和政策</span></label>
					</div>
					<el-button type="primary" @click="onSubmit(ruleFormRef)" class="submit-button">注册</el-button>
					<div class="divider">Or</div>
					<p class="login-link">已经有一个账户了？<a @click="toLogin" class="login-text">直接登录</a></p>
				</el-form>
			</div>
		</div>
		<div class="image-column">
			<img src="https://cdn.builder.io/api/v1/image/assets/TEMP/57c85c5a57a97e25f62108dae6bb441d5118360e489d82af80aad3460ca9f171?apiKey=536243e05bac4c5a824f9994313fcba8&" alt="Registration illustration" class="registration-image" />
		</div>
    </div>
</template>
	
<script setup>
import { ref, reactive, onBeforeUnmount } from 'vue'
import $axios from '../components/utils/axios'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router';
	
const router = useRouter()
const formSize = ref('default')
const ruleFormRef = ref()
const form = ref({})
	
const toLogin = () => {
	router.push("/login")
}

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
  ]
})

const countdown = ref(5)
let timer;
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
      }
    } else {
      console.log('error submit!', fields)
    }
  })
}

onBeforeUnmount(() => {
  clearInterval(timer);
});
</script>	

<style scoped>
.registration-content {
  display: flex;
  width: 100vw;
}

.form-column {
  width: calc(100vw - 75vh);
  display: flex;
  justify-content: center;
}

.form-space {
  width: 56%;
  margin: 0;
  height: 100vh;
}

.registration-form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
}

.form-title {
  margin-bottom: 63px;
  font-size: 42px;
  font-weight: 800;
}

.form-group {
  margin-bottom: 18px;
  width: 100%;
}

.form-label {
  display: block;
  font-size: 12px;
  font-weight: 500;
  margin-bottom: 6px;
  margin-left: 2px;
}

.form-input {
  width: calc(56vw - 42vh);
  border-radius: 10px;
}
:deep(.el-input__wrapper){
	border: 1px solid #d9d9d9;
	border-radius: 10px;
	font-size: 9px;
}

.password-input{
	width: calc(56vw - 42vh);
	border-radius: 10px;
	border: 1px solid #d9d9d9;
	font-size: 9px;
}

.terms-agreement {
  display: flex;
  align-items: center;
  gap: 6px;
  margin-top: 15px;
}

.terms-checkbox {
  width: 9px;
  height: 10px;
  border-radius: 2px;
  border: 1px solid #000;
}

.terms-label {
  font-size: 8px;
}

.submit-button {
  width: 100%;
  background-color: #3a5b22;
  color: #fff;
  border: none;
  border-radius: 10px;
  padding: 7px 10px;
  font-size: 11px;
  margin-top: 8px;
}

.divider {
  display: flex;
  align-items: center;
  text-align: center;
  margin: 59px 0 35px;
  font-size:10px;
  font-weight:400;
}

.divider::before,
.divider::after {
  content: '';
  flex: 1;
  border-bottom: 1px solid #d6d6d6;
}

.divider::before {
  margin-right: 10px;
}

.divider::after {
  margin-left: 10px;
}

.login-link {
  text-align: center;
  font-size: 14px;
  font-weight: 550;
}

.login-text {
	color: #0f3dde;
	text-decoration:none;
}

.image-column {
  width: 75vh;
}

.registration-image {
  width: 100%;
  height: 100vh;
  object-fit: contain;
  object-position: right;
}

@media (max-width: 991px) {
  .registration-container {
    padding: 0 20px;
  }

  .registration-content {
    flex-direction: column;
  }

  .form-column,
  .image-column {
    width: 100%;
  }

  .form-title {
    margin-top: 40px;
    margin-bottom: 40px;
  }

  .divider {
    margin: 40px 0;
  }

  .registration-image {
    max-width: 100%;
    margin-top: 40px;
  }
}

@media (max-width: 640px) {
  .registration-image {
    display: none;
  }
}
</style>