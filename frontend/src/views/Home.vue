<template>
  <div class="home">
    <Carousel />
    <RecommendedStudy />
  </div>
</template>

<script>
import Carousel from '@/components/Home/Carousel.vue'
import RecommendedStudy from '@/components/Home/RecommendedStudy.vue'

export default {
  name: 'Home',
  components: {
    Carousel,
    RecommendedStudy,
  }
}
</script>

<style>
@font-face {
  font-family: "Dohyeon";
  src: url("../assets/font/BMDOHYEON_ttf.ttf");
}

@font-face {
  font-family: "Hanna";
  src: url("../assets/font/BMHANNAPro.ttf");
}
</style>