<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span>网格员管理</span>
                <div style="display: flex">
                    <div class="mt-4">
                        <el-input v-model="name" placeholder="请输入网格员姓名" class="input-with-select">
                            <template #append>
                                <el-button @click="btn_loadbyname" :icon="Search" />
                            </template>
                        </el-input>
                    </div>
                    <el-button style="margin-left:20px" type="success" :icon="Plus" @click="btn_add">添加网格员</el-button>
                </div>
            </div>
        </template>
        <el-config-provider :locale="locale">
            <el-table :data="tableData" style="width: 100%" :table-layout="tableLayout" :row-class-name="tableRowClassName">
                <el-table-column prop="id" label="网格员编号" width="100px" />
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
                <el-table-column prop="grid" label="网格">
                    <template #default="scope">{{ scope.row.province.name }} {{ scope.row.city.name }} {{
                        scope.row.town.name }}
                    </template>
                </el-table-column>
                <el-table-column prop="state" label="状态" :filters="[
                    { text: '空闲', value: 0 },
                    { text: '工作中', value: 1 },
                    { text: '休假中', value: 2 },
                ]" :filter-method="filterState" filter-placement="bottom-end">
                    <template #default="scope">
                        <p v-if="scope.row.state == '0'">空闲</p>
                        <p v-if="scope.row.state == '1'">工作中</p>
                        <p v-if="scope.row.state == '2'">休假中</p>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template #default="scope">
                        <!-- <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button> -->
                        <el-button size="small" @click="transfer(scope.$index, scope.row)" type="primary">调动</el-button>
                        <el-button style="width: 70px;" size="small" type="warning"
                            @click="holiday(scope.$index, scope.row)" v-if="scope.row.state != '2'">休假</el-button>
                        <el-button style="width: 70px;" size="small" type="warning" @click="back(scope.$index, scope.row)"
                            v-if="scope.row.state == '2'">结束休假</el-button>
                        <el-popconfirm width="320" confirm-button-text="确定" cancel-button-text="取消" :icon="InfoFilled"
                            icon-color="#626AEF" :title="'确定要注销吗?'" @confirm="logout(scope.$index, scope.row)">
                            <template #reference>
                                <el-button size="small" type="danger">注销</el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>
        </el-config-provider>
    </el-card>

    <!-- 新增网格员 -->
    <el-dialog v-model="addDialogVisible" title="新增网格员" align-center width="30%">
        <el-form :model="form" ref="ruleFormRef" label-width="120px">
            <!-- <el-row>
            <el-col span="12"> -->
            <el-form-item label="用户名">
                <el-input v-model="form.username" style="width: 200px;" />
            </el-form-item>
            <!-- </el-col>
                <el-col span="12"> -->
            <el-form-item label="姓名">
                <el-input v-model="form.name" style="width: 200px;" />
            </el-form-item>
            <!-- </el-col>
            </el-row>
            <el-row>
                <el-col span="12"> -->
            <el-form-item label="性别">
                <el-radio-group v-model="form.sex" style="width: 200px;">
                    <el-radio label="0">男</el-radio>
                    <el-radio label="1">女</el-radio>
                </el-radio-group>
            </el-form-item>
            <!-- </el-col>
                <el-col span="12"> -->
            <el-form-item label="联系电话">
                <el-input v-model="form.telephone" style="width: 200px;" />
            </el-form-item>
            <el-form-item label="出生日期">
                <el-date-picker v-model="form.birthday" type="date" :disabledDate="hiredateDisabledDate" format="YYYY-MM-DD"
                    value-format="YYYY-MM-DD" style="width: 200px;" />
            </el-form-item>
            <!-- </el-col>
            </el-row> -->
            <el-form-item label="指派网格">
                <AddressSelect @selectChanged="selectChanged" :labelWidth="labelWidth" :selectWidth="selectWidth" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="cancelAdd(ruleFormRef)">取消</el-button>
                <el-button type="primary" @click="onAdd">
                    提交
                </el-button>
            </span>
        </template>
    </el-dialog>

    <!-- 网格员调动 -->
    <el-dialog v-model="transDialogVisible" title="网格员调动" align-center width="30%">
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
                    调动
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref, computed } from 'vue'
import { ElNotification } from 'element-plus'
import { InfoFilled, Search, Plus } from '@element-plus/icons-vue'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import en from 'element-plus/dist/locale/en.mjs'
import $axios from '../utils/axios'
import AddressSelect from '../supervisor/addressSelect.vue';

const language = ref('zh-cn')
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : en))

const tableLayout = ref('auto')

// ----------新增----------
//打开对话框
const addDialogVisible = ref(false)

//定义日历的禁用日期计算属性
const hiredateDisabledDate = (time) => time.getTime() > new Date().getTime();

//向子组件传值：宽度
//接受子组件 省市区选择组件 的选择结果
let selectWidth = "150px"
let labelWidth = "50px"
const grid = ref({
    province: { id: "", name: "", leaderId: "" },
    city: { id: "", name: "", leaderId: "" },
    town: { id: "", name: "", leaderId: "" }
})
const selectChanged = (f) => {
    grid.value = f
    form.value.pId = grid.value.province.id
    form.value.cId = grid.value.city.id
    form.value.tId = grid.value.town.id

}
const form = ref({})
const ruleFormRef = ref()

const btn_add = () => {
    addDialogVisible.value = true
}

const onAdd = async () => {
    form.value.password = '22222'
    form.value.state = 0
    form.value.roleId = 2
    console.log(form.value);
    const path = '/user/addGridman'
    const { data: res } = await $axios.put(path, form.value)
    if (res.code === 200) {
        addDialogVisible.value = false
        // tableData.value.push(form.value)
        init()
        ElNotification({
            title: '成功',
            message: '已成功添加网格员。',
            type: 'success',
        })
    } else if (res.code === 101) {
        ElNotification({
            title: '失败',
            message: '未知原因，添加失败！',
            type: 'error',
        })
    }
}
const cancelAdd = (formEl) => {
    addDialogVisible.value = false
    formEl.resetFields()
    if (!formEl) return
    // formEl.resetFields()
}

//----------调动----------
const transDialogVisible = ref(false)
const transForm = ref({})
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
let tname
const transfer = (index, row) => {
    console.log(index, row);
    tname = row.name
    transForm.value.id = row.id
    transDialogVisible.value = true
}
const onTransfer = async () => {
    console.log(transForm.value);
    const path = '/user/update'
    const { data: res } = await $axios.post(path, transForm.value)
    if (res.code === 200) {
        init()
        ElNotification({
            title: '成功',
            message: '已成功调动网格员。',
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


//斑马条
const tableRowClassName = ({ rowIndex }) => {
    return rowIndex % 2 === 1 ? 'success-row' : ''
}
//初始化
const tableData = ref([])
const init = async () => {
    const path = '/user/findGrid'
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
    }
}
init()
//搜索
const name = ref()
const btn_loadbyname = async () => {
    const path = '/user/findByName/' + name.value
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
    }
}

//按状态筛选
const filterState = (value, row) => {
    return row.state === value
}
//注销
const logout = async (index, row) => {
    console.log(index, row)
    row.roleId = 0
    const path = '/user/update'
    const { data: res } = await $axios.post(path, row)
    if (res.code === 200) {
        ElNotification({
            title: '成功',
            message: '已将' + row.name + '账号注销',
            type: 'success',
        })
    } else if (res.code === 103) {
        ElNotification({
            title: '失败',
            message: '修改失败',
            type: 'error',
        })
    }
}
//休假
const holiday = async (index, row) => {
    console.log(index, row)
    row.state = 2
    const path = '/user/update'
    const { data: res } = await $axios.post(path, row)
    if (res.code === 200) {
        ElNotification({
            title: '成功',
            message: row.name + '已休假',
            type: 'success',
        })
    } else if (res.code === 103) {
        ElNotification({
            title: '失败',
            message: '修改失败',
            type: 'error',
        })
    }
}
//回归
const back = async (index, row) => {
    console.log(index, row)
    row.state = 0
    const path = '/user/update'
    const { data: res } = await $axios.post(path, row)
    if (res.code === 200) {
        ElNotification({
            title: '成功',
            message: row.name + '已结束休假',
            type: 'success',
        })
    } else if (res.code === 103) {
        ElNotification({
            title: '失败',
            message: '修改失败',
            type: 'error',
        })
    }
}
// const handleDelete = (index, row) => {
//     console.log(index, row)
// }
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

.input-with-select .el-input-group__prepend {
    background-color: var(--el-fill-color-blank);
}
</style>

<style>
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>
