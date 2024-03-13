
var menu = document.getElementById('menu');
var search = document.getElementById("search");
document.getElementById("open__menu").addEventListener('click', () => {
    menu.style.display = "block";
});

document.getElementById("close__menu").addEventListener('click', ()=>{
    menu.style.display = "none";
    search.style.display = "none";
});

document.getElementById("open__search").addEventListener('click', ()=> {
    search.style.display = "block";
});

document.getElementById("close__search").addEventListener('click', ()=>{
    search.style.display = "none";
    menu.style.display = "none";
});