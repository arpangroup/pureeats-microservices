package com.arpangroup.gatewayserver;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SwaggerController {

    //    @Autowired
    //    private DiscoveryClient discoveryClient;

    // Services to exclude. You can modify this list as per your environment
    private static final List<String> KUBE_SERVICES = Arrays.asList("kubernetes", "kube-dns", "prometheus-kube-prometheus-kubelet");



//    @GetMapping("/v3/api-docs/swagger-config")
//    public Map<String, Object> swaggerConfig(ServerHttpRequest serverHttpRequest) throws URISyntaxException {
//        URI uri = serverHttpRequest.getURI();
//        String url = new URI(uri.getScheme(), uri.getAuthority(), null, null, null).toString();
//        Map<String, Object> swaggerConfig = new LinkedHashMap<>();
//        List<AbstractSwaggerUiConfigProperties.SwaggerUrl> swaggerUrls = new LinkedList<>();
//        //System.out.println("Services = " + discoveryClient.getServices());
////        discoveryClient.getServices().stream().filter(s -> !KUBE_SERVICES.contains(s)).forEach(serviceName ->
////                swaggerUrls.add(new AbstractSwaggerUiConfigProperties.SwaggerUrl(serviceName,
////                        url + "/" + serviceName + "/v3/api-docs")));
//        swaggerConfig.put("urls", swaggerUrls);
//        return swaggerConfig;
//    }

}
