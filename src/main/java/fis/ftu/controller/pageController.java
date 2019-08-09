package fis.ftu.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
public class pageController {
	@Autowired
	private DataSource dataSource;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}

	@GetMapping(value = "/answer_sheet_pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public void getImageWithMediaType(@PathVariable int id, HttpServletResponse response) throws IOException {
		String reportName = "answerSheet_" + new Date().getTime() + ".pdf";
		String reportTemplate = "D:\\archive\\file\\report\\AnswerSheet.jrxml";
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", reportName);
			fis = new FileInputStream(reportTemplate);
			os = new ByteArrayOutputStream();
			JasperReport jasperReport = JasperCompileManager.compileReport(fis);
			
			HashMap<String, Object> parameters = new HashMap<>();
			parameters.put("idanswer_sheet", new Integer(id));

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					dataSource.getConnection());
						
			//JasperExportManager.exportReportToPdfStream(jasperPrint, os);
			byte[] pdfByte = JasperExportManager.exportReportToPdf(jasperPrint);
			os = response.getOutputStream();
			os.write(pdfByte);
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
