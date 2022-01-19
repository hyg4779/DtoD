<template>
  <div class="studyboard">
    <div class="sidebar">
      <div @click="clicked">
        <span class="all">
          전체 글
        </span>
      </div>
      <div @click="clicked"> 
        <span class="write">
          글쓰기
        </span>
      </div>
      <div @click="clicked">
        <span class="my">
          내가 작성한 글
        </span>
      </div>
    </div>
    <div class="mainmenu">
      <div v-if="this.all === true">
        <AllBoard />
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
import AllBoard from '../../components/board/freeboard/AllBoard.vue'
import WriteBoard from '../../components/board/freeboard/WriteBoard.vue'
import MyBoard from '../../components/board/freeboard/MyBoard.vue'

export default {
  name: 'FreeBoard',
  components: {
    AllBoard,
    WriteBoard,
    MyBoard,
  },
  data() {
    return {
      all: true,
      write: false,
      my: false,
    }
  },
  methods: {
    clicked(event) {
      const className = event.target.className
      console.dir(event.target)
      if (className === 'all') {
        this.all = true
        this.write = false
        this.my = false
      }
      else if (className === 'write') {
        this.all = false
        this.write = true
        this.my = false
      }
      else if (className === 'my') {
        this.all = false
        this.write = false
        this.my = true
      }
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
  width: 200px;
  height: calc(100vh - 70px);
  text-align: center;
}

.studyboard .sidebar div {
  margin: 50px 0 20px 0;
  font-size: 15px;
  font-weight: bold;
}

.studyboard .sidebar div span {
  cursor: pointer;
  padding: 10px 20px;
}

.studyboard .sidebar div span:hover{
  cursor: pointer;
  transition: 0.2s;
  color: black;
  border: 1px solid white;
  background-color: white;
  border-radius: 25px;
  padding: 10px 20px;
}

.studyboard .mainmenu {
  background-color: #EBEDF0;
  width: calc(100vw - 200px);
}

</style>