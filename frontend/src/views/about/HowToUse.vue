<template>
  <div class="howtouse">
    <br>
    <div class="container">
      <div class="title">
        이용 방법
        <br>
        (How To Use)
      </div>
    </div>
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
  margin: 3vh 0 3vh 0;
  color: #24274A;
}

</style>