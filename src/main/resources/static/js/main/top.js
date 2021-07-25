function myOn(){
    document.getElementById("popup").style['display'] = 'block';
    document.getElementById("popup").style['display'] = 'block';
    document.getElementById("userImage").style['borderColor'] = '#ed4a4a';


}

function myOut(){
    document.getElementById("popup").style['display'] = 'none';
    document.getElementById("userImage").style['borderColor'] = 'white';
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

