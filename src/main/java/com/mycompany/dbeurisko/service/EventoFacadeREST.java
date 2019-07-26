/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbeurisko.service;

import com.google.gson.Gson;
import com.mycompany.dbeurisko.Evento;
import com.mycompany.dbeurisko.Foto;
import com.mycompany.dbeurisko.Utente;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author Giuseppe
 */
@Stateless
@Path("/event")
public class EventoFacadeREST extends AbstractFacade<Evento> {

    @PersistenceContext(unitName = "com.mycompany_dbEurisko_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public EventoFacadeREST() {
        super(Evento.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createEvent(Evento entity) {
        Random r = new Random();
        int id = r.nextInt();
        while (getEntityManager().find(Evento.class, id) != null) {
            id = r.nextInt();
        }
        entity.setIdevento(id);
        String username = entity.getCreatore().getUsername();
        Utente user = getEntityManager().find(Utente.class, username);
        entity.setCreatore(user);
        entity.setMiPiace(0);
        entity.setNonMiPiace(0);
        try {
            super.create(entity);
            return Response.ok().build();
        } catch (Exception e) {
            System.out.println(e.toString());
            return Response.serverError().build();
        }

    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Evento entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        Evento e = super.find(id);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("nome", e.getNome());
        jsonObj.put("descr", e.getDescrizione());
        jsonObj.put("citta", e.getCitta());
        jsonObj.put("creatore", e.getCreatore().getUsername());
        jsonObj.put("dataFine", e.getDataFine());
        jsonObj.put("dataInizio", e.getDataInizio());
        jsonObj.put("x", e.getLuogoX());
        jsonObj.put("y", e.getLuogoY());
        jsonObj.put("nome", e.getNome());
        jsonObj.put("oraFine", e.getOraFine());
        jsonObj.put("oraInizio", e.getOraInizio());
        jsonObj.put("tipo", e.getTipo());
        List<String> images = getImagesEvent(id);
        jsonObj.put("images", images);
        return Response.ok(jsonObj.toString(), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findEvents(@QueryParam("s") String search) {
        //Query query = em.createQuery("SELECT e FROM Evento e WHERE e.citta LIKE 'search%'");
        Query query = em.createQuery("SELECT e FROM Evento e WHERE e.citta LIKE '%" + search + "%'");

        //query.setParameter("search", search);
        List list = query.getResultList();
        Gson gson = new Gson();
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        for (Object o : list) {
            if (o instanceof Evento) {
                Integer idEv = ((Evento) o).getIdevento();
                JSONObject itemObj = new JSONObject();
                List images = getImagesEvent(idEv);
                String img = "";
                if (!images.isEmpty()) {
                    img = (String) images.get(0);
                }
                itemObj.put("nome", ((Evento) o).getNome());
                itemObj.put("descr", ((Evento) o).getDescrizione());
                itemObj.put("id", ((Evento) o).getIdevento());
                itemObj.put("img", img);
                jsonArr.put(itemObj);
            }
        }
        jsonObj.put("events", jsonArr);

        return Response.ok(jsonObj.toString(), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    private List<String> getImagesEvent(Integer id) {
        List<String> list = new ArrayList<>();
        try {
            //SELECT f FROM Foto f WHERE f.idfoto = :idfoto 
            List l = em.createQuery("SELECT f FROM Foto f WHERE f.idevento = :idevento").setParameter("idevento", getEntityManager().find(Evento.class, id)).getResultList();
            //List l = em.createQuery("SELECT img FROM Foto img WHERE img.idevento = ?1.").setParameter(1, getEntityManager().find(Evento.class, id)).getResultList();
            for (Object o : l) {
                if (o instanceof Foto) {
                    list.add(((Foto) o).getFoto());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}
