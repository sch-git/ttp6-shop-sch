<template>
  <div>
    <!--第一导航区-->
    <el-menu
      active-text-color="#fff"
      background-color="#5AA4EE"
      class="el-menu-demo el-menu"
      mode="horizontal"
      text-color="#fff">
      <el-menu-item style="font-size: 30px">Lucky商城</el-menu-item>
      <el-header style="text-align: right; font-size: 12px">
      </el-header>
    </el-menu>

  </div>
</template>

<script>
export default {
  name: 'Header',
  data () {
    return {
    }
  }
}
</script>

<style scoped>
  .avatar {
    line-height: 60px;
    vertical-align: middle;
  }

  .el-menu {
    padding-left: 50px;
    padding-right: 50px;
    border: solid 1px #2C5782;
  }

  .el-dropdown {
    color: #fff;
  }
</style>
