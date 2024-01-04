package com.harinpos.user;

@SuppressWarnings("serial")
public class DuplicateMemberException extends Exception {
	public DuplicateMemberException(String message) {
		super(message);
	}
}
