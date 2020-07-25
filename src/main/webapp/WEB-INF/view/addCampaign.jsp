<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <style>
        .campaignContainer {
            border-style: solid;
            border-width: 1px;
            border-color: #bbbdb9;
            border-radius: 10px;
            margin: 50px;
            padding: 30px;
            background-color: #e1e1e1;
        }

        .accountContainer {
            border-style: solid;
            border-width: 1px;
            border-color: #bbbdb9;
            border-radius: 10px;
            margin-top: 15px;
            padding: 15px;
            background-color: #ebebeb;
        }

        .templateContainer {
            border-style: solid;
            border-width: 1px;
            border-color: #bbbdb9;
            border-radius: 10px;
            margin-top: 15px;
            padding: 15px;
            background-color: #ebebeb;
        }

        .campaignReceiversContainer {
            margin-top: 15px;
        }

        .accountContainer > label {
            position: relative;
            top: -15px;
            left: 20px;
            background-color: white;
            font-size: medium;
        }
        .templateElement {
            margin-bottom: 5px;
        }

        .campaignBottomElement {
            display: inline-block;
        }

        h1 {
            text-align: center;
            font-size: medium;
            color: #595a59;
        }

        h2 {
            text-align: center;
            font-size: small;
            color: #595a59;
        }
        .error{
            color: red;
        }
    </style>
</head>
<body>
<form:form modelAttribute="campaignAttribute" action="saveCampaign" method="post" enctype="multipart/form-data">
    <div class="campaignContainer">
        <h1>Campaign</h1>
        <div class="campaignTitle">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">Title</span>
                </div>
                <form:input cssClass="form-control" path="title"/>
            </div>
        </div>
        <div class="accountContainer">
            <h2>Account</h2>
            <form:select cssClass="custom-select" itemValue="account" path="account.id">
                <c:forEach items="${accounts}" var="account">
                    <form:option value="${account.id}" label="${account.firstName} ${account.lastName} : ${account.email}"/>
                </c:forEach>
            </form:select>
        </div>
        <div class="templateContainer">
            <h2>Template</h2>
            <div class="templateElement">
                <form:select cssClass="custom-select" itemValue="template" path="template.id">
                    <c:forEach items="${templates}" var="template">
                        <form:option value="${template.id}" label="${template.title} – ${template.subject}"/>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="campaignReceiversContainer">
            <div class="campaignBottomElement">
                <label>Receivers: </label>
                <input name="file" type="file"/>
            </div>
            <div class="campaignBottomElement">
                <div class="form-group">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Delay, s</span>
                        </div>
                        <form:input cssClass="form-control" path="delay"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="campaignControlPanel">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </div>
</form:form>

</body>
</html>