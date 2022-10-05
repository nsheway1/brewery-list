<template>
  <div class="sidebar" :id="id" @dragover.prevent @drop.prevent="drop" >
      <h3 class="itinerary-title">Drag brewery here to add to itinerary!</h3>
      <ul>
      <li @click="setupItinerary">Brew My Trip
        <span></span><span></span><span></span><span></span>
      </li>
      </ul>
</div>
</template>

<script>
import breweryService from '@/services/BreweryService.js'
export default {
    name: 'sidebar',
    props: ['id'],
    data(){
        return{
            breweryNames: []
        }
    },

    methods: {
    drop(e) {
      const box_id = e.dataTransfer.getData('box_id');
      const name = document.createElement('h3');
      name.innerText = box_id;
      e.target.appendChild(name);
      this.breweryNames.push(box_id);
    },
    setupItinerary(){
        breweryService.getItineraryList(this.breweryNames).then( response => {
            const tempArr = response.data;
            // let markers = [];
            let addressObjArr = [];
            tempArr.forEach(brewery => {
                let addressObj = {
                    name: brewery.name,
                    address_line_1: brewery.streetAddress,
                    address_line_2: '',
                    city: brewery.city,
                    state: brewery.state,
                    zip_code: brewery.zipcode,
                    country: 'United States'
                };
                addressObjArr.push(addressObj);
                // this.$geocoder.send(addressObj, response => {
                //     let tempMarker = response.results[0].geometry.location;
                //     markers.push({position: tempMarker});
                // });
            });
            setTimeout(() => this.$store.commit("GENERATE_ITINERARY", addressObjArr), 1500);
            setTimeout(() => this.$router.push({name: 'itinerary-view'}), 1501);
        })
    }
  }
}
</script>

<style>

.itinerary-title {
  color: wheat;
  font-family: sans-serif;
  -webkit-text-stroke: 2px black;
	-webkit-text-fill-color: wheat;
  font-size: 2rem;
}

ul li {
    --c: goldenrod;
    color: var(--c);
    font-size: 16px;
    border: 0.3em solid var(--c);
    border-radius: 0.5em;
    width: 12em;
    height: 3em;
    text-transform: uppercase;
    font-weight: bold;
    font-family: sans-serif;
    letter-spacing: 0.1em;
    text-align: center;
    line-height: 3em;
    position: relative;
    overflow: hidden;
    z-index: 1;
    transition: 0.5s;
    margin: 1em;
}

ul li span {
    position: absolute;
    width: 25%;
    height: 100%;
    background-color: var(--c);
    transform: translateY(150%);
    border-radius: 50%;
    left: calc((var(--n) - 1) * 25%);
    transition: 0.5s;
    transition-delay: calc((var(--n) - 1) * 0.1s);
    z-index: -1;
}

ul li:hover {
    color: black;
    cursor: pointer;
}

ul li:hover span {
    transform: translateY(0) scale(2);
}

ul li span:nth-child(1) {
    --n: 1;
}

ul li span:nth-child(2) {
    --n: 2;
}

ul li span:nth-child(3) {
    --n: 3;
}

ul li span:nth-child(4) {
    --n: 4;
}

.sidebar{
  position: fixed;
  height: 32rem;
  width:19rem;
  margin-top: 13rem;
  top: 0;
  left: 0;

  border: .1rem black solid;
  border-radius: 1rem;
  background-color:rgba(251, 170, 27, .5);
  box-shadow: rgba(251, 170, 27, .5) 5px 5px, 
  rgba(251, 170, 27, .3) 10px 10px, 
  rgba(251, 170, 27, .2) 15px 15px,
  rgba(251, 170, 27, .1) 20px 20px,
  rgba(251, 170, 27, .005) 25px 25px;
}

</style>