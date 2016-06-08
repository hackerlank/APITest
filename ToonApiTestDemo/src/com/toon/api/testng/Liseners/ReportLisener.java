package com.toon.api.testng.Liseners;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.xml.XmlSuite;
import com.toon.api.types.ReportType;
import com.toon.api.utils.Config;
import com.toon.api.utils.Log;
import com.toon.api.utils.Mail;
import com.toon.api.utils.ReportCreator;

public class ReportLisener implements IReporter{
	
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String IReporter) {
		
		for(ISuite suite:suites){
			Log.logInfo(suite.getName());
			 Map<String, ISuiteResult> suiteResults = suite.getResults();
			 ReportCreator rc=new ReportCreator();
			 String htmlString=rc.createReport(ReportType.Publish_Report, suiteResults);
			 String emailString=rc.createReport(ReportType.Email_Report, suiteResults);
			 try{
			 FileOutputStream fos=new FileOutputStream("ReportTemplate/out.html");
			OutputStreamWriter pw = new OutputStreamWriter(fos,"UTF-8");
			pw.write(htmlString.toString());
			pw.close();
			fos.close();
			 }catch(Exception ex){
				 Log.logInfo(ex.getMessage());
			 }
			 //·¢ËÍÓÊ¼þ
			 if(Config.isSendEmail){
				 Mail.send(emailString);
			 }
		}
	}

}
