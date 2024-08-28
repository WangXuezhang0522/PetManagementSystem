<template>
  <div class="app-container">
    <el-row>
      <el-card>
        <div slot="header"><span><i class="el-icon-data-analysis"></i> 领养情况</span></div>
        <div class="el-table el-table--enable-row-hover el-table--medium">
          <div ref="commandstats" style="height: 420px" />
        </div>
      </el-card>
    </el-row>
    <el-row>
      <el-card>
        <div slot="header"><span><i class="el-icon-data-line"></i> 寄养情况</span></div>
        <div class="el-table el-table--enable-row-hover el-table--medium">
          <div ref="usedmemory" style="height: 420px" />
        </div>
      </el-card>
    </el-row>
  </div>
</template>

<script>
import { getEchartsData } from "@/api/pet/adopt";
import { getJYEchartsData } from "@/api/pet/fosterInstitution";
import * as echarts from "echarts";

export default {
  name: "Tjsx",
  data() {
    return {
      // 领养信息
      commandstats: null,
      // 寄养信息
      usedmemory: null,
    }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查缓信息 */
    getList() {
      getEchartsData().then((response) => {
        let data = response.data;
        console.log(data);
        this.commandstats = echarts.init(this.$refs.commandstats, "macarons");
        this.commandstats.setOption({
          xAxis: {
            type: 'category',
            data: data.xData
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              data: data.yData,
              type: 'bar'
            }
          ]
        });
        window.addEventListener("resize", () => {
          this.commandstats.resize();
        });
      });
      getJYEchartsData().then((response) => {
        let data = response.data;
        this.usedmemory = echarts.init(this.$refs.usedmemory, "macarons");
        this.usedmemory.setOption({
          xAxis: {
            type: 'category',
            data: data.xData
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              data: data.yData,
              type: 'line'
            }
          ]
        });
        window.addEventListener("resize", () => {
          this.usedmemory.resize();
        });
      });
    },
  }
};
</script>