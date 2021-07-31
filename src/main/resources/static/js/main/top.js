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

    let productThemeNo = document.getElementById("productThemeNo").value;
    let keyword = document.getElementById('searchProductKeyword').value;
    console.log(keyword);
    if(keyword == null || keyword == ''){
        alert('검색 키워드를 입력해 주세요.');
        return false;
    }
    location.href='/store?searchProductKeyword='+keyword+'&productThemeNo='+productThemeNo;
}

