<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>

    <style>
        .accountFromContainer {
            background-color: #2b3034;
            border-radius: 15px;
            padding: 20px;
            margin: 10%;
        }

        .row {
            margin: 10px;
        }

        h3 {
            color: #e1e1e1;
            text-align: center;
        }
        .accountTitle{
            display: inline-block;
        }
    </style>

</head>
<body>
<%--TODO--%>
<form:form modelAttribute="account" action="saveAccount" method="post">
<div class="accountFromContainer">
    <div class="accountTitle">
        <h3>Account</h3>
        <c:if test="${connectionStatus != ''}">
            <span class="badge badge-danger"><c:out value="${connectionStatus}"/></span>
        </c:if>
    </div>
    <div class="row">
        <div class="col">
            <form:input type="text" class="form-control" placeholder="First name" path="firstName"/>
        </div>
        <div class="col">
            <form:input type="text" class="form-control" placeholder="Last name" path="lastName"/>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="form-group">
                <form:input path="email" type="email" class="form-control" id="exampleFormControlInput1"
                            placeholder="name@example.com"/>
            </div>
        </div>
        <div class="col">
            <div class="form-group">
                <form:input path="password" type="password" class="form-control" id="inputPassword"
                            placeholder="Password"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="campaignControlPanel">
                <button type="submit" class="btn btn-primary">Test
                    connection and save account
                </button>
            </div>
        </div>
    </div>
    </form:form>


</body>
</html>