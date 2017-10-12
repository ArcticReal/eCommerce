package com.skytala.eCommerce.service;

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

@RestController
@RequestMapping("/service/OFBizWebToolsController")
public class OFBizWebToolsServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/entityImportDir")
	public ResponseEntity<Object> entityImportDir(HttpSession session, @RequestParam(value="path", required=false) String path, @RequestParam(value="deleteFiles", required=false) String deleteFiles, @RequestParam(value="placeholderValues", required=false) java.util.Map placeholderValues, @RequestParam(value="checkDataOnly", required=false) String checkDataOnly, @RequestParam(value="filePause", required=false) Long filePause, @RequestParam(value="createDummyFks", required=false) String createDummyFks, @RequestParam(value="maintainTimeStamps", required=false) String maintainTimeStamps, @RequestParam(value="txTimeout", required=false) Integer txTimeout, @RequestParam(value="mostlyInserts", required=false) String mostlyInserts) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("path",path);
		paramMap.put("deleteFiles",deleteFiles);
		paramMap.put("placeholderValues",placeholderValues);
		paramMap.put("checkDataOnly",checkDataOnly);
		paramMap.put("filePause",filePause);
		paramMap.put("createDummyFks",createDummyFks);
		paramMap.put("maintainTimeStamps",maintainTimeStamps);
		paramMap.put("txTimeout",txTimeout);
		paramMap.put("mostlyInserts",mostlyInserts);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("entityImportDir", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/exportServiceEoModelBundle")
	public ResponseEntity<Object> exportServiceEoModelBundle(HttpSession session, @RequestParam(value="eomodeldFullPath") java.lang.String eomodeldFullPath, @RequestParam(value="serviceName") java.lang.String serviceName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eomodeldFullPath",eomodeldFullPath);
		paramMap.put("serviceName",serviceName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("exportServiceEoModelBundle", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/saveLabelsToXmlFile")
	public ResponseEntity<Object> saveLabelsToXmlFile(HttpSession session, @RequestParam(value="fileName") String fileName, @RequestParam(value="update_label") String update_label, @RequestParam(value="confirm", required=false) String confirm, @RequestParam(value="localeNames", required=false) List localeNames, @RequestParam(value="localeValues", required=false) List localeValues, @RequestParam(value="localeComments", required=false) List localeComments, @RequestParam(value="removeLabel", required=false) String removeLabel, @RequestParam(value="key", required=false) String key, @RequestParam(value="keyComment", required=false) String keyComment) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fileName",fileName);
		paramMap.put("update_label",update_label);
		paramMap.put("confirm",confirm);
		paramMap.put("localeNames",localeNames);
		paramMap.put("localeValues",localeValues);
		paramMap.put("localeComments",localeComments);
		paramMap.put("removeLabel",removeLabel);
		paramMap.put("key",key);
		paramMap.put("keyComment",keyComment);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("saveLabelsToXmlFile", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/entityImportReaders")
	public ResponseEntity<Object> entityImportReaders(HttpSession session, @RequestParam(value="overrideDelegator", required=false) String overrideDelegator, @RequestParam(value="checkDataOnly", required=false) String checkDataOnly, @RequestParam(value="readers", required=false) String readers, @RequestParam(value="createDummyFks", required=false) String createDummyFks, @RequestParam(value="maintainTimeStamps", required=false) String maintainTimeStamps, @RequestParam(value="txTimeout", required=false) Integer txTimeout, @RequestParam(value="mostlyInserts", required=false) String mostlyInserts, @RequestParam(value="overrideGroup", required=false) String overrideGroup) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("overrideDelegator",overrideDelegator);
		paramMap.put("checkDataOnly",checkDataOnly);
		paramMap.put("readers",readers);
		paramMap.put("createDummyFks",createDummyFks);
		paramMap.put("maintainTimeStamps",maintainTimeStamps);
		paramMap.put("txTimeout",txTimeout);
		paramMap.put("mostlyInserts",mostlyInserts);
		paramMap.put("overrideGroup",overrideGroup);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("entityImportReaders", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/entityExportAll")
	public ResponseEntity<Object> entityExportAll(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="outpath", required=false) String outpath, @RequestParam(value="txTimeout", required=false) Integer txTimeout) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("outpath",outpath);
		paramMap.put("txTimeout",txTimeout);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("entityExportAll", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/entityMaintPermCheck")
	public ResponseEntity<Object> entityMaintPermCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("entityMaintPermCheck", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/parseEntityXmlFile")
	public ResponseEntity<Object> parseEntityXmlFile(HttpSession session, @RequestParam(value="xmltext", required=false) String xmltext, @RequestParam(value="placeholderValues", required=false) java.util.Map placeholderValues, @RequestParam(value="checkDataOnly", required=false) String checkDataOnly, @RequestParam(value="createDummyFks", required=false) String createDummyFks, @RequestParam(value="maintainTimeStamps", required=false) String maintainTimeStamps, @RequestParam(value="txTimeout", required=false) Integer txTimeout, @RequestParam(value="mostlyInserts", required=false) String mostlyInserts, @RequestParam(value="url", required=false) java.net.URL url) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("xmltext",xmltext);
		paramMap.put("placeholderValues",placeholderValues);
		paramMap.put("checkDataOnly",checkDataOnly);
		paramMap.put("createDummyFks",createDummyFks);
		paramMap.put("maintainTimeStamps",maintainTimeStamps);
		paramMap.put("txTimeout",txTimeout);
		paramMap.put("mostlyInserts",mostlyInserts);
		paramMap.put("url",url);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("parseEntityXmlFile", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getEntityRefData")
	public ResponseEntity<Object> getEntityRefData(HttpSession session) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getEntityRefData", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/exportEntityEoModelBundle")
	public ResponseEntity<Object> exportEntityEoModelBundle(HttpSession session, @RequestParam(value="eomodeldFullPath") java.lang.String eomodeldFullPath, @RequestParam(value="entityNamePrefix", required=false) java.lang.String entityNamePrefix, @RequestParam(value="entityGroupId", required=false) java.lang.String entityGroupId, @RequestParam(value="datasourceName", required=false) java.lang.String datasourceName, @RequestParam(value="entityPackageName", required=false) java.lang.String entityPackageName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eomodeldFullPath",eomodeldFullPath);
		paramMap.put("entityNamePrefix",entityNamePrefix);
		paramMap.put("entityGroupId",entityGroupId);
		paramMap.put("datasourceName",datasourceName);
		paramMap.put("entityPackageName",entityPackageName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("exportEntityEoModelBundle", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/entityImport")
	public ResponseEntity<Object> entityImport(HttpSession session, @RequestParam(value="placeholderValues", required=false) java.util.Map placeholderValues, @RequestParam(value="filename", required=false) String filename, @RequestParam(value="checkDataOnly", required=false) String checkDataOnly, @RequestParam(value="fmfilename", required=false) String fmfilename, @RequestParam(value="createDummyFks", required=false) String createDummyFks, @RequestParam(value="maintainTimeStamps", required=false) String maintainTimeStamps, @RequestParam(value="fulltext", required=false) String fulltext, @RequestParam(value="isUrl", required=false) String isUrl, @RequestParam(value="txTimeout", required=false) Integer txTimeout, @RequestParam(value="mostlyInserts", required=false) String mostlyInserts) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("placeholderValues",placeholderValues);
		paramMap.put("filename",filename);
		paramMap.put("checkDataOnly",checkDataOnly);
		paramMap.put("fmfilename",fmfilename);
		paramMap.put("createDummyFks",createDummyFks);
		paramMap.put("maintainTimeStamps",maintainTimeStamps);
		paramMap.put("fulltext",fulltext);
		paramMap.put("isUrl",isUrl);
		paramMap.put("txTimeout",txTimeout);
		paramMap.put("mostlyInserts",mostlyInserts);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("entityImport", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(value = (" * "))
	public ResponseEntity<Object> returnErrorPage(HttpSession session) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested service does not exist. JSESSIONID=" + session.getId());
	}

}
