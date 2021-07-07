

let delBtn = document.getElementById('registBtn').addEventListener('click',
    function (e) {

        //상품 등록 목록 값 가져오기
        const category = document.getElementById('category');
        const theme = document.getElementById('theme');
        const title = document.getElementById('title');
        const price = document.getElementById('price');
        const deliveryCharge = document.getElementById('deliveryCharge');
        const quantity = document.getElementById('quantity');
        const description = document.getElementById('description');


        console.log(category.options[category.selectedIndex].value)
        console.log(theme.options[theme.selectedIndex].value)
        console.log(title.value)
        console.log(price.value)
        console.log(deliveryCharge.value)
        console.log(quantity.value)
        console.log(description.value)
        console.log(setImageURL[0].imageURL)





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



