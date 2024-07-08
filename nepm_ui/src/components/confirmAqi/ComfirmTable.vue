<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <ComfirmSearchBar @search="handleSearch" :currentPage="currentPage" :pageSize="pageSize" />
            </div>
        </template>
        <el-table :data="tableData" :row-class-name="tableRowClassName" style="width: 100%" height="440">

            <!-- 展开行内容 -->
            <el-table-column type="expand">
                <template #default="props">
                    <el-descriptions class="margin-top" title="空气质量监测数据详情" :column="2" style="width:75%" border>
                        <el-descriptions-item>
                            <template #label>
                                <div class="cell-item">
                                    <el-icon :style="iconStyle">
                                        <UserFilled />
                                    </el-icon>
                                    反馈者信息
                                </div>
                            </template>
                            <el-tag style="margin-right: 2px;">{{ props.row.supervisor.name }}</el-tag>
                            <el-tag style="margin-right: 2px;" v-if="props.row.supervisor.sex == '0'">男</el-tag>
                            <el-tag style="margin-right: 2px;" v-if="props.row.supervisor.sex == '1'">女</el-tag>
                            <el-tag style="margin-right: 2px;">{{ props.row.supervisor.telephone }}</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template #label>
                                <div class="cell-item">
                                    <el-icon :style="iconStyle">
                                        <Avatar />
                                    </el-icon>
                                    网格员信息
                                </div>
                            </template>
                            <el-tag style="margin-right: 2px;">{{ props.row.gridman.name }}</el-tag>
                            <el-tag style="margin-right: 2px;" v-if="props.row.gridman.sex == '0'">男</el-tag>
                            <el-tag style="margin-right: 2px;" v-if="props.row.gridman.sex == '1'">女</el-tag>
                            <el-tag style="margin-right: 2px;">{{ props.row.gridman.telephone }}</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template #label>
                                <div class="cell-item">
                                    <el-icon :style="iconStyle">
                                        <location />
                                    </el-icon>
                                    确认信息所在地
                                </div>
                            </template>
                            <el-tag style="margin-right: 5px;">{{ props.row.province.name }}</el-tag>
                            <el-tag style="margin-right: 5px;">{{ props.row.city.name }}</el-tag>
                            <el-tag style="margin-right: 5px;">{{ props.row.town.name }}</el-tag>
                            <el-tag style="margin-right: 5px;">{{ props.row.address }}</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template #label>
                                <div class="cell-item">
                                    <el-icon :style="iconStyle">
                                        <Edit />
                                    </el-icon>
                                    确认AQI等级
                                </div>
                            </template>
                            <el-tag effect="dark" color="#67C23A" class="tagclass" v-if="props.row.aqiId == '1'">一级</el-tag>
                            <el-tag effect="dark" color="#FFFF00" class="tagclass" v-if="props.row.aqiId == '2'">二级</el-tag>
                            <el-tag effect="dark" color="#FFA500" class="tagclass" v-if="props.row.aqiId == '3'">三级</el-tag>
                            <el-tag effect="dark" color="#FF0000" class="tagclass" v-if="props.row.aqiId == '4'">四级</el-tag>
                            <el-tag effect="dark" color="#800080" class="tagclass" v-if="props.row.aqiId == '5'">五级</el-tag>
                            <el-tag effect="dark" color="#800000" class="tagclass" v-if="props.row.aqiId == '6'">六级</el-tag>
                            <el-tag effect="dark" color="#67C23A" class="tagclass" v-if="props.row.aqiId == '1'">优</el-tag>
                            <el-tag effect="dark" color="#FFFF00" class="tagclass" v-if="props.row.aqiId == '2'">良好</el-tag>
                            <el-tag effect="dark" color="#FFA500" class="tagclass"
                                v-if="props.row.aqiId == '3'">轻度污染</el-tag>
                            <el-tag effect="dark" color="#FF0000" class="tagclass"
                                v-if="props.row.aqiId == '4'">中度污染</el-tag>
                            <el-tag effect="dark" color="#800080" class="tagclass"
                                v-if="props.row.aqiId == '5'">重度污染</el-tag>
                            <el-tag effect="dark" color="#800000" class="tagclass"
                                v-if="props.row.aqiId == '6'">严重污染</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template #label>
                                <div class="cell-item">
                                    <el-icon :style="iconStyle">
                                        <Clock />
                                    </el-icon>
                                    确认AQI日期时间
                                </div>
                            </template>
                            <el-tag style="margin-right: 5px;">{{ props.row.confirmDate }}</el-tag>
                            <el-tag style="margin-right: 5px;">{{ props.row.confirmTime }}</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template #label>
                                <div class="cell-item">
                                    <el-icon :style="iconStyle">
                                        <tickets />
                                    </el-icon>
                                    确认AQI信息描述
                                </div>
                            </template>
                            {{ props.row.info }}
                        </el-descriptions-item>
                    </el-descriptions>
                </template>
            </el-table-column>

            <!-- table部分 -->
            <el-table-column prop="id" label="#" width="60px" />
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
            <el-table-column prop="tId" label="县区">
                <template #default="scope">
                    {{
                        scope.row.town.name
                    }}
                </template>
            </el-table-column>
            <el-table-column prop="aqiId" label="AQI等级">
                <template #default="scope">
                    <p v-if="scope.row.aqiId == '1'" style="color: #67C23A;">优（一）</p>
                    <p v-if="scope.row.aqiId == '2'" style="color: #FFFF00;">良好（二）</p>
                    <p v-if="scope.row.aqiId == '3'" style="color: #FFA500;">轻度污染（三）</p>
                    <p v-if="scope.row.aqiId == '4'" style="color: #FF0000;">中度污染（四）</p>
                    <p v-if="scope.row.aqiId == '5'" style="color: #800080;">重度污染（五）</p>
                    <p v-if="scope.row.aqiId == '6'" style="color: #800000;">严重污染（六）</p>
                </template>
            </el-table-column>
            <el-table-column prop="confirmDate" label="确认日期" />
            <!-- <el-table-column prop="confirmTime" label="确认时间" /> -->
            <el-table-column prop="gId" label="网格员">
                <template #default="scope">
                    {{
                        scope.row.gridman.name
                    }}
                </template>
            </el-table-column>
            <el-table-column prop="sId" label="反馈者姓名">
                <template #default="scope">
                    {{
                        scope.row.supervisor.name
                    }}
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
                        style="width: max-content;margin-top: 50px" />
                </el-config-provider>
            </div>
        </div>
    </el-card>
</template>
  
<script setup>
import { computed, ref } from 'vue'
import { Avatar, Edit, Location, Tickets, UserFilled, Clock } from '@element-plus/icons-vue'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import en from 'element-plus/dist/locale/en.mjs'
import $axios from '../utils/axios'
import ComfirmSearchBar from './ComfirmSearchBar.vue';

const language = ref('zh-cn')
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : en))

const size = ref('')
const iconStyle = computed(() => {
    const marginMap = {
        large: '8px',
        default: '6px',
        small: '4px',
    }
    return {
        marginRight: marginMap[size.value] || marginMap.default,
    }
})


//斑马条样式
const tableRowClassName = ({ rowIndex }) => {
    return rowIndex % 2 === 1 ? 'success-row' : ''
}

const tableData = ref([])

const currentPage = ref(1)
const pageSize = ref(10)
const total = ref('')
const background = ref(true)

// 初始化
const handleCurrentChange = async () => {
    // const path = `/statistics/findAllByPage/${currentPage.value}/${pageSize.value}` //ES6写法
    const path = '/statistics/findAllByPage/' + currentPage.value + '/' + pageSize.value
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
        total.value = parseInt(res.msg)
    }
    console.log(tableData.value, total.value)
}
handleCurrentChange()

//获得子组件条件查询结果
const handleSearch = (data, current, size, tt) => {
    tableData.value = data
    currentPage.value = current
    pageSize.value = size
    total.value = tt
}


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

.el-descriptions {
    margin-top: 20px;
}

.cell-item {
    display: flex;
    align-items: center;
}

.margin-top {
    margin-top: 20px;
}

.tagclass {
    margin-left: 5px;
    color: black;
    border: none;
}
</style>

<style>
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>
  