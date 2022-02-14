<template>
  <div class="studyboard">
    <div class="sidebar">
      <div @click="clicked">
        <span class="free" :class="{active1: isActive1}">
          자유 게시판
        </span>
      </div>
      <div @click="clicked">
        <span class="question" :class="{active1: isActive2}">
          질문 게시판
        </span>
      </div>
      <div @click="clicked"> 
        <span class="write" :class="{active2: isActive3}">
          글쓰기
        </span>
      </div>
      <div @click="clicked">
        <span class="my" :class="{active3: isActive4}">
          내가 작성한 글
        </span>
      </div>
    </div>
    <div class="mainmenu">
      <div v-if="this.free === true">
        <FreeBoard />
      </div>
      <div v-if="this.question === true">
        <QuestionBoard />
      </div>
      <div v-if="this.write === true">
        <WriteBoard />
      </div>
      <div v-if="this.my === true">
        <MyBoard />
      </div>
    </div>
  </div>
</template>

<script>
import FreeBoard from '../../components/board/freeboard/FreeBoard.vue'
import QuestionBoard from '../../components/board/freeboard/QuestionBoard.vue'
import WriteBoard from '../../components/board/freeboard/WriteBoard.vue'
import MyBoard from '../../components/board/freeboard/MyBoard.vue'

export default {
  name: 'StudyBoard',
  components: {
    FreeBoard,
    QuestionBoard,
    WriteBoard,
    MyBoard,
  },
  data() {
    return {
      free: true,
      question: false,
      write: false,
      my: false,
      isActive1: true,
      isActive2: false,
      isActive3: false,
      isActive4: false,
    }
  },
  methods: {
    clicked(event) {
      const className = event.target.className
      // console.dir(event.target)
      if (className === 'free') {
        this.free = true
        this.question = false
        this.write = false
        this.my = false
        this.isActive1 = true
        this.isActive2 = false
        this.isActive3 = false
        this.isActive4 = false
      }
      else if (className === 'question') {
        this.free = false
        this.question = true
        this.write = false
        this.my = false
        this.isActive1 = false
        this.isActive2 = true
        this.isActive3 = false
        this.isActive4 = false
      }
      else if (className === 'write') {
        this.free = false
        this.question = false
        this.write = true
        this.my = false
        this.isActive1 = false
        this.isActive2 = false
        this.isActive3 = true
        this.isActive4 = false
      }
      else if (className === 'my') {
        this.free = false
        this.question = false
        this.write = false
        this.my = true
        this.isActive1 = false
        this.isActive2 = false
        this.isActive3 = false
        this.isActive4 = true
      }
    }
  },
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

<style scoped>

.studyboard {
  display: flex;
}

.studyboard .sidebar {
  background-color: #24292F;
  width: 13vw;
  min-height: calc(100vh - 7.498vh);
  text-align: center;
}

.studyboard .sidebar div {
  margin: 5vh 0 2vh 0;
  font-size: 0.78vw;
  font-weight: bold;
}

.studyboard .sidebar div span {
  cursor: pointer;
  color: white;
  padding: 1vh 2vw;
}

.studyboard .sidebar div .active1 {
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
}

.studyboard .sidebar div .active2 {
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
}

.studyboard .sidebar div .active3 {
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
}

.studyboard .sidebar div .active4 {
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
}


.studyboard .sidebar div span:hover{
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 2.5rem;
  padding: 10px 20px;
}

.studyboard .mainmenu {
  width: calc(100vw - 13vw);
}

</style>
