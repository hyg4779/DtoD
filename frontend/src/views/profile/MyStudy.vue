<template>
  <div>
    여기는 내 학습 공간
  </div>
</template>

<script>
export default {
  created() {
    if (localStorage.getItem('jwt')) {
      // const token = localStorage.getItem('jwt')
      // console.log(token)
    } else {
      alert('로그인을 해주세요')
      this.$router.push({ name: 'Home' })
    }
  }
}
</script>

<style>

</style>