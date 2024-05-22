
<jsp:include page="components/topbar.jsp" />

<div class="main">
  <jsp:include page="components/sidebar.jsp" />
  <div class="content">
    <h1>Premier Soin</h1>

    <form action="<%= request.getContextPath()%>" method="post">
      <div>
        <h3>Information du patient</h3>
        <p>Nom Complet:
<%--          <%= person != null&& person.getGivenName() != null ? person.getGivenName() : "" %>--%>
<%--          <%= person != null&& person.getFirstName() != null ? person.getFirstName() : "" %>--%>
<%--          <%= person != null&& person.getLastName() != null ? person.getLastName() : "" %>--%>
        </p>
        <p>
<%--          Age: <%= person != null&& person.getDateOfBirth() != null ? person.getDateOfBirth():""%>--%>
        </p>
      </div>
      <div>
        <h3>Prelevement</h3>
        <table>
          <tr>
            <td><label>Temperature *:</label></td>
            <td><input type="number" name="temperature"></td>
          </tr>
          <tr>
            <td><label>Kilo *:</label></td>
            <td><input type="number" name="kilo"></td>
          </tr>
          <tr>
            <td><label>Tension *:</label></td>
            <td><input type="number" name="tension"></td>
          </tr>
          <tr>
            <td><label>Taille *:</label></td>
            <td><input type="number" name="taille"></td>
          </tr>
          <tr>
            <td><label>Respiration *:</label></td>
            <td><input type="number" name="respiration"></td>
          </tr>
        </table>
      </div>
      <button class="btn btn-blue" type="submit">Prelevez et envoyer pour consultation</button>
    </form>
  </div>
</div>