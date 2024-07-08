<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span>AQI指数趋势统计</span>
                <el-button class="button" text>刷新</el-button>
            </div>
        </template>
        <el-table :data="tableData" style="width: 50%" :row-class-name="tableRowClassName">
            <el-table-column prop="id" label="#" />
            <el-table-column prop="months" label="月份" />
            <el-table-column prop="nums" label="AQI空气质量指数超标数量" />
        </el-table>
    </el-card>
</template>

<script setup>
import { ref } from 'vue'
import $axios from '../utils/axios'

const tableData = ref([])

const init = async () => {
    const path = '/statistics/trend'
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
    }
    for (let index = 0; index < tableData.value.length; index++) {
        tableData.value[index].id = index + 1;
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
  