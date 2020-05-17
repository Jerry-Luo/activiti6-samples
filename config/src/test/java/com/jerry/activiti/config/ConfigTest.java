package com.jerry.activiti.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigTest.class);

    @Test
    public void testConfig1(){
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResourceDefault();
        LOGGER.info("configuration = {}", cfg);
    }

    @Test
    public void testConfig2(){
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        LOGGER.info("configuration = {}", cfg);
    }
}
