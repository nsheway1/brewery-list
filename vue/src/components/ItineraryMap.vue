<template>
  <div>
    <div class="selector-flex">
      <div>
    <b class="selector-label">Start:</b>
    <select class="selector" v-model="start">
      <option v-for="brewery in this.$store.state.itinerary" :key="brewery" 
      :value="brewery.address_line_1 + ', ' + brewery.city + ', ' + brewery.state">
      {{brewery.name}}
      </option>
    </select>
    </div>
    <div>
    <b class="selector-label">End:</b>
    <select class="selector"  @change="waypointCalc" v-model="end">
      <option v-for="brewery in this.$store.state.itinerary" :key="brewery" 
      :value="brewery.address_line_1 + ', ' + brewery.city + ', ' + brewery.state">
      {{brewery.name}}
      </option>
    </select>
    </div>
    </div>
    <GmapMap :zoom="11" :center="{ lat: 39.96, lng: -82.9988 }">
      <DirectionsRenderer travelMode="DRIVING" :origin="origin" :destination="destination" :waypoints="waypoints" />
    </GmapMap>
  </div>
</template>

<script>
import DirectionsRenderer from "@/components/DirectionsRenderer";

export default {
  components: {
    DirectionsRenderer
  },

  data: () => ({
    start: "",
    end: "",
    waypoints: []
  }),

  computed: {
    origin() {
      if (!this.start) return null;
      return { query: this.start };
    },
    destination() {
      if (!this.end) return null;
      return { query: this.end };
    },
  },

  methods: {
    waypointCalc(){
      this.$store.state.itinerary.forEach(brewStop => {
        let point = {
          location: brewStop.address_line_1 + ', ' + brewStop.city + ', ' + brewStop.state
        }
        if(point.location != this.start && point.location != this.end){
          this.waypoints.push(point);
        } 
      })
    }
  }
};
</script>

<style>
.vue-map-container {
  height: 40rem;
  width: 50rem;
  border: 1rem solid rgb(236, 170, 55);
}

.selector{
  padding-right: 5rem;
  font-size: 2rem;
}

.selector-label{
  font-size: 2rem;
}

.selector-flex{
  margin-top: 1rem;
  margin-bottom: 1rem;
  display: flex;
  justify-content: space-around;
}

</style>