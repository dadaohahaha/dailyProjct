<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="出发时间" prop="startTime">
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.startTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择出发时间">-->
<!--        </el-date-picker>-->
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="yyyy-MM-dd HH:mm:ss"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :default-time="['00:00:00', '23:59:59']"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="耗时/分钟" prop="spendTime">
        <el-input
          v-model="queryParams.spendTime"
          placeholder="请输入耗时/分钟"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行驶里程" prop="distance">
        <el-input
          v-model="queryParams.distance"
          placeholder="请输入行驶里程"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="自启动起油耗" prop="oilConsumption">
        <el-input
          v-model="queryParams.oilConsumption"
          placeholder="请输入自启动起油耗"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="剩余里程" prop="remainingMileage">
        <el-input
          v-model="queryParams.remainingMileage"
          placeholder="请输入剩余里程"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="累计行驶里程" prop="totalMileage">-->
<!--        <el-input-->
<!--          v-model="queryParams.totalMileage"-->
<!--          placeholder="请输入累计行驶里程"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="数据范围" prop="dataScope">-->
<!--        <el-input-->
<!--          v-model="queryParams.dataScope"-->
<!--          placeholder="请输入数据范围"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
          v-hasPermi="['trip:drive:add']"
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
          v-hasPermi="['trip:drive:edit']"
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
          v-hasPermi="['trip:drive:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['trip:drive:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="driveList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column label="记录ID" align="center" prop="driveId" />
      <el-table-column label="出发时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
<!--          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{m}') }}</span>-->
          <span>{{ parseTime(scope.row.startTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="耗时/分钟" align="center" prop="spendTime" />
      <el-table-column label="行驶里程(km)" align="center" prop="distance" />
      <el-table-column label="自启动起油耗" align="center" prop="oilConsumption" />
      <el-table-column label="剩余里程(km)" align="center" prop="remainingMileage" />
      <el-table-column label="累计行驶里程(km)" width="128" align="center" prop="totalMileage" />
<!--      <el-table-column label="型号" align="center" prop="carType" />-->
      <el-table-column label="型号" align="center" prop="carType" width="132">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.car_type" :value="scope.row.carType"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="数据范围" align="center" prop="dataScope" />-->
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['trip:drive:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['trip:drive:remove']"
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

    <!-- 添加或修改行驶信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="出发时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择出发时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="耗时(分钟)" prop="spendTime">
          <el-input v-model="form.spendTime" placeholder="请输入耗时/分钟" />
        </el-form-item>
        <el-form-item label="行驶里程(km)" prop="distance">
          <el-input v-model="form.distance" placeholder="请输入行驶里程" />
        </el-form-item>
        <el-form-item label="自启动起油耗" prop="oilConsumption">
          <el-input v-model="form.oilConsumption" placeholder="请输入自启动起油耗" />
        </el-form-item>
        <el-form-item label="剩余里程(km)" prop="remainingMileage">
          <el-input v-model="form.remainingMileage" placeholder="请输入剩余里程" />
        </el-form-item>
        <el-form-item label="累计行驶里程(km)" prop="totalMileage">
          <el-input v-model="form.totalMileage" placeholder="请输入累计行驶里程" />
        </el-form-item>
        <el-form-item label="型号" prop="carType">
          <el-select v-model="form.carType" placeholder="请选择型号">
            <el-option
              v-for="dict in dict.type.car_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
<!--        <el-form-item label="删除标志" prop="delFlag">-->
<!--          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="数据范围" prop="dataScope">-->
<!--          <el-input v-model="form.dataScope" placeholder="请输入数据范围" />-->
<!--        </el-form-item>-->
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listDrive, getDrive, delDrive, addDrive, updateDrive } from "@/api/trip/drive";

export default {
  name: "Drive",
  dicts: ['car_type'],
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
      // 行驶信息表格数据
      driveList: [],
      // 日期范围
      dateRange: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        startTime: null,
        spendTime: null,
        distance: null,
        oilConsumption: null,
        remainingMileage: null,
        totalMileage: null,
        carType: null,
        dataScope: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        spendTime: [
          { required: true, message: "耗时/分钟不能为空", trigger: "blur" }
        ],
        distance: [
          { required: true, message: "行驶里程不能为空", trigger: "blur" }
        ],
        oilConsumption: [
          { required: true, message: "自启动起油耗不能为空", trigger: "blur" }
        ],
        remainingMileage: [
          { required: true, message: "剩余里程不能为空", trigger: "blur" }
        ],
        totalMileage: [
          { required: true, message: "累计行驶里程不能为空", trigger: "blur" }
        ],
        carType: [
          { required: true, message: "型号不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询行驶信息列表 */
    getList() {
      this.loading = true;
      listDrive(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.driveList = response.rows;
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
        driveId: null,
        startTime: null,
        spendTime: null,
        distance: null,
        oilConsumption: null,
        remainingMileage: null,
        totalMileage: null,
        carType: null,
        delFlag: null,
        dataScope: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.driveId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加行驶信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const driveId = row.driveId || this.ids
      getDrive(driveId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改行驶信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.driveId != null) {
            updateDrive(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDrive(this.form).then(response => {
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
      const driveIds = row.driveId || this.ids;
      this.$modal.confirm('是否确认删除行驶信息编号为"' + driveIds + '"的数据项？').then(function() {
        return delDrive(driveIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('trip/drive/export', {
        ...this.queryParams
      }, `drive_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
