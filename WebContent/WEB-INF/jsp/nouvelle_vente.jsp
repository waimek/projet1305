<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="entete.jsp"%>




<form
	action="${pageContext.servletContext.contextPath}/GestionVenteServlet"
	method="post">

	<div class="form-group">
		<label for="article" id="lblArticle">Article :</label> <input
			type="text" id="article" name="article" class="form-control" />
	</div>
	<div class="form-group">
		<label for="description" id="lblDescription">Description :</label>
		<textarea name="description" id="description" class="form-control"></textarea>
	</div>
	<div class="form-group">
		<label for="categorie" id="lblCategorie">Catégorie :</label> <select
			class="custom-select mr-sm-2">
			<c:forEach var="categorie" items="${requestScope.listeCategories}">
				<option value="${categorie.id}">${categorie.libelle}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label class="form-label" for="miseAPrix" id="lblMiseAPrix">Mise à prix :</label> <input
			type="text" id="miseAPrix" name="miseAPrix" />
	</div>
	<div class="form-group">
		<label class="form-label" for="dateFinEncheres" id="lblDateFinEncheres">Fin de
			l'enchère :</label> <input type="text" id="dateFinEncheres"
			name="dateFinEncheres" />
	</div>
	<div class="bg-info">
		<div class="form-group">
			<label class="form-label" for="rue" id="lblRue">Rue :</label> <input type="text"
				id="rue" name="rue" />
		</div>
		<div class="form-group">
			<label class="form-label" for="codePostal" id="lblCodePostal">Code postal :</label> <input
				type="text" id="codePostal" name="codePostal" />
		</div>
		<div class="form-group">
			<label class="form-label" for="ville" id="lblVille">Code postal :</label> <input
				type="text" id="ville" name="ville" />
		</div>
	</div>
	<fieldset>
		<div class="row">
		<legend class="form-label">Retrait</legend>
			<input type="submit" class="btn btn-primary" value="Publier" /> 
			<a class="btn btn-primary"
				href="${pageContext.servletContext.contextPath}/RechercheServlet"
				role="button">Annuler</a>
			<a class="btn btn-primary"
				href="#"
				role="button">Enregistrer</a>
		</div>
	</fieldset>
</form>

<%@ include file="footer.jsp"%>