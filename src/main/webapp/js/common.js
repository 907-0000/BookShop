//验证用户是否登录
function isLogin(){
    var adminId=sessionStorage.getItem("adminId");
    var nickName=sessionStorage.getItem("nickName");
    if (adminId==null || nickName==null){//没登陆
        location.href="login.html";
        return;
    }
}