<template>
  <div>
    <!--第二导航区-->
    <el-menu
      active-text-color="#fff"
      background-color="#5AA4EE"
      class="el-menu-demo el-menu"
      mode="horizontal"
      text-color="#fff">
      <el-menu-item>
        <router-link to="/home/main"><i class="el-icon-s-home"></i>主页</router-link>
      </el-menu-item>
      <el-menu-item>
        <router-link to="/home/order"><i class="el-icon-s-home"></i>我的订单</router-link>
      </el-menu-item>
      <el-menu-item>
        <router-link to="/home/car"><i class="el-icon-s-home"></i>我的购物车</router-link>
      </el-menu-item>
      <el-dropdown style="float:right;padding-left: 15px">
        <div>
          <el-avatar :src=imageUrl class="avatar-head" fit="scale-down"
                     size="medium">
          </el-avatar>
          <span class="avatar-head" style="text-align: center">{{ userInfoForm.name }}</span>
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/home/userInfo">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link>
          <router-link to="/home/order">
            <el-dropdown-item>我的订单</el-dropdown-item>
          </router-link>
          <router-link to="/home/car">
            <el-dropdown-item>我的购物车</el-dropdown-item>
          </router-link>
        </el-dropdown-menu>
      </el-dropdown>
    </el-menu>
    <el-row>
      <el-col :offset="6" :span="12">
        <el-card :span="6" class="box-card">
          <el-row>
            <el-col :span="6">
              <el-upload
                :before-upload="beforeAvatarUpload"
                :on-success="handleAvatarSuccess"
                :show-file-list="false"
                action="http://localhost:8088/lucky/upload/image"
                class="el-upload--picture-card"
                name="picture">
                <img :src="imageUrl" class="avatar" v-if="imageUrl">
                <i class="el-icon-plus avatar-uploader-icon" v-else></i>
              </el-upload>
            </el-col>
            <el-col :span="6">
              <div class="username">
                用户名：<span v-text="userInfoForm.name">15160189247</span>
              </div>
              <div class="totalscore">
                总积分：<span v-text="userInfoForm.totalScore">15160189247</span>
              </div>
            </el-col>
            <el-col :offset="8" :span="3" style="margin-top: 80px">
              <el-button @click="editUserInfo" plain
                         type="success">编辑
              </el-button>
            </el-col>
          </el-row>
          <el-card class="box-card" shadow="never" v-if="!editButton">
            <div class="text item">
              &nbsp;&nbsp;&nbsp;用户名：&nbsp;&nbsp;&nbsp;<span v-text="userInfoForm.name">15160189247</span>
            </div>
            <div class="text item">
              手机号码：&nbsp;&nbsp;&nbsp;<span v-text="userInfoForm.phone">15160189247</span>
            </div>
            <div class="text item">
              邮箱地址：&nbsp;&nbsp;&nbsp;<span v-text="userInfoForm.email">1208579668@qq.com</span>
            </div>
            <div class="text item">
              默认地址：&nbsp;&nbsp;&nbsp;<span v-text="userInfoForm.address">福建省宁德市</span>
            </div>
          </el-card>
          <el-card class="box-card" shadow="never" v-if="editButton">
            <el-form :model="userInfoForm" :rules="rules" class="demo-ruleForm" label-width="100px" ref="userInfoForm"
                     status-icon v-if="editButton">
              <el-form-item label="用户名" prop="name">
                <el-input autocomplete="off" type="name" v-model="userInfoForm.name"></el-input>
              </el-form-item>
              <el-form-item label="手机号码" prop="phone">
                <el-input autocomplete="off" type="phone" v-model="userInfoForm.phone"></el-input>
              </el-form-item>
              <el-form-item label="邮箱地址" prop="email">
                <el-input autocomplete="off" type="email" v-model="userInfoForm.email"></el-input>
              </el-form-item>
              <el-form-item label="默认地址" prop="address">
                <el-input autocomplete="off" type="address" v-model="userInfoForm.address"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button @click="submitForm('userInfoForm')" type="primary">提交</el-button>
                <el-button @click="resetForm('userInfoForm')">重置</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'UserInfo',
  data () {
    return {
      imageUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      userInfoForm: {
        name: '',
        address: '',
        phone: '',
        email: '',
        headImageUrl: '',
        totalScore: ''
      },
      editButton: false,
      rules: {
        phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
        name: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        address: [{ required: true, message: '请输入地址', trigger: 'blur' }]
      }
    }
  },
  created () {

  },
  mounted () {
    this.getUserInfo()
  },
  methods: {
    // 获取用户个人页信息
    getUserInfo () {
      const _this = this
      this.$axios.get('/lucky/user/info').then((res) => {
        _this.userInfoForm = res.data.data
        if (_this.userInfoForm.headImageUrl.length > 0) {
          _this.imageUrl = _this.userInfoForm.headImageUrl
        }
      })
    },
    editUserInfo () {
      this.editButton = !this.editButton
    },
    // 修改用户信息
    submitForm (formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let userInfoAO = {
            name: this.userInfoForm.name,
            phone: this.userInfoForm.phone,
            email: this.userInfoForm.email,
            address: this.userInfoForm.address
          }
          this.$axios.put('/lucky/user/info', userInfoAO).then((res) => {
            if (res.data.code === 200) {
              _this.$message.success(res.data.msg)
              _this.editButton = !_this.editButton
            } else {
              _this.$message.error(res.data.msg)
            }
          }).catch(() => {
            _this.$message.error('修改失败!')
          })
        } else {
          this.$message.error('请填写完整信息!')
        }
      })
    },
    // 头像上传成功的回调
    handleAvatarSuccess (res, file) {
      const _this = this
      this.userInfoForm.headImageUrl = res.msg
      this.imageUrl = res.data
      let userInfoAO = {
        'headImageUrl': this.imageUrl
      }
      // 将图片url保存到数据库
      if (res.data != null) {
        this.$axios.post('/lucky/headImageUrl', userInfoAO).then((res) => {
          if (res.data.code === 200) {
            _this.$message.success(res.data.msg)
            _this.getUserInfo()
          } else {
            _this.$message.error(res.data.msg)
          }
        }).catch(() => {
          _this.$message.error('上传失败')
        })
      } else {
        this.$message.error('上传失败')
      }
    },
    // 头像上传之前的判断
    beforeAvatarUpload (file) {
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isLt2M
    }
  }
}
</script>

<style scoped>
  .username {
    font-size: 16px;
    font-weight: bolder;
    margin-top: 50px;
    margin-bottom: 20px;
  }

  .totalscore {
    font-size: 16px;
    font-weight: bolder;
  }

  .el-menu {
    padding-left: 50px;
    padding-right: 50px;
    height: 60px;
    border: solid 1px #2C5782;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .el-upload--picture-card {
    background-color: #fbfdff;
    border: 1px dashed #c0ccda;
    border-radius: 6px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    width: 128px;
    height: 128px;
    line-height: 126px;
    vertical-align: top;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 128px;
    height: 128px;
    line-height: 128px;
    text-align: center;
  }

  .avatar {
    width: 128px;
    height: 128px;
    display: block;
  }

  .text {
    font-size: 15px;
  }

  .item {
    padding: 18px 0;
  }

  .box-card {
    margin-top: 15px;
  }

  .avatar-head {
    line-height: 60px;
    vertical-align: middle;
  }

  .el-menu-item {
    height: 60px;
    line-height: 60px;
  }
</style>
