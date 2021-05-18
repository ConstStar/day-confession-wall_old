<template>
  <div>
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
        layout="prev, pager, next, jumper"
        :total=page_total>
      </el-pagination>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        type: 1,
        // 当前页
        page_index: 1,
        // 每个页面的内容数量
        page_size: 4,
        // 总条目数
        page_total: 0,
        // 后端数据存放在这里
        posts: {},
        genders: '',
        LoadingStatus: false
      }
    },
    created () {
      this.getPostList()
      this.getPostTotal()
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

<style scoped>

  .post, .block {
    width: 30rem;
  }

  /*@media (max-width: 800px) {*/
  /*  .post {*/
  /*    width: 10rem;*/
  /*    height: 5rem;*/
  /*  }*/
  /*}*/

  .post {
    height: 20rem;
  }

  /* 页码样式 */
  .block {
    background-color: #fff;
    display: flex;
    justify-content: center;
    margin: 0.7rem 0 0.7rem 0;
    padding: 0.3rem 0 0.3rem 0;
    border-radius: 0.3rem;
  }
</style>
