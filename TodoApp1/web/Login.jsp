<%-- 
    Document   : Login
    Created on : Feb 20, 2023, 10:24:06 PM
    Author     : FPTshop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/Login.css" />

    </head>
    <body>
        <h2 class="heading">TODO APP</h2>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form id="formSignUp">
                    <h1>Create Account</h1>
                    <div class="social-container"></div>
                    <input name="username_SignUp" type="username" placeholder="Username" />
                    <input name="password_SignUp" type="password" placeholder="Password" />
                    <input name="confirmPassword_SignUp" type="password" placeholder="Confirm Password" />
                    <br />
                    <p class="errMessage errSignUp"></p>
                    <br />

                    <button id="btn-submit-signUp" type="button">Sign Up</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form id="formSignIn" action="LoginServlet" method="POST">
                    <h1>Sign in</h1>
                    <div class="social-container"></div>
                    <input name="username_SignIn"  type="username" placeholder="Username" />
                    <input name="password_SignIn"  type="password" placeholder="Password" />
                    <br />
                    <p class="errMessage errSignIn">${errMessage}</p>
                    <br />
                    <button id="btn-submit-signIn" type="subit">Sign In</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="ghost" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js" integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="js/Login.js"></script>

</html>
