<template>
  <div class="aboutus">
    <br>
    <div class="container">
      <div class="title">
        D TO D
        <br>
        (Develop TOgether to be Developer)
      </div>
      <div class="introduce">
        <div >
          <div class="picture-icon">
            <img src="../../assets/people/seongyu.png" alt="seongyu">
          </div>
          <div class="name">
            <div>
              이름: 한선규
            </div>
            <div>
              Backend
            </div>
          </div>
        </div>
        <div >
          <div class="picture-icon">
            <img src="../../assets/people/jeongseok.png" alt="jeongseok">
          </div>
          <div class="name">
            <div>
              이름: 안정석
            </div>
            <div>
              Backend
            </div>
          </div>
        </div>
        <div >
          <div class="picture-icon">
            <img src="../../assets/people/beom.png" alt="seongyu">
          </div>
          <div class="name">
            <div>
              이름: 허범
            </div>
            <div>
              Leader
            </div>
          </div>
        </div>
        <div >
          <div class="picture-icon">
            <img src="../../assets/people/dongchul.png" alt="dongchul">
          </div>
          <div class="name">
            <div>
              이름: 이동철
            </div>
            <div>
              Frontend
            </div>
          </div>
        </div>
        <div>
          <div class="picture-icon">
            <img src="../../assets/people/seongyu.png" alt="seokjun">
          </div>
          <div class="name">
            <div>
              이름: 홍석준
            </div>
            <div>
              Frontend
            </div>
          </div>
        </div>
      </div>
      <div class="what">
        <div class="whattitle">1. DTOD는 무엇인가요?</div>
        <div class="whatcontent">
          DTOD는 온라인 화상 서비스를 통해
          <br>
          개발자들이 학습 열정을 공유하고
          <br>
          프로젝트 및 스터디 멤버를 모집하여
          <br>
          개발자라는 같은 꿈을 이루기 위해 공부하는 공간입니다.
          <br>
          또한 학습 상태 분석을 통해
          <br>
          체계적인 자기 관리가 가능한 서비스를 제공합니다.
          <br>
          이제, 집에서도 혼자 개발하지 마세요!
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AboutUs',
}
</script>

<style scoped>

.aboutus {
  background-color: #EBEDF0;
  width: 100vw;
  min-height: calc(100vh - 7.498vh);
}

.aboutus .container{
  width: 75vw;
  background-color: white;
  border-radius: 2rem;
  box-shadow: 5px 5px 5px rgb(122, 122, 122);
  margin: 0 auto;
  padding: 1vh 2vw;
  text-align: center;
}

.aboutus .container .title {
  font-family: 'Dohyeon';
  font-size: 1.78vw;
  margin: 5vh 0 5vh 0;
  color: #24274A;
}

.aboutus .container .introduce {
  display: flex;
  justify-content: space-evenly;
  margin: 2vh 0 2vh 0;
}

.aboutus .container .introduce .picture-icon {
  width : 15vh;
  height : 15vh;
  border-radius: 50%;
  overflow:hidden;
}

.aboutus .container .introduce .picture-icon img {
  width:100%;
  height:100%;
  object-fit:cover;
}

.aboutus .container .introduce .name {
  margin: 1vh 0 0 0;
  font-size: 1vw;
  font-weight: bold;
}

.aboutus .container .what .whattitle {
  font-family: 'Dohyeon';
  font-size: 1.5vw;
  margin: 10vh 0 0 0;
  color: #24274A;
}

.aboutus .container .what .whatcontent {
  font-family: 'Hanna';
  font-size: 1vw;
  margin: 3vh 0 0 0;
}

</style>