<template>
  <div>
<!--    搜索框-->
    <div style="margin: 10px 0; float: left">
      <el-input style="width: 200px" placeholder="请输入用户名" suffix-icon="el-icon-user-solid" v-model="username"></el-input>
      <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" v-model="email" class="ml-5"></el-input>
      <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position" v-model="address" class="ml-5"></el-input>
      <el-button class="ml-10" type="primary" @click="search">搜索</el-button>
      <el-button class="ml-10" type="warning" @click="reload">重置</el-button>
    </div>
<!--    增删导入导出-->
    <div style="margin: 10px 10px; float: left">
      <el-button type="primary" @click="insert">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm class="ml-5"
                     confirm-button-text='确定'
                     cancel-button-text='再想想'
                     icon="el-icon-info"
                     icon-color="red"
                     title="您确定删除吗？"
                     @confirm="deleteBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      <el-button type="primary" >导出 <i class="el-icon-top"></i></el-button>
      <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="inputForm" ref="inputForm">
          <el-form-item label="用户名" prop="username" :label-width="formLabelWidth" style="padding-right: 80px">
            <el-input v-model="inputForm.username" autocomplete="off" ></el-input>
          </el-form-item>
          <el-form-item label="昵称" prop="nickname" :label-width="formLabelWidth" style="padding-right: 80px">
            <el-input v-model="inputForm.nickname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email" :label-width="formLabelWidth" style="padding-right: 80px">
            <el-input v-model="inputForm.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="phone" :label-width="formLabelWidth" style="padding-right: 80px">
            <el-input v-model="inputForm.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address" :label-width="formLabelWidth" style="padding-right: 80px">
            <el-input v-model="inputForm.address" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </div>
      </el-dialog>
    </div>
<!--    表格主体-->
    <el-table
        :data="tableData"
        border
        stripe
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="50">
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="150">
      </el-table-column>
      <el-table-column prop="nickname" label="昵称" width="150">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" width="200">
      </el-table-column>
      <el-table-column prop="phone" label="电话">
      </el-table-column>
      <el-table-column prop="address" label="地址">
      </el-table-column>
      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click = "edit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm class="ml-5"
                         confirm-button-text='确定'
                         cancel-button-text='再想想'
                         icon="el-icon-info"
                         icon-color="red"
                         title="您确定删除吗？"
                         @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference" >删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
<!--    分页模块-->
    <div class="pd-10">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return{
      tableData: [],                //表格数据
      multipleSelection: [],           //多选内容
      total: 0,                     //数据总数
      pageNum: 1,                   //页码
      pageSize:10,                  //页面大小
      username: "",                 //用户名
      email: "",                    //邮箱
      address: "",                  //地址
      dialogFormVisible: false,    //表单可见
      inputForm: {
        username: '',
        nickname: '',
        email: '',
        phone: '',
        address: '',
      },
      formLabelWidth: '100px',
    }
  },
  created() {
    this.load();
  },
  methods: {
    load(){                               //加载数据


      //请求分页查询数据

      //fetch方法，较为复杂，无法拼接
      // fetch("http://localhost:9999/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&username="+this.username+"&address=").then(res => res.json()).then(res => {
      //   console.log(res);
      //   this.tableData = res.records;
      //   this.total = res.total;
      // })

      //使用axios
      this.request.get("user/page", {
        params: {
          pageNum: this.pageNum,          //查询参数
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address
        }
      }).then(res =>{                     //这边与fetch不同，不用转化为json
        console.log("res：" + res)

        this.tableData = res.records;
        this.total = res.total;
      })
    },
    insert() {                            //增
      this.dialogFormVisible = true;
    },

    del(id) {                             //删一条
      this.request.delete("user/del/" + id).then(res =>{
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
          this.load()
        }
      })
    },

    handleSelectionChange(val) {          //获取该行id
      console.log(val);
      this.multipleSelection = val;       //赋值给multipleSelection
    },

    deleteBatch(){                        //批量删除
      let ids = this.multipleSelection.map(v => v.id)       //[{}{}{}]->[1,2,3]，数据扁平化处理
      this.request.post("user/del/batch", ids).then(res =>{
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
          this.load()
        }
      })
    },

    edit(row) {                           //改
      this.dialogFormVisible = true
      this.inputForm = JSON.parse(JSON.stringify(row));      //scope传入的row数据,使用parse 防止在保存前修改列表中数据
    },

    search() {                            //查
      this.pageNum = 1;                   //使得搜索结果从第一页展示
      this.load();                        //更新数据
    },

    reload() {                            //重置查询
      this.pageNum = 1
      this.username = ""
      this.email = ""
      this.address = ""
      this.load()
    },

    save() {                              //表单保存，增添或修改一条数据
      this.dialogFormVisible = false;
      this.request.post("user", this.inputForm).then(res =>{
        if (res) {
          this.$message.success("保存成功")
        }else {
          this.$message.error("保存失败")
        }
        this.$refs.inputForm.resetFields();
        this.load()
      })
    },


    handleSizeChange(pageSize) {          //页面大小变化触发器
      console.log(pageSize);
      this.pageSize = pageSize;           //更新数据
      this.load();                        //加载数据
    },
    handleCurrentChange(pageNum) {        //换页触发器
      console.log(pageNum);
      this.pageNum = pageNum;             //更新数据
      this.load();                        //加载数据
    },
  }
}
</script>

<style scoped>

</style>