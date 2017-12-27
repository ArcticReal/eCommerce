package com.skytala.eCommerce.service.common;

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
@RequestMapping("/service/commonQrcode")
public class CommonQrcodeServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/generateQRCodeImage")
	public ResponseEntity<Map<String, Object>> generateQRCodeImage(HttpSession session, @RequestParam(value="message") String message, @RequestParam(value="logoImage", required=false) java.awt.image.BufferedImage logoImage, @RequestParam(value="logoImageMaxWidth", required=false) Integer logoImageMaxWidth, @RequestParam(value="logoImageMaxHeight", required=false) Integer logoImageMaxHeight, @RequestParam(value="format", required=false) String format, @RequestParam(value="width", required=false) Integer width, @RequestParam(value="verifyOutput", required=false) Boolean verifyOutput, @RequestParam(value="encoding", required=false) String encoding, @RequestParam(value="height", required=false) Integer height) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("message",message);
		paramMap.put("logoImage",logoImage);
		paramMap.put("logoImageMaxWidth",logoImageMaxWidth);
		paramMap.put("logoImageMaxHeight",logoImageMaxHeight);
		paramMap.put("format",format);
		paramMap.put("width",width);
		paramMap.put("verifyOutput",verifyOutput);
		paramMap.put("encoding",encoding);
		paramMap.put("height",height);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("generateQRCodeImage", paramMap);
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
