<template>
  <div v-if="loaded"> 
         <GmapMap 
  :center='center'
  :zoom="17"
  map-type-id="terrain"
  style="width: 100%; height: 25rem;"
>
  <GmapMarker
    :key="index"
    v-for="(m, index) in markers"
    :position="m.position"
    :clickable="true"
    :draggable="true"
    @click="center=m.position"
  />
</GmapMap>
  </div>
</template>

<script>

export default {
    name: 'brewery-map',
    props: ['brewery'],
    data() {
        return {
            loaded: false,
            center: null,
            markers: null,
            marker: null,
            addressObj: {
              address_line_1: '',
              address_line_2: '',
              city: '',
              state: '',
              zip_code: '',
              country: 'United States'
            },
        }
    },
       created() {
            this.addressObj.address_line_1 = this.brewery.streetAddress;
            this.addressObj.city = this.brewery.city;
            this.addressObj.state = this.brewery.state;
            this.addressObj.zip_code = this.brewery.zipcode;
    },

    mounted() {
              this.$geocoder.send(this.addressObj, response => {
              const tempMarker = response.results[0].geometry.location
              let tempMarkers = []
              tempMarkers.push({position: tempMarker})
              this.marker = tempMarker;
              this.markers = tempMarkers;
              this.center = tempMarker;
              this.loaded = true;
            })
    },

}
</script>

<style>

</style>