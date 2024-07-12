<template>
    <el-card class="container">
        <template #header>
            <div class="card-header">
                <div    v-if="roleId == 3"> 
                        <div class="h">公众监督员端</div> 
                    </div>
                    <div   v-if="roleId == 2"> 
                        <div class="h"> 网格员端</div>
                       
                    </div>
            </div>
        </template>
        <div><!-- <img src="../../assets/info11.png" alt="" class="gg"> -->
            
            <div >
                <!-- <div class="wenzi"> -->
                    
                         
                    <div  v-if="roleId == 3">
                        若想反馈信息，请您点击下方按钮
                    </div>
                    <!-- <router-link to="/reportView" class="yuding" v-if="roleId == 3">提交反馈</router-link> -->
                     <br>
                    <el-button color="#626aef"  v-if="roleId == 3" @click="handleButtonClick">提交反馈</el-button>
                <!-- </div> -->
            </div>
        </div>
        <div>
            <div class="else">

            </div>
        </div>
    </el-card>
</template>

<script setup>

import { ref } from 'vue'
const username = ref('')

username.value = JSON.parse(
    sessionStorage.getItem('user')
).username
const roleId = sessionStorage.getItem('roleId')
const handleButtonClick = () => {
    window.location.href = '/reportView';
}
</script>

<style scoped>
.container {
    width: 99%;
    height: 95%;
    margin-top: 12px;
    border-radius: 12px;
}

.greet {
    position: absolute;
    top: 200px;
    left: 380px;
    font-size: 30px;
    color: #333;
    margin-bottom: 20px;
    z-index: 1;
}
.card-header {
    display: flex;
    justify-content: center;
    align-items: center;
}
.h {
    font-size: 25px;
    margin-top: 15px;
    
}
.wenzi {
    position: absolute;
    top: 250px;
    left: 380px;
    font-size: 15px;
    color: #333;
    margin-bottom: 10px;
    z-index: 1;
    width: 500px;gridContainer: grid;
    border: 2px double rgb(0, 0, 0);
    padding: 25px;
}

.left-align {
    text-align: left;
    margin-top: 10px;
}

.left-align1 {
    text-align: left;
    margin-top: 10px;
    color: red;
}

.yuding {
    position: absolute;
    top: 105px;
    left: 240px;
    padding: 5px;

    color: rgb(59, 153, 252);
    font-size: 15px;
    z-index: 1;
}
</style>