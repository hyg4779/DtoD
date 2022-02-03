<template>
  <div class="aboutus">
    <br>
    <div class="container">
      <div class="title">
          D TO D
          <br>
          (Develop TOgether to be Developer)
      </div>
      <div class="picture">
        <div>
          <img src="../../assets/people/seongyu.png" alt="seongyu">
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
  border-radius: 25px;
  box-shadow: 5px 5px 5px rgb(122, 122, 122);
  margin: 0 auto;
  padding: 1vh 2vw;
  text-align: center;
}

.aboutus .container .title {
  font-family: 'Dohyeon';
  font-size: 1.78vw;
  margin: 1vh 0 1vh 0;
  color: #24274A;

}

.aboutus .container .picture div {
  width : 10vh;
  height : 10vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}

.aboutus .container .picture div img {
  width:100%;
  height:100%;
  object-fit:cover;
}
</style>