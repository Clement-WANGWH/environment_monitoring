<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span class="h">反馈任务列表---网格员端</span>
                <el-button class="button" @click="init" text>刷新</el-button>
            </div>
        </template>

        <!-- 表格 -->
        <el-table :data="tableData" height="550" style="width: 100%" :row-class-name="tableRowClassName">
            <el-table-column prop="estimatedGrade" label="预测AQI等级">
                <template #default="scope">
                    <p v-if="scope.row.aqi.id == '1'" style="color: #67C23A;">优（一）</p>
                    <p v-if="scope.row.aqi.id == '2'" style="color: #FFFF00;">良好（二）</p>
                    <p v-if="scope.row.aqi.id == '3'" style="color: #FFA500;">轻度污染（三）</p>
                    <p v-if="scope.row.aqi.id == '4'" style="color: #FF0000;">中度污染（四）</p>
                    <p v-if="scope.row.aqi.id == '5'" style="color: #800080;">重度污染（五）</p>
                    <p v-if="scope.row.aqi.id == '6'" style="color: #800000;">严重污染（六）</p>
                </template>
            </el-table-column>
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
            <el-table-column prop="tId" label="县（区）">
                <template #default="scope">
                    {{
                        scope.row.town.name
                    }}
                </template>
            </el-table-column>
            <el-table-column prop="fDate" label="反馈日期" />
            <el-table-column prop="assignDate" label="指派日期" />
            <el-table-column prop="address" label="具体地址" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button @click="handleEdit(scope.$index, scope.row)" :icon="Filter">去监测</el-button>
                    <!-- <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button> -->

                    <!-- 抽屉 -->
                    <el-drawer ref="drawerRef" v-model="dialog" title="反馈任务详细信息" :before-close="handleClose" direction="rtl"
                        class="demo-drawer">
                        <div class="demo-drawer__content">
                            <el-descriptions class="margin-top" :column="1" :size="size" border>
                                <!-- <template #extra>
                        <el-button type="primary">Operation</el-button>
                    </template> -->
                                <el-descriptions-item>
                                    <template #label>
                                        <div class="cell-item">
                                            <el-icon :style="iconStyle">
                                                <user />
                                            </el-icon>
                                            反馈用户
                                        </div>
                                    </template>
                                    <el-tag style="margin-right: 2px;">{{ scope.row.supervisor.name }}</el-tag>
                                    <el-tag style="margin-right: 2px;" v-if="scope.row.supervisor.sex == '0'">男</el-tag>
                                    <el-tag style="margin-right: 2px;" v-if="scope.row.supervisor.sex == '1'">女</el-tag>
                                    <el-tag style="margin-right: 2px;">{{ scope.row.supervisor.telephone }}</el-tag>
                                </el-descriptions-item>
                                <el-descriptions-item>
                                    <template #label>
                                        <div class="cell-item">
                                            <el-icon :style="iconStyle">
                                                <Grid />
                                            </el-icon>
                                            网格
                                        </div>
                                    </template>
                                    <el-tag style="margin-right: 2px;">{{ scope.row.province.name }}</el-tag>
                                    <el-tag style="margin-right: 2px;">{{ scope.row.city.name }}</el-tag>
                                    <el-tag style="margin-right: 2px;">{{ scope.row.town.name }}</el-tag>
                                </el-descriptions-item>
                                <el-descriptions-item>
                                    <template #label>
                                        <div class="cell-item">
                                            <el-icon :style="iconStyle">
                                                <location />
                                            </el-icon>
                                            详细地址
                                        </div>
                                    </template>
                                    <el-tag style="margin-right: 2px;">{{ scope.row.address }}</el-tag>
                                </el-descriptions-item>
                                <el-descriptions-item>
                                    <template #label>
                                        <div class="cell-item">
                                            <el-icon :style="iconStyle">
                                                <tickets />
                                            </el-icon>
                                            预估等级
                                        </div>
                                    </template>
                                    <el-tag effect="dark" color="#67C23A" class="tagclass"
                                        v-if="scope.row.aqi.id == '1'">一级</el-tag>
                                    <el-tag effect="dark" color="#FFFF00" class="tagclass"
                                        v-if="scope.row.aqi.id == '2'">二级</el-tag>
                                    <el-tag effect="dark" color="#FFA500" class="tagclass"
                                        v-if="scope.row.aqi.id == '3'">三级</el-tag>
                                    <el-tag effect="dark" color="#FF0000" class="tagclass"
                                        v-if="scope.row.aqi.id == '4'">四级</el-tag>
                                    <el-tag effect="dark" color="#800080" class="tagclass"
                                        v-if="scope.row.aqi.id == '5'">五级</el-tag>
                                    <el-tag effect="dark" color="#800000" class="tagclass"
                                        v-if="scope.row.aqi.id == '6'">六级</el-tag>

                                    <el-tag effect="dark" color="#67C23A" class="tagclass"
                                        v-if="scope.row.aqi.id == '1'">优</el-tag>
                                    <el-tag effect="dark" color="#FFFF00" class="tagclass"
                                        v-if="scope.row.aqi.id == '2'">良好</el-tag>
                                    <el-tag effect="dark" color="#FFA500" class="tagclass"
                                        v-if="scope.row.aqi.id == '3'">轻度污染</el-tag>
                                    <el-tag effect="dark" color="#FF0000" class="tagclass"
                                        v-if="scope.row.aqi.id == '4'">中度污染</el-tag>
                                    <el-tag effect="dark" color="#800080" class="tagclass"
                                        v-if="scope.row.aqi.id == '5'">重度污染</el-tag>
                                    <el-tag effect="dark" color="#800000" class="tagclass"
                                        v-if="scope.row.aqi.id == '6'">严重污染</el-tag>
                                </el-descriptions-item>
                                <el-descriptions-item>
                                    <template #label>
                                        <div class="cell-item">
                                            <el-icon :style="iconStyle">
                                                <ChatDotSquare />
                                            </el-icon>
                                            信息描述
                                        </div>
                                    </template>
                                    <el-tag style="margin-right: 2px;">{{ scope.row.info }}</el-tag>
                                </el-descriptions-item>
                            </el-descriptions>
                        </div>
                        <div class="demo-drawer__content" style="margin-top: 20px;">
                            <el-form :model="form">
                                <el-form-item label="SO₂浓度" :label-width="formLabelWidth">
                                    <el-input v-model="form.so2Value" autocomplete="off" placeholder="请输入SO₂浓度"
                                        style="width: 150px;" @input="SO2input" />ug/m3
                                    <el-tag v-if="form.so2Level == '0'"></el-tag>
                                    <el-tag effect="dark" color="#67C23A" class="tagclass"
                                        v-if="form.so2Level == '1'">一级</el-tag>
                                    <el-tag effect="dark" color="#FFFF00" class="tagclass"
                                        v-if="form.so2Level == '2'">二级</el-tag>
                                    <el-tag effect="dark" color="#FFA500" class="tagclass"
                                        v-if="form.so2Level == '3'">三级</el-tag>
                                    <el-tag effect="dark" color="#FF0000" class="tagclass"
                                        v-if="form.so2Level == '4'">四级</el-tag>
                                    <el-tag effect="dark" color="#800080" class="tagclass"
                                        v-if="form.so2Level == '5'">五级</el-tag>
                                    <el-tag effect="dark" color="#800000" class="tagclass"
                                        v-if="form.so2Level == '6'">六级</el-tag>
                                </el-form-item>
                                <el-form-item label="CO浓度" :label-width="formLabelWidth">
                                    <el-input v-model="form.coValue" autocomplete="off" placeholder="请输入CO浓度"
                                        style="width: 150px;" @input="COinput" />ug/m3
                                    <el-tag v-if="form.coLevel == '0'"></el-tag>
                                    <el-tag effect="dark" color="#67C23A" class="tagclass"
                                        v-if="form.coLevel == '1'">一级</el-tag>
                                    <el-tag effect="dark" color="#FFFF00" class="tagclass"
                                        v-if="form.coLevel == '2'">二级</el-tag>
                                    <el-tag effect="dark" color="#FFA500" class="tagclass"
                                        v-if="form.coLevel == '3'">三级</el-tag>
                                    <el-tag effect="dark" color="#FF0000" class="tagclass"
                                        v-if="form.coLevel == '4'">四级</el-tag>
                                    <el-tag effect="dark" color="#800080" class="tagclass"
                                        v-if="form.coLevel == '5'">五级</el-tag>
                                    <el-tag effect="dark" color="#800000" class="tagclass"
                                        v-if="form.coLevel == '6'">六级</el-tag>
                                </el-form-item>
                                <el-form-item label="PM2.5浓度" :label-width="formLabelWidth">
                                    <el-input v-model="form.spmValue" autocomplete="off" placeholder="请输入PM2.5浓度"
                                        style="width: 150px;" @input="spminput" />ug/m3
                                    <el-tag v-if="form.coLevel == '0'"></el-tag>
                                    <el-tag effect="dark" color="#67C23A" class="tagclass"
                                        v-if="form.spmLevel == '1'">一级</el-tag>
                                    <el-tag effect="dark" color="#FFFF00" class="tagclass"
                                        v-if="form.spmLevel == '2'">二级</el-tag>
                                    <el-tag effect="dark" color="#FFA500" class="tagclass"
                                        v-if="form.spmLevel == '3'">三级</el-tag>
                                    <el-tag effect="dark" color="#FF0000" class="tagclass"
                                        v-if="form.spmLevel == '4'">四级</el-tag>
                                    <el-tag effect="dark" color="#800080" class="tagclass"
                                        v-if="form.spmLevel == '5'">五级</el-tag>
                                    <el-tag effect="dark" color="#800000" class="tagclass"
                                        v-if="form.spmLevel == '6'">六级</el-tag>
                                </el-form-item>
                                <el-form-item label="实测AQI等级" :label-width="formLabelWidth">
                                    <el-tag v-if="form.aqiId == null">暂无实测等级</el-tag>
                                    <el-tag effect="dark" color="#67C23A" class="tagclass" v-if="form.aqiId == '1'">一级
                                        优</el-tag>
                                    <el-tag effect="dark" color="#FFFF00" class="tagclass" v-if="form.aqiId == '2'">二级
                                        良好</el-tag>
                                    <el-tag effect="dark" color="#FFA500" class="tagclass" v-if="form.aqiId == '3'">三级
                                        轻度污染</el-tag>
                                    <el-tag effect="dark" color="#FF0000" class="tagclass" v-if="form.aqiId == '4'">四级
                                        中度污染</el-tag>
                                    <el-tag effect="dark" color="#800080" class="tagclass" v-if="form.aqiId == '5'">五级
                                        重度污染</el-tag>
                                    <el-tag effect="dark" color="#800000" class="tagclass" v-if="form.aqiId == '6'">六级
                                        严重污染</el-tag>

                                    <el-button type="danger" @click="calculateIAQI"
                                        style="margin-left: 20px;">计算IAQI</el-button>
                                </el-form-item>
                                <!-- <el-form-item> -->
                                <!-- <div> -->
                                <!-- <el-button type="danger" @click="calculateIAQI">计算IAQI</el-button> -->
                                <!-- </div> -->
                                <!-- </el-form-item> -->
                                <el-form-item label="请填写监测信息" :label-width="formLabelWidth">
                                    <el-input v-model="form.info" maxlength="30" placeholder="请填写监测信息" show-word-limit
                                        type="textarea" />
                                </el-form-item>
                            </el-form>
                            <div class="demo-drawer__footer" style="margin-left: 100px;">
                                <el-button type="primary" :loading="loading" @click="onSubmit">{{ loading ? '提交中 ...' :
                                    '提交实测数据'
                                }}</el-button>
                                <el-button @click="cancelForm">关闭</el-button>
                            </div>
                        </div>
                    </el-drawer>
                </template>
            </el-table-column>
        </el-table>

    </el-card>
</template>
<!-- 知识点、技能 -->
<!-- 项目一：东软人口数据库管理系统 -->

<script setup>
import { ref, computed, onMounted } from 'vue'
import $axios from '../utils/axios';
import { Grid, Location, ChatDotSquare, Tickets, User, Filter } from '@element-plus/icons-vue'
import { ElDrawer, ElMessageBox, ElNotification } from 'element-plus'

const formLabelWidth = '130px'
let timer

const dialog = ref(false)
const loading = ref(false)

const form = ref({})

// 表格初始化
const tableData = ref([])
const taskNums = ref(0)
// let isNotified = false; // 全局变量用于跟踪是否已经显示了消息提醒

const gId = sessionStorage.getItem('id')
let isNotified = sessionStorage.getItem('isNotified') === 'true' ? true : false;
const init = async () => {
    const path = '/feedback/findByGid/' + gId
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        tableData.value = res.result
        taskNums.value = parseInt(res.msg)

        if (!isNotified) {
            ElNotification({
                title: '任务提醒',
                message: `您有 ${taskNums.value} 条未检测任务，请尽快处理!`,
                type: 'warning'
            })
            // 标记已经显示过消息提醒
            isNotified = true; // 标记已经显示过消息提醒
            sessionStorage.setItem('isNotified', 'true');
        }
    }
    console.log(tableData.value);
}

onMounted(init)

const tableRowClassName = ({ rowIndex }) => {
    return rowIndex % 2 === 1 ? 'success-row' : ''
}

const updateTable = ref({})

//打开抽屉
const handleEdit = (index, row) => {
    dialog.value = true
    form.value.pId = row.province.id
    form.value.cId = row.city.id
    form.value.tId = row.town.id
    form.value.address = row.address
    form.value.sId = row.supervisor.id
    form.value.gId = row.grid.id
    form.value.fId = row.id
    updateTable.value = row
    console.log(index, row, updateTable.value)
}

// const handleDelete = (index, row) => {
//     console.log(index, row)
// }


// 抽屉
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


// 从数据库获取判断条件判断污染物级别
const levelData = ref({})
const level = async () => {
    const path = '/aqi/findAll'
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        levelData.value = res.result
    }
}
level()

const SO2input = () => {
    for (let index = 0; index < levelData.value.length; index++) {
        if (form.value.so2Value >= levelData.value[index].so2Min && form.value.so2Value <= levelData.value[index].so2Max) {
            form.value.so2Level = levelData.value[index].id
        }
    }
}

const COinput = () => {
    for (let index = 0; index < levelData.value.length; index++) {
        if (form.value.coValue >= levelData.value[index].coMin && form.value.coValue <= levelData.value[index].coMax) {
            form.value.coLevel = levelData.value[index].id
        }
    }
}

const spminput = () => {

    for (let index = 0; index < levelData.value.length; index++) {
        // console.log(levelData.value[index]);
        if (form.value.spmValue >= levelData.value[index].spmMin && form.value.spmValue <= levelData.value[index].spmMax) {
            form.value.spmLevel = levelData.value[index].id
            // console.log(form.value.spmValue);
            // console.log(form.value.spmLevel);
        }
    }

}

// 计算IAQI
const calculateIAQI = () => {

    let IAQI = ''
    let so2IAQI = ''
    let coIAQI = ''
    let spmIAQI = ''
    for (let index = 0; index < levelData.value.length; index++) {
        if (form.value.so2Level === levelData.value[index].id) {
            let x = levelData.value[index].iaqiMax - levelData.value[index].iaqiMin //500-300
            let y = levelData.value[index].so2Max - levelData.value[index].so2Min //2620-1600
            let z = form.value.so2Value - levelData.value[index].so2Min //2000-1600
            so2IAQI = x / y * z + levelData.value[index].iaqiMin
            // console.log("so2IAQI:"+so2IAQI+" "+x+" "+y+" "+z);
        }
    }
    for (let index = 0; index < levelData.value.length; index++) {
        if (form.value.spmLevel === levelData.value[index].id) {
            let x = levelData.value[index].iaqiMax - levelData.value[index].iaqiMin
            let y = levelData.value[index].coMax - levelData.value[index].coMin
            let z = form.value.coValue - levelData.value[index].coMin
            coIAQI = x / y * z + levelData.value[index].iaqiMin
            // console.log("coIAQI:"+coIAQI+" "+x+" "+y+" "+z);
        }
    }
    for (let index = 0; index < levelData.value.length; index++) {
        if (form.value.spmLevel === levelData.value[index].id) {
            let x = levelData.value[index].iaqiMax - levelData.value[index].iaqiMin
            let y = levelData.value[index].spmMax - levelData.value[index].spmMin
            let z = form.value.spmValue - levelData.value[index].spmMin
            spmIAQI = x / y * z + levelData.value[index].iaqiMin
        }
    }

    IAQI = (so2IAQI > coIAQI) ? so2IAQI : coIAQI;
    IAQI = (IAQI > spmIAQI) ? IAQI : spmIAQI;

    for (let index = 0; index < levelData.value.length; index++) {
        if (IAQI > levelData.value[index].iaqiMin && IAQI <= levelData.value[index].iaqiMax) {
            form.value.aqiId = levelData.value[index].id
        }
    }

}

//获取当前反馈日期和时间
function dateFormat() {
    var now = new Date()
    var year = now.getFullYear()
    var month = now.getMonth() + 1 < 10 ?
        '0' + (now.getMonth() + 1) : now.getMonth() + 1
    var day = now.getDate() < 10 ? '0' + now.getDate() : now.getDate()
    var hour = now.getHours() < 10 ? '0' + now.getHours() : now.getHours()
    var minute = now.getMinutes() < 10 ? '0' + now.getMinutes() : now.getMinutes()
    var second = now.getSeconds() < 10 ? '0' + now.getSeconds() : now.getSeconds()
    form.value.confirmDate = year + '-' + month + '-' + day
    form.value.confirmTime = hour + ":" + minute + ":" + second
}


// const drawerRef = ref<InstanceType<typeof ElDrawer>>()
// const onClick = () => {
//   drawerRef.valueOf!.close()
// }

const updateGrid = ref({})
const drawerRef = ref(null);
const onSubmit = async () => {
    // console.log(tableData.value.id);
    dateFormat()
    const path = '/statistics/add'
    const { data: res } = await $axios.put(path, form.value)
    console.log(form.value);

    if (res.code === 200) {

        dialog.value = false
        // 修改反馈状态
        updateTable.value.state = 2
        const path = '/feedback/update'
        const { data: res } = await $axios.post(path, updateTable.value)
        if (res.code === 200) {
            init()
            console.log("修改成功!");
        }
        //修改网格员状态
        const path1 = '/feedback/findByGid/' + gId
        const { data: res1 } = await $axios.get(path1)
        if (res1.code === 100) {
            updateGrid.value.id = gId
            updateGrid.value.state = 0
            const path = '/user/update'
            const { data: res } = await $axios.post(path, updateGrid.value)
            if (res.code === 200) {
                console.log("已修改网格员状态为空闲！！！");
            }
        }
        ElNotification({
            title: '成功',
            message: '已成功提交监测数据！',
            type: 'success',
        })
    } else if (res.code === 101) {
        ElNotification({
            title: '失败',
            message: '未知原因，提交失败！',
            type: 'error',
        })
    }
    drawerRef.value.close();
};

const handleClose = (done) => {
    if (loading.value) {
        return
    }
    ElMessageBox.confirm('需要提交数据吗？')
        .then(() => {
            loading.value = true
            timer = setTimeout(() => {
                done()
                // 动画关闭需要一定的时间
                setTimeout(() => {
                    loading.value = false
                }, 400)
            }, 2000)
        })
        .catch(() => {
            // catch error
        })
}

const cancelForm = () => {
    loading.value = false
    dialog.value = false
    clearTimeout(timer)
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

/* .item {
    margin-bottom: 18px;
} */

.el-descriptions {
    margin-top: 20px;
}

.cell-item {
    display: flex;
    align-items: center;
}
.h {
    font-size: 25px;
    margin-top: 15px;
    
}
.margin-top {
    margin-top: 5px;
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
  