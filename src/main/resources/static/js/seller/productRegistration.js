

    $('.uploadBtn').click(function () {
    var formData = new FormData();
    var inputFile = $("input[type='file']");
    var files = inputFile[0].files;

    for (var i = 0; i < files.length; i++) {
    console.log(files[i]);
    formData.append("uploadFiles", files[i]);
}


    //업로드 ajax
    $.ajax({
    url: '/uploadAjax',
    processData: false,
    contentType: false,
    data: formData,
    type: 'POST',
    dataType: 'json',
    success: function (result) {
    showUploadImages(result);
},
    error: function (jqXHR, textStatus, errorThrown) {
    console.log(textStatus);
}
}); // ajax 끝

    function showUploadImages(arr) {
    console.log(arr);
    var divArea = $(".uploadResult");
    var str = "";

    for (let i = 0; i < arr.length; i++) {
    str += "<div class='thumnailBox'>";
    str += "<img src='/display?fileName=" + arr[i].thumbnailURL + "'>";
    str += "<button class='removeBtn' data-name='" + arr[i].imageURL + "'>REMOVE</button>"
    str += "</div>";
}
    divArea.append(str);
}

    $(".uploadResult").on("click", ".removeBtn", function (e) {
    var target = $(this);
    var fileName = target.data("name");
    var targetDiv = $(this).closest("div");

    console.log(fileName);
    $.post('/removeFile', {fileName: fileName}, function (result) {
    console.log(result);
    if (result === true) {
    targetDiv.remove();
}
});

});
});


