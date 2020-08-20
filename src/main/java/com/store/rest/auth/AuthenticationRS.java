package com.store.rest.auth;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.store.entity.User;
import com.store.security.PBKDF2Encoder;
import com.store.security.TokenGenerator;

@Path("/user")
@Tag(name = "user", description = "Login and Token operations.")
public class AuthenticationRS {

	@Inject
	PBKDF2Encoder passwordEncoder;

	@ConfigProperty(name = "com.store.quarkusjwt.jwt.duration")
	public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer")
	public String issuer;

	@PermitAll
	@POST
	@Operation(summary = "Get Bearer token.")	
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(
			@RequestBody(required = true, name = "login", description = "username/password to get Bearer token. Examples: user/user , admin/admin") AuthRequest authRequest) {
		User u = User.findByUsername(authRequest.username);
		if (u != null && u.password.equals(passwordEncoder.encode(authRequest.password))) {
			try {
				return Response
						.ok(new AuthResponse(TokenGenerator.generateToken(u.username, u.roles, duration, issuer)))
						.build();
			} catch (Exception e) {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
}
