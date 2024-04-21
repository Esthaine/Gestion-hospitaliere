<html>
<head>
    <jsp:include page="components/externals.jsp" />
    <title>Gestion Hospitaliere</title>
</head>
<body>
    <jsp:include page="components/header.jsp"/>
    <div class="main">
        <div class="banner">
            <div class="wrapper">
                <div class="info">
                    <h2>Bienvenue a Maman Yemo Sendwe Hospital</h2>
                    <p>Caring is ou mission</p>
                </div>
                <a class="btn btn-start" href="<%= request.getContextPath()%>/authentication?action=patients">
                    Commencez ici
                </a>
            </div>
        </div>
        <div class="liens-service">
            <div class="lien-service">
                <i class="lni lni-map"></i>
                <a href="<%=request.getContextPath()%>/docteurs">Docteurs</a>
            </div>
            <div class="lien-service">
                <i class="lni lni-map"></i>
                <a href="<%=request.getContextPath()%>/localisation">Localisations</a>
            </div>
            <div class="lien-service">
                <i class="lni lni-map"></i>
                <p>Rendez-vous Medical</p>
            </div>
            <div class="lien-service">
                <i class="lni lni-map"></i>
                <p>Historiques Rendez-vous</p>
            </div>
        </div>
    </div>
    <jsp:include page="components/footer.jsp"/>
</body>
</html>
