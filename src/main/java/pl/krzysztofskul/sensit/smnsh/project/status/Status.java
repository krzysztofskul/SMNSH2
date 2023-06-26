package pl.krzysztofskul.sensit.smnsh.project.status;


/**
	UNDEFINED("BRAK DANYCH / UNDEFINED", "BRAK DANYCH", "UNDEFINED"),
	PRECONTRACT("AKWIZYCJA / PRE-CONTRACT", "AKWIZYCJA", "PRE-CONTRACT"),
	CONTRACT("UMOWA / CONTRACT", "PODPISANIE UMOWY", "CONTRACT"),
	EXECUTION("REALIZACJA / EXECUTION", "REALIZACJA", "EXECUTION"),
	COMPLETED("ZAKOŃCZONY / COMPLETED", "ZAKOŃCZONY", "COMPLETED"),
	NOBID("ANULOWANY / NO-BID", "ANULOWANY", "NO-BID");
 */
public enum  Status {

	UNDEFINED("BRAK DANYCH / UNDEFINED", "BRAK DANYCH", "UNDEFINED"),
	PRECONTRACT("AKWIZYCJA / PRE-CONTRACT", "AKWIZYCJA", "PRE-CONTRACT"),
	CONTRACT("UMOWA / CONTRACT", "PODPISANIE UMOWY", "CONTRACT"),
	EXECUTION("REALIZACJA / EXECUTION", "REALIZACJA", "EXECUTION"),
	COMPLETED("ZAKOŃCZONY / COMPLETED", "ZAKOŃCZONY", "COMPLETED"),
	NOBID("ANULOWANY / NO-BID", "ANULOWANY", "NO-BID");

	
	private String name;
	private String namePl;
	private String nameEn;
	
	Status(String name){
		this.name = name;
	}

	private Status(String name, String namePl, String nameEn) {
		this.name = name;
		this.namePl = namePl;
		this.nameEn = nameEn;
	}


	@Override
	public String toString() {
		return name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getNamePl() {
		return namePl;
	}

	public void setNamePl(String namePl) {
		this.namePl = namePl;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	};
    
	
    
}