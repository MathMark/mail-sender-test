<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <style>
        .card-body {
            text-align: left;
            width: fit-content;
            display: inline-block;
        }

        .card-template-account {
            margin: auto;
            padding: 10px;
        }

        .template-card {
            display: inline-block;
            margin-right: 10px;
        }

        .account-card {
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="campaignContainer">
    <div class="card text-center">
        <div class="card-header">
            <div class="campaignTitleComponent">
                <c:out value="${campaign.title}"/>
            </div>
        </div>
        <div class="card-body">
            <div class="card-template-account">
                <div class="template-card">
                    <div class="card text-white bg-dark mb-3">
                        <div class="card-header">Template</div>
                        <div class="card-body">
                            <h5 class="card-title"><c:out value="${campaign.template.subject}"/></h5>
                            <p class="card-text"><c:out value="${campaign.template.body}"/></p>
                            <a href="#" class="btn btn-primary">Go to template</a>
                        </div>
                    </div>
                </div>
                <div class="account-card">
                    <div class="card text-white bg-dark mb-3">
                        <div class="card-header"><svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M13 14s1 0 1-1-1-4-6-4-6 3-6 4 1 1 1 1h10zm-9.995-.944v-.002.002zM3.022 13h9.956a.274.274 0 0 0 .014-.002l.008-.002c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664a1.05 1.05 0 0 0 .022.004zm9.974.056v-.002.002zM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                        </svg> Account</div>
                        <div class="card-body">
                            <h5 class="card-title"><c:out
                                    value="${campaign.account.firstName} ${campaign.account.lastName}"/></h5>
                            <p class="card-text"><c:out value="${campaign.account.email}"/></p>
                            <a href="#" class="btn btn-primary">Go to account</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="card-footer text-muted">
        <div class="campaignFooterComponent">
            <%int rowNum = 1;%>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Email</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${campaign.people}" var="person">
                    <tr>
                        <th scope="row"><%=rowNum%>
                        </th>
                        <td><c:out value="${person.email}"/></td>
                        <td><c:out value="${person.firstName}"/></td>
                        <td><c:out value="${person.lastName}"/></td>
                        <td><c:out value="${person.emailStatus.name()}"/></td>
                    </tr>
                    <%rowNum++;%>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>
</div>
</body>
</html>