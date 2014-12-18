<%@ page import="com.shinow.entity.TAuOperInfoEntity" %>
<%@ page import="java.awt.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path=request.getContextPath();%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
    <link href="<%=path%>/extjs/resources/ext-theme-access/ext-theme-access-all.css" type="text/css" rel="stylesheet"/>
    <script src="<%=path%>/extjs/ext-all.js" type="text/javascript"></script>
    <script src="<%=path%>/extjs/locale/ext-lang-zh_CN.js" type="text/javascript"></script>
    <script src="<%=path%>/myjs/main.js" type="text/javascript"></script>
    <script type="text/javascript">
        Ext.onReady(function () {
            Ext.create('ShinowMain', {
                renderTo: Ext.getBody()
            }).center();
        });
    </script>
</head>

<body>
<%--<%--%>
    <%--TAuOperInfoEntity user=(TAuOperInfoEntity)session.getAttribute("loginValid");--%>


<%--%>--%>
<%--<s:hidden name="session" id="session" value="<%=user%>"></s:hidden>--%>
<s:hidden name="jsonUrl" id="jsonUrl"></s:hidden>

</body>
</html>
