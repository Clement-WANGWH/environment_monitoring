<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span>省分组分项检查统计</span>
                <el-button class="button" text>刷新</el-button>
            </div>
        </template>
        <el-table :data="tableData" style="width: 60%" :row-class-name="tableRowClassName">
            <el-table-column prop="id" label="省编号" />
            <el-table-column prop="name" label="省名称" />
            <el-table-column prop="so2Level" label="SO2超标累计" />
            <el-table-column prop="coLevel" label="CO超标累计" />
            <el-table-column prop="spmLevel" label="PM2.5超标累计" />
            <el-table-column prop="aqiId" label="AQI超标累计" />
        </el-table>
    </el-card>
</template>

<script setup>
import { ref } from 'vue'
import $axios from '../utils/axios'

const tableData = ref([])

const init = async () => {
    const path = '/statistics/proGroup'
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
    }
    console.log(tableData.value);
}
init()

const tableRowClassName = ({ rowIndex }) => {
    return rowIndex % 2 === 1 ? 'success-row' : ''
}

</script>
  
<style scoped>
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

.box-card {
    width: 99%;
    height: 95%;
    margin-top: 12px;
    border-radius: 12px;
}
</style>
  
<style>
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>