// 숫자 format 설정
Number.prototype.format = function () {
    if (this == 0) return 0;

    let reg = /(^[+-]?\d+)(\d{3})/;
    let n = (this + '');

    while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');

    return n;
};

String.prototype.format = function () {
    var num = parseFloat(this);
    if (isNaN(num)) return "0";

    return num.format();
};

// 체크박스
function checkSelectAll() {
    // 전체 체크박스
    const checkboxes = document.querySelectorAll('input[name="selectNo"]');
    // 선택된 체크박스
    const checked = document.querySelectorAll('input[name="selectNo"]:checked');
    // select all 체크박스
    const selectAll = document.querySelector('input[name="selectall"]');

    if (checkboxes.length === checked.length) {
        selectAll.checked = true;
    } else {
        selectAll.checked = false;
    }

}

function selectAll(selectAll) {
    const checkboxes
        = document.getElementsByName('selectNo');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked
    })
}


// 상품 금액 계산
function totalPrice() {
    // 유닛 금액 [ 총 상품금액, 배송비] : 왼쪽
    let total = document.querySelectorAll('.totalSum');
    let deliveryCharge = document.querySelectorAll('.delivery');
    let unitPrice = document.querySelectorAll('.unitPrice');
    let qty = document.querySelectorAll('.qty');
    let totalLength = total.length;

    const sellerName = document.querySelectorAll('.sellerName');

    // 전체 금액 [ 상품금액, 배송비, 할인금액 및 총 결재금액] : 오른쪽
    let productTotalPrice = document.querySelector('.productTotalPrice');
    let deliveryChargeTotal = document.querySelector('.deliveryChargeTotal');

    let totalPayment = document.querySelector('.totalPayment');


    let productTotal = 0;
    let deliveryTotal = 0;

    // 상품 전체 금액 계산
    for (i = 0; i < totalLength; i++) {
        // 가변 금액 적용
        productTotal += parseInt(total[i].outerText);
        deliveryTotal += parseInt(deliveryCharge[i].outerText);

        // 포멧 변경 (,)
        total[i].outerText = total[i].outerText.format();
        deliveryCharge[i].outerText = deliveryCharge[i].outerText.format();
        unitPrice[i].outerText = unitPrice[i].outerText.format();
        qty[i].outerText = qty[i].outerText.format();

    }

    // 총 상품 금액 지정 및 포멧 적용
    productTotalPrice.innerText = parseInt(productTotal).format();
    deliveryChargeTotal.innerText = parseInt(deliveryTotal).format();
    totalPayment.innerText = parseInt(productTotal + deliveryTotal).format();
}



totalPrice();