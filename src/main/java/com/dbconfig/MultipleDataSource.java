package com.dbconfig;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class MultipleDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        Object result = DynamicDataSourceContextHolder.chooseDataSource();
        Boolean rs = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        System.out.println("MultipleDataSource:" + rs);
        DruidDataSource dataSource =  (DruidDataSource)this.resolveSpecifiedDataSource(result);
        if(dataSource.isKeepAlive()) return result;
        else return "master";
    }
}
