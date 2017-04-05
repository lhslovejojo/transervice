package com.smurfs.console.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.HessianServiceExporter;

import com.blockchain.service.AccountService;
import com.blockchain.service.QueryService;
import com.blockchain.service.StudentAdd;
import com.blockchain.service.TxnService;
import com.smurfs.console.business.facade.AccountServiceFacade;
import com.smurfs.console.business.facade.StuTest;
import com.smurfs.console.business.facade.TxnServiceFacade;

@Configuration
public class WebConfig {
	@Autowired
    private AccountServiceFacade accountServiceFacade; 
	@Autowired
    private TxnServiceFacade txnServiceFacade; 
	@Autowired
    private QueryService queryService; 
	@Autowired
    private StuTest stuTest; 

	/**
	 * 账户接口调用hessian客户端
	 * @return
	 */
	@Bean
	public HessianProxyFactoryBean accountServiceClient() {
		HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
		factory.setServiceUrl("http://localhost:8080/remote/accountService");
		factory.setServiceInterface(AccountService.class);
		return factory;
	}
	/**
	 * 交易接口调用hessian客户端
	 * @return
	 */
	@Bean
	public HessianProxyFactoryBean txnServiceClient() {
		HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
		factory.setServiceUrl("http://localhost:8080/remote/txnService");
		factory.setServiceInterface(TxnService.class);
		return factory;
	}
	@Bean(name = "/remote/accountService") 
	public HessianServiceExporter accountService() {
		HessianServiceExporter exporter = new HessianServiceExporter();
		exporter.setService(accountServiceFacade);
		exporter.setServiceInterface(AccountService.class);
		return exporter;
	 } 
	@Bean(name = "/remote/txnService") 
	public HessianServiceExporter txnService() {
		HessianServiceExporter exporter = new HessianServiceExporter();
		exporter.setService(txnServiceFacade);
		exporter.setServiceInterface(TxnService.class);
		return exporter;
	 } 
	@Bean(name = "/remote/queryService") 
	public HessianServiceExporter queryService() {
		HessianServiceExporter exporter = new HessianServiceExporter();
		exporter.setService(queryService);
		exporter.setServiceInterface(QueryService.class);
		return exporter;
	 } 
	@Bean(name = "/remote/testSpring") 
	public HessianServiceExporter stuTest() {
		HessianServiceExporter exporter = new HessianServiceExporter();
		exporter.setService(stuTest);
		exporter.setServiceInterface(StudentAdd.class);
		return exporter;
	 } 


}
