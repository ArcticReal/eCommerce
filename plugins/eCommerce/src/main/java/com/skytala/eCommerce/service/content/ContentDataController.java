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

@RestController
@RequestMapping("/service/ContentDataController")
public class ContentDataController{

	@RequestMapping(method = RequestMethod.POST, value = "/updateElectronicText")
	public ResponseEntity<Object> updateElectronicText(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("textData",textData);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateElectronicText", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createDataResourceAndText")
	public ResponseEntity<Object> createDataResourceAndText(HttpSession session, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("surveyId",surveyId);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataResourceAndText", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getElectronicText")
	public ResponseEntity<Object> getElectronicText(HttpSession session, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="content", required=false) org.apache.ofbiz.entity.GenericValue content) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("content",content);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getElectronicText", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeImageDataResource")
	public ResponseEntity<Object> removeImageDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeImageDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateDataResourceMetaData")
	public ResponseEntity<Object> updateDataResourceMetaData(HttpSession session, @RequestParam(value="metaDataPredicateId") String metaDataPredicateId, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="metaDataValue", required=false) Long metaDataValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("metaDataPredicateId",metaDataPredicateId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("metaDataValue",metaDataValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDataResourceMetaData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeDataResource")
	public ResponseEntity<Object> removeDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createFile")
	public ResponseEntity<Object> createFile(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="dataResource", required=false) org.apache.ofbiz.entity.GenericValue dataResource, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="binData", required=false) java.nio.ByteBuffer binData, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("dataResource",dataResource);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("binData",binData);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFile", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeDataResourceMetaData")
	public ResponseEntity<Object> removeDataResourceMetaData(HttpSession session, @RequestParam(value="metaDataPredicateId") String metaDataPredicateId, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("metaDataPredicateId",metaDataPredicateId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeDataResourceMetaData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createOtherDataResource")
	public ResponseEntity<Object> createOtherDataResource(HttpSession session, @RequestParam(value="dataResourceContent", required=false) byte[] dataResourceContent, @RequestParam(value="dataResourceId", required=false) String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceContent",dataResourceContent);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createOtherDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateElectronicTextForm")
	public ResponseEntity<Object> updateElectronicTextForm(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="contentId", required=false) String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("textData",textData);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateElectronicTextForm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createElectronicText")
	public ResponseEntity<Object> createElectronicText(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createElectronicText", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createAnonFile")
	public ResponseEntity<Object> createAnonFile(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="dataResource", required=false) org.apache.ofbiz.entity.GenericValue dataResource, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="binData", required=false) java.nio.ByteBuffer binData, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("dataResource",dataResource);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("binData",binData);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAnonFile", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateDataTemplateType")
	public ResponseEntity<Object> updateDataTemplateType(HttpSession session, @RequestParam(value="dataTemplateTypeId") String dataTemplateTypeId, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("extension",extension);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDataTemplateType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateVideoDataResource")
	public ResponseEntity<Object> updateVideoDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="videoData", required=false) byte[] videoData) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("videoData",videoData);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateVideoDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateAudioDataResource")
	public ResponseEntity<Object> updateAudioDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="audioData", required=false) byte[] audioData) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("audioData",audioData);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAudioDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createDataResourcePurpose")
	public ResponseEntity<Object> createDataResourcePurpose(HttpSession session, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataResourcePurpose", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createElectronicTextForm")
	public ResponseEntity<Object> createElectronicTextForm(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createElectronicTextForm", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createDataResourceAndAssocToContent")
	public ResponseEntity<Object> createDataResourceAndAssocToContent(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="templateDataResource", required=false) String templateDataResource, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("surveyId",surveyId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("templateDataResource",templateDataResource);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataResourceAndAssocToContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateDataResource")
	public ResponseEntity<Object> updateDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("surveyId",surveyId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateDataResourcePurpose")
	public ResponseEntity<Object> updateDataResourcePurpose(HttpSession session, @RequestParam(value="contentPurposeTypeId") String contentPurposeTypeId, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDataResourcePurpose", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/clearAssociatedRenderCache")
	public ResponseEntity<Object> clearAssociatedRenderCache(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("clearAssociatedRenderCache", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateDataText")
	public ResponseEntity<Object> updateDataText(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("surveyId",surveyId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("contentId",contentId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDataText", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeDataResourcePurpose")
	public ResponseEntity<Object> removeDataResourcePurpose(HttpSession session, @RequestParam(value="contentPurposeTypeId") String contentPurposeTypeId, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeDataResourcePurpose", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateDataResourceAndText")
	public ResponseEntity<Object> updateDataResourceAndText(HttpSession session, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("surveyId",surveyId);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDataResourceAndText", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeElectronicText")
	public ResponseEntity<Object> removeElectronicText(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeElectronicText", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateOtherDataResource")
	public ResponseEntity<Object> updateOtherDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="dataResourceContent", required=false) byte[] dataResourceContent) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("dataResourceContent",dataResourceContent);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateOtherDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createDataResourceMetaData")
	public ResponseEntity<Object> createDataResourceMetaData(HttpSession session, @RequestParam(value="metaDataPredicateId") String metaDataPredicateId, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="metaDataValue", required=false) Long metaDataValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("metaDataPredicateId",metaDataPredicateId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("metaDataValue",metaDataValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataResourceMetaData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createImageDataResource")
	public ResponseEntity<Object> createImageDataResource(HttpSession session, @RequestParam(value="imageData", required=false) byte[] imageData, @RequestParam(value="dataResourceId", required=false) String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("imageData",imageData);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createImageDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createVideoDataResource")
	public ResponseEntity<Object> createVideoDataResource(HttpSession session, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="videoData", required=false) byte[] videoData) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("videoData",videoData);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createVideoDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeAudioDataResource")
	public ResponseEntity<Object> removeAudioDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeAudioDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createDataText")
	public ResponseEntity<Object> createDataText(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataText", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateFile")
	public ResponseEntity<Object> updateFile(HttpSession session, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="dataResource", required=false) org.apache.ofbiz.entity.GenericValue dataResource, @RequestParam(value="binData", required=false) java.nio.ByteBuffer binData) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("textData",textData);
		paramMap.put("rootDir",rootDir);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("dataResource",dataResource);
		paramMap.put("binData",binData);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFile", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteDataTemplateType")
	public ResponseEntity<Object> deleteDataTemplateType(HttpSession session, @RequestParam(value="dataTemplateTypeId") String dataTemplateTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteDataTemplateType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeOtherDataResource")
	public ResponseEntity<Object> removeOtherDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeOtherDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeVideoDataResource")
	public ResponseEntity<Object> removeVideoDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeVideoDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createAudioDataResource")
	public ResponseEntity<Object> createAudioDataResource(HttpSession session, @RequestParam(value="audioData", required=false) byte[] audioData, @RequestParam(value="dataResourceId", required=false) String dataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("audioData",audioData);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAudioDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createDataResource")
	public ResponseEntity<Object> createDataResource(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createDataTemplateType")
	public ResponseEntity<Object> createDataTemplateType(HttpSession session, @RequestParam(value="extension", required=false) String extension, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("extension",extension);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDataTemplateType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateImageDataResource")
	public ResponseEntity<Object> updateImageDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="imageData", required=false) byte[] imageData) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("imageData",imageData);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateImageDataResource", paramMap);
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
