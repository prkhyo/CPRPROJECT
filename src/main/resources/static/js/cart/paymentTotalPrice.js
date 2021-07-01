Number.prototype.format = function (){
    if(this==0) return 0;

    let reg = /(^[+-]?\d+)(\d{3})/;
    let n = (this + '');

    while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');

    return n;
};
String.prototype.format = function(){
    var num = parseFloat(this);
    if( isNaN(num) ) return "0";

    return num.format();
};



function calculatePrice(point) {

    let totalPrice = document.querySelectorAll('.totalPrice');
    let deliveryCharge = document.querySelectorAll('.deliveryCharge');
    let finalPrice = document.querySelectorAll('.finalPrice');

    let productTotalSum = document.querySelector('.productTotalSum');
    let deliveryTotalCharge = document.querySelector('.deliveryTotalCharge');
    let pointTotalUse = document.querySelectorAll('.totalPoint');



    // 초기 값
    let total = 0;
    let delivery = 0;

    // 아이템 총액 계산
    for (let i = 0; i < totalPrice.length; i++) {

        total += parseInt(totalPrice[i].outerText);
        delivery += parseInt(deliveryCharge[i].outerText);

    }

// 금액 입히기
    productTotalSum.innerText = total.format();
    deliveryTotalCharge.innerText = delivery.format();


    finalPrice[0].innerText = parseInt(total + delivery - point);

    addPoint();

}

calculatePrice(0);


// 포인트 사용
function pointUse() {

    //사용 포인트
    let usedPoint = document.querySelectorAll('.usedPoint')[0].value;
    // 보유 포인트
    let havePoint = document.querySelectorAll('.havePoint')[0].outerText;
    // 최종금액
    let finalPrice = document.querySelectorAll('.finalPrice')[0].outerText;

    if (havePoint - usedPoint < 0) {
        alert("사용 포인트가 보유 포인트 보다 적습니다.")

    }else if (usedPoint > finalPrice) {
        alert("결제금액을 오버합니다.")

        document.querySelectorAll('.usedPoint')[0].innerText = finalPrice.format();
        // 잔여 포인트 변경
        document.querySelectorAll('.havePoint')[0].outerText = (havePoint - usedPoint).format();
        // 포인트 적용
        document.querySelectorAll('.totalPoint')[0].outerText = finalPrice.format();

        calculatePrice(finalPrice);
    } else {
        // 잔여 포인트 변경
        document.querySelectorAll('.havePoint')[0].outerText = (havePoint - usedPoint).format();
        // 포인트 적용
        document.querySelectorAll('.totalPoint')[0].outerText = usedPoint.format();

        calculatePrice(usedPoint);
    }

}

function addPoint() {
    let gradeId = document.querySelectorAll('.gradeId')[0].value;
    let finalPrice = document.querySelectorAll('.finalPrice')[0].outerText;
    let addPoint = document.querySelectorAll('.addPoint');


    if (gradeId == 1) {

        addPoint[0].outerText = Math.round(finalPrice * 0.001).format()
    } else if (gradeId == 2) {
        addPoint[0].outerText =  Math.round(finalPrice * 0.002).format()
    } else if (gradeId == 3) {
        addPoint[0].outerText =  Math.round(finalPrice * 0.003).format()
    } else if (gradeId == 4) {
        addPoint[0].outerText =  Math.round(finalPrice * 0.004).format()
    } else if (gradeId == 5) {
        addPoint[0].outerText =  Math.round(finalPrice * 0.005).format()
    }

}
