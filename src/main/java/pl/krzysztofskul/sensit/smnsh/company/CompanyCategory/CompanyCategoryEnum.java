package pl.krzysztofskul.sensit.smnsh.company.CompanyCategory;

public enum CompanyCategoryEnum {

	INVESTOR("Investor", "Inwestor (płatnik)"), 
	CUSTOMER("Customer", "Klient (odbiorca/użytkownik)"), 
	SUBCONTRACTOR("Subocntractor", "Podwykonawca"),
	SUBCONTRACTOR_GENERAL("General subcontractr", "Generalny wykonwaca"),
	SUBCONTRACTOR_ROOM_ADAPTATION("Subcontractor of room adaptations", "Wykonawca adaptacji pomieszczeń"),
	SUPPLIER("Supplier", "Dostawca");

	private String namePL;
	private String nameEN;
	
	private CompanyCategoryEnum(String namePL, String nameEN) {
		this.namePL = namePL;
		this.nameEN = nameEN;
	}

	/**
	 * @return the namePL
	 */
	public String getNamePL() {
		return namePL;
	}

	/**
	 * @param namePL the namePL to set
	 */
	public void setNamePL(String namePL) {
		this.namePL = namePL;
	}

	/**
	 * @return the nameEN
	 */
	public String getNameEN() {
		return nameEN;
	}

	/**
	 * @param nameEN the nameEN to set
	 */
	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}
	
	
	
}
