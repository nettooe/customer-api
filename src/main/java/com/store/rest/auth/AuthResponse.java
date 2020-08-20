package com.store.rest.auth;

public class AuthResponse {

	public String token;

	public AuthResponse() {
	}

	/**
	 * @param token
	 */
	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "AuthResponse [token=" + token + "]";
	}

}
