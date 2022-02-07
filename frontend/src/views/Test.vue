<template>
  <div>
    여기는 동철의 테스트를 위한 공간
    <input type="file" accept="image/*" @change="previewImage" /> 

    <img v-if="picture" :src="picture" alt="preview" style="max-widht: 150px; max-height: 150px;"> 
    <p>Progress: {{ uploadValue.toFixed() + "%" }}</p>

    <button  @click="onUpload">전송</button> 
  </div>
</template>

<script>
import firebase from 'firebase/compat/app';
import 'firebase/compat/storage'

export default {
  data () {
    return {
      uploadValue : 0,
      picture: null,
      imageData: null
    }
  },
  methods: {
    previewImage(event) { 
      this.uploadValue=0; 
      this.picture=null; 
      this.imageData = event.target.files[0]; 
    }, 
    onUpload( ){ 
      this.picture=null; 
      const storageRef = firebase.storage().ref(`${this. imageData.name}`).put(this.imageData); 
      storageRef.on( `state_changed` ,snapshot=>{ 
        this.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes)*100; 
      }, error=>{console.log(error.message)}, 
      ()=>{this.uploadValue=100; 
      storageRef.snapshot.ref.getDownloadURL().then((url)=>{ 
        this.picture =url; 
        console.log(url)
        }); 
      }
      )
    }
  }
}
</script>