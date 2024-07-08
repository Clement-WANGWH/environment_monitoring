<template>
    <el-card class="container">
        <div><!-- <img src="../../assets/info11.png" alt="" class="gg"> -->
            <div class="greet">您好，{{ username }}</div>
            <div class="up">
                <div class="wenzi">
                    <div class="left-align" v-if="roleId == 3"> 欢迎登录环保公众监督系统--公众监督员端。</div>
                    <div class="left-align" v-if="roleId == 2"> 欢迎登录环保公众监督系统--网格员端。</div>
                    <div class="left-align1">如果您的密码在其他网站也使用，建议您修改本网站密码。</div>
                    <div class="left-align">如有任何疑问，请随时联系我们。</div>
                    <div class="left-align" v-if="roleId == 3">如果您需要反馈信息，请您点击</div>
                    <router-link to="/reportView" class="yuding" v-if="roleId == 3">提交反馈</router-link>
                </div>
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
    left: 440px;
    font-size: 30px;
    color: #333;
    margin-bottom: 20px;
    z-index: 1;
}

.wenzi {
    position: absolute;
    top: 250px;
    left: 380px;
    font-size: 15px;
    color: #333;
    margin-bottom: 10px;
    z-index: 1;
    width: 1100px;
    border: 2px dotted greenyellow;
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