<template>
  <el-container class="home-container">
    <el-header v-if="isMobile==false">
      <span style="margin-right: auto;margin-left: 3rem">day墙(这个名字真他妈难听)</span>
      <el-menu class="menu" :default-active="activeIndex" mode="horizontal" @select="handleSelect">
        <el-menu-item class="item" index="/Home">
          <i class="ico fas fa-home"></i>
          主页
        </el-menu-item>
        <el-menu-item class="item" index="/PostList">
          <i class="ico fas fa-clipboard"></i>
          表白墙
        </el-menu-item>
        <el-menu-item class="item" index="/Confession">
          <i class="ico fas fa-heart"></i>
          我要表白
        </el-menu-item>
        <el-menu-item class="item" index="/Help">
          <i class="ico fas fa-question"></i>
          帮助
        </el-menu-item>
      </el-menu>

      <el-input v-model="SearchKeyword" @keyup.enter.native="goSearch" placeholder="请输入你想搜索的数据"
                style="width: 18.75rem;margin-left: 1.25rem">
        <el-button @click="goSearch" slot="append" icon="el-icon-search"></el-button>
      </el-input>

    </el-header>
    <el-header v-else>
      <span style="margin-right: auto;margin-left: 3rem">day墙(这个名字真他妈难听)</span>
      <span v-on:click="changeMenuState = !changeMenuState" class="btn-menu el-icon-menu"></span>
    </el-header>
    <transition name="fade">
      <el-row v-if="changeMenuState" class="tac">
        <el-col style="position: absolute;z-index: 3">
          <el-menu
            default-active="2"
            text-color="#fff"
            active-text-color="#ffd04b"
            @select="handleSelect">
            <el-menu-item index="/Home">
              <i class="ico fas fa-home"></i>
              <span slot="title">主页</span>
            </el-menu-item>
            <el-menu-item index="/PostList">
              <i class="ico fas fa-clipboard"></i>
              <span slot="title">表白墙</span>
            </el-menu-item>
            <el-menu-item index="/Confession">
              <i class="ico fas fa-heart"></i>
              <span slot="title">我要表白</span>
            </el-menu-item>
            <el-menu-item index="/Help">
              <i class="ico fas fa-question"></i>
              <span slot="title">帮助</span>
            </el-menu-item>
            <el-input v-model="SearchKeyword" @keyup.enter.native="goSearch" placeholder="请输入你想搜索的数据"
                      style="width: 100%;margin-top:0.8rem;margin-bottom:0.4rem">
            </el-input>
          </el-menu>
        </el-col>
      </el-row>
    </transition>
    <el-container>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      //是否为手机端
      isMobile: false,
      activeIndex: '1',
      // 搜索关键词
      SearchKeyword: '',
      //是否展开侧边导航栏
      changeMenuState: false
    }
  },
  created () {
    //获取当前是否为手机端
    this.isMobile = this.getIsMobile()
    //获取路由路径
    this.activeIndex = this.$route.path
  },
  watch: {
    // isMenuOpen (newValue,oldValue) {
    //   if(newValue){
    //
    //   }else{
    //
    //   }
    // }
  },
  methods: {
    goSearch () {
      this.$router.push({
        name: 'Search',
        params: { keyword: this.SearchKeyword }
      })
    },
    //跳转导航
    handleSelect (key, keyPath) {
      this.$router.push({
        path: key
      })
    },
    //打开或关闭侧边导航栏
    changeMenuState () {
      this.isMenuState = !this.isMenuState
    }
  }
}
</script>
<style scoped>
@import "../../assets/css/Home.css";

.home-container {
  height: 100%;
  margin-right: calc(100% - 100vw);
}

.item, .el-header {
  background-color: #3B6FA8;
}

.el-aside {
  background-color: #82ADD9;
}

.el-main {
  background-image: linear-gradient(94.3deg, rgba(126, 141, 206, 1) 10.9%, rgba(184, 166, 166, 1) 87.1%);
  display: flex;
  justify-content: center;
  align-items: center;
}

@media screen and (max-width: 1600px) and (min-width: 1000px) {
  .menu {
    border-bottom: none !important;
    margin-left: 1.25rem;
    margin-right: 1.25rem;
    /*display: flex;*/
  }

  .el-header {
    display: flex;
    align-items: center;
    justify-content: flex-start;
    font-weight: bold;
    font-size: 1.25rem;
    padding-left: 5.5rem;
    padding-right: 5.5rem;
    color: white;
  }
  .el-menu--horizontal>.el-menu-item,
  .el-menu--horizontal>.el-menu-item i
  {
    color: #ffffff;
  }
  .el-menu--horizontal>.el-menu-item.is-active
  {
    border-bottom: 2px solid #409EFF;
    color: #D88333
  }
  .el-menu--horizontal>.el-menu-item.is-active i{
    color: #D88333
  }
  /*.el-menu--horizontal .el-menu-item:not(.is-disabled):hover i{*/
  /*  color: #D88333;*/
  /*}*/
  .el-menu--horizontal .el-menu-item:not(.is-disabled):focus,
  .el-menu--horizontal .el-menu-item:not(.is-disabled):hover,
  .el-menu--horizontal .el-menu-item:not(.is-disabled):hover i{
    outline: 0;
    color: #D88333;
    background-color: #5f79a8;
  }
}

@media screen and (max-width: 1000px) {
  .menu {
    border-bottom: none !important;
    display: flex;
  }
  .el-menu{
    background-color: rgba(84,92,100,.95);
  }
  .el-header {
    display: flex;
    align-items: center;
    justify-content: flex-start;
    font-weight: bold;
    font-size: 1.25rem;
    color: white;
  }
}


.item {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.25rem;
  /* 移入时鼠标变成小手 */
  cursor: pointer;
}

.router-link-active {
  color: #ffc107;
  border-bottom: 0.3rem solid #ffc107;
}

.ico {
  display: inline-block;
  margin-right: 0.2rem;
}

/* 侧边导航栏 */
.btn-menu {
  /*display: flex;*/
  /*justify-content: flex-end;*/

  cursor: pointer;
}

.tac {
  width: 100%;
  position: relative;
}
/* 有bug的动画样式 */
.fade-enter-active, .fade-leave-active {
  /* 淡入淡出 */
  /*transition: opacity .5s;*/

  /* 滑动 */
  /*overflow-y: hidden;*/
  /*max-height: 600px;*/
  /*!* 最大高度 *!*/
  /*background: pink;*/
  /*height: 200px;*/
  /*!*  Webkit内核浏览器：Safari and Chrome*!*/
  /*-webkit-transition-property: all;*/
  /*-webkit-transition-duration: .5s;*/
  /*-webkit-transition-timing-function: cubic-bezier(0, 1, 0.5, 1);*/
  /*!*  Mozilla内核浏览器：firefox3.5+*!*/
  /*-moz-transition-property: all;*/
  /*-moz-transition-duration: .5s;*/
  /*-moz-transition-timing-function: cubic-bezier(0, 1, 0.5, 1);*/
  /*!*  Opera*!*/
  /*-o-transition-property: all;*/
  /*-o-transition-duration: .5s;*/
  /*-o-transition-timing-function: cubic-bezier(0, 1, 0.5, 1);*/
  /*!*  IE9*!*/
  /*-ms-transition-property: all;*/
  /*-ms-transition-duration: .5s;*/
  /*-ms-transition-timing-function: cubic-bezier(0, 1, 0.5, 1);*/
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  /* 淡入淡出 */
  /*opacity: 0;*/


  /* 滑动 */
  /*max-height: 0;*/
}
</style>
