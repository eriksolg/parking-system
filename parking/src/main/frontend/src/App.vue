<template>
  <div id="app">
    <AppHeader></AppHeader>
    <ParkingInfo></ParkingInfo>
    <AppFooter></AppFooter>
  </div>
</template>

<script>
import AppHeader from './components/AppHeader/AppHeader';
import AppFooter from './components/AppFooter/AppFooter';
import ParkingInfo from './components/ParkingInfo/ParkingInfo';

export default {
  name: 'App',
  components: {
    AppHeader,
    ParkingInfo,
    AppFooter
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&family=Roboto:wght@100&display=swap');
@import url('https://meyerweb.com/eric/tools/css/reset/reset.css');

#app {
  font-family: 'Roboto', sans-serif;
  background-color: #DEF2F1;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

body, html {
  height: 100%;
}
</style>
