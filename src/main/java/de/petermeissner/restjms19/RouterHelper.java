package de.petermeissner.restjms19;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Helper class for Router that collects static methods
 */
public class RouterHelper {

    public static String getHTTPEndpoinst(Class<?> c) {
        List<String> endpoints = new ArrayList<String>();

        for (Method m : c.getMethods()) {

            // get annotations
            Annotation[] anot = m.getDeclaredAnnotations();

            // check for get/post annotations
            boolean has_get = Arrays.stream(anot).anyMatch(a -> a.annotationType().getName().equals("javax.ws.rs.GET"));
            boolean has_post = Arrays.stream(anot).anyMatch(a -> a.annotationType().getName().equals("javax.ws.rs.POST"));

            String http_method = "";
            if (has_get) {
                http_method = "GET";
            } else if (has_post) {
                http_method = "POST";
            } else {
                continue;
            }

            for (Annotation a : anot) {

                if (a.annotationType().getName().equals("javax.ws.rs.Path")) {
                    final String path = a.toString().split("=")[1].split("\\)")[0];
                    endpoints.add(http_method + ": <a href=\"/restjms19/api" + path + "\">" + path + "</a>");
                }
            }
        }

        Collections.sort(endpoints);
        return String.join("<br>\n", endpoints);
    }
}
