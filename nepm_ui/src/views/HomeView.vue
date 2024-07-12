<template>
    <div id="home">
        <el-container class="page-container">
            <el-header>
                <HomeHeader></HomeHeader>
            </el-header>
            <el-container>
                <!-- 68px  -->
                <el-aside width="200px">
                    <!-- 主页的左侧导航栏 -->
                    <AsideMenu></AsideMenu>
                </el-aside>
                <el-main>
                    <HomeBreadcrumb></HomeBreadcrumb>
                    <RouterView></RouterView>
                    <!-- <ChinaCharts></ChinaCharts> -->
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>

import AsideMenu from '../components/home/AsideMenu.vue'
import HomeHeader from '../components/home/HomeHeader.vue'
import HomeBreadcrumb from '../components/home/HomeBreadcrumb.vue'
// import ChinaCharts from '../components/chart/ChinaCharts.vue';
// const roleId = sessionStorage.getItem('roleId')
</script>

<style scoped lang="scss">
//lang="scss"支持css嵌套
#home {
    width: 100%;
    height: 100%;

    // background-color:#b6f499;
    .page-container {
        width: 100%;
        height: 100%;
        // background-color:#b6f499;

        .el-main {
            background-color: rgb(245, 247, 255);
            padding: 15px;
        }

        .el-header {
            height: 68px;
            background-color: #2075cb;
        }

        .el-aside {
            background-color: #3286da;
            width: 250px;
        }
    }
}
</style>
