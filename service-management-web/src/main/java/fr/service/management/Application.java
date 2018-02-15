/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management;


import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.apache.jasper.runtime.TldScanner;
import org.eclipse.jetty.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ReflectionUtils;
/**
 *
 * @author zouhairhajji
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    
    private static final Pattern jspEntryMatcher = Pattern.compile(".*[.](jsp|tag)");
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    
    
    @Bean
    public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory() {
        clearJspSystemUris();
        JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory = new JettyEmbeddedServletContainerFactory();
        jettyEmbeddedServletContainerFactory.addServerCustomizers(new JettyServerCustomizer() {
            @Override
            public void customize(Server server) {
                org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
                classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");

            }
        });
        return jettyEmbeddedServletContainerFactory;
    }

    

    private void clearJspSystemUris() {
        Field systemUris = ReflectionUtils.findField(TldScanner.class, "systemUris");
        systemUris.setAccessible(true);
        ReflectionUtils.setField(systemUris, null, new HashSet<String>());
    }
    private Predicate<String> jspZipEntry() {
        return new Predicate<String>() {
            @Override
            public boolean test(String zipEntry) {
                return jspEntryMatcher.matcher(zipEntry).matches();
            }
        };
    }

    private String substringBefore(String string, String delimiter) {
        int delimiterIndex = string.indexOf(delimiter);
        return delimiterIndex != -1 ? string.substring(0, delimiterIndex) : null;
    }
}
