<template>
  <div class="barrages-drop">
    <vue-baberrage
      :isShow="barrageIsShow"
      :barrageList="barrageList"
      :maxWordCount="maxWordCount"
      :throttleGap="throttleGap"
      :loop="barrageLoop"
      :boxHeight="boxHeight"
      :messageHeight="messageHeight"
    >
<!--      <template v-slot:default="slotProps">-->
<!--        <span style="color: #000">-->
<!--&lt;!&ndash;          {{slotProps.item.data.sender}} : {{slotProps.item.data.recipient}}&ndash;&gt;-->
<!--          h-->
<!--        </span>-->
<!--      </template>-->
    </vue-baberrage>
  </div>
</template>
<script>
  import Vue from 'vue'
  import { vueBaberrage, MESSAGE_TYPE } from 'vue-baberrage'

  Vue.use(vueBaberrage)

  export default {
    props: ['posts', 'isShow'],
    name: 'Barrages',
    data () {
      return {
        list: [],
        barrageIsShow: this.isShow,
        messageHeight: 3,
        boxHeight: 150,
        barrageLoop: true,
        maxWordCount: 3,
        throttleGap: 5000,
        // 弹幕数据列表
        barrageList: []
      }
    },
    mounted () {
      this.addToList()
    },
    watch:{
      posts(newValue,oldValue){
        this.barrageList = newValue
        for (var i = 0; i < this.barrageList.length; i++){
          this.list.push(this.barrageList[i].content)
        }
        var lista =  JSON.parse(JSON.stringify(this.list))
      }
    },
    methods: {
      addToList: function () {
        let list = [
          {
            id: 1,
            avatar: '',
            msg: 'this.msgContent[index]',
            time: 3,
            barrageStyle: 'style'
          }
        ]
        list.forEach((v) => {
          this.barrageList.push({
            id: v.id,
            avatar: v.avatar,
            msg: v.msg,
            time: v.time,
            type: MESSAGE_TYPE.NORMAL,
            barrageStyle: v.barrageStyle
          })
        })
        // for (let i = 0; i < this.barrageList.length; i++) {
        //   console.log(this.barrageList[i].id)
        //   this.barrageList.push({
        //     id: this.posts[i].id,
        //     data: this.posts[i],
        //     time: 3,
        //     type: MESSAGE_TYPE.NORMAL,
        //     barrageStyle: 'style'
        //   })
        // }
        // console.log(this.posts)
      }
    }
  }
</script>
<style>
  .barrages-drop .style {
    border-radius: 110px;
    background: #3B6FA8;
    color: #fff;
  }

  .barrages-drop .baberrage-stage {
    position: absolute;
    width: 100%;
    height: 212px;
    overflow: hidden;
    top: 0;
    margin-top: 130px;
  }
</style>
