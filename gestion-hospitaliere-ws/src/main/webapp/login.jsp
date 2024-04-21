<%@ page import="java.lang.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/styles/hospital/login.css" rel="stylesheet" />
    <title>Document</title>
</head>
<body>
    <div class="content">
        <%
            String patients = String.valueOf((request.getParameter("action") != null && request.getParameter("action").equals("patients") )? "active": "");
            String organization = String.valueOf((request.getParameter("action") != null && request.getParameter("action").equals("organization") ? "active": ""));
            String admin = String.valueOf(( request.getParameter("action") != null && request.getParameter("action").equals("superAdmin") ? "active": ""));
        %>
        <div class="left">
            <ul class="type-user-login">
                <li class="type-user <%=patients%>">
                    <a href="<%= request.getContextPath() %>/authentication?action=patients">
                        <div class="user">
                            <i class="lni lni-customer icone-principal"></i>
                            <div class="description">
                                <h2>Patient</h2>
                                <p>Se connecter pour un rendez-vous medicale et voir l'historique des rendez-vous.</p>
                            </div>
                            <i class="lni lni-arrow-right icone-arrow"></i>
                        </div>
                    </a>
                </li>

                <li class="type-user <%=organization%>">
                    <a href="<%= request.getContextPath() %>/authentication?action=organization">
                        <div class="user">
                            <i class="lni lni-ambulance icone-principal"></i>
                            <div class="description">
                                <h2>Corps Medical</h2>
                                <p>Se connecter comme fournisseur de service medicale</p>
                            </div>
                            <i class="lni lni-arrow-right icone-arrow"></i>
                        </div>
                    </a>
                </li>
                <li class="type-user <%=admin%>">
                    <a href="<%= request.getContextPath() %>/authentication?action=superAdmin">
                        <div class="user">
                            <i class="lni lni-user icone-principal"></i>
                            <div class="description">
                                <h2>Super Admin</h2>
                                <p>Se connecter en tant que Administrateur du systeme.</p>
                            </div>
                            <i class="lni lni-arrow-right icone-arrow"></i>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        <div class="right">
            <div class="login-container">
                <h2>
                    Connectez-vous en tant que
                    <% if (request.getParameter("action") != null &&request.getParameter("action").equals("patients")) { %>
                        <span>Patient</span>
                    <%}%>

                    <% if (request.getParameter("action") != null && request.getParameter("action").equals("organization")) { %>
                        <span>Organisation</span>
                    <%}%>

                    <% if (request.getParameter("action") != null && request.getParameter("action").equals("superAdmin")) { %>
                        <span>Super Admin</span>
                    <%}%>

                </h2>
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null){ %>
                <span class="error"> <%=error%></span>;
                <%  } %>

                <% if (request.getParameter("action") != null && request.getParameter("action").equals("patients")) { %>
                   <form action="<%= request.getContextPath() %>/authentication?role=patients" method="POST" >
                <%}%>

                <% if (request.getParameter("action") != null && request.getParameter("action").equals("organization")) { %>
                    <form action="<%= request.getContextPath() %>/authentication?role=organization" method="POST">
                <%}%>

                <% if (request.getParameter("action") != null && request.getParameter("action").equals("superAdmin")) { %>
                    <form action="<%= request.getContextPath() %>/authentication?role=superAdmin" method="POST">
                <%}%>
                    <div class="form-group">
                        <label for="username">Nom ou Employee ID</label>
                        <input type="text" name="username"  class="input-login" placeholder="Nom ou Numero Employee"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Mot de passe</label>
                        <input type="password" name="password" class="input-login" placeholder="Mot de passe"/>
                    </div>

                    <button type="submit" class="btn btn-login">Connexion</button>
                </form>

                <% if (request.getParameter("action") != null && request.getParameter("action").equals("patients")) { %>
                       <div class="new-account">
                           <p>Créer un nouveau compte</p>
                           <a href="<%= request.getContextPath()%>/enregistrement">S'inscrire</a>
                       </div>
                <%}%>

            </div>
        </div>
    </div>
    <script src="<%= request.getContextPath() %>/js/login.js"></script>
</body>
</html>