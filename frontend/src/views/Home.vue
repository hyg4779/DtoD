<template>
  <div class="home">
    <Carousel />
    <RecommendedStudy />
  </div>
</template>

<script>
import Carousel from '@/components/home/Carousel.vue'
import RecommendedStudy from '@/components/home/RecommendedStudy.vue'

export default {
  name: 'Home',
  components: {
    Carousel,
    RecommendedStudy,
  }
}
</script>

<style>

</style>
