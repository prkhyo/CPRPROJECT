function reviewPageMove(page){

    let productId_ = document.getElementById("productId").value;
    let questionCurrentPageNo_ = document.getElementById("questionCurrentPageNo").value;

    location.href ='/product/detail?questionCurrentPageNo='+questionCurrentPageNo_+'&reviewCurrentPageNo='+page+'&productId='+productId_+'#production-selling-review';

}

document.addEventListener("DOMContentLoaded", function(){
   let classSet = document.getElementsByClassName('allReview');
   for(let i = 0; i <classSet.length;i++){
       console.log(classSet[i].value);
       if(classSet[i].value == 'helpful'){

           classSet[i].previousElementSibling.classList.add('production-review-item__help__btn--active');

           classSet[i].nextElementSibling.value = true;
   }
       console.log(classSet[i].nextElementSibling.value);
}

});


// 로그인 체크 필요 없게되면 추후 수정 예정
function logincheck(){
    let sessionUser_ = document.getElementById("memberId").value;

    if(sessionUser_ == null || sessionUser_ == ''){
        alert('로그인 후 이용가능합니다.');
        location.href ='/login';
    }else{
        return -1;
    }

}





function help(reviewId) {
    if (logincheck() == -1) {

        if (document.getElementsByName(reviewId)[0].value == false) {
            helpIncrease(reviewId);
            console.log('증가');
        } else {
            helpDecrease(reviewId);
            console.log('감소');
        }
    }
}

function helpDecrease(reviewId){
    document.getElementsByName(reviewId)[0].value= false;
    var btnClass = document.getElementById(reviewId);
    btnClass.classList.remove('production-review-item__help__btn--active');
    btnClass.innerText = '도움이 돼요';

    let productId_ = document.getElementById("productId").value;
    let questionCurrentPageNo_ = document.getElementById("questionCurrentPageNo").value;
    let reviewCurrentPageNo_ = document.getElementById("reviewCurrentPageNo").value;

    location.href='/review/help/decrease?reviewId='+reviewId+'&productId='+productId_+'&questionCurrentPageNo='+questionCurrentPageNo_+'&reviewCurrentPageNo='+reviewCurrentPageNo_;

}


function helpIncrease(reviewId){

   document.getElementsByName(reviewId)[0].value=true;

    let btnClass = document.getElementById(reviewId);
    btnClass.classList.add('production-review-item__help__btn--active');
    btnClass.innerText = '∨ 도움됨';


    let productId_ = document.getElementById("productId").value;
    let questionCurrentPageNo_ = document.getElementById("questionCurrentPageNo").value;
    let reviewCurrentPageNo_ = document.getElementById("reviewCurrentPageNo").value;


    location.href='/review/help/increase?reviewId='+reviewId+'&productId='+productId_+'&questionCurrentPageNo='+questionCurrentPageNo_+'&reviewCurrentPageNo='+reviewCurrentPageNo_;


}
