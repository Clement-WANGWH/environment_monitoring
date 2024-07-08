<template>
    <div id="asdf" style="width:100%;height:400px">


    </div>
</template>

<script>
import * as echarts from 'echarts';
import ChinaMap from '@/assets/chinaChange.json';

export default {
    name: 'ChinaMap',
    data() {
        return {

        }
    },
    mounted() {
        this.initChar()
    },
    methods: {
        initChar() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.querySelector('#asdf'));
            echarts.registerMap('china', ChinaMap);
            // 绘制图表
            // 指定图表的配置和数据
            var option = {
                geo: {
                    show: true,//是否显示
                    type: 'map',//类型是地图
                    map: 'china',//中国地图
                    label: { //设置字体样式
                        show: true,  //字体是否显示
                        fontSize: 7, //设置字体大小
                        color: 'yellow' //设置字体颜色
                    },
                    itemStyle: { //设置图形样式
                        areaColor: 'blue', //地图颜色
                        borderWidth: 3,//地图边框宽度
                        borderColor: '#fff'//地图边框颜色
                    },
                    zoom: 1,//地图放大到原来的多少倍
                    emphasis: { //高亮状态（鼠标放在哪里，哪里发生变化）
                        label: { //字体设置
                            show: true,//显示字体
                            color: '#2596cc',//字体颜色
                            fontSize: 14,//字体大小
                        },
                        itemStyle: { //设置图像样式
                            areaColor: '#ffffad',//设置地图背景颜色
                            borderColor: 'green',//设置边框颜色
                            borderWidth: 2,//边框宽度

                        }
                    }

                },
                series: [   //设置特效的
                    {
                        type: 'effectScatter',//带有特效动画的散点图
                        coordinateSystem: 'geo',//该系列使用的坐标系
                        rippleEffect: {//特效相关配置
                            period: 2,
                            brushType: 'stroke',
                            scale: 5,  //这个是设置类似于雷达波纹的大小的
                        },
                        label: { //标签
                            normal: {  //正常（默认）标签设置
                                show: true,  //标签显示
                                position: 'right'//标签显示在右侧
                            }
                        },
                        itemStyle: {
                            color: '#fc5531' //圆点的颜色
                        },
                        Symbol: 'circle',  //特效是圆
                        // symbolSize:10,  //设置圆点的大小
                        symbolSize: function (value) {
                            return value[2]
                        },
                        data: [  //数据
                            {
                                name: '北京',
                                value: [116.4551, 40.2539, 20, '#fc5531']
                            },
                            {
                                name: '长沙',
                                value: [113.0823, 28.2568, 10, '#b34b62']
                            }
                        ]

                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图标
            myChart.setOption(option);
        }
    }
}
</script>

<style></style>
