<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="entete.jsp"%>
<%@ page import="fr.eni.ecole.troc_encheres.bo.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page redirection test Connexion</title>
</head>
<body>
	<%
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
	%>
	<h1>Vous êtes bien connecté</h1>
	<h2>Bonjour :</h2>
<ul>
<li><%=utilisateur.getPseudo() %>
<li><%=utilisateur.getMdp() %>
</ul>
</body>
</html>