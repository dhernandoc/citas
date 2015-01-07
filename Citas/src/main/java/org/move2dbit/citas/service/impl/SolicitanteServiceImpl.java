/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.move2dbit.citas.service.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.move2dbit.citas.model.Solicitante;
import org.move2dbit.citas.service.SolicitanteService;
import org.move2dbit.citas.service.exception.CreateSolicitanteException;


// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@Local(SolicitanteService.class)
public class SolicitanteServiceImpl {

	@Inject
	Logger log;
    @Inject
    private EntityManager em;

    @Inject
    private Event<Solicitante> solicitanteEventSrc;

    public void create(Solicitante solicitante) throws CreateSolicitanteException {
       try
       {
        em.persist(solicitante);
        solicitanteEventSrc.fire(solicitante);    	   
       }
       catch (Throwable th)
       {
    	   CreateSolicitanteException tth = new CreateSolicitanteException("Se produjo un error al crear el paciente", th);
    	   throw tth;
       }
    }

    public void delete(Long idSolicitante) throws Exception {
    	log.info("Borrando Solicitante " + idSolicitante);
        Solicitante solicitante = em.find(Solicitante.class, idSolicitante);
	    if (solicitante == null)
	    {
	       throw new Exception();
	    }
        em.remove(solicitante);
    }

    public List<Solicitante> listAllSolicitantes() throws Exception{
        log.info("Obtengo la lista completa");
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Solicitante> criteria = cb.createQuery(Solicitante.class);
        Root<Solicitante> solicitante = criteria.from(Solicitante.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(solicitante).orderBy(cb.asc(solicitante.get("nombre")));
        return em.createQuery(criteria).getResultList();

    }
    public Solicitante findById(Long id) throws Exception{
        return em.find(Solicitante.class, id);
    }

    public Solicitante findByEmail(String email) throws Exception{
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Solicitante> criteria = cb.createQuery(Solicitante.class);
        Root<Solicitante> solicitante = criteria.from(Solicitante.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.email), email));
        criteria.select(solicitante).where(cb.equal(solicitante.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }


    public Solicitante updateSolicitante(Solicitante solicitante) throws Exception{
    	solicitante = em.merge(solicitante);
	      return solicitante;
		   
	   }
       
}
