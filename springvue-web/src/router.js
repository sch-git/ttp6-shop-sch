import Vue from 'vue'
import Router from 'vue-router'
import Admin from './views/Admin'
import Login from './views/Login'
import Register from './views/Register'
import AdminLogin from './views/AdminLogin'

Vue.use(Router)

export default new Router({
  routes: [
    /*        {
      path: '/about',
      name: 'about',
      component: () => import(/!* webpackChunkName: "about" *!/ './views/About.vue')
    } */
    {
      path: '/admin',
      name: 'admin',
      component: Admin,
      children: [
        {
          path: 'member',
          name: 'admin/member',
          component: () => import(/* webpackChunkName: "member" */ './components/admin/Member.vue')
        },
        {
          path: 'userList',
          name: 'admin/userList',
          component: () => import(/* webpackChunkName: "userList" */ './components/admin/UserList.vue')
        },
        {
          path: 'goodList',
          name: 'admin/goodList',
          component: () => import(/* webpackChunkName: "goodList" */ './components/admin/GoodList.vue')
        },
        {
          path: 'goodCategory',
          name: 'admin/goodCategory',
          component: () => import(/* webpackChunkName: "goodCategory" */ './components/admin/GoodCategory.vue')
        },
        {
          path: 'goodProperty',
          name: 'admin/goodProperty',
          component: () => import(/* webpackChunkName: "goodProperty" */ './components/admin/GoodProperty.vue')
        },
        {
          path: 'goodPropertyValue/:id',
          name: 'admin/goodPropertyValue',
          component: () => import(/* webpackChunkName: "goodPropertyValue" */ './components/admin/GoodPropertyValue.vue')
        },
        {
          path: 'orderList',
          name: 'admin/orderList',
          component: () => import(/* webpackChunkName: "orderList" */ './components/admin/OrderList.vue')
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/adminLogin',
      name: 'adminLogin',
      component: AdminLogin
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/',
      name: 'home',
      component: () => import(/* webpackChunkName: "home" */ './views/Home.vue'),
      children: [
        {
          path: 'home/header',
          name: 'home/header',
          component: () => import(/* webpackChunkName: "home/header" */ './components/common/Header.vue')
        },
        {
          path: 'home/main',
          name: 'home/main',
          component: () => import(/* webpackChunkName: "home/main" */ './components/common/Main.vue')
        },
        {
          path: 'home/car',
          name: 'home/car',
          component: () => import(/* webpackChunkName: "home/car" */ './components/common/Car.vue')
        },
        {
          path: 'home/payMent',
          name: 'home/payMent',
          component: () => import(/* webpackChunkName: "home/payMent" */ './components/common/PayMent.vue')
        },
        {
          path: 'home/order',
          name: 'home/order',
          component: () => import(/* webpackChunkName: "home/order" */ './components/common/Order.vue')
        },
        {
          path: 'home/userInfo',
          name: 'home/userInfo',
          component: () => import(/* webpackChunkName: "home/userInfo" */ './components/common/UserInfo.vue')
        },
        {
          path: 'home/goodInfo/:id',
          name: 'home/goodInfo',
          component: () => import(/* webpackChunkName: "home/goodInfo" */ './components/common/GoodInfo.vue')
        }
      ],
      redirect: 'home/main'
    }
  ]
})
