<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="社团名" prop="corporationName">
        <el-input
          v-model="queryParams.corporationName"
          placeholder="请输入社团名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="社团类型" prop="typeName">
        <el-input
          v-model="queryParams.typeName"
          placeholder="请输入社团类型"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ask:setCorporation:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ask:setCorporation:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ask:setCorporation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ask:setCorporation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="setCorporationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="成员创建社团申请主键id" align="center" prop="setCorporationId" />-->
      <el-table-column label="用户名" align="center" prop="userName" />
      <el-table-column label="社团名" align="center" prop="corporationName" />
      <el-table-column label="社团标语" align="center" prop="corporationSlogan" />
      <el-table-column label="社团类型" align="center" prop="typeName" />
      <el-table-column label="图片" align="center" prop="logo" >
        <template slot-scope="scope">
          <el-image :src="scope.row.logo"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="申请状态" align="center" prop="status" >
        <template slot-scope="scope">
          <el-tag type="danger" v-if="scope.row.status === 2">未通过</el-tag>
          <el-tag type="success" v-if="scope.row.status === 1">通过</el-tag>
          <el-tag type="warning" v-if="scope.row.status === 0">待审</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="申请理由" align="center" prop="reason" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <div v-if="scope.row.status == 0">
            <el-button
              style="width: 50px"
              plain
              size="mini"
              type="success"
              @click="pass(scope.row)"
            >通过</el-button>
            <el-button
              style="width: 50px"
              plain
              size="mini"
              type="danger"
              @click="notPass(scope.row)"
            >未通过</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改成员创建社团申请管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="社团名" prop="corporationName">
          <el-input v-model="form.corporationName" placeholder="请输入社团名" />
        </el-form-item>
        <el-form-item label="社团类型id" prop="corporationSlogan">
          <el-input v-model="form.corporationSlogan" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="社团类型id" prop="corporationTypeId">
          <el-input v-model="form.corporationTypeId" placeholder="请输入社团类型id" />
        </el-form-item>
        <el-form-item label="上传" prop="logo" required>
          <image-upload v-model="form.logo"></image-upload>
        </el-form-item>
        <el-form-item label="申请理由" prop="reason">
          <el-input v-model="form.reason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSetCorporation, getSetCorporation, delSetCorporation, addSetCorporation, updateSetCorporation,audit } from "@/api/ask/setCorporation";

export default {
  name: "SetCorporation",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 成员创建社团申请管理表格数据
      setCorporationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        corporationName: null,
        corporationSlogan: null,
        corporationTypeId: null,
        status: null,
        reason: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询成员创建社团申请管理列表 */
    getList() {
      this.loading = true;
      listSetCorporation(this.queryParams).then(response => {
        this.setCorporationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        setCorporationId: null,
        userId: null,
        corporationName: null,
        corporationSlogan: null,
        corporationTypeId: null,
        status: 0,
        reason: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.setCorporationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加成员创建社团申请管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const setCorporationId = row.setCorporationId || this.ids
      getSetCorporation(setCorporationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改成员创建社团申请管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.setCorporationId != null) {
            updateSetCorporation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSetCorporation(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const setCorporationIds = row.setCorporationId || this.ids;
      this.$modal.confirm('是否确认删除成员创建社团申请管理编号为"' + setCorporationIds + '"的数据项？').then(function() {
        return delSetCorporation(setCorporationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ask/setCorporation/export', {
        ...this.queryParams
      }, `setCorporation_${new Date().getTime()}.xlsx`)
    },

    pass(row){
      row.status = 1;
      audit(row).then(response=>{
        this.$modal.msgSuccess("审核成功！");
        this.getList();
      })
    },

    notPass(row){
      row.status = 2;
      audit(row).then(response=>{
        this.$modal.msgSuccess("审核成功！");
        this.getList();
      })
    }
  }
};
</script>
