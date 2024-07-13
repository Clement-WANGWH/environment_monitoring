<template>
	<div class="logo">
		<div class="logo-column">
			<img src="../assets/img/logo.png" class="logo-icon">
		</div>
		<lable class="website-name">环保公众监督系统</lable>
	</div>
    <div class="registration-content">
		<div class="form-column">
			<div class="form-space">
				<el-form :model="ruleForm" ref="ruleFormRef" status-icon :rules="rules" class="registration-form">
					<h1 class="form-title"><b><span style="color: #558431;">绿网恢恢</span>疏而不漏</b></h1>
					<div class="form-group">
						<label class="form-label">账户</label>
						<div class="el-input">
							<el-form-item prop="loginName">
								<el-input v-model="ruleForm.loginName" type="text" placeholder="输入注册名或手机号码" />
							</el-form-item>
						</div>
					</div>
					<div class="form-group">
						<label class="form-label">密码</label>
						<el-form-item prop="checkPass">
							<el-input v-model="ruleForm.password" type="password" class="form-input" placeholder="输入密码" show-password/>
						</el-form-item>
					</div>
					<div class="terms-agreement">
						<input v-model="ruleForm.check" type="checkbox" name="agree" class="terms-checkbox" />
						<label for="agree" class="terms-label">我同意相关<span style="text-decoration: underline;">条款和政策</span></label>
					</div>
					<el-form-item>
						<el-button type="primary" @click="submitForm(ruleFormRef)" class="submit-button">登录</el-button>
					</el-form-item>
			  
			  <div class="divider">Or</div>
			  <p class="login-link">
				还没有账户？<a @click="register" class="login-text">马上注册</a>
			  </p>
			</el-form>
			</div>
		</div>
		<div class="image-column">
			<img src="../assets/img/hummingbird.png" alt="Registration illustration" class="registration-image" />
		</div>
    </div>
</template>

<script setup>
	import { ElMessage } from 'element-plus';
	import { reactive, ref } from 'vue'
	import { useRouter } from 'vue-router';
	import $axios from '../components/utils/axios'
	
	const router = useRouter()
	
	const ruleFormRef = ref()
	const ruleForm = ref({
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
	      console.log(res.code);
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
	        if (user.roleId === 3) {
	          router.push('/SuperviseCenter')
	        }
	        else if (user.roleId === 2) {
	          router.push('/GridCenter')
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
	
	
</script>

<style scoped>
.logo {
  position: absolute;
  top: 7px;
  left: 7px;
  display: flex;
  align-items: center; /* 确保内部元素垂直居中 */
  gap: 6px; /* 元素间距 */
}

.logo-column {
  width: 30px;
  display: flex; /* 使内部img标签水平排布 */
  align-items: center; /* 垂直居中 */
}

.logo-icon {
  width: 100%;
  object-fit: contain; /* 保持图片比例 */
}

.website-name {
  color: grey; /* 灰色字体 */
  font-size: 12px;
}
	
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
  font-size: 15px;
  font-weight: 500;
  margin-bottom: 11px;
  margin-left: 2px;
}

.el-input {
  width: calc(56vw - 42vh);
  border-radius: 10px;
}
:deep(.el-input__wrapper){
	border: 1px solid #d9d9d9;
	border-radius: 10px;
	font-size: 12px;
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
  padding: 9px 10px;
  font: 700 13px;
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
  border-top-left-radius: 45px;
  border-bottom-left-radius: 45px;
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