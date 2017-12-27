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
@RequestMapping("/service/contentContent")
public class ContentContentServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentAssoc")
	public ResponseEntity<Map<String, Object>> updateContentAssoc(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contentAssocTypeId") String contentAssocTypeId, @RequestParam(value="contentIdTo") String contentIdTo, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deactivateExisting", required=false) String deactivateExisting) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentId",contentId);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("upperCoordinate",upperCoordinate);
		paramMap.put("leftCoordinate",leftCoordinate);
		paramMap.put("mapKey",mapKey);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("deactivateExisting",deactivateExisting);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContentMetaData")
	public ResponseEntity<Map<String, Object>> removeContentMetaData(HttpSession session, @RequestParam(value="metaDataPredicateId") String metaDataPredicateId, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("metaDataPredicateId",metaDataPredicateId);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContentMetaData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/attachUploadToDataResource")
	public ResponseEntity<Map<String, Object>> attachUploadToDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("attachUploadToDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentAttribute")
	public ResponseEntity<Map<String, Object>> createContentAttribute(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentOperation")
	public ResponseEntity<Map<String, Object>> createContentOperation(HttpSession session, @RequestParam(value="contentOperationId", required=false) String contentOperationId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentOperationId",contentOperationId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentOperation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContentOperation")
	public ResponseEntity<Map<String, Object>> removeContentOperation(HttpSession session, @RequestParam(value="contentOperationId") String contentOperationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentOperationId",contentOperationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContentOperation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContentAttribute")
	public ResponseEntity<Map<String, Object>> removeContentAttribute(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContentAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContentPurposeOperation")
	public ResponseEntity<Map<String, Object>> removeContentPurposeOperation(HttpSession session, @RequestParam(value="contentPurposeTypeId") String contentPurposeTypeId, @RequestParam(value="privilegeEnumId") String privilegeEnumId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="statusId") String statusId, @RequestParam(value="contentOperationId") String contentOperationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("contentOperationId",contentOperationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContentPurposeOperation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentAssoc")
	public ResponseEntity<Map<String, Object>> createContentAssoc(HttpSession session, @RequestParam(value="contentAssocTypeId") String contentAssocTypeId, @RequestParam(value="contentIdTo") String contentIdTo, @RequestParam(value="contentId") String contentId, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deactivateExisting", required=false) String deactivateExisting) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentId",contentId);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("upperCoordinate",upperCoordinate);
		paramMap.put("leftCoordinate",leftCoordinate);
		paramMap.put("mapKey",mapKey);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("deactivateExisting",deactivateExisting);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContentRole")
	public ResponseEntity<Map<String, Object>> removeContentRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContentRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContentAndRelated")
	public ResponseEntity<Map<String, Object>> removeContentAndRelated(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="roleTypeList", required=false) List roleTypeList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("userLogin",userLogin);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("roleTypeList",roleTypeList);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContentAndRelated", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentPurposeOperation")
	public ResponseEntity<Map<String, Object>> createContentPurposeOperation(HttpSession session, @RequestParam(value="contentPurposeTypeId") String contentPurposeTypeId, @RequestParam(value="privilegeEnumId") String privilegeEnumId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="statusId") String statusId, @RequestParam(value="contentOperationId") String contentOperationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("contentOperationId",contentOperationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentPurposeOperation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/forceIndexContentKeywords")
	public ResponseEntity<Map<String, Object>> forceIndexContentKeywords(HttpSession session, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("forceIndexContentKeywords", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/assocContent")
	public ResponseEntity<Map<String, Object>> assocContent(HttpSession session, @RequestParam(value="contentAssocTypeId") String contentAssocTypeId, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="entityOperation", required=false) String entityOperation, @RequestParam(value="thruDate", required=false) String thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("userLogin",userLogin);
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("statusId",statusId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("entityOperation",entityOperation);
		paramMap.put("thruDate",thruDate);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("assocContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContentAssoc")
	public ResponseEntity<Map<String, Object>> removeContentAssoc(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contentAssocTypeId") String contentAssocTypeId, @RequestParam(value="contentIdTo") String contentIdTo, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContentAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentRole")
	public ResponseEntity<Map<String, Object>> createContentRole(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContentPurpose")
	public ResponseEntity<Map<String, Object>> removeContentPurpose(HttpSession session, @RequestParam(value="contentPurposeTypeId") String contentPurposeTypeId, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContentPurpose", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentRevisionItem")
	public ResponseEntity<Map<String, Object>> createContentRevisionItem(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentRevisionSeqId") String contentRevisionSeqId, @RequestParam(value="itemContentId") String itemContentId, @RequestParam(value="oldDataResourceId", required=false) String oldDataResourceId, @RequestParam(value="newDataResourceId", required=false) String newDataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("itemContentId",itemContentId);
		paramMap.put("oldDataResourceId",oldDataResourceId);
		paramMap.put("newDataResourceId",newDataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentRevisionItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentMetaData")
	public ResponseEntity<Map<String, Object>> createContentMetaData(HttpSession session, @RequestParam(value="metaDataPredicateId") String metaDataPredicateId, @RequestParam(value="contentId") String contentId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="metaDataValue", required=false) Long metaDataValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("metaDataPredicateId",metaDataPredicateId);
		paramMap.put("contentId",contentId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("metaDataValue",metaDataValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentMetaData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteContentKeyword")
	public ResponseEntity<Map<String, Object>> deleteContentKeyword(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="keyword") String keyword) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("keyword",keyword);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteContentKeyword", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentApproval")
	public ResponseEntity<Map<String, Object>> createContentApproval(HttpSession session, @RequestParam(value="contentApprovalId", required=false) String contentApprovalId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="approvalDate", required=false) Timestamp approvalDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentRevisionSeqId", required=false) String contentRevisionSeqId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="approvalStatusId", required=false) String approvalStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentApprovalId",contentApprovalId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("approvalDate",approvalDate);
		paramMap.put("comments",comments);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("contentId",contentId);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("partyId",partyId);
		paramMap.put("approvalStatusId",approvalStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentApproval", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/copyContentAndElectronicTextandAssoc")
	public ResponseEntity<Map<String, Object>> copyContentAndElectronicTextandAssoc(HttpSession session, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyContentAndElectronicTextandAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkContentPermission")
	public ResponseEntity<Map<String, Object>> checkContentPermission(HttpSession session, @RequestParam(value="quickCheckContentId", required=false) String quickCheckContentId, @RequestParam(value="displayPassCond", required=false) Boolean displayPassCond, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="roleTypeString", required=false) String roleTypeString, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="thruDate", required=false) String thruDate, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="userLoginId", required=false) String userLoginId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="currentContent", required=false) org.apache.ofbiz.entity.GenericValue currentContent, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="entityOperation", required=false) String entityOperation, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("quickCheckContentId",quickCheckContentId);
		paramMap.put("displayPassCond",displayPassCond);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("roleTypeString",roleTypeString);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("currentContent",currentContent);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("entityOperation",entityOperation);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkContentPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContent")
	public ResponseEntity<Map<String, Object>> removeContent(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="roleTypeList", required=false) List roleTypeList) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("userLogin",userLogin);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("roleTypeList",roleTypeList);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentAndUploadedFile")
	public ResponseEntity<Map<String, Object>> updateContentAndUploadedFile(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="partyContentTypeId") String partyContentTypeId, @RequestParam(value="contentAssocTypeId") String contentAssocTypeId, @RequestParam(value="contentIdTo") String contentIdTo, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="contentId") String contentId, @RequestParam(value="partyId") String partyId, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="description", required=false) String description, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deactivateExisting", required=false) String deactivateExisting, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("partyContentTypeId",partyContentTypeId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("upperCoordinate",upperCoordinate);
		paramMap.put("leftCoordinate",leftCoordinate);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("description",description);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("deactivateExisting",deactivateExisting);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("localeString",localeString);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentAndUploadedFile", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSingleContentPurpose")
	public ResponseEntity<Map<String, Object>> updateSingleContentPurpose(HttpSession session, @RequestParam(value="contentPurposeTypeId") String contentPurposeTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSingleContentPurpose", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTextAndUploadedContent")
	public ResponseEntity<Map<String, Object>> createTextAndUploadedContent(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="childBranchCount", required=false) Long childBranchCount, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("fromDate",fromDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTextAndUploadedContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setContentStatus")
	public ResponseEntity<Map<String, Object>> setContentStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setContentStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentKeyword")
	public ResponseEntity<Map<String, Object>> updateContentKeyword(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="keyword") String keyword, @RequestParam(value="relevancyWeight", required=false) Long relevancyWeight) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("keyword",keyword);
		paramMap.put("relevancyWeight",relevancyWeight);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentKeyword", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createTextContent")
	public ResponseEntity<Map<String, Object>> createTextContent(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="childBranchCount", required=false) Long childBranchCount, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("fromDate",fromDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("textData",textData);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createTextContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateTextContent")
	public ResponseEntity<Map<String, Object>> updateTextContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contentAssocTypeId") String contentAssocTypeId, @RequestParam(value="contentIdTo") String contentIdTo, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="description", required=false) String description, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deactivateExisting", required=false) String deactivateExisting, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("upperCoordinate",upperCoordinate);
		paramMap.put("leftCoordinate",leftCoordinate);
		paramMap.put("contentId",contentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("description",description);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("textData",textData);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("deactivateExisting",deactivateExisting);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("localeString",localeString);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateTextContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkAssocPermission")
	public ResponseEntity<Map<String, Object>> checkAssocPermission(HttpSession session, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="thruDate", required=false) String thruDate, @RequestParam(value="userLogin", required=false) GenericValue userLogin, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="entityOperation", required=false) String entityOperation) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin",userLogin);
		paramMap.put("fromDate",fromDate);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("entityOperation",entityOperation);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkAssocPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/indexContentKeywords")
	public ResponseEntity<Map<String, Object>> indexContentKeywords(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentInstance", required=false) org.apache.ofbiz.entity.GenericValue contentInstance) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentInstance",contentInstance);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("indexContentKeywords", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentKeyword")
	public ResponseEntity<Map<String, Object>> createContentKeyword(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="keyword") String keyword, @RequestParam(value="relevancyWeight", required=false) Long relevancyWeight) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("keyword",keyword);
		paramMap.put("relevancyWeight",relevancyWeight);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentKeyword", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentRevisionItem")
	public ResponseEntity<Map<String, Object>> updateContentRevisionItem(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentRevisionSeqId") String contentRevisionSeqId, @RequestParam(value="itemContentId") String itemContentId, @RequestParam(value="oldDataResourceId", required=false) String oldDataResourceId, @RequestParam(value="newDataResourceId", required=false) String newDataResourceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("itemContentId",itemContentId);
		paramMap.put("oldDataResourceId",oldDataResourceId);
		paramMap.put("newDataResourceId",newDataResourceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentRevisionItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createMissingContentAltUrls")
	public ResponseEntity<Map<String, Object>> createMissingContentAltUrls(HttpSession session, @RequestParam(value="prodCatalogId", required=false) String prodCatalogId, @RequestParam(value="webSiteId", required=false) String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createMissingContentAltUrls", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContent")
	public ResponseEntity<Map<String, Object>> createContent(HttpSession session, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("partyId",partyId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("localeString",localeString);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deactivateAllContentRoles")
	public ResponseEntity<Map<String, Object>> deactivateAllContentRoles(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deactivateAllContentRoles", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContentRevision")
	public ResponseEntity<Map<String, Object>> removeContentRevision(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentRevisionSeqId") String contentRevisionSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContentRevision", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentFromDataResource")
	public ResponseEntity<Map<String, Object>> createContentFromDataResource(HttpSession session, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("partyId",partyId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("localeString",localeString);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentFromDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentAttribute")
	public ResponseEntity<Map<String, Object>> updateContentAttribute(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentOperation")
	public ResponseEntity<Map<String, Object>> updateContentOperation(HttpSession session, @RequestParam(value="contentOperationId") String contentOperationId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentOperationId",contentOperationId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentOperation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/findRelatedContent")
	public ResponseEntity<Map<String, Object>> findRelatedContent(HttpSession session, @RequestParam(value="currentContent") org.apache.ofbiz.entity.GenericValue currentContent, @RequestParam(value="fromDate", required=false) String fromDate, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="contentAssocTypeList", required=false) List contentAssocTypeList, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="contentTypeList", required=false) List contentTypeList, @RequestParam(value="entityOperation", required=false) String entityOperation, @RequestParam(value="toFrom", required=false) String toFrom, @RequestParam(value="thruDate", required=false) String thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currentContent",currentContent);
		paramMap.put("fromDate",fromDate);
		paramMap.put("userLogin",userLogin);
		paramMap.put("contentAssocTypeList",contentAssocTypeList);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("contentTypeList",contentTypeList);
		paramMap.put("entityOperation",entityOperation);
		paramMap.put("toFrom",toFrom);
		paramMap.put("thruDate",thruDate);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findRelatedContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentRole")
	public ResponseEntity<Map<String, Object>> updateContentRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="partyId") String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentRole", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentPurpose")
	public ResponseEntity<Map<String, Object>> updateContentPurpose(HttpSession session, @RequestParam(value="contentPurposeTypeId") String contentPurposeTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentPurpose", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentPurpose")
	public ResponseEntity<Map<String, Object>> createContentPurpose(HttpSession session, @RequestParam(value="contentPurposeTypeId") String contentPurposeTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentPurpose", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteContentKeywords")
	public ResponseEntity<Map<String, Object>> deleteContentKeywords(HttpSession session, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteContentKeywords", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentRevision")
	public ResponseEntity<Map<String, Object>> createContentRevision(HttpSession session, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentRevisionSeqId", required=false) String contentRevisionSeqId, @RequestParam(value="committedByPartyId", required=false) String committedByPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("comments",comments);
		paramMap.put("contentId",contentId);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("committedByPartyId",committedByPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentRevision", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentPurposeOperation")
	public ResponseEntity<Map<String, Object>> updateContentPurposeOperation(HttpSession session, @RequestParam(value="contentPurposeTypeId") String contentPurposeTypeId, @RequestParam(value="privilegeEnumId") String privilegeEnumId, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="statusId") String statusId, @RequestParam(value="contentOperationId") String contentOperationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("statusId",statusId);
		paramMap.put("contentOperationId",contentOperationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentPurposeOperation", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentRevision")
	public ResponseEntity<Map<String, Object>> updateContentRevision(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentRevisionSeqId") String contentRevisionSeqId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="committedByPartyId", required=false) String committedByPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("comments",comments);
		paramMap.put("committedByPartyId",committedByPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentRevision", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentMetaData")
	public ResponseEntity<Map<String, Object>> updateContentMetaData(HttpSession session, @RequestParam(value="metaDataPredicateId") String metaDataPredicateId, @RequestParam(value="contentId") String contentId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="metaDataValue", required=false) Long metaDataValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("metaDataPredicateId",metaDataPredicateId);
		paramMap.put("contentId",contentId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("metaDataValue",metaDataValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentMetaData", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentApproval")
	public ResponseEntity<Map<String, Object>> updateContentApproval(HttpSession session, @RequestParam(value="contentApprovalId") String contentApprovalId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="approvalDate", required=false) Timestamp approvalDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentRevisionSeqId", required=false) String contentRevisionSeqId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="approvalStatusId", required=false) String approvalStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentApprovalId",contentApprovalId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("approvalDate",approvalDate);
		paramMap.put("comments",comments);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("contentId",contentId);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("partyId",partyId);
		paramMap.put("approvalStatusId",approvalStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentApproval", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContentApproval")
	public ResponseEntity<Map<String, Object>> removeContentApproval(HttpSession session, @RequestParam(value="contentApprovalId") String contentApprovalId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentApprovalId",contentApprovalId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContentApproval", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createContentFromUploadedFile")
	public ResponseEntity<Map<String, Object>> createContentFromUploadedFile(HttpSession session, @RequestParam(value="surveyId", required=false) String surveyId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="rootDir", required=false) String rootDir, @RequestParam(value="dataCategoryId", required=false) String dataCategoryId, @RequestParam(value="surveyResponseId", required=false) String surveyResponseId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="contentAssocTypeId", required=false) String contentAssocTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="objectInfo", required=false) String objectInfo, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceName", required=false) Long dataResourceName, @RequestParam(value="targetOperationString", required=false) String targetOperationString, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentPurposeString", required=false) String contentPurposeString, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName, @RequestParam(value="childBranchCount", required=false) Long childBranchCount, @RequestParam(value="contentIdTo", required=false) String contentIdTo, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="description", required=false) String description, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="relatedDetailId", required=false) String relatedDetailId, @RequestParam(value="isPublic", required=false) String isPublic, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="dataTemplateTypeId", required=false) String dataTemplateTypeId, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("surveyId",surveyId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("rootDir",rootDir);
		paramMap.put("dataCategoryId",dataCategoryId);
		paramMap.put("surveyResponseId",surveyResponseId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("partyId",partyId);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("targetOperationString",targetOperationString);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentPurposeString",contentPurposeString);
		paramMap.put("fromDate",fromDate);
		paramMap.put("statusId",statusId);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("relatedDetailId",relatedDetailId);
		paramMap.put("isPublic",isPublic);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("dataTemplateTypeId",dataTemplateTypeId);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createContentFromUploadedFile", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContent")
	public ResponseEntity<Map<String, Object>> updateContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contentAssocTypeId") String contentAssocTypeId, @RequestParam(value="contentIdTo") String contentIdTo, @RequestParam(value="contentId") String contentId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="upperCoordinate", required=false) Long upperCoordinate, @RequestParam(value="leftCoordinate", required=false) Long leftCoordinate, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="roleTypeList", required=false) List roleTypeList, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="contentPurposeTypeId", required=false) String contentPurposeTypeId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="contentAssocPredicateId", required=false) String contentAssocPredicateId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="deactivateExisting", required=false) String deactivateExisting, @RequestParam(value="contentPurposeList", required=false) List contentPurposeList, @RequestParam(value="targetOperationList", required=false) List targetOperationList, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="skipPermissionCheck", required=false) String skipPermissionCheck, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="mapKey", required=false) String mapKey, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="displayFailCond", required=false) Boolean displayFailCond, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="contentIdFrom", required=false) String contentIdFrom, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentAssocTypeId",contentAssocTypeId);
		paramMap.put("contentIdTo",contentIdTo);
		paramMap.put("contentId",contentId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("upperCoordinate",upperCoordinate);
		paramMap.put("leftCoordinate",leftCoordinate);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("roleTypeList",roleTypeList);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("contentPurposeTypeId",contentPurposeTypeId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("contentAssocPredicateId",contentAssocPredicateId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("deactivateExisting",deactivateExisting);
		paramMap.put("contentPurposeList",contentPurposeList);
		paramMap.put("targetOperationList",targetOperationList);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("skipPermissionCheck",skipPermissionCheck);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("displayFailCond",displayFailCond);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("contentIdFrom",contentIdFrom);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeContentRevisionItem")
	public ResponseEntity<Map<String, Object>> removeContentRevisionItem(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="contentRevisionSeqId") String contentRevisionSeqId, @RequestParam(value="itemContentId") String itemContentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("contentRevisionSeqId",contentRevisionSeqId);
		paramMap.put("itemContentId",itemContentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeContentRevisionItem", paramMap);
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
