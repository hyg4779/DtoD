<template>
  <div class="sort-icon">
      <div class="icon">
        <i class="fab fa-font-awesome-flag"></i>
        <span>추천순</span>
      </div>
      <div class="icon">
        <i class="fas fa-history"></i>
        <span>추천순</span>
      </div>
      <div class="icon">
        <i class="fas fa-chart-line"></i>
        <span>추천순</span>
      </div>      
  </div>
</template>

<script>

export default {
  name: 'RecommendedStudy',

}
</script>

<style>
.sort-icon {
  margin: 10px 0 0 20px;
  display: flex;
}

.sort-icon .icon {
  display: grid;
  padding: 0 10px 0 10px;
  text-align: center;
}

.sort-icon .icon i {
  font-size:35px; 
}
</style>