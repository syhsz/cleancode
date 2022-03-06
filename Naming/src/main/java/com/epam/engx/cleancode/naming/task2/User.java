package com.epam.engx.cleancode.naming.task2;

import java.util.Arrays;
import java.util.Date;

public class User {

	protected boolean admin = false;

	private String birth;

	private String name;

	private User[] subordinateArray;

	private int rating;

	public User(String name, String birth, User[] subordinateArray) {
		this.birth = birth;
		this.name = name;
		this.subordinateArray = subordinateArray;
	}

	@Override
	public String toString() {
		return "User [dateOfBirth=" + birth + ", name=" + name + ", isAdmin=" + admin + ", subordinates="
				+ Arrays.toString(subordinateArray) + ", rating=" + rating + "]";
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
