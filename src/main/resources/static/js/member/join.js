

function rightPassword(){
    let password = document.getElementById('password').value;
    let password2 = document.getElementById('password2').value;
    const passwordMessage = document.getElementById('passwordMessage');
    console.log('password'+ password,'checkpassword'+password2);

    if(password !== password2){
        console.log('비밀번호가 일치하지 않습니다.');
        passwordMessage.innerHTML ='비밀번호가 일치하지 않습니다.';
    }else {
        passwordMessage.innerHTML="";
    }
}

function gatherAddress(){
    let zipcode = document.getElementById('sample4_postcode');
    let roadAddress = document.getElementById('sample4_roadAddress');
    let detailAddress = document.getElementById('sample4_detailAddress');

    let result = zipcode.value+'^'+roadAddress.value+'^'+detailAddress.value
    console.log(result);
    document.getElementById('finalAddress').value=result;
}



function checkId() {
    const accountId = document.getElementById("accountId");
    const request = new XMLHttpRequest();

    if (accountId.value == '') {
        alert('아이디를 입력해주세요.');

    }
    if(accountId.value.length<4 || accountId.value.length>20){
        alert("아이디는 4~20글자 사이로 작성해야 합니다. ");
        return;
    }

    request.onreadystatechange = function () {
        if (this.readyState === 4 & this.status === 200) {
            if (request.responseText == 'true') {
                alert("사용가능한 아이디 입니다.")
                accountId.setAttribute('readonly', "true");
            } else {
                alert("이미 사용중인 아이디 입니다.")
            }
        }
    }

    request.open("post", "/join/checkId");
    request.setRequestHeader("Content-type", "text/plain");
    request.send(accountId.value);
}

