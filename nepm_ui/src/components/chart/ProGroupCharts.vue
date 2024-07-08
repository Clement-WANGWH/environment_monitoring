<template>
    <div class="container">
        <el-card class="box-card">
            <template #header>
                <div class="card-header">
                    <span>省分组分项检查统计</span>
                    <el-button class="button" text>刷新</el-button>
                </div>
            </template>
            <div class="content">
                <el-table :data="tableData" style="width: 45%" :row-class-name="tableRowClassName">
                    <el-table-column prop="id" label="省编号" width="70px" />
                    <el-table-column prop="name" label="省名称" width="80px" />
                    <el-table-column prop="so2Level" label="SO2超标累计" width="110px" />
                    <el-table-column prop="coLevel" label="CO超标累计" width="105px" />
                    <el-table-column prop="spmLevel" label="PM2.5超标累计" width="125px" />
                    <el-table-column prop="aqiId" label="AQI超标累计" width="110px" />
                </el-table>
                <div id="chart" class="echarts-container"></div>
            </div>
        </el-card>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import $axios from '../utils/axios'
import * as echarts from 'echarts'

const tableData = ref([])

const init = async () => {
    const path = '/statistics/proGroup'
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
        drawChart()
    }
}

onMounted(() => {
    init()
})

const tableRowClassName = ({ rowIndex }) => {
    return rowIndex % 2 === 1 ? 'success-row' : ''
}

const drawChart = () => {
    const chartData = tableData.value.map(item => ({
        name: item.name,
        so2Level: item.so2Level,
        coLevel: item.coLevel,
        spmLevel: item.spmLevel,
        aqiId: item.aqiId
    }))

    const chart = echarts.init(document.getElementById('chart'))
    const option = {
        title: {
            text: '省分组检查统计',
            left: 'center'
        },
        color: ['#67C23A', '#FFFF00', '#FFA500', '#FF0000'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            // orient: 'vertical',
            right: '10%',
            top: '7%',
            data: ['SO2超标累计', 'CO超标累计', 'PM2.5超标累计', 'AQI超标累计']
        },
        // 直角坐标系
        grid: {
            top: '15%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            data: chartData.map(item => item.name)
        },
        series: [
            {
                name: 'SO2超标累计',
                type: 'bar',
                data: chartData.map(item => item.so2Level)
            },
            {
                name: 'CO超标累计',
                type: 'bar',
                data: chartData.map(item => item.coLevel)
            },
            {
                name: 'PM2.5超标累计',
                type: 'bar',
                data: chartData.map(item => item.spmLevel)
            },
            {
                name: 'AQI超标累计',
                type: 'bar',
                data: chartData.map(item => item.aqiId)
            }
        ]
    }
    chart.setOption(option)
}
</script>

<style scoped>
.container {
    display: flex;
    height: 95%;
}

.box-card {
    width: 100%;
    height: 100%;
    margin-top: 12px;
    border-radius: 12px;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.content {
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    width: 100%;
    height: 100%;
}

.echarts-container {
    width: 50%;
    height: 500px;
    margin-left: 30px;
}

#chart {
    width: 50%;
    height: 500px;
    margin-top: 12px;
    border-radius: 12px;
}

.button {
    padding: 0;
}
</style>

<style>
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>