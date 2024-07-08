<template>
    <div class="container">
        <el-card class="box-card">
            <template #header>
                <div class="card-header">
                    <span>AQI指数分布统计</span>
                    <el-button class="button" @click="init" text>刷新</el-button>
                </div>
            </template>

            <div class="content">
                <el-table :data="tableData" style="width: calc(50% - 20px);" :row-class-name="tableRowClassName">
                    <el-table-column prop="id" label="AQI指数等级" />
                    <el-table-column prop="category" label="AQI指数等级描述" />
                    <el-table-column prop="count" label="AQI指数等级分布统计" />
                </el-table>
                <div id="chart" class="chart-container"></div>
            </div>
        </el-card>
    </div>
</template>
<script setup>
import * as echarts from 'echarts'
import { ref } from 'vue'
import $axios from '../utils/axios'

const tableData = ref([])

// 定义初始化echarts图表的方法
const initChart = (chartData) => {
    const chart = echarts.init(document.getElementById('chart'))

    // 定义颜色映射表
    const colorMap = {
        优: '#67C23A',
        良好: '#FFFF00',
        轻度污染: '#FFA500',
        中度污染: '#FF0000',
        重度污染: '#800080',
        严重污染: '#800000'
    }
    // 配置图表
    const options = {
        // 标题
        title: {
            text: 'AQI指数分布统计',
            left: 'center'
        },
        // 提示框
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        // 图例
        legend: {
            orient: 'vertical',
            right: '10%',
            top: '10%',
            data: chartData.map(item => item.category)
        },
        series: [
            {
                name: 'AQI指数等级',
                type: 'pie',
                radius: '50%',
                data: chartData.map(item => ({
                    name: item.category,
                    value: item.count
                })),
                itemStyle: {
                    // 根据颜色映射表设置图表每个扇形的颜色
                    color: function (params) {
                        return colorMap[params.name]
                    }
                },
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    }

    // 设置图表配置项
    chart.setOption(options)
}

const init = async () => {
    // 获取统计数据
    const path = '/statistics/distribution'
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result

        // 初始化echarts图表
        initChart(res.result)
    }
}
init()

const tableRowClassName = ({ rowIndex }) => {
    return rowIndex % 2 === 1 ? 'success-row' : ''
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

.el-table {
    width: 50%;
}

.chart-container {
    width: 50%;
    height: 500px;
    margin-left: 30px;
}


.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}


#chart {
    margin-top: 12px;
    border-radius: 12px;
}
</style>

<style>
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>