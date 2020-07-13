<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
</head>
<body>

<%int rowNum = 1;%>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Email</th>
        <th scope="col">Related campaigns</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accounts}" var="account">
        <tr>
            <th scope="row"><%=rowNum%>
            </th>
            <td><c:out value="${account.firstName}"/></td>
            <td><c:out value="${account.lastName}"/></td>
            <td><c:out value="${account.email}"/></td>
            <td><c:out value="${account.campaigns.size()}"/></td>
        </tr>
        <%rowNum++;%>
    </c:forEach>
    </tbody>
</table>

</body>
</html>