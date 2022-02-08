$(function () {
    //用户名input获取光标
    $("#user_name").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入用户名").css("display", "inline-block").css("color", "#00A0E9");
    });
    //昵称input获取光标
    $("#user_nickname").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入昵称").css("display", "inline-block").css("color", "#00A0E9");
    });
    //电话input获取光标
    $("#user_phone").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入电话").css("display", "inline-block").css("color", "#00A0E9");
    });
    //邮件input获取光标
    $("#user_email").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入邮件").css("display", "inline-block").css("color", "#00A0E9");
    });
    //个性签名input获取光标
    $("#user_sign").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入个性签名").css("display", "inline-block").css("color", "#00A0E9");
    });
    //密码input获取光标
    $("#user_password").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入密码").css("display", "inline-block").css("color", "#00A0E9");
    });
    //再次输入密码input获取光标
    $("#user_password_one").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请再次输入密码").css("display", "inline-block").css("color", "#00A0E9");
    });
    //出生日期input获取光标
    $("#user_birthday").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入出生日期").css("display", "inline-block").css("color", "#00A0E9");
    });
    //地址input光标
    $("#user_address").focus(function () {
        $(this).css("border", "1px solid #3879D9")
            .next().text("请输入地址").css("display", "inline-block").css("color", "#00A0E9");
    });

    //input离开光标
    $(".form-text").blur(function () {
        $(this).css("border-color", "#cccccc")
            .next().css("display", "none");
    });

    //非空验证
    $("#register_sub").click(function () {
        //昵称
        var user_nickname = $.trim($("input[name=user_nickname]").val());
        //电话
        var user_phone = $.trim($("input[name=user_phone]").val());
        //邮件
        var user_email = $.trim($("input[name=user_email]").val());
        //个性签名
        var user_sign = $.trim($("input[name=user_sign]").val());
        //密码
        var user_password = $.trim($("input[name=user_password]").val());
        //确认密码
        var user_password_one = $.trim($("input[name=user_password_one]").val());
        //出生日期
        var user_birthday = $.trim($("input[name=user_birthday]").val());
        //地址
        var user_address = $.trim($("input[name=user_address]").val());

        //验证密码的格式 包含数字和英文字母
        var reg = new RegExp(/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/);
        if (user_nickname == null || user_nickname === "") {
            $("#user_nickname").css("border", "1px solid red")
                .next().text("请输入昵称").css("display", "inline-block").css("color", "red");
            return false;
        } else if (user_phone == null || user_phone === "") {
            $("#user_phone").css("border", "1px solid red")
                .next().text("请输入电话").css("display", "inline-block").css("color", "red");
            return false;
        }else if (user_email == null || user_email === "") {
            $("#user_email").css("border", "1px solid red")
                .next().text("请输入邮件").css("display", "inline-block").css("color", "red");
            return false;
        }else if (user_password == null || user_password === "") {
            $("#user_password").css("border", "1px solid red")
                .next().text("请输入密码").css("display", "inline-block").css("color", "red");
            return false;
        } else if (user_password_one == null || user_password_one === "") {
            $("#user_password_one").css("border", "1px solid red")
                .next().text("请重复输入密码").css("display", "inline-block").css("color", "red");
            return false;
        } else if (!reg.test(user_password)) {
            $("#user_password").css("border", "1px solid red")
                .next().text("密码格式必须包含数字和字母").css("display", "inline-block").css("color", "red");
            return false;
        } else if (user_password !== user_password_one) {
            $("#user_password_one").css("border", "1px solid red")
                .next().text("两次输入密码不相同").css("display", "inline-block").css("color", "red");
            return false;
        } else if (user_birthday == null || user_birthday === "") {
            $("#user_birthday").css("border", "1px solid red")
                .next().text("请选择出生日期").css("display", "inline-block").css("color", "red");
            return false;
        }
        return true;
    });
});


//图片上传
function uploadImage(fileDom) {
    //获取文件
    var file = fileDom.files[0];
    //判断类型
    var imageType = /^image\//;
    if (file === undefined || !imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //判断大小
    if (file.size > 512000) {
        alert("图片大小不能超过500K！");
        return;
    }
    //清空值
    $(fileDom).val('');
    var formData = new FormData();
    formData.append("file", file);
    //上传图片
    $.ajax({
        url: "/tmall/user/uploadUserHeadImage",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        dataType: "json",
        mimeType: "multipart/form-data",
        success: function (data) {
            if (data.success) {
                $(fileDom).prev("img").attr("src","/tmall/res/images/item/userProfilePicture/"+data.fileName);
                $("#user_icon_value").val(data.fileName);
            } else {
                alert("图片上传异常！");
            }
        },
        beforeSend: function () {
        },
        error: function () {

        }
    });
}