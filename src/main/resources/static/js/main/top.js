function myOn(){
    document.getElementById("popup").style['display'] = 'block';
    document.getElementById("popup").style['display'] = 'block';
    document.getElementById("userImage").style['borderColor'] = '#ed4a4a';
}

function myOut(){
    document.getElementById("popup").style['display'] = 'none';
    document.getElementById("userImage").style['borderColor'] = 'white';
}

// 글쓰기 버튼 1번 클릭 하면 팝업 / 다시 클릭하면 팝업 꺼짐.
var cnt = 0;
function myOn2(){
    if(cnt==0){
        document.getElementById("popup2").style['display'] = 'block';
        document.getElementById("popup2").style['display'] = 'block';
        cnt=1;
    } else if(cnt!=0) {
        document.getElementById("popup2").style['display'] = 'none';
        cnt=0;
    }
}


function storeSearch(){
    console.log('검색');

    let productThemeNo_ = document.getElementById("productThemeNo").value;
    let keyword_ = document.getElementById('searchProductKeyword').value;
    console.log(keyword_);
    if(keyword_ == null || keyword_ == ''){
        alert('검색 키워드를 입력해 주세요.');
        return false;
    }
    location.href='/store?searchProductKeyword='+keyword_+'&productThemeNo='+productThemeNo_;
}

// 판매자 신청
function applyNewSeller(accountId, memberRole){
    if(memberRole == 'ROLE_USER'){
        if(!confirm("\""+accountId+"\"님, 판매자 자격을 신청하시겠습니까?")){
            alert("판매자 자격 신청을 취소하였습니다.")
        } else {
            location.href = "/admin/member/applyNewSeller/" + accountId;
            alert("판매자 자격 신청이 접수되었습니다.");
        }
    } else if(memberRole == 'APPLY_SELLER') {
        alert("판매자 자격이 신청되어 처리중입니다.");
    } else if(memberRole == 'SELLER') {
        alert("이미 판매자 자격 증명이 완료된 ID입니다.");
    } else {
        alert("관리자 ID는 판매자를 신청할 수 없습니다.");
    }
}