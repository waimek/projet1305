<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="entete.jsp"%>




<form
	action="${pageContext.servletContext.contextPath}/GestionVenteServlet"
	method="post">

	<div class="form-group">
		<label for="article" id="lblArticle">Article :</label> 
		<input type="text" id="article" name="article" class="form-control" />
	</div>
	<div class="form-group">
		<label for="description" id="lblDescription">Description :</label> 
		<textarea name="description" id="description" class="form-control"></textarea>
	</div>
	<div class="form-group">
		<label for="categorie" id="lblCategorie">Catégorie :</label> 
		
	</div>



</form>

<%@ include file="footer.jsp"%>