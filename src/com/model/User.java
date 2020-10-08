package com.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
	private int id;
	private String username, email, password, fullname, phone, address;

}
