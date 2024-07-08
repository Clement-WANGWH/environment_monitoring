<template>
    <div id="chart" style="width: 600px; height: 400px;"></div>
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
        if (aqiId >= 1 && aqiId <= 2) {
            return '#00FF00';  // 绿色
        } else if (aqiId >= 3 && aqiId <= 4) {
            return '#FFFF00';  // 黄色
        } else if (aqiId >= 5 && aqiId <= 6) {
            return '#FFA500';  // 橙色
        } else if (aqiId >= 7 && aqiId <= 8) {
            return '#FF0000';  // 红色
        } else if (aqiId >= 9 && aqiId <= 10) {
            return '#800080';  // 紫色
        }
    }

    const option = {
        title: {
            text: '全国AQI累计超标统计情况',
            left: 'center',
        },
        tooltip: {
            formatter: '{b}：{c}'
        },
        visualMap: {
            min: 1,
            max: 10,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],
            inRange: {
                color: ['#00FF00', '#FFFF00', '#FFA500', '#FF0000', '#800080']
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
<style scoped></style>