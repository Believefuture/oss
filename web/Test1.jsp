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
    <link href="<%=path%>/extjs/resources/ext-theme-access/ext-theme-access-all.css" type="text/css" rel="stylesheet"/>
    <script src="<%=path%>/extjs/ext-all.js" type="text/javascript"></script>
    <script src="<%=path%>/extjs/locale/ext-lang-zh_CN.js" type="text/javascript"></script>
    <script src="<%=path%>/myjs/Extjs/test.js" type="text/javascript"></script>
    <script type="text/javascript">
        Ext.onReady(function () {
            Ext.create('myjs.Extjs.test', {
                renderTo: Ext.getBody()
            }).center();
        });
    </script>
</head>
<body>

</body>
</html>
