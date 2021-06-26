

function setEventListener(items) {

    const deleteBtn = document.querySelector('.selectDelete');

    deleteBtn.addEventListener('click', () => console.log("items" + items));

}