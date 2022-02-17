<template>
  <div class="carousel">
    <v-carousel
      continuous
      interval=10800
      :show-arrows="false"
      height="45vh"
      hide-delimiter-background
      cycle
      hide-delimiters
    >
      <v-carousel-item>
        <v-sheet
          height="100%"
          class="sheet1"
        >
        <v-row class="sheet1-content">
          <div class="sheet1-content1">
            같이 공부할 친구를 찾는 것은,
            <br>
            프로젝트보다 만들기 어렵습니다.
          </div>
          <div class="sheet1-content2">
            학습 목표가 같은 동료 개발자를 찾아보세요!
            <br>
            당신이 원하는 스터디룸을 추천해드립니다.
            <br>
            동료와 같이 코드 리뷰를 해보세요!
          </div>
        </v-row>
        <img src="../../assets/seokjun_desk.jpg">
        </v-sheet>
      </v-carousel-item>
      <v-carousel-item>
        <v-sheet
          height="100%"
          class="sheet2"
        >
        <v-row class="sheet2-content">
          <div class="sheet2-content1">
            당신의 열정 100도,
            <br>
            동료와 함께 코드리뷰에서 보여주세요!
          </div>
          <div class="sheet2-content2">
            혼자 공부하기 힘든 분들,
            <br>
            스터디룸에서 같이 공부할 동료를 구하세요!
            <br>
            열정으로 가득찬 스터디를 진행하세요!
          </div>
        </v-row>
        <img src="../../assets/seongyu_desk.jpg">
        </v-sheet>
      </v-carousel-item>
      <v-carousel-item>
        <v-sheet
          height="100%"
          class="sheet3"
        >
        <v-row class="sheet3-content">
          <div class="sheet3-content1">
            개발자가 되고 싶은 당신,
            <br>
            D to D 에서 협업을 경험해보세요!
          </div>
          <div class="sheet3-content2">
            팀 프로젝트를 하고 싶다면,
            <br>
            스터디 모집에 글을 올려보세요!
            <br>
            그리고 열정으로 가득찬 스터디원을 만나세요!
          </div>
        </v-row>
        <img src="../../assets/beom_desk.jpg">
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
  </div>
</template>

<script>

export default {
  name: 'Carousel',
  data () {
    return {

    }
  },
}
</script>

<style scoped>

.sheet1 {
  display: flex;
  justify-content: space-between;
  background-color: #DDF1FF !important;
  margin: 0 !important;
  padding: 0 !important;
}

.sheet1 img {
  width: 30%;
  height: 100%;
}

.sheet1 .sheet1-content {
  display: flex;
  flex-direction: column;
}

.sheet1 .sheet1-content1 {
  font-family: 'Hanna', 'Roboto';
  font-size: 5vh;
  padding: 5vh 0 0 50px;
  line-height: 8vh;
}

.sheet1 .sheet1-content2 {
  font-family: 'Epilogue', sans-serif;
  font-size: 3vh;
  font-weight: 500;
  padding: 3vh 0 0 50px;
  line-height: 5vh;
  color: #33628C;
}

.sheet2 {
  display: flex;
  justify-content: space-between;
  background-color: #EDA68F !important;
  margin: 0 !important;
  padding: 0 !important;
}

.sheet2 img {
  width: 30%;
  height: 100%;
}

.sheet2 .sheet2-content {
  display: flex;
  flex-direction: column;
}

.sheet2 .sheet2-content1 {
  font-family: 'Hanna', 'Roboto';
  font-size: 5vh;
  padding: 5vh 0 0 50px;
  line-height: 8vh;
}

.sheet2 .sheet2-content2 {
  font-family: 'Epilogue', sans-serif;
  font-size: 3vh;
  font-weight: 500;
  padding: 3vh 0 0 50px;
  line-height: 5vh;
  color: #A02B2B;
}

.sheet3 {
  display: flex;
  justify-content: space-between;
  background-color: #c38fed !important;
  margin: 0 !important;
  padding: 0 !important;
}

.sheet3 img {
  width: 30%;
  height: 100%;
}

.sheet3 .sheet3-content {
  display: flex;
  flex-direction: column;
}

.sheet3 .sheet3-content1 {
  font-family: 'Hanna', 'Roboto';
  font-size: 5vh;
  padding: 5vh 0 0 50px;
  line-height: 8vh;
}

.sheet3 .sheet3-content2 {
  font-family: 'Epilogue', sans-serif;
  font-size: 3vh;
  font-weight: 500;
  padding: 3vh 0 0 50px;
  line-height: 5vh;
  color: #672ba0;
}
</style>