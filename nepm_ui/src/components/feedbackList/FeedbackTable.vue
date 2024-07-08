<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <FeedbackSearchBar @search="handleSearch" :currentPage="currentPage" :pageSize="pageSize" />
            </div>
        </template>
        <el-config-provider :locale="locale">
            <el-table :data="tableData" :row-class-name="tableRowClassName" height="440"
                :default-sort="{ prop: 'fDate', order: 'ascending' }" row-key="id" style="width: 100%">

                <!-- 展开行内容 -->
                <el-table-column type="expand" width="60px">
                    <template #default="props">
                        <el-descriptions class="descriptions" title="公众监督数据详情" :column="2" style="width: 75%" border>
                            <template #extra>
                                <el-tooltip class="box-item" effect="light" content="指派" placement="bottom">
                                    <span>
                                        <el-popover placement="left" :width="300" trigger="click" :visible="visible">
                                            <template #reference>
                                                <el-button type="success" :icon="Pointer" circle
                                                    :disabled="props.row.state === 0 ? false : true"
                                                    @click="visible = true" />
                                            </template>
                                            <div>
                                                <el-form :inline="true" :model="formAssign" class="demo-form-inline"
                                                    :label-width="labelWidth1">
                                                    <el-form-item label="是否异地指派">
                                                        <el-switch v-model="remote" />
                                                    </el-form-item>

                                                    <!-- 省市县选择子组件 -->
                                                    <address-select @selectChanged="selectChanged" :initvalue="formAssign"
                                                        :labelWidth="labelWidth" :selectWidth="selectWidth" />
                                                    <!-- <address-select  /> -->

                                                    <el-form-item label="被指派网格员">
                                                        <el-select v-model="updateFrom.gId" placeholder="选择网格员"
                                                            style="width: 120px" clearable :disabled="girmanDisabled"
                                                            value-key="id">
                                                            <el-option v-for="(gridman, key) in gridmans" :key="key"
                                                                :value="gridman.id" :label="gridman.name">
                                                                <span style="float: left">{{ gridman.name }}</span>
                                                                <span
                                                                    style="float: right;color: var(--el-text-color-secondary);font-size: 13px;"
                                                                    v-if="gridman.state == 0">空闲</span>
                                                                <span
                                                                    style="float: right;color: var(--el-text-color-secondary);font-size: 13px;"
                                                                    v-if="gridman.state == 1">工作中</span>
                                                                <span
                                                                    style="float: right;color: var(--el-text-color-secondary);font-size: 13px;"
                                                                    v-if="gridman.state == 2">休假中</span>
                                                            </el-option>
                                                        </el-select>
                                                    </el-form-item>
                                                    <el-form-item>
                                                        <el-button type="primary"
                                                            @click="onAssign(props.row)">确认指派</el-button>
                                                        <el-button type="primary" @click="visible = false">关闭</el-button>
                                                    </el-form-item>
                                                </el-form>
                                            </div>
                                        </el-popover>
                                    </span>
                                </el-tooltip>
                            </template>
                            <el-descriptions-item>
                                <template #label>
                                    <div class="cell-item">
                                        <el-icon :style="iconStyle">
                                            <user />
                                        </el-icon>
                                        反馈者信息
                                    </div>
                                </template>
                                <el-tag style="margin-right: 2px">{{ props.row.supervisor.name }}</el-tag>
                                <el-tag style="margin-right: 2px" v-if="props.row.supervisor.sex == '0'">男</el-tag>
                                <el-tag style="margin-right: 2px" v-if="props.row.supervisor.sex == '1'">女</el-tag>
                                <el-tag style="margin-right: 2px" v-if="props.row.supervisor.birthday">{{
                                    props.row.supervisor.birthday }}</el-tag>
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template #label>
                                    <div class="cell-item">
                                        <el-icon :style="iconStyle">
                                            <iphone />
                                        </el-icon>
                                        反馈者联系电话
                                    </div>
                                </template>
                                <el-tag>{{ props.row.supervisor.telephone }}</el-tag>
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template #label>
                                    <div class="cell-item">
                                        <el-icon :style="iconStyle">
                                            <location />
                                        </el-icon>
                                        反馈信息所在地
                                    </div>
                                </template>
                                <el-tag style="margin-right: 5px">{{ props.row.province.name }}</el-tag>
                                <el-tag style="margin-right: 5px">{{ props.row.city.name }}</el-tag>
                                <el-tag style="margin-right: 5px">{{ props.row.town.name }}</el-tag>
                                <el-tag style="margin-right: 5px">{{ props.row.address }}</el-tag>
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template #label>
                                    <div class="cell-item">
                                        <el-icon :style="iconStyle">
                                            <Odometer />
                                        </el-icon>
                                        预估污染等级
                                    </div>
                                </template>

                                <el-tag effect="dark" color="#67C23A" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '1'">一级</el-tag>
                                <el-tag effect="dark" color="#FFFF00" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '2'">二级</el-tag>
                                <el-tag effect="dark" color="#FFA500" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '3'">三级</el-tag>
                                <el-tag effect="dark" color="#FF0000" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '4'">四级</el-tag>
                                <el-tag effect="dark" color="#800080" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '5'">五级</el-tag>
                                <el-tag effect="dark" color="#800000" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '6'">六级</el-tag>

                                <el-tag effect="dark" color="#67C23A" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '1'">优</el-tag>
                                <el-tag effect="dark" color="#FFFF00" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '2'">良好</el-tag>
                                <el-tag effect="dark" color="#FFA500" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '3'">轻度污染</el-tag>
                                <el-tag effect="dark" color="#FF0000" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '4'">中度污染</el-tag>
                                <el-tag effect="dark" color="#800080" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '5'">重度污染</el-tag>
                                <el-tag effect="dark" color="#800000" style="margin-right: 5px; color: black;border: none;"
                                    v-if="props.row.aqi.id == '6'">严重污染</el-tag>
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template #label>
                                    <div class="cell-item">
                                        <el-icon :style="iconStyle">
                                            <ChatLineSquare />
                                        </el-icon>
                                        反馈信息描述
                                    </div>
                                </template>
                                <el-tag>{{ props.row.info }}</el-tag>
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template #label>
                                    <div class="cell-item">
                                        <el-icon :style="iconStyle">
                                            <DocumentChecked />
                                        </el-icon>
                                        反馈时间
                                    </div>
                                </template>
                                <el-tag style="margin-right: 5px">{{ props.row.fDate }}</el-tag>
                                <el-tag style="margin-right: 5px">{{ props.row.fTime }}</el-tag>
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template #label>
                                    <div class="cell-item">
                                        <el-icon :style="iconStyle">
                                            <Star />
                                        </el-icon>
                                        反馈信息状态
                                    </div>
                                </template>
                                <el-tag style="margin-right: 5px" v-if="props.row.state == '0'">未指派</el-tag>
                                <el-tag style="margin-right: 5px" v-if="props.row.state == '1'">已指派</el-tag>
                                <el-tag style="margin-right: 5px" v-if="props.row.state == '2'">已确认</el-tag>
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template #label>
                                    <div class="cell-item">
                                        <el-icon :style="iconStyle">
                                            <Checked />
                                        </el-icon>
                                        指派时间
                                    </div>
                                </template>
                                <el-tag style="margin-right: 5px">{{ props.row.assignDate ? props.row.assignDate : "尚未指派"
                                }}</el-tag>
                                <el-tag v-if="props.row.assignTime" style="margin-right: 5px">{{ props.row.assignTime
                                }}</el-tag>
                            </el-descriptions-item>


                            <el-descriptions-item v-if="props.row.grid">
                                <template #label>
                                    <div class="cell-item">
                                        <el-icon :style="iconStyle">
                                            <user />
                                        </el-icon>
                                        网格员信息
                                    </div>
                                </template>
                                <el-tag style="margin-right: 2px">{{ props.row.grid.name }}</el-tag>
                                <el-tag style="margin-right: 2px" v-if="props.row.grid.sex == '0'">男</el-tag>
                                <el-tag style="margin-right: 2px" v-if="props.row.grid.sex == '1'">女</el-tag>
                                <el-tag style="margin-right: 2px" v-if="props.row.grid.birthday">{{ props.row.grid.birthday
                                }}</el-tag>
                            </el-descriptions-item>
                            <el-descriptions-item v-if="props.row.grid">
                                <template #label>
                                    <div class="cell-item">
                                        <el-icon :style="iconStyle">
                                            <iphone />
                                        </el-icon>
                                        网格员联系电话
                                    </div>
                                </template>
                                <el-tag>{{ props.row.grid.telephone }}</el-tag>
                            </el-descriptions-item>
                        </el-descriptions>
                    </template>
                </el-table-column>

                <!-- table部分 -->
                <el-table-column prop="id" label="反馈信息编号" width="120px" />
                <el-table-column prop="sId" label="反馈者姓名" width="120px">
                    <template #default="scope">
                        {{ scope.row.supervisor ? scope.row.supervisor.name : "" }}
                    </template>
                </el-table-column>
                <el-table-column prop="pId" label="省份" width="120px">
                    <template #default="scope">
                        {{ scope.row.province ? scope.row.province.name : "" }}
                    </template>
                </el-table-column>
                <el-table-column prop="cId" label="城市" width="120px">
                    <template #default="scope">
                        {{ scope.row.city ? scope.row.city.name : "" }}
                    </template>
                </el-table-column>
                <el-table-column prop="tId" label="县区" width="120px">
                    <template #default="scope">
                        {{ scope.row.town ? scope.row.town.name : "" }}
                    </template>
                </el-table-column>
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
                <el-table-column prop="fDate" label="反馈日期" sortable />
                <el-table-column prop="state" label="状态" :filters="[
                    { text: '未指派', value: 0 },
                    { text: '已指派', value: 1 },
                    { text: '已确认', value: 2 },
                ]" :filter-method="filterState" filter-placement="bottom-end">
                    <template #default="scope">
                        <p v-if="scope.row.state == '0'" style="color: #ff0000"><el-icon :style="iconStyle">
                                <CircleCloseFilled />
                            </el-icon>未指派</p>
                        <p v-if="scope.row.state == '1'" style="color: #ffa500"><el-icon :style="iconStyle">
                                <WarningFilled />
                            </el-icon>已指派</p>
                        <p v-if="scope.row.state == '2'" style="color: #67c23a"><el-icon :style="iconStyle">
                                <SuccessFilled />
                            </el-icon>已确认</p>
                    </template>
                </el-table-column>

            </el-table>

            <!-- 分页 -->
            <div style="text-align: -webkit-center">
                <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :small="small"
                    :page-sizes="[6, 7, 8, 9, 10]" :background="background" layout="prev, pager, next, jumper,sizes,total"
                    v-model:total="total" @current-change="handleCurrentChange" @size-change="handleCurrentChange"
                    style="width: max-content;margin-top: 50px" />
            </div>
        </el-config-provider>
    </el-card>
</template>

<script setup>
import { computed, ref } from 'vue'
import $axios from '../utils/axios'
import { ElNotification } from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import en from 'element-plus/dist/locale/en.mjs'
import { Pointer, Iphone, Location, Star, User, Odometer, ChatLineSquare, DocumentChecked, Checked, CircleCloseFilled, WarningFilled, SuccessFilled } from '@element-plus/icons-vue'
import FeedbackSearchBar from './FeedbackSearchBar.vue';
import AddressSelect from '../supervisor/addressSelect.vue'

const language = ref('zh-cn')
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : en))

const size = ref('')
const iconStyle = computed(() => {
    const marginMap = {
        large: '10px',
        default: '6px',
        small: '4px'
    }
    return {
        marginRight: marginMap[size.value] || marginMap.default
    }
})
//斑马条样式
const tableRowClassName = ({ rowIndex }) => {
    return rowIndex % 2 === 1 ? 'success-row' : ''
}

//按状态筛选
const filterState = (value, row) => {
    return row.state === value
}

const remote = ref(true)
const visible = ref(false)
//向搜索栏组件传值
const labelWidth1 = '100px'
let selectWidth = '120px'
let labelWidth = '100px'

const currentPage = ref(1)
const pageSize = ref(10)
const total = ref('')
const background = ref(true)

const formAssign = ref({})

const tableData = ref([])

const girmanDisabled = ref(true)
const gridmans = ref({})
const updateFrom = ref({})

//获得子组件条件查询结果
const handleSearch = (data, current, size, tt) => {
    tableData.value = data
    currentPage.value = current
    pageSize.value = size
    total.value = tt
}

// 初始化
const handleCurrentChange = async () => {
    const path = '/feedback/findAllByPage/' + currentPage.value + '/' + pageSize.value
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
        total.value = parseInt(res.msg)
    }
    console.log("反馈信息列表   ");
    console.log(tableData.value);
}
handleCurrentChange()


//向省市县组件传值，查找对应的网格员
const selectChanged = async (f, lw, sw) => {
    formAssign.value = f
    labelWidth = lw
    selectWidth = sw
    // visible.value = true
    girmanDisabled.value = false
    // const tId = formAssign.value.tId
    const path = '/user/findGridmanByTid/' + formAssign.value.town.id
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        gridmans.value = res.result
    }
    console.log(gridmans.value);
    console.log(formAssign.value);
}

// 获取当前指派时间
function dateFormat() {
    var now = new Date()
    var year = now.getFullYear()
    var month = now.getMonth() + 1 < 10 ?
        '0' + (now.getMonth() + 1) : now.getMonth() + 1
    var day = now.getDate() < 10 ? '0' + now.getDate() : now.getDate()
    var hour = now.getHours() < 10 ? '0' + now.getHours() : now.getHours()
    var minute = now.getMinutes() < 10 ? '0' + now.getMinutes() : now.getMinutes()
    var second = now.getSeconds() < 10 ? '0' + now.getSeconds() : now.getSeconds()
    updateFrom.value.assignDate = year + '-' + month + '-' + day
    updateFrom.value.assignTime = hour + ":" + minute + ":" + second
}

const updateGrid = ref({})

// 指派给网格员
const onAssign = async (row) => {
    console.log("指派");
    // console.log(row);
    dateFormat()
    updateFrom.value.id = row.id
    updateFrom.value.state = 1
    console.log(updateFrom.value);
    const path = '/feedback/update'
    const { data: res } = await $axios.post(path, updateFrom.value)
    if (res.code === 200) {
        visible.value = false
        updateGrid.value.id = updateFrom.value.gId
        updateGrid.value.state = 1
        const path = '/user/update'
        const { data: res } = await $axios.post(path, updateGrid.value)
        if (res.code === 200) {
            console.log("已修改网格员状态");
        }
        handleCurrentChange()
        ElNotification({
            title: '成功',
            message: '已成功指派网格员！',
            type: 'success',
        })
    } else if (res.code === 103) {
        ElNotification({
            title: '失败',
            message: '未知原因，指派网格员失败！',
            type: 'error',
        })

    }
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

.descriptions {
    margin-top: 20px;
}
</style>

<style>
.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>
