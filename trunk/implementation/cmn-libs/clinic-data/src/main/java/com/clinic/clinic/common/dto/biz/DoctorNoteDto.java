package com.clinic.clinic.common.dto.biz;
import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

public class DoctorNoteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 199470818958031843L;

	@NotNull
	private String text;
	
	private String imgage;
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getImgage() {
		return imgage;
	}
	
	public void setImgage(String imgage) {
		this.imgage = imgage;
	}
}
