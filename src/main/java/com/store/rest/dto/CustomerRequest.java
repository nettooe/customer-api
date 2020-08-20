package com.store.rest.dto;

public class CustomerRequest {

	public String name;
	public String cpf;
	public String address;

	/**
	 * @param name
	 * @param cpf
	 * @param address
	 */
	public CustomerRequest(String name, String cpf, String address) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.address = address;
	}

	public CustomerRequest() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
