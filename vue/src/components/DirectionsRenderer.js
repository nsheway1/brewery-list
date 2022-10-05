import { MapElementFactory } from "vue2-google-maps";

export default MapElementFactory({
  name: "directionsRenderer",
  ctr() {
    return window.google.maps.DirectionsRenderer;
  },
  events: [],
  mappedProps: {},
  props: {
    origin: { type: [Object, Array] },
    destination: { type: [Object, Array] },
    waypoints: { type: [Object, Array] },
    travelMode: { type: String }
  },
  afterCreate(directionsRenderer) {
    let directionsService = new window.google.maps.DirectionsService();
    this.$watch(
      () => [this.origin, this.destination, this.waypoints, this.travelMode],
      () => {
        let { origin, destination, waypoints, travelMode } = this;
        if (!origin || !destination || !waypoints || !travelMode) return;
        directionsService.route(
          {
            origin,
            destination,
            waypoints,
            travelMode,
          },
          (response, status) => {
            if (status !== "OK") return;
            directionsRenderer.setDirections(response);
          }
        );
      }
    );
  },
});