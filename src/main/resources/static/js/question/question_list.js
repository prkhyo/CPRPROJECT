function pageMove(page){

    let productId = document.getElementById("productId").value;
    let reviewCurrentPageNo = document.getElementById("reviewCurrentPageNo").value;
    let reviewSort = document.getElementById("reviewSort").value;

    location.href ='/product/detail?questionCurrentPageNo='+page+'&reviewCurrentPageNo='+reviewCurrentPageNo+'&productId='+productId+'&reviewSort='+reviewSort+'#production-selling-question';

}


// 로그인 체크 필요 없게되면 추후 수정 예정
function writeQuestion(){
    let productId = document.getElementById("productId").value;
    let sessionUser = document.getElementById("memberId").value;
    if(sessionUser == null || sessionUser == ''){
        alert('로그인 후 문의 글 작성이 가능합니다.');
        location.href ='/login';
    }else{
        location.href = '/question/add?productId='+productId;
    }

}

function writeAnswer(){
    let productId = document.getElementById("productId").value;
    let questionId = document.getElementById("questionId").value;
    let sessionUser_ = document.getElementById("memberId").value;
    if(sessionUser_ == null || sessionUser_ == ''){
        alert('로그인 후 문의 글 작성이 가능합니다.');
        location.href ='/login';
    }else{
        location.href = '/answer/add?productId='+productId+'&questionId='+questionId;
    }
}


function questionDelete(questionId){
    let productId = document.getElementById("productId").value;
    let questionCurrentPageNo = document.getElementById("questionCurrentPageNo").value;

    location.href = '/question/questionDelete?questionId='+questionId+"&questionCurrentPageNo="+questionCurrentPageNo+"&productId="+productId;


}

function answerDelete(answerId){
    let productId_ = document.getElementById("productId").value;
    let questionCurrentPageNo_ = document.getElementById("questionCurrentPageNo").value;
    let questionId_ = document.getElementById("questionId").value;

    location.href = '/answer/answerDelete?answerId='+answerId+'&questionCurrentPageNo='+questionCurrentPageNo_+"&productId="+productId_+"&questionId="+questionId_;
}