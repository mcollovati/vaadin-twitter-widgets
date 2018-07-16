/*
 * Copyright (C) 2016-2018 Marco Collovati (mcollovati@gmail.com)
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

import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.VaadinService;
import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
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

    public void setUpCurrentService() {
        Assert.assertNull(VaadinService.getCurrent());
        this.service = this.createService();
        VaadinService.setCurrent(this.service);
    }

    public void clearCurrentService() {
        VaadinService.setCurrent((VaadinService)null);
        this.service = null;
    }


    protected VaadinService createService() {
        DeploymentConfiguration configuration = Mockito.mock(DeploymentConfiguration.class);
        when(configuration.isProductionMode()).thenReturn(false);

        VaadinService service = Mockito.mock(VaadinService.class);
        when(service.getDeploymentConfiguration())
            .thenReturn(configuration);

        when(service.getResourceAsStream(ArgumentMatchers.startsWith("frontend://src/"), any(), any()))
            .then(i -> Tweet.class.getResourceAsStream(
                String.format("/META-INF/resources/%s", i.<String>getArgument(0).replace(":/", "")))
            );
        return service;
    }

}
