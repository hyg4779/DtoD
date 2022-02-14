<template>
  <div class="editmystudy">
    <div class="sidebar">
      <div @click="clicked">
        <span class="editnfo" :class="{active1: isActive1}">
          스터디 정보 수정
        </span>
      </div>
      <!-- <div @click="clicked"> 
        <span class="editmember" :class="{active2: isActive2}">
          스터디 멤버 관리
        </span>
      </div> -->
    </div>
    <div class="mainmenu">
      <div v-if="this.editinfo === true">
        <EditInfo 
        :item_pk="this.itempk"
        />
      </div>
      <!-- <div v-if="this.editmember === true">
        <EditMember />
      </div> -->
    </div>
  </div>
</template>

<script>
import EditInfo from '../../components/profile/editstudy/EditInfo.vue'
// import EditMember from '../../components/profile/editstudy/EditMember.vue'

export default {
  name: 'MyProfile',
  components: {
    EditInfo,
    // EditMember,
  },
  data() {
    return {
      editinfo: true,
      // editmember: false,

      isActive1: true,
      // isActive2: false,

      itempk: null,
    }
  },
  methods: {
    clicked(event) {
      const className = event.target.className
      // console.dir(event.target)
      if (className === 'editinfo') {
        this.editinfo = true
        // this.editmember = false

        this.isActive1 = true
        // this.isActive2 = false
      }
      // else if (className === 'editmember') {
      //   this.editinfo = false
      //   this.editmember = true

      //   this.isActive1 = false
      //   this.isActive2 = true
      // }
    }
  },
  created() {
    if (localStorage.getItem('jwt')) {
      // const token = localStorage.getItem('jwt')
      // console.log(token)
      // console.log(this.$route.params.sessionId)
      this.itempk = this.$route.params.sessionId

    } else {
      alert('로그인을 해주세요')
      this.$router.push({ name: 'Home' })
    }
  }
}
</script>

<style scoped>

.editmystudy {
  display: flex;
}

.editmystudy .sidebar {
  background-color: #24292F;
  text-align: center;
  width: 13vw;
  min-height: calc(100vh - 7.498vh);
}

.editmystudy .sidebar div {
  margin: 5vh 0 2vh 0;
  font-size: 0.78vw;
  font-weight: bold;
}

.editmystudy .sidebar div span {
  cursor: pointer;
  color: white;
  padding: 1vh 2vw;
}

.editmystudy .sidebar div .active1 {
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
}

.editmystudy .sidebar div .active2 {
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
}

/* .myprofile .sidebar div .active3 {
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
} */

.editmystudy .sidebar div span:hover{
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
}

.editmystudy .mainmenu {
  width: calc(100vw - 13vw);
}

</style>
