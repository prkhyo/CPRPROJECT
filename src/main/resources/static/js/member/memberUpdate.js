    window.onload = function (){
    const address = document.getElementById('finalAddress').value;
    const addressArr =address.split('^');


    document.getElementById('sample4_postcode').value = addressArr[0];
    document.getElementById('sample4_roadAddress').value = addressArr[1];
    document.getElementById('sample4_detailAddress').value = addressArr[2];

}

    function editSubmit() {
    if(confirm("수정하시겠습니까?")==true){

    let zipcode = document.getElementById('sample4_postcode');
    let roadAddress = document.getElementById('sample4_roadAddress');
    let detailAddress = document.getElementById('sample4_detailAddress');

    let result = zipcode.value + '^' + roadAddress.value + '^' + detailAddress.value
    console.log(result);
    document.getElementById('finalAddress').value = result;



    const frm = document.frm;

        if(!frm.email.value || frm.email.value == ""){
            alert("이메일 입력하세요");
            frm.email.focus();
            return false;
        }
        if(!frm.name.value || frm.name.value == ""){
            alert("이름을 입력하세요");
            frm.name.focus();
            return false;
        }
        if(!frm.address.value || frm.address.value == ""){
            alert("주소를 입력하세요");
            frm.address.focus();
            return false;
        }
        if(!frm.telephone.value || frm.telephone.value == ""){
            alert("전화번호를 입력하세요");
            frm.telephone.focus();
            return false;
        }
        if(!frm.name.value || frm.birthdate.value == ""){
            alert("생일을 입력하세요");
            frm.birthdate.focus();
            return false;
        }

    frm.submit();
    }else{
    return false}
    }
