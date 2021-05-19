<template>
  <div class="posts">
    <ul :data="posts">
      <li>
        <div class="post" v-for="(item,index) in posts" :key="item.id">
          <Post :item="item" :is-detail="false">
          </Post>
        </div>
      </li>
    </ul>
    <div class="block">
      <el-pagination
        :page-size="page_size"
        :pager-count="5"
        @current-change=PostListChange
        :current-page="page_index"
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
      isMobile: false,
      type: 1,
      // 当前页
      page_index: 1,
      // 每个页面的内容数量
      page_size: 4,
      // 总条目数
      page_total: 0,
      // 分页样式
      page_layout: '',
      // 后端数据存放在这里
      posts: {},
      genders: '',
      LoadingStatus: false
    }
  },
  created () {
    //获取当前是否为手机端
    this.isMobile = this.getIsMobile()

    this.getPostList()
    this.getPostTotal()
  },
  watch: {
    isMobile (newValue,oldValue) {
      //根据分辨率设置分页样式
      if (newValue) {
        this.page_layout = 'prev, pager, next, jumper'
      } else {
        this.page_layout = 'prev, pager, next'
      }
    }
  },
  methods: {
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
    },

    // 获取帖子总数量
    async getPostTotal () {
      const { data: res } = await this.$http.get('post', {
        params: {
          type: 3
        }
      })
      if (res.code === 1) {
        this.page_total = res.count
      } else {
        console.error(res.message)
      }
    },
    // 页面改变时触发
    PostListChange (newpage) {
      this.page_index = newpage
      this.getPostList()
    }
  }
}
</script>

<style src="../../assets/css/posts.css"></style>
<style scoped>
</style>
