<template>
  <div class="mystudy">
    <br>
    <div class="madebyme">
      <div class="title">
        내가 만든 스터디
      </div>
      <div class="allitems">
        <MyItems
        :items="this.items"
        />
      </div>
    </div>
    <br>
    <br>
    <div class="joinedstudy">
      <div class="title">
        내가 참여하는 스터디
      </div>
      <div class="allitems">
        <Items
        :items="this.items"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { dummy } from "../../../generated.js";
import MyItems from '../../components/profile/madebyme/MyItems.vue'
import Items from '../../components/profile/joined/Items.vue'

export default {
  name: 'MyStudy',
  components: {
    MyItems,
    Items
  },
  data () {
    return {
      items: [],
    }
  },
  created() {
    if (localStorage.getItem('jwt')) {
      this.items = dummy
      // const token = localStorage.getItem('jwt')
      // console.log(token)
    } else {
      alert('로그인을 해주세요')
      this.$router.push({ name: 'Home' })
    }
  }
}
</script>

<style scoped>
.mystudy {
  display: flex;
  flex-direction: column;
}

.mystudy .title {
  font-size: 1.58vw;
  margin: 1vh 0 0 4vw;
  font-weight: bold;
  color: #0D1350;
}
</style>