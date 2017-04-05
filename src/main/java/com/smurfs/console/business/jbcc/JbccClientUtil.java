package com.smurfs.console.business.jbcc;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.smurfs.console.frame.exception.JbccServerException;
import com.tiandetech.jbcc.sll.client.JBCCClient;
import com.tiandetech.jbcc.sll.thrift.JBCCResult;

@Component
public class JbccClientUtil {
	public static Logger log = LoggerFactory.getLogger(JbccClientUtil.class);
	private JBCCClient jbccClient;
	@Value("${jbccHost}")
	private String jbccHost;

	public JBCCClient getJbccClient() {
		if (jbccClient == null) {
			reConnection();
		}
		return jbccClient;

	}

	public  synchronized void  reConnection() {
		if (jbccClient == null) {
			jbccClient = JBCCClient.getInstance();
		}
		try {
			log.info("jbccHost:"+jbccHost+"openSyncConnection");
			JBCCResult jBCCResult = jbccClient.openSyncConnection(jbccHost);
			log.info("jBCCResult:"+jBCCResult.toString());
			int i = 0;
			while (jBCCResult.status != 0 && i < 4) {
				jBCCResult = jbccClient.openSyncConnection(jbccHost);
				i++;
			}
			if (jBCCResult.status != 0) {
				jbccClient = null;
			}

		} catch (TException e) {
			log.error("jbccClient connection error", e);
			throw new JbccServerException(e);
		}

	}

}
