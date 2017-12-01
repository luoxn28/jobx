<template>
  <div id="executor">

    <el-table
      :data="executorList"
      style="width: 100%"
      :row-class-name="tableRowClassName">
      <el-table-column type="index" width="50"/>
      <el-table-column align="center" prop="name" label="名字"/>
      <el-table-column align="center" prop="ip" label="IP"/>
      <el-table-column align="center" prop="port" label="端口"/>
      <el-table-column align="center" prop="status" label="状态">
        <template scope="scope">
          <el-tag v-if="scope.row.status === 'ONLINE'" type="success">在线</el-tag>
          <el-tag v-if="scope.row.status === 'OFFLINE'" type="gray">离线</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template scope="scope">
          <el-button @click="editExecutor(scope.row)" type="text" size="small">编辑</el-button>
          <el-button @click="deleteExecutor(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  export default {
    name: 'Executor',
    data() {
      return {
        executorList: [],
        executorUrl: '/api/executor/list/'
      }
    },

    created: function() {
      this.$http.get(this.executorUrl).then((res) => {
        this.executorList = res.data;
      });
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
      editExecutor(row) {
        console.info(row);
      },
      deleteExecutor(row) {
        console.info(row);
      }
    },
  }
</script>

<style lang="stylus" rel="stylesheet/stylus">
  #executor {
    .el-table .warning-row {
      background: oldlace;
    }

    .el-table .success-row {
      background: #f0f9eb;
    }
  }
</style>

