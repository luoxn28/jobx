<template>
  <div id="job">

    <el-button class="addJobButton" type="primary" @click="AddJobDialogVisible = true"
      icon="el-icon-circle-plus-outline">
      添加任务
    </el-button>

    <el-table class="jobList"
      v-loading="loadingJobList"
      element-loading-text="任务去哪里了 :("
      :data="jobList"
      style="width: 100%"
      :row-class-name="tableRowClassName">

      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="任务名字">
              <span>{{ props.row.jobName }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column align="center" prop="jobName" label="名字"></el-table-column>
      <el-table-column align="center" prop="jobType" label="任务类型" width="100"></el-table-column>
      <el-table-column align="center" prop="cron" label="cron表达式"></el-table-column>
      <el-table-column align="center" prop="param" label="任务参数"></el-table-column>
      <el-table-column align="center" prop="className" label="执行类"></el-table-column>
      <el-table-column align="center" prop="updateTime" label="更新时间"></el-table-column>
      <el-table-column label="操作">
        <template scope="scope">
          <el-button @click="beforeEditJob(scope.row)" type="text" size="small">编辑</el-button>
          <el-button @click="deleteJob(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  export default {
    name: 'Job',
    data() {
      return {
        loadingJobList: true,
        jobList: [],
        jobUrl: '/api/job/',

        // 添加任务
        AddJobDialogVisible: false,
      }
    },

    created: function() {
      this.loadingJobList = true;
      this.loadJobList();
      this.loadingJobList = false;
    },

    methods: {
      tableRowClassName({row, rowIndex}) {
        if (rowIndex === 1) {
          return 'warning-row';
        } else if (rowIndex === 3) {
          return 'success-row';
        }
        return '';
      },

      // 加载任务列表
      loadJobList() {
        this.$http.get(this.jobUrl + "list").then((res) => {
          this.jobList = res.data;
        });
      },

      beforeEditJob(job) {

      },
      deleteJob(job) {

      }
    },
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">
  #job {
    .addJobButton {
      margin-top 5px
      margin-right 10px
      float right
    }

    .jobList {
      margin-left 5px
    }

    // 扩展行css属性
    .demo-table-expand {
      font-size: 0;
    }
    .demo-table-expand label {
      width: 90px;
      color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
      margin-right: 0;
      margin-bottom: 0;
      width: 50%;
    }

  }
</style>

