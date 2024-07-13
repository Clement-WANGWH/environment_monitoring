<template>
    <!-- <el-button style="background-color: #b3e19d;" :icon="Fold" size="small" @click="isCollapse = !isCollapse" /> -->
    <el-menu active-text-color="rgb(135,206,235)" background-color="#2075cb" class="el-menu-vertical-demo" default-active="2"
        text-color="#fff" @open="handleOpen" @close="handleClose" router :collapse="isCollapse">
        <el-sub-menu index="1" v-if="roleId == 1">
            <template #title>
                <el-icon>
                    <User />
                </el-icon>
                <span>用户管理</span>
            </template>
            <el-menu-item index="SupervisorManage">公众监督员管理</el-menu-item>
            <el-menu-item index="GridmanManage">网格员管理</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="2" v-if="roleId == 1">
            <template #title>
                <el-icon>
                    <ChatDotRound />
                </el-icon>
                <span>公众监督数据管理</span>
            </template>
            <el-menu-item index="FeedbackView">公众监督数据列表</el-menu-item>
            <el-menu-item index="ComfirmView">确认AQI数据列表</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="3" v-if="roleId == 1 || roleId == 4">
            <template #title>
                <el-icon>
                    <DocumentCopy />
                </el-icon>
                <span>统计数据管理</span>
            </template>
            <el-menu-item index="ProvinceGroupView">省分组检查统计</el-menu-item>
            <el-menu-item index="AQIDistribution">AQI指数分布统计</el-menu-item>
            <el-menu-item index="AQITrendView">AQI指数趋势统计</el-menu-item>
            <!-- <el-menu-item index="OtherDataView">其他数据统计</el-menu-item> -->
        </el-sub-menu>
        <el-sub-menu index="4" v-if="roleId == 4">
            <template #title>
                <el-icon>
                    <Grid />
                </el-icon>
                <span>网格数据管理</span>
            </template>
        </el-sub-menu>
        <el-menu-item index="GridCenter" v-if="roleId == 2">
            <el-icon>
                <House />
            </el-icon>
            <span>个人中心</span>
        </el-menu-item>
		<el-menu-item index="SuperviseCenter" v-if="roleId == 3">
		    <el-icon>
		        <House />
		    </el-icon>
		    <span>个人中心</span>
		</el-menu-item>
        <el-sub-menu index="6" v-if="roleId == 3">
            <template #title>
                <el-icon>
                    <Message />
                </el-icon>
                <span>反馈中心</span>
            </template>
            <el-menu-item index="reportView">提交反馈</el-menu-item>
            <el-menu-item index="HistoryReportList">历史反馈记录</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="7" v-if="roleId == 2">
            <template #title>
                <el-icon>
                    <DocumentCopy />
                </el-icon>
                <span>任务中心</span>
            </template>
            <el-menu-item index="MonitorTaskView">检测任务列表</el-menu-item>
            <el-menu-item index="HistoryTaskList">历史检测记录</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="8" v-if="roleId == 3 || roleId == 2">
            <template #title>
                <el-icon>
                    <Star />
                </el-icon>
                <span>账号设置</span>
            </template>
            <el-menu-item index="PersonalInfo">查看个人信息</el-menu-item>
            <el-menu-item index="UpdatePsw">登录密码</el-menu-item>
            <el-menu-item index="cancel" v-if="roleId == 3">账号注销</el-menu-item>
        </el-sub-menu>
    </el-menu>
    <div style="display: flex; align-content: center;">
        <!-- 拖拽vue.draggable网址：https://www.itxst.com/vue-draggable-next/tutorial.html -->

        <!-- <draggable :list="arr" delay="500" animation="200" @start="onStart" @end="onEnd">
            <template #item="{ element }">
                <div class="item">
                    <el-button>
                        <el-icon>
                            <HomeFilled />
                        </el-icon>
                    {{ element.name }}
                    </el-button>
                </div>
            </template>
        </draggable> -->

        <!-- <draggable v-model="buttons"> -->
        <!-- <template v-for="button in buttons"> -->
        <!-- <div :key="button.id" v-draggable-item> -->
        <!-- <el-button @click="handleClick()" circle size="large" style="margin-top: 400px;margin-left: 100px;">
            <el-icon>
                <HomeFilled />
            </el-icon>
        </el-button> -->
        <!-- </div> -->
        <!-- </template> -->
        <!-- </draggable> -->
    </div>
</template>
  
<script setup>
import { ref } from 'vue';
// import { useRouter } from 'vue-router'
import { ChatDotRound, Grid, DocumentCopy, User, Star, Message, House } from '@element-plus/icons-vue'
// import { draggable, draggableItem } from "vuedraggable";

const isCollapse = ref(false);
const roleId = sessionStorage.getItem('roleId')
// console.log(roleId);

// const buttons = ref([
//     { id: 1, text: '拖拽按钮', route: 'about' },
//     // 可以添加更多的按钮对象
// ]);

// const router = useRouter()
// function handleClick() {
//     router.push('/About'); // 跳转到指定路由
// }

// const arr = ref({
//     list: [
//         {
//             name: "a",
//             id: 1
//         }, {
//             name: "b",
//             id: 2
//         }
//     ]
// })

// const onStart = () => {
//     console.log("onStart");
// }

// const onEnd = () => {
//     console.log("onEnd");
//     // this.drag = true;
// }


const handleOpen = (key, keyPath) => {
    console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
    console.log(key, keyPath)
}
</script>

<style lang="scss" scoped>
.el-button {
    width: 100%;
}

.el-menu {
    border-right: 0px solid;
}

[v-draggable-item] {
    display: contents;
}
</style>
  