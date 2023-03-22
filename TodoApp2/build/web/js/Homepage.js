/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const btnOpenModal = $("#btn-openModal");
const btnCloseModal = $("#btn-closeModal");
const btnCloseModalUser = $(".btn-closeModalUser");
const btnAddTodo = $("#btn-AddTodo");
const btnUpdateTodo = $("#btn-UpdateTodo");
const modalWrapper = $(".modal_wrapper.modalWrapperTodo");
const modalWrapperUser = $(".modal_wrapper.modalWrapperUser");
const modal = $(".modal");
const modalUser = $(".modalUser");
const titleTodo = $("#titleTodo");
const noteTodo = $("#noteTodo");
const createAt = $('.date_createAt');
const updateAt = $('.date_updateAt');
const idTodoFormUpdate = $("#idTodoFormUpdate");
const infoUser = $(".infoUser");
const btnSignOut = $("#btn-signOut");
const form_InfoUser = $(".form-infoUser");
const form_UpdateUser = $(".form-updateUser");
const btnUpdateUser = $("#btn-UpdateUser");
const btnSaveUser = $("#btn-SaveUser");


btnOpenModal.click(() => {
    modalWrapper.addClass("show");
    modal.addClass("modal-add");
    modal.removeClass("modal-details");
    titleTodo.val("");
    noteTodo.val("");
    idTodoFormUpdate.val("");

});

btnCloseModal.click(() => {
    modalWrapper.removeClass("show");
    modal.removeClass("modal-add");
    modal.removeClass("modal-details");
});

modal.click((e) => {
    e.stopPropagation();
});

modalWrapper.click(() => {
    modalWrapper.removeClass("show");
    modal.removeClass("modal-add");
    modal.removeClass("modal-details");
});

// RENDER TODOS
function renderTodo(todos)
{
    var htmlTemplate = "";
    todos.forEach(function (todo) {
        htmlTemplate += getTemplateTodo(todo.id_TODO, todo.title);
    });
    $(".list_todo").html(htmlTemplate);
}


// TEMPLATE ITEM TODO
function getTemplateTodo(idTodo, titleTodo)
{
    return  `
            <li class="item_todo" itemid=${idTodo}>
                <p onclick="handleDetailsTodo(this)">${titleTodo}</p>
                <div class="icons">
                    <div class="icon-wrapper icon-check" onClick="handleCompleteTodo(this)">
                        <i class="fa-sharp fa-regular fa-circle-check"></i>
                    </div>
                    <div class="icon-wrapper icon-trash" onClick="handleRemoveTodo(this)">
                        <i class="fa-solid fa-trash  "></i>
                    </div>
                </div>
            </li>
        `;
}


// HANDLE GET TODO BY USER
$(document).ready(function ()
{
    const idUser = $("#ID_USER").val();
    $.ajax({
        url: 'todo/GetTodoServlet',
        data: {
            id_User: idUser
        },
        dataType: 'json',
        type: 'POST',
        success: function (response) {
            if (response.status)
            {
                renderTodo(response.data);

            } else {
                console.log("Error !!!");

            }
        },
        error: function (res) {
            console.log(res.responseText);
        }
    });
});


// HANDLE DETAILS TODO
function handleDetailsTodo(item)
{
    const id = item.parentNode.getAttribute("itemid");
    $.ajax({
        url: 'todo/DetailsTodoServlet',
        data: {
            id_todo: id
        },
        dataType: 'json',
        type: 'POST',
        success: function (response) {
            if (response.status)
            {
                titleTodo.val(response.title);
                noteTodo.val(response.note);
                idTodoFormUpdate.val(id);
                createAt.text(response.createAt);
                updateAt.text(response.updateAt);
                modalWrapper.addClass("show");
                modal.removeClass("modal-add");
                modal.addClass("modal-details");
            } else {
                console.log("Todo không tồn tại !!!");

            }
        },
        error: function (res) {
            console.log(res.responseText);
        }
    });
}


// CREATE TOAST
function create_Toast(message, type) {
    var html = `
                <div class="toast-content">
                    ${type === "success" ? "<i class='fas fa-solid fa-check icon'></i>" : ""}     
                    ${type === "warning" ? "<i class='fa-solid fa-exclamation icon'></i>" : ""}   
                    ${type === "error" ? "<i class='fa-solid fa-xmark icon'></i>" : ""}
                    <div class="message">
                        <p class="text text-1">Notification</p>
                        <p class="text text-2">${message}</p>
                    </div>
                </div>
                <i class="fa-solid fa-xmark close"></i>
                <div class="progress active"></div>
            `;

    var div_html = document.createElement('div');
    div_html.classList.add('toast', type);
    div_html.innerHTML = html;
    document.getElementById('toast_wrap').appendChild(div_html);

    // Auto remove Toast mesage after 2.5s
    const autoRemove = setTimeout(() => {
        div_html.classList.add('remove');
        setTimeout(() => {
            document.querySelector('#toast_wrap').removeChild(div_html);
        }, 1000);
    }, 1500);

    //  Click icon and remove Toast mesage
    div_html.onclick = function (e) {
        if (e.target.classList.contains('close')) {
            div_html.classList.add('remove');
            setTimeout(() => {
                document.querySelector('#toast_wrap').removeChild(div_html);
            }, 1000);
            clearTimeout(autoRemove);
        }
    };
}


// HANDLE ADD TODO
btnAddTodo.click(function () {
    var titleTodoAdd = $("input[name=titleTodo]").val();
    if (titleTodoAdd.toString().trim() === "")
    {
        create_Toast("Please enter your to-do title", "warning");
    } else {
        var formData = $(".form-todo").serialize();
        $.ajax({
            url: 'todo/AddTodoServlet',
            data: formData,
            dataType: 'json',
            type: 'POST',
            success: function (response) {
                if (response.status)
                {
                    create_Toast("Add Successfully", "success");
                    var htmlTemplate = getTemplateTodo(response.id_TODO, response.title);
                    $(".list_todo").prepend(htmlTemplate);
                    $("#titleTodo").val("");
                    $("#noteTodo").val("");
                    modalWrapper.removeClass("show");

                } else {
                    create_Toast("Add Failed - Please try again", "error");
                }
            },
            error: function (res) {
                console.log(res.responseText);
            }
        });
    }

});


// HANDLE REMOVE TODO
function handleRemoveTodo(item)
{
    // tag <li> contains id
    const item_id = item.parentNode.parentNode;
    const id = item_id.getAttribute("itemid");

    $.ajax({
        url: 'todo/RemoveTodoServlet',
        data: {
            idTodo: id
        },
        dataType: 'json',
        type: 'POST',
        success: function (response) {
            if (response.status)
            {
                $(item_id).fadeOut(500, function () {
                    $(this).remove();
                    create_Toast(" Remove Successfully!!!", "success");
                });

            } else {
                create_Toast("Remove Failed", "error");
            }
        },
        error: function (res) {
            console.log(res.responseText);
        }
    });

}


// HANDLE UPDATE TODO
btnUpdateTodo.click(function () {
    var titleTodoUpdate = $("input[name=titleTodo]").val();
    if (titleTodoUpdate.toString().trim() === "")
    {
        create_Toast("Please enter your to-do title", "warning");
    } else {
        $.ajax({
            url: 'todo/UpdateTodoServlet',
            data: {
                idTodo: idTodoFormUpdate.val(),
                title: titleTodo.val(),
                note: noteTodo.val()
            },
            dataType: 'json',
            type: 'POST',
            success: function (response) {
                if (response.status)
                {
                    $("li[itemid = " + idTodoFormUpdate.val() + "] p").text(titleTodo.val());
                    create_Toast("Update Successfully", "success");
                    modalWrapper.removeClass("show");
                } else {
                    create_Toast("Update Failed - Please try again", "error");
                }
            },
            error: function (res) {
                console.log(res.responseText);
            }
        });
    }
});


// HANDLE SEARCH TODO
$("input[name=search_todo]").on("input", function () {
    $.ajax({
        url: 'todo/SearchTodoServlet',
        data: {
            searchKey: $(this).val()
        },
        dataType: 'json',
        type: 'POST',
        success: function (response) {
            if (response.data)
            {
                renderTodo(response.data);
            } else {
                create_Toast("Remove Failed", "error");
            }
        },
        error: function (res) {
            console.log(res.responseText);
        }
    });
});


// HANDLE SET COMPLETE TODO
function handleCompleteTodo(item)
{
    // tag <li> contains id
    const id = item.parentNode.parentNode.getAttribute("itemid");
    console.log(id);
}

// HANDLE SIGN OUT
btnSignOut.click(() => {
    $.ajax({
        url: 'user/LogoutServlet',
        data: {},
        dataType: 'json',
        type: 'POST',
        success: function (response) {
            if (response.status)
            {
                window.location.href = "Login.jsp";
            } else {
                create_Toast("Logout Failed", "error");
            }
        },
        error: function (res) {
            console.log(res.responseText);
        }
    });
});


// HANDLE OPEN MODAL USER
infoUser.click(function () {
    modalWrapperUser.addClass("show");
    form_InfoUser.css("display", "block");
    form_UpdateUser.css("display", "none");


});
modalUser.click((e) => {
    e.stopPropagation();
});
modalWrapperUser.click(() => {
    modalWrapperUser.removeClass("show");
});

btnCloseModalUser.click(() => {
    modalWrapperUser.removeClass("show");
});

btnUpdateUser.click(() => {
    form_InfoUser.css("display", "none");
    form_UpdateUser.css("display", "block");
    $("input[name=currentPassword]").val("");
    $("input[name=currentPassword]").focus();
    $("input[name=newPassword]").val("");
    $("input[name=confirmPassword]").val("");
});

btnSaveUser.click(() => {
    var currentPassword = $("input[name=currentPassword]").val();
    var newPassword = $("input[name=newPassword]").val();
    var confirmPassword = $("input[name=confirmPassword]").val();
    if (currentPassword.trim() !== "" && newPassword.trim() !== "" && confirmPassword.trim() !== "")
    {
        if (newPassword.toString().toLowerCase() === confirmPassword.toString().toLowerCase())
        {
            $.ajax({
                url: 'user/UpdateUserServlet',
                data: {
                    currentPassword: currentPassword,
                    newPassword: newPassword
                },
                dataType: 'json',
                type: 'POST',
                success: function (data) {
                    if (data.status)
                    {
                        create_Toast("Update Successfully", "success");
                        $("input[name=password]").val(newPassword);
                        modalWrapperUser.removeClass("show");
                    } else {
                        create_Toast("The current password is incorrect", "warning");
                    }
                },
                error: function (res) {
                    console.log(res.responseText);
                }
            });
        } else {
            create_Toast("2 passwords must be the same", "warning");
        }

    } else {
        create_Toast("Please fill in the information", "warning");
    }

});
 