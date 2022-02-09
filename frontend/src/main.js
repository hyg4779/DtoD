import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import VueSweetalert2 from "vue-sweetalert2";
import AirbnbStyleDatepicker from 'vue-airbnb-style-datepicker'
import VueAwesomeSwiper from 'vue-awesome-swiper'

import "sweetalert2/dist/sweetalert2.min.css";
import "vuetify/dist/vuetify.min.css";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import 'vue-airbnb-style-datepicker/dist/vue-airbnb-style-datepicker.min.css'
import 'swiper/css/swiper.css'

import firebase from "firebase/compat/app";
import "firebase/compat/storage";

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueSweetalert2);
Vue.use(VueAwesomeSwiper)

Vue.config.productionTip = false;

const datepickerOptions = {}
Vue.use(AirbnbStyleDatepicker, datepickerOptions)

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),

  created() {
    const firebaseConfig = {
      apiKey: "AIzaSyCxGdCkiDUfB9hOSGubxoYvUgrxwRa9mDc",
      authDomain: "dtod-image-upload.firebaseapp.com",
      projectId: "dtod-image-upload",
      storageBucket: "dtod-image-upload.appspot.com",
      messagingSenderId: "332159323834",
      appId: "1:332159323834:web:8c67f5ef2599889b873c83",
    };
    firebase.initializeApp(firebaseConfig);
  },
}).$mount("#app");

// test
