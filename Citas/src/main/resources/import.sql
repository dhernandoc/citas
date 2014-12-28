--
-- JBoss, Home of Professional Open Source
-- Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements

insert into Member (id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212') 
insert into Paciente (id, nombre, email, telefono,version) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212',0); 
insert into Profesional (id, nombre,version) values (0, 'Perico Perez',0); 
insert into Cita (id, fecha, horaInicio, horafin, idprofesional_id,idpaciente_id,version) values (0, '12-12-2014', '10:00','11:00',0,0,0);
--insert into Cita (id, fecha, horaInicio, horafin, idPaciente, idProfesional) values (0, '12-12-2014', '10:00','11:00', 0,0)

