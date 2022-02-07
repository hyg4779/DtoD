import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import VueSweetalert2 from 'vue-sweetalert2';

import firebase from 'firebase/compat/app';
import 'firebase/compat/storage'

import 'sweetalert2/dist/sweetalert2.min.css';
import 'vuetify/dist/vuetify.min.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(VueSweetalert2)

Vue.config.productionTip = false


new Vue({
  router,
  store,
  vuetify,
  render: h => h(App),

  created() {
    const firebaseConfig = {
      apiKey: "AIzaSyCxGdCkiDUfB9hOSGubxoYvUgrxwRa9mDc",
      authDomain: "dtod-image-upload.firebaseapp.com",
      projectId: "dtod-image-upload",
      storageBucket: "dtod-image-upload.appspot.com",
      messagingSenderId: "332159323834",
      appId: "1:332159323834:web:8c67f5ef2599889b873c83"
    };
    firebase.initializeApp(firebaseConfig);
  }

}).$mount('#app')
