<template>
    <div>
      <div class="container">
        <div class="handle-box">
          <el-button type="primary" icon="el-icon-search" @click="sugbigpush">上架</el-button>
        </div>
            <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header">

                <el-table-column prop="name" label="商品名" width="325"></el-table-column>
                <el-table-column prop="category_id" label="所属分类ID" width="325"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                              type="text"
                              icon="el-icon-delete"
                              class="red"
                              @click="handleDelete(scope.$index, scope.row)">
                          删除</el-button>
                    </template>
                </el-table-column>
            </el-table>



        <el-dialog title="上架推荐商品" :visible.sync="editVisible" width="30%">
          <el-form ref="form" :model="newform" label-width="70px">
            <el-form-item label="商品ID">
              <el-input v-model="newform.goodid" maxlength=3 ></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
          </span>
        </el-dialog>

    </div>
    </div>
</template>

<script>

    import { delSuggBig, getSugBig, pushSuggBig} from '../../api';

export default {
    props: ['kind'],
    name: 'SugBigChild',
    data() {
        return {
            query: {
                pageIndex: 1,
                pageSize: 8,
            },
            tableData:[],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            newform:{},
            pageTotal: 0,
            idx: -1,
            id: -1
        };
    },

    created(){
      this.getData(this.kind)
    },
    methods: {
        getData(kind) {
            getSugBig(kind).then(res=>{
                console.log(res);
                this.tableData = res.list;
                this.pageTotal = res.pageTotal || 50;
            })
        },
        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(() => {
                    delSuggBig(this.tableData[index]);
                    this.$message.success('删除成功');
                    this.tableData.splice(index, 1);
            }).catch(() => {});
        },
        sugbigpush(){
            this.editVisible = true;
        },
        saveEdit() {
            pushSuggBig(this.newform.goodid,this.kind).then(res=>{
                if(res.status === 500){
                    this.$message.error("商品已上架,请勿重复推荐");
                }
                else if(res.status === 400){
                    this.$message.error("上架类目不符合要求");
                }
                else
                {
                    this.editVisible = false;
                    this.$message.success(`上架成功`);
                    this.newform.goodid=''
                    this.getData(this.kind)
                }
            });

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
