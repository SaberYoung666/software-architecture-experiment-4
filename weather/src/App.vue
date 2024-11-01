<template>
  <div id="app" class="weather-app">
    <el-container class="h-100">
      <el-header class="header">
        <h1 class="title">天气预报</h1>
      </el-header>
      <el-main class="main-content">
        <el-row class="search-row" type="flex" justify="center" align="middle">
          <el-col :span="12">
            <el-input
                placeholder="请输入城市名称"
                v-model="city"
                @keyup.enter="fetchWeather"
                class="search-input"
            ></el-input>
            <el-button type="primary" @click="fetchWeather" class="search-button">查询天气</el-button>
          </el-col>
        </el-row>
        <el-row v-if="weather" :gutter="20" class="weather-cards">
          <el-col v-for="(day, index) in weather" :key="index" :span="8">
            <el-card class="box-card">
              <div slot="header" class="clearfix card-header">
                <span>{{ day.date }}天气</span>
              </div>
              <div class="card-body">
                <p>最高温度：{{ day.high }}</p>
                <p>最低温度：{{ day.low }}</p>
                <p>湿度：{{ day.humidity }}</p>
                <p>白天天气：{{ day.text_day }}</p>
                <p>夜间天气：{{ day.text_night }}</p>
                <p>风速：{{ day.wind_speed }}</p>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  name: 'App',
  data() {
    return {
      city: '',
      weather: null,
      apiKey: 'SxmuCVNPt2TogfiAI', // 替换为您的API密钥
    };
  },
  methods: {
    fetchWeather() {
      if (this.city.trim() === '') {
        alert('请输入城市名称');
        return;
      }
      axios.get(`https://api.seniverse.com/v3/weather/daily.json?key=${this.apiKey}&location=${this.city}&language=zh-Hans&unit=c`)
          .then(response => {
            this.weather = response.data.results[0].daily;
            console.log(this.weather);
          })
          .catch(error => {
            console.error(error);
            alert('获取天气信息失败，请检查城市名称是否正确');
          });
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.weather-app {
  height: 100vh;
}

.header {
  background-color: #409eff;
  color: white;
  text-align: center;
  line-height: 60px;
}

.title {
  margin: 0;
}

.main-content {
  padding: 20px;
}

.search-row {
  margin-bottom: 20px;
}

.search-input {
  margin-right: 10px;
}

.search-button {
  width: 100px;
}

.weather-cards {
  margin-top: 20px;
}

.box-card {
  margin-bottom: 20px;
}

.card-header {
  font-size: 18px;
  font-weight: bold;
}

.card-body p {
  margin: 10px 0;
}

</style>