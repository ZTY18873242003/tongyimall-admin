<template>
    <div class="">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-copy"></i> 首页商品大类推荐</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <el-tabs v-model="message">
                <el-tab-pane :label="`手机(限制分类ID:1,后同)`" name="first">
                  <child :kind=1></child>
                </el-tab-pane>

                <el-tab-pane :label="`家电(2,4)`" name="second">
                  <child :kind=2></child>
                </el-tab-pane>

                <el-tab-pane :label="`智能(5,9)`" name="third">
                  <child :kind=3></child>
                </el-tab-pane>

              <el-tab-pane :label="`搭配(7)`" name="forth">
                <child :kind=4></child>
              </el-tab-pane>

              <el-tab-pane :label="`配件(6)`" name="fifth">
                <child :kind=5></child>
              </el-tab-pane>

              <el-tab-pane :label="`周边(5,8)`" name="sixth">
                <child :kind=6></child>
              </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
  //引入子组件表格
import SugBigChild from './SugBigChild';
    export default {
        name: 'SuggestBig',
        components:{
         child:SugBigChild
        },
        data() {
            return {
                message: 'first',
                showHeader: false,
                phone: [{
                    date: '2018-04-19 20:00:00',
                    title: '【系统通知】该系统将于今晚凌晨2点到5点进行升级维护',
                    id: 1,
                    name: "ZTY",
                },
                    {
                    date: '2018-04-19 21:00:00',
                    title: '今晚12点整发大红包，先到先得',
                    id: 1,
                    name: "ZTY",
                }]
            }
        },
        methods: {
            handleRead(index) {
                const item = this.unread.splice(index, 1);
                console.log(item);
                this.read = item.concat(this.read);
            },
            handleDel(index) {
                const item = this.read.splice(index, 1);
                this.recycle = item.concat(this.recycle);
            },
            handleRestore(index) {
                const item = this.recycle.splice(index, 1);
                this.read = item.concat(this.read);
            }
        },
        computed: {
            unreadNum(){
                return this.unread.length;
            }
        }
    }

</script>

<style>
.message-title{
    cursor: pointer;
}
.handle-row{
    margin-top: 30px;
}
</style>

