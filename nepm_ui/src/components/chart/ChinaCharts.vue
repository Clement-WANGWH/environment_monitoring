<template>
    <div class="chart-container">
        <div id="chart1" class="chart-left"></div>
        <div class="chart-right">
            <pro-chartstable class="chart-right-item"></pro-chartstable>
            <aqiDistributionchart class="chart-right-item"></aqiDistributionchart>
            <aqiTrendchart class="chart-right-item"></aqiTrendchart>
        </div>
    </div>
</template>


<script setup>
import { ref, onMounted } from 'vue'
import $axios from '../utils/axios'
import * as echarts from 'echarts';
import ChinaMap from '@/assets/chinaChange.json';
import ProChartstable from './ProChartstable .vue';
import aqiDistributionchart from './aqiDistributionchart.vue';
import aqiTrendchart from './aqiTrendcharts.vue';

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
    const chart1 = echarts.init(document.getElementById('chart1'));
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
    chart1.setOption(option);
}
</script>

<style scoped>
.chart-container {
    display: flex;
    height: 100vh; /* 使用视口高度确保全屏展示 */
    width: 100%;
}

.chart-left {
    flex: 1;  /* 左侧图表占据1/2宽度 */
    margin: 10px;
    height: 100%;
}

.chart-right {
    flex: 1;  /* 右侧图表占据1/2宽度 */
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin: 10px;
    height: 100%;
}

.chart-right-item {
    flex: 1;
    margin-bottom: 10px;
    height: 30%; /* 每个图表占据右侧容器的1/3高度 */
   
}

#chart1 {
    width: 100%;
    height: 100%;
}
</style>
