package lk.icta.police.external.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import lk.icta.police.framework.constant.PoliceConstant;
import lk.icta.police.framework.utility.CommonUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import org.apache.log4j.Logger;

public class ReportFileExportUtil {

	
	private static final Logger LOGGER = Logger.getLogger(ReportFileExportUtil.class);
	private static ReportFileExportUtil instance = null;
	private static final String PROP_FILE_NAME = "police";
	
	/**
	 * Singleton Implementation
	 * 
	 */
	public static ReportFileExportUtil getInstance() {
		synchronized (ReportFileExportUtil.class) {
			if (instance == null) {
				instance = new ReportFileExportUtil();
			}
			return instance;
		}
	}
		

	/**
	 * @param type
	 * @param jp
	 * @param baos
	 * @param fileName without the extension
	 * @return
	 * @throws Exception
	 */
	public String export(String type, JasperPrint jp, ByteArrayOutputStream baos, String fileName)throws Exception {	
		LOGGER.info("type :" + type);
		String fullFilePath=null;		
		final String filePath = CommonUtil.getValueFromFile(PROP_FILE_NAME,"police.report.file.location");
		if (type.equalsIgnoreCase(PoliceConstant.FILE_TYPE_PDF)) {
			// Export to output stream
			exportPdf(jp, baos);			 
			// Create a new file
			// declare a custom filename
			fileName = fileName + ".pdf";
			new File(filePath).mkdirs();
			fullFilePath = filePath + fileName;
			LOGGER.info("fullFilePath - "+fullFilePath);
			File pdfFile = new File(fullFilePath);						
			// write data to a file and store
			OutputStream outputStream = new FileOutputStream(pdfFile); 
			baos.writeTo(outputStream);
			outputStream.close();
			baos.close();
		} else if(type.equalsIgnoreCase(PoliceConstant.FILE_TYPE_WORD)){			
			// Export to output stream
			exportWord(jp, baos);			 
			// Create a new file
			// declare a custom filename
			fileName = fileName + ".docx";
			fullFilePath = filePath + fileName;
			File pdfFile = new File(fullFilePath);			
			// write data to a file and store
			OutputStream outputStream = new FileOutputStream(pdfFile); 
			baos.writeTo(outputStream);
			outputStream.close();
			baos.close();			
		}else{			
			// Export to output stream
			exportCSV(jp, baos);			 
			// Create a new file
			// declare a custom filename
			fileName = fileName + ".csv";
			fullFilePath = filePath + fileName;
			File pdfFile = new File(fullFilePath);			
			// write data to a file and store
			OutputStream outputStream = new FileOutputStream(pdfFile); 
			baos.writeTo(outputStream);
			outputStream.close();
			baos.close();
		}
		return fileName;
	}
	
	public void exportPdf(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		// Create a JRXlsExporter instance
		JRPdfExporter exporter = new JRPdfExporter();		
		// Here assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();		
	}
	
	public void exportWord(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		// Create a JRXlsExporter instance
		JRDocxExporter exporter = new JRDocxExporter();
		// Here assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();		
	}
	
	public void exportCSV(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		// Create a JRXlsExporter instance
		JRCsvExporter exporter = new JRCsvExporter();
		// Here assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();
	}	
	
	private ReportFileExportUtil() {
		
	}
}
