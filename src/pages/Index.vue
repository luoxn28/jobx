<template>
  <div id="index">
    <header class="toolbar">
      <el-menu
        id="menu-top"
        class="el-menu-demo"
        background-color="#2D2F33">
        <el-button id="title" type="text">任务调度中心</el-button>
      </el-menu>
    </header>

    <el-row class="tac">
      <el-col :span="4">
        <el-menu :span="4"
          default-active="1"
          class="el-menu-vertical-demo"
          background-color="#2D2F33"
          text-color="#fff"
          active-text-color="#ffd04b">

          <router-link to="/index">
            <el-menu-item index="1">
              <i class="el-icon-location"></i>
              <span slot="title">首页</span>
            </el-menu-item>
          </router-link>

          <router-link to="/index/job">
            <el-menu-item index="2">
              <i class="el-icon-menu"></i>
              <span slot="title">任务管理</span>
          </el-menu-item>
          </router-link>

          <router-link to="/index">
            <el-menu-item index="3">
              <i class="el-icon-tickets"></i>
              <span slot="title">日志管理</span>
            </el-menu-item>
          </router-link>

          <router-link to="/index/executor">
            <el-menu-item index="4">
              <i class="el-icon-setting"></i>
              <span slot="title">执行器管理</span>
            </el-menu-item>
          </router-link>

        </el-menu>
      </el-col>

      <el-col :span="20">
        <router-view></router-view>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: 'Index',
    data () {
      return {
      }
    },

    methods: {
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">
  #index {

  }

  #menu-top {
    height: 60px;
  }
  #title {
    font-size: large;
    margin-left: 40px;
    margin-top: 10px;
  }
</style>
