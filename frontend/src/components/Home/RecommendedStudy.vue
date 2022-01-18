<template>
  <div>
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
    <Slider />
  </div>
</template>

<script>
import Slider from '../Home/Slider.vue'

export default {
  name: 'RecommendedStudy',
  components: {
    Slider,
  }
}
</script>

<style>
.sort-icon {
  margin: 20px 0 0 20px;
  display: flex;
}

.sort-icon .icon {
  display: grid;
  padding: 0 20px 0 20px;
  text-align: center;
}

.sort-icon .icon i {
  font-size:35px; 
}

.sort-icon .icon span {
  font-weight: bold;
}

</style>