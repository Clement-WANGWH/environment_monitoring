<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span>其他数据统计</span>
                <el-button class="button" text>刷新</el-button>
            </div>
        </template>
        <el-table :data="tableData" height="250" style="width: 100%">
            <el-table-column prop="date" label="空气质量检测总数量" />
            <el-table-column prop="name" label="空气质量检测良好数量" />
            <el-table-column prop="address" label="省会城市网格覆盖范围" />
            <el-table-column prop="address" label="大城市网格覆盖范围" />
        </el-table>
    </el-card>
</template>

<script setup>
import { ref } from 'vue'

const tableData = ref([])


</script>
  
<style scoped>
.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}

.box-card {
    width: 99%;
    height: 95%;
    margin-top: 12px;
    border-radius: 12px;
}
</style>
  