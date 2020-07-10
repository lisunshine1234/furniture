
var contextPath = $('#contextPath').val()
function is_check(CHILD_ID) {
    var FATHER_ID = document.getElementById('FATHER_ID').value;
    var checkbox = document.getElementsByName('CHILD_ID');
    var SearchInfo = document.getElementById('SearchInfoSave').value;

    var list = new Array();
    var array = new Array();

    if (checkbox[0].checked == true) {
        for (var i = 1; i < checkbox.length; i++) {
            checkbox[i].checked = true;
        }
        for (var i = 1; i < checkbox.length; i++) {
            if (checkbox[i].value == CHILD_ID) {
                if (checkbox[i].checked == true) {
                    checkbox[0].checked = false;
                    checkbox[i].checked = false;
                }
            }
        }
    }

    // 复选框选中记录
    for (var i = 1; i < checkbox.length; i++) {
        if (checkbox[i].checked == true) {
            list[i] = checkbox[i].value;
        }
        if (checkbox[i].checked == false) {
            checkbox[0].checked = false;
            list[i] = -1;
        }
    }
    // 字符串整理
    for (var j = 1; j < list.length; j++) {
        if (list[j] > 0) {
            array.push(list[j]);
        }
    }

    if (array.length <= 0) {
        for (var i = 1; i < checkbox.length; i++) {
            array.push(checkbox[i].value);
        }
    }

    $.ajax({
        url: contextPath+"/productCheck",
        type: "get",// 请求方式
        async: false,
        data: {
            "array": array,
            "fatherId": FATHER_ID,
            "SearchInfo": SearchInfo
        },
        dataType: "json",// 设置返回数据类型
        success: function (jsonArray) {
            childProduct(jsonArray);
        },
        error: function (XMLHttpRequest, statusText) {
            alert("选中失败!")
        }// 响应失败后执行的回调方法
    })
}

//ajax实现家具列表异步刷新
function childProduct(proList) {
    var DisplayProduct = document.getElementById('DisplayProduct');
    DisplayProduct.innerHTML = "";
    let html = "";
    if (proList != null && proList.length > 0) {
        for (var i = 0; i < proList.length; i++) {
            html +=
                "<div class='col-xs-6 col-md-4 col-md-3 col-lg-2 product-left' style='margin: 0;padding: 10px'\n" +
                "   <div class='p-one simpleCart_shelfItem jwe'>\n" +
                "       <a href='"+contextPath +"/productView?prodId='" + proList[i].id + "'>\n" +
                "           <div style='wid100%;height:0;position: relative;padding-bottom: 100%'>\n" +
                "               <img src='" +contextPath + proList[i].address1 + "' style='object-fit:cover;top:0;left:0;wid100%;height:100%;position: absolute;' alt=''/>\n" +
                "           </div>\n" +
                "       </a>\n" +
                "       <div class='product-left-cart' style='height: 45px; wid 140px;'>\n" +
                "           <a><span class=' item_price' text='" + proList[i].prodName + "'> </span></a>\n" +
                "       <div class='clearfix'></div>\n" +
                "       </div>\n" +
                "   </div>\n" +
                "</div>"
        }
        DisplayProduct.innerHTML = html;
    } else {
        DisplayProduct.innerHTML = "暂无家具信息！";
    }

}

// 获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

// 登陆
function GetUrl() {
    let contextPath = $('#contextPath').val()
    var url = window.location.href;
    window.location.href = contextPath+"/login?url=" + url.split(contextPath)[1];
}

//注册
function Register() {
    var url = window.location.href;
    window.location.href = contextPath+"/register?url=" + url;
}

// 查看收藏夹判断登录
function IsOnloadViewCart(USER_ID) {
    if (USER_ID != null && USER_ID != "") {
        window.location.href = contextPath+"/cart";
    } else {
        alert("未登录！");
        window.location.href = contextPath+"/login?url=/cart";

    }
}

// 已经登陆跳转
function ReadyLoading(USER_ID) {
    var url = get_url();
    var r = getQueryString("url");

    if (USER_ID != null && USER_ID != "") {
        if (r == null || r == "") {
            window.location.href = "IndexServlet";
        } else {
            window.location.href = r;
        }
    } else {
        alert("未登录！");
        if (r == null || r == "") {
            window.location.href = "LoginServlet?url=" + url;
        } else {
            window.location.href = url;
        }
    }

}

// 加入收藏夹判断登录
function IsOnloadAddCart(PRODUCT_ID) {

    var url = window.location.href;
    if (CheckProductExist(url, PRODUCT_ID)) {
        $.ajax({
            url: contextPath+"/addCart",
            type: "GET",// 请求方式
            async: false,
            data: {
                "prodId": PRODUCT_ID,
                "url": url

            },
            dataType: "json",// 设置返回数据类型
            success: function (sign) {
                if (sign.valueOf() == 1) {
                    alert("成功添加到收藏夹！");
                } else if (sign.valueOf() == 2) {
                    window.location.href = contextPath+"/login?url=" + url;
                } else {
                    alert("添加到收藏夹失败！")
                }
            },
            error: function (XMLHttpRequest, statusText) {
                alert("添加到收藏夹失败!")
            }// 响应失败后执行的回调方法
        })

    } else {
        alert("已经收藏过该家具！");
    }
}

// 留言判断登陆
function IsOnloadContact() {
    var url = window.location.href;
    var messageInfo = document.getElementById("messageInfo");

    if (validate_required(messageInfo, "请填写内容！") == false) {
        messageInfo.focus();
    } else {
        $.ajax({
            url: contextPath+"/message",
            type: "GET",// 请求方式
            async: false,
            data: {
                "messageInfo": messageInfo.value
            },
            dataType: "json",// 设置返回数据类型
            success: function (test) {
                if (test.valueOf() == 1) {
                    messageInfo.value = "";
                    alert("提交成功！");
                } else if (test.valueOf() == 2) {
                    alert("未登录！");
                    window.location.href = contextPath+"/login?url=" + url;
                } else {
                    alert("提交失败!字数最多不能超过200。")
                }

            },
            error: function (XMLHttpRequest, statusText) {
                alert("提交失败!")
            }// 响应失败后执行的回调方法
        })
    }

}

// 判断输入框是否为空方法
function validate_required(field, alerttxt) {
    with (field) {
        if (value == null || value == "") {
            alert(alerttxt);
            return false
        } else {
            return true
        }
    }
}

// 查找
function DoSearch() {
    var Search = document.getElementById("SearchInfo");
    if (validate_required(Search, "查找内容不能为空") == false) {
        SearchInfo.focus();
        return false;
    } else {
        return true;
    }

}

// 登录页面
function validate_form(thisform) {
    with (thisform) {
        if (validate_required(userName, "账号不能为空！") == false) {
            userName.focus();
            return false
        }

        if (validate_required(passWord, "密码不能为空！") == false) {
            passWord.focus();
            return false
        }

        if (validate_wrong(userName, "UserWrong") == false) {
            userName.focus();
            return false
        }
        if (validate_wrong(passWord, "PasswordWrong") == false) {
            passWord.focus();
            return false
        }

    }
}

// 登录页面
function validate_wrong(field, obj) {
    var Wrong = document.getElementById(obj);

    var regName = /^[a-zA-Z][a-zA-Z0-9]{4,29}$/;
    var regPass = /^[a-zA-Z0-9]{5,30}$/;

    with (field) {
        switch (id) {
            case "userName":
                if (regName.test(value) == false && value != null && value != "") {
                    Wrong.innerHTML = "账号格式错误！";
                    return false;
                } else {
                    Wrong.innerHTML = "";
                    return true;
                }
                break;
            case "passWord":
                if (regPass.test(value) == false && value != null && value != "") {
                    Wrong.innerHTML = "密码格式错误！";
                    return false;
                } else {
                    Wrong.innerHTML = "";
                    return true;
                }
        }
        return true;
    }
}

// 注册判断为空及错误字符
function register_form(thisform) {
    with (thisform) {
        if (validate_required(userName, "用户名（账号）不能为空！") == false) {
            userName.focus();
            return false
        }

        if (validate_required(callName, "姓名（昵称）不能为空！") == false) {
            callName.focus();
            return false
        }

        if (validate_required(passWord, "密码不能为空！") == false) {
            passWord.focus();
            return false
        }

        if (validate_required(rePassWord, "确认密码不能为空！") == false) {
            rePassWord.focus();
            return false
        }

        if (validate_required(tel, "手机号码不能为空！") == false) {
            tel.focus();
            return false
        }

        if (validate_required(email, "邮箱不能为空！") == false) {
            email.focus();
            return false
        }

        if (validate_required(question, "密保问题不能为空！") == false) {
            question.focus();
            return false
        }

        if (validate_required(answer, "密保答案不能为空！") == false) {
            answer.focus();
            return false
        }

        if (register_wrong(userName, 'UserRight', 'UserWrong', 'UserWrongText') == false) {
            userName.focus();
            return false
        }
        if (register_wrong(callName, 'NameRight', 'NameWrong', 'NameWrongText') == false) {
            callName.focus();
            return false
        }
        if (register_wrong(passWord, 'PasswordRight', 'PasswordWrong',
            'PasswordWrongText') == false) {
            passWord.focus();
            return false
        }
        if (register_wrong(rePassWord, 'RePasswordRight', 'RePasswordWrong',
            'RePasswordWrongText') == false) {
            rePassWord.focus();
            return false
        }
        if (register_wrong(tel, 'TelRight', 'TelWrong', 'TelWrongText') == false) {
            tel.focus();
            return false
        }
        if (register_wrong(email, 'EmailRight', 'EmailWrong', 'EmailWrongText') == false) {
            email.focus();
            return false
        }
        if (register_wrong(question, 'QuestionRight', 'QuestionWrong',
            'QuestionWrongText') == false) {
            question.focus();
            return false
        }
        if (register_wrong(answer, 'AnswerRight', 'AnswerWrong',
            'AnswerWrongText') == false) {
            answer.focus();
            return false
        }

    }
}

// 注册判断错误字符
function register_wrong(field, Right, Wrong, WrongText) {
    var regEmail = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
    var regMobile = /^1(3|4|5|7|8)\d{9}$/;
    var regName = /^[a-zA-Z][a-zA-Z0-9]{4,29}$/;
    var regPass = /^[a-zA-Z0-9]{5,30}$/;
    var regChineseWord = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/;

    var Right = document.getElementById(Right);
    var Wrong = document.getElementById(Wrong);
    var WrongText = document.getElementById(WrongText);

    with (field) {
        switch (id) {
            case "userName":
                if (value != null && value != "") {
                    if (regName.test(value) == false) {
                        Wrong.style.display = "";
                        Right.style.display = "none";
                        WrongText.innerHTML = "用户名只能是字母开头和字母数字结尾，长度在5-30之间!";
                        return false;
                    } else {
                        if (CheckUserNameExist(value)) {
                            Wrong.style.display = "none";
                            WrongText.innerHTML = "";
                            Right.style.display = "";
                            return true;
                        } else {
                            Wrong.style.display = "";
                            WrongText.innerHTML = "该用户名已存在！";
                            Right.style.display = "none";
                            return false;
                        }
                    }
                }
                break;
            case "callName":
                if (value != null && value != "") {
                    if (regChineseWord.test(value) == false || value.length < 2
                        || value.length > 10) {
                        Wrong.style.display = "";
                        Right.style.display = "none";
                        WrongText.innerHTML = "姓名（昵称）只能是中文、英文、数字和下划线的组合，并且长度不能小于2，大于10！";
                        return false;
                    }
                }
                break;
            case "passWord":
                if (value != null && value != "") {
                    if (regPass.test(value) == false) {
                        Wrong.style.display = "";
                        Right.style.display = "none";
                        WrongText.innerHTML = "密码不能含有非法字符，长度在5-30之间！";
                        return false;
                    } else {
                        Wrong.style.display = "none";
                        WrongText.innerHTML = "";
                        Right.style.display = "";
                        return true;
                    }
                }
                break;
            case "rePassWord":
                if (value != null && value != "") {
                    if (value != document.getElementById("passWord").value) {
                        Wrong.style.display = "";
                        Right.style.display = "none";
                        WrongText.innerHTML = "两次输入的密码不相同！";
                        return false;
                    } else {
                        Wrong.style.display = "none";
                        WrongText.innerHTML = "";
                        Right.style.display = "";
                        return true;
                    }
                }
                break;
            case "tel":
                if (value != null && value != "") {
                    if (regMobile.test(value) == false) {
                        Wrong.style.display = "";
                        Right.style.display = "none";
                        WrongText.innerHTML = "格式错误！手机必须为11位并且只能是数字！";
                        return false;
                    } else {
                        if (CheckPhoneExist(value)) {
                            Wrong.style.display = "none";
                            WrongText.innerHTML = "";
                            Right.style.display = "";
                            return true;
                        } else {
                            Wrong.style.display = "";
                            WrongText.innerHTML = "该手机号码已存在！";
                            Right.style.display = "none";
                            return false;
                        }
                    }
                }
                break;
            case "email":
                if (value != null && value != "") {
                    if (regEmail.test(value) == false || value.length > 30) {
                        Wrong.style.display = "";
                        Right.style.display = "none";
                        WrongText.innerHTML = "电子邮件不能为空并且长度不能大于30,例:email@exemple.com";
                        return false;
                    } else {
                        if (CheckEmailExist(value)) {
                            Wrong.style.display = "none";
                            WrongText.innerHTML = "";
                            Right.style.display = "";
                            return true;
                        } else {
                            Wrong.style.display = "";
                            WrongText.innerHTML = "该邮箱已存在！";
                            Right.style.display = "none";
                            return false;
                        }
                    }
                }
                break;
            case "question":
                if (value != null && value != "") {
                    if (regChineseWord.test(value) == false || value.length < 5
                        || value.length > 30) {
                        Wrong.style.display = "";
                        Right.style.display = "none";
                        WrongText.innerHTML = "密保问题只能是中文、英文、数字和下划线的组合，并且长度在5-30字之间！";
                        return false;
                    } else {
                        Wrong.style.display = "none";
                        WrongText.innerHTML = "";
                        Right.style.display = "";
                        return true;
                    }
                }
                break;
            case "answer":
                if (value != null && value != "") {
                    if (regChineseWord.test(value) == false || value.length < 2
                        || value.length > 30) {
                        Wrong.style.display = "";
                        Right.style.display = "none";
                        WrongText.innerHTML = "密保答案只能是中文、英文、数字和下划线的组合，并且长度在2-30字之间！";
                        return false;
                    } else {
                        Wrong.style.display = "none";
                        WrongText.innerHTML = "";
                        Right.style.display = "";
                        return true;
                    }
                }
                break;
        }
        return true;
    }
}

function CheckUserNameExist(value) {
    var flag = false;
    $.ajax({
        url: contextPath+"/checkLoginName",
        type: "GET",// 请求方式
        async: false,
        data: {
            "value": value
        },
        dataType: "json",// 设置返回数据类型
        success: function (sign) {
            if (sign.valueOf() == 1) {
                flag = true;
            } else {
                flag = false;
            }

        },
        error: function (XMLHttpRequest, statusText) {
            alert("系统异常!")
        }// 响应失败后执行的回调方法
    })
    return flag;
}

function CheckPhoneExist(value) {
    var flag = false;
    $.ajax({
        url: contextPath+"/checkPhone",
        type: "GET",// 请求方式
        async: false,
        data: {
            "value": value
        },
        dataType: "json",// 设置返回数据类型
        success: function (sign) {
            if (sign.valueOf() == 1) {
                flag = true;
            } else {
                flag = false;
            }

        },
        error: function (XMLHttpRequest, statusText) {
            alert("系统异常!")
        }// 响应失败后执行的回调方法
    })
    return flag;
}

function CheckEmailExist(value) {
    var flag = false;
    $.ajax({
        url: contextPath+"/checkEmail",
        type: "GET",// 请求方式
        async: false,
        data: {
            "value": value
        },
        dataType: "json",// 设置返回数据类型
        success: function (sign) {
            if (sign.valueOf() == 1) {
                flag = true;
            } else {
                flag = false;
            }

        },
        error: function (XMLHttpRequest, statusText) {
            alert("系统异常!")
        }// 响应失败后执行的回调方法
    })
    return flag;
}

function CheckProductExist(url, PRODUCT_ID) {
    var flag = false;
    $.ajax({
        url: contextPath+"/checkProduct",
        type: "GET",// 请求方式
        async: false,
        data: {
            "prodId": PRODUCT_ID,
            "url": url
        },
        dataType: "json",// 设置返回数据类型
        success: function (sign) {
            if (sign.valueOf() == 1) {
                flag = true;
            } else if (sign.valueOf() == 2) {
                alert("未登录！");
                flag = true;
            } else {
                flag = false;
            }
        },
        error: function (XMLHttpRequest, statusText) {
            alert("系统异常!")
        }// 响应失败后执行的回调方法
    })
    return flag;
}

// 收藏夹删除
function deleteCart(cartId) {
    $.ajax({
        url: contextPath+"/deleteCart",
        type: "get",// 请求方式
        async: false,
        data: {
            "cartId": cartId,
        },
        dataType: "json",// 设置返回数据类型
        success: function (sign) {

            if (sign.valueOf() == 1) {
                alert("删除成功");
            } else {
                alert("删除失败");
            }
            window.location.href = contextPath+"/cart";
        },
        error: function (XMLHttpRequest, statusText) {
            alert("系统异常!")
        }// 响应失败后执行的回调方法
    })
}
