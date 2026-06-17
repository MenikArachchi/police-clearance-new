package lk.icta.police.business;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.vo.PoliceApplicationIssueReportVO;
import lk.icta.police.util.ReportFileExportUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class PoliceReportBusinessTest {
	

	@Test
	public void testGetPoliceApplicationIssuedReport() {
		try {
			
			List<PoliceApplicationIssueReportVO> list=PoliceReportBusiness.getInstance().getPoliceApplicationIssuedReport(null, null);
				
			// 1. Add report parameters			
			JRDataSource reportData = new JRBeanCollectionDataSource(list);
			// 1. Add report parameters
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			
			// 2.  Retrieve template
			InputStream reportStream = this.getClass().getResourceAsStream(PoliceConstant.APPLICATION_LIST_STATUS_REPORT); 			
			// 3. Convert template to JasperDesign
			JasperDesign jd = JRXmlLoader.load(reportStream);			
			// 4. Compile design to JasperReport
			JasperReport jr = JasperCompileManager.compileReport(jd);			
			// 5. Create the JasperPrint object
			// Make sure to pass the JasperReport, report parameters, and data source
			JasperPrint jp = JasperFillManager.fillReport(jr, params, reportData);			
			// 6. Create an output byte stream where data will be written
			ByteArrayOutputStream baos = new ByteArrayOutputStream();	
			
			String fileType=PoliceConstant.FILE_TYPE_EXCEL;
			
			String fileName="report_" + System.currentTimeMillis();
			try {
				fileName = ReportFileExportUtil.getInstance().export(fileType, jp, baos,fileName);
				if(StringUtils.isNotEmpty(fileName)){
					System.out.println("Status report was generated successfully!" + fileName);
				}else{
					System.out.println("Could not generate the status report. Internal Server Error!");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} catch (JRException e1) {
			e1.printStackTrace();
		}
	
	}

}
