function pageMove(page){

    let productId_ = document.getElementById("productId").value;
    location.href ='/product/detail?questionCurrentPageNo='+page+'&productId='+productId_+'#production-selling-question';

}

function writeQuestion(){
    let productId = document.getElementById("productId").value;

    location.href = '/question/add?productId='+productId;

}