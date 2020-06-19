package com.tuyano.springboot;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class FormModel {

	private int id;

	@Size(min = 8, max = 16)
	private String name;

	private int age;

	private String mail;

	private String memo;



}
