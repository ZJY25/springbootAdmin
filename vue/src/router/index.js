import Vue from 'vue'
import store from "@/store";
import VueRouter from 'vue-router'
import HomeView from '../views/Manage.vue'
import Home from "@/views/Home";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component: () => import('../views/Manage'),
    redirect: '/Home',  //默认重定向
    // 路由
    children: [
      {path: 'User', name: '主页', component: () => import('../views/User'),},
      {path: 'Home', name: '主页', component: () => import('../views/Home')}
    ]
  },

  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = new VueRouter({
  routes
})

//vuex所需路由守卫
router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)    //设置当前路由名称, 为了在Header组件中使用
  store.commit("setPath")                             //触发store的数据更新
  next()                                              //放行路由
})

export default router
