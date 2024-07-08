<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <h1 class="info">个人信息</h1>
            </div>
        </template>
        <div class="registerwin">
            <el-form :model="form" label-width="120px" :size="formSize" status-icon>
                <div class="registerform">
                    <el-form-item label="用户名" prop="username">
                        <el-input disabled v-model="username" class="input" />
                    </el-form-item>
                    <el-form-item label="姓名" prop="name">
                        <el-input disabled v-model="name" class="input" />
                    </el-form-item>
                    <el-form-item label="手机号码" prop="telephone">
                        <el-input disabled v-model="telephone" class="input" />
                    </el-form-item>
                    <el-form-item label="出生日期" prop="birthday">
                        <el-date-picker v-model="form.birthday" type="date" placeholder="选择出生日期"
                            :disabledDate="hiredateDisabledDate" :shortcuts="hiredateShortcuts" format="YYYY-MM-DD"
                            value-format="YYYY-MM-DD" style="width: 300px" :disabled="disabled" />
                    </el-form-item>
                    <el-form-item label="性别" prop="sex">
                        <el-radio-group v-model="form.sex" :disabled="disabled">
                            <el-radio label="0">男</el-radio>
                            <el-radio label="1">女</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="onEdit" v-if="disabled">编辑</el-button>
                        <el-button @click="onSave" v-if="!disabled">保存</el-button>
                    </el-form-item>

                </div>
            </el-form>
        </div>
    </el-card>
</template>
  
<script setup>
import { ref } from 'vue'
//   import axios from 'axios'
import $axios from '../components/utils/axios'
import { ElNotification } from 'element-plus'
// import { Plus } from '@element-plus/icons-vue'

const form = ref({})
form.value.id = sessionStorage.getItem('id')
const username = ref('')
username.value = JSON.parse(sessionStorage.getItem('user')).username
const name = ref('')
name.value = JSON.parse(sessionStorage.getItem('user')).name
const telephone = ref('')
telephone.value = JSON.parse(sessionStorage.getItem('user')).telephone
form.value.sex = JSON.parse(sessionStorage.getItem('user')).sex
form.value.birthday = JSON.parse(sessionStorage.getItem('user')).birthday
//定义日历的禁用日期计算属性
const hiredateDisabledDate = (time) => time.getTime() > new Date().getTime();

//定义日期的快捷键 快速选择
const hiredateShortcuts = [
    {
        text: '昨天',
        value: () => {
            let now = new Date().getTime();
            now -= 86400000;
            return new Date().setTime(now);
        }
    }, {
        text: '前天',
        value: () => {
            let now = new Date().getTime();
            now -= 86400000 * 2;
            return new Date().setTime(now);
        }
    }, {
        text: '7天前',
        value: () => {
            let now = new Date().getTime();
            now -= 86400000 * 7;
            return new Date().setTime(now);
        }
    }
]

const formSize = ref('default')

const disabled = ref(true)

const onEdit = () => {
    disabled.value = false
}
const onSave = async () => {
    disabled.value = true
    console.log(form.value)
    const path = '/user/update'
    const { data: res } = await $axios.post(path, form.value);
    if (res.code === 200) {
        ElNotification({
            title: '成功',
            message: '已成功修改个人信息。',
            type: 'success',
        })
    } else if (res.code === 103) {
        ElNotification({
            title: '失败',
            message: '未知原因，修改失败！',
            type: 'error',
        })
    }

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

.input {
    width: 300px;
}
</style>
  