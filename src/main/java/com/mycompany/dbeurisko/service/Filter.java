/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbeurisko.service;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;


@Provider
public class Filter implements ContainerRequestFilter,ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //DO Stuff
        System.out.println("RequestContext");
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext s)
            throws IOException {
        //DO Stuff
        System.out.println("ResponseContext");
        s.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        s.getHeaders().putSingle("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        s.getHeaders().putSingle("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
    }
}