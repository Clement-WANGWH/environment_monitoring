import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      children: [
		{//网格员个人中心
			path: 'GridCenter',
			name: 'GridCenter',
			component: () => import('../views/GridCenter.vue')
		},
		{//监督员个人中心
			path: 'SuperviseCenter',
			name: 'SuperviseCenter',
			component: () => import('../views/SuperviseCenter.vue')
		},
        {//公众监督员提交反馈
          path: '/reportView',
          name: 'reportView',
          component: () => import('../components/supervisor/reportView.vue')
        }, {//自己的反馈信息列表
          path: '/HistoryReportList',
          name: 'HistoryReportList',
          component: () => import('../components/supervisor/HistoryReportList.vue')
        }, {//查看个人信息
          path: '/PersonalInfo',
          name: 'PersonalInfo',
          component: () => import('../views/PersonalInfo.vue')
        }, {//修改登录密码
          path: '/UpdatePsw',
          name: 'UpdatePsw',
          component: () => import('../views/UpdatePsw.vue')
        }, {//更换头像 × 👇
          path: '/UploadAvatar',
          name: 'UploadAvatar',
          component: () => import('../views/UploadAvatar.vue')
        }, {//网格员
          path: '/MonitorTaskView',
          name: 'MonitorTaskView',
          component: () => import('../components/gridman/MonitorTaskView.vue')
        }, {//网格员历史检测列表
          path: '/HistoryTaskList',
          name: 'HistoryTaskList',
          component: () => import('../components/gridman/HistoryTaskList.vue')
        }, {//反馈信息列表
          path: '/FeedbackView',
          name: 'FeedbackView',
          component: () => import('../components/feedbackList/FeedbackTable.vue')
        }, {//确认信息列表
          path: '/ComfirmView',
          name: 'ComfirmView',
          component: () => import('../components/confirmAqi/ComfirmTable.vue')
        }, {
          path: '/ChinaCharts',
          name: 'ChinaCharts',
          component: () => import('../components/chart/ChinaCharts.vue')

        }, {//省分组统计
          path: '/ProvinceGroupView',
          name: 'ProvinceGroupView',
          component: () => import('../components/chart/ProGroupCharts.vue')
        }, {//分布统计
          path: '/AQIDistribution',
          name: 'AQIDistribution',
          component: () => import('../components/chart/DistributeCharts.vue')
        }, {//趋势统计
          path: '/AQITrendView',
          name: 'AQITrendView',
          component: () => import('../components/chart/TrendCharts.vue')
        }, {
          path: '/OtherDataView',
          name: 'OtherDataView',
          component: () => import('../components/statisticalData/OtherDataView.vue')
        }, {//分布饼状图
          path: '/DistributeCharts',
          name: 'DistributeCharts',
          component: () => import('../components/chart/DistributeCharts.vue')
        }, {//趋势折线图
          path: '/TrendCharts',
          name: 'TrendCharts',
          component: () => import('../components/chart/TrendCharts.vue')
        }, {//省分组柱状图
          path: '/ProGroupCharts',
          name: 'ProGroupCharts',
          component: () => import('../components/chart/ProGroupCharts.vue')
        }, {//管理监督员
          path: '/SupervisorManage',
          name: 'SupervisorManage',
          component: () => import('../components/userManage/SupervisorManage.vue')
        }, {//管理网格员
          path: '/GridmanManage',
          name: 'GridmanManage',
          component: () => import('../components/userManage/GridmanManage.vue')
        },
        {
          path: '/UploadAvatarCopy',
          name: 'UploadAvatarCopy',
          component: () => import('../views/UploadAvatarCopy.vue')
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/loginView.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/registerView.vue')
    },
    {
      path: '/registerCopy',
      name: 'registerCopy',
      component: () => import('../views/registerCopy.vue')
    },
    {
      path: '/About',
      name: 'About',
      component: () => import('../views/AboutView.vue')
    }
  ]
})

/**
 * 在浏览器地址栏内容发生变化之前对本次页面的跳转进行控制
 *
 * to:新地址
 * from：旧地址
 *
 */

// router.beforeEach((to) => {
//   return true
// console.log(' beforeEach ')
// console.log(to)
// console.log(' from ')
// console.log(from)
// const topath = to.path
// // 校验Open
// const openpaths = ['login', 'reg', 'resetpassword']
// const adminpaths = ['login', 'reg', 'resetpassword']
// const emppaths = []

// const index = openpaths.findIndex((e) => {
//   return (
//     topath.toLowerCase().indexOf(e.toLowerCase()) > -1
//   )
// })
// console.log(index)
// if (index > -1) {
//   return true
// }

// // admin

// const roleId = sessionStorage.getItem('roleId')
// if (roleid === 'admin') {
//   const index = adminpaths.findIndex((e) => {
//     return (
//       topath.toLowerCase().indexOf(e.toLowerCase()) > -1
//     )
//   })
//   if (index > -1) {
//     return true
//   }
// }

// // emp
// if (roleid === 'emp') {
//   const index = emppaths.findIndex((e) => {
//     return (
//       topath.toLowerCase().indexOf(e.toLowerCase()) > -1
//     )
//   })
//   if (index > -1) {
//     return true
//   }
// }

// /login

// return true  放弃拦截
// 取消导航：不进入目标页面
// return false // 取消导航
// 最后必须统一跳转的登录页

// return '/login'
// })

// router.beforeEach((to, from) => {
//   // const openpaths = ['login', 'reg', 'resetpassword']
//   // cosnt currentpath = to.path
//   // if (  openpaths.find(openpath) > -1 )    ) {
//   //   return true
//   // } else {
//   //   return false
//   // }
//   // ...
//   // 返回 false 以取消导航
//   // return false
//   // if(to )
//   // return '/login'
// })

export default router
