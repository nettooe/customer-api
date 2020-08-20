package com.store;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@OpenAPIDefinition(
	    info = @Info(
	        title="Customer API",
	        version = "1.0.0",
	        contact = @Contact(
	            name = "Oliver Netto",
	            url = "https://www.linkedin.com/in/olivernetto",
	            email = "netto.oe@gmail.com"),
	        license = @License(
	            name = "Apache 2.0",
	            url = "http://www.apache.org/licenses/LICENSE-2.0.html")),
    		components = @Components(
                    securitySchemes = {
                            @SecurityScheme(
                                    securitySchemeName = "bearerAuth",
                                    type = SecuritySchemeType.HTTP,
                                    scheme = "bearer",
                                    bearerFormat = "JWT"
                            )
                    }
            ),
            security = {
                    @SecurityRequirement(
                            name = "bearerAuth"
                    )
            }
	)
public class CustomerApi extends Application {

}
