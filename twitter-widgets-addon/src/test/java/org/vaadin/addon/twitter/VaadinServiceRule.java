/*
 * Copyright (C) 2016-2020 Marco Collovati (mcollovati@gmail.com)
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

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.server.DependencyFilter;
import com.vaadin.flow.server.MockVaadinServletService;
import com.vaadin.flow.server.ServiceException;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.shared.ui.Dependency;
import com.vaadin.tests.util.MockDeploymentConfiguration;
import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

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
            );*/
        return service;
    }

}
