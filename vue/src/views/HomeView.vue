<template>
  <el-container style="min-height: 100vh">

    <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
      <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
               background-color="rgb(48, 65, 86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="isCollapse"
      >
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
          <b style="color: white" v-show="logoTextShow">后台管理系统</b>
        </div>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-message"></i>
            <span slot="title">导航一</span>
          </template>
          <el-menu-item-group title="分组2">
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">选项4</template>
            <el-menu-item index="1-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">导航二</span>
          </template>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span slot="title">导航三</span>
          </template>
          <el-submenu index="3-4">
            <template slot="title">选项4</template>
            <el-menu-item index="3-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
<!--        justify-content控制布局-->
      <el-header style=" padding-left: 0; font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px;display: flex; justify-content: space-between">
        <div style="width: 50px; font-size: 20px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <span>admin</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown" >
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">个人信息</el-dropdown-item>
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>

      <el-main>
        <div style="margin-bottom: 30px">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>用户管理</el-breadcrumb-item>
          </el-breadcrumb>
        </div>


        <div style="margin: 10px 0; float: left">
          <el-input style="width: 200px" placeholder="请输入用户名" suffix-icon="el-icon-user-solid" v-model="username"></el-input>
          <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" v-model="email" class="ml-5"></el-input>
          <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position" v-model="address" class="ml-5"></el-input>
          <el-button class="ml-10" type="primary" @click="search">搜索</el-button>
          <el-button class="ml-10" type="warning" @click="reload">重置</el-button>
        </div>
        <div style="margin: 10px 10px; float: left">
          <el-button type="primary" @click="dialogFormVisible = true">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
          <el-button type="danger">批量删除 <i class="el-icon-remove-outline"></i></el-button>
          <el-button type="primary">导入 <i class="el-icon-bottom"></i></el-button>
          <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>
          <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form :model="inputForm">
              <el-form-item label="用户名" :label-width="formLabelWidth" style="padding-right: 80px">
                <el-input v-model="inputForm.username" autocomplete="off" ></el-input>
              </el-form-item>
              <el-form-item label="昵称" :label-width="formLabelWidth" style="padding-right: 80px">
                <el-input v-model="inputForm.nickname" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" :label-width="formLabelWidth" style="padding-right: 80px">
                <el-input v-model="inputForm.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="电话" :label-width="formLabelWidth" style="padding-right: 80px">
                <el-input v-model="inputForm.phone" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="地址" :label-width="formLabelWidth" style="padding-right: 80px">
                <el-input v-model="inputForm.address" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </div>
          </el-dialog>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
          <el-table-column prop="id" label="ID" width="180">
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
      </el-main>

    </el-container>
  </el-container>
</template>

<script>

export default {
  name: 'Home',
  data() {
    return {
      tableData: [],                //表格数据
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
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      headerBg: 'headerBg'
    }
  },
  created() {
    this.load();
  },
  methods: {
    load(){             //加载数据


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
          pageNum: this.pageNum,              //查询参数
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address
        }
      }).then(res =>{                   //这边与fetch不同，不用转化为json
        console.log(res)

        this.tableData = res.records;
        this.total = res.total;
      })
    },
    reload() {  //重置
      this.username = ""
      this.email = ""
      this.address = ""
      this.load()
    },

    save() {                //增添或修改一条数据
      this.dialogFormVisible = false;
      this.request.post("user", this.inputForm).then(res =>{
        if (res) {
          this.$message.success("保存成功")
        }else {
          this.$message.error("保存失败")
        }
        this.load()
      })
    },

    edit(row) {                  //编辑一条数据触发器
      this.dialogFormVisible = true
      this.inputForm = JSON.parse(JSON.stringify(row));      //scope传入的row数据,使用parse 防止在保存前修改列表中数据
    },

    del(id) {
      this.request.delete("user/" + id).then(res =>{
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
          this.load()
        }
      })
    },


    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    search() {
      this.load();                        //更新数据
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
    }
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>
