package com.Nghiem.tgu.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "carousel")
public class Carousel extends AuditModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String hinh;
	@Column
	private String text1;
	@Column
	private String text2;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getHinh() {
		return hinh;
	}


	public void setHinh(String hinh) {
		this.hinh = hinh;
	}


	public String getText1() {
		return text1;
	}


	public void setText1(String text1) {
		this.text1 = text1;
	}


	public String getText2() {
		return text2;
	}


	public void setText2(String text2) {
		this.text2 = text2;
	}


	@Override
	public String toString() {
		return "Carousel [id=" + id + ", hinh=" + hinh + ", text1=" + text1 + ", text2=" + text2 + "]";
	}


	public Carousel(long id, String hinh, String text1, String text2) {
		super();
		this.id = id;
		this.hinh = hinh;
		this.text1 = text1;
		this.text2 = text2;
	}


	public Carousel() {
		super();
	}


	
}
