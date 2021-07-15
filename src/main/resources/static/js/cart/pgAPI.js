

function payment() {
    let receiver = document.querySelectorAll('.receiver')[0].value;
    let phone = document.querySelectorAll('.phone')[0].value;
    let add1 = document.querySelectorAll('.add1')[0].value;
    let email = document.querySelectorAll('.email')[0].value;
    let name = document.querySelectorAll('.name')[0].value;
    let finalPrice = document.querySelectorAll('.finalPrice')[0].outerText;

    let  check = document.querySelector('input[name="selectCheck()"]');
    let  checked = document.querySelector('input[name="selectCheck()"]:checked');

    if(check !==  checked){
        alert("동의 해주세용 ")
    }else if (receiver == "") {
        alert("받는 사람을 입력해 주세요.");
    } else if (phone == "") {
        alert("연락처를  입력해 주세요.");
    } else if (add1 == "") {
        alert("주소를 입력해 주세요.");

    } else {
        var IMP = window.IMP;
        IMP.init("imp65630741");
        IMP.request_pay({
            pg: 'html5_inicis',
            pay_method: 'card',
            merchant_uid: 'merchant_' + new Date().getTime(),
            name: 'REDHOME(가구판매)', //결제창에서 보여질 이름
            amount: finalPrice, //실제 결제되는 가격
            buyer_email: email,
            buyer_name: name,
            buyer_tel: phone,
        }, function (rsp) { // callback
            if (rsp.success) {
                var msg = '결제가 완료되었습니다.';

                dataSend();
                location.href = "/cart";


            } else {
                var msg = '결제에 실패하였습니다.';
            }
            alert(msg);
            orderForm.submit();
        });
    }

}


// 결제 성공 시 실행
function dataSend() {

////////////////////////////  사용자 정보  //////////////////////////////////////
    let receiver = document.querySelectorAll('.receiver')[0].value;
    let phone = document.querySelectorAll('.phone')[0].value;
    let add1 = document.querySelectorAll('.add1')[0].value;
    let add2 = document.querySelectorAll('.add2')[0].value;
    let add3 = document.querySelectorAll('.add4')[0].value;
    let add4 = add1 + " " + add2 + " " + add3;
    let request = document.querySelectorAll('.request')[0].value;
    let addPoints = document.querySelectorAll('.addPoint');



////////////////////////////  제품 정보  //////////////////////////////////////
    let unitPrice = document.querySelectorAll('.unitPrice');
    let qty = document.querySelectorAll('.qty');
    let productId = document.querySelectorAll('.productId');
    let cartId = document.querySelectorAll('.cartId');
    let accountId = document.querySelectorAll('.accountId')[0].value;
    let memberId = document.querySelectorAll('.memberId')[0].value;
    let usedPoint = document.querySelectorAll('.usedPoint')[0].value;
    let deliveryCharge = document.querySelectorAll('.deliveryCharge');
    // let totalPoint = document.querySelectorAll('.totalPoint');

    // 주문번호 orderId 생성을 위한 날짜 함수
    const today = new Date();
    const year = today.getFullYear(); // 년도
    const month = today.getMonth() + 1;  // 월
    const date = today.getDate();  // 날짜
    const hours = today.getHours(); // 시
    const minutes = today.getMinutes();  // 분
    const seconds = today.getSeconds();  // 초

    // 주문번호 생성
    let orderId = memberId + "" + year + "" + month + "" + date + "-" + hours + "" + minutes + "" + seconds



    // json값 만들기 위한 배열 객체 생성
    let forJson = new Array();

    for (let i = 0; i < unitPrice.length; i++) {
        let data = new Object();

        data.orderId = orderId;
        data.address = add4;
        data.accountId = accountId;
        data.memberId = parseFloat(memberId);
        data.productId = parseInt(productId[i].value);
        data.cartId = parseInt(cartId[i].value);
        data.receiver = receiver;
        data.phoneNumber = phone;
        data.orderRequest = request;
        data.deliveryCharge = deliveryCharge[i].outerText;
        // data.totalPoint = totalPoint[0].outerText;
        data.price = parseInt(unitPrice[i].outerText);
        data.qty = parseInt(qty[i].outerText);
        data.usedPoint = parseFloat(usedPoint / unitPrice.length);
        data.totalPoint = parseInt(usedPoint);
        data.addPoint =  parseInt(addPoints[0].outerText);

        forJson.push(data);
    }

    const jsonData = JSON.stringify(forJson);

    ajax(jsonData)

}

//  server로 값 전달
function ajax(data) {

    // console.log("data   " + data);

    let httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", "/cart/payments");
    httpRequest.setRequestHeader("Content-Type", "application/json");
    httpRequest.send(data);
    location.reload();

}