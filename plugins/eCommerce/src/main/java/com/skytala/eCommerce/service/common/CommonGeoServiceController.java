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
@RequestMapping("/service/commonGeo")
public class CommonGeoServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCountryCapital")
	public ResponseEntity<Map<String, Object>> deleteCountryCapital(HttpSession session, @RequestParam(value="countryCode") String countryCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryCode",countryCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCountryCapital", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCountryAddressFormat")
	public ResponseEntity<Map<String, Object>> deleteCountryAddressFormat(HttpSession session, @RequestParam(value="geoId") String geoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCountryAddressFormat", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCountryCapital")
	public ResponseEntity<Map<String, Object>> createCountryCapital(HttpSession session, @RequestParam(value="countryCode") String countryCode, @RequestParam(value="countryCapital", required=false) String countryCapital) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryCode",countryCode);
		paramMap.put("countryCapital",countryCapital);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCountryCapital", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCountryCode")
	public ResponseEntity<Map<String, Object>> createCountryCode(HttpSession session, @RequestParam(value="countryCode") String countryCode, @RequestParam(value="countryNumber", required=false) String countryNumber, @RequestParam(value="countryAbbr", required=false) String countryAbbr, @RequestParam(value="countryName", required=false) String countryName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryCode",countryCode);
		paramMap.put("countryNumber",countryNumber);
		paramMap.put("countryAbbr",countryAbbr);
		paramMap.put("countryName",countryName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCountryCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCountryCode")
	public ResponseEntity<Map<String, Object>> deleteCountryCode(HttpSession session, @RequestParam(value="countryCode") String countryCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryCode",countryCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCountryCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCountryTeleCode")
	public ResponseEntity<Map<String, Object>> createCountryTeleCode(HttpSession session, @RequestParam(value="countryCode") String countryCode, @RequestParam(value="teleCode", required=false) String teleCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryCode",countryCode);
		paramMap.put("teleCode",teleCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCountryTeleCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCountryTeleCode")
	public ResponseEntity<Map<String, Object>> updateCountryTeleCode(HttpSession session, @RequestParam(value="countryCode") String countryCode, @RequestParam(value="teleCode", required=false) String teleCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryCode",countryCode);
		paramMap.put("teleCode",teleCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCountryTeleCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCountryAddressFormat")
	public ResponseEntity<Map<String, Object>> createCountryAddressFormat(HttpSession session, @RequestParam(value="geoId") String geoId, @RequestParam(value="requirePostalCode", required=false) String requirePostalCode, @RequestParam(value="postalCodeRegex", required=false) String postalCodeRegex, @RequestParam(value="requireStateProvinceId", required=false) String requireStateProvinceId, @RequestParam(value="hasPostalCodeExt", required=false) String hasPostalCodeExt, @RequestParam(value="requirePostalCodeExt", required=false) String requirePostalCodeExt, @RequestParam(value="addressFormat", required=false) String addressFormat, @RequestParam(value="geoAssocTypeId", required=false) String geoAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("requirePostalCode",requirePostalCode);
		paramMap.put("postalCodeRegex",postalCodeRegex);
		paramMap.put("requireStateProvinceId",requireStateProvinceId);
		paramMap.put("hasPostalCodeExt",hasPostalCodeExt);
		paramMap.put("requirePostalCodeExt",requirePostalCodeExt);
		paramMap.put("addressFormat",addressFormat);
		paramMap.put("geoAssocTypeId",geoAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCountryAddressFormat", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCountryCapital")
	public ResponseEntity<Map<String, Object>> updateCountryCapital(HttpSession session, @RequestParam(value="countryCode") String countryCode, @RequestParam(value="countryCapital", required=false) String countryCapital) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryCode",countryCode);
		paramMap.put("countryCapital",countryCapital);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCountryCapital", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCountryCode")
	public ResponseEntity<Map<String, Object>> updateCountryCode(HttpSession session, @RequestParam(value="countryCode") String countryCode, @RequestParam(value="countryNumber", required=false) String countryNumber, @RequestParam(value="countryAbbr", required=false) String countryAbbr, @RequestParam(value="countryName", required=false) String countryName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryCode",countryCode);
		paramMap.put("countryNumber",countryNumber);
		paramMap.put("countryAbbr",countryAbbr);
		paramMap.put("countryName",countryName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCountryCode", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCountryAddressFormat")
	public ResponseEntity<Map<String, Object>> updateCountryAddressFormat(HttpSession session, @RequestParam(value="geoId") String geoId, @RequestParam(value="requirePostalCode", required=false) String requirePostalCode, @RequestParam(value="postalCodeRegex", required=false) String postalCodeRegex, @RequestParam(value="requireStateProvinceId", required=false) String requireStateProvinceId, @RequestParam(value="hasPostalCodeExt", required=false) String hasPostalCodeExt, @RequestParam(value="requirePostalCodeExt", required=false) String requirePostalCodeExt, @RequestParam(value="addressFormat", required=false) String addressFormat, @RequestParam(value="geoAssocTypeId", required=false) String geoAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("geoId",geoId);
		paramMap.put("requirePostalCode",requirePostalCode);
		paramMap.put("postalCodeRegex",postalCodeRegex);
		paramMap.put("requireStateProvinceId",requireStateProvinceId);
		paramMap.put("hasPostalCodeExt",hasPostalCodeExt);
		paramMap.put("requirePostalCodeExt",requirePostalCodeExt);
		paramMap.put("addressFormat",addressFormat);
		paramMap.put("geoAssocTypeId",geoAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCountryAddressFormat", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCountryTeleCode")
	public ResponseEntity<Map<String, Object>> deleteCountryTeleCode(HttpSession session, @RequestParam(value="countryCode") String countryCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("countryCode",countryCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCountryTeleCode", paramMap);
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
