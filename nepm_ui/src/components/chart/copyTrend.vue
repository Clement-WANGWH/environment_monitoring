<template>
    <div id="chart-container" style="width: 300px;height: 300px;"></div>
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
/* .echarts-container {
    height: 300px;
    margin-left: 30px;
}

#chart-container {
    width: 50%;
    height: 300px;
    margin-top: 12px;
} */
</style>