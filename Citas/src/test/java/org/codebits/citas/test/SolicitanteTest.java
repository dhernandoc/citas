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
package org.codebits.citas.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.move2dbit.citas.model.Solicitante;
import org.move2dbit.citas.service.impl.SolicitanteServiceImpl;
import org.move2dbit.citas.util.Resources;


@RunWith(Arquillian.class)
public class SolicitanteTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Solicitante.class, SolicitanteServiceImpl.class, Resources.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("META-INF/test-ds.xml");
    }

    @Inject
    SolicitanteServiceImpl solicitanteServiceImpl;

    @Inject
    Logger log;

    @Test
    public void testCreateSolicitante() throws Exception {
        Solicitante solicitante = new Solicitante();
        solicitante.setNombre("Uno");
        long id = 0;
        solicitante.setId(id);
        solicitante.setEmail("daniel.hernando@gmail.com");
        solicitante.setTelefono("916871073");
        solicitante.setVersion(0);
        solicitanteServiceImpl.create(solicitante);
        assertNotNull(solicitante.getId());
        log.info(solicitante.toString());
    }

}
