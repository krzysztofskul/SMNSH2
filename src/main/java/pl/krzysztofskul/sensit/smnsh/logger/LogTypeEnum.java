package pl.krzysztofskul.sensit.smnsh.logger;

public enum LogTypeEnum {
	
	USER_REGISTER("Zarejestrowano użytkownika","User has been registered"),
	USER_DELETE("Usunięto użytkownika","User has been deleted"),
	USER_LOGIN("Zalogowano użytkownika", "User has been logged in"),
	USER_LOGOUT("Wylogowano użytkownika","User has been logged out"),	
	USER_VIEW_HOME_PAGE("Wyświetlono stronę startową","The home page has been shown"),
	USER_VIEW_PROJECTS_PAGE("Wyświetlono listę projektów","The page with projetcs list has been shown"),
	USER_VIEW_PROJECT_PAGE("Wyświetlono szczegóły projektu","The page with details of  the project has been shown"),
	PROJECT_CREATE("Utworzono projekt","Project has been created"), 
	PROJECT_UPDATE("Zaktualizowano projekt","Project has been updated"),
	PROJECT_VIEW("Wyświetlono projekt","Project has been displayed"),
	PROJECT_DELETE("Usunięto projekt","Project has been deleted");
	
	private String namePl;
	private String nameEn;
	
	private LogTypeEnum(String namePl, String nameEn) {
		this.namePl = namePl;
		this.nameEn = nameEn;
	}

	/**
	 * @return the namePl
	 */
	public String getNamePl() {
		return namePl;
	}

	/**
	 * @return the nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}

	

}
