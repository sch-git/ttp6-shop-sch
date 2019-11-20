<template>
  <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
           label-width="0px" ref="loginForm">
    <h3 class="login_title">登录</h3>
    <el-form-item prop="phone">
      <el-input auto-complete="off" placeholder="用户名"
                type="text" v-model="loginForm.phone"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input auto-complete="off" placeholder="密码"
                type="password" v-model="loginForm.password"></el-input>
    </el-form-item>
    <el-checkbox class="login_remember"
                 label-position="left">记住密码
    </el-checkbox>
    <el-form-item style="width: 100%">
      <el-button @click="submitClick('loginForm')" style="width: 100%" type="primary">登录</el-button>
    </el-form-item>
    <router-link to="/register"><span style="font-size: 12px">前往注册==></span></router-link>
  </el-form>
</template>

<script>
export default {
  data () {
    return {
      rules: {
        phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      },
      loginForm: {
        phone: '',
        password: ''
      },
      loading: false
    }
  },
  methods: {
    submitClick (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          const userAO = {
            phone: _this.loginForm.phone,
            password: _this.loginForm.password
          }
          this.$axios.post('/lucky/login', userAO).then((response) => {
            // 登录成功跳转到home页
            if (response.data.code === 200) {
              _this.$message.success('登陆成功!')
              // 用户登录成功设置token
              sessionStorage.setItem('token', 'true')
              sessionStorage.setItem('user', 'true')
              _this.$router.push('/')
            } else {
              _this.$message.error(response.data.msg)
            }
          }).catch((err) => {
            _this.$message.error('登录错误')
            console.log(err)
          })
        } else {
          this.$message.error('请填写完整信息!')
        }
      })
    }
  }
}
</script>

<style scoped>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 80px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0 auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login_remember {
    margin: 0 0 35px 0;
    text-align: left;
  }
</style>
