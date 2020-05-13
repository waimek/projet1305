<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Se Connecter</title>
</head>
<body>
<h1>Trocencheres.org</h1>
<!-- Ceci est un commentaire de type test -->
<form method="post" action="<%=request.getContextPath()%>/connexion">
            <fieldset>
                <legend>Connexion</legend>
                <label for="pseudo">S'identifier<span class="required">*</span></label>
                <input type="pseudo" id="pseudo" name="pseudo" value="<c:out value="${util.pseudo}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['pseudo']}</span>
                <br />

                <label for="mdp">Mot de passe <span class="required">*</span></label>
                <input type="password" id="mdp" name="mdp" value="<c:out value="${util.mdp}"/>" size="20" maxlength="20" />
                <%-- <span class="erreur">${form.erreurs['mdp']}</span> --%>
                <br />

                <input type="submit" value="Connexion" class="sansLabel" />
                <br />
                
              <%--   <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p> --%>
            </fieldset>
        </form>
</body>
</html>