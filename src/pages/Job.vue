<template>
  <div id="job">

    <AddJob></AddJob>

    <el-table class="jobList"
      v-loading="loadingJobList"
      element-loading-text="任务去哪里了 :("
      :data="jobList"
      style="width: 100%"
      :row-class-name="tableRowClassName">

      <!--隐藏job信息-->
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="任务名字">
              <span>{{ props.row.jobName }}</span>
            </el-form-item>
            <el-form-item label="更新时间">
              <span>{{ props.row.updateTime }}</span>
            </el-form-item>
            <el-form-item label="任务参数">
              <span>{{ props.row.className }}</span>
            </el-form-item>
            <el-form-item label="任务角色">
              <el-tag v-if="props.row.jobRole === 'Normal'" type="success" size="small">独立任务</el-tag>
              <el-tag v-if="props.row.jobRole === 'Child'" type="success" size="small">子任务</el-tag>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column align="center" prop="jobName" label="名字"></el-table-column>
      <el-table-column align="center" prop="jobType" label="任务类型" width="100">
        <template scope="scope">
          <el-tag v-if="scope.row.jobType === 'Script'" type="info" size="small">脚本任务</el-tag>
          <el-tag v-if="scope.row.jobType === 'Java'" type="success" size="small">Java任务</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="cron" label="cron表达式"></el-table-column>
      <el-table-column align="center" prop="classNameScriptPath" label="执行类/脚本"></el-table-column>
      <el-table-column align="center" prop="status" label="状态">
        <template scope="scope">
          <el-tag v-if="scope.row.status === 'CREATED'" type="info" size="small" >创建</el-tag>
          <el-tag v-if="scope.row.status === 'RUNNING'" type="success" size="small">运行</el-tag>
          <el-tag v-if="scope.row.status === 'PAUSE'" type="info" size="small">暂停</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template scope="scope">
          <el-button @click="beforeEditJob(scope.row)" size="mini" type="text" style="color: #409EFF;">编辑</el-button>
          <el-button @click="deleteJob(scope.row)" size="mini" type="text" style="color: #FA5555;">删除</el-button>
          <el-button @click="triggerJob(scope.row)" size="mini" type="text" style="color: #67C23A;">执行</el-button>
          <el-button v-if="scope.row.status !== 'PAUSE'"
                     @click="pauseJob(scope.row)" size="mini" type="text" style="color: #67C23A;">暂停</el-button>
          <el-button v-if="scope.row.status === 'PAUSE'"
                     @click="resumeJob(scope.row)" size="mini" type="text" style="color: #67C23A;">恢复</el-button>
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
        jobUrl: '/api/job',

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
          scriptPath: '',
          className: '',
          classNameScriptPath: '',
          paramCreator: '',
          paramDynamic: '',
          emailPhone: '',
          routeStrategy: '',
          failStrategy: '',
          createTime: '',
          updateTime: '',
          status: '',
        },
      }
    },

    created: function() {
      this.loadingJobList = true;
      this.loadJobList();
      this.loadingJobList = false;
    },

    methods: {
      catchError(res) {
        if (res.code < 0) {
          this.$message.error(`请求错误：` + res.msg);
          return true;
        }

        return false;
      },

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
        this.$http.get(this.jobUrl + "/list").then((res) => {
          if (this.catchError(res)) {
            return false;
          }

          this.jobList = res.data.data;
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

        this.$confirm('此操作将删除任务[ ' + job.jobName + ' ], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.$http.delete(this.jobUrl + '/' + job.jobId)
            .then((res) => {
              if (this.catchError(res)) {
                return false;
              }

              this.$message.success("删除任务成功");
              this.loadJobList();
            })
        }).catch(() => {
          // 点击了取消
        });
      },

      triggerJob(job) {

        this.$confirm('此操作将触发任务[ ' + job.jobName + ' ], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.$http.put(this.jobUrl + '/trigger/' + job.jobId)
            .then((res) => {
              if (this.catchError(res)) {
                return false;
              }

              this.$message.success("触发任务成功");
              this.loadJobList();
            })
        }).catch(() => {
          // 点击了取消
        });
      },

      pauseJob(job) {

        this.$confirm('此操作将暂定任务[ ' + job.jobName + ' ], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.$http.put(this.jobUrl + '/pause/' + job.jobId)
            .then((res) => {
              if (this.catchError(res)) {
                return false;
              }

              this.$message.success("暂定任务成功");
              this.loadJobList();
            })
        }).catch(() => {
          // 点击了取消
        });
      },

      resumeJob(job) {

        this.$confirm('此操作将恢复任务[ ' + job.jobName + ' ], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.$http.put(this.jobUrl + '/resume/' + job.jobId)
            .then((res) => {
              if (this.catchError(res)) {
                return false;
              }

              this.$message.success("恢复任务成功");
              this.loadJobList();
            })
        }).catch(() => {
          // 点击了取消
        });
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

