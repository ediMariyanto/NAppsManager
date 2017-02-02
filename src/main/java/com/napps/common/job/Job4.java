package com.napps.common.job;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.w3c.dom.Document;

public class Job4 extends QuartzJobBean {

	private static org.apache.log4j.Logger log = Logger.getLogger(Job4.class);

	@Override
	protected void executeInternal(JobExecutionContext job)
			throws JobExecutionException {
		try {
			File path = new File("D:/Kalacakra/NAppsManager/target/classes/configurationJob.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document document = docBuilder.parse(path);
			String jobs = document.getElementsByTagName("pathJob4").item(0)
					.getTextContent();

			if (jobs.equals("")) {
				log.warn("'job4' is empty !");
			} else {
				Runtime r = Runtime.getRuntime();
				r.exec(jobs);

				log.info("'JOB 4' Running at : "
						+ job.getFireTime() + " \t"
						+ "- and next Running at : "
						+ job.getNextFireTime());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Configuration 'JOB4' not valid " + ex);
		}
	}

}
