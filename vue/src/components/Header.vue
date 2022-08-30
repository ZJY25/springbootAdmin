<template>
<!--  template中只能有一个元素-->

<!--  justify-content 使得布局分列左右-->
  <div style="line-height: 60px; display: flex; justify-content: space-between">

    <div style="flex: 1; text-align: left">
      <span :class="collapseBtnClass" style="cursor: pointer; padding-left: 10px" @click="collapse"></span>
  <!--    面包屑-->
      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-dropdown style="width: 70px; cursor: pointer">
      <span>admin</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown" >
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">个人信息</el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>

</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Function
  },

  //vuex
  computed: {
    currentPathName() {
      return this.$store.state.currentPathName;       //需要监听的数据
    }
  },
  watch: {
    currentPathName (newVal, oleVal) {
      console.log(newVal)
    }
  },


}
</script>

<style scoped>

</style>