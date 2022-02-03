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
  margin: 3vh 0 3vh 0;
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
</style>