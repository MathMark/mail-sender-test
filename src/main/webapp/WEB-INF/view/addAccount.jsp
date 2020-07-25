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
            display: block;
            margin: auto;
            width: auto;
            padding: 15px;
        }
    </style>

</head>
<body>
<%--TODO--%>
<form:form modelAttribute="account" action="saveAccount" method="post">
<div class="accountFromContainer">
    <h3>Account</h3>
    <div class="accountTitle">
        <c:if test="${connectionStatus != ''}">
            <span class="badge badge-danger"><c:out value="${connectionStatus}"/></span>
        </c:if>
        <span class="badge badge-danger"><form:errors path="firstName" cssClass="error"/></span>
        <span class="badge badge-danger"><form:errors path="lastName" cssClass="error"/></span>
        <span class="badge badge-danger"><form:errors path="email" cssClass="error"/></span>
        <span class="badge badge-danger"><form:errors path="password" cssClass="error"/></span>
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