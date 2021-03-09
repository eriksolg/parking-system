<template>
    <div id="clockContainer">
        <time>
            <div id="time">{{ time }}</div>
            <div id="date">{{ date }}</div>
        </time>
    </div>
    
</template>

<script>
import moment from 'moment';

export default {
    name: 'Clock',
    data() {
        return {
            time: '',
            date: ''
        }
    },
    methods: {
        setClock() {
            this.time = moment().format('HH:mm:ss');
            this.date = moment().format('MM/DD/yyyy');
        }
    },
    mounted() {
        this.interval = setInterval(this.setClock, 1000);
    },
    beforeDestroy() {
        clearInterval(this.interval);
    }
}
</script>

<style scoped>
#clockContainer {
    background-color: #FEFFFF;

    height: 100%;
    width: 350px;
    margin-left: auto;
    
    justify-content: flex-end;
    align-self: center;
}

time {
    color: #2B7A78;
    align-self: center;
    display: block;
    height: 70%;
    align-self: center;
}

#time {
    font-size: 60px;
}

#date {
    font-size: 40px;
    float: right;
}

@media (max-width: 1024px) {
    #clockContainer {    
        width: 100%;
    }
}


</style>