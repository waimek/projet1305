<!-- Author Lucille -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="fr.eni.ecole.troc_encheres.bo.Utilisateur"%>
<%@ page import="fr.eni.ecole.troc_encheres.form.ConnexionForm" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<%@ include file="head_css.jsp"%>
</head>
<body>
	<!-- Auteur : Lucille/Matthieu -->
	<div class="container">
		<div class="row my-3">
			<div class="col">
				<h1>Trocencheres.org</h1>
			</div>
		</div>
		
		<!-- Ceci est un commentaire de type test -->
		<div class="row my-3 mb-5">
			<div class="col">
			
				<form action="${pageContext.servletContext.contextPath}/connexion"
					method="post">
					<div class="row form-group">
						<label class="col-3" for="nom">Pseudo : </label> 
						<input required=" " class="col" type="text" id="identifiant" name="identifiant" value="<c:out value="${variableIdentifiant.equals(pseudo) ? util.pseudo : util.email}"/>" required />
						<%-- 		<input type="text" id="identifiant" name="identifiant"
							value="<c:out value="${util.pseudo}"/>" /> --%>
					</div>
					<c:if test="${!empty form.erreurs['pseudo']}">
						<div class="row alert alert-danger " role="alert">
							<span class="erreur text-danger">${form.erreurs['pseudo']}</span>
						</div>
					</c:if>
					
					<div class="row form-group">
						<label class="col-3" for="password">Mot de passe</label> 
						<input required=" " class="col form-control" type="password" name="textMdp"  id="textMdp" value="<c:out value="${util.mdp}" />" required/>
						
						<%-- 		<c:out value="${num % 2 eq 0 ? 'even': 'odd'}" /> --%>
					</div>
					<c:if test="${!empty form.erreurs['mdp']}">
						<div class="row alert alert-danger" role="alert">
							<span class="erreur text-danger">${form.erreurs['mdp']}</span>
						</div>
					</c:if>
					
					<div class="row">
						<input  class="col sansLabel mr-4 btn btn-primary" type="submit" value="Connexion" /> <br />
						<div class="col form-check ml-4">
							<input class="form-check-input" type="checkbox" value=""
								id="defaultCheck1"> <label class="form-check-label"
								for="defaultCheck1"> Se souvenir de moi </label> </br> <a href="#">Mot
								de passe oublié</a>
						</div>
					</div>
				
					<p class="${empty form.erreurs ? 'succes' : 'erreur'} text-danger" >${form.resultat}</p>
				</form>
				<c:choose>
					<c:when test="${!empty sessionScope.sessionUtilisateur}">
						<p class="succes">Vous êtes connecté(e) avec le pseudo :
							${sessionScope.sessionUtilisateur.pseudo}</p>
					</c:when>
				</c:choose>
			</div>
		</div>
		
		<div class="row mt-5">
			<div class="col my-5">
				<a class="btn btn-primary d-block py-4" href="${pageContext.servletContext.contextPath}/nouveauCompte" title="Créer un compte" >Créer un compte</a>
			</div>
		</div>
	</div>


	<%@ include file="footer.jsp"%>

</body>
</html>
