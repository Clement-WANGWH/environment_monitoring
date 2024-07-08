<template>
  <div id="homeheader">
    <div style="display: flex;">
      <div class="logo">
        <img src="../../assets/img/nepmLogo.gif" />
      </div>
      <div class="title">
        <p>环保公众监督系统</p>
      </div>
    </div>
    <div style="display: flex;align-items: center;">
      <el-dropdown @command="handleCommand">
        <!-- http://localhost:18081/avatar/pangke.jpg -->
        <el-avatar v-if="avatar == null" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
        <el-avatar v-if="avatar !== null" :src="imgUrl" />
        <el-button>
          Dropdown List<el-icon class="el-icon--right"><arrow-down /></el-icon>
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="ChinaCharts" v-if="roleId == 1">首页</el-dropdown-item>
            <el-dropdown-item command="UploadAvatarCopy">更换头像</el-dropdown-item>
            <el-dropdown-item command="PersonalInfo">账号设置</el-dropdown-item>
            <el-dropdown-item command="login">切换账号</el-dropdown-item>
            <el-dropdown-item command="login">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      你好，{{ username }}
      <!-- <el-button :icon="SwitchButton" @click="toLogin">退出</el-button> -->
    </div>
  </div>
</template>

<script setup>
// import { SwitchButton } from '@element-plus/icons-vue'
import { ref } from 'vue';
import { useRouter } from 'vue-router';

// import { useUserStore } from '../../stores/user'
// import { storeToRefs } from 'pinia'
// const userStore = useUserStore()

const router = useRouter()
const handleCommand = (command) => {
  router.push(`${command}`)
}

const roleId = sessionStorage.getItem('roleId')

// const toLogin = () => {
//   router.push("/login")
// }

const username = ref('')
username.value = JSON.parse(
  sessionStorage.getItem('user')
).username
// console.log(username.value);

const avatar = sessionStorage.getItem('avatar')
console.log(avatar);
const imgUrl = sessionStorage.getItem('imgUrl')
console.log(imgUrl);
// const url = ref("http://localhost:18081/avatar/" + username.value + ".jpg")
// console.log(url.value)
// const { imageUrl } = storeToRefs(userStore);
// console.log(imageUrl);
// const initAvatar = () => {
//   userStore.initAvatar()
// }
// initAvatar()

</script>

<style lang="scss" scoped>
#homeheader {
  display: flex;
  justify-content: space-between;

  .logo {
    img {
      height: 68px;
    }
  }

  .title {
    p {
      line-height: 68px;
      font-family: '微软雅黑', Courier, monospace;
      font-size: 1.875rem;
      color: rgb(0, 0, 0);
    }
  }
}
</style>