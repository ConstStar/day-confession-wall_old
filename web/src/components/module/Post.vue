<template>
  <div class="post" ref="postRef">
    <el-breadcrumb separator="❤">
      <el-breadcrumb-item class="gender">
        <div v-if="item.sender_sex === 1"><i class="fas fa-mars" style="color: #3B6FA8"></i></div>
        <div v-else-if="item.sender_sex === 2"><i class="fas fa-venus" style="color: deeppink"></i></div>
        <div v-else><i class="fas fa-genderless"></i></div>
        <div class="name" @click="goSearch(item.sender)">
          {{item.sender}}
        </div>
      </el-breadcrumb-item>
      <el-breadcrumb-item class="gender">

        <div class="name" @click="goSearch(item.recipient)">
          {{item.recipient}}
        </div>
        <div v-if="item.recipient_sex === 1"><i class="fas fa-mars" style="color: #3B6FA8"></i></div>
        <div v-else-if="item.recipient_sex === 2"><i class="fas fa-venus" style="color: deeppink"></i></div>
        <div v-else><i class="fas fa-genderless"></i></div>
      </el-breadcrumb-item>
    </el-breadcrumb>
    <div v-if="isDetail===false" @click="goPostDetail(item.id)" class="post-content" style="cursor: pointer;">{{item.content}}</div>
    <div v-else class="post-content">{{item.content}}</div>
    <div class="create-time">{{this.toDates(item.create_time)}}</div>

    <el-button-group>
      <!--   点赞   -->
      <el-button
        icon="fas fa-thumbs-up"
        @click="thumbs_up(item.id)"
        id="thumbsTrue"
        v-if="flag">
        {{item.thumbs_up}}
      </el-button>
      <el-button
        icon="fas fa-thumbs-up"
        @click="thumbs_up(item.id)"
        id="thumbsFalse"
        v-else>
        {{item.thumbs_up}}
      </el-button>
      <!--  评论  -->
      <el-button v-if="isDetail===false" icon="fas fa-comment-alt" @click="goPostDetail(item.id)"> {{this.CommentTotal}}</el-button>
      <!--  分享  -->
      <el-button icon="fas fa-share-square" @click="showQrCode(item.id)"></el-button>
    </el-button-group>
    <!--  点击分享后显示的页面  -->
    <el-dialog
      :visible.sync="QRVisible"
      width="37%">
      <div class="qrcode" ref="qrCodeUrl"></div>
      <div class="search" style="width: 32.125rem;margin-left: 1.25rem">
        <el-breadcrumb separator="❤" style="border-bottom: 0;font-size: 1.5em">
          <el-breadcrumb-item class="gender">
            <div v-if="item.sender_sex === 1"><i class="fas fa-mars" style="color: #3B6FA8"></i></div>
            <div v-else-if="item.sender_sex === 2"><i class="fas fa-venus" style="color: deeppink"></i></div>
            <div v-else><i class="fas fa-genderless"></i></div>
            <div class="name" @click="goSearch(item.sender)">
              {{item.sender}}
            </div>
          </el-breadcrumb-item>
          <el-breadcrumb-item class="gender">
            <div class="name" @click="goSearch(item.recipient)">
              {{item.recipient}}
            </div>
            <div v-if="item.recipient_sex === 1"><i class="fas fa-mars" style="color: #3B6FA8"></i></div>
            <div v-else-if="item.recipient_sex === 2"><i class="fas fa-venus" style="color: deeppink"></i></div>
            <div v-else><i class="fas fa-genderless"></i></div>
          </el-breadcrumb-item>
        </el-breadcrumb>
        <el-input style="height: 0.6rem" size="small" v-model="url" ref="urlText" readonly>
          <el-button slot="append" @click="copyUrl">复制</el-button>
        </el-input>
      </div>
    </el-dialog>
  </div>

</template>

<script>
  import QRCode from 'qrcodejs2'
  export default {
    data(){
      return{
        QRVisible: false,
        PostId: '',
        url:'',
        flag:true,
        CommentTotal: 0
      }
    },
    name: 'Post',

    //item 内容
    //isDetail 是否为详情页
    //          详情页没有点击进入详情页功能
    props: ['item', 'isDetail'],


    //是否点过赞
    is_thumbs_up: false,
    created(){
      this.getCommentTotal()
    },
    methods: {
      // 点击喜欢按钮后的函数
      async thumbs_up (id) {

        const { data: res } = await this.$http.put('post', {
          'type': 1,
          'id': id,
          'thumbs_up': !this.is_thumbs_up
        })
        if (res.code === 1) {
          this.is_thumbs_up = !this.is_thumbs_up
          this.getPostData()
          this.flag = !this.flag
        }
      },
      // 根据id获取帖子数据
      async getPostData () {
        const { data: res } = await this.$http.get('post', {
          params: {
            'type': 2,
            'id': this.item.id
          }
        })
        if (res.code === 1) {
          this.item = res
        } else {
          console.error(res.message)
        }
      },
      // 获取评论总数
      async getCommentTotal(){
        const{data: res} = await this.$http.get('comment',{
          params:{
            type:3,
            p_id:this.item.id
          }
        })
        this.CommentTotal=res.count
      },
      // 打开详情页
      goPostDetail (id) {
        this.$router.push({
          name: 'PostDetail',
          params: { id: id }
        })
      },
      //搜索
      goSearch (keyword) {
        this.$router.push({
          name: 'Search',
          params: { keyword: keyword }
        })
      },
      // 显示分享页面
      showQrCode(id){
        this.QRVisible = true
        this.PostId = id
      },
      // 创建二维码的函数
      creatQrCode() {
        this.$nextTick(() => {
          // 获取不带路径的url地址
          var origin = window.location.origin;
          const url = origin + '/#/PostDetail/' + this.PostId
          this.url=url
          this.$refs.qrCodeUrl.innerHTML='' //清空原有的二维码
          var qrcode = new QRCode(this.$refs.qrCodeUrl, {
            text: url, // 需要转换为二维码的内容
            width: 100,
            height: 100,
            colorDark: '#0099ce',
            colorLight: '#ffffff',
            correctLevel: QRCode.CorrectLevel.H
          })
        })
      },
      copyUrl(){
        var ele = this.$refs.urlText;
        ele.select();
        document.execCommand("Copy");
        alert("复制链接成功！");
      }
    },
    watch:{
      QRVisible:function(val, oldVal){
        if(val===true){
          this.creatQrCode();
        }
      }
    },
    mounted() {

    }
  }
</script>

<style scoped>
  /deep/ .el-breadcrumb__separator {
    color: red;
  }

  .el-breadcrumb {
    display: flex;
    justify-content: center;
    padding-bottom: 1.25rem;
    border-bottom: 0.05rem solid #999;
  }

  /* 内容列表整体框的样式 */
  .post {
    width: 100%;
    height: 100%;
    background-color: #fff;
    margin-top: 0.6rem;
    margin-bottom: 1.25rem;
    padding: 1.25rem;
    border-radius: 0.6rem;
  }

  .post:last-child {
    margin-bottom: 0.6rem;
  }

  /* 一个内容的样式 */
  .post-content {
    margin-top: 1.25rem;
    width: 100%;
    height: 45%;
    background-color: #f4f4f48a;
    word-wrap:break-word;
  }

  /* 内容里面的按钮数组 */
  .el-button-group {
    display: flex;
    justify-content: center;
  }

  /* 创建时间的样式 */
  .create-time {
    color: #999;
    font-size: 0.8rem;
    display: flex;
    justify-content: flex-end;
    margin-top: 1.25rem;
    margin-bottom: 1.25rem;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  }

  .gender div {
    display: inline-block;
    vertical-align: -0.187rem;
  }

  .gender div i {
    font-size: 1.25rem;
  }
  /deep/ .el-dialog__body{
    display: flex;
    justify-content: center;
  }

  .name {
    font-weight: bold;
    color: #303133;
    cursor: pointer;

    margin-left: 0.6rem;
    margin-right: 0.6rem;
  }
  #thumbsFalse{
    /*color: #606266;*/
    /*border-color: #DCDFE6;*/
    /*background-color: #FFF;*/
    color: #409EFF;
    border-color: #c6e2ff;
    background-color: #ecf5ff;
  }
  #thumbsFalse:hover{
    color: #409EFF;
    border-color: #c6e2ff;
    background-color: #ecf5ff;
  }
  #thumbsTrue:focus{
    color: #606266;
    border-color: #DCDFE6;
    background-color: #FFF;
  }
  #thumbsTrue:hover{
    color: #409EFF;
    border-color: #c6e2ff;
    background-color: #ecf5ff;
  }
</style>
