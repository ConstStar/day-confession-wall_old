<template>
  <div class="posts">
    <ul :data="posts">
      <li>
        <div class="post" v-for="(item,index) in posts" :key="item.id">
          <Post :item="item" :is-detail="false">
            <el-button-group>
              <!--   点赞   -->
              <el-button
                icon="fas fa-thumbs-up"
                @click="like(item.sender,item.sender_sex,item.recipient,item.recipient_sex,item.content,item.id)">
                {{ item.thumbs_up }}
              </el-button>
              <!--  评论  -->
              <el-button icon="fas fa-comment-alt" @click="goPostDetail(item.id)"></el-button>
              <el-button icon="fas fa-share-square"></el-button>
            </el-button-group>
          </Post>
        </div>
      </li>
    </ul>
    <div class="block">
      <el-pagination
        :page-size=page_size
        :pager-count="5"
        @current-change=PostListChange
        :current-page=page_index
        :layout="page_layout"
        :total=page_total>
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      //是否为手机端
      isMobile: null,
      keyword: this.$route.params.keyword,
      posts: {},
      // 当前页
      page_index: 1,
      // 每个页面的内容数量
      page_size: 4,
      // 查询帖子数量
      page_total: 0,
      // 分页样式
      page_layout: ''
    }
  },
  created () {
    //获取当前是否为手机端
    this.isMobile = this.getIsMobile()

    this.search()
  },
  watch: {
    isMobile (newValue, oldValue) {
      //根据分辨率设置分页样式
      if (newValue) {
        this.page_layout = 'prev, pager, next'
      } else {
        this.page_layout = 'prev, pager, next, jumper'
      }
    }
  },
  methods: {
    //打开详情页
    goPostDetail (id) {
      this.$router.push({
        name: 'PostDetail',
        params: { id: id }
      })
    },
    // 获取搜索数据
    async search () {
      const { data: res } = await this.$http.get('post', {
        params: {
          type: 4,
          keyword: this.keyword,
          page_index: this.page_index,
          page_size: this.page_size
        }
      })
      console.log(res)
      if (res.code === 1) {
        this.posts = res.posts
      } else {
        console.error(res.message)
      }
      this.getSearchTotal()
    },
    // 页面改变时触发
    PostListChange (newpage) {
      this.page_index = newpage
      this.search()
    },
    // 查询帖子数量
    async getSearchTotal () {
      console.log(this.keyword)
      const { data: res } = await this.$http.get('post', {
        params: {
          type: 5,
          keyword: this.keyword
        }
      })
      this.page_total = res.count
      console.log(this.page_total)
    }
  }
}
</script>

<style src="../../assets/css/posts.css"></style>
<style scoped>

</style>
