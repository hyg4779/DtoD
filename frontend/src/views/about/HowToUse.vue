<template>
  <div class="howtouse">
    <br>
    <div class="container">
      <div class="title">
        이용 방법
        <br>
        (How To Use)
      </div>
      <!-- <div class="image">
        <img src="../../assets/about/howtouse/001.png" alt="001">
        <img src="../../assets/about/howtouse/002.png" alt="002">
        <img src="../../assets/about/howtouse/003.png" alt="003">
        <img src="../../assets/about/howtouse/002.png" alt="002">
        <img src="../../assets/about/howtouse/004.png" alt="004">
      </div> -->
      <div class="flow">
        <div class="flowtitle">1. 스터디 진행의 흐름</div>
        <div class="flowcontainer">
          <div class="flowcontent">
            흐름 내용
          </div>
        </div>
      </div>
      <div class="board">
        <div class="boardtitle">2. 게시판 이용방법</div>
        <div class="boardcontainer">
          <div class="boardcontent">
            게시판
          </div>
        </div>
      </div>
      <div class="study">
        <div class="studytitle">3. 스터디룸 이용방법</div>
        <div class="studycontainer">
          <div class="studycontent">
            스터디룸
          </div>
        </div>
      </div>
    </div>
    <br>
  </div>
</template>

<script>
export default {
  name: 'HowToUse',
}
</script>

<style scoped>

.howtouse {
  background-color: #EBEDF0;
  width: 100vw;
  min-height: calc(100vh - 7.498vh);
}

.howtouse .container{
  width: 75vw;
  background-color: white;
  border-radius: 2rem;
  box-shadow: 5px 5px 5px rgb(122, 122, 122);
  margin: 0 auto;
  padding: 1vh 2vw;
  text-align: center;
}

.howtouse .container .title {
  font-family: 'Dohyeon';
  font-size: 1.78vw;
  margin: 5vh 0 5vh 0;
  color: #24274A;
}

/* .howtouse .container .image {
  display: flex;
}

.howtouse .container .image img {
  height: 50%;
  width: 20%;
} */

.howtouse .flow .flowtitle {
  font-family: 'Dohyeon';
  font-size: 1.5vw;
  margin: 10vh 0 0 0;
  color: #24274A;
}

.howtouse .flow .flowcontainer {
  display: flex;
  justify-content: center;
  margin: 3vh 0 0 0;
}

.howtouse .flow .flowcontainer img {
  margin: auto 0;
  height: 14vh;
  margin: 0 1.5vw 0 0;
}

.howtouse .flow .flowcontainer .flowcontent {
  margin: auto 0;
  font-family: 'Hanna';
  font-size: 1vw;
}

.howtouse .board .boardtitle {
  font-family: 'Dohyeon';
  font-size: 1.5vw;
  margin: 10vh 0 0 0;
  color: #24274A;
}

.howtouse .board .boardcontainer {
  display: flex;
  justify-content: center;
  margin: 3vh 0 0 0;
}

.howtouse .board .boardcontainer img {
  margin: auto 0;
  height: 14vh;
  margin: 0 1.5vw 0 0;
}

.howtouse .board .boardcontainer .boardcontent {
  margin: auto 0;
  font-family: 'Hanna';
  font-size: 1vw;
}

.howtouse .study .studytitle {
  font-family: 'Dohyeon';
  font-size: 1.5vw;
  margin: 10vh 0 0 0;
  color: #24274A;
}

.howtouse .study .studycontainer {
  display: flex;
  justify-content: center;
  margin: 3vh 0 0 0;
}

.howtouse .study .studycontainer img {
  margin: auto 0;
  height: 14vh;
  margin: 0 1.5vw 0 0;
}

.howtouse .study .studycontainer .studycontent {
  margin: 0 0 5vh 0;
  font-family: 'Hanna';
  font-size: 1vw;
}
</style>