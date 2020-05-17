package com.jerry.activiti.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigDBTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDBTest.class);

    @Test
    public void testConfig1(){
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResourceDefault();
        LOGGER.info("configuration = {}", cfg);

        ProcessEngine processEngine = cfg.buildProcessEngine();
        LOGGER.info("获取流程引擎 {}", processEngine.getName());
        processEngine.close();
    }

    @Test
    public void testConfig2(){
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.druid.xml");
        LOGGER.info("configuration = {}", cfg);

        ProcessEngine processEngine = cfg.buildProcessEngine();
        LOGGER.info("获取流程引擎 {}", processEngine.getName());
        processEngine.close();
    }

}
