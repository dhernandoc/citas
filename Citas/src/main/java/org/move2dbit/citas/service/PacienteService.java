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
package org.move2dbit.citas.service;

import java.util.List;

import javax.ejb.Local;

import org.move2dbit.citas.model.Paciente;
import org.move2dbit.citas.service.exception.CreatePacienteException;


@Local
public interface PacienteService {

	public void create(Paciente paciente) throws CreatePacienteException;
    public void delete(Long idPaciente) throws Exception;
	public List<Paciente> listAllPacientes() throws Exception;
	public Paciente updatePaciente(Paciente paciente);
    
}
