<template>
  <div class="carousel">
    <q-carousel
      animated
      v-model="slide"
      navigation
      infinite
      :autoplay="10800"
      arrows
      height="450px"
      transition-prev="slide-right"
      transition-next="slide-left"
      @mouseenter="autoplay = false"
      @mouseleave="autoplay = true"
    >
      <q-carousel-slide :name="1" class="slide1">
        <div>
          <div class="slider1-content1">
            같이 공부할 친구를 찾는 것은,
            <br>
            프로젝트보다 만들기 어렵습니다.
          </div>
          <div class="slider1-content2">
            학습 목표가 같은 동료 개발자를 찾아보세요!
            <br>
            당신이 원하는 스터디룸을 추천해드립니다.
            <br>
            동료와 같이 코드 리뷰를 해보세요!
          </div>
        </div>
        <img src="../../assets/carousel1.png" alt="1">
      </q-carousel-slide>
      <q-carousel-slide :name="2" class="slide2">
        <div>
          <div class="slider2-content1">
            당신의 열정,
            <br>
            동료와 함께 코드리뷰에서 보여주세요!
          </div>
          <div class="slider2-content2">
            혼자 공부하기 힘든 분들,
            <br>
            스터디룸에서 같이 공부할 동료를 구하세요!
            <br>
            열정으로 가득찬 스터디를 진행하세요!
          </div>
        </div>
        <img src="../../assets/carousel1.png" alt="2">
      </q-carousel-slide>
    </q-carousel>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'Carousel',
  setup () {
    return {
      slide: ref(1),
      autoplay: ref(true)
    }
  }
}
</script>

<style>
@font-face {
  font-family: "Dohyeon";
  src: url("../../assets/font/BMDOHYEON_ttf.ttf");
}

@font-face {
  font-family: "Hanna";
  src: url("../../assets/font/BMHANNAPro.ttf");
}

.slide1 {
  display: flex;
  justify-content: space-between;
  background-color: #DDF1FF;
  margin: 0;
  padding: 0 !important;
}

.slide1 img {
  width: 30%;
  height: 100%;
}

.slide1 .slider1-content1 {
  font-family: 'Hanna', 'Roboto';
  font-size: 45px;
  padding: 30px 0 0 60px;
}

.slide1 .slider1-content2 {
  font: 'Tomorrow', sans-serif;
  font-size: 25px;
  font-weight: 500;
  padding: 15px 0 0 60px;
  color: #33628C;
}

.slide2 {
  display: flex;
  justify-content: space-between;
  background-color: #EDA68F;
  margin: 0;
  padding: 0 !important;
}

.slide2 img {
  width: 30%;
  height: 100%;
}

.slide2 .slider2-content1 {
  font-family: 'Hanna', 'Roboto';
  font-size: 45px;
  padding: 30px 0 0 60px;
}

.slide2 .slider2-content2 {
  font: 'Tomorrow', sans-serif;
  font-size: 25px;
  font-weight: 500;
  padding: 15px 0 0 60px;
  color: #A02B2B;
}
</style>