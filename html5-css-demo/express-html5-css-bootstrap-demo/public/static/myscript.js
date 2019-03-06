
$(document).ready(function () {

    $('.mynavheader').load("navigation.html");

    $("#addQualificationsBtn").on("click",
        {
            inputFieldIdn: '#addQualifications-input',
            parentNodeIdn: '#qualificationsArticleUList'
        },
        addFunction);

    $("#addExperienceBtn").on("click",
        {
            inputFieldIdn: '#addExperience-input',
            parentNodeIdn: '#experienceArticleUList'
        },
        addFunction);


    function addFunction(e) {

        var inputFieldId = e.data.inputFieldIdn;
        var parentNodeId = e.data.parentNodeIdn;

        var newElemText = $(inputFieldId).val();

        if (newElemText != null && newElemText != "" && newElemText != undefined) {

            var node = $("<li></li>").text(newElemText + "     ").append(
                $('<a/>', { 'href': '#/', 'class': 'btn btn-danger', 'id': 'removeBtn' }).text('X Remove')
            );

            $(parentNodeId).append(node);

        } else {
            alert('Please provide an input.');
        }

    }

    $(document).on("click", "#removeBtn", function () {
        var val = confirm("Are you sure you want to remove \" " + $($(this).parent().contents()[0]).text() + " \" ?")

        if (val) {
            $(this).parent().remove();
        }
    });

    $("#addProjectBtn").click(function () {

        var newPrjNameText = $("#addProjectName-input").val();
        var newPrjDescText = $("#addProjectDesc-input").val();

        if (newPrjNameText == null || newPrjNameText == "" || newPrjNameText == undefined) {
            alert('Please provide a Project name.');
            return;
        }

        if (newPrjDescText == null || newPrjDescText == "" || newPrjDescText == undefined) {
            alert('Please provide Project Description.');
            return;
        }

        var node = $('<li/>', { 'style': 'list-style-type:none;' }).append(
            $('<section/>', { 'id': 'prjSection' }).append(
                $('<article/>', { 'id': 'prjArticle' }).html(
                    "<header><h5>Project Name :" + newPrjNameText + "</h5></header>" + "<p>" + newPrjDescText + "</p><br><br>").append(
                        $('<a/>', { 'href': '#/', 'class': 'btn btn-danger', 'id': 'removeBtn' }).text('X Remove')
                    )
            ));

        $("#projectsDtlsSection").append(node);

    });


});

