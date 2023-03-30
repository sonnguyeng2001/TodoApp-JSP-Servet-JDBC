<%-- 
    Document   : Homepage
    Created on : Mar 17, 2023, 7:35:15 PM
    Author     : FPTshop
--%>
<%

    if (request.getSession().getAttribute("idUser") == null) {
        response.sendRedirect("Login.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
              integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="css/Homepage.css">  
        <link rel="stylesheet" href="css/Toast.css">
    </head>
    <body>
        <div class="wrapper">
            <div class="app">
                <header class="heading">Todo List</header>
                <input style="display: none" id="ID_USER" value="<%= session.getAttribute("idUser")%>"/>
                <div class="searchTodo_filterTodo">
                    <div class="searchTodo">
                        <input type="text" name="search_todo" class="search_todo" placeholder="Find your todo..." />
                        <button style="display: inline-block" class="btn btn_searchTodo">Search</button>
                    </div>
                    <div class="filterTodo">
                        <div>
                            <select class="statusTodo">
                                <option value="">All</option>
                                <option value="1">Complete</option>
                                <option value="0">Incomplete</option>
                            </select>
                            <i class="fa-solid fa-arrow-down arrow-to-bottom"></i>
                        </div>
                    </div>
                </div>
                <div class="Todos">
                    <ul class="list_todo"></ul>
                    <div class="imgEmptyList">
                        <img src="img/emptyList.png" alt=""/>
                    </div>
                    <div class="addTodo_wrapper">
                        <button id="btn-openModal" class="btn addTodo" type="button">
                            <i class="fa-solid fa-plus"></i>
                            &nbsp;&nbsp;
                            <span>Add Todo</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal_wrapper modalWrapperTodo">
            <div class="modal">
                <h3 class="titleModal title-add">ADD TODO</h3>   
                <h3 class="titleModal title-details">DETAILS TODO</h3>
                <form class="form-todo">
                    <input id="idTodoFormUpdate" type="text"style="display: none"/>
                    <div class="form-item">
                        <label for="name" class="label_modal">Name: </label>
                        <br />
                        <input required="true" name="titleTodo" type="text" class="input_modal" id="titleTodo"/>
                    </div>
                    <!-- -->
                    <div class="form-item">
                        <label for="note" class="label_modal">Note: </label>
                        <br />
                        <textarea name="noteTodo" id="noteTodo" class="input_modal" rows="6"></textarea>
                    </div>
                    <!-- -->
                    <div class="dateWrapper">
                        <p class="dateItem">Ngày tạo:&nbsp;
                            <span class="date_createAt"></span>
                        </p>                              
                        <p class="dateItem">Chỉnh sửa lần cuối:&nbsp;
                            <span class="date_updateAt">--/--/--</span>
                        </p>
                    </div>
                    <!-- -->
                    <div class="group-btn">
                        <button class="btn btn_success" id="btn-AddTodo" type="button">Add todo</button>
                        <button class="btn btn_info" id="btn-UpdateTodo" type="button">Update todo</button>
                        <button class="btn btn_dangerous" id="btn-closeModal" type="button">Cancel</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="modal_wrapper modalWrapperUser">
            <div class="modal modalUser">
                <h3 style="display: block" class="titleModal">INFO USER</h3>   
                <%-- form info user --%>
                <form class="form-infoUser">
                    <div class="form-item">
                        <label for="userName" class="label_modal">Username: </label>
                        <br />
                        <input name="userName" disabled="true" type="text" class="input_modal"  value="<%= session.getAttribute("userName")%>"/>
                    </div>
                    <!-- -->
                    <div class="form-item">
                        <label for="password" class="label_modal">Password: </label>
                        <br />
                        <input name="password" disabled="true" type="password" class="input_modal" value="<%= session.getAttribute("password")%>"/>
                    </div>
                    <div class="group-btn">
                        <button style="display: block" class="btn btn_info" id="btn-UpdateUser" type="button">Update</button>
                        <button style="display: block" class="btn btn_dangerous btn-closeModalUser"   type="button">Cancel</button>
                    </div>
                </form>
                <%-- form update user --%>
                <form class="form-updateUser">
                    <!-- -->
                    <div class="form-item">
                        <label for="password" class="label_modal">Current Password: </label>
                        <br />
                        <input name="currentPassword" type="password" class="input_modal"   />
                    </div>
                    <div class="form-item">
                        <label for="password" class="label_modal">New Password: </label>
                        <br />
                        <input name="newPassword" type="password" class="input_modal"   />
                    </div>
                    <div class="form-item">
                        <label for="password" class="label_modal">Confirm Password: </label>
                        <br />
                        <input name="confirmPassword" type="password" class="input_modal"   />
                    </div>
                    <div class="group-btn">
                        <button style="display: block" class="btn btn_success" id="btn-SaveUser" type="button">Save</button>
                        <button style="display: block" class="btn btn_dangerous btn-closeModalUser"  type="button">Cancel</button>
                    </div>
                </form>
            </div>
        </div>     
        <div id="toast_wrap"></div>
        <div class="infoUserWrapper">
            <div class="infoUser">
                <img src="https://source.unsplash.com/random/500x500?sig=1002202810" />
                <p class=""><%= session.getAttribute("userName")%></p>

            </div>
            <button class="sign-out" id="btn-signOut">
                <i style="margin-right: 6px" class="fa-solid fa-right-from-bracket"></i>
                Sign out
            </button>
        </div>
    </body>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="js/Homepage.js"></script>
</html>
