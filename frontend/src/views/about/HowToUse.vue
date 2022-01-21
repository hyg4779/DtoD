<template>
  <div class="howtouse">
    <br>
    <div class="container">
      <span>
        이용 방법 안내
      </span>
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
  /* background-color: #F5F5F5; */
  width: 100vw;
  height: calc(100vh - 70px);
}

.howtouse .container{
  width: 75vw;
  background-color: white;
  border-radius: 25px;
  box-shadow: 5px 5px 5px rgb(122, 122, 122);
  margin: auto;
  padding: 10px 20px;
  text-align: center;
}
</style>