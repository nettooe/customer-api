package com.store.repository;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import com.store.entity.Customer;
import com.store.rest.dto.CustomerRequest;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

	public void deleteStefs() {
		delete("name", "Stef");
	}

	@Transactional
	public Customer persist(CustomerRequest req) {
		Customer customer = new Customer(req.getName(), req.getCpf(), req.getAddress());
		persist(customer);
		return customer;
	}

	@Transactional
	public Customer partialUpdate(Long id, CustomerRequest req) {

		Optional<Customer> optional = findByIdOptional(id, LockModeType.PESSIMISTIC_WRITE);

		if (!optional.isPresent()) {
			return null;
		}

		Customer customer = optional.get();

		if (req.getName() != null && !req.getName().isEmpty()) {
			customer.setName(req.getName());
		}

		if (req.getCpf() != null && !req.getCpf().isEmpty()) {
			customer.setCpf(req.getCpf());
		}

		if (req.getAddress() != null && !req.getAddress().isEmpty()) {
			customer.setAddress(req.getAddress());
		}

		persist(customer);

		return customer;
	}

	@Transactional
	public boolean remove(Long id) {
		return deleteById(id);
	}

}
