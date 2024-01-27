package pl.krzysztofskul.smnsh2.company.CompanyCategory;

public enum CompanyCategoryEnum {

	INVESTOR("Investor", "Klinet/Inwestor (płatnik)"), 
	CUSTOMER("Customer", "Klient/Konsument (odbiorca/użytkownik)"),  
	SUBCONTRACTOR("Subcontractor", "Podwykonawca"),
	SUBCONTRACTOR_GENERAL("General subcontractor", "Generalny wykonwaca"),
	SUBCONTRACTOR_ROOM_ADAPTATION("Subcontractor of room adaptations", "Wykonawca adaptacji pomieszczeń"),
	SUPPLIER("Supplier", "Dostawca");

	private String namePL;
	private String nameEN;
	
	private CompanyCategoryEnum(String nameEN, String namePL) {
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
