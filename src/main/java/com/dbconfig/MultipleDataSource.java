package com.dbconfig;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class MultipleDataSource extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        Object result = ChooseDataSourceUtils.chooseDataSource();
        Boolean rs = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        System.out.println("MultipleDataSource:"+rs);
        return result;
    }
}
