<template>
  <div class="page404">
    <div>
      <img src="../assets/err-img.png" alt="page404">
    </div>
    <router-link :to="{ name: 'Home' }">
      <button>홈으로</button>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'Page404'
}
</script>

<style scoped>

.page404 {
  text-align : center;
}

.page404 div {
  margin: 13vh 0 0 0;
}

.page404 div img {
  width: 30vw;
  height: 60vh;
}

.page404 button {
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 1.5vw;
  font-weight: bold;
  color: white;
  height: 6vh;
  width: 12vw;
  border: none;
  background-color: #24274A;
  border-radius: 15rem;
}
</style>