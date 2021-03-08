<template>
    <div id="titleContainer">
        <h1>Parking System</h1>
    </div>
    
</template>

<script>
export default {
    name: 'Title'
}
</script>

<style scoped>
#titleContainer {    
    height: 80%;
    justify-content: flex-start;

    align-self: center;
    margin-left: 20px;
}

h1 {
    color: #17252A;
    font-size: 70px;
    font-weight: 500;
}

@media (max-width: 1024px) {
    h1 {
        font-size: 50px;
    }
}

</style>