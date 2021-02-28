<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 商品上下架
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr10"
                    @click="delAllSelection"
                >批量删除</el-button>
              <el-input v-model="query.name" placeholder="商品名" class="handle-input mr10"></el-input>
              <el-input v-model="query.category" placeholder="分类ID" maxlength=1 class="handle-input mr10"></el-input>
              <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
              <el-button type="primary" icon="el-icon-lx-add" @click="newShop">上架商品</el-button>
            </div>
            <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="75" align="center"></el-table-column>
                <el-table-column prop="good_id" label="商品ID" width="75" align="center"></el-table-column>
                <el-table-column prop="name" label="商品名" align="center"></el-table-column>
                <el-table-column prop="category_id" label="所属分类ID" width="110" align="center"></el-table-column>
                <el-table-column prop="newhot" label="热门新品(1热门,0不热门)" width="200" align="center"></el-table-column>
              <el-table-column prop="pushtime" label="上架时间" align="center"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
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
            <div class="allshop">
                <a :total="pageTotal"
                >本页共{{currTotal}}条;仓库商品共:{{pageTotal}}条</a>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="商品名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>

              <el-form-item label="是否热门" :label-width="formLabelWidth">
                <el-select v-model="form.newhot" placeholder="状态">
                  <el-option label="是" value="1"></el-option>
                  <el-option label="否" value="0"></el-option>
                </el-select>
              </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>


      <el-dialog title="上架商品" :visible.sync="editVisible2" width="30%">
        <el-form ref="form" :model="newform" label-width="90px">
          <el-form-item label="商品名">
            <el-input v-model="newform.name"></el-input>
          </el-form-item>
          <el-form-item label="所属分类ID">
            <el-input v-model="newform.category_id" maxlength=1></el-input>
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
    import { fetchData, getGoodByName, pushGood, updGood } from '../../api/index';
    import { delGood, delMulGood, getGood, getGoodByCategory, updSuggFoot } from '../../api';
export default {
    name: 'GoodUpd',
    data() {
        return {
            query: {
                name: '',
                category: '',
                pageIndex: 1,
                pageSize: 24
            },
            formLabelWidth: '120px',
            tableData: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            editVisible2: false,
            pageTotal: 0,
            currTotal: 0,
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
            getGood(this.query).then(res => {
                console.log(res);
                this.tableData = res.list;
                this.pageTotal = res.pageTotal || 50;
                this.currTotal = res.currTotal || 50;
            });
        },
        // 触发搜索按钮
        handleSearch() {
            // this.$set(this.query, 'pageIndex', 1);
            if(this.query.category&&this.query.name){
                this.$message.error("输入时商品名和分类ID只能二选一");
            }
            else if(this.query.category){
                getGoodByCategory(this.query).then(res=>{
                    this.tableData = res.list;
                    this.pageTotal = res.pageTotal || 50;
                    this.currTotal = res.currTotal || 50;
                });
            }
            else if(this.query.name){
              getGoodByName(this.query).then(res=>{
                  this.tableData = res.list;
                  this.pageTotal = res.pageTotal || 50;
                  this.currTotal = res.currTotal || 50;
              });
            }
            else{
                this.getData();
            }
        },
        newShop(){
            this.editVisible2 = true;
        },
        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(() => {
                    delGood(this.tableData[index]);
                    this.$message.success('删除成功');
                    this.tableData.splice(index, 1);
                }).catch(() => {});
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        delAllSelection() {
            const length = this.multipleSelection.length;
            let str = '';
            this.delList = this.delList.concat(this.multipleSelection);
            for (let i = 0; i < length; i++) {
                str += this.multipleSelection[i].name + ' ';
            }
            this.$message.success(`删除了${str}`);

            delMulGood(this.multipleSelection);
            this.multipleSelection = [];
            this.$router.go(0);
        },
        // 编辑操作
        handleEdit(index, row) {
            this.idx = index;
            this.form = row;
            this.editVisible = true;
        },
        // 保存编辑
        saveEdit() {

            updGood(this.tableData[this.idx]).then(res=>{
                if(res.status===200){
                    this.editVisible = false;
                    this.$message.success(`修改第 ${this.idx + 1} 行成功`);
                    this.$set(this.tableData, this.idx, this.form);
                }
                else {
                    this.$message.error(`商品名不能为空！`)
                }
            })

        },
        saveEdit2() {
            this.editVisible2 = false;
            if(this.newform.name && this.newform.category_id) {
                pushGood(this.newform).then(res => {
                    if (res.status === 200) {
                        this.newform.name=this.newform.category_id='';
                        this.$message.success(`上架成功`);
                    } else {
                        this.$message.error(`商品已存在`)
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

  .allshop {
    margin-top: 20px;
    margin-right: 50px;
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
