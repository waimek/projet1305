<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recherche</title>
<%@ include file="head_css.jsp"%>
</head>
<body>
	<%@ include file="entete.jsp"%>
	<!-- Auteur : Matthieu -->
	<div class="container">
		<div class="row">
			<form class="col mt-2" method="post" action="${pageContext.servletContext.contextPath}/RechercheServlet" enctype="application/x-www-form-urlencoded">
				<p class="row font-weight-bold mb-0">Filtres :</p>
				<div class="row ml-3">
					<div class="col">
						<div class="row">
							<input type="checkbox" name="mesVentes" id="mesVentes" class="form-check-input" /> 
							<label for="mesVentes" class="form-check-label" id="lblVentes">Mes ventes</label> 
						</div>
						<div class="row">
							<input type="checkbox" name="mesEncheresEnCours" id="mesEncheresEnCours" class="form-check-input" /> 
							<label for="mesEncheresEnCours" id="lblEncheresEnCours" class="form-check-label">Mes enchères en cours</label>
						</div>
						<div class="row">
							<input type="checkbox" name="mesAcquisitions" id="mesAcquisitions" class="form-check-input" /> 
							<label for="mesAcquisitions" id="lblAcquisitions" class="form-check-label"> Mes acquisitions</label> 
						</div>
						<div class="row mb-2">
							<input type="checkbox" name="autresEncheres" id="autresEncheres" class="form-check-input" /> 
							<label for="autresEncheres" id="lblAutresEncheres" class="form-check-label">Autres enchères</label>
						</div>
					</div>
				</div>
				
				<div class="row mb-2">
					<label for="choixCategorie" id="lblChoixCategorie" class="form-check-label col-2 pt-1">Catégories</label>
					<select name="choixCategorie" class="custom-select mr-sm-2 col">
						<option value="0">Toutes</option>
						<c:forEach var="categorie" items="${listeCategories}">
							<option value="${categorie.numero}">${categorie.libelle}</option>
						</c:forEach>
					</select>
				</div>
			
				<div class="row my-3">
					<input class="form-control form-control-sm rounded-pill mr-2 " type="text" name="textRecherche" placeholder="Le nom de l'article contient" aria-label="Search"> 
				</div>
				
				<div class="row my-3">
					<input type="submit" class="btn btn-primary col py-4" value="Rechercher" />
				</div>
			</form>
		</div>
		
		

		<c:if test="${aucunResultat}">
			<div class="row">
				<div class="col">
					<div class="alert alert-danger" role="alert">
						Aucun résultat trouvé
					</div>
				</div>
				
			</div>
		</c:if>
		<c:forEach var="vente" items="${listeRecherche}">
			<div class="row my-3">
				<div class="col">
					<div class="card">
						<div class="card-body row">
							<div class="col-3" >
								<div class="border border-primary">
									<img class=img-responsive  style="width: 100%" alt="image" src="${pageContext.servletContext.contextPath}/img/code-1076536_1920.jpg">
								</div>
							</div>
							<div class="col">
								<h5 class="card-title">
									<a href="${pageContext.servletContext.contextPath}/GestionVenteServlet?action=detailsVente&id=${vente.numero}">${vente.nomArticle}</a>
								</h5>
								<p class="card-text">
									<span class="d-block">Prix : ${vente.prixVente}</span>
									<span class="d-block">Fin de l'enchère : ${vente.dateFinEncheres}</span>
									<span class="d-block">
										Vendeur : 
										<a href="" >${vente.util.pseudo}</a>
									</span>
								</p>
								<a href="#" class="btn btn-primary">Go somewhere</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>