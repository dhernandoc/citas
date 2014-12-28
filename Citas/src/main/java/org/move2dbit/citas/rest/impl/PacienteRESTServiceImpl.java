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

import org.move2dbit.citas.model.Paciente;
import org.move2dbit.citas.rest.PacienteRESTService;
import org.move2dbit.citas.rest.entity.PacienteRestBean;
import org.move2dbit.citas.service.PacienteService;


public class PacienteRESTServiceImpl implements PacienteRESTService {


	@Inject
    private Logger log;

	@EJB
    private PacienteService pacienteService;

	public List<PacienteRestBean> listAllPacientesRest() {
    	log.entering(this.getClass().getName(), "public List<PacienteRestBean> listAllPacientesRest()");
    	List<PacienteRestBean> resultadoRest=null;
		try {
			resultadoRest = getListaPacientesRest(pacienteService.listAllPacientes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	log.exiting(this.getClass().getName(), "public List<PacienteRestBean> listAllPacientesRest()", resultadoRest);
    	return resultadoRest;
	}


	@Override
	public Response createPaciente(Paciente paciente) {
		// TODO Auto-generated method stub
		try
		{
			pacienteService.create(paciente);
		}
		catch (Throwable ex)
		{
			log.severe(ex.getMessage());		
		}
	    return Response.ok(paciente).build();
	}

		@Override
		public Response deleteById(Long id) {
		   try
		   {
			   pacienteService.delete(id);
			   return Response.noContent().build();

		   }
		   catch (Exception ex)
		   {
		      return Response.status(Status.NOT_FOUND).build(); 
		   }
		}
	   public Response getPacienteById(@PathParam("id") long id)
	   {
	      Paciente paciente=null;
	      try
	      {
	    	  System.out.println("No implmentado");
	      }
	      catch (Exception nre)
	      {
	    	  log.severe(nre.getMessage());
	      }
	      if (paciente == null)
	      {
	         return Response.status(Status.NOT_FOUND).build();
	      }
	      return Response.ok(paciente).build();
	   }

	   @GET
	   @Produces("application/json")
	   public List<Paciente> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
	   {
		   List<Paciente> resultado=null;
		   try {
			resultado = pacienteService.listAllPacientes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return resultado;
	   }
	   public Response updatePacienteRest(Paciente entity)
	   {
	      try
	      {
	         entity = pacienteService.updatePaciente(entity);
	      }
	      catch (Exception e)
	      {
	         return Response.status(Response.Status.CONFLICT).entity(entity).build();
	      }
	      return Response.noContent().build();
	   }
	   
	   private List<PacienteRestBean> getListaPacientesRest(List<Paciente> pacientes)
	   {
	    	List<PacienteRestBean> resultadoRest = new ArrayList<PacienteRestBean>();
	    	PacienteRestBean temp = null;
	    	try
	    	{
	    		for (Paciente paciente : pacientes) {
					temp = new PacienteRestBean();
					temp.setNombre(paciente.getNombre());
					temp.setId(paciente.getId());
					temp.setEmail(paciente.getEmail());
					temp.setTelefono(paciente.getTelefono());
					temp.setVersion(paciente.getVersion());
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
