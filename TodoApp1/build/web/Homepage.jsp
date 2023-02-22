<%-- 
    Document   : Homepage
    Created on : Feb 21, 2023, 9:28:53 PM
    Author     : FPTshop
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
          integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="css/Homepage.css">
    <body>
        <div class="wrapper">
            <div class="app">
                <header class="heading">Todo List</header>
                <div class="searchTodo_filterTodo">
                    <div class="searchTodo">
                        <input type="text" name="search_todo" class="search_todo" placeholder="Search your todo...">
                        <button class="btn btn_info">Tìm kiếm</button>
                    </div>
                    <div class="filterTodo">
                        <div>
                            <select class="statusTodo">
                                <option value="all">Tất cả</option>
                                <option value="true">Đã hoàn thành</option>
                                <option value="false">Chưa hoàn thành</option>
                            </select>
                            <i class="fa-solid fa-arrow-down arrow-to-bottom"></i>
                        </div>
                    </div>
                </div>
                <div class="Todos">
                    <ul class="list_todo">
                        <c:forEach items="${listTodo}" var="o" >
                            <li class="item_todo">
                                <p>${o.title}</p>
                                <div class="icons">
                                    <div class="icon-wrapper icon-check">
                                        <i class="fa-sharp fa-regular fa-circle-check"></i>
                                    </div>
                                    <div class="icon-wrapper icon-trash">
                                        <i class="fa-solid fa-trash  "></i>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
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
        <div class="modal_wrapper">
            <div class="modal">
                <header class="title">Add Your Todo</header>
                <form class="form-main">
                    <div class="form-item">
                        <label for="name" class="label_modal">Name: </label>
                        <br />
                        <input type="text" class="input_modal" name="name">
                    </div>
                    <!-- -->
                    <div class="form-item">
                        <label for="note" class="label_modal">Note: </label>
                        <br />
                        <textarea name="note" class="input_modal" rows="6"></textarea>
                    </div>
                    <div class="group-btn">
                        <button class="btn btn_success" type="submit">Add todo</button>
                        <button class="btn btn_dangerous" id="btn-closeModal" type="button">Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script src="js/Homepage.js"></script>
</html>
