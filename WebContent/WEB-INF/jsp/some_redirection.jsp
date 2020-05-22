<!-- Author Lucille -->
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
	<h1>Test de déconnexion / redirection</h1>
	<p>De la parlotte</p>
	<a href="${pageContext.servletContext.contextPath}/deconnexion">Déconnexion</a>
</body>
</html>