<template>
    <div class="container">
        <el-card class="box-card">
            <template #header>
                <div class="card-header">
                    <span>AQI指数趋势统计</span>
                    <el-button class="button" @click="init" text>刷新</el-button>
                </div>
            </template>
            <div class="content">
                <el-table :data="tableData" style="width: 50%" :row-class-name="tableRowClassName">
                    <el-table-column prop="id" label="#" />
                    <el-table-column prop="months" label="月份" />
                    <el-table-column prop="nums" label="AQI空气质量指数超标数量" />
                </el-table>
                <div class="echarts-container" id="chart-container"></div>
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
    const path = '/statistics/trend'
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
    }
    for (let index = 0; index < tableData.value.length; index++) {
        tableData.value[index].id = index + 1;
    }
}

onMounted(() => {
    init().then(() => {
        drawChart();
    });
})

const tableRowClassName = ({ rowIndex }) => {
    return rowIndex % 2 === 1 ? 'success-row' : ''
}

const drawChart = () => {
    const chartContainer = document.getElementById('chart-container')
    const chart = echarts.init(chartContainer)

    // 配置图表
    const options = {
        title: {
            text: 'AQI指数趋势',
            left: 'center'
        },
        // 提示框
        tooltip: {
            trigger: 'axis',
        },
        // 图例
        legend: {
            orient: 'vertical',
            right: '10%',
            top: '5%',
            data: ['AQI空气质量指数超标数量'],
        },
        xAxis: {
            type: 'category',
            data: tableData.value.map(item => item.months),
        },
        yAxis: {
            type: 'value',
        },
        series: [
            {
                name: 'AQI空气质量指数超标数量',
                type: 'line',
                data: tableData.value.map(item => item.nums),
                smooth: true,
            },
        ],
    }

    // 设置图表配置项
    chart.setOption(options)
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
}

.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}



.echarts-container {
    height: 300px;
    margin-left: 30px;
}

#chart-container {
    width: 50%;
    height: 300px;
    margin-top: 12px;
}
</style>

<style>
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>