/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbeurisko.service;

import com.google.gson.Gson;
import com.mycompany.dbeurisko.Utente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

/**
 *
 * @author Giuseppe
 */
@Stateless
@Path("/user")
public class UtenteFacadeREST extends AbstractFacade<Utente> {

    @PersistenceContext(unitName = "com.mycompany_dbEurisko_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public UtenteFacadeREST() {
        super(Utente.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createUser(Utente entity) {
        Utente u = getEntityManager().find(Utente.class, entity.getUsername());
        if(u == null){
            super.create(entity);
            return Response.ok(new Gson().toJson("ok"), MediaType.APPLICATION_JSON).build();
        }
        return Response.ok(null, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Utente entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response loginUser(@PathParam("id") String id,Utente u) {
        Utente ut = getEntityManager().find(Utente.class, u.getUsername());
        if(ut == null){
            return Response.ok(null, MediaType.APPLICATION_JSON).build();
        }else{
            if(u.getPassword().equals(ut.getPassword()))
                return Response.ok(new Gson().toJson(ut.getMail()), MediaType.APPLICATION_JSON).build();
            else
                return Response.ok(null, MediaType.APPLICATION_JSON).build();
        }
        
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Utente> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Utente> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
