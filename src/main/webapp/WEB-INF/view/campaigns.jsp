<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="header.jsp" />
    <script src="https://kit.fontawesome.com/d193344e06.js" crossorigin="anonymous"></script>
    <style>
        .mainCampaignContainer{
            width: 100%;
            padding: 20px;
            background-color: #ffffff;
        }
        .campaignTitleElement{
            display: inline-block;
        }
        .campaignElement{
            width: 24%;
            display: inline-block;
            text-align: center;
            vertical-align: middle;
        }
        .statisticLabel{
            width: 20%;
            display: inline-block;
            text-align: center;
        }
        .statisticValue{
            width: 20%;
            display: inline-block;
            text-align: center;
        }
        .campaignControlPanel{
            right: 10px;
        }
    </style>

</head>
<body>

<div class="mainCampaignContainer">
    <c:forEach items="${campaigns}" var="campaign">
        <form:form class="tr" modelAttribute="campaignAttribute">
            <div class="campaignContainer">
                <div class="card text-white bg-dark mb-3">
                    <div class="card-header"><c:out value="${campaign.title}"/></div>
                    <div class="card-body">
                        <h5 class="card-title"></h5>
                        <div class="campaignElement">
                            <div class="campaignTitleElement">
                                <button type="button" class="btn btn-light" onclick="location.href='campaigns/run/${campaign.id}'">Run</button>
                            </div>
                            <div class="campaignTitleElement">
                                <button type="button" class="btn btn-light" onclick="location.href='campaigns/view/${campaign.id}'">View</button>
                            </div>
                        </div>
                        <div class="campaignElement">
                            <c:out value="${campaign.emailStatistics.campaignStatus}"/>
                        </div>
                        <div class="campaignElement">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-bounding-box" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" d="M1.5 1a.5.5 0 0 0-.5.5v3a.5.5 0 0 1-1 0v-3A1.5 1.5 0 0 1 1.5 0h3a.5.5 0 0 1 0 1h-3zM11 .5a.5.5 0 0 1 .5-.5h3A1.5 1.5 0 0 1 16 1.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 1-.5-.5zM.5 11a.5.5 0 0 1 .5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 1 0 1h-3A1.5 1.5 0 0 1 0 14.5v-3a.5.5 0 0 1 .5-.5zm15 0a.5.5 0 0 1 .5.5v3a1.5 1.5 0 0 1-1.5 1.5h-3a.5.5 0 0 1 0-1h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 1 .5-.5z"/>
                                <path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                            </svg>
                            <div class="senderName">
                                <c:out value="${campaign.account.firstName} ${campaign.account.lastName}"/>
                            </div>
                            <div class="senderEmail">
                                <c:out value="${campaign.account.email}"/>
                            </div>
                        </div>
                        <div class="campaignElement">
                            <div class="labels">
                                <div class="statisticLabel">
                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-people-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M7 14s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1H7zm4-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5.784 6A2.238 2.238 0 0 1 5 13c0-1.355.68-2.75 1.936-3.72A6.325 6.325 0 0 0 5 9c-4 0-5 3-5 4s1 1 1 1h4.216zM4.5 8a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"></path>
                                    </svg>
                                    <p>All</p>
                                </div>
                                <div class="statisticLabel">
                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-envelope" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2zm13 2.383l-4.758 2.855L15 11.114v-5.73zm-.034 6.878L9.271 8.82 8 9.583 6.728 8.82l-5.694 3.44A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.739zM1 11.114l4.758-2.876L1 5.383v5.73z"></path>
                                    </svg>
                                    <p>Sent</p>
                                </div>
                                <div class="statisticLabel">
                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-emoji-dizzy" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                        <path fill-rule="evenodd" d="M9.146 5.146a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708.708l-.647.646.647.646a.5.5 0 0 1-.708.708l-.646-.647-.646.647a.5.5 0 1 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 0-.708zm-5 0a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 1 1 .708.708l-.647.646.647.646a.5.5 0 1 1-.708.708L5.5 7.207l-.646.647a.5.5 0 1 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 0-.708z"/>
                                        <path d="M10 11a2 2 0 1 1-4 0 2 2 0 0 1 4 0z"/>
                                    </svg>
                                    <p>Rejected</p>
                                </div>
                            </div>
                            <div class="statisticValues">
                                <div class="statisticValue">
                                    <c:out value="${campaign.peopleList.people.size()}"/>
                                </div>
                                <div class="statisticValue">
                                    <c:out value="${campaign.emailStatistics.sentEmailsCount}"/>
                                </div>
                                <div class="statisticValue">
                                    <c:out value="${campaign.emailStatistics.rejectedEmailsCount}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </c:forEach>
    <div class="campaignControlPanel">
        <button type="button" class="btn btn-primary" onclick="location.href='campaigns/addCampaign'">Create campaign</button>
        <button type="button" class="btn btn-primary" onclick="location.href='campaigns/addCampaign'">Create from existing elements</button>
        <button type="button" class="btn btn-primary" onclick="location.href='campaigns/addCampaign'">
            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-file-earmark-ruled" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M5 9H3V8h10v1H6v2h7v1H6v2H5v-2H3v-1h2V9z"/>
                <path d="M4 1h5v1H4a1 1 0 0 0-1 1v10a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V6h1v7a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2z"/>
                <path d="M9 4.5V1l5 5h-3.5A1.5 1.5 0 0 1 9 4.5z"/>
            </svg> Create from .csv</button>
    </div>
</div>
</body>
</html>