package pl.krzysztofskul.sensit.smnsh.project.milestone;

public enum MilestoneStatusEnum {
	
	WAITING ("WAITING", "OCZEKUJE"),
	IN_PROGRESS ("IN PROGRESS", "W TOKU"),
	COMPLETED ("FINISHED", "ZAKOŃCZONY"),
	CANCELED ("CANCELED", "ANULOWANY");

	/*
	 * PARAMS.
	 */
	private String nameEN;
	private String namePL;
	
	
	/*
	 * CONSTR.
	 */
	private MilestoneStatusEnum(String nameEN, String namePL) {
		this.nameEN = nameEN;
		this.namePL = namePL;
	}
	
	/*
	 * GETTERS AND SETTERS
	 * 
	 */
	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getNamePL() {
		return namePL;
	}

	public void setNamePL(String namePL) {
		this.namePL = namePL;
	}
	
}
