<jsp:include page="components/topbar.jsp" />

<div class="main">
  <jsp:include page="components/sidebar.jsp" />
  <div class="content">
    <form class="form-fiche">
      <div class="antecedants">
        <h2>Liste antecedants medicales</h2>
        <div class="antecedants-items-list">
          <div class="antecedants-item">
            <h4>Allergies</h4>
            <p>Description</p>
          </div>
          <div class="antecedants-item">
            <h4>Allergies</h4>
            <p>Description</p>
          </div>
          <div class="antecedants-item">
            <h4>Allergies</h4>
            <p>Description</p>
          </div>
          <div class="antecedants-item">
            <h4>Allergies</h4>
            <p>Description</p>
          </div>
        </div>

        <div class="antecedants-items-form">
          <div class="antecedants-item-form">
            <div class="form-table-complet-h">
              <div class="form-table">
                <label>Date debut:</label>
                <input type="date" name="date_debut">
              </div>

              <div class="form-table">
                <label>Date fin:</label>
                <input type="date" name="date_fin">
              </div>
            </div>
            <div class="form-table-complet">
              <label>Type:</label>
              <input type="text" name="type">
            </div>
            <div class="form-table-complet">
              <label>Description:</label>
              <textarea name="description"></textarea>
            </div>
          </div>
          <a class="action-button action-add" id="add-antecedant">
            <i class="fa fa-plus" aria-hidden="true"></i>
          </a>
        </div>
      </div>
      <div class="demand__examens">
        <h2>Examens Labo</h2>
        <div class="analyse">
          <div class="questions">
            <div class="question-item">
              <div class="question">
                <div class="questions-items">
                  <h3>Demande:</h3>
                  <label>Examen:</label>
                  <input type="text" name="question">
                  <label>Appreciation</label>
                  <input type="text">
                </div>
                <div class="reponse-items">
                  <h3>Reponse:</h3>
                  <label>Resultat:</label>
                  <input type="text" name="reponse">
                  <label>Appreciation</label>
                  <input type="text" name="appreciation_reponse">
                </div>
              </div>
            </div>
            <a class="action-button action-add" id="add-examen">
              <i class="fa fa-plus" aria-hidden="true"></i>
            </a>
          </div>

        </div>
      </div>
      <div class="subscripiton_medical">
        <h2>Subscription Medical</h2>
        <div class="medicaments">
          <div class="medicament-item-container">
            <div class="medicament-item">
              <label>Nom Medicaments</label>
              <input type="text" name="">
            </div>
          </div>
        </div>
        <a class="action-button action-add" id="add-medicament">
          <i class="fa fa-plus" aria-hidden="true"></i>
        </a>
      </div>
      <button type="submit" class="btn-submit">Soumettre le details</button>
    </form>
  </div>
</div>
<script src="<%= request.getContextPath()%>/js/admin.js"></script>
