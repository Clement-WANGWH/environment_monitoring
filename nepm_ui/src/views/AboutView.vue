<template>
  <div class="about">
    <div class="distri">
      <copyDistribution />
    </div>
    <div class="china">
      <ChinaCharts />
    </div>
    <div class="pro">
      <copyProGroup />
    </div>
    <div class="trend">
      <copyTrend />
    </div>
  </div>
</template>

<script setup>
import ChinaCharts from '../components/chart/ChinaCharts.vue';
import copyDistribution from '../components/chart/copyDistribute.vue'
import copyProGroup from '../components/chart/copyProGroup.vue'
import copyTrend from '../components/chart/copyTrend.vue'
</script>

<style scoped>
.about {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  height: 100vh;
  padding: 20px;
}

.distri {
  flex: 1;
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
}

.china {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  z-index: 1;
}

.pro {
  flex: 1;
  display: flex;
  justify-content: flex-end;
  align-items: flex-start;
  position: relative;
  z-index: 2;
}

.trend {
  flex: 1;
  display: flex;
  justify-content: flex-end;
  align-items: flex-end;
  position: relative;
  z-index: 3;
}
</style>
