function reviewPageMove(page){

    let productId_ = document.getElementById("productId").value;
    let questionCurrentPageNo_ = document.getElementById("questionCurrentPageNo").value;

    location.href ='/product/detail?questionCurrentPageNo='+questionCurrentPageNo_+'&reviewCurrentPageNo='+page+'&productId='+productId_+'#production-selling-review';

}