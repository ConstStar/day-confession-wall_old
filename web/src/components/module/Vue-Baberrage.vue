<template>
  <div class="barrages-drop">
    <vue-baberrage
      :isShow="barrageIsShow"
      :barrageList="barrageList"
      :maxWordCount="maxWordCount"
      :throttleGap="throttleGap"
      :loop="barrageLoop"
      :boxHeight="boxHeight"
      :messageHeight="messageHeight">
    </vue-baberrage>
  </div>
</template>
<script>
  import Vue from 'vue';
  import { vueBaberrage, MESSAGE_TYPE } from 'vue-baberrage';

  Vue.use(vueBaberrage);

  export default {
    props: ['item', 'isShow'],
    name: 'Barrages',
    data() {
      return {
        msg: item,
        barrageIsShow: isShow,
        messageHeight: 5,
        boxHeight: 150,
        barrageLoop: true,
        maxWordCount: 3,
        throttleGap: 5000,
        // 弹幕数据列表
        barrageList: []
      };
    },
    mounted() {
      this.addToList();
    },
    methods: {
      addToList() {
        let list = [
          {
            id: 1,
            avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
            msg: this.msg.content,
            time: 3,
            barrageStyle: 'style'
          },
          {
            id: 2,
            avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
            msg: this.msg.content,
            time: 8,
            barrageStyle: 'style'
          },
          {
            id: 3,
            avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
            msg: this.msg.content,
            time: 10,
            barrageStyle: 'style'
          },
          {
            id: 4,
            avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
            msg: this.msg.content,
            time: 5,
            barrageStyle: 'style'
          },
          {
            id: 5,
            avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
            msg: this.msg.content,
            time: 6,
            barrageStyle: 'style'
          },
          {
            id: 6,
            avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
            msg: this.msg.content,
            time: 12,
            barrageStyle: 'style'
          },
          {
            id: 7,
            avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
            msg: this.msg.content,
            time: 5,
            barrageStyle: 'style'
          },
          {
            id: 8,
            avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
            msg: this.msg.content,
            time: 5,
            barrageStyle: 'style'
          },
          {
            id: 9,
            avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
            msg: this.msg.content,
            time: 8,
            barrageStyle: 'style'
          },
          {
            id: 10,
            avatar: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3064584167,3502823640&fm=26&gp=0.jpg',
            msg: this.msg.content,
            time: 10,
            barrageStyle: 'style'
          }
        ];
        list.forEach((v) => {
          this.barrageList.push({
            id: v.id,
            avatar: v.avatar,
            msg: v.msg,
            time: v.time,
            type: MESSAGE_TYPE.NORMAL,
            barrageStyle: v.barrageStyle
          });
        });
        console.log(this.item)
      }
    }
  };
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
