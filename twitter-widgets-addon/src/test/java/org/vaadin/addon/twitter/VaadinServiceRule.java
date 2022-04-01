/*
 * Copyright (C) 2016-2022 Marco Collovati (mcollovati@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.addon.twitter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import com.helger.commons.io.resourceprovider.DefaultResourceProvider;
import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

import com.vaadin.flow.di.DefaultInstantiator;
import com.vaadin.flow.di.Lookup;
import com.vaadin.flow.di.ResourceProvider;
import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.ServiceException;
import com.vaadin.flow.server.VaadinContext;
import com.vaadin.flow.server.VaadinService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VaadinServiceRule implements TestRule {

    private VaadinService service;

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                setUpCurrentService();
                try {
                    base.evaluate();
                } finally {
                    clearCurrentService();
                }
            }

        };
    }

    public VaadinService getService() {
        return service;
    }

    public void setUpCurrentService() throws ServiceException {
        Assert.assertNull(VaadinService.getCurrent());
        this.service = this.createService();
        VaadinService.setCurrent(this.service);
    }

    public void clearCurrentService() {
        VaadinService.setCurrent((VaadinService) null);
        this.service = null;
    }

    protected VaadinService createService() throws ServiceException {
        DeploymentConfiguration configuration = mock(DeploymentConfiguration.class);
        when(configuration.isProductionMode()).thenReturn(false);

        VaadinContext vaadinContext = mock(VaadinContext.class);
        when(vaadinContext.getAttribute(Lookup.class)).thenReturn(Lookup.of(
                new MockResourceProvider(), ResourceProvider.class));

        VaadinService service = mock(VaadinService.class);
        when(service.getDeploymentConfiguration()).thenReturn(configuration);
        when(service.getInstantiator()).thenReturn(new DefaultInstantiator(service));
        when(service.getContext()).thenReturn(vaadinContext);
        return service;
    }

    private static class MockResourceProvider implements ResourceProvider {

        @Override
        public URL getApplicationResource(String path) {
            return MockResourceProvider.class.getResource(path);
        }

        @Override
        public List<URL> getApplicationResources(String path) throws IOException {
            return Collections.list(MockResourceProvider.class.getClassLoader().getResources(path));
        }

        @Override
        public URL getClientResource(String path) {
            return MockResourceProvider.class.getResource(path);
        }

        @Override
        public InputStream getClientResourceAsStream(String path) throws IOException {
            return MockResourceProvider.class.getResourceAsStream(path);
        }
    }

    /*
    protected VaadinService createService() throws ServiceException {
        //DeploymentConfiguration configuration = Mockito.mock(DeploymentConfiguration.class);
        //when(configuration.isProductionMode()).thenReturn(false);
        MockDeploymentConfiguration configuration = new MockDeploymentConfiguration();
        configuration.setProductionMode(true);
        configuration.setEnableDevServer(false);


        VaadinService service = new MockVaadinServletService(configuration) {
            @Override
            public Iterable<DependencyFilter> getDependencyFilters() {
                ArrayList<DependencyFilter> f = new ArrayList<>();
                super.getDependencyFilters().forEach(f::add);
                f.add((dependencies, filterContext) -> {
                    List<Dependency> copy = new ArrayList<>(dependencies);
                    copy.replaceAll(d -> {
                        if (d.getUrl().startsWith("./")) {
                            return new Dependency(d.getType(), d.getUrl().replaceFirst("^\\./", "META-INF/resources/frontend/"), d.getLoadMode());
                        } else {
                            return d;
                        }
                    });
                    return copy;
                });
                return f;
            }
        };
        service.init();


        //VaadinService service = Mockito.mock(VaadinService.class);
        //when(service.getDeploymentConfiguration())
        //    .thenReturn(configuration);

        /*
        when(service.getResourceAsStream(ArgumentMatchers.startsWith("frontend://src/"), any(), any()))
            .then(i -> Tweet.class.getResourceAsStream(
                String.format("/META-INF/resources/%s", i.<String>getArgument(0).replace(":/", "")))
            );

      * /
        return service;
    }
    */
}
