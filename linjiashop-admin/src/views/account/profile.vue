<template>
  <div class="app-container">
    <div class="user">
      <strong>{{ user.name }}</strong><br>
      <small>{{ user.dept }} &nbsp;&nbsp; {{ user.roles }}</small>
    </div>
    <el-row class="user-content" style="">
      <el-col :span="6" class="profile">
        <img class="user-avatar" src="@/assets/img/avatar.gif"><br>
        <p><span class="title"><i class="el-icon-phone"></i>&nbsp;&nbsp;{{ user.phone }}</span></p>
        <p><span class="title"><i class="el-icon-message"></i>&nbsp;&nbsp;{{ user.email }}</span></p>
        <p><span class="title"><i class="el-icon-open"></i>&nbsp;&nbsp;{{ user.status == '1'?'启用' : '禁用' }}</span></p>
        <p><span class="title"><i class="el-icon-location-outline"></i>&nbsp;&nbsp;上海市浦东大道290弄</span></p>
      </el-col>
      <el-col :span="18" style="padding-left:10px;">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="Personal Information" name="profile"></el-tab-pane>
          <el-tab-pane label="Recent Activities" name="timeline"></el-tab-pane>
          <el-tab-pane label="Change password" name="updatePwd"></el-tab-pane>
        </el-tabs>
        <el-form  label-width="80px">
          <h3>Basic Information</h3>
          <el-form-item label="Name">
            <span>{{ user.name }}</span>
          </el-form-item>
          <el-form-item label="Gender">
            <span> {{ user.sex = 1 ? 'Male' : 'Female' }}</span>
          </el-form-item>
          <el-form-item label="Birthday">
            <span> {{ user.birthday }}</span>
          </el-form-item>
          <h3>Contact Information</h3>
          <el-form-item label="Cell phone">
            <span>{{ user.phone }}</span>
          </el-form-item>
          <el-form-item label="Mailbox">
            <span> {{ user.email }}</span>
          </el-form-item>
          <el-form-item label="Address">
            <span> Minsk</span>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>


  </div>
</template>

<script src="./profile.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>

