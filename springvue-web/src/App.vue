<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app',
  created () {
    const _this = this
    // 配置访问后端的端口
    this.axios.defaults.baseURL = 'http://localhost:8080/adminback'
    this.axios.defaults.withCredentials = true
    this.axios.defaults.crossDomain = true
    this.$axios.get('/listAdmin').then(res => {

    })
    this.$axios.get('/listAdmin').then(res => {

    })
    this.$axios.get('/lucky/user/info').then((res) => {

    })
    if (!sessionStorage.getItem('token')) {
      sessionStorage.setItem('token', 'false')
    }
    if (!sessionStorage.getItem('admin')) {
      sessionStorage.setItem('admin', 'false')
    }
    if (!sessionStorage.getItem('user')) {
      sessionStorage.setItem('user', 'false')
    }
    // TODO 配置全局路由守卫
    this.$router.beforeEach((to, from, next) => {
      const nextRouter = ['login', 'register', 'adminLogin', 'home/main'] //  不需要登陆就能访问的页面
      let token = sessionStorage.getItem('token')
      if (nextRouter.indexOf(to.name) >= 0) {
        next()
      } else {
        // 未登录
        if (token === 'false') {
          this.$confirm('用户未登录，是否前往登录?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'error'
          }).then(() => {
            next('/login')
          }).catch(() => {
          })
        } else {
          console.log('to->' + to.name)
          console.log('from->' + from.name)
          console.log('flag->' + to.name.indexOf('admin'))
          if (to.name.indexOf('admin') >= 0 && sessionStorage.getItem('admin') === 'true') {
            next()
          } else if (to.name.indexOf('admin') < 0 && sessionStorage.getItem('user') === 'true') {
            next()
          } else {
            _this.$message.error('权限不足!')
          }
        }
      }
    })
  },
  methods: {
    admin () {
      this.$router.push('/admin')
    },
    get () {
      this.$axios.get('/vue').then(res => {
        console.log(res)
        console.log('success')
        console.log(res.data.msg)
      })
    }
  }
}
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }

  html, body {
    margin: 0;
    padding: 0;
    border: 0;
    background: #f3f4f6;
  }

  a {
    text-decoration: none;
  }

  hr {
    margin: 0;
    padding: 0;
  }
</style>
