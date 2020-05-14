<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="fr.eni.ecole.troc_encheres.bo.Categorie"%>
<%@ include file="entete.jsp"%>



<form method="post" action="#">
	<div>
		<p>Filtres :</p>
		<input type="checkbox" name="mesVentes" id="mesVentes"
			class="form-check-input" /> <label for="mesVentes"
			class="form-check-label" id="lblVentes">Mes ventes</label><input
			type="checkbox" name="mesEncheresEnCours" id="mesEncheresEnCours"
			class="form-check-input" /> <label for="mesEncheresEnCours"
			id="lblEncheresEnCours" class="form-check-label">Mes enchères
			en cours</label> <input type="checkbox" name="mesAcquisitions"
			id="mesAcquisitions" class="form-check-input" /><label
			for="mesAcquisitions" id="lblAcquisitions" class="form-check-label">
			Mes acquisitions</label> <input type="checkbox" name="autresEncheres"
			id="autresEncheres" class="form-check-input" /><label
			for="autresEncheres" id="lblAutresEncheres" class="form-check-label">Autres
			enchères</label>
	</div>
	<i class="fas fa-search" aria-hidden="true"></i> <input
		class="form-control form-control-sm ml-3 w-75" type="text"
		placeholder="Le nom de l'article contient" aria-label="Search">

	<select class="custom-select mr-sm-2">
		<c:forEach var="categorie" items="${requestScope.listeCategories}">

			<option value="${categorie.id}">${categorie.libelle}</option>

		</c:forEach>

	</select> <input type="submit" class="btn btn-primary" value="Rechercher" />
</form>

<div class="row">
	<c:forEach var="vente" items="${requestScope.listeVentes}"
		varStatus="status">
		<div class="col-sm-6">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title"><a href="${pageContext.servletContext.contextPath}/GestionVenteServlet?action=detailsVente&id=${vente.numero}"><c:out value="${vente.nomArticle}"/></a></h5>
					<p class="card-text">Prix : <c:out value="${vente.prixVente}"/> <br/>Fin de l'enchère : <c:out value="${vente.dateFinEncheres}"/><br/>Retrait : <c:out value="${vente.util.pseudo}"/></p>
					<a href="#" class="btn btn-primary">Go somewhere</a>
				</div>
			</div>
		</div>
		<c:if test="${status.count %2 == 1 }">
			<c:out value="</div><div class="row">"/>
		</c:if>
	</c:forEach>
</div>

</div>

<%@ include file="footer.jsp"%>