package com.example.systemproperty.bootstrap;

import com.example.systemproperty.SystemPropertyApplication;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

@Slf4j
public class SystemPropertiesBootstrap implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final String SYSTEM_PROPERTIES_JSON = "system-properties.json";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        log.info("Initializing system properties before load spring context.....");

        getSettings()
            .forEach((key, value) -> {
                log.info("{} = {}", key, value);
                System.setProperty(key, value);
            });

        log.info("Initialization completed");
    }

    private Map<String, String> getSettings() {
        try {
            var loader = SystemPropertyApplication.class.getClassLoader();
            var uri = Objects.requireNonNull(loader.getResource(SYSTEM_PROPERTIES_JSON)).toURI();
            var content = Files.readString(Paths.get(uri));
            return new ObjectMapper().readValue(content, new TypeReference<HashMap<String, String>>() {});
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
