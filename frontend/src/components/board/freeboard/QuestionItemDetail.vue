<template>
  <div class="questionitemdetail">
    <div class="cardmodal">
      <div class="detailtitle">
        {{this.title}}
      </div>
      <div class="profilebox">
        <div class="profileicon">
          <img v-if="userImg" :src="userImg"> 
          <img v-else src="../../../assets/default_user.png">
        </div>
        <div class="profilename">작성자: {{this.itemuserName}}</div>
      </div>
      <div class="tech-control">
        <div class="techstack">
          기술 스택
          <span v-for="(item, idx) in getSkills" :key="idx">
            {{item}}
          </span>
        </div>
        <div class="item-control">
          <button class="update" @click="updateArticle">수정</button>
          <button class="delete" @click="deleteArticle">삭제</button>
        </div>
      </div>
      <div class="img-etc">
        <div class="img-box" :style="style">
          <img 
          v-for="(stack, idx) in imgs"
          :key="idx"
          id="stackImg"
          :src="require(`@/assets/stacks/${stack}.png`)"
          alt="img"
          >
        </div>
      </div>
      <div class="content">
        <div class="contenttitle">
          내용
        </div>
        <div class="contentdetail">
          <p v-html="getContent(this.content)"></p>
        </div>
      </div>
      <div class="code">
        <div class="codetitle">
          코드
        </div>
        <div class="code">
          <Tiptap 
            :item_pk="item_pk"
          />
            <!-- :techstack="this.techstack" -->
        </div>
      </div>
      <div>
      <QuestionComment 
        v-for="(comment, idx) in this.comments"
        :key="idx"
        :comment="comment"
        :item_pk="item_pk"
        @onParentDeleteComment="onParentDeleteComment"
      />
      </div>
      <hr>
      <div class="commentprofilebox">
        <div class="commentprofileicon">
          <img v-if="userImg" :src="userImg"> 
          <img v-else src="../../../assets/default_user.png">
        </div>
        <div class="commentprofilename">{{this.userName}}</div>
      </div>
      <form @submit="commentSubmit">
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
          <button class="submit">등록</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { api } from '../../../../api.js'
import axios from 'axios'
import Tiptap from "../vieweditor/Tiptap.vue"
import QuestionComment from "./QuestionComment.vue"

export default {
  name: 'QuestionItemDetail',
  components: {
    Tiptap,
    QuestionComment
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
      console.log(res)
      const temp = []
      res.data.forEach((element)=>{
        temp.push(element)
      })
      this.comments = temp
      console.log(this.comments)
    }).catch((err)=>{
      console.error(err)
    })
  }
}
</script>

<style scoped>
.questionitemdetail{
  width: auto; 
  height: auto; 
  padding:10px !important;
  font-size: 20px;
}
.detailtitle {
  text-align: center;
  font-size: 1.4vw;
  font-weight: bold;
}
/* .profilebox{
  display: flex;
} */
.profileicon {
  margin: 2.5vh 1vw 2vh 17vw;
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
.profilename {
  margin: 2vh 0 0 15vw;
  font-size: 1vw;
  font-weight: bold;
  font-family: 'Epilogue', sans-serif;
}
.tech-control {
  display: flex;
  justify-content: space-between;
}

.techstack {
  font-weight: 400;
  font-size: 1vw;
  margin:  0.6vh 0 3vh 0;
  font-family: 'Epilogue', sans-serif;
}
.techstack span {
  border: 1px solid #F0F0F0;
  border-radius: 8rem;
  padding: 0.5vh 0.5vw 0.6vh 0.8vw;
  margin: 0 0 0 1vw;
  background-color: #F0F0F0;
  font-weight: bold;
  font-family: 'Epilogue', sans-serif;
}
.item-control .update{
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  color: #24274A;
  height: 3vh;
  width: 3vw;
  margin: 0 1vw 0 0;
  border: 1px solid;
  background-color: white;
  border-radius: 1.1rem;
}
.item-control .delete{
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.7vw;
  font-weight: bold;
  color: white;
  height: 3vh;
  width: 3vw;
  /* margin: 0 1vw 0 0; */
  background-color: #24274A;
  border-radius: 1.1rem;
}

.content {
  margin: 0 0 2vh 0;
}
.content .contenttitle{
  font-weight: bold;
  font-size: 1.1vw;
  margin: 3vh 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}
.content .contentdetail {
  font-weight:300;
  font-family: 'Epilogue', sans-serif;
  margin: 0 0 2vh 0;
  font-size: 0.9vw
}
.code .codetitle{
  font-weight: bold;
  font-size: 1.1vw;
  margin: 0 0 1vh 0;
  font-family: 'Epilogue', sans-serif;
}

.form-group .form-control{
  /* background-color: black; 
  color:white; */
  border-radius: 1rem;
  height: 10vh;
}

.submit {
  cursor: pointer;
  font-family: 'Roboto';
  font-size: 0.85vw;
  font-weight: bold;
  color: white;
  height: 4vh;
  width: 4vw;
  margin: 1vh 0 0 0;
  background-color: #24274A;
  border-radius: 1.1rem;
}

.commentprofilebox{
  display: flex;
}
.commentprofileicon {
  margin: 0 1vw 1vh 0;
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

.img-etc {
  display: flex;
  margin:  0.6vh 0 2vh 0;
}
.img-etc .img-box {
  width: 31vh;
  height: 30vh;
}
.img-etc .img-box #stackImg {
  margin: 9vh 0 0 0;
  width: 10vh;
}
</style>