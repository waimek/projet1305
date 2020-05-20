<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:setLocale value="fr_FR" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vente</title>
<%@ include file="head_css.jsp"%>
</head>
<body>
	<%@ include file="entete.jsp"%>
	<div class="container">
		<!-- Auteur : Edouard -->
		<h2 class="text-center h2">Détail vente</h2>
		<c:if test="${not empty acheteur && vente.dateFinEncheres.time < now.time}">
			<h3 class="text-center h3">${acheteur.pseudo} a remporté l'enchère</h3>
		</c:if>
			<h4 class="h4">${ vente.nomArticle}</h4>
		<ul class="col list-group list-group-flush">
			<li class="list-group-item">
				Description : ${vente.description}
			</li>
				<li class="list-group-item">
				Meilleure offre : ${vente.prixVente}</li>
				<li class="list-group-item">
				Mise à prix : ${vente.miseAPrix}</li>
				<li class="list-group-item">
				Fin de l'enchère : <fmt:formatDate value="${vente.dateFinEncheres}" pattern="dd/MM/yyyy"/>
			</li>
				<li class="list-group-item">Retrait : ${vente.retrait.rue} ${vente.retrait.cp } ${vente.retrait.ville}
			</li>
				<li class="list-group-item">
			Vendeur : ${vente.util.pseudo}
				</li>
				</ul>
	
		
		<c:if test="${empty acheteur && vente.dateFinEncheres.time > now.time}">
			<a class="btn btn-primary"
				href ="#"
				role="button">Annuler la vente</a><!-- href="${pageContext.servletContext.contextPath}/GestionVenteServlet?action=annulerVente&noVente=${vente.numero}"-->
		</c:if>
		
		<c:if test="${not empty acheteur && vente.dateFinEncheres.time < now.time}">
			<c:if test="${not empty retrait}">
				<a class="btn btn-primary"
					href="${pageContext.servletContext.contextPath}/RechercheServlet?action=validerRetrait&noVente=${vente.numero}"
					role="button">Retrait effectué</a>
			</c:if>
			<a class="btn btn-primary"
				href="${pageContext.servletContext.contextPath}/GestionVenteServlet?action=contact&utilisateur=${acheteur.numero}"
				role="button">Contacter ${acheteur.pseudo}</a>
		</c:if>
		<a class="btn btn-primary"
			href="${pageContext.servletContext.contextPath}/RechercheServlet"
			role="button">Retour</a>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>