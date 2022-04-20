package com.hashan.nagp.adminservice.util;

import com.hashan.nagp.adminservice.exceptions.UriBuilderException;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Component
public class UrlGenerator {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;


    public URI generateUri(String serviceName, String path) throws UriBuilderException {
        Application currentService = eurekaClient.getApplication(serviceName);

        if (currentService == null) {
            throw new UriBuilderException("Unable to find " + serviceName + " in service registry.");
        }

        List<InstanceInfo> instances = currentService.getInstances();

        if (CollectionUtils.isEmpty(instances)) {
            throw new UriBuilderException("Unable to find " + serviceName + " instances");
        }

        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(instances.get(0).getHomePageUrl());
        } catch (URISyntaxException e) {
            throw new UriBuilderException("Error occurred when trying to build the URI", e);
        }

        if (path != null) {
            uriBuilder.setPath(path);
        }

        URI uri = null;

        try {
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new UriBuilderException("Error occurred when trying to build the URI", e);
        }
    }
}
