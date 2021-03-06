package com.dbconfig;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

public class MyDataSourceManager extends DataSourceTransactionManager {

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        boolean readOnly = definition.isReadOnly();
        System.out.println("MyDataSourceManager:"+readOnly);
        if(readOnly) {
            DynamicDataSourceContextHolder.setChooseType("slave");
        } else {
            DynamicDataSourceContextHolder.setChooseType("master");
        }

        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);

        DynamicDataSourceContextHolder.clean();
    }
}
