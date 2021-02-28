<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 商品图片管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
              <el-input v-model="query.name" placeholder="商品名" class="handle-input mr10"></el-input>
              <el-input v-model="query.category" placeholder="分类ID" maxlength=1 class="handle-input mr10"></el-input>
              <el-button type="primary" icon="el-icon-search"  @click="handleSearch">搜索</el-button>
            </div>
            <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                >
                <el-table-column prop="good_id" label="商品ID" width="65" align="center"></el-table-column>
                <el-table-column prop="name" label="商品名" width="150" align="center"></el-table-column>

                <el-table-column label="首页分类图标(40*40)" align="center">
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.icon"
                            :preview-src-list="[scope.row.icon]"
                        ></el-image>
                    </template>
                </el-table-column>

              <el-table-column label="顶部弹出图(160*110)" align="center">
                <template slot-scope="scope">
                  <el-image
                          class="table-td-thumb"
                          :src="scope.row.imageHead"
                          :preview-src-list="[scope.row.imageHead]"
                  ></el-image>
                </template>
              </el-table-column>

              <el-table-column label="首页商品图(200*200)" align="center">
                <template slot-scope="scope">
                  <el-image
                          class="table-td-thumb"
                          :src="scope.row.imageFoot"
                          :preview-src-list="[scope.row.imageFoot]"
                  ></el-image>
                </template>
              </el-table-column>

              <el-table-column label="购物车图(800*800)" align="center">
                <template slot-scope="scope">
                  <el-image
                          class="table-td-thumb"
                          :src="scope.row.image"
                          :preview-src-list="[scope.row.image]"
                  ></el-image>
                </template>
              </el-table-column>

              <el-table-column>
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)"
                        >编辑前三种图</el-button>
                    </template>
                </el-table-column>

              <el-table-column>
                <template slot-scope="scope">
                  <el-button
                          type="text"
                          icon="el-icon-edit"
                          @click="handleEditShop(scope.$index, scope.row)"
                  >编辑购物车图</el-button>
                </template>
              </el-table-column>

            </el-table>
          <div class="allshoppic">
            <a :total="pageTotal"
            >本页共{{currTotal}}条;仓库商品共:{{pageTotal}}条</a>
          </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑1" :visible.sync="editVisible" width="50%">
            <el-form ref="form" :model="form" label-width="120px">
                <el-form-item label="目录图URL(下同)">
                    <el-input v-model="form.icon"></el-input>
                </el-form-item>
                <el-form-item label="首页顶弹出图">
                    <el-input v-model="form.imageHead"></el-input>
                </el-form-item>
                <el-form-item label="首页商品大图">
                  <el-input v-model="form.imageFoot"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确定修改</el-button>
            </span>
        </el-dialog>


      <!-- 编辑弹出框 -->
      <el-dialog title="编辑2" :visible.sync="editVisible2" width="50%">
        <el-form ref="formShop" :model="formShop" label-width="120px">
          <el-form-item label="购物车主图(下同)">
            <el-input v-model="formShop.pic1"></el-input>
          </el-form-item>
          <el-form-item label="购物车图2">
            <el-input v-model="formShop.pic2"></el-input>
          </el-form-item>
          <el-form-item label="购物车图3">
            <el-input v-model="formShop.pic3"></el-input>
          </el-form-item>
          <el-form-item label="购物车图4">
            <el-input v-model="formShop.pic4"></el-input>
          </el-form-item>

        </el-form>
        <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible2 = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit2">确定修改</el-button>
        </span>
      </el-dialog>


    </div>
</template>

<script>
    import { fetchData, getGoodByCategory, getGoodByName } from '../../api/index';
    import { getGoodPic, getGoodPicByCategory, getGoodPicByName, updGoodPic } from '../../api';
export default {
    name: 'GoodPic',
    data() {
        return {
            query: {
                name: '',
                category:'',
                pageIndex: 1,
                pageSize: 24
            },
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            editVisible2: false,
            pageTotal: 0,
            form: {},
            formShop:{},
            idx: -1,
            id: -1
        };
    },
    created() {
        this.getData();
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {
            getGoodPic(this.query).then(res => {
                console.log(res);
                this.tableData = res.list;
                this.pageTotal = res.pageTotal;
                this.currTotal = res.currTotal;
            });
        },
        // 触发搜索按钮
        handleSearch() {
            if(this.query.category&&this.query.name){
                this.$message.error("输入时商品名和分类ID只能二选一");
            }
            else if(this.query.category){
                getGoodPicByCategory(this.query).then(res=>{
                    this.tableData = res.list;
                    this.pageTotal = res.pageTotal || 50;
                    this.currTotal = res.currTotal || 50;
                });
            }
            else if(this.query.name){
                getGoodPicByName(this.query).then(res=>{
                    this.tableData = res.list;
                    this.pageTotal = res.pageTotal || 50;
                    this.currTotal = res.currTotal || 50;
                });
            }
            else{
                this.getData();
            }
        },
        // 编辑操作
        handleEdit(index, row) {
            this.idx = index;
            this.form = row;
            this.editVisible = true;
        },

        handleEditShop(index, row){
            this.idx = index;
            this.formShop = row;
            this.editVisible2 = true;
        },
        // 保存编辑
        saveEdit() {
            this.editVisible = false;
            this.$message.success(`修改第 ${this.idx + 1} 行成功`);
            this.$set(this.tableData, this.idx, this.form);
            updGoodPic(this.tableData[this.idx])
        },

        saveEdit2() {
            this.editVisible2 = false;
            this.$message.success(`修改第 ${this.idx + 1} 行成功`);
            this.$set(this.tableData, this.idx, this.formShop);
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageIndex', val);
            this.getData();
        }
    }
};
</script>
<style scoped>
  .allshoppic {
    margin-top: 20px;
    margin-right: 30px;
    text-align: right;
  }
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
