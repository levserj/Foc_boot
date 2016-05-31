/**
 * Created by Levchynskyi Serhii on 28.05.2016.
 */
var userLogin;
var userId;
var user;


$(document).ready(function () {
    userId = $("#userId").val();
    getItems(userId);
    $.ajax({
        type: "GET",
        url: "/rest/users/" + userId,
        success: function (result) {
            user = result;
            console.log(user);
        }
    });

    $("#addItem").click(function () {
        console.log("Function addItem binded to button Add item fired");
        var itemTitle = $("#title").val();
        var itemDescription = $("#description").val();

        var item = {
            title: itemTitle,
            maxQuestionValue: itemDescription,
            owner: user
        };
        console.log(item);
        var itemToEditId = $("#itemToEditId");
        console.log(itemToEditId.val());
        if (itemToEditId.val() != "") {
            console.log("Item to edit ID: " + itemToEditId.val());
            $.ajax({
                type: "PUT",
                url: "/rest/items/" + itemToEditId.val(),
                data: JSON.stringify(item),
                contentType: "application/json",
                success: function () {
                    console.log("Successfully edited item");
                    itemToEditId.val("");
                    getItems(userId);
                }
            });
        } else {
            $.ajax({
                type: "POST",
                url: "/rest/items/",
                data: JSON.stringify(item),
                contentType: "application/json",
                success: function (item, request) {
                    var itemUrl = request.getResponseHeader('Location')
                    console.log(itemUrl)
                    console.log("Done creating item");
                    /* $.ajax({
                     type: "POST",
                     url: "/rest/templates/" + templateId + "/questions",
                     data: JSON.stringify(createdQuestion),
                     contentType: "application/json;",
                     success: function () {
                     console.log("Done saving created question to template");
                     getQuestions(templateId, $("#activeTemplate").html());
                     },
                     error: function () {
                     console.log("Saving question to template failed");
                     }
                     });*/
                },
                error: function () {
                    console.log("failed");
                }
            });
        }
        $("#title").val("");
        $("#description").val("");
    });

    /*  $("#saveQuestionsOrder").click(function(){
     var rowArray = document.getElementsByClassName("questionsRow");
     $.each(rowArray, function(i, row){
     var spans = row.getElementsByTagName('span');
     var questionString = spans[0].innerHTML;
     var questionId = spans[1].innerHTML;
     var questionMaxValue = spans[2].innerHTML;
     var question = {
     id: questionId,
     questionString: questionString,
     maxQuestionValue: questionMaxValue
     };
     activeTemplateQuestions.push(question);
     });
     console.log(JSON.stringify(activeTemplateQuestions));
     $.ajax({
     type: "POST",
     url: "/rest/templates/"+ $("#activeTemplateId").val() + "/clearAddList",
     data: JSON.stringify(activeTemplateQuestions),
     contentType: "application/json",
     success: function (result) {
     console.log(JSON.stringify(result.questions));
     }
     });
     activeTemplateQuestions = [];
     });*/
});

function loadTemplates() {
    $(".selectedTemplates").find("li:gt(1)").remove();
    $.ajax({
        type: "GET",
        url: "/rest/templates/interviewers/" + userId,
        success: function (templates) {
            console.log("/rest/templates/interviewers/" + userId);
            $.each(templates, function (i, template) {
                $(".selectedTemplates").append('<li >' +
                    '<a class="chosenTemplate" href="#" style="display: inline;" ' +
                    'onclick="getQuestions(\'' + template.id + '\', \'' + template.name + '\')">' + template.name + '</a>'
                    + '<a onclick="deleteTemplate(\'' + template.id + '\')" style="display: inline;" class="glyphicon glyphicon-trash badge">'
                    + '</li>');
            });
        }
    });
}

function addNewTemplate() {
    var template = prompt("Please enter template's name", "");
    if (template != null) {
        var name = template;
        var newTemplate = {
            name: name,
            questions: null,
            favourite: false,
            interviewer: user
        };
        console.log(newTemplate);
        $.ajax({
            type: "POST",
            url: "/rest/templates/",
            data: JSON.stringify(newTemplate),
            contentType: "application/json",
            success: function () {
                console.log("Template created");
                loadTemplates();
            }
        });
    }
}

function deleteTemplate(templateId) {
    console.log("Function deleteTemplate fired");
    console.log("Template Id is: " + templateId);
    $.ajax({
        type: "GET",
        url: "/rest/templates/" + templateId + "/questions",
        success: function (questions) {
            $.each(questions, function (i, question) {
                $.ajax({
                    type: "DELETE",
                    url: "/rest/questions/" + question.id,
                    success: function () {
                        console.log("Question with Id " + question.id + " deleted");
                    }
                })
            });
            $.ajax({
                type: "DELETE",
                url: "/rest/templates/" + templateId,
                success: function () {
                    console.log("Template with Id " + templateId + " deleted");
                    location.reload();
                }
            })
        }
    });
}

// Function to display all the questions from the chosen template
function getItems(userId) {
    console.log("Function getItems binded to template dropdown fired");
    /*    $("#activeTemplate").html(name);
     $("#activeTemplateId").val(id);*/
    /*    console.log("Template id: " +  id + "  ---- Template name:"+ name);*/
    $.ajax({
        type: "GET",
        url: "/rest/items/user/" + userId,
        success: function (items) {
            console.log("URL for REST: /rest/items/user/" + userId);
            $("#myItems").empty();
            $.each(items, function (i, item) {
                console.log("---" + item.id + "---" + item.title);
                $("#myItems").prepend(
                    '<tr>' +
                    '<td>' + item.title + '</td>' +
                    '<td>' + item.description + '</td>' +
                    '<td><a id="editItem" onclick="editItem(item.id)" class="glyphicon glyphicon-pencil"></a></td>' +
                    '<td><a id="deleteItem" onclick="deleteItem(item.id)" class="glyphicon glyphicon-trash"></a></td>' +
                        /*'<td>' +  + '</td>'*/
                    '</tr>'
                );
                /*'<tr class="questionsRow"><td><span id="questionString">' + question.questionString + '</span></td>' +
                 '<td><a id="editQuestion" onclick="editQuestion(\'' + id + '\','
                 + '\'' + question.id + '\',' + '\'' + question.questionString +  '\',' + '\'' + question.maxQuestionValue + '\')"' +
                 ' class="glyphicon glyphicon-pencil">&nbsp;' +
                 '<a id="deleteQuestion" onclick="deleteQuestion(\'' + id + '\','
                 + '\'' + question.id + '\')" class="glyphicon glyphicon-trash" ></td>' +
                 '<td><span id="questionId" hidden>' + question.id + '</span>'+
                 '<span id="questionMaxValue" hidden>' + question.maxQuestionValue + '</span></td></tr>'*/
            });
        }
    });
    /*    $("#templateQuestionsBody").sortable({
     revert : true,
     });*/
}


function deleteQuestion(templateId, questionId) {
    console.log("Function deleteQuestion binded to ref deleteQuestion fired");
    console.log("Template Id is: " + templateId + " Question Id is: " + questionId);
    $.ajax({
        type: "DELETE",
        url: "/rest/templates/" + templateId + "/questions/" + questionId,
        success: function () {
            console.log("/rest/templates/" + templateId + "/questions/" + questionId);
            $.ajax({
                type: "DELETE",
                url: "/rest/questions/" + questionId,
                success: function () {
                    console.log("/rest/questions/" + questionId);
                    getQuestions(templateId, $("#activeTemplate").html());
                },
                error: function (result) {
                    console.log(result);
                }
            });
        },
        error: function (result) {
            console.log(result);
        }
    });
}

function editQuestion(templateId, qId, qString, qMaxValue) {
    console.log("Function editQuestion binded to ref editQuestion fired");
    console.log("Template Id is: " + templateId + " Question Id is: " + qId +
        " QuestionString is: " + qString + " MaxValue is: " + qMaxValue);
    $("#textArea").val(qString);
    $("#editQuestionId").val(qId);
    $("#maxValue").html(qMaxValue);
    $("#slider").slider("value", qMaxValue);
}