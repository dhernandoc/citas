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

import org.move2dbit.citas.model.Paciente;
import org.move2dbit.citas.service.PacienteService;
import org.move2dbit.citas.service.exception.CreatePacienteException;


// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@Local(PacienteService.class)
public class PacienteServiceImpl {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Paciente> pacienteEventSrc;

    public void create(Paciente paciente) throws CreatePacienteException {
       try
       {
       	log.info("Creando Paciente " + paciente.getNombre());
        em.persist(paciente);
        pacienteEventSrc.fire(paciente);    	   
        log.exiting("Va", "Ahora");
       }
       catch (Throwable th)
       {
    	   CreatePacienteException tth = new CreatePacienteException("Se produjo un error al crear el paciente", th);
    	   throw tth;
       }
    }

    public void delete(Long idPaciente) throws Exception {
        log.info("Borrando Paciente " + idPaciente);
        Paciente paciente = em.find(Paciente.class, idPaciente);
	    if (paciente == null)
	    {
	       throw new Exception();
	    }
        em.remove(paciente);
    }

    public List<Paciente> listAllPacientes() throws Exception{
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Paciente> criteria = cb.createQuery(Paciente.class);
        Root<Paciente> paciente = criteria.from(Paciente.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(paciente).orderBy(cb.asc(paciente.get("nombre")));
        return em.createQuery(criteria).getResultList();

    }
    public Paciente findById(Long id) throws Exception{
        return em.find(Paciente.class, id);
    }

    public Paciente findByEmail(String email) throws Exception{
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Paciente> criteria = cb.createQuery(Paciente.class);
        Root<Paciente> paciente = criteria.from(Paciente.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.email), email));
        criteria.select(paciente).where(cb.equal(paciente.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }


    public Paciente updatePaciente(Paciente paciente) throws Exception{
	         paciente = em.merge(paciente);
	      return paciente;
		   
	   }
       
}
