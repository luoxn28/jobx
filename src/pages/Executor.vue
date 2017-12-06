<template>
  <div id="executor">

    <el-table
      v-loading="loadingExecutorList"
      element-loading-text="执行器去哪里了 :("
      :data="executorList"
      style="width: 100%"
      :row-class-name="tableRowClassName">
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column align="center" prop="name" label="名字"></el-table-column>
      <el-table-column align="center" prop="ip" label="IP"></el-table-column>
      <el-table-column align="center" prop="port" label="端口" width="80"></el-table-column>
      <el-table-column align="center" prop="registerTimeStr" label="注册时间"></el-table-column>
      <el-table-column align="center" prop="keepAliveTime" label="心跳间隔(s)" width="100"></el-table-column>
      <el-table-column align="center" prop="status" label="状态" width="100">
        <template scope="scope">
          <el-tag v-if="scope.row.status === 'ONLINE'" type="success" size="small">在线</el-tag>
          <el-tag v-if="scope.row.status === 'OFFLINE'" type="warning" size="small">离线</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template scope="scope">
          <el-button @click="beforeEditExecutor(scope.row)" type="text" size="small">编辑</el-button>
          <el-button @click="deleteExecutor(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--编辑-->
    <el-dialog title="编辑执行器" :visible.sync="editDialogFormVisible">
      <el-form :model="executorForm">
        <el-form-item label="IP" :label-width="formLabelWidth">
          <el-input v-model="executorForm.ip" auto-complete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="port" :label-width="formLabelWidth">
          <el-input v-model="executorForm.port" auto-complete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="名字" :label-width="formLabelWidth">
          <el-input v-model="executorForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="状态" :label-width="formLabelWidth">
          <el-input v-model="executorForm.status" auto-complete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="注册时间" :label-width="formLabelWidth">
          <el-input v-model="executorForm.registerTimeStr" auto-complete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="心跳间隔(s)" :label-width="formLabelWidth">
          <el-input v-model="executorForm.keepAliveTime" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editExecutor(executorForm)">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  export default {
    name: 'Executor',
    data() {
      return {
        loadingExecutorList: true,
        executorList: [],
        executorListUrl: '/api/executor/list',
        executorUrl: '/api/executor/',

        // 执行器编辑
        executorForm: {
          executorId: '',
          ip: '',
          port: '',
          name: '',
          status: '',
          keepAliveTime: '',
          registerTimeStr: '',
        },
        editDialogFormVisible: false,
        formLabelWidth: '120px',
      }
    },

    created: function() {
      this.loadingExecutorList = true;
      this.loadExecutorData();
      this.loadingExecutorList = false;
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
      // 加载执行器信息
      loadExecutorData() {
        this.$http.get(this.executorListUrl).then((res) => {
          this.executorList = res.data;
        });
      },

      // 编辑执行器
      beforeEditExecutor(row) {
        if (row.status !== 'ONLINE') {
          this.$message({
            type: 'error',
            message: '无法编辑不在线的执行器'
          });
          return;
        }

        this.executorForm = row;
        this.editDialogFormVisible = true
      },
      editExecutor(executor) {
        this.editDialogFormVisible = false;

        this.$http.put(this.executorUrl, {
          executorId: executor.executorId,
          ip: executor.ip,
          port: executor.port,
          name: executor.name,
          keepAliveTime: executor.keepAliveTime,
        }).then((res) => {
          this.$message({
            type: 'success',
            message: '更新成功!'
          });
          this.loadExecutorData()
        }).catch((res) => {
          this.$message({
            type: 'error',
            message: '更新失败!'
          });
        });
      },

      // 删除执行器
      deleteExecutor(row) {
        if (row.status === 'ONLINE') {
          this.$message({
            type: 'error',
            message: '无法删除在线的执行器'
          });
          return;
        }

        this.$confirm('此操作将删除该执行器[' + row.ip + ':' + row.port +'], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.$http.delete(this.executorUrl + row.executorId)
            .then((res) => {
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
              this.loadExecutorData();
            }).catch((res) => {
              this.$message({
                type: 'success',
                message: '删除失败!'
              });
            })

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    },
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">
  #executor {
    margin-top 10px
    margin-left 5px

    .el-table .warning-row {
      background: oldlace;
    }

    .el-table .success-row {
      background: #f0f9eb;
    }
  }
</style>

