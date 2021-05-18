import PostComponent from './Post.vue'


const Post = {
  install: function (Vue) {
    Vue.component('Post', PostComponent)
  }
}
export default Post
