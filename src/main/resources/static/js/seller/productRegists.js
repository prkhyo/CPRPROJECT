function getMainImage(event) {
    const checked = document.querySelectorAll('input[name="getMain"]:checked');
    const noChecked = document.querySelectorAll('input[name="getMain"]:not(:checked)');

    if (checked.length > 1) {
        event.preventDefault();
        alert("메인사진은 하나만 선택해 주세요. ")
    }

}


let delBtn = document.getElementById('registBtn').addEventListener('click',
    function (e) {

        //상품 등록 목록 값 가져오기
        const category = document.getElementById('category');
        const memberId = document.getElementById('memberId');
        const theme = document.getElementById('theme');
        const title = document.getElementById('title');
        const price = document.getElementById('price');
        const deliveryCharge = document.getElementById('deliveryCharge');
        const quantity = document.getElementById('quantity');
        const description = document.getElementById('description');
        const imageURLS = setImageURL;
        const checked = document.querySelectorAll('input[name="getMain"]:checked');

        // 공백은 안된다구요! 채워주세요!!
        if (title.value == "") {
            e.preventDefault();
            alert("상품 제목을 입력해 주세요 ")
        } else if (price.value == "") {
            e.preventDefault();
            alert("가격을 입력해 주세요 ")
        } else if (deliveryCharge.value == "") {
            e.preventDefault();
            alert("배송비를 입력해 주세요 ")
        } else if (quantity.value == "") {
            e.preventDefault();
            alert("판매수량을 입력해 주세요 ")
        } else if (description.value == "") {
            e.preventDefault();
            alert("상품 설명을 입력해 주세요 ");
        } else if (setImageURL == '') {
            e.preventDefault();
            alert("사진을 넣어주세요");
        } else if (checked[0] == undefined) {
            e.preventDefault();
            alert("메인 사진을 지정해 주세요");

        } else {

            // json 배열 생성
            let registProductJson = new Array();

            //배열 => json type

            for (let i = 0; i < setImageURL.length; i++) {
                let productRegistObject = new Object();

                productRegistObject.imageUrl = imageURLS[i].imageURL;
                productRegistObject.mainImageUrl = imageURLS[checked[0].value].imageURL;
                productRegistObject.memberId = memberId.value;
                productRegistObject.categoryNo = category.options[category.selectedIndex].value;
                productRegistObject.themeNo = theme.options[theme.selectedIndex].value;
                productRegistObject.title = title.value;
                productRegistObject.price = price.value;
                productRegistObject.deliveryCharge = deliveryCharge.value;
                productRegistObject.totalQuantity = quantity.value;
                productRegistObject.description = description.value;

                registProductJson.push(productRegistObject);


            }
            const data = JSON.stringify(registProductJson);
            ajax(data, "POST", "/product/regist")


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
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            var status = httpRequest.status;
            if (status === 0 || (status >= 200 && status < 400)) {
                // The request has been completed successfully
                window.location.href = '/';
            } else {
                // Oh no! There has been an error with the request!
            }
        }
    };

}



