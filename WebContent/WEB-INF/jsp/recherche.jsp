<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.eni.ecole.troc_encheres.bo.Categorie" %>
<%@ include file="entete.jsp" %>


<% List<Categorie> categories = (List<Categorie>) request.getAttribute("listeCategories");  %>
<form method="post" action="#">
	<div>
		<p>Filtres :</p>
		<input type="checkbox" name="mesVentes" id="mesVentes"
			class="form-check-input" /> <label for="mesVentes"
			class="form-check-label" id="lblVentes">Mes ventes</label><input
			type="checkbox" name="mesEncheresEnCours" id="mesEncheresEnCours"
			class="form-check-input" /> <label for="mesEncheresEnCours"
			id="lblEncheresEnCours" class="form-check-label">Mes
			enchères en cours</label> <input type="checkbox" name="mesAcquisitions"
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
	<c:forEach var="listeCategories" begin="0" end="10" step="2">
	
		
	</c:forEach>
		<option value="1">One</option>
		<option value="2">Two</option>
		<option value="3">Three</option>
	</select>
	<input type="submit" class="btn btn-primary" />
</form>

<div class="row">
  <div class="col-sm-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Special title treatment</h5>
        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
        <a href="#" class="btn btn-primary">Go somewhere</a>
      </div>
    </div>
  </div>
  <div class="col-sm-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Special title treatment</h5>
        <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
        <a href="#" class="btn btn-primary">Go somewhere</a>
      </div>
    </div>
  </div>
</div>

<%@ include file="footer.jsp" %>