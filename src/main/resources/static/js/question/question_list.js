function pageMove(page){

    let productId_ = document.getElementById("productId").value;
    location.href ='/product/detail?questionCurrentPageNo='+page+'&productId='+productId_+'#production-selling-question';

}


function writeQuestion(){
    let productId = document.getElementById("productId").value;
    let sessionUser_ = document.getElementById("sessionUser").value;
    if(sessionUser_ == null || sessionUser_ == ''){
        alert('로그인 후 문의 글 작성이 가능합니다.');
        location.href ='/login';
    }else{
        location.href = '/question/add?productId='+productId;
    }

}


function questionDelete(questionId){
    let productId_ = document.getElementById("productId").value;
    let questionCurrentPageNo_ = document.getElementById("questionCurrentPageNo").value;

    location.href = '/question/questionDelete?questionId='+questionId+"&questionCurrentPageNo="+questionCurrentPageNo_+"&productId="+productId_;


}