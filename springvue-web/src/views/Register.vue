<template>
  <el-form :model="registerForm" :rules="rules" class="login-container" label-position="left"
           label-width="0px" ref="registerForm">
    <h3 class="login_title">系统注册</h3>
    <el-form-item prop="username">
      <el-input auto-complete="off" placeholder="手机号"
                type="text" v-model="registerForm.phone"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input auto-complete="off" placeholder="密码"
                type="password" v-model="registerForm.password"></el-input>
    </el-form-item>
    <el-form-item prop="passwordChecked">
      <el-input auto-complete="off" placeholder="确认密码"
                type="password" v-model="registerForm.passwordChecked"></el-input>
    </el-form-item>
<!--    <el-form-item prop="code">
      <el-input placeholder="请输入验证码" :model="this.registerForm.code">
        <template slot="append">
          <el-button @click="sendSms('registerForm')" :class="{disabled: !this.registerForm.canClick}">
            {{registerForm.codeMsg}}
          </el-button>
        </template>
      </el-input>
    </el-form-item>-->
    <el-form-item style="width: 100%">
      <el-button @click="submitClick('registerForm')" style="width: 100%" type="primary">注册</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data () {
    // eslint-disable-next-line no-unused-vars
    const checkedPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      registerForm: {
        phone: '',
        password: '',
        passwordChecked: '',
        code: '',
        codeMsg: '获取验证码',
        totalTime: 30,
        canClick: true
      },
      rules: {
        phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        passwordChecked: [{ validator: checkedPassword, trigger: 'blur' }]
      },
      loading: false
    }
  },
  methods: {
    submitClick (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          const registerAO = {
            'phone': _this.registerForm.phone,
            'password': _this.registerForm.password
          }
          this.$axios.post('/lucky/register', registerAO).then((response) => {
            // 注册成功跳转到login页
            if (response.data.code === 200) {
              _this.$message.success(response.data.msg)
              _this.$router.push('/login')
            } else {
              _this.$message.error(response.data.msg)
            }
          }).catch((err) => {
            _this.$message.error('注册失败')
            console.log(err)
          })
        } else {
          this.$message.error('请填写完整信息!')
        }
      })
    }
    /* sendSms () {
      if (this.registerForm.phone !== '') {
        if (!this.registerForm.canClick) {
          return
        }
        const _this = this
        this.registerForm.canClick = false
        this.registerForm.codeMsg = this.registerForm.totalTime + 's后重新发送'
        let clock = window.setInterval(() => {
          this.registerForm.totalTime--
          this.registerForm.codeMsg = this.registerForm.totalTime + 's后重新发送'
          if (this.registerForm.totalTime < 0) {
            window.clearInterval(clock)
            this.registerForm.codeMsg = '重新发送验证码'
            this.registerForm.totalTime = 30
            this.registerForm.canClick = true
          }
        }, 1000)
        let registerAO = {
          'phone': this.registerForm.phone,
          'password': this.registerForm.password
        }
        this.$axios.post('/code', registerAO).then((res) => {
          console.log(res)
          if (res.data.code === 200) {
            _this.$message.success((res.data.msg))
          } else if (res.data.code === 404) {
            _this.$message.error((res.data.msg))
          }
        })
      } else {
        this.$message.error(('手机号不能为空'))
      }
    }, */
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
</style>
