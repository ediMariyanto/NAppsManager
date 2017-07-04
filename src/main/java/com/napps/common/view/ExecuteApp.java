package com.napps.common.view;

import java.io.File;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.w3c.dom.Document;

import com.napps.common.bean.Holliday;
import com.napps.common.controller.HollidayController;

import org.apache.log4j.*;

@Configuration
@EnableScheduling
public class ExecuteApp
{
	private static org.apache.log4j.Logger log = Logger.getLogger(ExecuteApp.class);

	Holliday holl;
	HollidayController hollCtr;

	java.sql.Date myDate;

	public void runningJob()
	{
		try
		{

			File path = new File("D:/Kalacakra/NAppsManager/src/main/resources/configurationJob.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document document = docBuilder.parse(path);
			String job1 = document.getElementsByTagName("pathJob1").item(0).getTextContent();
			String job2 = document.getElementsByTagName("pathJob2").item(0).getTextContent();
			String job3 = document.getElementsByTagName("pathJob3").item(0).getTextContent();
			String job4 = document.getElementsByTagName("pathJob4").item(0).getTextContent();
			String job5 = document.getElementsByTagName("pathJob5").item(0).getTextContent();
			String job6 = document.getElementsByTagName("pathJob6").item(0).getTextContent();
			String job7 = document.getElementsByTagName("pathJob7").item(0).getTextContent();
			String job8 = document.getElementsByTagName("pathJob8").item(0).getTextContent();
			String job9 = document.getElementsByTagName("pathJob9").item(0).getTextContent();
			String job10 = document.getElementsByTagName("pathJob10").item(0).getTextContent();
			log.info("Job Starting......");
			if (job1.equals(""))
			{
				log.warn("Job1 empty !");
			} else
			{
				log.info("Job 1 Running ..... -> " + job1);
			}
			if (job2.equals(""))
			{
				log.warn("Job2 empty !");
			} else
			{
				log.info("Job 2 Running ..... -> " + job2);
			}
			if (job3.equals(""))
			{
				log.warn("Job3 empty !");
			} else
			{
				log.info("Job 3 Running ..... -> " + job3);
			}
			if (job4.equals(""))
			{
				log.warn("Job4 empty !");
			} else
			{
				log.info("Job 4 Running ..... -> " + job4);
			}
			if (job5.equals(""))
			{
				log.warn("Job5 empty !");
			} else
			{
				log.info("Job 5 Running ..... -> " + job5);
			}
			if (job6.equals(""))
			{
				log.warn("Job6 empty !");
			} else
			{
				log.info("Job 6 Running ..... -> " + job6);
			}
			if (job7.equals(""))
			{
				log.warn("Job7 empty !");
			} else
			{
				log.info("Job 7 Running ..... -> " + job7);
			}
			if (job8.equals(""))
			{
				log.warn("Job8 empty !");
			} else
			{
				log.info("Job 8 Running ..... -> " + job8);
			}
			if (job9.equals(""))
			{
				log.warn("Job9 empty !");
			} else
			{
				log.info("Job 9 Running ..... -> " + job9);
			}
			if (job10.equals(""))
			{
				log.warn("Job10 empty !");
			} else
			{
				log.info("Job 10 Running ..... -> " + job10);
			}

			log.info("Job Running......");

		} catch (Exception e)
		{
			log.error("Aplication Error " + e);
		}

	}

	@Scheduled(cron =" 05 00 00 * * ?")
	public void validationHolliday() throws Exception
	{		
		try
		{
			System.out.println("<==================================Starting=================================================>");
			log.info("Starting Validation Start Day Scheduler......");
			
			Date tgl = new Date();
			myDate = new java.sql.Date(tgl.getTime());
			holl = new Holliday();
			holl.sethDate(myDate);

			hollCtr = new HollidayController();
			String result = hollCtr.getValidationHolliday(holl);

			if (result.equals("HOLLIDAY"))
			{
				log.warn("Application can not be executed...!!! 'HOLLIDAY'");

			} else
			{
				new ClassPathXmlApplicationContext("file:D:/Kalacakra/NAppsManager/target/classes/configurationScheduler.xml");
				runningJob();
				log.info("application is running......");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			log.error("Validation Aplication Error " + e);
		}

	}

	public void executeApp()
	{
		int konfirmasi = JOptionPane.showConfirmDialog(null, "Scheduler Running Now ? ", "Running Scheduler",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (konfirmasi == JOptionPane.YES_OPTION)
		{
			
			log.info("Starting Scheduler......");
			try
			{
				log.info("Check Date from Database");
				validationHolliday();
			} catch (Exception e)
			{
				log.error("Application Error " + e);
			}
		}

	}

	@Scheduled(cron =" 00 00 00 * * ?")
	public void stopMethod()
	{
		try
		{
			Thread.sleep(5);
			log.warn("Stop Scheduller");
			System.out.println(
					"<==================================Stop===================================================>");
		} catch (InterruptedException e)

		{
			log.error(e);
		}

	}

	public void confirmStopApp()
	{
		int konfirmasi = JOptionPane.showConfirmDialog(null, "Are you sure close Scheduler? ", "Exit Scheduler",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (konfirmasi == JOptionPane.YES_OPTION)
		{
			ExecuteApp app = new ExecuteApp();
			app.stopMethod();

		}
	}

}
