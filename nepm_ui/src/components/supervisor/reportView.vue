<template>
    <el-card class="box-card">
        <el-steps :active="active" finish-status="success" align-center space="200px"
            style="justify-content:center;margin-top:15px;">
            <el-step title="选择网格" />
            <el-step title="选择AQI" />
            <el-step title="确认提交" />
        </el-steps>
        <!-- <el-row> -->

        <!-- 选择网格的card -->
        <!-- <el-col :span="8"> -->
        <div class="gridContainer" v-if="active == 0">
            <el-card shadow="hover" class="selectGrid">
                <!-- 头部 -->
                <template #header>
                    <div class="card-header">
                        <span>选择网格</span>
                        <!-- <el-button class="button" text>Operation button</el-button> -->
                    </div>
                </template>
                <!-- 网格表单 -->
                <el-form :inline="true" :model="formSubmit" class="demo-form-inline" :label-width="labelWidth1">

                    <AddressSelect @selectChanged="selectChanged" :initvalue="formSubmit" :labelWidth="labelWidth"
                        :selectWidth="selectWidth" />
                    <el-form-item label="地址">
                        <el-input v-model="formSubmit.address" placeholder="具体地址" clearable />
                    </el-form-item>
                    <el-form-item>
                        <div class="btntoAQI">
                            <el-button type="primary" @click="toAQI">下一步</el-button>
                        </div>
                    </el-form-item>
                </el-form>
            </el-card>
        </div>
        <!-- </el-col> -->

        <!-- 选择AQI的card -->
        <div class="aqiContainer" v-if="active == 1">
            <!-- <el-col :span="12" style="margin-left: 25px;"> -->
            <el-card shadow="hover" class="selectAqi">
                <!-- 头部 -->
                <!-- <template #header>
                    <div class="card-header">
                        <span>选择AQI</span>
                    </div>
                </template> -->
                <!-- AQI表格 -->
                <el-table :data="tableData" style="width: 100%;" :row-class-name="tableRowClassName">
                    <el-table-column prop="level" label="AQI等级" width="100" />
                    <el-table-column prop="category" label="AQI类别" width="100" />
                    <el-table-column prop="aqiExplain" label="描述" />
                </el-table>
                <el-form :inline="true" :model="formSubmit" labelWidth="150px">
                    <!-- <el-form-item>
                    </el-form-item> -->
                    <!-- AQI表单 -->
                    <el-form-item label="请预估当前AQI等级：" style="margin-top: 20px">
                        <el-radio-group v-model="formSubmit.estimatedGrade">
                            <el-row>
                                <el-col :span="4">
                                    <el-radio label="1" border>一级</el-radio>
                                </el-col>
                                <el-col :span="4">
                                    <el-radio label="2" border>二级</el-radio>
                                </el-col>
                                <el-col :span="4">
                                    <el-radio label="3" border>三级</el-radio>
                                </el-col>
                                <el-col :span="4">
                                    <el-radio label="4" border>四级</el-radio>
                                </el-col>
                                <el-col :span="4">
                                    <el-radio label="5" border>五级</el-radio>
                                </el-col>
                                <el-col :span="4">
                                    <el-radio label="6" border>六级</el-radio>
                                </el-col>
                            </el-row>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="请填写反馈信息：">
                        <el-input v-model="formSubmit.info" maxlength="30" placeholder="请填写反馈信息" show-word-limit
                            type="textarea" style="width:460px" />
                    </el-form-item>
                    <el-form-item>
                        <div class="btntoAQI">
                            <el-button type="primary" @click="togrid">上一步</el-button>
                            <el-button type="primary" @click="toAQI">下一步</el-button>
                        </div>
                    </el-form-item>
                </el-form>
            </el-card>
            <!-- </el-col> -->
            <!-- </el-row> -->
        </div>

        <div class="allContainer" v-if="active == 2">
            <!-- <template #default="" -->
            <div class="comfirm">
                <el-descriptions title="确认待反馈信息" :column="1" border>
                    <el-descriptions-item label="省份" label-align="right" align="center" width="200px">
                        {{ grid.province.name ? grid.province.name : '' }}
                    </el-descriptions-item>
                    <el-descriptions-item label="城市" label-align="right" align="center">
                        {{ grid.city.name ? grid.city.name : '' }}
                    </el-descriptions-item>
                    <el-descriptions-item label="区县" label-align="right" align="center">
                        {{ grid.town.name ? grid.town.name : '' }}
                    </el-descriptions-item>
                    <el-descriptions-item label="地址" label-align="right" align="center">
                        {{ formSubmit.address ? formSubmit.address : '' }}
                    </el-descriptions-item>
                    <el-descriptions-item label="AQI等级" label-align="right" align="center"
                        v-if="formSubmit.estimatedGrade == 1">一级</el-descriptions-item>
                    <el-descriptions-item label="AQI等级" label-align="right" align="center"
                        v-if="formSubmit.estimatedGrade == 2">二级</el-descriptions-item>
                    <el-descriptions-item label="AQI等级" label-align="right" align="center"
                        v-if="formSubmit.estimatedGrade == 3">三级</el-descriptions-item>
                    <el-descriptions-item label="AQI等级" label-align="right" align="center"
                        v-if="formSubmit.estimatedGrade == 4">四级</el-descriptions-item>
                    <el-descriptions-item label="AQI等级" label-align="right" align="center"
                        v-if="formSubmit.estimatedGrade == 5">五级</el-descriptions-item>
                    <el-descriptions-item label="AQI等级" label-align="right" align="center"
                        v-if="formSubmit.estimatedGrade == 6">六级</el-descriptions-item>
                    <el-descriptions-item label="描述信息" label-align="right" align="center">
                        {{ formSubmit.info ? formSubmit.info : '' }}
                    </el-descriptions-item>
                </el-descriptions>
                <div class="btnCom">
                    <el-button type="primary" @click="togrid">上一步</el-button>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                </div>
            </div>
        </div>
    </el-card>
</template>

<script setup>
import { ref } from 'vue'
import $axios from '../utils/axios'
import { useRouter } from 'vue-router'
import { ElNotification } from 'element-plus'
import AddressSelect from './addressSelect.vue';

//步骤条
const active = ref(0)

const togrid = () => {
    active.value--
}
const toAQI = () => {
    active.value++
}

//获取当前反馈日期和时间
function dateFormat() {
    // var a = new Date().getTime(); //获取到当前时间戳
    // var b = new Date(a); //创建一个指定的日期对象
    // console.log(a);   //1692924791311
    // console.log(b);   //Fri Aug 25 2023 08:53:11 GMT+0800 (GMT+08:00)
    var now = new Date()
    var year = now.getFullYear()
    var month = now.getMonth() + 1 < 10 ?
        '0' + (now.getMonth() + 1) : now.getMonth() + 1
    var day = now.getDate() < 10 ? '0' + now.getDate() : now.getDate()
    var hour = now.getHours() < 10 ? '0' + now.getHours() : now.getHours()
    var minute = now.getMinutes() < 10 ? '0' + now.getMinutes() : now.getMinutes()
    var second = now.getSeconds() < 10 ? '0' + now.getSeconds() : now.getSeconds()
    formSubmit.value.fDate = year + '-' + month + '-' + day
    formSubmit.value.fTime = hour + ":" + minute + ":" + second
}
// console.log(dateFormat(b));   //2023-08-25 09:11:47


const labelWidth1 = "80px"
let selectWidth = "120px"
let labelWidth = "80px"

// 表单数据
const formSubmit = ref({})

// -----------选择网格部分--------------------------
// 获得省市区组件传回的值
const grid = ref({
    province: { id: "", name: "", leaderId: "" },
    city: { id: "", name: "", leaderId: "" },
    town: { id: "", name: "", leaderId: "" }
})
const selectChanged = (f) => {
    grid.value = f
    formSubmit.value.pId = grid.value.province.id
    formSubmit.value.cId = grid.value.city.id
    formSubmit.value.tId = grid.value.town.id
}

// -----------选择AQI部分--------------------------
const tableData = ref()

// 获得AQI表中的数据，供选择AQI
const getAqiTable = async () => {
    const path = '/aqi/findAll'
    const { data: res } = await $axios.get(path)
    console.log(res);
    if (res.code === 200) {
        tableData.value = res.result
        // console.log(tableData.value)
    }
}
getAqiTable()

// let timer
const router = useRouter()
// -----------提交表单数据----------
const sId = sessionStorage.getItem('id')
const onSubmit = async () => {
    // console.log('submit!')
    dateFormat();
    formSubmit.value.sId = sId
    console.log(formSubmit.value);
    const path = '/feedback/add'
    const { data: res } = await $axios.put(path, formSubmit.value)
    if (res.code === 200) {
        ElNotification({
            title: '成功',
            message: '反馈成功！',
            type: 'success',
        })
        // .then(() => {
        //     timer = setTimeout(() => {
        //         router.push('/HistoryReportList')
        //     }, 2000)
        // })
        //     .catch(() => {
        //         // catch error
        //     })
    } else if (res.code === 101) {
        ElNotification({
            title: '失败',
            message: '未知原因，反馈失败！',
            type: 'error',
        })
    }
    router.push('/HistoryReportList')
    // clearTimeout(timer)
}

// 自定义AQI表格样式
const tableRowClassName = ({ row, rowIndex }) => {
    console.log(row)
    if (rowIndex === 0) {
        return 'good-row'
    }
    else if (rowIndex === 1) {
        return 'fine-row'
    }
    else if (rowIndex === 2) {
        return 'light-row'
    }
    else if (rowIndex === 3) {
        return 'medium-row'
    }
    else if (rowIndex === 4) {
        return 'serious-row'
    }
    else if (rowIndex === 5) {
        return 'heavy-row'
    }
    return ''
}
</script>

<style>
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

.demo-form-inline .el-input {
    --el-input-width: 220px;
}

.gridContainer {
    display: flex;
    align-items: center;
    justify-content: center;
}

.selectGrid {
    width: 400px;
    /* position: absolute; */
    margin-top: 100px;
    align-items: center;
}

.btntoAQI {
    margin-left: 150px;
}

.aqiContainer {
    display: flex;
    align-items: center;
    justify-content: center;

}

.selectAqi {
    width: 700px;
    /* position: absolute; */
    margin-top: 20px;
    align-items: center;
}

.allContainer {
    display: flex;
    align-items: center;
    justify-content: center;

}

.comfirm {
    width: 400px;
    margin-top: 30px;
    align-items: center;
}

.btnCom {
    margin-top: 20px;
    margin-left: 120px;
}

/* 一级 绿色 */
.el-table .good-row {
    --el-table-tr-bg-color: #67C23A;
    color: black;
}

/* 二级 黄色 */
.el-table .fine-row {
    --el-table-tr-bg-color: #FFFF00;
    color: black;
}

/* 三级 橙色 */
.el-table .light-row {
    --el-table-tr-bg-color: #FFA500;
    color: black;
}

/* 四级 红色 */
.el-table .medium-row {
    --el-table-tr-bg-color: #FF0000;
    color: black;
}

/* 五级 紫色 */
.el-table .serious-row {
    --el-table-tr-bg-color: #800080;
    color: black;
}

/* 六级 红褐色 */
.el-table .heavy-row {
    --el-table-tr-bg-color: #800000;
    color: black;
}
</style>
