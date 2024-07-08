<template>
    <div id="chart" style="width: 300px;height: 200px;"></div>
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
/* .echarts-container {
    width: 50%;
    height: 500px;
    margin-left: 30px;
}

#chart {
    width: 50%;
    height: 500px;
    margin-top: 12px;
    border-radius: 12px;
} */
</style>