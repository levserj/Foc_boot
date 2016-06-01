/**
 * Created by Levchynskyi Serhii on 28.05.2016.
 */

var userId;

$(document).ready(function () {
    userId = $("#userId").val();
    getItems(userId);
    $("#itemForm").submit(function () {
        console.log("Add item button click binded");
        var itemTitle = $("#title").val();
        var itemDescription = $("#description").val();
        var item = {
            title: itemTitle,
            description: itemDescription
        };
        console.log(item);
        var itemToEditId = $("#itemToEditId");
        console.log(itemToEditId.val());
        if (itemToEditId.val() != "") {
            console.log("Item to edit ID: " + itemToEditId.val());
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "PUT",
                url: "/rest/items/" + itemToEditId.val(),
                data: JSON.stringify(item),
                success: function (result) {
                    console.log("Successfully edited item");
                    itemToEditId.val("");
                    getItems(userId);
                }
            });
        } else {
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: "POST",
                url: "/rest/items/user/" + userId,
                data: JSON.stringify(item),
                success: function (result) {
                    console.log("Done creating item");
                },
                error: function () {
                    console.log("failed");
                }
            });
        }
        $("#title").val("");
        $("#description").val("");
    });

});



// Function to display all the questions from the chosen template
function getItems(userId) {
    console.log("Function getItems binded to template dropdown fired");
    $.ajax({
        type: "GET",
        url: "/rest/items/user/" + userId,
        success: function (items) {
            $("#myItems").empty();
            $.each(items, function (i, item) {
                console.log("---" + item.id + "---" + item.title);
                $("#myItems").prepend(
                    '<tr>' +
                    '<td>' + item.title + '</td>' +
                    '<td style="width: 30%">' + item.description + '</td>' +
                    '<td><a id="editItem" onclick="editItem(\'' + item.id +
                    '\', \'' + item.title + '\', \'' + item.description + '\')"  ' +
                    'class="glyphicon glyphicon-pencil"></a></td>' +
                    '<td><a id="deleteItem" onclick="deleteItem(\'' + item.id + '\')" + ' +
                    'class="glyphicon glyphicon-trash"></a></td>' +
                    '</tr>'
                );
            });
        }
    });
}

function deleteItem(itemId) {
    console.log("Function deleteItem binded to ref deleteItem fired");
    $.ajax({
        type: "DELETE",
        url: "/rest/items/" + itemId + "/user/" + userId,
        success: function () {
            console.log("/rest/items/" + itemId + "/user/" + userId);
            getItems(userId);
        },
        error: function (result) {
            console.log(result);
        }
    });
}

function editItem(itemId, title, description) {
    console.log("Function editQuestion binded to ref editQuestion fired");
    console.log("Item Id is : " + itemId + " Item title is: " + title + "Item description is: " + description);
    $("#title").val(title);
    $("#description").val(description);
    $("#itemToEditId").val(itemId);
}