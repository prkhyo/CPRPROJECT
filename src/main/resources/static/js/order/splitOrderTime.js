
window.onload = function() {
    let orderTime = document.getElementById('orderTime').innerText;
    console.log('orderTime = ', orderTime);
    let orderTimeArr = orderTime.split('T');
    document.getElementById('orderTime').innerText = orderTimeArr[0];
}