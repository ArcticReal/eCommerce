package com.skytala.eCommerce.service.content;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.ServiceAuthException;
import org.apache.ofbiz.service.ServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/service/contentOutput")
public class ContentOutputServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/sendPrintFromScreen")
	public ResponseEntity<Map<String, Object>> sendPrintFromScreen(HttpSession session, @RequestParam(value="screenLocation") String screenLocation, @RequestParam(value="screenContext", required=false) Map screenContext, @RequestParam(value="printRequestAttributes", required=false) List printRequestAttributes, @RequestParam(value="printerName", required=false) String printerName, @RequestParam(value="docAttributes", required=false) List docAttributes, @RequestParam(value="contentType", required=false) String contentType, @RequestParam(value="printerContentType", required=false) String printerContentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("screenLocation",screenLocation);
		paramMap.put("screenContext",screenContext);
		paramMap.put("printRequestAttributes",printRequestAttributes);
		paramMap.put("printerName",printerName);
		paramMap.put("docAttributes",docAttributes);
		paramMap.put("contentType",contentType);
		paramMap.put("printerContentType",printerContentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("sendPrintFromScreen", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFileFromScreen")
	public ResponseEntity<Map<String, Object>> createFileFromScreen(HttpSession session, @RequestParam(value="fileName") String fileName, @RequestParam(value="screenLocation") String screenLocation, @RequestParam(value="screenContext", required=false) Map screenContext, @RequestParam(value="filePath", required=false) String filePath, @RequestParam(value="contentType", required=false) String contentType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fileName",fileName);
		paramMap.put("screenLocation",screenLocation);
		paramMap.put("screenContext",screenContext);
		paramMap.put("filePath",filePath);
		paramMap.put("contentType",contentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFileFromScreen", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}



}
