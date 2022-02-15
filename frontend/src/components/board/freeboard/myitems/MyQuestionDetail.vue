<template>
    <div class="questionitemdetail">
    <header>
      <h3>{{this.title}}</h3>
      <div class="profileicon">
        <img v-if="itemuserImg" :src="itemuserImg"> 
        <img v-else src="../../../../assets/default_user.png">
      </div>
      {{this.itemuserName}}
    </header>

    <body>
      <nav>
        <div id="stacks">
          <h6 class="p-0 m-0">기술 스택</h6>
          <span
            v-for="(item, idx) in getSkills"
            :key="idx"
          >
            {{item}}
          </span>
        </div>
        <div class="btnGroup" v-if="userName === itemuserName">
          <button class="myBtn" id="up" @click="updateArticle">수정</button>
          <button class="myBtn" id="de" @click="deleteArticle">삭제</button>
        </div>
      </nav>

      <section>
        <div class="img-box" :style="style">
          <img 
          v-for="(stack, idx) in imgs"
          :key="idx"
          id="stackImg"
          :src="require(`@/assets/stacks/${stack}.png`)"
          alt="img"
          >
        </div>
        <ul>
          <li>
            <p>질문내용</p>
            <p v-html="getContent(this.content)"></p>
          </li>
        </ul>
      </section>

      <article>
          <p>코드</p>
          <Tiptap 
            :item_pk="item_pk"
          />
            <!-- :techstack="this.techstack" -->
      </article>
    </body>

    <footer>
      <MyQuestionComment 
        v-for="(comment, idx) in this.comments"
        :key="idx"
        :comment="comment"
        :item_pk="item_pk"
        @onParentDeleteComment="onParentDeleteComment"
      />
      <hr v-if="this.comments.length !== 0">
      <!-- <div class="commentprofilebox">
        <div class="commentprofileicon">
          <img v-if="userImg" :src="userImg"> 
          <img v-else src="../../../assets/default_user.png">
        </div>
        <div class="commentprofilename">{{this.userName}}</div>
      </div> -->
      <form
        @submit="commentSubmit"
        class="form-group"
      >
        <textarea 
          class="form-control"
          placeholder="댓글을 남겨주세요" 
          id="comment" 
          rows="2" 
          v-model="mycomment" 
          @keypress.enter="commentSubmit"
          >
        </textarea>
        <button class="myBtn" id="sub">등록</button>
      </form>
    </footer>
  </div>
</template>

<script>
import { api } from '../../../../../api.js'
import axios from 'axios'
import Tiptap from "../../vieweditor/Tiptap.vue"
import MyQuestionComment from "./MyQuestionComment.vue"

export default {
  name: 'QuestionItemDetail',
  components: {
    Tiptap,
    MyQuestionComment
  },
  props: {
    item_pk: Number,
  },
  data() {
    return {
      title: '',
      tech:'',
      content: '',
      code: '',
      imgPath: '',
      imgs: null,
      style: {
        backgroundColor: this.imgPath
      },

      itemuserName: '',
      itemuserImg: '',
      itemuserEmail: '',
      userImg: '',
      userName: '',

      mycomment: '',
      comments: [],
    }
  },
  computed: {
    getSkills: function() {
      const t = this.tech
      const temp = t.split(',')
      let res = []
      for(let i = 0; i < temp.length; i++){
        res.push(temp[i])
      }
      return res
    },
  },
  methods:{
    getContent(content) { 
      return content.split('\n').join('<br>'); 
    },
    commentSubmit(event) {
      event.preventDefault()
      if (this.mycomment.length !== 0) {
        const item_pk = this.item_pk
        const token = localStorage.getItem('jwt')
        axios({
          url: api.CREATE_FREE_BOARD_COMMENT + `${item_pk}`,
          method: 'POST',
          data: {
            ccommentContent: this.mycomment
          },
          headers: {
            Authorization: 'Bearer ' + token
          },
        }).then((res)=>{
          console.log(res.data)
          axios({
            url: api. GET_FREE_BOARD_COMMENT + `${item_pk}`,
            method: 'GET',
            headers: {
              Authorization: 'Bearer ' + token
            },
          }).then((res)=>{
              const temp = []
              res.data.forEach((element)=>{
                temp.push(element)
              })
              this.comments = temp
          }).catch((err)=>{
            console.error(err)
          })
        }).catch((err)=>{
          console.error(err)
        })
        this.mycomment = ''
      } 
      else {
        alert("댓글을 입력하세요.")
      }
    },
    onParentDeleteComment() {
      const item_pk = this.item_pk
      const token = localStorage.getItem('jwt')
      axios({
        url: api.GET_FREE_BOARD_COMMENT + `${item_pk}`,
        method: 'GET',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then((res)=>{
          const temp = []
          res.data.forEach((element)=>{
            temp.push(element)
          })
          this.comments = temp
      }).catch((err)=>{
        const temp = []
        this.comments = temp
        console.error(err)
      })
    },
    deleteArticle() {
      const token = localStorage.getItem('jwt')
      axios({
        url: api.DELETE_FREE_BOARD + `${this.item_pk}`,
        method: 'DELETE',
        headers: {
          Authorization: 'Bearer ' + token
        },
      }).then(()=>{
        // console.log(res)
        this.$router.go();
      }).catch((err)=>{
        console.error(err)
      })
    },
    updateArticle() {
      this.$emit('update-modal-open', this.item_pk)
    },
  },
  created() {
    const item_pk = this.item_pk
    const token = localStorage.getItem('jwt')

    axios({
      url:  api.GET_FREE_BOARD_DETAIL + `${this.item_pk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.itemuserEmail = res.data.user.userEmail
      this.itemuserImg = res.data.user.userImg
      this.itemuserName = res.data.user.userName
      this.title = res.data.cboardTitle
      this.code = res.data.cboardCode
      this.tech = res.data.cboardTechstack
      this.content = res.data.cboardContent
      this.imgPath = res.data.cboardImg

      let stacks = res.data.cboardTechstack
      // 배열로 저장
      let result = stacks.split(',')
      // console.log(result.length)

      // 기술이 4개 이상이면 3개만 담고 그 이하는 다 담기
      if(result.length >= 4){
        // console.log(result.slice(0,3))
        this.imgs = result.slice(0,3)
      }else{
        this.imgs = result
      }
      
      this.style.backgroundColor = res.data.cboardImg
    }).catch((err)=>{
      console.error(err)
    })

    axios({
      url:  api.USER_INFO_GET,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      this.userName = res.data.userName
      this.userImg = res.data.userImg
    }).catch((err)=>{
      console.error(err)
    })

    axios({
      url: api.GET_FREE_BOARD_COMMENT + `${item_pk}`,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res)
      const temp = []
      res.data.forEach((element)=>{
        temp.push(element)
      })
      this.comments = temp
      // console.log(this.comments)
    }).catch((err)=>{
      console.error(err)
    })
  }
}
</script>

<style scoped>
hr{
  margin: 8px;
}
.questionitemdetail{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: auto;
  height:auto;
  padding:10px !important;
}

header{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0 0 2.5vh 0;
}

header h3{
  font-weight: bold;
}

.profileicon {
  margin: 2vh 1vw 2vh 1vw;
  width : 6.5vh;
  height : 6.5vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}

.profileicon img{
  width:100%;
  height:100%;
  object-fit:cover;
}

body{
  background-color: #FFFFFF !important;
}

nav{
  display: flex;
  justify-content: space-between;
}

#stacks{
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  font-weight: 400;
  font-size: 1vw;
}

#stacks span{
  border: 1px solid #F0F0F0;
  border-radius: 8rem;
  padding: 0 0.5vw 0.5vh 0.5vw;
  margin: 0 0 0 1vw;
  background-color: #F0F0F0;
}

section{
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin:  0.6vh 0 2vh 0;
}

.img-box{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top:1rem;
  padding: 1rem;
  width: 20rem;
  height: 15rem;
  border-radius: 2rem;
  box-shadow: 0.1rem 0.1rem 0.1rem rgba(0, 0, 0, 0.25);
}

.img-box img{
  margin: .5rem;
  width: 5rem;
  height: 5rem;
}

ul{
  list-style-type: none;
  margin: 1rem 0 0 0;
}

ul p:nth-child(1){
  font-weight: bold;
  font-size: 1.1vw;
}

article{
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
  padding: 1rem;
}


footer form {
  position: relative;
  margin-bottom:10px;
}

.form-group .form-control{
  border-radius: 1rem;
  height: 10vh;
}

.myBtn {
  border-radius: 1rem;
  color: #FFFFFF;
  cursor: pointer;
  font-size: 0.8vw;
  font-weight: bold;
  height: 3.5vh;
  margin: 0 0.3vw 0 0.3vw;
  padding: 0 0.5vw 0.1vh 0.5vw;
  transition: all 300ms cubic-bezier(.23, 1, 0.32, 1);
  touch-action: manipulation;
  will-change: transform;
}

.myBtn:hover {
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

.myBtn:active {
  box-shadow: none;
  transform: translateY(0);
}

#up{
  background-color: rgb(30, 200, 30);
}

#de{
  background-color: rgb(250, 100, 100);
}

form {
  position: relative;
}
#sub {
  background-color: rgb(50,100,250);
  position: absolute;
  right: 0.5vw;
  bottom: 1vh;
  padding: 0.4vh 0.6vw 0.5vh 0.6vw;
}
</style>