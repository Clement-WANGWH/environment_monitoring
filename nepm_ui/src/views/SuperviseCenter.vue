<template>
	<el-card class="container">
		<div class="content">
			<div class="top">
				<div class="decoration"></div>
			</div>
			<div class="formal">
				<div class="encourage-column">
					<div class="slogan">
						<h1 style="font-size:32px; color:#054a21; font-weight:550">环境监督</h1>
						<h1 style="font-size:36px; color:#69A614; font-weight:750">守护绿色未来</h1>
					</div>
					<div class="divider"></div>
					<div class="appealing">
						你们的工作不仅仅是一份责任，更是一份对地球和后代的承诺。你们的每一次检查，每一次记录，都是为了让我们的家园更加美好。
					</div>
					<div class="prosecute" @click="navigateToReportView">提交反馈</div>
				</div>
				<div class="picture-column">
					<img src="../assets/img/supervise.png" class="picture" />
				</div>
			</div>
		</div>
	</el-card>
</template>

<script>
export default {
  methods: {
    navigateToReportView() {
      this.$router.push('/reportView');
    }
  }
}
</script>

<style>
.container {
    width: 99%;
    height: 95%;
    margin-top: 12px;
    border-radius: 12px;
}

.content {
  display: flex;
  flex-direction: column; /* 纵向排列 */
  gap: 20px; /* 内部间距20px */
  padding-right: 20px; /* 距离右边框20px */
  width: 100%;
}

.top {
  width: 100%; /* 宽度100% */
  margin-top: 10px;
}

.decoration {
  margin-left: auto; /* 推动到右侧 */
  margin-right: 15px; /* 距右边距15px */
  height: 37px; /* 高度7px */
  width: 16%; /* 宽度35px */
  border-radius: 32px; /* 圆角30度 */
  background: linear-gradient(to right, rgba(112, 175, 20, 1), rgba(30, 69, 20, 1)); /* 颜色渐变 */
}

.formal {
  display: flex; /* 横向排布 */
  justify-content: space-between; /* 子元素间隔均匀分布 */
  margin-left: 2%; /* 距左边5% */
}

.encourage-column {
  width: 40%; /* 宽度占24% */
  margin-top: 8%; /* 垂直居中偏上10% */
  display: flex;
  flex-direction: column; /* 纵向排布 */
  align-items: flex-start; /* 元素向左对齐 */
}

.picture-column {
  flex-grow: 1; /* 占据剩下的宽度 */
  margin-left: 8px; /* 与encourage-column间隔8px */
}

.slogan {
  display: flex;
  flex-direction: column; /* 纵向排布 */
  gap: 16px; /* 元素间隔8px */
}

.divider {
  margin-top: 28px;
  margin-bottom: 24px;
  width: 43px;
  height: 5px;
  background: rgba(105, 166, 20, 1);
  border-radius:3px;
}

.prosecute {
  margin-top: 55px; /* 距离上元素28px */
  height: 36px; /* 高度7px */
  width: 70%; /* 宽度35px */
  border-radius: 32px; /* 圆角30度 */
  background: linear-gradient(to right, rgba(112, 175, 20, 1), rgba(30, 69, 20, 1)); /* 颜色渐变 */
  display: flex; /* 使用flex布局 */
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  text-align: center; /* 文字水平居中 */
  color: white; /* 文字颜色为白色 */
  font-weight: 350;
  font-size: 14px;
  letter-spacing: 1.5px;
}

.picture {
  width: auto; /* 保持原长宽比 */
  height: auto; /* 保持原长宽比 */
  max-width: 100%; /* 防止溢出 */
}

.appealing{
	color: #1A581B;
	font-size: 14px;
	font-weight:200;
	line-height: 20px;
}
</style>