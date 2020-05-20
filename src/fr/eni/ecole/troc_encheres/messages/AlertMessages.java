package fr.eni.ecole.troc_encheres.messages;

public class AlertMessages {

	public static String success(String message ) {
		return "<div class=\"alert alert-success\" role=\"alert\">" + message + "</div>";
	}
	
	public static String failed(String message ) {
		return "<div class=\"alert alert-danger\" role=\"alert\">" + message + "</div>";
	}
}
