<template>
    <el-container class="home-container">
      <el-header>
        <span style="margin-right: auto;margin-left: 3rem">day墙(这个名字真他妈难听)</span>
        <div class="menu">
          <router-link class="item" tag="div" to="/Home">
            <i class="fas fa-home"></i>
            主页
          </router-link>
          <router-link class="item" tag="div" to="/PostList">
            <i class="fas fa-clipboard"></i>
            表白墙
          </router-link>
          <router-link class="item" tag="div" to="/Confession">
            <i class="fas fa-heart"></i>
            我要表白
          </router-link>
          <router-link class="item" tag="div" to="/Help">
            <i class="fas fa-question"></i>
            帮助
          </router-link>
        </div>
        <el-input v-model="SearchKeyword" @keyup.enter.native="goSearch" placeholder="请输入你想搜索的数据" style="width: 18.75rem;margin-left: 1.25rem">
          <el-button @click="goSearch" slot="append" icon="el-icon-search"></el-button>
        </el-input>
      </el-header>
      <el-container>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
      <barrages></barrages>
    </el-container>
</template>

<script>
export default {
  data () {
    return {
      type: 1,
      page_index: 1,
      page_size: 10,
      activeIndex: '1',
      // 搜索关键词
      SearchKeyword: '',
      // 获取分页帖子列表
      posts:''
    }
  },
  mounted () {
    this.getPostList()
  },
  methods: {
    goSearch(){
      this.$router.push({ name: 'Search', params: { keyword: this.SearchKeyword } })
    },
    // 获取分页帖子列表
    async getPostList () {
      const { data: res } = await this.$http.get('post', {
        params: {
          type: this.type,
          page_index: this.page_index,
          page_size: this.page_size
        }
      })
      if (res.code === 1) {
        this.posts = res.posts
      } else {
        console.error(res.message)
      }
      console.log(this.posts)
    }
  }
}
</script>
<style scoped>
  @import "../../assets/css/Home.css";
  .home-container{
    height: 100%;
    margin-right: calc(100% - 100vw);
  }
  .el-header{
    background-color: #3B6FA8;
    display: flex;
    align-items: center;
    justify-content: flex-start;
    font-weight: bold;
    font-size: 1.25rem;
    padding-left: 5.5rem;
    padding-right: 5.5rem;
    color: white;
  }
  .el-aside{
    background-color: #82ADD9;
  }
  .el-main{
    background-image: linear-gradient( 94.3deg, rgba(126, 141, 206, 1) 10.9%, rgba(184, 166, 166, 1) 87.1% );
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .menu{
    border: 0;
    margin-left: 1.25rem;
    margin-right: 1.25rem;
    display: flex;
  }
  .menu i{
    padding-right: 0.75rem;
  }
  .item{
    margin-left: 1.25rem;
    margin-right: 1.25rem;
    line-height: 3.5rem;
    width: 7rem;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.25rem;
    /*border-bottom: 5rem solid transparent;*/
    /* 移入时鼠标变成小手 */
    cursor:pointer;
  }
  .router-link-active{
    color: #ffc107;
    border-bottom: 0.3rem solid #ffc107;
  }
</style>
