<template>
  <div id="app">
    <AppHeader></AppHeader>
  </div>
</template>

<script>
import AppHeader from './components/AppHeader';

export default {
  name: 'App',
  components: {
    AppHeader
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
}
</style>
