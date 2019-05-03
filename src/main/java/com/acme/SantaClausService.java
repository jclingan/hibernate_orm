package com.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/api")
public class SantaClausService {
    @Inject
    EntityManager em;

    @Transactional
    @POST
    @Path("/{description}")
    public void createGift(@PathParam("description") String giftDescription) {
        Gift gift = new Gift();
        gift.setName(giftDescription);
        em.persist(gift);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Gift[] getAllGifts() {
        return em.createNamedQuery("Gift.findAll", Gift.class).getResultList().toArray(new Gift[0]);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Transactional
    public void deleteGift(@PathParam("id") int id) {
        Gift gift = null;
        Query query = em.createNamedQuery("Gift.findById");
        query.setParameter("id", (long)id);
        try {
            gift = (Gift) query.getSingleResult();
        } catch (PersistenceException ex) {
        }

        if (gift != null) {
            em.remove(gift);
        }
    }
}