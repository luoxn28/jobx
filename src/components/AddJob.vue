<template>
  <div id="addJob">
    <el-button class="addJobButton" type="primary" @click="addJobDialogVisible = true"
               icon="el-icon-circle-plus-outline">
      添加任务
    </el-button>

    <!-- 添加任务 -->
    <el-dialog title="编辑任务" :visible.sync="addJobDialogVisible">
      <el-form :model="jobForm">
        <el-form-item label="任务名字" :label-width="jobFormLabelWidth">
        <el-input v-model="jobForm.jobName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="任务类型" :label-width="jobFormLabelWidth">
          <el-select v-model="jobForm.jobType" placeholder="请选择">
            <el-option
              v-for="item in jobTypeOptions"
              :key="item[0]"
              :label="item[1]"
              :value="item[0]">
            </el-option>
          </el-select>
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
        <el-button type="danger" @click="clearJob(true)">清空</el-button>
        <el-button @click="addJobDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addJob(jobForm)">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  export default
  {
    name: 'addJob',
    data () {
      return {
        addJobDialogVisible: false,
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
        jobTypeOptions: [],
        jobTypeUrl: '/api/job/type/'
      }
    },

    created: function() {
      this.$http.get(this.jobTypeUrl).then((res) => {
        this.jobTypeOptions = res.data;
      });
    },

    methods: {
      addJob(job) {
        this.addJobDialogVisible = false;

        console.info(job);
      },
      clearJob(showMessage) {
        this.jobForm.jobId= '';
        this.jobForm.jobName= '';
        this.jobForm.jobType= '';
        this.jobForm.jobRole= '';
        this.jobForm.cron= '';
        this.jobForm.desc= '';
        this.jobForm.param= '';
        this.jobForm.className= '';
        this.jobForm.paramCreator= '';
        this.jobForm.paramDynamic= '';
        this.jobForm.emailPhone= '';
        this.jobForm.routeStrategy= '';
        this.jobForm.failStrategy= '';
        this.jobForm.createTime= '';
        this.jobForm.updateTime= '';

        if (showMessage) {
          this.$message({
            type: 'success',
            message: '任务信息清空完成'
          });
        }
      }
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">
  #addJob {
    .addJobButton {
      margin-top 5px
      margin-right 10px
      float right
    }
  }
</style>
