/**
 * Created by Levchynskyi Serhii on 28.03.2016.
 */
var userLogin;
var userId;
var user;
var activeTemplateQuestions = [];

$(document).ready(function () {
    userLogin = $("#userLogin").val();
    $.ajax({
        type: "GET",
        url: "/rest/interviewers/" + userLogin + "/dto",
        success: function (interviewer) {
            userId = interviewer.id;
            user = interviewer;
            console.log("interviewer JSON: " + JSON.stringify(user));
            console.log(userLogin + "---" + userId);
            loadTemplates();
        }
    });

    // Slider settings
    $(function () {
        var sliderDiv = $("#slider");
        var sliderValue = $("#maxValue");
        sliderDiv.slider({
            value: 50,
            step: 1,
            min: 1,
            max: 100,
            slide: function (event, ui) {
                sliderValue.html(ui.value);
            }
        });
        sliderValue.html(sliderDiv.slider("value"));
    });

    $("#addQuestion").click(function () {
        console.log("Function addQuestion binded to button Add question fired");
        var templateId = $("#activeTemplateId").val();
        var questionString = $("#textArea").val();
        var maxValue = $("#maxValue").html();
        var question = {
            questionString: questionString,
            maxQuestionValue: maxValue
        };
        console.log(question);
        var questionToEditId = $("#editQuestionId");
        console.log(questionToEditId.val());
        if (questionToEditId.val() != "") {
            console.log("Question to edit ID: " + questionToEditId.val());
            $.ajax({
                type: "PUT",
                url: "/rest/questions/" + questionToEditId.val(),
                data: JSON.stringify(question),
                contentType: "application/json",
                success: function () {
                    console.log("Successfully edited question");
                    questionToEditId.val("");
                    getQuestions(templateId, $("#activeTemplate").html());
                }
            });
        } else {
            $.ajax({
                type: "POST",
                url: "/rest/questions/",
                data: JSON.stringify(question),
                contentType: "application/json",
                success: function (createdQuestion, textStatus, request) {
                    var questionUrl = request.getResponseHeader('Location')
                    console.log(questionUrl)
                    console.log("Done creating question");
                    $.ajax({
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
                    });
                },
                error: function () {
                    console.log("failed");
                }
            });
        }
        $("#textArea").val("");
    });

    $("#saveQuestionsOrder").click(function () {
        var rowArray = document.getElementsByClassName("questionsRow");
        $.each(rowArray, function (i, row) {
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
            url: "/rest/templates/" + $("#activeTemplateId").val() + "/clearAddList",
            data: JSON.stringify(activeTemplateQuestions),
            contentType: "application/json",
            success: function (result) {
                console.log(JSON.stringify(result.questions));
            }
        });
        activeTemplateQuestions = [];
    });
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
function getQuestions(id, name) {
    console.log("Function getQuestion binded to template dropdown fired");
    $("#activeTemplate").html(name);
    $("#activeTemplateId").val(id);
    console.log("Template id: " + id + "  ---- Template name:" + name);
    $.ajax({
        type: "GET",
        url: "/rest/templates/" + id + "/questions",
        success: function (questions) {
            console.log("URL for REST: /rest/templates/" + id + "/questions");
            $("#templateQuestionsBody").empty();
            $.each(questions, function (i, question) {
                console.log("---" + question.id + "---" + question.questionString);
                $("#templateQuestions").append('<tr class="questionsRow"><td><span id="questionString">' + question.questionString + '</span></td>' +
                    '<td><a id="editQuestion" onclick="editQuestion(\'' + id + '\','
                    + '\'' + question.id + '\',' + '\'' + question.questionString + '\',' + '\'' + question.maxQuestionValue + '\')"' +
                    ' class="glyphicon glyphicon-pencil">&nbsp;' +
                    '<a id="deleteQuestion" onclick="deleteQuestion(\'' + id + '\','
                    + '\'' + question.id + '\')" class="glyphicon glyphicon-trash" ></td>' +
                    '<td><span id="questionId" hidden>' + question.id + '</span>' +
                    '<span id="questionMaxValue" hidden>' + question.maxQuestionValue + '</span></td></tr>');
            });
        }
    });
    $("#templateQuestionsBody").sortable({
        revert: true,
    });
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