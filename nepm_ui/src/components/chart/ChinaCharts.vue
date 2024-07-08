<template>
    <div class="container">
        <el-card class="box-card">
            <div id="chart" class="charts"></div>
        </el-card>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import $axios from '../utils/axios'
import * as echarts from 'echarts';
import ChinaMap from '@/assets/chinaChange.json';

const tableData = ref()
const initData = async () => {
    // 从后端获取数据并绑定到tableData
    const path = '/statistics/proGroup'
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result;
        console.log(tableData.value);
        initChart()
    }
}
onMounted(() => {
    initData()
})

const initChart = () => {
    const chart = echarts.init(document.getElementById('chart'));
    echarts.registerMap('china', ChinaMap);

    const getColorByAQIId = (aqiId) => {
        if (aqiId >= 1 && aqiId <= 4) {
            return '#67C23A';  // 绿色
        } else if (aqiId >= 5 && aqiId <= 7) {
            return '#FFFF00';  // 黄色
        } else if (aqiId >= 8 && aqiId <= 10) {
            return '#FFA500';  // 橙色
        } else if (aqiId >= 11 && aqiId <= 12) {
            return '#FF0000';  // 红色
        } else if (aqiId >= 13 && aqiId <= 15) {
            return '#800080';  // 紫色
        }
    }

    const option = {
        // geo: {
        //     zoom: 1.5
        // },
        title: {
            text: '全国AQI累计超标统计情况',
            left: 'center',
        },
        tooltip: {
            formatter: '{b}：{c}'
        },
        visualMap: {
            min: 1,
            max: 15,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],
            inRange: {
                color: ['#67C23A', '#FFFF00', '#FFA500', '#FF0000', '#800080']
            },
            calculable: true
        },
        series: [
            {
                name: 'AQI',
                type: 'map',
                map: 'china',
                selectedMode: 'multiple',
                roam: false,
                label: {
                    show: true,
                    color: '#000',
                    fontSize: 10,  // 设置默认字体大小
                    emphasis: {
                        show: true,
                        fontSize: 14,  // 设置鼠标移至省份时的字体大小
                    }
                },
                itemStyle: {
                    emphasis: {
                        areaColor: 'rgba(128, 128, 128, 0.5)',  // 设置鼠标移至省份时的区域颜色
                    }
                },
                data: tableData.value.map(item => ({
                    name: item.name,
                    value: item.aqiId,
                    itemStyle: {
                        emphasis: {
                            areaColor: getColorByAQIId(item.aqiId)
                        }
                    }
                }))

            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表
    chart.setOption(option);
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
    align-content: center;
}

#chart {
    width: 75%;
    height: 600px;
    margin-top: 20px;
}

.charts {
    /* width: 600px; */
    /* height: 400px; */
    margin-left: 150px;
}
</style>