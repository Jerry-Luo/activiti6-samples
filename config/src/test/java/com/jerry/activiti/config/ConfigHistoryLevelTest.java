package com.jerry.activiti.config;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConfigHistoryLevelTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHistoryLevelTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti.history.cfg.xml");

    @Test
    @Deployment(resources = {"my-process.bpmn20.xml"})
    public void test() {

        // 启动流程
        startProcess();

        // 修改变量
        changeVariable();

        // 提交表单 task
        submitForm();

        // 输出历史内容
        // 输出历史活动
        printOutHistory();

        // 输出历史变量
        printOutHistoryVariable();

        // 输出历史表单
        printOutHisForm();

        // 输出历史详情
        printOutHisDetails();
    }

    private void printOutHisDetails() {
        List<HistoricDetail> historicDetails = activitiRule.getHistoryService().createHistoricDetailQuery().listPage(0, 100);
        historicDetails.forEach(historicDetail -> LOGGER.info("historicDetail = {}", toString(historicDetail)));
        LOGGER.info("historicDetails.size() = {}", historicDetails.size());
    }

    private void printOutHisForm() {
        List<HistoricDetail> historicDetailsForms = activitiRule.getHistoryService().createHistoricDetailQuery().formProperties().listPage(0, 100);
        historicDetailsForms.forEach(historicDetail -> LOGGER.info("historicDetailsForm = {}", toString(historicDetail)));
        LOGGER.info("historicDetailsForms.size() = {}", historicDetailsForms.size());
    }

    private void printOutHistoryVariable() {
        List<HistoricVariableInstance> historicVariableInstances = activitiRule.getHistoryService().createHistoricVariableInstanceQuery().listPage(0, 100);
        historicVariableInstances.forEach(historicVariableInstance -> LOGGER.info("historicVariableInstance = {}", historicVariableInstance));
        LOGGER.info("historicVariableInstances.size() = {}", historicVariableInstances.size());
    }

    private void printOutHistory() {
        List<HistoricActivityInstance> historicActivityInstances = activitiRule.getHistoryService().createHistoricActivityInstanceQuery().listPage(0, 100);
        historicActivityInstances.forEach(historicActivityInstance -> LOGGER.info("historicActivityInstance = {}", historicActivityInstance));
        LOGGER.info("historicActivityInstances.size() = {}", historicActivityInstances.size());

        List<HistoricTaskInstance> historicTaskInstances = activitiRule.getHistoryService().createHistoricTaskInstanceQuery().listPage(0, 100);
        historicTaskInstances.forEach(historicTaskInstance -> LOGGER.info("historicTaskInstance = {}", historicTaskInstance));
        LOGGER.info("historicTaskInstances.size() = {}", historicTaskInstances.size());
    }

    private void submitForm() {
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        Map<String, String> properties = new HashMap<>();
        properties.put("formkey1", "valuef1");
        properties.put("formkey2", "valuef2");
        activitiRule.getFormService().submitTaskFormData(task.getId(), properties);
    }

    private void changeVariable() {
        List<Execution> executions = activitiRule.getRuntimeService().createExecutionQuery().listPage(0, 100);
        executions.forEach(execution -> LOGGER.info("execution = {}", execution));
        LOGGER.info("execution size = {}", executions.size());
        String id = executions.iterator().next().getId();
        activitiRule.getRuntimeService().setVariable(id, "keyStart1", "value1_");
    }

    private void startProcess() {
        Map<String, Object> params = new HashMap<>();
        params.put("keyStart1", "value1");
        params.put("keyStart2", "value2");
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", params);
    }

    static String toString (HistoricDetail historicDetail) {
        return ToStringBuilder.reflectionToString(historicDetail, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
