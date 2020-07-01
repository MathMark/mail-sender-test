<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <style>
        .textClass{
            white-space: nowrap;
            width: 200px;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .container{
            padding: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <%int i = 0; %>
    <c:forEach items="${templatesTable}" var="templateUnit" varStatus="loop">
        <div class="row">
            <c:forEach items="${templateUnit}" var="template">
                <div class="col-sm">
                    <div class="cardClass">
                        <div class="card text-white bg-dark mb-3">
                            <div class="card-header"><c:out value="${template.title}"/> </div>
                            <div class="card-body">
                                <h5 class="card-title"><p class="textClassClass"><c:out value="${template.subject}"/></p></h5>
                                <p class="card-text"><p class="textClass">
                                <c:out value="${template.body}"/>
                            </p></p>
                                <a href="#" class="btn btn-primary">Open</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:forEach>
</div>
</body>
</html>