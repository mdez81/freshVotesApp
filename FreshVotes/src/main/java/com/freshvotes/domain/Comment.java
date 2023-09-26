package com.freshvotes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Comment {

	private CommnetId pk;
	private String text;
	
	@EmbeddedId
	public CommnetId getPk() {
		return pk;
	}
	public void setPk(CommnetId pk) {
		this.pk = pk;
	}
	
	@Column(length=5000)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
