<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span>历史反馈信息列表</span>
                <!-- <el-button class="button" text>Operation button</el-button> -->
            </div>
        </template>

        <el-table :data="tableData" style="width: 100%" height="480" :row-class-name="tableRowClassName">
            <el-table-column type="expand">
                <template #default="props">
                    <el-timeline style="width:500px;margin-left:50px;">
                        <el-timeline-item :timestamp="props.row.fDate" placement="top" type="success" size="large"
                            :icon="Select">
                            <el-card shadow="hover">
                                <h3>提交反馈时间</h3>
                                <p style="margin-top:10px;">{{ props.row.fDate }} {{ props.row.fTime }}</p>
                            </el-card>
                        </el-timeline-item>
                        <el-timeline-item placement="top"
                            :timestamp="props.row.assignDate == null ? '' : props.row.assignDate" size="large"
                            :type="props.row.assignDate == null ? 'warning' : 'success'"
                            :icon="props.row.assignDate == null ? SemiSelect : Select">
                            <el-card v-if="props.row.state == 2 || props.row.state == 1" shadow="hover">
                                <h3>指派网格员时间</h3>
                                <p style="margin-top:10px;">{{ props.row.assignDate }} {{ props.row.assignTime }}</p>
                            </el-card>
                            <el-card v-else shadow="hover">
                                <h3>尚未指派</h3>
                            </el-card>
                        </el-timeline-item>
                        <el-timeline-item size="large" placement="top"
                            :timestamp="props.row.statistics == null ? '' : props.row.statistics.confirmDate"
                            :type="props.row.statistics == null ? 'danger' : 'success'"
                            :icon="props.row.statistics == null ? CloseBold : Select">
                            <el-card v-if="props.row.state == 2" shadow="hover">
                                <h3>检测确认时间</h3>
                                <p style="margin-top:10px;">{{ props.row.statistics.confirmDate }} {{
                                    props.row.statistics.confirmTime }}</p>
                                <p v-if="props.row.statistics.aqiId == '1'" style="color: #67C23A;margin-top:10px;">优（一）</p>
                                <p v-if="props.row.statistics.aqiId == '2'" style="color: #FFFF00;margin-top:10px;">良好（二）
                                </p>
                                <p v-if="props.row.statistics.aqiId == '3'" style="color: #FFA500;margin-top:10px;">轻度污染（三）
                                </p>
                                <p v-if="props.row.statistics.aqiId == '4'" style="color: #FF0000;margin-top:10px;">中度污染（四）
                                </p>
                                <p v-if="props.row.statistics.aqiId == '5'" style="color: #800080;margin-top:10px;">重度污染（五）
                                </p>
                                <p v-if="props.row.statistics.aqiId == '6'" style="color: #800000;margin-top:10px;">严重污染（六）
                                </p>
                            </el-card>
                            <el-card v-else shadow="hover">
                                <h3>尚未实测</h3>
                            </el-card>
                        </el-timeline-item>
                    </el-timeline>
                </template>
            </el-table-column>
            <el-table-column prop="id" label="编号" width="80px" />
            <el-table-column prop="aqi" label="预估污染等级">
                <template #default="scope">
                    <p v-if="scope.row.aqi.id == '1'" style="color: #67c23a">优（一）</p>
                    <p v-if="scope.row.aqi.id == '2'" style="color: #ffff00">良好（二）</p>
                    <p v-if="scope.row.aqi.id == '3'" style="color: #ffa500">轻度污染（三）</p>
                    <p v-if="scope.row.aqi.id == '4'" style="color: #ff0000">中度污染（四）</p>
                    <p v-if="scope.row.aqi.id == '5'" style="color: #800080">重度污染（五）</p>
                    <p v-if="scope.row.aqi.id == '6'" style="color: #800000">严重污染（六）</p>
                </template>
            </el-table-column>
            <el-table-column prop="fDate" label="反馈日期" />
            <el-table-column prop="fTime" label="反馈时间" />
            <el-table-column prop="pId" label="省份">
                <template #default="scope">
                    {{
                        scope.row.province.name
                    }}
                </template>
            </el-table-column>
            <el-table-column prop="cId" label="城市">
                <template #default="scope">
                    {{
                        scope.row.city.name
                    }}
                </template>
            </el-table-column>
            <el-table-column prop="tId" label="县/区">
                <template #default="scope">
                    {{
                        scope.row.town.name
                    }}
                </template>
            </el-table-column>
            <el-table-column prop="state" label="状态">
                <template #default="scope">
                    <p v-if="scope.row.state == '0'" style="color: #f89898"><el-icon :style="iconStyle">
                            <CircleCloseFilled />
                        </el-icon>尚未指派</p>
                    <p v-if="scope.row.state == '1'" style="color: #eebe77"><el-icon :style="iconStyle">
                            <WarningFilled />
                        </el-icon>尚未实测</p>
                    <p v-if="scope.row.state == '2'" style="color: #95d475"><el-icon :style="iconStyle">
                            <SuccessFilled />
                        </el-icon>已确认</p>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <div style="text-align: -webkit-center">
            <div style="margin-right: 30px;">
                <el-config-provider :locale="locale">
                    <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :small="small"
                        :page-sizes="[6, 7, 8, 9, 10]" :background="background"
                        layout="prev, pager, next, jumper,sizes,total" v-model:total="total"
                        @current-change="handleCurrentChange" @size-change="handleCurrentChange"
                        style="width: max-content;margin-top: 35px" />
                </el-config-provider>
            </div>
        </div>
    </el-card>
</template>
  
<script setup>
import { ref, computed } from 'vue'
import $axios from '../utils/axios'
import { CircleCloseFilled, WarningFilled, SuccessFilled, Select, SemiSelect, CloseBold } from '@element-plus/icons-vue'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import en from 'element-plus/dist/locale/en.mjs'

const language = ref('zh-cn')
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : en))

const tableRowClassName = ({ rowIndex }) => {
    return rowIndex % 2 === 1 ? 'success-row' : ''
}

const tableData = ref([])
const currentPage = ref(1)
const pageSize = ref(6)
const total = ref('')
const background = ref(true)

const form = ref({})

const sId = sessionStorage.getItem('id')
// const init = async () => {
//     const path = '/feedback/findBySid/' + sId
//     const { data: res } = await $axios.get(path)
//     if (res.code === 200) {
//         tableData.value = res.result
//     }
//     for (let index = 0; index < tableData.value.length; index++) {
//         tableData.value[index].id = index + 1;
//     }
//     console.log(tableData.value, sId);
// }
// init()

const handleCurrentChange = async () => {
    form.value.sId = sId
    form.value.currentPage = currentPage.value
    form.value.pageSize = pageSize.value
    const path = '/feedback/findBySidByPage'
    const { data: res } = await $axios.post(path, form.value)
    if (res.code === 200) {
        tableData.value = res.result
        total.value = parseInt(res.msg)
    }
    for (let index = 0; index < tableData.value.length; index++) {
        tableData.value[index].id = index + 1;
    }
    console.log(tableData.value, sId);
}
handleCurrentChange()

</script>

<style scoped>
.box-card {
    width: 99%;
    height: 95%;
    margin-top: 12px;
    border-radius: 12px;
}

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
</style>

<style>
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>