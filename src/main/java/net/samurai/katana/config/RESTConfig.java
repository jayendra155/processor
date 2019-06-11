package net.samurai.katana.config;

import net.samurai.katana.model.Product;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

/**
 * JayendraSingh
 */
public class RESTConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig) {
        restConfig.exposeIdsFor(Product.class);
        ExposureConfiguration config = restConfig.getExposureConfiguration();
        config.forDomainType(Product.class).withItemExposure((metdata, httpMethods) -> httpMethods.enable(HttpMethod.DELETE, HttpMethod.GET,
                HttpMethod.PUT, HttpMethod.POST, HttpMethod.PATCH));
    }
}
