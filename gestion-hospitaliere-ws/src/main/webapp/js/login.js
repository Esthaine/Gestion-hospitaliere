const typeUsers = document.querySelectorAll('.type-user');

typeUsers.forEach(typeUser => {
    typeUser.addEventListener('click', function() {
        const activeTab = document.querySelector('.type-user.active');
        if(this.classList.contains('active')) return;
        this.classList.add('active');
        if(activeTab){
            console.log('Is there')
            activeTab.classList.remove('active');
        }
    });

});