<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span>历史反馈信息列表</span>
                <!-- <el-button class="button" text>Operation button</el-button> -->
            </div>
        </template>

        <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
            <!-- <el-table-column type="expand">
                <template #default="props">
                    <div m="4">
                        <p m="t-0 b-2">State: {{ props.row.state }}</p>
                        <p m="t-0 b-2">City: {{ props.row.city }}</p>
                        <p m="t-0 b-2">Address: {{ props.row.address }}</p>
                        <p m="t-0 b-2">Zip: {{ props.row.zip }}</p>
                        <h3>Family</h3>
                        <el-table :data="props.row.family" :border="childBorder">
                            <el-table-column label="Name" prop="name" />
                            <el-table-column label="State" prop="state" />
                            <el-table-column label="City" prop="city" />
                            <el-table-column label="Address" prop="address" />
                            <el-table-column label="Zip" prop="zip" />
                        </el-table>
                    </div>
                </template>
            </el-table-column> -->
            <el-table-column prop="id" label="编号" width="80px" />
            <el-table-column prop="aqiId" label="实测等级">
                <template #default="scope">
                    <p v-if="scope.row.aqiId == '1'" style="color: #67c23a">优（一）</p>
                    <p v-if="scope.row.aqiId == '2'" style="color: #ffff00">良好（二）</p>
                    <p v-if="scope.row.aqiId == '3'" style="color: #ffa500">轻度污染（三）</p>
                    <p v-if="scope.row.aqiId == '4'" style="color: #ff0000">中度污染（四）</p>
                    <p v-if="scope.row.aqiId == '5'" style="color: #800080">重度污染（五）</p>
                    <p v-if="scope.row.aqiId == '6'" style="color: #800000">严重污染（六）</p>
                </template>
            </el-table-column>
            <el-table-column prop="confirmDate" label="检测日期" />
            <el-table-column prop="confirmTime" label="检测时间" />
            <el-table-column prop="pId" label="省份">
                <template #default="scope">
                    {{
                        scope.row.province.name
                    }}
                </template>
            </el-table-column>
            <el-table-column prop="cId" label="城市">
                <template #default="scope">
                    {{
                        scope.row.city.name
                    }}
                </template>
            </el-table-column>
            <el-table-column prop="tId" label="县/区">
                <template #default="scope">
                    {{
                        scope.row.town.name
                    }}
                </template>
            </el-table-column>
            <!-- <el-table-column prop="state" label="状态">
                <template #default="scope">
                    <p v-if="scope.row.state != '2'" style="color: #ff0000"><el-icon :style="iconStyle">
                            <CircleCloseFilled />
                        </el-icon>未确认</p>
                    <p v-if="scope.row.state == '2'" style="color: #67c23a"><el-icon :style="iconStyle">
                            <SuccessFilled />
                        </el-icon>已确认</p>
                </template>
            </el-table-column> -->
        </el-table>

    </el-card>
</template>
  
<script setup>
import { ref } from 'vue'
import $axios from '../utils/axios'
// import { CircleCloseFilled, SuccessFilled } from '@element-plus/icons-vue'

const tableRowClassName = ({ rowIndex }) => {
    return rowIndex % 2 === 1 ? 'success-row' : ''
}

const tableData = ref([])

const gId = sessionStorage.getItem('id')
const init = async () => {
    const path = '/statistics/findByGidHistory/' + gId
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
    }
    for (let index = 0; index < tableData.value.length; index++) {
        tableData.value[index].id = index + 1;
    }
    // console.log(tableData.value);
}
init()
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

.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}
</style>

<style>
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>