import Vue from 'vue'
import VueRouter from 'vue-router'
import Bar from '../components/home/Bar'
import PostList from '../components/home/PostList'
import PostDetail from '../components/home/PostDetail'
import Home from '../components/home/Home'
import Confession from '../components/home/Confession'
import Help from '../components/home/Help'
import Search from '../components/home/Search'


Vue.use(VueRouter)

const routes = [
  // reditect是重定向的意思 说明如果访问了'/'的地址 就重定向到'home'地址
  {
    path: '/',
    redirect: '/Bar'
  },
  {
    //导航栏
    path: '/Bar',
    component: Bar,
    redirect: '/Home',
    children: [
      {
        path: '/Home',
        component: Home
      },
      {
        path: '/PostList',
        component: PostList
      },
      {
        path: '/Confession',
        component: Confession
      },
      {
        path: '/Help',
        component: Help
      },
      {
        path: '/PostDetail/:id',
        name: 'PostDetail',
        component: PostDetail,
        props: true
      },
      {
        path: '/Search/:keyword',
        name: 'Search',
        component: Search,
        props: true
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router

