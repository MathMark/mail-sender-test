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
        /*#progress-bar{
            width: 10%;
            display: inline-block;
            vertical-align: middle;
        }
        #controlButtons{
            width: 10%;
        }*/
        .noCampaignsContainer{
            text-align: center;
            border-style: solid;
            border-width: 2px;
            border-color: #e1e1e1;
            border-radius: 20px;
            color: #595a59;
            padding: 20px;
        }
        #frownEmoji{
            text-align: center;
            margin: 20px;
            color: #e1e1e1;
        }
        #campaign-status-new{
            background-color: blueviolet;
        }
        #campaign-status-running{
            background-color: cornflowerblue;
        }
        #campaign-status-stopped{
            background-color: #595a59;
        }
        #campaign-status-finished{
            background-color: forestgreen;
        }#campaign-status-failed{
             background-color: darkred;
         }
    </style>

</head>
<body>

<div class="mainCampaignContainer">
    <c:if test="${campaigns.size() == 0}">
        <div class="noCampaignsContainer">
            <h2>Ooops, you haven't created any campaigns yet...</h2>
            <div id="frownEmoji"><svg width="5em" height="5em" viewBox="0 0 16 16" class="bi bi-emoji-frown" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                <path fill-rule="evenodd" d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.498 3.498 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.498 4.498 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683z"/>
                <path d="M7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
            </svg></div>
            <div class="campaignControlPanel">
                <button type="button" class="btn btn-primary" onclick="location.href='campaigns/addCampaign'">Create campaign</button>
            </div>
        </div>
    </c:if>
    <c:if test="${campaigns.size() != 0}">
        <c:forEach items="${campaigns}" var="campaign">
            <form:form class="tr" modelAttribute="campaignAttribute">
                <div class="campaignContainer">
                    <div class="card text-white bg-dark mb-3">
                        <div class="card-header"><c:out value="${campaign.title}"/>
                            <c:if test="${campaign.emailStatistics.campaignStatus.title == 'New'}">
                                <span id="campaign-status-new" class="badge badge-primary"><c:out value="${campaign.emailStatistics.campaignStatus.title}"/></span>
                            </c:if>
                            <c:if test="${campaign.emailStatistics.campaignStatus.title == 'Running'}">
                                <span id="campaign-status-running" class="badge badge-primary"><c:out value="${campaign.emailStatistics.campaignStatus.title}"/></span>
                            </c:if>
                            <c:if test="${campaign.emailStatistics.campaignStatus.title == 'Stopped'}">
                                <span id="campaign-status-stopped" class="badge badge-primary"><c:out value="${campaign.emailStatistics.campaignStatus.title}"/></span>
                            </c:if>
                            <c:if test="${campaign.emailStatistics.campaignStatus.title == 'Finished'}">
                                <span id="campaign-status-finished" class="badge badge-primary"><c:out value="${campaign.emailStatistics.campaignStatus.title}"/></span>
                            </c:if>
                            <c:if test="${campaign.emailStatistics.campaignStatus.title == 'Failed'}">
                                <span id="campaign-status-failed" class="badge badge-primary"><c:out value="${campaign.emailStatistics.campaignStatus.title}"/></span>
                            </c:if>
                        <div class="card-body">
                            <h5 class="card-title"></h5>
                            <div class="campaignElement">
                                <div class="campaignElement">
                                    <c:if test="${campaign.emailStatistics.campaignStatus.title == 'New' || campaign.emailStatistics.campaignStatus.title == 'Stopped'}">
                                        <button type="button" class="btn btn-light" onclick="location.href='campaigns/run/${campaign.id}'">Run</button>
                                    </c:if>
                                    <c:if test="${campaign.emailStatistics.campaignStatus.title == 'Running'}">
                                        <button type="button" class="btn btn-light" onclick="location.href='campaigns/stop/${campaign.id}'">Stop</button>
                                    </c:if>
                                </div>
                                <div class="campaignTitleElement">
                                    <button type="button" class="btn btn-light" onclick="location.href='campaigns/view/${campaign.id}'">View</button>
                                </div>
                            </div>
                            <div class="campaignElement">
                                <p>Progress</p>
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" style="width: ${campaign.emailStatistics.progress}%" aria-valuenow="${campaign.emailStatistics.progress}" aria-valuemin="0" aria-valuemax="100"><c:out value="${campaign.emailStatistics.progress}"/>%</div>
                                </div>
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
                                        <c:out value="${campaign.people.size()}"/>
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
        </div>
    </c:if>
</div>
</body>
</html>