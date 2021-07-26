
$('.uploadBtn').click(function () {
    var formData = new FormData();
    var inputFile = $("input[type='file']");
    var files = inputFile[0].files;

    for (var i = 0; i < files.length; i++) {
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
        }
    }); // ajax 끝

});

let setImageURL = [];


function showUploadImages(arr) {
    setImageURL = arr;


    var divArea = $(".uploadResult");
    var str = "";

    for (let i = 0; i < arr.length; i++) {
        str += "<div class='thumnailBox' >";
        str += "<input name='getMain' type='checkbox' onclick='getMainImage(event)' value="+ i +" >";
        str += "<img class='imageLocation' name='inputImg' style='max-width: 200px; max-height: 200px;' src='/display?fileName=" + arr[i].imageURL+ "'>";
        str += "<button class='removeBtn' data-name='" + arr[i].imageURL + "'><i class=\"fas fa-times\"></i></button>"
        str += "</div>";
    }
    divArea.append(str);
}

$(".uploadResult").on("click", ".removeBtn", function (e) {
    var target = $(this);
    var fileName = target.data("name");
    var targetDiv = $(this).closest("div");

    $.post('/removeFile', {fileName: fileName}, function (result) {
        if (result === true) {
            targetDiv.remove();
        }

    });

});





