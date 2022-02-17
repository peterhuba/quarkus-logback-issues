package org.acme.getting.started;

import java.util.Optional;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class ResponseLoggingFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        String logMessage = "\nOutbound response\n-----------------\nStatus: "
                + responseContext.getStatus() +
                "\nHeaders: " + FilterHelper.getHeadersAsString(responseContext.getHeaders()) +
                "\nBody: " + getBody(responseContext);

        log.info(logMessage);
    }

    private String getBody(ContainerResponseContext context) {
        return Optional.ofNullable(context.getEntity())
                .map(Object::toString)
                .orElse("<no response body>");
    }
}
