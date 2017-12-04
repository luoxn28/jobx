<template>
  <div id="job">

    <AddJob></AddJob>

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
          <el-button @click="beforeEditJob(scope.row)" size="mini" type="text">编辑</el-button>
          <el-button @click="deleteJob(scope.row)" size="mini" type="text">删除</el-button>
          <el-button @click="triggerJob(scope.row)" size="mini" type="text">执行</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 编辑任务 -->
    <el-dialog title="编辑任务" :visible.sync="editJobFormVisible">
      <el-form :model="jobForm">
        <el-form-item label="任务名字" :label-width="jobFormLabelWidth">
          <el-input v-model="jobForm.jobName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="任务类型" :label-width="jobFormLabelWidth">
          <el-select v-if="jobForm.jobType === 'Script'" disabled placeholder="脚本任务"></el-select>
          <el-select v-if="jobForm.jobType === 'Java'" disabled placeholder="脚本任务"></el-select>
        </el-form-item>
        <el-form-item label="CRON" :label-width="jobFormLabelWidth">
          <el-input v-model="jobForm.cron" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="任务描述" :label-width="jobFormLabelWidth">
          <el-input v-model="jobForm.desc" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="参数" :label-width="jobFormLabelWidth">
          <el-input v-model="jobForm.param" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editJobFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editJob(jobForm)">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import AddJob from '../components/AddJob.vue'

  export default {
    name: 'Job',
    components: {
      AddJob
    },
    data() {
      return {
        loadingJobList: true,
        jobList: [],
        jobUrl: '/api/job/',

        // 编辑任务
        editJobFormVisible: false,
        jobFormLabelWidth: '120px',
        jobForm: {
          jobId: '',
          jobName: '',
          jobType: '',
          jobRole: '',
          cron: '',
          desc: '',
          param: '',
          className: '',
          paramCreator: '',
          paramDynamic: '',
          emailPhone: '',
          routeStrategy: '',
          failStrategy: '',
          createTime: '',
          updateTime: '',
        },
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
        this.jobForm = job;
        this.editJobFormVisible = true;
      },
      editJob(job) {
        this.editJobFormVisible = false;
      },
      deleteJob(job) {

      },
      triggerJob(job) {

      }
    },
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">
  #job {

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

