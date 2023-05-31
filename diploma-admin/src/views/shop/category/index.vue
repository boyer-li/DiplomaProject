<template>
  <div class="app-container">
    <div class="block">
      <el-row>
        <el-col :span="24">
          <el-button type="success" size="mini" icon="el-icon-plus" @click.native="add" v-permission="['/category/edit']">{{ $t('button.add') }}</el-button>
           </el-col>
      </el-row>
    </div>


    <el-table
      :data="list"
      row-key="id"
     >
      <el-table-column label="Name">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column label="Icon">
        <template slot-scope="scope">
          <img v-if="scope.row.icon" :src="apiUrl+ '/file/getImgStream?idFile=' +scope.row.icon" style="width:50px;">
        </template>
      </el-table-column>
      <el-table-column label="Order">
        <template slot-scope="scope">
          {{scope.row.sort}}
        </template>
      </el-table-column>
      <el-table-column label="Show on home page">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.showIndex"
            @change="changeShowIndex(scope.row)"
          >
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="Banner Management">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-picture" @click.native="bannerMgr(scope.row)">Management</el-button>
        </template>
      </el-table-column>
      <el-table-column label="Property Management">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-s-operation" @click.native="attrKeyMgr(scope.row)">Management</el-button>
        </template>
      </el-table-column>
      <el-table-column label="Operation" >
        <template slot-scope="scope">
          <el-button type="text" size="mini" icon="el-icon-edit" @click="edit(scope.row)"  v-permission="['/category/edit']">{{ $t('button.edit') }}</el-button>
          <el-button type="text" size="mini" icon="el-icon-delete" @click="remove(scope.row)"  v-permission="['/category/edit']">{{ $t('button.delete') }}</el-button>
        </template>
      </el-table-column>
    </el-table>


    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="70%">
      <el-form ref="form" :model="form" label-width="150px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="Pictures">
              <el-upload
                drag
                multiple=false
                :action="uploadUrl"
                :headers="uploadHeaders"
                :before-upload="handleBeforeUpload"
                :on-success="handleUploadSuccess"
              >
                <img v-if="form.icon" :src="apiUrl+ '/file/getImgStream?idFile=' +form.icon" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Parent Menu">
              <el-select v-model="form.pid" placeholder="choose">
                <el-option
                  v-for="item in rootList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Name">
              <el-input v-model="form.name" minlength=1></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Remarks">
              <el-input  type="textarea" :rows="2" v-model="form.descript" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Order" >
              <el-input type="number" v-model="form.sort"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12"  v-show="form.showIndex">
            <el-form-item label="Whether to show in the home page">
              <el-radio-group v-model="form.showIndex">
                <el-radio :label="true">Yes</el-radio>
                <el-radio :label="false">No</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button type="primary" @click="save">{{ $t('button.submit') }}</el-button>
          <el-button @click.native="formVisible = false">{{ $t('button.cancel') }}</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
    <el-dialog
      title="Banner Management"
      :visible.sync="banner.visible"
      width="70%">
      <el-tabs v-model="banner.activeName">
        <el-tab-pane label="Management" name="first">
          <el-button type="primary" size="mini" icon="el-icon-plus" @click.native="addBanner">Add</el-button>
          <el-table :data="banner.list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>
            <el-table-column label="Title">
              <template slot-scope="scope">
                {{scope.row.title}}
              </template>
            </el-table-column>
            <el-table-column label="url">
              <template slot-scope="scope">
                {{scope.row.url}}
              </template>
            </el-table-column>
            <el-table-column label="Pictures">
              <template slot-scope="scope">
                <img :src="apiUrl+ '/file/getImgStream?idFile=' +scope.row.idFile" style="width:100px;">
              </template>
            </el-table-column>
            <el-table-column label="Operation">
              <template slot-scope="scope">
                <el-button type="danger" size="mini" icon="el-icon-delete" @click.native="bannerRemove(scope.row.id)">Delete</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="Preview" name="second">
          <el-carousel indicator-position="outside">
            <el-carousel-item v-for="item in banner.list" :key="item">
              <img :src="apiUrl+ '/file/getImgStream?idFile=' +item.idFile" style="width:100%;">
            </el-carousel-item>
          </el-carousel>
        </el-tab-pane>

      </el-tabs>
    </el-dialog>
    <el-dialog
      title="Property Management"
      :visible.sync="attrKey.visible"
      width="70%">
      <el-row>
        <el-col :span="24">
          <el-button type="success" size="mini" icon="el-icon-plus" @click.native="attrKeyAdd" v-permission="['/category/edit']">{{ $t('button.add') }}</el-button>
        </el-col>
      </el-row>
      <el-table :data="attrKey.list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>
        <el-table-column label="Property Name">
          <template slot-scope="scope">
            {{scope.row.attrName}}
          </template>
        </el-table-column>
        <el-table-column label="Operation">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="el-icon-edit" @click.native="attrKeyEdit(scope.row)">Editor</el-button>
            <el-button type="primary" size="mini" icon="el-icon-edit" @click.native="openAttrValDialog(scope.row)">Edit property values</el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click.native="attrKeyRemove(scope.row.id)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-dialog>
    <el-dialog
      title="Property Value Management"
      :visible.sync="attrVal.visible"
      width="50%">
      <el-row>
        <el-row>
          <el-col :span="24">
            <el-button type="success" size="mini" icon="el-icon-plus" @click.native="attrValAdd" v-permission="['/category/edit']">{{ $t('button.add') }}</el-button>
          </el-col>
        </el-row>
        <el-table :data="attrVal.list" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>
          <el-table-column label="Attribute Value">
            <template slot-scope="scope">
              {{scope.row.attrVal}}
            </template>
          </el-table-column>
          <el-table-column label="Operation">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" icon="el-icon-edit" @click.native="attrValEdit(scope.row)">Editor</el-button>
              <el-button type="danger" size="mini" icon="el-icon-delete" @click.native="attrValRemove(scope.row.id)">Delete</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </el-dialog>
  </div>
</template>

<script src="./category.js"></script>


<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/common.scss";
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 150px;
    line-height: 150px;
    text-align: center;
  }
</style>

