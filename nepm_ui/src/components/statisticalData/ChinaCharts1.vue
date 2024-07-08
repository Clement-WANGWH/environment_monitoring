<template>
    <div id="chart" style="width: 800px; height: 600px;"></div>
</template>

<script>
import $axios from '../utils/axios'
import * as echarts from 'echarts';
import ChinaMap from '@/assets/chinaChange.json';

export default {
    name: 'ChinaMap',
    data() {
        return {
            tableData: []
        }
    },
    mounted() {
        this.initData();
    },
    methods: {
        async initData() {
            // 从后端获取数据并绑定到tableData
            const path = '/statistics/proGroup'
            const { data: res } = await $axios.get(path)
            if (res.code === 200) {
                this.tableData = res.result;
                this.initChart();
            }
        },
        initChart() {
            // 基于准备好的dom，初始化echarts实例
            const chart = echarts.init(document.getElementById('chart'));
            echarts.registerMap('china', ChinaMap);

            // 处理tableData数据为series需要的格式
            const seriesData = this.tableData.map(item => ({
                name: item.name,
                value: item.aqiId
            }));

            // 配置项
            const option = {
                tooltip: {
                    trigger: 'item',
                    formatter: '{b}<br/>{c}'
                },
                visualMap: {
                    min: 1,
                    max: 10,
                    left: 'left',
                    top: 'bottom',
                    text: ['高', '低'],
                    inRange: {
                        color: ['#00FF00', '#FFFF00', '#FFA500', '#FF4500', '#800080']
                    },
                    calculable: true
                },
                series: [
                    {
                        name: 'AQI',
                        type: 'map',
                        mapType: 'china',
                        selectedMode: 'multiple',
                        roam: false,
                        itemStyle: {
                            emphasis: {
                                areaColor: '#800080'
                            }
                        },
                        label: {
                            show: true,
                            color: '#000'
                        },
                        data: seriesData
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表
            chart.setOption(option);
        }
    }
}
</script>