package com.jerry.activiti.config;

import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConfigMDCTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigMDCTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti.mdc.cfg.xml");

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void test() {
        // activiti 只有流程以异常的时候才会记录 MDC
        LogMDC.setMDCEnabled(true);

        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        assertNotNull(processInstance);

        LOGGER.info("test test test");

        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        assertEquals("Activiti is awesome!", task.getName());

        activitiRule.getTaskService().complete(task.getId());
    }
}
