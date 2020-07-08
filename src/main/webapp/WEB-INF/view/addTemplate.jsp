<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <style>
        .templateContainer{
            border-style: solid;
            border-color: #bbbdb9;
            border-width: 1px;
            border-radius: 30px;
            padding: 20px;
            margin-top: 2%;
            margin-left: 10%;
            margin-right: 10%;
        }
        .templateElement{
            margin-bottom: 10px;
        }
        #body{
            height: 40%;
        }
        #signature{
            height: 20%;
        }
        .error{
            color: red;
        }
        .errorLabel{
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<form:form modelAttribute="templateAttribute" action="saveTemplate" method="post" enctype="multipart/form-data">
    <div class="templateContainer">
        <h2>Template</h2>
        <div class="errorLabel"><form:errors path="title" cssClass="error"/></div>
        <div class="templateElement">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">Template name</span>
                </div>
                <form:input cssClass="form-control" path="title"/>
            </div>
        </div>
        <div class="errorLabel"><form:errors path="subject" cssClass="error"/></div>
        <div class="templateElement">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">Subject</span>
                </div>
                <form:input cssClass="form-control" path="subject"/>
            </div>
        </div>
        <div class="errorLabel"><form:errors path="body" cssClass="error"/></div>
        <div class="templateElement">
            <form:textarea path="body" cssClass="form-control" id="body" placeholder="Email body"/>
        </div>
        <div class="templateElement">
            <div class="templateElement">
                <form:textarea path="signature" cssClass="form-control" id="signature" placeholder="Signature (optional)"/>
            </div>
        </div>
        <div class="templateElement">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </div>

</form:form>

</body>
</html>