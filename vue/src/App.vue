<template>
  <div id="app">
   
    <header id="nav">
      <div id="site-logo">  
      <img id="beer" class="gif" src="../src/img/beer.gif" />
      <router-link class="logo-link" v-bind:to="{ name: 'all-breweries'}">
      <h1 class="site-logo">Brew Hub</h1>
      </router-link>
      </div>
      <h1 class="page-name">{{this.$store.state.currentPage}}</h1>
      <div id="links">
      <div v-if="!this.$store.state.token" class="link" @mouseover="hover=true" @mouseleave="hover=false">
        <router-link v-bind:to="{ name: 'login'}">
      <img v-if="!hover" class="tap" src="../src/img/tap.jpg" />
        <img v-else class="tap" src="../src/img/beerglass.jpg" />
      Login</router-link>
        </div>
        <div v-if="this.$store.state.token" class="link" @mouseover="hoverThree=true" @mouseleave="hoverThree=false">
      <img v-if="!hoverThree" class="tap" src="../src/img/tap.jpg" />
        <img @click="logout" v-else class="tap" src="../src/img/beerglass.jpg" />Logout
        </div>
        <div class="link" @mouseover="hoverTwo=true" @mouseleave="hoverTwo=false">
          <router-link  v-bind:to="{ name: 'all-breweries'}">
        <img v-if="!hoverTwo" class="tap" src="../src/img/tap.jpg" />
        <img v-else class="tap" src="../src/img/beerglass.jpg" />
      Home</router-link>
      </div>
      </div>
    </header>

    <router-view class="main" />
  </div>
</template>

<script>
export default {
  data(){
    return{
      hover: false,
      hoverTwo: false,
      hoverThree: false
    } 
  },
  methods: {
    logout(){
      this.$store.commit("LOGOUT");
    }
  }
}
</script>

<style>

#nav{
  display: flex;
  justify-content: space-between;
  align-items: center;
  /* background-color:  #d6d8da; */
  color: black;
  border-bottom: .1rem solid black;
  /* background: linear-gradient(165deg, #d6d8da 20%, green 20%); */
  background: -webkit-linear-gradient(165deg, #d6d8da 20%, rgba(0, 0, 0, 0) 20%), -webkit-linear-gradient(165deg, rgb(236, 170, 55) 70%, #d6d8da 70%);
    background: -o-linear-gradient(165deg, #d6d8da 20%, rgba(0, 0, 0, 0) 20%), -o-linear-gradient(165deg, rgb(236, 170, 55) 70%, #d6d8da 70%);
    background: -moz-linear-gradient(165deg, #d6d8da 20%, rgba(0, 0, 0, 0) 20%), -moz-linear-gradient(165deg, rgb(236, 170, 55) 70%, #d6d8da 70%);
    background: linear-gradient(165deg, #d6d8da 20%, rgba(0, 0, 0, 0) 20%), linear-gradient(165deg, rgb(236, 170, 55) 70%, #d6d8da 70%);
}

.gif{
  height: 5rem;
}

.tap{
  height: 3rem;
}

#links{
  display: flex;
  align-items: center;
}

.logo-link{
  text-decoration: none;
  color: inherit;
}

.site-logo{
  padding-left: 1rem;
  margin-top: 0rem;
  margin-bottom: .5rem;
  
  font-family: 'Bungee', cursive;
  font-size: 2.4rem;
  transform: rotate(-15deg);
}

.link{
  padding-right: 1rem;
  display: inline-block;
  cursor: pointer;
}

.link:hover{
  background-color: rgb(167, 161, 161);
}

.link a{
  text-decoration: none;
  color: inherit;
}

#app {
   font-family: 'Merriweather', serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #d6d8da;
}

html {
  background: url("../src/img/background.jpg") no-repeat center center fixed;
  background-size: cover;
}

.page-name {
  font-family: 'Bungee', cursive;
}


@import url('https://fonts.googleapis.com/css2?family=Bungee&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Merriweather&family=Tangerine:wght@700&display=swap');

</style>
