<%@ taglib  uri="jakarta.tags.core" prefix="c" %>
<%@ taglib  uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="MyValue" value="12" />
<html>
<head>
    <jsp:include page="components/externals.jsp" />
    <title>Recherche</title>
</head>
<body>
    <jsp:include page="components/header.jsp"/>
    <div class="main">
        <c:out value="Welcome to Java"/>
        <c:out value="${MyValue}" />
    </div>
    <jsp:include page="components/footer.jsp"/>
</body>
</html>
