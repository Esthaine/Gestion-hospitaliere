<%@ page import="com.gestion.hospitaliere.model.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <nav class="nav">
        <div class="nav_left">
            <a href="<%= request.getContextPath() %>">
                <img src="<%= request.getContextPath() %>/images/Logo_MamanYemo.png" class="logo">
            </a>
            <span id="open__menu"><i class="lni lni-menu"></i>Menu</span>
        </div>
        <div class="nav_right">
            <ul>
                <li>Covid</li>
                <li id="open__search">
                    <i class="lni lni-search-alt"></i>
                    Recherche
                </li>
                <% UserDto userDto = (UserDto) session.getAttribute("authenticated");
                    if (userDto!=null){%>
                    <li><a href="<%= request.getContextPath() %>/deconnexion">Deconnexion</a> </li>
                <%}%>
            </ul>
        </div>
    </nav>
    <div class="menu" id="menu">
        <i class="lni lni-close" id="close__menu"></i>
        <div class="menu_content">
            <div class="menu_content_left">
                <ul>
                    <li><a href="<%= request.getContextPath() %>/about"> A propos</a> </li>
                    <li><a href="<%= request.getContextPath() %>/docteurs">Docteurs</a> </li>
                    <li><a href="<%= request.getContextPath() %>/appointement">Prendre rendez-vous</a> </li>
                </ul>
            </div>
            <div class="menu_content_right">
                <ul>
                    <li>Urgence</li>
                    <li>Hospitalisation</li>
                    <li>Menu 9</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="search" id="search">
        <i class="lni lni-close" id="close__search"></i>
        <form>
            <label for="search">Recherche</label>
            <div class="form__group">
                <input type="search" placeholder="Recherche par mot cle" name="search" >
                <button type="submit" class="button button-primary">Recherche</button>
            </div>
        </form>
    </div>
</div>

