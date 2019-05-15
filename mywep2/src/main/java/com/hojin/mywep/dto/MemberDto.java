package com.hojin.mywep.dto;

public class MemberDto {
	private String ID;
	private String PW;
	private char ADMIN;
	private String NAME;
	private String EMAIL;
	private String TEL;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public char getADMIN() {
		return ADMIN;
	}
	public void setADMIN(char aDMIN) {
		ADMIN = aDMIN;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	@Override
	public String toString() {
		return "MemberDto [ID=" + ID + ", PW=" + PW + ", ADMIN=" + ADMIN + ", NAME=" + NAME + ", EMAIL=" + EMAIL
				+ ", TEL=" + TEL + "]";
	}
	
}
