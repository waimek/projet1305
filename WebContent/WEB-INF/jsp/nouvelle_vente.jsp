<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
<%@ include file="head_css.jsp"%>
</head>
<body>
	<%@ include file="entete.jsp"%>
	<!-- Auteur : Edouard -->
	<div class="container">
		<h2 class ="text-center h2">Nouvelle vente</h2>
		<form
			action="${pageContext.servletContext.contextPath}/GestionVenteServlet"
			method="post">
		
			<div class="form-group">
				<label for="article" id="lblArticle">Article :</label> <input
					type="text" id="article" name="article" class="form-control" required/>
			</div>
			<div class="form-group">
				<label for="description" id="lblDescription">Description :</label>
				<textarea name="description" id="description" class="form-control" required></textarea>
			</div>
			<div class="form-group">
				<label for="categorie" id="lblCategorie">Catégorie :</label> <select
					class="custom-select mr-sm-2" name="categorie">
					<c:forEach var="categorie" items="${listeCategories}">
						<option value="${categorie.numero}">${categorie.libelle}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label class="form-label" for="miseAPrix" id="lblMiseAPrix">Mise à prix :</label> <input
					type="number" id="miseAPrix" name="miseAPrix"  class="form-control" required/>
			</div>
			<div class="form-group">
				<label class="form-label" for="dateFinEncheres" id="lblDateFinEncheres">Fin de
					l'enchère :</label> <input type="date" id="dateFinEncheres"
					name="dateFinEncheres"  class="form-control" required/>
			</div>
			<div class="bg-info">
			<legend class="form-label">Retrait</legend>
			
				<div class="form-group">
					<label class="form-label" for="rue" id="lblRue">Rue :</label> <input type="text"
						id="rue" name="rue"  class="form-control" required/>
				</div>
				<div class="form-group">
					<label class="form-label" for="codePostal" id="lblCodePostal">Code postal :</label> <input
						type="text" class="form-control" id="codePostal" name="codePostal" required/>
				</div>
				<div class="form-group">
					<label class="form-label" for="ville" id="lblVille">Ville :</label> <input
						type="text"  class="form-control" id="ville" name="ville" required/>
				</div>
			</div>
			<fieldset>
				<div class="row">
					<input type="hidden" name="action" value="nouvelleVente"/>
					<input type="submit" class="btn btn-primary" value="Publier" /> 
					<a class="btn btn-primary"
						href="${pageContext.servletContext.contextPath}/RechercheServlet"
						role="button">Annuler</a>
					<!--   <a class="btn btn-primary"
						href="${pageContext.servletContext.contextPath}/RechercheServlet"
						role="button">Enregistrer</a> -->
				</div>
			</fieldset>
		</form>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>