<template>
  <div class="posts">
    <div class="post" style="height: 20rem">
      <Post :item="PostData" :is-detail="true">
      </Post>
    </div>

    <div id="inputComment" class="input-comment">
      <div style="margin-bottom: 0.7rem;">发布评论</div>
      <div class="text-comment">
        <span style="color: #828282">您的名字：</span>
        <input
          v-model="CommentName"
          type="text"
          style="border-radius: 0.3rem;border: 0.05rem solid #999;height: 1.55rem;padding: 0.7rem"
          @keyup.enter="addComment">
        <textarea
          v-model="CommentContent"
          placeholder="请在此处填写评论"
          name="inputComment"
          rows="8"></textarea>
      </div>
      <button @click="addComment">发布评论</button>
    </div>
    <!--    <div class="input-comment-all">-->
    <!--    </div>-->
    <div class="comment">
      <div class="comment-title">评论(共{{ CommentTotal }}条)</div>
      <ul :data="PostComment">
        <li>
          <div v-for="(item,index) in PostComment.comments" :key="item.id">
            <a href="/">{{ item.name }}：</a>
            <div style="font-size: 0.9rem">{{ item.content }}</div>
            <div style="font-size: 0.75rem">{{ toDates(item.create_time) }}</div>
          </div>
        </li>
      </ul>
    </div>
    <div class="more-comment">暂无更多评论</div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      id: this.$route.params.id,
      // 根据帖子id返回的数据
      PostData: {},
      //后端返回的评论内容
      PostComment: {},
      // 页数
      page_index: 1,
      // 一页包含的内容个数
      page_size: 10,
      // 评论总数
      CommentTotal: 0,
      // 评论人名字
      CommentName: '',
      // 评论内容
      CommentContent: '',
      // 当前页面地址
      PostDetailHref: ''
    }
  },
  created () {
    this.getPostData()
    this.getComment()
    this.getCommentTotal()
    this.getHref()
  },
  methods: {
    // 获取评论
    async getComment () {
      const { data: res } = await this.$http.get('comment', {
        params: {
          type: 1,
          p_id: this.id,
          page_index: this.page_index,
          page_size: this.page_size
        }
      })
      this.PostComment = res
    },
    // 获取评论总数
    async getCommentTotal () {
      const { data: res } = await this.$http.get('comment', {
        params: {
          type: 3,
          p_id: this.id
        }
      })
      this.CommentTotal = res.count
    },

    //发布评论
    async addComment () {
      var name = this.CommentName
      var content = this.CommentContent
      if (name === '' || content === '') {
        this.$message.error('请输入名字或者评论内容')
        return
      }
      const { data: res } = await this.$http.post('comment', {
        p_id: this.PostData.id,
        name: name,
        content: content
      })
      console.log(res)
      this.$message.success('发布成功')
      this.CommentName = ''
      this.CommentContent = ''
      this.getComment()
      this.getCommentTotal()
    },

    // 根据id获取帖子数据
    async getPostData () {
      const { data: res } = await this.$http.get('post', {
        params: {
          type: 2,
          id: this.id
        }
      })
      if (res.code === 1) {
        this.PostData = res
      } else {
        console.error(res.message)
      }
    },
    getHref () {
      this.PostDetailHref = window.location.href
      console.log(this.PostDetailHref)
    }
  }
}
</script>
<style src="../../assets/css/posts.css"></style>
<style scoped>
.comment {
  padding: 0 1.25rem 0 1.25rem;
  background-color: #fff;
}

/* 评论标题样式 */
.comment-title {
  padding-bottom: 1rem;
  border-bottom: 0.05rem solid black;
  display: flex;
  justify-content: center;
  background-color: #fff;
}

.comment ul li > div {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1.25rem 0 1.25rem 0;
  padding-bottom: 0.05rem;
  border-bottom: 0.05rem solid #999;
}

.comment ul li > div:last-child {
  margin-bottom: 0;
}

.comment ul li > div a {
  text-decoration: none;
  font-weight: bold;
  color: #3B6FA8;
}

.more-comment {
  background-color: #fff;
  border-radius: 0 0 0.6rem 0.6rem;
  display: flex;
  justify-content: center;
  padding-top: 1.25rem;
  padding-bottom: 1.25rem;
  color: #828282;
  font-size: 0.75rem;
  font-weight: bold;
}

/* 输入框的样式 */
/*.inputCommentAll{*/
/*  background-color: #fff;*/
/*}*/
.input-comment {
  margin-top: 0.6rem;
  background-color: #fff;
  text-align: center;
  padding: 1.25rem;
  border-radius: 0.7rem 0.7rem 0 0;
}

.input-comment textarea {
  height: 6.25rem;
  width: 90%;
  /* 规定能否拉伸样式 */
  resize: none;
  border: 0.05rem solid #999;
  border-radius: 0.5rem;
  margin: 0.6rem;
  padding: 0.8rem;
}

.input-comment button {
  background-color: #7482e1;
  color: white;
  border: 0.05rem solid #999;
  border-radius: 0.5rem;
  padding: 0.3rem;
}

.input-comment button:hover {
  background-color: #999;
  cursor: pointer;
}

.text-comment {
  /*display: flex;*/
}
</style>
