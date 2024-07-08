<template>
    <el-form :model="form" ref="ruleFormRef" :labelWidth="labelWidth1" style="display: flex;">
        <!-- <el-row>
            <el-col span="8"> -->
        <addressSelect @selectChanged="selectChanged" :initvalue="form" :labelWidth="labelWidth"
            :selectWidth="selectWidth" />
        <!-- </el-col>
            <el-col span="8"> -->
        <el-form-item label="确认时间">
            <el-date-picker v-model="form.confirmDate" type="date" placeholder="选择确认时间" :disabledDate="hiredateDisabledDate"
                :shortcuts="hiredateShortcuts" format="YYYY-MM-DD" value-format="YYYY-MM-DD" />
        </el-form-item>
        <!-- </el-col>
            <el-col span="8"> -->
        <el-form-item>
            <div class="btn">
                <el-button type="success" @click="searchSql" :icon="Search">查询</el-button>
                <el-button type="warning" @click="resetForm(ruleFormRef)" :icon="Delete">清空</el-button>
            </div>
        </el-form-item>
        <!-- </el-col>
        </el-row> -->
    </el-form>
</template>

<script setup>
import addressSelect from '../supervisor/addressSelect.vue';
import { ref, reactive } from 'vue'
import $axios from '../utils/axios'
import { Search, Delete } from '@element-plus/icons-vue'

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

const ruleFormRef = ref()
const form = ref({})

const tableData = ref({})

// 收到父组件 确认列表 的分页信息
const props = defineProps({
    currentPage: {
        type: Number,
        default: 1
    },
    pageSize: {
        type: Number,
        default: 10
    }
})
// 读取传递的数据给value
const currentPage = props.currentPage
const pageSize = props.pageSize
const total = ref('')

const labelWidth1 = "80px"
let selectWidth = "120px"
let labelWidth = "68px"

//接受子组件 省市区选择组件 的值
const grid = ref()
const selectChanged = (f) => {
    grid.value = f
    // console.log(grid.value.province.id, grid.value.city.id, grid.value.town.id);
    // form.value.pId = f.province.id
    // form.value.cId = f.city.id
    // form.value.tId = f.town.id
    // labelWidth = lw
    // selectWidth = sw
}

// ----------BUG----------BUG----------BUG----------BUG----------BUG----------BUG----------BUG----------BUG----------BUG----------BUG----------BUG----------BUG----------
// 向父组件 确认列表 传递查询结果
const emits = defineEmits(['search', 'a', 'b', 'c'])
const searchSql = async () => {
    form.value.pId = grid.value.province.id
    form.value.cId = grid.value.city.id
    form.value.tId = grid.value.town.id
    form.value.currentPage = currentPage
    form.value.pageSize = pageSize
    console.log(form.value)
    const path = '/statistics/findBySql'
    const { data: res } = await $axios.post(path, form.value)
    if (res.code === 200) {
        tableData.value = res.result
        total.value = parseInt(res.msg)
    }
    console.log(tableData.value)
    emits('search', tableData.value, currentPage, pageSize, total.value)
}

// const resetFormData = ref(null);


const resetForm = (formEl) => {
    // formEl.resetFields()
    if (!formEl) return
    formEl.resetFields()
}

</script>

<style scoped>
.radio {
    padding: 5px;
}
</style>