/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dbeurisko.service;

import com.mycompany.dbeurisko.Evento;
import com.mycompany.dbeurisko.Foto;
import com.mycompany.dbeurisko.Utente;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Giuseppe
 */
@Stateless
@Path("/photo")
public class FotoFacadeREST extends AbstractFacade<Foto> {

    @PersistenceContext(unitName = "com.mycompany_dbEurisko_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public FotoFacadeREST() {
        super(Foto.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Foto entity) {
        Evento e = getEntityManager().find(Evento.class, entity.getIdevento().getIdevento());
        Utente u = getEntityManager().find(Utente.class, entity.getCreatore().getUsername());
        entity.setIdevento(e);
        entity.setCreatore(u);

        Random r = new Random();
        int id = r.nextInt(1000000);
        while (getEntityManager().find(Foto.class, id) != null) {
            id = r.nextInt(1000000);
        }
        entity.setIdfoto(id);
        try {
            super.create(entity);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
