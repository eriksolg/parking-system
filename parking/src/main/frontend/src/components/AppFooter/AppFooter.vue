<template>
    <div>
        <footer>
            <a href="/swagger-ui/">API</a>
        </footer>
    </div>
</template>

<script>

export default {
    name: 'AppFooter'
}
</script>

<style scoped>


footer {
    width: 100%;
    height: 60px;
    background-color: #3aafa93b;
}

footer:hover {
    background-color: #FEFFFF;
}

a {
    height: 100%;
    width: 100%;
    display: block;
    color: #17252A;
    text-decoration: none;
    font-size: 50px;
    text-align: center;
}

</style>