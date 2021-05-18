<template>
  <div class="text-all">
    <div>hi！这是个匿名表白墙</div>
    <div>想对自己喜欢的人进行表白却不好意思说出口？</div>
    <div>让它来帮助你!</div>
    <div>Click!</div>
    <div>
      <i class="fas fa-arrow-down"></i>
    </div>
    <div class="text_icon">
      <router-link to="Confession">
        <i class="fab fa-gratipay"></i>
      </router-link>
    </div>
  </div>
<!--  <div id="app">-->
<!--    <vue-baberrage-->
<!--      :isShow= "barrageIsShow"-->
<!--      :barrageList = "barrageList"-->
<!--      :loop = "barrageLoop">-->
<!--    </vue-baberrage>-->
<!--  </div>-->
</template>

<script>
export default {
  name:'app',
  data () {
    return {
      // 弹幕
      msg: 'Hello vue-baberrage',
      barrageIsShow: true,
      currentId: 0,
      barrageLoop: false,
      barrageList: []
    }
  },
  methods: {
    addToList () {
      this.barrageList.push({
        id: ++this.currentId,
        avatar: "./static/avatar.jpg",
        msg: this.msg,
        time: 5,
        type: MESSAGE_TYPE.NORMAL
      })
    }
  }
}
</script>
<!--<style src="../../assets/css/Home.css"></style>-->
<style scoped>
  .text-all div{
    margin: 10px;
    color: #fff;
  }
  .text-all div:nth-child(1){
    font-size: 25px;
  }
  .text-all div:nth-child(2){
    font-size: 40px;
    font-weight: bold;
  }
  .text-all div:nth-child(3){
    font-size: 30px;
  }
  .text-all div:nth-child(4), div:nth-child(5){
    font-size: 30px;
    text-align: center;
    /*color: red;*/
    font-weight: bold;
  }
  .text-all div:nth-child(6){
    display: flex;
    justify-content: center;
  }
  .text-all div:nth-child(6) a{
    text-decoration: none;
    color: red;
    font-size: 80px;
  }
</style>
