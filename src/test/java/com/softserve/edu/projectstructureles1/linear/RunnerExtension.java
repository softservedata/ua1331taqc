package com.softserve.edu.projectstructureles1.linear;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class RunnerExtension implements AfterTestExecutionCallback {
    
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Boolean isExecutionPresent = context.getExecutionException().isPresent();
        System.out.println("\t\t\t\tException.isPresent() = " + isExecutionPresent); //false - SUCCESS, true - FAILED
        System.out.println("\t\t\t\tTest context.getDisplayName(): "+ context.getDisplayName());
        //
        GreencityLinearTest.isTestSuccessful = !isExecutionPresent;
    }
}    