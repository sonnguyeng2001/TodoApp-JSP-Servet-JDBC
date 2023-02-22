

//SignIn - SignUp Variables
let errMessage_SignUp = $(".errSignUp");
let errMessage_SignIn = $(".errSignIn");


// Toggle SignUp - SignIn
const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
    errMessage_SignUp.text("");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
    errMessage_SignIn.text("");
});



// Handle SignIn
//const btn_SignIn = $('#btn-submit-signIn');
//btn_SignIn.click(function () {
//    var username_SignIn = $("input[name=username_SignIn]").val();
//    var password_SignIn = $("input[name=password_SignIn]").val();
//    var data = $("#formSignIn").serialize();
//    if (username_SignIn.trim() !== "" && password_SignIn.trim() !== "")
//    {
//        $.ajax({
//            url: 'LoginServlet',
//            data: data,
//            dataType: 'json',
//            type: 'POST',
//            success: function (data) {
//                if (data.status)
//                {
//                    window.location.href = "Homepage.jsp";
//                } else {
//                    errMessage_SignIn.text("Tên đăng nhập hoặc mật khẩu không chính xác");
//                }
//            },
//            error: function (res) {
//                console.log(res.responseText);
//            }
//        });
//    } else {
//        errMessage_SignIn.html("Vui lòng điền đầy đủ thông tin");
//
//    }
//});

// Handle SignUp
const btn_SignUp = $('#btn-submit-signUp');
btn_SignUp.click(function () {
    var username_SignUp = $("input[name=username_SignUp]").val();
    var password_SignUp = $("input[name=password_SignUp]").val();
    var confirmPassword_SignUp = $("input[name=confirmPassword_SignUp]").val();

    var data = $("#formSignUp").serialize();
    if (username_SignUp.trim() !== "" && password_SignUp.trim() !== "" && confirmPassword_SignUp.trim() !== "")
    {
        if (password_SignUp.toString().toLowerCase() === confirmPassword_SignUp.toString().toLowerCase())
        {
            $.ajax({
                url: 'RegisterServlet',
                data: data,
                dataType: 'json',
                type: 'POST',
                success: function (data) {
                    if (data.status)
                    {
                        errMessage_SignUp.addClass("text-success");
                        errMessage_SignUp.text("Đăng ký thành công");

                    } else {
                        errMessage_SignUp.text("Tên đăng nhập đã tồn tài");
                        errMessage_SignUp.removeClass("text-success");

                    }
                },
                error: function (res) {
                    console.log(res.responseText);
                }
            });
        } else {
            errMessage_SignUp.text("2 mật khẩu phải giống nhau !!!");
        }

    } else {
        errMessage_SignUp.text("Vui lòng điền đầy đủ thông tin !!!");

    }
});