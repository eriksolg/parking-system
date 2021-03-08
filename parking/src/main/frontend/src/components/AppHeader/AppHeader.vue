<template>
    <div>
        <header>
            <Title></Title>
            <Clock></Clock>
        </header>
    </div>
</template>

<script>
import Title from './Title';
import Clock from './Clock';

export default {
    name: 'AppHeader',
    components: {
        Title,
        Clock
    }
}
</script>

<style scoped>

header {
    width: 100%;
    height: 100px;
    background-color: #3AAFA9;

    display: flex;
    flex-direction: row;
}

@media (max-width: 1024px) {
    header {    
        height: 200px;
        flex-direction: column;
    }
}

</style>