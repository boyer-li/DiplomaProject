<template>
  <div class="app-container">
    <div class="block">
      <el-row  :gutter="20">
        <el-col :span="6">
          <el-input v-model="listQuery.title" size="mini" placeholder="Title"></el-input>
        </el-col>

        <el-col :span="6">
          <el-button type="success" size="mini" icon="el-icon-search" @click.native="search">{{ $t('button.search') }}</el-button>
          <el-button type="primary" size="mini" icon="el-icon-refresh" @click.native="reset">{{ $t('button.reset') }}</el-button>
        </el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button type="success" size="mini" icon="el-icon-plus" @click.native="add" v-permission="['/banner/edit']">{{ $t('button.add') }}</el-button>

          <el-button type="success" size="mini" icon="el-icon-edit" @click.native="setBanner" v-show="shopCategory.show" :disabled="shopCategory.disabled">选中banner</el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click.native="remove" v-permission="['/banner/delete']">{{ $t('button.delete') }}</el-button>
        </el-col>
      </el-row>
    </div>


    <el-table :data="list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
              @current-change="handleCurrentChange"
    @row-click="clickRow">

      <el-table-column label="ID">
        <template slot-scope="scope">
          {{scope.row.id}}
        </template>
      </el-table-column>
      <el-table-column label="Title">
        <template slot-scope="scope">
          {{scope.row.title}}
        </template>
      </el-table-column>
      <el-table-column label="Category">
        <template slot-scope="scope">
          {{scope.row.type}}
        </template>
      </el-table-column>
      <el-table-column label="打开界面">
        <template slot-scope="scope">
          {{scope.row.page}}
        </template>
      </el-table-column>
      <el-table-column label="参数">
        <template slot-scope="scope">
          {{scope.row.param}}
        </template>
      </el-table-column>
      <el-table-column label="Pictures">
        <template slot-scope="scope">
          <img :src="scope.row.img" style="width:200px;">
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="70%">
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-row>
          <el-col :span="12">
            <el-row>

              <el-col :span="12">
                <el-form-item label="banner图片">
                  <el-upload
                    class="upload-demo"
                    drag
                    multiple=false
                    :action="uploadUrl"
                    :headers="uploadHeaders"
                    :before-upload="handleBeforeUpload"
                    :on-success="handleUploadSuccess"
                  >
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">Upload Picture</div>
                  </el-upload>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="12">
            <el-row>
              <el-col :span="24">
                <el-form-item label="Title" prop="title">
                  <el-input v-model="form.title" minlength=1></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="打开的界面">
                  <el-input v-model="form.page"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="参数" prop="param">
                  <el-input type="textarea" v-model="form.param"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="类型">
                  <el-select v-model="form.type" placeholder="choose">
                    <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
        </el-row>





        </el-row>
        <el-form-item>
          <el-button type="primary" @click="save">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
  </div>
</template>

<script src="./banner.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
</style>
