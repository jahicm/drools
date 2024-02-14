package org.drools.demo.config;

import org.springframework.context.annotation.Configuration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = {"org.drools.demo"})
public class DroolsConfig {

	
    @Bean
    KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("com/example/rules/rules1.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("com/example/rules/rules2.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("com/example/rules/rules3.drl"));
        kieFileSystem.write(ResourceFactory.newClassPathResource("com/example/rules/student-rule-flow.xml"));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        return kieServices.newKieContainer(kieBuilder.getKieModule().getReleaseId());
    }

}
