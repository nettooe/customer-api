package com.store.rest.customer;

import java.net.URI;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.store.entity.Customer;
import com.store.repository.CustomerRepository;
import com.store.rest.dto.CustomerRequest;

@Path(CustomerRS.PATH_CUSTOMER)
@Tag(name="customer", description="Operations related to customers")
public class CustomerRS {

	static final String PATH_CUSTOMER = "/customer";
	
	@Inject
	CustomerRepository repository;

	@RolesAllowed({ "USER", "ADMIN" })
	@GET
	@Operation(summary = "Get Customer by ID.")
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerById(@PathParam("id") Long id) {
		Optional<Customer> optional = repository.findByIdOptional(id);

		if (!optional.isPresent()) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(optional.get()).build();
	}


	@RolesAllowed({ "USER", "ADMIN" })
	@POST
	@Operation(summary = "Register new customer.")
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(@RequestBody(required = true, name = "customer", description = "Customer data to persist.") CustomerRequest customer) {
		Customer saved = repository.persist(customer);
		return Response.created(URI.create(PATH_CUSTOMER + "/" + saved.getId())).build();
	}
	
	@RolesAllowed({ "USER", "ADMIN" })
	@PUT
	@Operation(summary = "Update partial customer data.")
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(@PathParam("id") Long id, @RequestBody(required = true, name = "customer", description = "Customer data to persist.") CustomerRequest customer) {
		Customer saved = repository.partialUpdate(id, customer);
		if(saved!=null) {
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@DELETE
	@RolesAllowed({"ADMIN" })
	@Operation(summary = "Remove customer.")
	@Path("/{id}")	
	@Consumes(MediaType.APPLICATION_JSON)
	// Only users with an ADMIN profile can perform this operation.
	public Response remove(@PathParam("id") Long id) {
		if(repository.remove(id)) {
			return Response.noContent().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}