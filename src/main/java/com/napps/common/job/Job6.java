package com.napps.common.job;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.w3c.dom.Document;

public class Job6 extends QuartzJobBean {

	private static org.apache.log4j.Logger log = Logger.getLogger(Job6.class);

	@Override
	protected void executeInternal(JobExecutionContext job)
			throws JobExecutionException {
		try {
			File path = new File("D:/Kalacakra/NAppsManager/target/classes/configurationJob.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document document = docBuilder.parse(path);
			String jobs = document.getElementsByTagName("pathJob6").item(0)
					.getTextContent();

			if (jobs.equals("")) {
				log.warn("'job6' is empty !");
			} else {
				Runtime r = Runtime.getRuntime();
				r.exec(jobs);

				log.info("'JOB 6' Running at : "
						+ job.getFireTime() + " \t"
						+ "- and next Running at : "
						+ job.getNextFireTime());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Configuration 'JOB6' not valid " + ex);
		}
	}

}
