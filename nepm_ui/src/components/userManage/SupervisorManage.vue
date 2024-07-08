<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span>公众监督员管理</span>
                <div class="mt-4">
                    <el-input v-model="name" placeholder="请输入监督员姓名" class="input-with-select">
                        <template #append>
                            <el-button @click="btn_loadbyno" :icon="Search" />
                        </template>
                    </el-input>
                </div>
            </div>
        </template>
        <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
            <el-table-column prop="id" label="监督员编号" />
            <el-table-column prop="username" label="用户名" />
            <el-table-column prop="name" label="姓名" />
            <el-table-column prop="sex" label="性别">
                <template #default="scope">
                    <p v-if="scope.row.sex == '0'">男</p>
                    <p v-if="scope.row.sex == '1'">女</p>
                </template>
            </el-table-column>
            <el-table-column prop="birthday" label="出生日期" />
            <el-table-column prop="telephone" label="联系电话" />
            <el-table-column label="操作" width="180">
                <template #default="scope">
                    <!-- <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button> -->
                    <el-popconfirm width="320" confirm-button-text="确定" cancel-button-text="取消" :icon="InfoFilled"
                        icon-color="#626AEF" :title="'确定升级为网格员吗?'" @confirm="transfer(scope.$index, scope.row)">
                        <template #reference>
                            <el-button size="small" type="success">升级为网格员</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
    </el-card>

    <!-- 指派网格 -->
    <el-dialog v-model="transDialogVisible" title="指派网格" align-center width="30%">
        <el-form :model="transForm" ref="ruleFormRef" label-width="120px">
            <el-form-item label="姓名">
                <el-input v-model="tname" style="width: 200px;" disabled />
            </el-form-item>
            <el-form-item label="指派网格">
                <AddressSelect @selectChanged="selectChanged1" :labelWidth="labelWidth" :selectWidth="selectWidth" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="cancelTrans(ruleFormRef)">取消</el-button>
                <el-button type="primary" @click="onTransfer">
                    升级
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref } from 'vue'
import { ElNotification } from 'element-plus'
import { InfoFilled, Search } from '@element-plus/icons-vue'
import $axios from '../utils/axios'
import AddressSelect from '../supervisor/addressSelect.vue';

// const visible = ref(false)
//初始化
const tableData = ref([])
const init = async () => {
    const path = '/user/findSup'
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
    }
}
init()
//斑马条
const tableRowClassName = ({ rowIndex }) => {
    return rowIndex % 2 === 1 ? 'success-row' : ''
}
//搜索
const name = ref()
const btn_loadbyno = async () => {
    const path = '/user/findByName/' + name.value
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
    }
}
//更改身份
const transDialogVisible = ref(false)
const transForm = ref({})
let selectWidth = "150px"
let labelWidth = "50px"
const transGrid = ref({
    province: { id: "", name: "", leaderId: "" },
    city: { id: "", name: "", leaderId: "" },
    town: { id: "", name: "", leaderId: "" }
})
const selectChanged1 = (f) => {
    transGrid.value = f
    console.log(transGrid.value);
    transForm.value.pId = transGrid.value.province.id
    transForm.value.cId = transGrid.value.city.id
    transForm.value.tId = transGrid.value.town.id

}
// let transIndex = -1
let tname
const transfer = async (index, row) => {
    transDialogVisible.value = true
    console.log(index, row)
    tname = row.name
    transForm.value.id = row.id
}
const onTransfer = async () => {
    transForm.value.roleId = 2
    transForm.value.state = 0
    console.log(transForm.value);
    const path = '/user/update'
    const { data: res } = await $axios.post(path, transForm.value)
    if (res.code === 200) {
        init()
        ElNotification({
            title: '成功',
            message: '已将' + tname + '升级为网格员。',
            type: 'success',
        })
    } else if (res.code === 103) {
        ElNotification({
            title: '失败',
            message: '未知原因，升级失败！',
            type: 'error',
        })
    }
    transDialogVisible.value = false
}
const cancelTrans = (formEl) => {
    transDialogVisible.value = false
    formEl.resetFields()
    if (!formEl) return
    // formEl.resetFields()
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
</style>

<style>
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>