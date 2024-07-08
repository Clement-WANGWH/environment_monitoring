<template>
    <el-form :model="form" ref="ruleFormRef" :rules="rules" :label-width="labelWidth" style="display: flex;">
        <!-- <el-row>
            <el-col span="6"> -->
        <el-form-item label="地区">
            <el-cascader placeholder="-- 全部 --" size="default" :options="regionData" v-model="selectedOptions"
                @change="handleChange" style="width: 210px;" clearable>
            </el-cascader>
        </el-form-item>
        <!-- </el-col>
            <el-col span="6"> -->
        <el-form-item label="预估等级">
            <el-select v-model="form.estimatedGrade" placeholder="-- 全部 --" clearable style="width: 120px;">
                <el-option v-for="item in aqiForm" :key="item.id" :value="item.id" :label="item.level"></el-option>
            </el-select>
        </el-form-item>
        <!-- </el-col>
            <el-col span="6"> -->
        <el-form-item label="反馈时间">
            <el-date-picker v-model="form.fDate" type="date" placeholder="选择反馈时间" :disabledDate="hiredateDisabledDate"
                :shortcuts="hiredateShortcuts" format="YYYY-MM-DD" value-format="YYYY-MM-DD" style="width: 135px;" />
        </el-form-item>
        <el-form-item>
            <div class="btn">
                <el-button type="success" @click="searchSql" :icon="Search" style="margin-left: 25px;">查询</el-button>
                <el-button type="danger" @click="clear(ruleFormRef)" :icon="Delete">清空</el-button>
            </div>
        </el-form-item>
        <!-- </el-col>
        </el-row> -->
    </el-form>
</template>

<script setup>
import { ref, reactive } from 'vue'
import $axios from '../utils/axios'
import { Search, Delete } from '@element-plus/icons-vue'
//引用 element-china-area-data
import { regionData, codeToText } from 'element-china-area-data'


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
const rules = reactive({})
const ruleFormRef = ref()
const form = ref({})
const aqiForm = ref({})
const stateForm = ref({})
const tableData = ref({})


//获得级联选择器中的tid
const handleChange = async (value) => {
    // if (value[1] != null && value[2] != null) {
    //     var dz = codeToText[value[0]] + '/' + codeToText[value[1]] + '/' + codeToText[value[2]]
    //     value.addressid = value[2]
    // } else {
    //     if (value[1] != null) {
    //         dz = codeToText[value[0]] + '/' + codeToText[value[1]]
    //         value.addressid = value[1]
    //     } else {
    //         dz = codeToText[value[0]]
    //         value.addressid = value[0]
    //     }
    // }
    // value.address = dz

    // console.log(dz)
    console.log(codeToText[value[2]])
    const path = '/grid/findTidByName/' + codeToText[value[2]]
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        form.value.tId = res.result
    }
}

const aqiSelect = async () => {
    const path = '/aqi/findAll'
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        aqiForm.value = res.result
    }
}
aqiSelect()

const stateSelect = async () => {
    const path = '/state/findAll'
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        stateForm.value = res.result
    }
}
stateSelect()

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

//条件查询
const emits = defineEmits(['search'])
const searchSql = async () => {
    form.value.currentPage = currentPage
    form.value.pageSize = pageSize
    console.log(form.value);
    const path = '/feedback/findBySql'
    const { data: res } = await $axios.post(path, form.value)
    if (res.code === 200) {
        tableData.value = res.result
        total.value = parseInt(res.msg)
    }
    console.log(tableData.value);
    emits('search', tableData.value, currentPage, pageSize, total.value)
}

const clear = (formEl) => {
    formEl.resetFields()
    if (!formEl) return
    formEl.resetFields()
}
const labelWidth = "100px"

</script>

<style scoped>
.radio {
    padding: 5px;
    /* width: 100px; */
}

.btn {
    display: flex;
    justify-content: flex-end;
}
</style>