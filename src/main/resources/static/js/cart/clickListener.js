

let delBtn = document.getElementById('selectDelete').addEventListener('click',
    function (e) {

        const checkId = document.querySelectorAll('input[name="selectNo"]');
        let forJson = new Array();

        for (let i = 0; i < checkId.length; i++) {
            let data = new Object();
            if (document.getElementsByName("selectNo")[i].checked == true) {
                data.cartId = checkId[i].value;
                forJson.push(data);
            }
        }
        if (forJson == "") {
            e.preventDefault();
            alert("제거 할 물품을 선택해 주세요")
        } else if(confirm("선택한 상품을 제거 하시겠습니까?")){
            const jsonData = JSON.stringify(forJson);
            e.preventDefault();
            ajax(jsonData, "POST", "/cart/delete");

        }else{
            alert("취소 되었습니다.");
            e.preventDefault();
        }
    });



// 구매하기 버튼 클릭 시 이벤트 발생
let subBtn = document.getElementById('submitBtn').addEventListener('click',
    function (e) {

        const checkId = document.querySelectorAll('input[name="selectNo"]');
        let forJson = new Array();

        for (let i = 0; i < checkId.length; i++) {
            let data = new Object();
            if (document.getElementsByName("selectNo")[i].checked == true) {
                data.cartId = checkId[i].value;
                forJson.push(data);
            }
        }
        if (forJson == "") {
            e.preventDefault();
            alert("구매 할 물품을 선택해 주세요")
        } else {
            alert("결제 페이지로 이동합니다")
        }
    });





//  server로 값 전달
function ajax(data, method, url) {

    let httpRequest = new XMLHttpRequest();
    httpRequest.open(method, url, true);
    httpRequest.setRequestHeader("Content-Type", "application/json");
    httpRequest.send(data);

    httpRequest.onreadystatechange = function () {
        // In local files, status is 0 upon success in Mozilla Firefox
        if(httpRequest.readyState === XMLHttpRequest.DONE) {
            var status = httpRequest.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                // The request has been completed successfully
                console.log(httpRequest.responseText);
                location.reload();
            } else {
                // Oh no! There has been an error with the request!
            }
        }
    };

}



