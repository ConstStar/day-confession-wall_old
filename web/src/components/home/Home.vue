<template>
  <!--  <div class="text-all">-->
  <!--    <div>hi！这是个匿名表白墙</div>-->
  <!--    <div>想对自己喜欢的人进行表白却不好意思说出口？</div>-->
  <!--    <div>让它来帮助你!</div>-->
  <!--    <div>Click!</div>-->
  <!--    <div>-->
  <!--      <i class="fas fa-arrow-down"></i>-->
  <!--    </div>-->
  <!--    <div class="text_icon">-->
  <!--      <router-link to="Confession">-->
  <!--        <i class="fab fa-gratipay"></i>-->
  <!--      </router-link>-->
  <!--    </div>-->
  <!--  </div>-->
  <div style="width: 100%;height: 100%">
    <barrages :posts="posts" :is-show="true"></barrages>
  </div>
</template>

<script>
export default {
  name: 'app',
  data () {
    return {
      // 热门帖子
      type: 1,
      page_index: 1,
      page_size: 30,
      posts: []
    }
  },
  mounted () {
    this.getPostList()
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
    }
  }
}
</script>
<!--<style src="../../assets/css/Home.css"></style>-->
<style scoped>
.text-all div {
  margin: 10px;
  color: #fff;
}

.text-all div:nth-child(1) {
  font-size: 25px;
}

.text-all div:nth-child(2) {
  font-size: 40px;
  font-weight: bold;
}

.text-all div:nth-child(3) {
  font-size: 30px;
}

.text-all div:nth-child(4), div:nth-child(5) {
  font-size: 30px;
  text-align: center;
  /*color: red;*/
  font-weight: bold;
}

.text-all div:nth-child(6) {
  display: flex;
  justify-content: center;
}

.text-all div:nth-child(6) a {
  text-decoration: none;
  color: red;
  font-size: 80px;
}
</style>
