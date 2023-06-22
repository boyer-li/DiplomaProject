<template>
  <div class="app-container">
    <div class="block">
      <el-row>
        <el-col :span="24">
          <el-button type="primary" @click="save" size="mini">{{active==3?"Submit":"Next"}}</el-button>
          <el-button @click="prev" v-show="active>0" size="mini">Previous</el-button>
        </el-col>
      </el-row>
    </div>
    <br>
    <div class="block">
      <el-steps :active="active" finish-status="success">
        <el-step title="Basic Information"></el-step>
        <el-step title="Product Album"></el-step>
        <el-step title="Product Details"></el-step>
        <el-step title="Uploaded information"></el-step>
      </el-steps>
    </div>


    <el-form  :model="form"   label-width="150px" v-show="active==0">
      <el-row>
        <el-col :span="24">
          <el-form-item label="Name">
            <el-input v-model="form.name" minlength=1></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Product Description">
            <el-input v-model="form.descript" minlength=1></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Category">
            <treeselect v-model="form.idCategory"  :options="categories"  placeholder="请选择商品类别"/>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="New or old">
            <el-radio-group v-model="form.isNew">
              <el-radio :label="true">Yes</el-radio>
              <el-radio :label="false">No</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Hot or not">
            <el-radio-group v-model="form.isHot">
              <el-radio :label="true">Yes</el-radio>
              <el-radio :label="false">No</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>


      </el-row>
    </el-form>

    <el-form  label-width="150px" v-show="active==1">
      <el-row>

        <el-col :span="24">
          <el-form-item label="Product Thumbnails">
            <el-upload
              :headers="uploadHeaders"
              :action="uploadUrl"
              :show-file-list="false"
              :on-success="handleUploadPicSuccess"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif">
              <img v-if="form.pic" :src="apiUrl+ '/file/getImgStream?idFile=' +form.pic" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"/>
            </el-upload>

          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="Product Album">
              <el-upload
                list-type="picture-card"
                :action="uploadUrl"
                :headers="uploadHeaders"
                :file-list="galleryList"
                :on-remove="handleRemove"
                :on-success="handleUploadGallerySuccess">
                <i class="el-icon-plus"></i>
              </el-upload>

          </el-form-item>
        </el-col>


      </el-row>
    </el-form>

    <div :class="{fullscreen:fullscreen}" class="tinymce-container editor-container" v-show="active==2">
      <textarea id="tinymceId" class="tinymce-textarea"/>
      <div class="editor-custom-btn-container">
        <editorImage color="#1890ff" class="editor-upload-btn" @successCBK="imageSuccessCBK"/>
      </div>
    </div>
    <el-form  label-width="150px" v-show="active==3">
      <el-row>



        <el-col :span="24">
          <el-form-item label="Product specifications">
            <el-radio class="radio" v-model="spec" label="one">Single specification</el-radio>
            <el-radio class="radio" v-model="spec" label="more" v-show="attrKeyList.length>0">Multiple specifications</el-radio>
          </el-form-item>
        </el-col>
        <el-col :span="24" v-if="spec == 'one'">
          <el-form-item label="Inventory">
            <el-input-number v-model="form.stock" :min="0" :max="100000"></el-input-number>
          </el-form-item>
          <el-form-item label="Market price(ruble)">
            <el-input-number v-model="form.marketingPrice" :min="0" :max="10000000"></el-input-number>
          </el-form-item>
          <el-form-item label="Price(ruble)">
            <el-input-number v-model="form.price" :min="0" :max="10000000"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col  :offset="3" :span="18" v-else style="overflow: auto; text-align: center;">
          <el-button type="primary" @click="openAddSkuForm" style="margin-bottom: 10px;">Add specifications</el-button>
          <el-table
            :data="skuList"
            style="margin-bottom: 20px;"
            :row-class-name="tableRowClassName">
            <el-table-column
              prop="codeName"
              label="Specification">
            </el-table-column>
            <el-table-column
              prop="marketingPrice"
              label="Market Price">
            </el-table-column>

            <el-table-column
              prop="price"
              label="Price(ruble)">
            </el-table-column>

            <el-table-column
              prop="stock"
              label="Inventory">
            </el-table-column>

            <el-table-column label="Operation">
              <template slot-scope="scope">
                <el-button
                  size="small"
                  type="danger"
                  @click="removeSku(scope.$index)">Delete
                </el-button>
              </template>
            </el-table-column>

          </el-table>
        </el-col>
      </el-row>
    </el-form>
    <el-dialog title="SKU Configuration" :visible.sync="specDialogFormVisible">
      <el-form :model="skuForm">

            <el-form-item label="Selected specifications" label-width="100px" prop="specs">
            <el-tag
              v-for="tag in tags"
              :key="tag.id"
              closable
              :type="tag.id"
              @close="removeTag(tag)">
              {{tag.attrVal}}
            </el-tag>
            </el-form-item>

        <el-row>
          <el-col :span="8">
            <el-form-item label="Property Name" label-width="100px"  >
              <el-select
                v-model="attrKeySel"
                filterable
                allow-create
                default-first-option
                @change="changeAttrKey"
                placeholder="Property Name">
                <el-option
                  v-for="item in attrKeyList"
                  :key="item.id"
                  :label="item.attrName"
                  :value="item.id">
                </el-option>
              </el-select>

            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="Attribute Value" label-width="100px"  >
              <el-select
                v-model="attrValSel"
                filterable
                allow-create
                default-first-option
                placeholder="Attribute Value">
                <el-option
                  v-for="item in attrValListSel"
                  :key="item.id"
                  :label="item.attrVal"
                  :value="item.id">
                </el-option>
              </el-select>

            </el-form-item>
          </el-col>
          <el-col :offset="2" :span="6">
            <el-button type="success" @click="addToTag">Add specifications</el-button>
          </el-col>
        </el-row>

        <el-form-item label="Market price (ruble)" label-width="100px">
          <el-input-number v-model="skuForm.marketingPrice" :min="0" :max="100000"></el-input-number>
        </el-form-item>
        <el-form-item label="Price(分)" label-width="100px">
          <el-input-number v-model="skuForm.price" :min="0" :max="1000000"></el-input-number>
        </el-form-item>
        <el-form-item label="Inventory" label-width="100px">
          <el-input-number v-model="skuForm.stock" :min="0" :max="100000"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="default" @click="closeAddSkuForm">Cancel</el-button>
        <el-button type="primary" @click="addSku">Confirm</el-button>
      </div>
    </el-dialog>
    <br>
    <div class="block">
      <el-row>
        <el-col :span="24">
          <el-button type="primary" @click="save" size="mini">{{active==3?"Submit":"Next"}}</el-button>
          <el-button @click="prev" v-show="active>0" size="mini">Previous</el-button>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script src="./goodsEdit.js"></script>


<style scoped>

  .tinymce-container {
    position: relative;
    line-height: normal;
  }

  .tinymce-container >>> .mce-fullscreen {
    z-index: 10000;
  }

  .tinymce-textarea {
    visibility: hidden;
    z-index: -1;
  }

  .editor-custom-btn-container {
    position: absolute;
    right: 4px;
    top: 4px;
    /*z-index: 2005;*/
  }

  .fullscreen .editor-custom-btn-container {
    z-index: 10000;
    position: fixed;
  }

  .editor-upload-btn {
    display: inline-block;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }
  .avatar {
    width: 145px;
    height: 145px;
    display: block;
  }

  .add_attr_key_row {
    height: 0;
    overflow: hidden;
    transition: all 400ms;
  }

  .showEdit {
    height: 185px;
  }

  .add_attr_key_button {
    text-align: center;
    line-height: 40px;
    transition: all 400ms;

  }
</style>

