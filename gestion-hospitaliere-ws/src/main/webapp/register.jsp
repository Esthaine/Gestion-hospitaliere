<%@ page import="com.gestion.hospitaliere.dao.*"%>
<%@ page import="com.gestion.hospitaliere.dao.impl.*"%>
<%@ page import="com.gestion.hospitaliere.entity.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link href="<%= request.getContextPath() %>/styles/hospital/register.css" rel="stylesheet" />
    <title>Enregistrement</title>
</head>
<body>
    <div class="content">
        <div class="left">
            <div class="left-content-cover">
                <a href="<%= request.getContextPath() %>/authentication?action=patients">
                    <i class="lni lni-arrow-left"></i>
                </a>
                <div class="message">
                    <h1>Message to Patient</h1>
                    <p>Small Description</p>
                </div>

            </div>

        </div>
        <div class="right">
            <div class="login-container">
                <h2>S'enregistrer en tant que <span>Patient</span></h2>
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null){ %>
                        <span class="error"> <%=error%></span>;
                <%  } %>
                <form action="<%=request.getContextPath()%>/enregistrement" method="POST">
                    <div class="form-group">
                        <label for="username">Nom d'utilisateur * : </label>
                        <input type="text" name="username"  class="input-login"/>
                    </div>

                    <div class="form-compose">
                        <div class="form-compose-item">
                            <label for="firtname">Prenom * :</label>
                            <input name="firstname" class="input-login"/>
                        </div>
                        <div class="form-compose-item">
                            <label for="lastname">Nom * :</label>
                            <input name="lastname" class="input-login"/>
                        </div>
                    </div>

                    <div class="form-group mot-de-passe">
                        <label for="email">Email * : </label>
                        <input type="text" name="email"  class="input-login"/>
                    </div>

                    <div class="form-group mot-de-passe">
                        <label for="password">Mot de passe * :</label>
                        <input type="password" name="password" class="input-login"/>
                    </div>
                    <div class="form-group">
                        <label for="confirm-password">Confirmer Mot de passe * :</label>
                        <input type="password" name="confirm-password" class="input-login"/>
                    </div>

                    <div class="form-compose">
                        <div class="form-compose-item">
                            <label>Pays * :</label>
                            <select name="country" class="input-login">
                            <%
                                PaysDao paysRepository = new PaysDaoImpl((Class<Pays>) Class.forName("com.gestion.hospitaliere.entity.Pays"));
                                List<Pays> pays = paysRepository.findAll();
                                for(int i = 0; i < pays.size(); i++){
                            %>
                                <option value="<%= pays.get(i).getId() %>"><%= pays.get(i).getNom() %></option>
                            <%
                                }
                            %>
                            </select>
                        </div>
                        <div class="form-compose-item">
                            <label>Ville * :</label>
                            <select name="town" class="input-login">
                            <%
                                VilleDao villeRepository = new VilleDaoImpl((Class<Ville>) Class.forName("com.gestion.hospitaliere.entity.Ville"));
                                List<Ville> villes = villeRepository.findAll();

                                for(int i = 0; i < villes.size(); i++){%>
                                <option value="<%= villes.get(i).getId() %>"><%= villes.get(i).getNom()%></option>

                            <%
                              }
                            %>
                            </select>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-login">S'enregistrer</button>
                </form>
            </div>
        </div>
    </div>
    <script src="login.js"></script>
</body>
</html>