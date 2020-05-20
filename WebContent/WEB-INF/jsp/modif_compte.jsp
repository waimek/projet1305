<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un compte</title>
<%@ include file="head_css.jsp"%>
</head>
<body>
	<!-- author Dominika/Matthieu -->
	<div class="container">
		<div class="row">
			<h2>Modifier les informations</h2>
		</div>
		
		<div class="row">
		<%
			List<String> listeErreur = (List<String>) request.getAttribute("listeErreur");
		if (listeErreur != null) { %>
		<p style="color: red;">
			Erreur :
			<% 
			for (int i = 0; i < listeErreur.size(); i++) {
		%>
			<%=listeErreur.get(i)%></p>
		<%
		}
		}
		%>
		</div>
		
		<div class="row mt-4">
			<div class="col">
				<form action="${pageContext.servletContext.contextPath}/modifierProfil" method="post">
				
					<div class="form-row my-2">
						<label class="col-2" for="lblPseudo">Pseudo :</label> 
						<input class="col form-control" type="text"  placeholder="Pseudo" name="pseudo" id="pseudo" value="${sessionScope.pseudo}" required>
					</div>
					<div class="form-row my-2">		
						<label class="col-2" for="lblNom" >Nom :</label> 
						<input class="col form-control" type="text"  placeholder="Nom" name="nom" id="Nom" value="${sessionScope.nom}" required>
					</div>
					
					<div class="form-row my-2">		
						<label class="col-2" for="lblPrenom">Prenom :</label> 
						<input class="col form-control" type="text"  placeholder="Prénom" name="prenom" id="prenom" value="${sessionScope.prenom}" required>
					</div>
					
					<div class="form-row my-2">	
						<label class="col-2" for="lblEmail">E-mail :</label> 
						<input class="col form-control" type="email"  placeholder="Email" name="email" id="email" value="${sessionScope.email}" required>	
					</div>
					
					<div class="form-row my-2">	
						<label class="col-2" for="lblTel">Téléphone :</label> 
						<input class="col form-control" type="tel"  placeholder="Téléphone" name="tel" id="tel" value="${sessionScope.tel}" required>
					</div>
				
					<div class="form-row my-2">	
						<label class="col-2" for="lblRue">Rue :</label> 
						<input class="col form-control" type="text" placeholder="Rue" name="rue" id="rue" value="${sessionScope.rue}" required>
					</div>
				
					<div class="form-row my-2">	
						<label class="col-2"  for="lblCP">CodePostal :</label> 
						<input class="col form-control" type="text" placeholder="Code postal" name="cp" id="cp"  pattern="[0-9]{5}" value="${sessionScope.cp}" required>
					</div>
				
					<div class="form-row my-2">	
						<label class="col-2" for="lblVille">Ville :</label> 
						<input class="col form-control" type="text" placeholder="Ville" name="ville" id="ville" value="${sessionScope.ville}" required>
					</div>
				
					<div class="form-row my-2">	
						<label class="col-2" for="lblMdp">Mot de passe :</label> 
						<input class="col form-control" type="password" placeholder="Mot de passe" name="mdp" id="mdp" value="${sessionScope.mdp}" required>
					</div>
				
					<div class="form-row my-2">	
						<label class="col-2" for="lblMdpConf">Confirmation :</label> 
						<input class="col form-control" type="password" placeholder="Confirmation de mot de passe" name="mdpConf" id="mdpConf" value="${sessionScope.mdp}" required>
					</div>
				
				
					<div class="row mt-4 mb-2">
						<div class="col">
							<button type="submit" class="btn btn-primary w-100 py-3">Valider</button>
						</div>
						<div class="col">
							<button type="reset" class="btn btn-primary w-100 py-3">Annuler</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>

</body>
</html>