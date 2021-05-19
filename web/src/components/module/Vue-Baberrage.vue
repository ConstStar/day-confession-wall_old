<template>
  <div class="barrages-drop">
    <vue-baberrage class="baberrage-stage"
                   :isShow="barrageIsShow"
                   :barrageList="barrageList"
                   :throttleGap="throttleGap"
                   :lanesCount="lanesCount"
                   :loop="barrageLoop"
                   maxWordCount="60"
    >
      <template v-slot:default="slotProps">

        <div class="name">
          {{ slotProps.item.data.sender }}
        </div>
        <span class="xin">❤</span>
        <div class="name">
          {{ slotProps.item.data.recipient }}
        </div>
        <span class="fas fa-thumbs-up">
            {{ slotProps.item.data.thumbs_up }}
        </span>

      </template>
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
      barrageIsShow: this.isShow,
      barrageLoop: true,
      throttleGap: 2000,
      lanesCount: 10, //泳道的数量
      // 弹幕数据列表
      barrageList: []
    }
  },
  mounted () {
    this.init()
  },
  watch: {
    posts (newValue, oldValue) {
    }
  },
  methods: {
    getRandomInt: function (min, max) {
      return Math.floor(Math.random() * (max - min + 1)) + min
    },
    //每隔一段时间添加一个弹幕
    timerMonitor: function () {
      var v = this.posts.pop()
      if (v != undefined) {
        this.barrageList.push({
          id: v.id,
          data: v,
          time: this.getRandomInt(10, 20),
          barrageStyle: 'barrageStyle'
        })
      }
    },
    init: function () {
      //定时器
      setInterval(this.timerMonitor, 500)
    }
  }
}
</script>
<style>

.barrages-drop .barrageStyle {
  padding: 0.5rem;
  border-radius: 110px;
  /*background-color: #fff;*/
  border: 1px solid #000;
}

.barrages-drop .baberrage-stage {
  position: absolute;
  overflow: hidden;
  top: 0;
  margin-top: 130px;
  margin-bottom: 130px;
}

.name {
  font-weight: bold;
  color: #303133;
  cursor: pointer;

  display: inline-block;
  margin-left: 0.6rem;
  margin-right: 0.6rem;
}

.xin {
  color: red;
}
</style>
