<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014-11-06
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path= request.getContextPath();%>
<html>
<head>
    <title></title>
    <%--<link href="<%=path%>/extjs/resources/ext-theme-gray/ext-theme-gray-all-rtl.css" type="text/css" rel="stylesheet"/>--%>
    <script src="<%=path%>/extjs/ext-all.js" type="text/javascript"></script>
    <%--<script src="<%=path%>/extjs/locale/ext-lang-zh_CN.js" type="text/javascript"></script>--%>
    <script src="<%=path%>/myjs/logindemo.js" type="text/javascript"></script>
    <script type="text/javascript">
        Ext.onReady(function () {
            Ext.create('ShinowLogin', {
                renderTo: testlogin
            }).center();
        });
    </script>
    <style>
        body{ font-family:"微软雅黑";}
        *{ margin:0px; padding:0px;}
        .denglu{ position:relative; width:470px; height:400px; margin:150px auto 0;}
        .tiao{ height:50px; background:#c5e6eb;}
        .white{ height:350px; background:#fff;}
        .dingwei{ position:absolute; top:70px; left:-17px; width:430px; height:60px; background:url(bcDingwei.png) no-repeat; color:#fff; line-height:50px; font-weight:bold; text-indent:52px;}
        #textfield-1010-labelEl,#textfield-1011-labelEl{ float:left; width: 64px; text-align: center;}
        #textfield-1010-inputEl{ float:left;}
        #ext-comp-1009-formTable{ margin-left: 43px; }
        #button-1016,#button-1017{ color: #000;}
        #textfield-1010-inputEl,#textfield-1011-inputEl,#textfield-1013-inputEl{ width: 100px; height: 30px;}
        #panel-1012-innerCt{width: 300px;}
        #textfield-1013{float: left;}
        #panel-1014{ float:left;}
    </style>
</head>
<body style="background:url(bc.jpg) repeat-x;">
<div class="denglu">
    <h2 class="tiao">后台管理系统</h2>
    <p class="white">
    </p>
    <h3 class="dingwei">
        登陆窗口
        <div id="testlogin"></div>
    </h3>

</div>
</body>
</html>
