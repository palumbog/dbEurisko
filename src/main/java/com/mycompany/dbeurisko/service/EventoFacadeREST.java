/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbeurisko.service;

import com.mycompany.dbeurisko.Commento;
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
        int id = r.nextInt(1000000);
        while (getEntityManager().find(Evento.class, id) != null) {
            id = r.nextInt(1000000);
        }
        entity.setIdevento(id);
        String username = entity.getCreatore().getUsername();
        Utente user = getEntityManager().find(Utente.class, username);
        entity.setCreatore(user);
        try {
            super.create(entity);
            return Response.ok(id, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.out.println(e.toString());
            return Response.serverError().build();
        }

    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        deletePhotos(id);
        deleteComments(id);
        super.remove(super.find(id));
    }

    @GET
    @Path("positions")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPosition() {
        Query query = em.createQuery("SELECT e FROM Evento e");
        List list = query.getResultList();
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        for (Object o : list) {
            if (o instanceof Evento) {
                Integer idEv = ((Evento) o).getIdevento();
                JSONObject itemObj = new JSONObject();
                itemObj.put("nome", ((Evento) o).getNome());
                itemObj.put("descr", ((Evento) o).getDescrizione());
                itemObj.put("id", ((Evento) o).getIdevento());
                itemObj.put("lat", ((Evento) o).getLuogoX());
                itemObj.put("long", ((Evento) o).getLuogoY());
                itemObj.put("tipo", ((Evento) o).getTipo());
                jsonArr.put(itemObj);
            }
        }
        jsonObj.put("positions", jsonArr);
        return Response.ok(jsonObj.toString(), MediaType.APPLICATION_JSON).build();
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
        List<JSONObject> comments = getCommentsEvent(e.getIdevento());
        jsonObj.put("comments", comments);
        return Response.ok(jsonObj.toString(), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findEvents(@QueryParam("s") String search, @QueryParam("t") String type) {
        //Query query = em.createQuery("SELECT e FROM Evento e WHERE e.citta LIKE 'search%'");
        Query query;
        if ("".equals(type) || "123".equals(type)) {
            query = em.createQuery("SELECT e FROM Evento e WHERE e.citta LIKE '%" + search + "%'");
        } else {
            char[] array = type.toCharArray();
            String strQuery = "SELECT e FROM Evento e WHERE e.citta LIKE '%" + search + "%' AND (";
            for (int i = 0; i < type.length(); i++) {
                strQuery += "e.tipo = " + array[i];
                if (array.length > 1 && i != array.length - 1) {
                    strQuery += " OR ";
                }
            }
            strQuery += ")";
            query = em.createQuery(strQuery);
        }
        //query.setParameter("search", search);
        List list = query.getResultList();
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
                itemObj.put("citta", ((Evento) o).getCitta());
                jsonArr.put(itemObj);
            }
        }
        jsonObj.put("events", jsonArr);

        return Response.ok(jsonObj.toString(), MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("user/{user}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findEventUser(@PathParam("user") String user) {
        Query query = em.createQuery("SELECT e FROM Evento e WHERE e.creatore = :user").setParameter("user", getEntityManager().find(Utente.class, user));
        List list = query.getResultList();
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
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    private List<String> getImagesEvent(Integer id) {
        List<String> list = new ArrayList<>();
        try {
            List l = em.createQuery("SELECT f FROM Foto f WHERE f.idevento = :idevento").setParameter("idevento", getEntityManager().find(Evento.class, id)).getResultList();
            for (Object o : l)
                if (o instanceof Foto)
                    list.add(((Foto) o).getFoto());
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    private List<JSONObject> getCommentsEvent(Integer idevento) {
        List result = em.createQuery("SELECT c.commento, c.creatore FROM Commento c WHERE c.idevento = :idevento").setParameter("idevento", getEntityManager().find(Evento.class, idevento)).getResultList();
        List<JSONObject> comments = new ArrayList<>();
        for(Object res:result) {
            Object[] ar = (Object[]) res;
            JSONObject obj = new JSONObject();
            obj.put("comment", (String)ar[0]);
            Utente u = (Utente)ar[1];
            obj.put("creator", u.getUsername());
            comments.add(obj);
        }
        return comments;
    }

    private void deletePhotos(Integer id) {
        List result = em.createQuery("SELECT f FROM Foto f WHERE f.idevento = :idevento").setParameter("idevento", getEntityManager().find(Evento.class, id)).getResultList();
        for(Object foto: result){
            if (foto instanceof Foto) {
                    getEntityManager().remove(foto);
                }
        }
    }

    private void deleteComments(Integer id) {
        List result = em.createQuery("SELECT f FROM Commento f WHERE f.idevento = :idevento").setParameter("idevento", getEntityManager().find(Evento.class, id)).getResultList();
        for(Object commento: result){
            if (commento instanceof Commento) {
                    getEntityManager().remove(commento);
                }
        }
    }

}
