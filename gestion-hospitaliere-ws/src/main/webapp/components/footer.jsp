<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<footer class="main_footer">
    <div class="content_footer">
      <div class="content_links">
        <div class="section_container">
          <h3>Patient</h3>
          <a href="#">Urgence</a>
          <a href="#">Hospitalisation</a>
        </div>
        <div class="section_container">
          <h3>Départements</h3>
          <a href="#" class="section">Maladies et affections</a>
          <a href="#" class="section">Patients internationaux</a>
          <a href="#" class="section">Rechercher des essais cliniques</a>
        </div>

        <div class="section_container">
          <h3>Connexion</h3>
          <a href="<%= request.getContextPath()%>/authentication?action=patients" class="section">Patients</a>
          <a href="<%= request.getContextPath()%>/authentication?action=organzation" class="section">Corps Medical</a>
          <a href="<%= request.getContextPath()%>/authentication?action=superAdmin" class="section">Admin</a>
        </div>

        <!-- <div class="section_container">
          <h3>Ressources par profession</h3>
          <a href="#" class="section">Référez un patient</a>
          <a href="#" class="section">Transférez un patient</a>
          <a href="#" class="section">Connexion clinique</a>
        </div> -->

        <div class="section_container">
          <h3>Trouver des sujets de recherche</h3>
          <a href="#" class="section">Docteurs</a>
          <a href="#" class="section">Departements</a>
        </div>
      </div>
      <div class="reseaux_sociaux">
        <div class="social_links">
          <a href="#" class="section">Maman Yemo Sendwe Hopital</a> |
          <a href="#" class="section">Assistance linguistique disponible</a> |
          <a href="#" class="section">Contact et informations sur la confidentialité</a> |
          <a href="https://www.facebook.com/" target="_blank" class="social-icon facebook">Facebook</a> |
          <a href="https://x.com/" target="_blank" class="social-icon x">X</a> |
          <a href="https://linkedln.com/" target="_blank" class="social-icon linkedln">Linkedln</a>
        </div>
        <hr>
        <div class="copyright">
          <p>&copy; 2024 CHN. Tous droits réservés.</p>
        </div>
      </div>
    </div>
    </footer>
<script src="js/main.js"></script>
