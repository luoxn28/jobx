<template>
  <div id="index">
    <header class="toolbar">
      <el-menu id="menu-top" theme="dark" :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
        <el-button id="title" type="text">任务调度中心</el-button>
      </el-menu>
    </header>

    <el-row class="tac">
      <el-col :span="4">
        <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" theme="dark">
          <router-link to="/index/job">
            <el-menu-item index="1">任务管理</el-menu-item>
          </router-link>
          <router-link to="/index/log">
            <el-menu-item index="2">调度日志</el-menu-item>
          </router-link>
          <router-link to="/index/executor">
            <el-menu-item index="3">执行器管理</el-menu-item>
          </router-link>
          <router-link to="/index/executor-group">
            <el-menu-item index="4">任务组管理</el-menu-item>
          </router-link>
          <el-submenu index="5">
            <template slot="title">导航</template>
            <el-menu-item index="1-1">选项1</el-menu-item>
            <el-menu-item index="1-2">选项2</el-menu-item>
            <el-menu-item index="1-3">选项3</el-menu-item>
            <el-menu-item index="1-4">选项4</el-menu-item>
          </el-submenu>
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
        activeIndex: '2'
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style>
  #menu-top {
    height: 50px;
  }
  #title {
    font-size: large;
    margin-left: 40px;
    margin-top: 10px;
  }
</style>
