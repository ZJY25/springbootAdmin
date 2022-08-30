import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/globle.css'
import request from "@/utils/request";

Vue.config.productionTip = false

Vue.use(ElementUI, { size: "mini" });

Vue.prototype.request=request  //axios的request组件

new Vue({
  router,
  store,      //引入vuex
  render: h => h(App)
}).$mount('#app')