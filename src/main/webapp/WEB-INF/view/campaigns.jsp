<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Campaigns</title>
    <style>
        DIV.table
        {
            display:table; width: 100%;;
        }

        FORM.tr, DIV.tr
        {
            display:table-row;
            padding: 10px;
            margin: 10px;
        }
        SPAN.td
        {
            display:table-cell;
            padding: 10px;
            margin: 10px;
        }
        SPAN.th
        {
            display:table-cell;
            font-weight: bold;
        }
    </style>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<div class="table">
    <div class="tr">
        <span class="th">Title</span>
        <span class="th">Delay</span>
        <span class="th">Size</span>
        <span class="th">Sent</span>
        <span class="th">Rejected</span>
    </div>
    <c:forEach items="${campaigns}" var="campaign">
        <form:form class="tr" modelAttribute="campaignAttribute" method="post">
            <span class="td"><c:out value="${campaign.title}"/></span>
            <span class="td"><c:out value="${campaign.delay}"/></span>
            <span class="td"><c:out value="${campaign.peopleList.people.size()}"/></span>
            <span class="td"><c:out value="${campaign.emailStatistics.sentEmailsCount}"/></span>
            <span class="td"><c:out value="${campaign.emailStatistics.rejectedEmailsCount}"/></span>
        </form:form>
    </c:forEach>
</div>

</body>
</html>