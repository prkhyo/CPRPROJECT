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
        const imageURLS = setImageURL;




        /*        // 공백은 안된다구요! 채워주세요!!
                if (title.value == "") {
                    e.preventDefault();
                    alert("상품 제목을 입력해 주세요 ")
                }else if (price.value == "") {
                    e.preventDefault();
                    alert("상품 제목을 입력해 주세요 ")
                }else if (deliveryCharge.value == "") {
                    e.preventDefault();
                    alert("상품 제목을 입력해 주세요 ")
                }else if (quantity.value == "") {
                    e.preventDefault();
                    alert("상품 제목을 입력해 주세요 ")
                }else if (description.value == "") {
                    e.preventDefault();
                    alert("상품 제목을 입력해 주세요 ");

                }else {*/

        // json 배열 생성
        let registProductJson = new Array();

        //배열 => json type
        let productRegistObject = new Object();

        if (setImageURL.length == 0) {
            console.log("no have image");

            productRegistObject.categoryNo = category.options[category.selectedIndex].value;
            productRegistObject.themeNo = theme.options[theme.selectedIndex].value;
            productRegistObject.title = title.value;
            productRegistObject.price = price.value;
            productRegistObject.deliveryCharge = deliveryCharge.value;
            productRegistObject.totalQuantity = quantity.value;
            productRegistObject.description = description.value;

            registProductJson.push(productRegistObject);

        } else {
            console.log("have photos");


            console.log("setImageURL.length  = " + setImageURL.length);
            for (let i = 0; i < setImageURL.length; i++) {
                console.log(" i = " + i)

                console.log(" ㅂ  =" + imageURLS[0].imageURL);
                console.log(" ㅈ  =" + imageURLS[1].imageURL);
                console.log(" ㅁ  =" + imageURLS[i].imageURL);

                productRegistObject.imageUrls = imageURLS[i].imageURL;
                productRegistObject.thumbnailUrls = imageURLS[i].thumbnailURL;
                productRegistObject.categoryNo = category.options[category.selectedIndex].value;
                productRegistObject.themeNo = theme.options[theme.selectedIndex].value;
                productRegistObject.title = title.value;
                productRegistObject.price = price.value;
                productRegistObject.deliveryCharge = deliveryCharge.value;
                productRegistObject.totalQuantity = quantity.value;
                productRegistObject.description = description.value;


                registProductJson.push(productRegistObject);
            }
        }

        console.log("productRegistObject = " + registProductJson);
        //  배열 json 변경
        const jsonData = JSON.stringify(registProductJson);
        console.log("jsonData  = " + jsonData);

        let httpRequest = new XMLHttpRequest();
        httpRequest.open("POST", "/product/regist", true);
        httpRequest.setRequestHeader("Content-Type", "application/json");
        httpRequest.send(jsonData);  //위에서 json으로 변경한 뒤에 controller로 보냄!!!!!!!!!

        httpRequest.onreadystatechange = function () {
            // In local files, status is 0 upon success in Mozilla Firefox
            if (httpRequest.readyState === XMLHttpRequest.DONE) {
                var status = httpRequest.status;
                if (status === 0 || (status >= 200 && status < 400)) {
                    // The request has been completed successfully
                    console.log(httpRequest.responseText);
                    // window.location.href = '/cart';
                } else {
                    // Oh no! There has been an error with the request!
                }
            }
        };


        // }
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
                console.log(httpRequest.responseText);
                // window.location.href = '/cart';
            } else {
                // Oh no! There has been an error with the request!
            }
        }
    };

}



