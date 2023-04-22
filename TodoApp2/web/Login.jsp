<%-- 
    Document   : Login
    Created on : Mar 17, 2023, 6:47:32 PM
    Author     : FPTshop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/Login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h2 class="heading">TODO APP</h2>
        <div class="container" id="container">
            <%--form Register --%>
            <div class="form-container sign-up-container">
                <form id="formSignUp">
                    <h1>Create Account</h1>
                    <div class="social-container"></div>
                    <input name="username_SignUp" type="text" placeholder="Username" />
                    <input name="password_SignUp" type="password" placeholder="Password" />
                    <input name="confirmPassword_SignUp" type="password" placeholder="Confirm Password" />
                    <br />
                    <p class="errMessage errSignUp"></p>
                     <div class="lds-ellipsis" id="loadingSignUp">
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                    </div>
                    <br />
                    <button id="btn-submit-signUp" type="button">Sign Up</button>
                </form>
            </div>

            <%--form Login --%>
            <div class="form-container sign-in-container">
                <form id="formSignIn" action="" method="POST">
                    <h1>Sign in</h1>
                    <div class="social-container"></div>
                    <input name="username_SignIn"  type="text" placeholder="Username" />
                    <input name="password_SignIn"  type="password" placeholder="Password" />
                    <br />
                    <p class="errMessage errSignIn">${errMessage}</p>
                    <div class="lds-ellipsis" id="loadingSignIn">
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                    </div>
                    <br />
                    <button id="btn-submit-signIn" type="button">Sign In</button>
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
    <script src="js/Login.js" type="text/javascript"></script>
</html>
