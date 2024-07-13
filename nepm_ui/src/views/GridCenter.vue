<template>
	<el-card class="container">
		<div class="content">
			<div class="image-column">
				<img src="../assets/img/trip.png" class="image" />
			</div>
			<div class="panel">
				<div class="welcome">
					<h1 class="greeting">欢迎回来，{{ username }}</h1>
					<h2 class="encourage">今天，也要山明水秀\(￣︶￣*\))</h2>
				</div>
				<div class="card">
					<div class="card-content">
						<p class="normal-word">您还有</p>
						<div class="remained-task">
							<p class="remained-task-number">{{taskNums}}</p>
							<p class="task-word">个</p>
						</div>
						<p class="normal-word">待检测反馈需要处理</p>
					</div>
				</div>
			</div>
		</div>
		
		
	</el-card>
	
</template>

<script>
import { ref,onMounted } from 'vue'
import $axios from '../components/utils/axios';

const username = ref('')
const taskNums = ref(0)

username.value = JSON.parse(
    sessionStorage.getItem('user')
).username

const gId = sessionStorage.getItem('id')

const init = async () => {
    const path = '/feedback/findByGid/' + gId
    const { data: res } = await $axios.get(path)
	if (res.code === 200) {
	    taskNums.value = parseInt(res.msg)
	}
	console.log(tableData.value);
}

export default {
    setup() {
        onMounted(() => {
            init()
        })

        // 如果你需要在模板中使用taskNums，需要返回它
        return {
            taskNums,username
        }
    }
}
</script>

<style scoped>
.container {
    width: 99%;
    height: 95%;
    margin-top: 12px;
    border-radius: 12px;
}

.content {
    display: flex;
    gap: 20px; /* 设置子元素之间的间隔为20px */
	align-items: stretch; 
}

.image-column {
    width: 30%; /* 设置宽度为30% */
}
.image{
	width:100%;
}

.panel {
    flex-grow: 1; /* 自动占据剩余的宽度 */
    display: flex;
    flex-direction: column;
}

.welcome {
	display: flex;
	flex-direction: column;
	gap:15px;
	margin-top:20px;
}
.greeting {
	font-size: 48px;
	font-weight: 600;
}
.encourage {
	font-size: 18px;
	font-weight:200;
}



.card {
  margin-top: 30px;
  border-radius: 12px;
  box-shadow: 0px 0px 12px 2px rgba(184, 184, 210, 0.2);
  overflow: hidden;
  display: flex;
  flex-direction: column; /* 使内部组件垂直排列 */
  flex-grow: 1;
  padding-top: 20px; /* 内部组件距离上边框10px */
  padding-bottom: 20px; /* 内部组件距离下边框10px */
}
.card-content{
	display: flex;
	flex-direction: column; /* 使内部组件垂直排列 */
	justify-content: space-between; /* 保持内部组件的间距弹性相同 */
	align-items: center; /* 保持水平居中 */
	flex-grow: 1;
}
.remained-task {
  display: flex; /* 设置为flex布局实现横向排布 */
  align-items: center; /* 垂直居中内部组件 */
}

.remained-task-number {
  font-size: 54px; /* 字号设置为40px */
  color: #d80003; /* 字体颜色设置为红色 */
  font-weight: 650;
}

.task-word {
  margin-left: 7px; /* 距左边7px */
  font-weight:450;
  color:#a8a8a8;
}


.card-content {
  width: 100%;
  height: 100%;
  padding: 10px;
}
</style>