package com.dbconfig;

import com.dbconfig.ChooseDataSourceUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import javax.sql.DataSource;

public class MyDataSourceManager extends DataSourceTransactionManager {

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        boolean readOnly = definition.isReadOnly();
        System.out.println("MyDataSourceManager:"+readOnly);
        if(readOnly) {
            ChooseDataSourceUtils.setChooseType("slave");
        } else {
            ChooseDataSourceUtils.setChooseType("master");
        }

        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);

        ChooseDataSourceUtils.clean();
    }
}
