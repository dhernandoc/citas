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
package org.move2dbit.citas.rest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.move2dbit.citas.interceptors.LogTracing;
import org.move2dbit.citas.model.Solicitante;
import org.move2dbit.citas.rest.SolicitanteRESTService;
import org.move2dbit.citas.rest.entity.SolicitanteRestBean;
import org.move2dbit.citas.service.SolicitanteService;


@LogTracing
public class SolicitanteRESTServiceImpl implements SolicitanteRESTService {

	@Inject 
	Logger log;

	@EJB

	private SolicitanteService solicitanteService;

	public List<SolicitanteRestBean> listAllSolicitantesRest() {
    	log.fine("Obtiene lista de Solicitantes");
    	List<SolicitanteRestBean> resultadoRest=null;
		try {
			resultadoRest = getListaSolicitantesRest(solicitanteService.listAllSolicitantes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultadoRest;
	}


	@Override
	public Response createSolicitante(Solicitante solicitante) {
		// TODO Auto-generated method stub
		try
		{
			solicitanteService.create(solicitante);
		}
		catch (Throwable ex)
		{
			log.severe(ex.getMessage());		
		}
	    return Response.ok(solicitante).build();
	}

		@Override
		public Response deleteById(Long id) {
		   try
		   {
			   solicitanteService.delete(id);
			   return Response.noContent().build();

		   }
		   catch (Exception ex)
		   {
		      return Response.status(Status.NOT_FOUND).build(); 
		   }
		}
	   public Response getSolicitanteById(@PathParam("id") long id)
	   {
	      Solicitante solicitante=null;
	      try
	      {
	    	  log.fine("No implmentado");
	      }
	      catch (Exception nre)
	      {
	    	  log.severe(nre.getMessage());
	      }
	      if (solicitante == null)
	      {
	         return Response.status(Status.NOT_FOUND).build();
	      }
	      return Response.ok(solicitante).build();
	   }

	   @GET
	   @Produces("application/json")
	   public List<Solicitante> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
	   {
		   List<Solicitante> resultado=null;
		   try {
			resultado = solicitanteService.listAllSolicitantes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return resultado;
	   }
	   public Response updateSolicitanteRest(Solicitante entity)
	   {
	      try
	      {
	         entity = solicitanteService.updateSolicitante(entity);
	      }
	      catch (Exception e)
	      {
	         return Response.status(Response.Status.CONFLICT).entity(entity).build();
	      }
	      return Response.noContent().build();
	   }
	   
	   private List<SolicitanteRestBean> getListaSolicitantesRest(List<Solicitante> solicitantes)
	   {
	    	List<SolicitanteRestBean> resultadoRest = new ArrayList<SolicitanteRestBean>();
	    	SolicitanteRestBean temp = null;
	    	try
	    	{
	    		for (Solicitante solicitante : solicitantes) {
					temp = new SolicitanteRestBean();
					temp.setNombre(solicitante.getNombre());
					temp.setId(solicitante.getId());
					temp.setEmail(solicitante.getEmail());
					temp.setTelefono(solicitante.getTelefono());
					temp.setVersion(solicitante.getVersion());
					resultadoRest.add(temp);
	    		}
	    	}
			catch (Exception ex)
			{
				log.severe(ex.getMessage());
			}
	    	return resultadoRest;
	   }


}
