<template>
  <el-breadcrumb :separator-icon="ArrowRight">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item :key="index" v-for="(item, index) in currentpath">
      {{ item }}</el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup>
import { ArrowRight } from '@element-plus/icons-vue'
// 引入路由
// vue3 中，用啥，引啥

import { useRouter } from 'vue-router'
import { watch, ref } from 'vue'

const paths = {
  SupervisorManage: ['用户管理', '公众监督员管理'],
  GridmanManage: ['用户管理', '网格员管理'],
  FeedbackView: ['公众监督数据管理', '公众监督数据列表'],
  ComfirmView: ['公众监督数据管理', '确认AQI数据列表'],
  ProvinceGroupView: ['统计数据管理', '省分组检查统计'],
  AQIDistribution: ['统计数据管理', 'AQI指数分布统计'],
  AQITrendView: ['统计数据管理', 'AQI指数趋势统计'],
  OtherDataView: ['统计数据管理', '其他数据统计'],
  IndividualCenter: ['个人中心'],
  reportView: ['反馈中心', '提交反馈'],
  HistoryReportList: ['反馈中心', '历史反馈记录'],
  MonitorTaskView: ['任务中心', '检测任务列表'],
  HistoryTaskList: ['任务中心', '历史检测列表'],
  PersonalInfo: ['个人信息', '查看个人信息'],
  UpdatePsw: ['个人信息', '登录密码'],
  cancel: ['个人信息', '账号注销'],
  UploadAvatar: ['个人信息', '更换头像']
}

const currentpath = ref([])
// 实例化路由对象
const router = useRouter()
// 监听当前路由
watch(
  () => router.currentRoute.value, //  箭头路径的改变
  (newValue) => {
    // console.log('newValue', paths[newValue.name])
    // 使用[ ] ,通过反射机制调用对象属性
    //  key = 'emp'
    // paths[key]  ====  paths.emp

    currentpath.value = paths[newValue.name]
  },
  { immediate: true } // immediate 箭头立即生效
)
</script>
