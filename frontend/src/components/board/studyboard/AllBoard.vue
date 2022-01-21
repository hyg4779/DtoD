<template>
  <div class="allboard">
    <div class="container">
      <div class="title">
        전체 글
      </div>
      <div class="sort-icon">
        <div class="icon">
          <i class="fab fa-font-awesome-flag"></i>
          <span>추천순</span>
        </div>
        <div class="icon">
          <i class="fas fa-history"></i>
          <span>최신순</span>
        </div>
        <div class="icon">
          <i class="fas fa-chart-line"></i>
          <span>인기순</span>
        </div>      
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.allboard .container {
  display: flex;
  justify-content: space-between;
}

.allboard .title {
  font-size: 30px;
  font-weight: bold;
  color: #0D1350;
  margin: 20px 0 0 20px;
}

.allboard .sort-icon {
  margin: 20px 0 0 20px;
  display: flex;
}

.allboard .sort-icon .icon {
  display: grid;
  padding: 0 30px 0 0;
  text-align: center;
}

.allboard .sort-icon .icon i {
  font-size:25px; 
}

.allboard .sort-icon .icon span {
  font-weight: bold;
}
</style>