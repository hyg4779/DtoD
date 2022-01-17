<template>
  <div class="home">
    <div>
      <q-carousel
        class="carousel"
        animated
        v-model="slide"
        navigation
        infinite
        :autoplay="autoplay"
        arrows
        transition-prev="slide-right"
        transition-next="slide-left"
        @mouseenter="autoplay = false"
        @mouseleave="autoplay = true"
      >
        <q-carousel-slide :name="1" class="slide1">
          <div>
            <span>
              같이 공부할 친구를 찾는 것은,
              <br>
              프로젝트보다 만들기 어렵습니다.
            </span>
          </div>
          <img src="../assets/carousel1.png" alt="1">
        </q-carousel-slide>
        <q-carousel-slide :name="2" class="slide2">
        </q-carousel-slide>
        <q-carousel-slide :name="3" class="slide3">
        </q-carousel-slide>
      </q-carousel>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'Home',
  components: {

  },
  setup () {
    return {
      slide: ref(1),
      // autoplay: ref(true)
    }
  }
}
</script>

<style>
.carousel {
  
}

.slide1 {
  display: flex;
  justify-content: space-between;
  background-color: #DDF1FF;
  margin: 0;
  padding: 0 !important;
}

.slide1 > img {
  /* float: right; */
  width: 35%;
  height: 100%;
}

</style>