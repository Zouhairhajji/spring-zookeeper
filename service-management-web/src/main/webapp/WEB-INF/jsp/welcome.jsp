<%-- 
    Document   : homePage
    Created on : 8 août 2017, 16:03:41
    Author     : fqlh0717
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/jsp/template/header.jsp" />
    </head>
    <body class="hold-transition skin-blue sidebar-collapse">
        <div class="wrapper">
            <jsp:include page="/WEB-INF/jsp/template/navbar.jsp" />
            <jsp:include page="/WEB-INF/jsp/template/leftmenu.jsp" />
            <div class="content-wrapper">
                <section class="content">
                    Content Here Prototype
                </section>
            </div>
            <jsp:include page="/WEB-INF/jsp/template/footer.jsp" />
        </div>
        <jsp:include page="/WEB-INF/jsp/template/javascript.jsp" />
    </body>
</html>