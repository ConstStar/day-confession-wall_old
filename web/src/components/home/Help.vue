<template>
  <div>
<!--    <div style="width: 1000px"></div>-->
    <button @click="addToList">弹幕</button>
<!--    <vue-baberrage-->
<!--      :isShow= "barrageIsShow"-->
<!--      :barrageList = "barrageList"-->
<!--      :loop = "barrageLoop"-->
<!--    >-->
<!--    </vue-baberrage>-->
  </div>
</template>


<script>
  // import { MESSAGE_TYPE } from 'vue-baberrage'
export default {
  data () {
    return {
      // 弹幕
      msg: 'Hello vue-baberrage',
      barrageIsShow: true,
      currentId: 0,
      barrageLoop: true,
      barrageList: []
    }
  },
  methods: {
    addToList () {
      this.barrageList.push({
        id: ++this.currentId,
        avatar: "",
        msg: this.msg,
        time: 5,
        type: MESSAGE_TYPE.NORMAL,
      })
    }
  },
  mounted() {
    // this.creatQrCode()
    // this.addToList()
  }
}
</script>

<style scoped>
  div{
    font-weight: bold;
    font-size: 50px;
  }
</style>
