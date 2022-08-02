<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="社团类型" prop="typeId">
        <el-select v-model="queryParams.typeId" placeholder="请选择">
          <el-option
            v-for="item in typeList"
            :key="item.coporationTypeId"
            :label="item.typeName"
            :value="item.coporationTypeId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="社团编号" prop="corporationCode">
        <el-input
          v-model="queryParams.corporationCode"
          placeholder="请输入社团编号"
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
          v-hasPermi="['club:corporation:add']"
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
          v-hasPermi="['club:corporation:edit']"
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
          v-hasPermi="['club:corporation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['club:corporation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="corporationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="社团主键id" align="center" prop="corporationId" />-->
<!--      <el-table-column label="社团类型id" align="center" prop="typeId" />-->
      <el-table-column label="社团编号" align="center" prop="corporationCode" />
      <el-table-column label="社团类型" align="center" prop="typeName" />
      <el-table-column label="社团名" align="center" prop="corporationName" />
      <el-table-column label="成员数量" align="center" prop="number" />
      <el-table-column label="图片" align="center" prop="logo" >
        <template slot-scope="scope">
          <el-image :src="scope.row.logo"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="社团标语" align="center" prop="slogan" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            style="width: 60px"
            type="primary"
            plain
            size="mini"
            @click="seeMore(scope.row)"
          >查看详情</el-button>
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

    <!-- 添加或修改社团对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="社团类型" prop="typeId">
          <el-select v-model="form.typeId" placeholder="请选择">
            <el-option
              v-for="item in typeList"
              :key="item.coporationTypeId"
              :label="item.typeName"
              :value="item.coporationTypeId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="社团名" prop="corporationName">
          <el-input v-model="form.corporationName" placeholder="请输入社团名" />
        </el-form-item>
        <el-form-item label="上传" prop="logo" required>
          <image-upload v-model="form.logo"></image-upload>
        </el-form-item>
        <el-form-item label="社团标语" prop="slogan">
          <el-input v-model="form.slogan" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="openSeeMore" width="1000px" append-to-body>
      <el-table v-loading="loading" :data="userIdentityList" @selection-change="handleSelectionChange">
<!--        <el-table-column type="selection" width="55" align="center" />-->
        <el-table-column label="用户名" align="center" prop="userName" />
        <el-table-column label="身份状态" align="center" prop="userStatus" >
          <template slot-scope="scope">
            <el-tag type="warning" v-if="scope.row.userStatus === 0"> 社长 </el-tag>
            <el-tag type="info" v-if="scope.row.userStatus === 1"> 成员 </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="用户状态" align="center" prop="status" >
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.status === 0"> 在社 </el-tag>
            <el-tag type="danger" v-if="scope.row.status === 1"> 离社 </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="入社日期" align="center" prop="joinTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.joinTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

  </div>
</template>

<script>
import { listCorporation, getCorporation, delCorporation, addCorporation, updateCorporation, upload, getStuUserIdentityList } from "@/api/club/corporation";
import { listType, getType, delType, addType, updateType } from "@/api/club/type";

export default {
  name: "Corporation",
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
      // 社团表格数据
      corporationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openSeeMore: false,
      userIdentityList:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        typeId: null,
        corporationName: null,
        number: null,
        slogan: null,
        corporationCode: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        typeId: [
          { required: true, message: "社团类型id不能为空", trigger: "change" }
        ],
        corporationName: [
          { required: true, message: "社团名不能为空", trigger: "blur" }
        ],
        logo: [
          { required: true, message: "社团logo不能为空", trigger: "blur" }
        ],
        slogan: [
          { required: true, message: "社团标语不能为空", trigger: "blur" }
        ],
      },
      //类型集合
      typeList:[],
    };
  },
  created() {
    this.getList();
    this.getTypeList();
  },
  methods: {
    /** 查询社团列表 */
    getList() {
      this.loading = true;
      listCorporation(this.queryParams).then(response => {
        this.corporationList = response.rows;
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
        corporationId: null,
        typeId: null,
        corporationName: null,
        number: null,
        slogan: null,
        createTime: null
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
      this.ids = selection.map(item => item.corporationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加社团";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const corporationId = row.corporationId || this.ids
      getCorporation(corporationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改社团";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.corporationId != null) {
            updateCorporation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCorporation(this.form).then(response => {
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
      const corporationIds = row.corporationId || this.ids;
      this.$modal.confirm('是否确认删除社团编号为"' + corporationIds + '"的数据项？').then(function() {
        return delCorporation(corporationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('club/corporation/export', {
        ...this.queryParams
      }, `corporation_${new Date().getTime()}.xlsx`)
    },

    getTypeList(){
      listType().then(response =>{
        this.typeList = response.rows;
      })
    },
    upload(file) {
      console.log(file);
      update(file).then(response =>{
        console.log(response);
      })
    },

    seeMore(row) {
      console.log(row)
      getStuUserIdentityList(row.corporationId).then(response=>{
        this.userIdentityList = response.data
        this.openSeeMore = true
        this.title = row.corporationName
      })
    }

  }
};
</script>

