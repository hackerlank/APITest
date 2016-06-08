package com.toon.api.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IResultMap;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.toon.api.base.TestCase;
import com.toon.api.data.RunningData;
import com.toon.api.types.ReportType;

public class ReportCreator {

	/**创建发布的报告**/
	private String createPublishReport(Map<String, ISuiteResult> suiteResults){
		StringBuffer htmlString=new StringBuffer("");
		htmlString.append("<!DOCTYPE html>\r\n");
		htmlString.append("<html>\r\n");
		htmlString.append("<head>\r\n");
		htmlString.append("<meta charset=\"UTF-8\">\r\n");
		htmlString.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/result.css\">\r\n");
		htmlString.append("<title>\r\n");
		htmlString.append("Test Report");
		htmlString.append("</title>\r\n");
		/*htmlString.append("<script type=\"text/javascript\"></script>\r\n");*/
		htmlString.append("</head>\r\n");
		htmlString.append("<body>\r\n");
		htmlString.append("<div id=\"main\">\r\n");
		htmlString.append("<div id=\"result\">\r\n");
		htmlString.append("<div id=\"result_title\" class=\"title\">\r\n");
		htmlString.append("结果");
		htmlString.append("</div>\r\n");
		htmlString.append("<div id=\"result_content\">\r\n");
		htmlString.append("<table id=\"tb_result\" width=\"400\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\" class=\"gridtable\">\r\n");
		htmlString.append("<tr><th>TestName</th><th>Total</th><th>Skip</th><th>Pass</th><th>Failure</th><th>Pass Rate</th></tr>\r\n");
		
		NumberFormat formatter = new DecimalFormat("0.0");
		int total_pass=0;
		int total_skip=0;
		int total_fail=0;
		int total_total=0;
		for(ISuiteResult result:suiteResults.values()){			
			ITestContext content=result.getTestContext();
			IResultMap ss=content.getFailedTests();
			Set<ITestResult> dd=ss.getAllResults();
			for(ITestResult d:dd){
				System.out.println(Arrays.toString(d.getParameters()));
			}
			int pass=content.getPassedTests().size();
			int fail=content.getFailedTests().size();
			int skip=content.getSkippedTests().size();
			int total =pass+skip+fail;
			total_pass+=pass;
			total_skip+=skip;
			total_fail+=fail;
			total_total+=total;
			
			htmlString.append("<tr><td>"+content.getName()+"</td><td>"+total+"</td><td>"+skip+"</td><td>"+pass+"</td><td>"+fail+
					"</td><td>"+formatter.format((double)pass/(pass+skip+fail)*100)+"%</td>");
		}
		htmlString.append("<tr><td>Total</td><td>"+(total_total)+"</td><td>"+total_skip+"</td><td>"+total_pass+"</td><td>"+total_fail+
				"</td><td>"+formatter.format((double)total_pass/(total_total)*100)+"%</td>");
		
		htmlString.append("</table>\r\n");
		htmlString.append("</div>\r\n");
		htmlString.append("<div id==\"result_log\">\r\n");
		htmlString.append("<div id=\"log_title\" class=\"title\" style=\"margin-top:10px;\">\r\n");
		htmlString.append("测试日志");
		htmlString.append("</div>\r\n");
		htmlString.append("<!-- tab页显示结果-->\r\n");
		htmlString.append("<div class=\"wrap\">\r\n");
		htmlString.append("<div class=\"tabs\">\r\n");
		htmlString.append("<a href=\"#\" hidefocus=\"true\" class=\"active\">\r\n");
		htmlString.append("失败日志");
		htmlString.append("</a>\r\n");
		htmlString.append("<a href=\"#\" hidefocus=\"true\">\r\n");
		htmlString.append("成功日志");
		htmlString.append("</a>\r\n");
		htmlString.append("<a href=\"#\" hidefocus=\"true\">\r\n");
		htmlString.append("全部日志");
		htmlString.append("</a>\r\n");
		htmlString.append("</div>\r\n");
		htmlString.append("<div class=\"swiper-container\">\r\n");
		htmlString.append("<div class=\"swiper-wrapper\">\r\n");
		htmlString.append("<div class=\"swiper-slide\">\r\n");
		htmlString.append("<div class=\"content-slide\">\r\n");

		//加载失败日志
		htmlString.append("<table style=\"font-size:13px\">");
		int num=0;
		for(int i=0;i<RunningData.caseList.size();i++){
			TestCase testCase=RunningData.caseList.get(i);
			if(!testCase.flag){
				htmlString.append("<tr style=\"color:red;\">");
				htmlString.append("<td valign=\"top\" style=\"padding-top:10px;\">["+testCase.getRunTime()+
						"] ["+testCase.testName+"] ["+testCase.testCaseName+"]"+"</td>");
				StringBuffer sbFailLog=new StringBuffer("执行失败，以下为失败日志内容：</br>");
				for(String failLog:testCase.failLog){
					sbFailLog.append(failLog+"</br>");
				}
				htmlString.append("<td style=\"padding:10px 0px 0px 20px;\">"+sbFailLog.toString()+"</td>");
				htmlString.append("</tr>\r\n");
				num++;
			}
		}
		if(num==0){
			htmlString.append("无失败日志");
		}
		htmlString.append("</table>");
		htmlString.append("</div>\r\n");
		htmlString.append("</div>\r\n");
		htmlString.append("<div class=\"swiper-slide\">\r\n");
		htmlString.append("<div class=\"content-slide\">\r\n");
		htmlString.append("<table style=\"font-size:13px\">");
		for(int i=0;i<RunningData.caseList.size();i++){
			TestCase testCase=RunningData.caseList.get(i);
			if(testCase.flag){
				htmlString.append("<tr>");
				htmlString.append("<td valign=\"top\" style=\"padding-top:10px;\">["+testCase.getRunTime()+
						"] ["+testCase.testName+"] ["+testCase.testCaseName+"]"+"</td>");
				StringBuffer sbFailLog=new StringBuffer("");
				sbFailLog.append("<td style=\"padding:10px 0px 0px 20px;\">执行成功</td>");
				htmlString.append(sbFailLog.toString());
				htmlString.append("</tr>\r\n");
			}
		}
		htmlString.append("</table>");
		htmlString.append("</div>\r\n");
		htmlString.append("</div>\r\n");
		htmlString.append("<div class=\"swiper-slide\">\r\n");
		htmlString.append("<div class=\"content-slide\">\r\n");
		//全部日志
		htmlString.append("<table style=\"font-size:13px\">");
		for(int i=0;i<RunningData.caseList.size();i++){
			TestCase testCase=RunningData.caseList.get(i);
			StringBuffer sbFailLog=new StringBuffer("");			
			if(testCase.flag){
				htmlString.append("<tr>");
				htmlString.append("<td valign=\"top\" style=\"padding-top:10px;\">["+testCase.getRunTime()+
						"] ["+testCase.testName+"] ["+testCase.testCaseName+"]"+"</td>");
				sbFailLog.append("执行成功");
			}else{
				htmlString.append("<tr style=\"color:red\">");
				htmlString.append("<td valign=\"top\" style=\"padding-top:10px;\">["+testCase.getRunTime()+
						"] ["+testCase.testName+"] ["+testCase.testCaseName+"]"+"</td>");
				sbFailLog.append("执行失败，以下为失败日志内容：</br>");
				for(String failLog:testCase.failLog){
					sbFailLog.append(failLog+"</br>");
				}				
			}
			htmlString.append("<td style=\"padding:10px 0px 0px 20px;\">"+sbFailLog.toString()+"</td>");
			htmlString.append("</tr>\r\n");
		}
		htmlString.append("</table>");
		htmlString.append("</div>\r\n");
		htmlString.append("</div>\r\n");
		htmlString.append("</div>\r\n");
		htmlString.append("</div>\r\n");
		htmlString.append("</div>\r\n");
		htmlString.append("</div>\r\n");
		htmlString.append("</div>\r\n");
		htmlString.append("");
		htmlString.append("<script src=\"JS/tab_main.js\"></script>\r\n");
		htmlString.append("<script src=\"JS/tab.js\"></script>\r\n");
		htmlString.append("<script>\r\n");
		htmlString.append("var tabsSwiper = new Swiper('.swiper-container',{\r\n");
		htmlString.append("speed:500,\r\n");
		htmlString.append("onSlideChangeStart: function(){\r\n");
		htmlString.append("$(\".tabs .active\").removeClass('active');\r\n");
		htmlString.append("$(\".tabs a\").eq(tabsSwiper.activeIndex).addClass('active');\r\n");
		htmlString.append("}\r\n");
		htmlString.append("});\r\n");
		htmlString.append("$(\".tabs a\").on('touchstart mousedown',function(e){\r\n");
		htmlString.append("e.preventDefault()\r\n");
		htmlString.append("$(\".tabs .active\").removeClass('active');\r\n");
		htmlString.append("$(this).addClass('active');\r\n");
		htmlString.append("tabsSwiper.swipeTo($(this).index());\r\n");
		htmlString.append("});\r\n");
		htmlString.append("");
		htmlString.append("$(\".tabs a\").click(function(e){\r\n");
		htmlString.append("e.preventDefault();\r\n");
		htmlString.append("});\r\n");
		htmlString.append("</script>\r\n");
		htmlString.append("</body>\r\n");
		htmlString.append("</html>");
		return htmlString.toString();
	}
	
	/**创建email报告**/
	private String createEmailReport(Map<String, ISuiteResult> suiteResults){
		StringBuffer emailHtml=new StringBuffer("");
		emailHtml.append("<!DOCTYPE html>\r\n");
		emailHtml.append("<html>                                                                                                               ");
		emailHtml.append("<meta charset=\"UTF-8\">\r\n");
		emailHtml.append("<title>$$$$</title>                                                                                                  ");
		emailHtml.append("<head>                                                                                                               ");
		emailHtml.append("<style>                                                                                                              ");
		emailHtml.append("	html,body{                                                                                                         ");
		emailHtml.append("		margin:0px;                                                                                                    ");
		emailHtml.append("		padding:0px;                                                                                                   ");
		emailHtml.append("		width:100%;                                                                                                    ");
		emailHtml.append("		height:100%;                                                                                                   ");
		emailHtml.append("	}                                                                                                                  ");
		emailHtml.append("                                                                                                                     ");
		emailHtml.append("	table.gridtable {                                                                                                  ");
		emailHtml.append("		font-family: 'Microsoft YaHei';                                                                                ");
		emailHtml.append("		font-size:13px;                                                                                                ");
		emailHtml.append("		color:#333333;                                                                                                 ");
		emailHtml.append("		border-width: 1px;                                                                                             ");
		emailHtml.append("		border-color: #666666;                                                                                         ");
		emailHtml.append("		border-collapse: collapse;                                                                                     ");
		emailHtml.append("		margin-left: 20px;                                                                                             ");
		emailHtml.append("		margin-top: 10px;                                                                                              ");
		emailHtml.append("	}                                                                                                                  ");
		emailHtml.append("	                                                                                                                   ");
		emailHtml.append("	table.gridtable th {                                                                                               ");
		emailHtml.append("		border-width: 1px;                                                                                             ");
		emailHtml.append("		padding: 8px;                                                                                                  ");
		emailHtml.append("		border-style: solid;                                                                                           ");
		emailHtml.append("		border-color: #666666;                                                                                         ");
		emailHtml.append("		background-color: #3992d0;                                                                                     ");
		emailHtml.append("	}                                                                                                                  ");
		emailHtml.append("	                                                                                                                   ");
		emailHtml.append("	table.gridtable td {                                                                                               ");
		emailHtml.append("		border-width: 1px;                                                                                             ");
		emailHtml.append("		padding: 8px;                                                                                                  ");
		emailHtml.append("		border-style: solid;                                                                                           ");
		emailHtml.append("		border-color: #666666;                                                                                         ");
		emailHtml.append("		background-color: #ffffff;                                                                                     ");
		emailHtml.append("	}                                                                                                                  ");
		emailHtml.append("                                                                                                                     ");
		emailHtml.append("	.title{                                                                                                            ");
		emailHtml.append("			height:30px;                                                                                               ");
		emailHtml.append("			background: #3992d0;                                                                                       ");
		emailHtml.append("			font-family:'Microsoft YaHei';                                                                             ");
		emailHtml.append("	        font-size:14px;                                                                                            ");
		emailHtml.append("	        color:white;                                                                                               ");
		emailHtml.append("			line-height:30px;                                                                                          ");
		emailHtml.append("			text-align:left;                                                                                           ");
		emailHtml.append("			padding-left:20px;                                                                                         ");
		emailHtml.append("			font-weight:800;                                                                                           ");
		emailHtml.append("	}                                                                                                                  ");
		emailHtml.append("	                                                                                                                   ");
		emailHtml.append("	.logtable{                                                                                                         ");
		emailHtml.append("			 font-size:13px;                                                                                           ");
//		emailHtml.append("			 color:red;                                                                                                ");
		emailHtml.append("			 padding-top:10px;                                                                                         ");
		emailHtml.append("	}                                                                                                                  ");
		emailHtml.append("	                                                                                                                   ");
		emailHtml.append("	.logheadtd{                                                                                                        ");
		emailHtml.append("			 padding-top:10px; color:red;                                                                                        ");
		emailHtml.append("	}                                                                                                                  ");
		emailHtml.append("	                                                                                                                   ");
		emailHtml.append("	.logcontenttd{                                                                                                     ");
		emailHtml.append("			 padding:10px 0px 0px 20px; color:red;                                                                               ");
		emailHtml.append("	}                                                                                                                  ");
		emailHtml.append("	#result_content{                                                                                                     ");
		emailHtml.append("			 margin-top:10px;                                                                               ");
		emailHtml.append("	}                                                                                                                  ");
		
		
		emailHtml.append("</style>                                                                                                             ");
		emailHtml.append("                                                                                                                     ");
		emailHtml.append("</head>                                                                                                              ");
		emailHtml.append("<body>                                                                                                               ");
		emailHtml.append("<div style=\"margin:10px;\">请点击<a style=\"margin-left:5px;margin-right:5px;\" href=\""+Config.jenkinsReportPath+"\">详细测试报告</a>查看更为详细的测试报告结果</div>");
		emailHtml.append("    <div id=\"result_title\" class=\"title\">结果</div>                                                              ");
		emailHtml.append("    <div id=\"result_content\">                                                                                      ");
		emailHtml.append("        <table id=\"tb_result\" width=\"400\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\" class=\"gridtable\">  ");
		emailHtml.append("            <tr>                                                                                                     ");
		emailHtml.append("                <th>TestName</th>                                                                                    ");
		emailHtml.append("                <th>Total</th>                                                                                       ");
		emailHtml.append("                <th>Skip</th>                                                                                        ");
		emailHtml.append("                <th>Pass</th>                                                                                        ");
		emailHtml.append("                <th>Failure</th>                                                                                     ");
		emailHtml.append("                <th>Pass Rate</th>                                                                                   ");
		emailHtml.append("            </tr>                                                                                                    ");	
		NumberFormat formatter = new DecimalFormat("0.0");
		int total_pass=0;
		int total_skip=0;
		int total_fail=0;
		int total_total=0;
		for(ISuiteResult result:suiteResults.values()){
			ITestContext content=result.getTestContext();
			int pass=content.getPassedTests().size();
			int fail=content.getFailedTests().size();
			int skip=content.getSkippedTests().size();
			int total =pass+skip+fail;
			total_pass+=pass;
			total_skip+=skip;
			total_fail+=fail;
			total_total+=total;
			
			emailHtml.append("<tr><td>"+content.getName()+"</td><td>"+total+"</td><td>"+skip+"</td><td>"+pass+"</td><td>"+fail+
					"</td><td>"+formatter.format((double)pass/(pass+skip+fail)*100)+"%</td>");
		}
		emailHtml.append("<tr><td>Total</td><td>"+(total_total)+"</td><td>"+total_skip+"</td><td>"+total_pass+"</td><td>"+total_fail+
				"</td><td>"+formatter.format((double)total_pass/(total_total)*100)+"%</td>");
		emailHtml.append("         </table>                                                                                                    ");
		emailHtml.append("      </div>                                                                                                         ");
		emailHtml.append("                                                                                                                     ");
		emailHtml.append("      <div id=\"result_log\">                                                                                        ");
		emailHtml.append("          <div id=\"log_title\" class=\"title\" style=\"margin-top:10px;\">执行日志</div>                            ");
		emailHtml.append("          <div style=\"margin-left:20px;\">                                                                          ");
		emailHtml.append("          	<table class=\"logtable\">                                                                             ");	
		int num=0;
		for(int i=0;i<RunningData.caseList.size();i++){
			TestCase testCase=RunningData.caseList.get(i);
			if(!testCase.flag){
				emailHtml.append("<tr>");
				emailHtml.append("<td valign=\"top\" class=\"logheadtd\">["+testCase.getRunTime()+
						"] ["+testCase.testName+"] ["+testCase.testCaseName+"]"+"</td>");
				StringBuffer sbFailLog=new StringBuffer("执行失败，以下为失败日志内容：</br>");
				for(String failLog:testCase.failLog){
					sbFailLog.append(failLog+"</br>");
				}
				emailHtml.append("<td class=\"logcontenttd\">"+sbFailLog.toString()+"</td>");
				emailHtml.append("</tr>\r\n");
				num++;
			}else{
				emailHtml.append("<tr>");
				emailHtml.append("<td valign=\"top\" sytle=\"padding-top:10px;\">["+testCase.getRunTime()+
						"] ["+testCase.testName+"] ["+testCase.testCaseName+"]"+"</td>");
				emailHtml.append("<td style=\"padding:10px 0px 0px 20px;\">执行通过</td>");
				emailHtml.append("</tr>\r\n");
				num++;
			}
		}
		if(num==0){
			emailHtml.append("无");
		}
		emailHtml.append("          	</table>                                                                                               ");
		emailHtml.append("          </div>                                                                                                     ");
		emailHtml.append("      </div>                                                                                                         ");
		emailHtml.append("</body>                                                                                                              ");
		emailHtml.append("</html>                                                                                                              ");
		
		return emailHtml.toString();
	}
	
	/**创建测试报告
	 * @param reportType 报告类型，详见ReportType接口中的类型设置
	 * @return html字符串**/
	public String createReport(String reportType,Map<String, ISuiteResult> suiteResults){
		String reportString="";
		switch(reportType){
			case ReportType.Publish_Report:
				reportString=this.createPublishReport(suiteResults);
				break;
			case ReportType.Email_Report:
				reportString=this.createEmailReport(suiteResults);
				break;
		}
		return reportString;
	}
}
