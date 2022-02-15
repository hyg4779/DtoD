<template>
  <div class="itemdetail">

    <header>
      <div class="profileicon">
        <img v-if="itemuserImg" :src="itemuserImg"> 
        <img v-else src="../../../assets/default_user.png">
      </div>
      <h6 style='font-weight: bold'>{{itemuserName}}</h6>
    </header>

    <body>
      <div>
        <div id="title">
          <h5>글 제목</h5>
          <p>{{title}}</p>
        </div>
        <div class="btnGroup" v-if="userName === itemuserName">
          <button class="myBtn" id="up" @click="updateArticle">수정</button>
          <button class="myBtn" id="de" @click="deleteArticle">삭제</button>
        </div>
      </div>
      <div id="title">
        <h5>내용</h5>
        <!-- <p>{{content}}</p> -->
        <p v-html="getContent(this.content)"></p>
      </div>
    </body>

    <footer>
      <FreeComment 
        v-for="(comment, idx) in this.comments"
        :key="idx"
        :comment="comment"
        :item_pk="item_pk"
        @onParentDeleteComment="onParentDeleteComment"
      />
      <hr v-if="this.comments.length !== 0" style="margin: 8px;">
      <form>
        <div class="form-group" style="margin-bottom:10px;">
          <textarea 
            class="form-control"
            placeholder="댓글을 남겨주세요" 
            id="comment" 
            rows="2" 
            v-model="mycomment" 
            @keypress.enter="commentSubmit"
            >
          </textarea>
          <button
            class="myBtn submit"
            id="sub"
            @click="commentSubmit"
          >등록</button>
        </div>
      </form>
    </footer>
    <!-- <div class="commentprofilebox">
      <div class="commentprofileicon">
        <img v-if="userImg" :src="userImg"> 
        <img v-else src="../../../assets/default_user.png">
      </div>
      <div class="commentprofilename">{{userName}}</div>
    </div> -->
  </div>
</template>

<script>
import { api } from '../../../../api.js'
import axios from 'axios'
import FreeComment from "./FreeComment.vue"

export default {
  name: 'ItemDetail',
  components: {
    FreeComment,
  },
  props: {
    item_pk: Number,
  },
  data() {
    return {
      title: '',
      tech:'',
      content: '',
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
        }).then(()=>{
          // console.log(res.data)
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
      this.tech = res.data.cboardTechstack
      this.content = res.data.cboardContent
      this.imgPath = res.data.cboardImg
      
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
.itemdetail{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding:10px !important;
  width: auto; 
  height:auto; 
}

header{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  /* margin: 1em; */
}

header h2{
  font-weight: bold;
}

body{
  background-color: #FFFFFF !important;
}

body > div{
  display:flex;
  flex-direction: row;
  justify-content: space-between;
}

#title{
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

#title h5{
  font-weight: bold;
}

.profileicon {
  margin: 2vh 1vw 2vh 1vw;
  width : 6.5vh;
  height : 6.5vh;
  border-radius: 50%;
  overflow:hidden;
}

.profileicon img{
  width:100%;
  height:100%;
  object-fit:cover;
}

/* .profilbox h5 {
  margin: 3.5vh 0 0 0;
  font-size: 1.2vw;
  font-weight: bold;
  font-family: 'Epilogue', sans-serif;
} */

/* .tech-control {
  display: flex;
  justify-content: space-between;
  align-content: center;
} */


.content .contentdetail {
  font-weight:300;
  margin: 0 0 2vh 0;
  font-family: 'Epilogue', sans-serif;
  font-size: 0.9vw;
}

.form-group .form-control{
  /* background-color: black; 
  color:white; */
  border-radius: 1rem;
  height: 10vh;
  font-family: 'Epilogue', sans-serif;
}

.commentprofilebox{
  display: flex;
}
.commentprofileicon {
  margin: 0 1vw 2vh 0;
  width : 5vh;
  height : 5vh;
  /* border: 1px solid; */
  border-radius: 50%;
  overflow:hidden;
}
.commentprofileicon img{
  width:100%;
  height:100%;
  object-fit:cover;
}
.commentprofilename {
  margin: 1vh 0 0 0;
  font-size: 1.1vw;
  font-weight: bold;
  font-family: 'Epilogue', sans-serif;
}

.img-btn {
  display: flex;
  justify-content: space-between;
}

.img-box {
  display: flex;
  margin:  0.6vh 0 2vh 0;
}
.img-box .img-box div{
  margin: 9vh 0 0 0;
  width: 15vw;
  height: 17vh;
}

.btnGroup{
  display: flex;
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
}
</style>
