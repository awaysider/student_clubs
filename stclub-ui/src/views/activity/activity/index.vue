<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动编号" prop="activityCode">
        <el-input
          v-model="queryParams.activityCode"
          placeholder="请输入活动编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="举办人" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入举办人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="社团名" prop="corporationName">
        <el-input
          v-model="queryParams.corporationName"
          placeholder="请输入社团"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动名字" prop="activityName">
        <el-input
          v-model="queryParams.activityName"
          placeholder="请输入活动名字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="活动预算" prop="activityBudget">-->
<!--        <el-input-->
<!--          v-model="queryParams.activityBudget"-->
<!--          placeholder="请输入活动预算"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="参加最大人数" prop="maxNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.maxNumber"-->
<!--          placeholder="请输入参加最大人数"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="开始时间" prop="startTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.startTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择开始时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="结束时间" prop="endTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.endTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择结束时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="活动地点" prop="activityPlace">
        <el-input
          v-model="queryParams.activityPlace"
          placeholder="请输入活动地点"
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
          v-hasPermi="['activity:activity:add']"
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
          v-hasPermi="['activity:activity:edit']"
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
          v-hasPermi="['activity:activity:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['activity:activity:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="activityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="活动主键id" align="center" prop="actId" />-->
<!--      <el-table-column label="举办人id" align="center" prop="userId" />-->
      <el-table-column label="活动编号" align="center" prop="activityCode"/>
      <el-table-column label="举办人名" align="center" prop="userName" />
<!--      <el-table-column label="社团id" align="center" prop="corporationId" />-->
      <el-table-column label="社团名" align="center" prop="corporationName" />
      <el-table-column label="活动名字" align="center" prop="activityName" />
      <el-table-column label="活动预算" align="center" prop="activityBudget" />
      <el-table-column label="参加最大人数" align="center" prop="maxNumber" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动地点" align="center" prop="activityPlace" />
      <el-table-column label="活动内容" align="center" prop="activityContent" />
      <el-table-column label="活动状态" align="center" prop="status" width="180">
        <template slot-scope="scope">
          <el-tag type="danger" v-if="scope.row.status === 3">已结束</el-tag>
          <el-tag type="success" v-if="scope.row.status === 2">进行中</el-tag>
          <el-tag type="warning" v-if="scope.row.status === 1">待进行</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['activity:activity:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['activity:activity:remove']"
          >删除</el-button>
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

    <!-- 添加或修改活动信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="举办人id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入举办人id" />
        </el-form-item>
        <el-form-item label="社团id" prop="corporationId">
          <el-input v-model="form.corporationId" placeholder="请输入社团id" />
        </el-form-item>
        <el-form-item label="活动名字" prop="activityName">
          <el-input v-model="form.activityName" placeholder="请输入活动名字" />
        </el-form-item>
        <el-form-item label="活动预算" prop="activityBudget">
          <el-input v-model="form.activityBudget" placeholder="请输入活动预算" />
        </el-form-item>
        <el-form-item label="参加最大人数" prop="maxNumber">
          <el-input v-model="form.maxNumber" placeholder="请输入参加最大人数" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable size="small"
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable size="small"
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动地点" prop="activityPlace">
          <el-input v-model="form.activityPlace" placeholder="请输入活动地点" />
        </el-form-item>
        <el-form-item label="活动内容">
          <el-input type="textarea"  v-model="form.activityContent" placeholder="请输入活动内容"/>
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
import { listActivity, getActivity, delActivity, addActivity, updateActivity } from "@/api/activity/activity";

export default {
  name: "Activity",
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
      // 活动信息管理表格数据
      activityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        activityCode: null,
        corporationId: null,
        activityName: null,
        activityBudget: null,
        maxNumber: null,
        startTime: null,
        endTime: null,
        activityPlace: null,
        activityContent: null
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
    /** 查询活动信息管理列表 */
    getList() {
      this.loading = true;
      listActivity(this.queryParams).then(response => {
        this.activityList = response.rows;
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
        actId: null,
        userId: null,
        activityCode: null,
        corporationId: null,
        activityName: null,
        activityBudget: null,
        maxNumber: null,
        startTime: null,
        endTime: null,
        activityPlace: null,
        activityContent: null
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
      this.ids = selection.map(item => item.actId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加活动信息管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const actId = row.actId || this.ids
      getActivity(actId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动信息管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.actId != null) {
            console.log(this.form);
            updateActivity(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addActivity(this.form).then(response => {
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
      const actIds = row.actId || this.ids;
      this.$modal.confirm('是否确认删除活动信息管理编号为"' + actIds + '"的数据项？').then(function() {
        return delActivity(actIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('activity/activity/export', {
        ...this.queryParams
      }, `activity_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
