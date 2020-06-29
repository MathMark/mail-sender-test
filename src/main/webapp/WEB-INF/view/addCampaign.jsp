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
        .campaignReceiversContainer{
            margin-top: 15px;
        }

        .accountContainer > label {
            position: relative;
            top: -15px;
            left: 20px;
            background-color: white;
            font-size: medium;
        }

        .accountFirstAndLastName{
            width: 100%;
            display: inline-block;
        }
        .accountCredentials {
            width: 100%;
        }

        .accountCredential {
            display: inline-block;
            width: 49%;
        }
        .accountNameElement{
            display: inline-block;
            width: 49%;
            margin-bottom: 10px;
        }

        .templateElement {
            margin-bottom: 5px;
        }
        .campaignBottomElement{
            display: inline-block;
        }
        h1{
            text-align: center;
            font-size: medium;
            color: #595a59;
        }
        h2{
            text-align: center;
            font-size: small;
            color: #595a59;
        }
    </style>
</head>
<body>
<form:form modelAttribute="campaignAttribute" action="saveCampaign" method="post">
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
            <div class="accountFirstAndLastName">
                <div class="accountNameElement">
                    <div class="input-group">
                        <form:input cssClass="form-control" path="accountFirstName" placeHolder="First name"/>
                    </div>
                </div>
                <div class="accountNameElement">
                    <div class="input-group">
                        <form:input cssClass="form-control" path="accountLastName" placeHolder="Last name"/>
                    </div>
                </div>
            </div>
            <div class="accountCredentials">
                <div class="accountCredential">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Username</span>
                        </div>
                        <form:input cssClass="form-control" path="accountUsername" placeHolder="username@gmail.com"/>
                    </div>
                </div>
                <div class="accountCredential">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Password</span>
                        </div>
                        <form:input type="password" cssClass="form-control" path="accountPassword"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="templateContainer">
            <h2>Template</h2>
            <div class="templateElement">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">Subject</span>
                    </div>
                    <form:input cssClass="form-control" path="templateSubject"/>
                </div>
            </div>
            <div class="templateElement">
                <form:textarea path="templateBody" cssClass="form-control" placeholder="Email body"/>
            </div>
            <div class="templateElement">
                <div class="templateElement">
                    <form:textarea path="templateSignature" cssClass="form-control" placeholder="Signature"/>
                </div>
            </div>
        </div>
        <div class="campaignReceiversContainer">
            <div class="campaignBottomElement">
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