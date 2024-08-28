<template>
  <div class="app-container home">
    <div v-hasRole="['yonghu']">
      <el-card class="box-card" style="margin-bottom: 20px;">
        <div slot="header" class="clearfix">
          <span>可领养的宠物</span>
        </div>
        <div class="text item" v-for="item in adoptList" :key="item.id" style="display: inline-block;margin: 0px 20px;">
          <h1 style="text-align: center;">{{ item.name }}</h1>
          <image-preview :src="item.img" :width="150" :height="150" />
        </div>
      </el-card>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>宠物商品</span>
        </div>
        <div class="text item" v-for="item in commodityList" :key="item.id"
          style="display: inline-block;margin: 0px 20px;">
          <h1 style="text-align: center;">{{ item.name }}</h1>
          <image-preview :src="item.img" :width="150" :height="150" />
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import { listAdopt } from "@/api/pet/adopt";
import { listCommodity } from "@/api/pet/store";
export default {
  name: "Index",
  data() {
    return {
      adoptList: [],
      commodityList: [],
    };
  },
  created() {
    this.getListAdopt();
    this.getListCommodity();
  },
  methods: {
    /** 查询可领养的宠物数据 */
    getListAdopt() {
      listAdopt({
        state: "0"
      }).then(response => {
        this.adoptList = response.rows;
        console.log(this.adoptList);
      });
    },
    /** 查询商品信息列表 */
    getListCommodity() {
      listCommodity().then(response => {
        this.commodityList = response.rows;
      });
    },
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans",
  "Helvetica Neue",
  Helvetica,
  Arial,
  sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>
