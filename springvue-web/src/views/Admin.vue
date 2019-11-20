<template>
  <el-container style="height: 100vh; border: 1px solid #eee">
    <el-header style="text-align: right;font-size: 14px">
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 10px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>{{ this.adminVO.name }}</span>
    </el-header>
    <el-container>
      <el-aside style="background-color: rgb(238, 241, 246)" width="200px">
        <el-menu router>
          <el-menu-item index="0">首页</el-menu-item>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>会员管理</template>
            <el-menu-item-group>
              <el-menu-item index="/admin/userList">用户列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>订单管理</template>
            <el-menu-item-group>
              <el-menu-item index="/admin/orderList">订单列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>商品管理</template>
            <el-menu-item-group>
              <el-menu-item index="/admin/goodList">商品列表</el-menu-item>
              <el-menu-item index="/admin/goodCategory">分类管理</el-menu-item>
              <el-menu-item index="/admin/goodProperty">属性管理</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
    <el-footer></el-footer>
  </el-container>
</template>

<script>
export default {
  mounted () {
    const _this = this
    this.$axios.get('/lucky/admin').then((res) => {
      _this.adminVO = res.data.data
      console.log(res.data)
    })
  },
  data () {
    return {
      adminVO: {
        'name': ''
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    color: #333;
    text-align: center;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>
