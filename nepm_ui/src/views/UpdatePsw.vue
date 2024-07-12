<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <h1 class="info">设置密码</h1>
            </div>
        </template>
        <div class="registerwin">
           
            <el-form :model="form" label-width="120px" ref="ruleFormRef" :rules="rules" :size="formSize" status-icon>
                <div class="registerform">
                    <el-form-item label="用户名" prop="username">
                        <el-input disabled v-model="username" class="input" />
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="form.password" placeholder="请输入密码" class="input" />
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass">
                        <el-input type="password" v-model="form.checkPass" placeholder="确认密码" class="input" />
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="onSave(ruleFormRef)" 
                            style="margin-top: 15px;">保存</el-button>
                    </el-form-item>

                </div>
            </el-form>
        </div>
    </el-card>
</template>
  
<script setup>
import { ref, reactive } from 'vue'
import $axios from '../components/utils/axios'
import { ElNotification } from 'element-plus'

const form = ref({})
form.value.id = sessionStorage.getItem('id')
const username = ref('')
username.value = JSON.parse(sessionStorage.getItem('user')).username

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

const onSave = async (formEl) => {
    if (!formEl) return
    // 在提交数据之前对所有的数据进行一次统一的验证
    await formEl.validate(async (valid, fields) => {
        if (valid) {
            console.log(form.value)
            const path = '/user/update'
            const { data: res } = await $axios.post(path, form.value);
            if (res.code === 200) {
                ElNotification({
                    title: '成功',
                    message: '已成功修改登录密码。',
                    type: 'success',
                })
            } else if (res.code === 103) {
                ElNotification({
                    title: '失败',
                    message: '未知原因，修改失败！',
                    type: 'error',
                })
            }

        } else {
            console.log('error submit!', fields)
        }
    })
}
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

.h {
    font-size: 25px;
    margin-top: 15px;
    margin-left: 25px;
}

.registerform {
    margin-top: 50px;
}

.input {
    width: 300px;
}
</style>
  