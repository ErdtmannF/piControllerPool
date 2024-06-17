package de.erdtmann.soft.homesteuerung.piControllerPool.utils;

public enum Temperatur {
	WASSER("Wassertemperatur", 1, 0, "/wasser"), SOLAR("Solartemperatur", 2, 1, "/solar");

	// Technische ID f�r die Datenbank
	private int id;
	// ID f�r den AD Wandler
	private int value;
	// Rest Pfad f�r eine Temperatur
	private String restPfad;
	// Bezeichnung der Temperatur
	private String bezeichnung;

	Temperatur(String bezeichnung, int id, int value, String restPfad) {
		this.bezeichnung = bezeichnung;
		this.id = id;
		this.value = value;
		this.restPfad = restPfad;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public int getId() {
		return id;
	}

	public int getValue() {
		return value;
	}

	public String getRestPfad() {
		return restPfad;
	}

}
