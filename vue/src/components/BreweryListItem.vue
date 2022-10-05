<template>
<div class ="brewery-div" :id="id" :draggable="draggable" @dragstart="dragStart" @dragover.stop>  
  <router-link class="brewery-link" v-bind:to="{ name: 'brewery-details', params: {id: brewery.id}}">
  <div class="brewerybox">
      <h1 class="brewery-list-title">{{brewery.name}}</h1>
      <img draggable="false" :src="imageUrl" /> 
  </div>
  </router-link>
  <div class="social-parent">
  <div class="social">
    <!-- <div class="hvr-grow"> -->
    <p class="like-text">Like {{this.brewery.name}} ?</p>
  <img v-if="!voted && !voteLocked" class="unliked" src="../img/singleBeerEmpty.jpg" @click="voted=true; updateBrewVotes(brewery, brewery.id)"/>
  <img v-if="voted" class="liked" src="../img/twinBeersFull.jpg"/>
  <!-- </div> -->
  </div>
  </div>
</div>  
</template>



<script>
import axios from 'axios'
import breweryService from '@/services/BreweryService.js'

export default {
    name: 'brewery-list-item',
    props: ['brewery', 'id', 'draggable'],
    data(){
      return{
        imageUrl: '',
        isLoaded: false,
        voted: false,
        voteLocked: false
      }
    },
    created(){
      axios.get('https://us-central1-brewery-finder-f943e.cloudfunctions.net/getImageUrl', { params: { name: this.brewery.name + '.jpg' }})
      .then(response => {
        this.imageUrl = response.data;
      });
      // this.isLoaded = true;
    },

    methods: {
      updateBrewVotes(breweryToUpdate, breweryId) {
        breweryService.updateVoteCount(breweryToUpdate, breweryId);
        this.voteLocked = true;
        setTimeout(() => this.$store.commit("INCREMENT_VOTE_COUNT"), 500);
      },
        dragStart: e => {
        const target = e.target;
        const dragIcon = document.getElementById('beer'); 
        e.dataTransfer.setDragImage(dragIcon, 10, 10);
        e.dataTransfer.setData('box_id', target.id);
    }
    }
}
</script>

<style>
.like-text {
  font-size: 1rem;
}

.social-parent {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  
  
}

.social {
  display: flex;
  background-color: wheat;
  justify-content: center;
  max-width: 22rem;
  max-height: 3rem;
  border: 3px solid rgb(141, 109, 3);
  border-radius: 3px;
  padding: 0rem .5rem 0rem .5rem; 
  font-weight: 700;
 
  
}


.unliked{
  
  height: 3rem;
  margin-top: 0rem;

}

@-webkit-keyframes hvr-pulse {
  25% {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }
  75% {
    -webkit-transform: scale(0.9);
    transform: scale(0.9);
  }
}
@keyframes hvr-pulse {
  25% {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }
  75% {
    -webkit-transform: scale(0.9);
    transform: scale(0.9);
  }
}
.hvr-pulse {
  display: inline-block;
  vertical-align: middle;
  -webkit-transform: perspective(1px) translateZ(0);
  transform: perspective(1px) translateZ(0);
  box-shadow: 0 0 1px rgba(0, 0, 0, 0);
}
.hvr-pulse:hover, .hvr-pulse:focus, .hvr-pulse:active {
  -webkit-animation-name: hvr-pulse;
  animation-name: hvr-pulse;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-timing-function: linear;
  animation-timing-function: linear;
  -webkit-animation-iteration-count: infinite;
  animation-iteration-count: infinite;
}



.unliked:hover {

  cursor: pointer;
}

.liked {
  height: 3rem;
  margin-top: 0rem;
}

.brewery-div{
  margin-bottom: 4rem;
  
}

.brewery-link{
  text-decoration: none;
  color: inherit;
}

.brewerybox{
  border: .1rem black solid;
  border-radius: 1rem;
  -webkit-transform: perspective(1px) translateZ(0);
  transform: perspective(1px) translateZ(0);
  -webkit-transition-duration: 0.3s;
  transition-duration: 0.3s;
  -webkit-transition-property: transform;
  transition-property: transform;
  -webkit-transition-timing-function: ease-out;
  transition-timing-function: ease-out;
  background-color:rgba(251, 170, 27, .5);
  margin-bottom: 3%;
  box-shadow: rgba(251, 170, 27, .5) 5px 5px, 
  rgba(251, 170, 27, .3) 10px 10px, 
  rgba(251, 170, 27, .2) 15px 15px,
  rgba(251, 170, 27, .1) 20px 20px,
  rgba(251, 170, 27, .005) 25px 25px;
  
}



.brewerybox:hover, .brewerybox:focus, .brewerybox:active {
  -webkit-transform: translateY(-8px);
  transform: translateY(-8px);
}
.brewerybox img{
  width: 20rem;
  height: 15rem;
  margin-bottom: 2%;
}

.brewery-list-title{
  font-size: 2.3rem;
  background-color: rgb(0, 0, 0, .6);
  font-weight: normal;
}

@keyframes shake {
 0% { transform: translate(1px, 1px) rotate(0deg); }
  10% { transform: translate(-1px, -2px) rotate(-1deg); }
  20% { transform: translate(-3px, 0px) rotate(1deg); }
 30% { transform: translate(3px, 2px) rotate(0deg); }
 40% { transform: translate(1px, -1px) rotate(1deg); }
 50% { transform: translate(-1px, 2px) rotate(-1deg); }
 60% { transform: translate(-3px, 1px) rotate(0deg); }
 70% { transform: translate(3px, 1px) rotate(-1deg); }
 80% { transform: translate(-1px, -1px) rotate(1deg); }
 90% { transform: translate(1px, 2px) rotate(0deg); }
 100% { transform: translate(1px, -2px) rotate(-1deg); }
}



</style>