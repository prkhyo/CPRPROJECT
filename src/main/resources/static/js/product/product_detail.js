
function loginCheck(){
    let sessionUser_ = document.getElementById("memberId").value;

    if(sessionUser_ == null || sessionUser_ == ''){
        alert('로그인 후 구매 가능합니다.');
        location.href ='/login';
    }else{
        return -1;
    }

}

function cartInsert(){
    if(loginCheck() == -1) {

        let productId_ = document.getElementById("productId").value;
        let quantity_ = document.getElementById("quantity").value;

        location.href='/product/insertTo/cart?productId='+ productId_ +'&quantity='+ quantity_;
    }


}


function payment(){
    if(loginCheck() == -1) {

        let productId_ = document.getElementById("productId").value;
        let quantity_ = document.getElementById("quantity").value;

        location.href='/product/moveTo/payment?productId='+ productId_ +'&quantity='+ quantity_;
    }


}



function priceInputValueCheck(desiredQuantity){


    var chkStyle = /[0-9]/ ;      //체크 방식(숫자)

    if(!(chkStyle.test(desiredQuantity))){
        desiredQuantity = 1;
        document.getElementById("desiredQuantity").value =1;
    }
    return desiredQuantity;
}


function quantityCheck(){
    let desiredQuantity = document.getElementById("desiredQuantity").value;
    let productQuantity = document.getElementById("product_quantity").value;
    let productPrice = document.getElementById("product_price").value;



    desiredQuantity = priceInputValueCheck(desiredQuantity);

    if(desiredQuantity == 0){
        priceReset();
        return;
    }

    let purchasePrice = desiredQuantity * productPrice;
    let maxPurchasePrice = productQuantity * productPrice;

    if(purchasePrice > maxPurchasePrice){
        alert('선택한 개수가 너무 큽니다. 도움이 필요하시면 고객센터에 문의해 주세요. < 제품 재고량: '+productQuantity+' >');
        priceReset();
        return;

    }

    priceCalculation(desiredQuantity);

}


function priceCalculation(productQuantity){
    let productPrice = document.getElementById("product_price").value;
    let purchasePrice = (productQuantity * productPrice).toLocaleString('ko-KR');

    for(var i = 0; i < 2; i++){
        document.getElementsByClassName("purchasePrice")[i].innerHTML = purchasePrice;
        document.getElementById("purchasePrice").value = purchasePrice;
    }

    document.getElementById("quantity").value = productQuantity ;



}


function priceReset(){
    let productPrice = document.getElementById("product_price_comma").value;
    document.getElementById("desiredQuantity").value = 1;
    for(var i = 0; i < 2; i++){
        document.getElementsByClassName("purchasePrice")[i].innerHTML = productPrice;
    }
    document.getElementById("purchasePrice").value = productPrice;

}




function showShare(){

    let share =  document.getElementById("shareSns");
    share.style.visibility='visible';

}

function hideShare(){
    let share =  document.getElementById("shareSns");
    share.style.visibility='hidden';
}

function shareFacebook(){

    var url = window.document.location.href;
    window.open("http://www.facebook.com/sharer/sharer.php?u=" + url);
}


function shareKakao(){

    var url = window.document.location.href;
    var title = document.getElementById("product_title").value;
    // 사용할 앱의 JavaScript 키 설정
    Kakao.init('fd7626ea3a5e0e31243689e0c229e721');

    // 카카오링크 버튼 생성
    Kakao.Link.createDefaultButton({
        container: '#kakaoBtn', // 카카오공유버튼ID
        objectType: 'feed',
        content: {
            title: "레드홈 판매 상품: ", // 보여질 제목
            description: title, // 보여질 설명
            imageUrl: url, // 콘텐츠 URL
            link: {
                mobileWebUrl: url,
                webUrl: url
            }
        }
    });
}

function shareNaver(){

    var url = window.document.location.href;
    var title = '레드홈 판매 상품: '+ document.getElementById("product_title").value;
    var shareURL = "https://share.naver.com/web/shareView?url=" + url + "&title=" + title;
    document.location = shareURL;

}

function shareUrl(){

    var url = '';
    var textarea = document.createElement("textarea");
    document.body.appendChild(textarea);
    url = window.document.location.href;
    textarea.value = url;
    textarea.select();
    document.execCommand("copy");
    document.body.removeChild(textarea);
    alert("클립보드에 복사되었습니다.")
}

