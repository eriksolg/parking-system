<template>
    <div id="parkingInfoContainer">
        <table>
            <thead>
                <th>
                    First Name
                </th>
                <th>
                    Last Name
                </th>
                <th>
                    Floor
                </th>
                <th>
                    Slot
                </th>
            </thead>
            <tbody>
                <tr 
                    :key="reg.id"
                    v-for="reg in registrations"
                >
                <td>
                    {{ reg.firstName }}
                </td>
                <td>
                    {{ reg.lastName }}
                </td>
                <td>
                    {{ reg.floor }}
                </td>
                <td>
                    {{ reg.slot }}
                </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from 'axios';
import Vue from 'vue';

export default {
    name: 'ParkingInfo',
    data() {
        return {
            registrations: [],
            customers: []
        }
    },
    methods: {
        async fetchRegistrations() {
            let fetchedData = [];
            await axios.get('/api/registrations', {
                params: {
                    active: true
                }
            }).then(response => {
                fetchedData = response.data;
                
            }).catch(err => console.log(err));

            fetchedData.forEach(async registration => {
                let customerId = registration.customerId;
                let slotId = registration.slotId;

                let customer;
                let slot;

                await axios.get(`/api/customers/${customerId}`).then(response => {
                    customer = response.data;
                }).catch(err => console.log(err));


                await axios.get(`/api/floors/slots/${slotId}`).then(response => {
                    slot = response.data;
                }).catch(err => console.log(err));

                Vue.set(registration, 'firstName', customer.firstName);
                Vue.set(registration, 'lastName', customer.lastName);
                Vue.set(registration, 'slot', slot.name);
                Vue.set(registration, 'floor', slot.floor);
            });

            this.registrations = fetchedData;
        }
    },
    mounted() {
        this.fetchRegistrations();
        this.interval = setInterval(this.fetchRegistrations, 30000);
    },
    beforeDestroy() {
        clearInterval(this.interval);
    }
}
</script>

<style scoped>
table {
    margin: auto;
  width: 800px;
  border-collapse: collapse;
  overflow: hidden;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

@media (max-width: 1024px) {
    table {
        width: 80%;
    }

    td, th {
        font-size: 15px !important;
    }

}
th,
td {
  padding: 15px;
  background-color: rgba(255, 255, 255, 0.2);
  font-weight: 600;
  font-size: 20px;
}

th {
  color: white;
}

td {
    color: #17252a;
}

th {
  text-align: left;
}
thead th {
  background-color: #2b7a78;
}
tbody tr:hover {
  background-color: rgba(255, 255, 255, 0.3);
}
tbody td {
  position: relative;
}
tbody td:hover:before {
  content: "";
  position: absolute;
  left: 0;
  right: 0;
  top: -9999px;
  bottom: -9999px;
  background-color: rgba(255, 255, 255, 0.2);
  z-index: -1;
}


</style>