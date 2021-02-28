<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 首页商品推荐
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
              <el-button type="primary" icon="el-icon-lx-add" @click="newShop" >上架</el-button>
            </div>
            <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange">
                <el-table-column prop="name" label="商品名" width="240" align="center"></el-table-column>

                <el-table-column label="推荐页图片(400*400)" align="center">
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.imgUrl"
                            :preview-src-list="[scope.row.imgUrl]"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="price" label="爆款价格" width="80" align="center"></el-table-column>

                <el-table-column label="操作" width="280" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            @click="handleEdit(scope.$index, scope.row)"
                        >编辑</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="handleDelete(scope.$index, scope.row)"
                        >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageIndex"
                    :page-size="query.pageSize"
                    :total="pageTotal"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="40%">
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="图片URL">
                    <el-input v-model="form.imgUrl"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>


      <el-dialog title="上架推荐商品" :visible.sync="editVisible2" width="40%">
        <el-form ref="form" :model="newform" label-width="130px">
          <el-form-item label="商品ID">
            <el-input v-model="newform.goodid" maxlength=3></el-input>
          </el-form-item>
          <el-form-item label="推荐商品图片Url">
            <el-input v-model="newform.imgUrl"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible2 = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit2">确 定</el-button>
        </span>
      </el-dialog>
    </div>
</template>

<script>
    import { delSuggFoot, getSugFoot, pushSuggFoot, updSuggFoot } from '../../api';
export default {
    name: 'Suggest',
    data() {
        return {
            query: {
                name: '',
                category:'',
                pageIndex: 1,
                pageSize: 20
            },
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            editVisible2: false,
            pageTotal: 0,
            form: {},
            newform: {},
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
            getSugFoot(this.query).then(res => {
                console.log(res);
                this.tableData = res.list;
                this.pageTotal = res.pageTotal || 50;
            });
        },
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
            if(this.query.name){

            }
            else
              this.getData();
        },
        newShop(){
            this.editVisible2 = true;
        },
        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    delSuggFoot(this.tableData[index])
                    this.$message.success('删除成功');
                    this.tableData.splice(index, 1);
                })
                .catch(() => {});
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        // 编辑操作
        handleEdit(index, row) {
            this.idx = index;
            this.form = row;
            this.editVisible = true;
        },
        // 保存编辑
        saveEdit() {
            updSuggFoot(this.form);
            this.editVisible = false;
            this.$message.success(`更新第 ${this.idx + 1} 行图片成功`);
            this.$set(this.tableData, this.idx, this.form);

        },
        saveEdit2() {
            this.editVisible2 = false;
            if(this.newform.goodid && this.newform.imgUrl) {
                pushSuggFoot(this.newform).then(res => {
                    if (res.status === 200) {
                        this.$message.success(`上架成功`);
                        this.newform.goodid=this.newform.imgUrl=''
                        this.getData();
                    }
                    else if(res.status === 404) {
                        this.$message.error(`商品ID不存在,只能推荐已有商品`);
                    }
                    else{
                      this.$message.error(`商品已被推荐，勿重复上架`)
                    }
                });
            }
            else {
                this.$message.error("上架失败,信息没有填写完整!")
            }
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
