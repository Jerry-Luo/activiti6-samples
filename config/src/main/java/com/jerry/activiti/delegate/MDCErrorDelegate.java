package com.jerry.activiti.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MDCErrorDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(MDCErrorDelegate.class);

    @Override
    public void execute(DelegateExecution execution) {
        LOGGER.info("run MDCErrorDelegate");
        throw new RuntimeException("only for test");
    }
}
