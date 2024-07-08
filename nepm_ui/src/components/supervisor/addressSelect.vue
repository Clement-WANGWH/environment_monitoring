<template>
    <el-form :inline="true" :model="formAssign" class="demo-form-inline" :label-width="labelWidth">
        <el-form-item label="省份" prop="pId">
            <el-select v-model="formAssign.province" placeholder="请选择省份" @change="provinceChange" clearable value-key="id"
                :style="{ width: selectWidth }">
                <el-option v-for="(province, key) in provinces" :key="key" :value="province" :label="province.name">
                    {{ province.name }}
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="城市" prop="cId">
            <el-select v-model="formAssign.city" placeholder="请选择城市" clearable value-key="id" @change="cityChange"
                :disabled="!formAssign.province" :style="{ width: selectWidth }">
                <el-option v-for="(city, key) in cities" :value="city" :key="key" :label="city.name">
                    {{ city.name }}
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="县/区" prop="tId">
            <el-select v-model="formAssign.town" placeholder="请选择区县" clearable value-key="id" @change="townChange"
                :disabled="!formAssign.city || !formAssign.province" :style="{ width: selectWidth }">
                <el-option v-for="(town, key) in towns" :value="town" :key="key" :label="town.name">
                    {{ town.name }}
                </el-option>
            </el-select>
        </el-form-item>
        <!-- <el-form-item label="省份" prop="pId">
            <el-select v-model="formAssign.pId" placeholder="请选择省份" @change="provinceChange" clearable value-key="id"
                :style="{ width: selectWidth }">
                <el-option v-for="(province, key) in provinces" :key="key" :value="province.id" :label="province.name">
                    {{ province.name }}
                </el-option>
            </el-select>
        </el-form-item> -->
        <!-- <el-form-item label="城市" prop="cId">
            <el-select v-model="formAssign.cId" placeholder="请选择城市" clearable value-key="id" @change="cityChange"
                :disabled="!formAssign.pId" :style="{ width: selectWidth }">
                <el-option v-for="(city, key) in cities" :value="city.id" :key="key" :label="city.name">
                    {{ city.name }}
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="县/区" prop="tId">
            <el-select v-model="formAssign.tId" placeholder="请选择区县" clearable value-key="id" @change="townChange"
                :disabled="!formAssign.cId || !formAssign.pId" :style="{ width: selectWidth }">
                <el-option v-for="(town, key) in towns" :value="town.id" :key="key" :label="town.name">
                    {{ town.name }}
                </el-option>
            </el-select>
        </el-form-item> -->
    </el-form>
</template>

<script setup>
import { ref } from 'vue'
import $axios from '../utils/axios'

const formAssign = ref({})

// 在子组件中定义一个事件
const emits = defineEmits(['selectChanged', 'a', 'b'])

// 在组件中定义属性
//  B 父向子传值，defineProps  props.initvalue 收到父组件传递的数据-->
//  C ：自定义属性是只读的
const props = defineProps({
    initvalue: {
        type: Object,
        required: true,
        default() {
            return {}
        }
    },
    labelWidth: {
        type: String,
        default: '100px' // 设置默认的 label 宽度
    },
    selectWidth: {
        type: String,
        default: '50px' // 设置默认的 select 宽度
    }
})

//D 同步，读取父组件传递的数据给value
// formAssign.value = props.initvalue
const labelWidth = props.labelWidth
const selectWidth = props.selectWidth


const provinces = ref({})
// 加载省份
const init = async () => {
    const path = '/grid/findProvince'
    const { data: res } = await $axios.get(path)
    // console.log(res);
    if (res.code === 200) {
        provinces.value = res.result
        // console.log(provinces.value)
    }
}
init()

const cities = ref({})
//省份改变的监听事件，查询省份的城市
let provinceChange = async (provincep) => {
    console.log(provincep.id);
    const path = '/grid/findCityByPid/' + provincep.id
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        cities.value = res.result
        console.log(cities.value)
    }
    // if (val == '' || val == null) {
    //     formAssign.value.citie = ''
    //     formAssign.value.town = ''
    // }

}

const towns = ref({})
//城市改变的监听事件，查询城市的区/县
let cityChange = async (city) => {
    console.log(city.id);
    const path = '/grid/findTownByCid/' + city.id
    const { data: res } = await $axios.get(path)
    if (res.code === 200) {
        towns.value = res.result
        console.log(towns.value)
    }

}

let townChange = (town) => {
    console.log(town.id)
    // 子组件向父组件传值,依赖的组件的自定义事件完成的
    // 子组件发生一个事件
    //  emits:op1:事件名称，op2:事件携带的数据
    emits('selectChanged', formAssign.value)
    console.log(formAssign.value);
}
</script>
