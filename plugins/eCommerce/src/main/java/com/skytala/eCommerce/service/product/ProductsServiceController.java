package com.skytala.eCommerce.service.product;

import com.skytala.eCommerce.domain.party.PartyController;
import com.skytala.eCommerce.domain.party.model.Party;
import com.skytala.eCommerce.domain.party.query.FindPartysBy;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.query.FindProductsBy;
import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.role.ProdCatalogRole;
import com.skytala.eCommerce.domain.product.relations.product.control.categoryMember.ProductCategoryMemberController;
import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryMember.ProductCategoryMember;
import com.skytala.eCommerce.domain.product.relations.product.query.category.FindProductCategorysBy;
import com.skytala.eCommerce.service.login.util.SecurityGroups;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericDelegator;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ServiceAuthException;
import org.apache.ofbiz.service.ServiceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;
import static com.skytala.eCommerce.framework.util.AuthorizeMethods.DENY_ALL;
import static com.skytala.eCommerce.framework.util.AuthorizeMethods.HAS_ADMIN_AUTHORITY;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/products")
@PreAuthorize(DENY_ALL)
public class ProductsServiceController {

	@Autowired
	private ProductCategoryMemberController productCategoryMemberController;

	private FindPartysBy getFindPartiesQuery(String partyId) throws Exception {
		return new FindPartysBy(UtilMisc.toMap("partyId", partyId));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWebAnalyticsType")
	public ResponseEntity<Object> deleteWebAnalyticsType(HttpSession session, @RequestParam(value="webAnalyticsTypeId") String webAnalyticsTypeId) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webAnalyticsTypeId",webAnalyticsTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWebAnalyticsType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addProductCatalogToParty")
	// @Role(Role.ADMINSTRATOR, Role.BOOKKEEPING)
	public ResponseEntity addProductCatalogToParty(HttpSession session, ProdCatalogRole role) throws Exception {

		Party party = new Party(role.getPartyId(), getFindPartiesQuery(role.getPartyId()));

		try{
			party.addProductCatalog(session, role);
		} catch(ServiceAuthException e) {
			return unauthorized();
		} catch (ServiceValidationException e) {
			return serverError();
		}
		return successful();
	}



	@RequestMapping(method = RequestMethod.POST, value = "/addProdCatalogToParty")
	public ResponseEntity addProdCatalogToParty(HttpSession session,
												@RequestParam(value="roleTypeId") String roleTypeId,
												@RequestParam(value="partyId") String partyId,
												@RequestParam(value="prodCatalogId") String prodCatalogId,
												@RequestParam(value="fromDate", required=false) Timestamp fromDate,
												@RequestParam(value="sequenceNum", required=false) Long sequenceNum,
												@RequestParam(value="thruDate", required=false) Timestamp thruDate) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addProdCatalogToParty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductMaintType")
	public ResponseEntity<Object> deleteProductMaintType(HttpSession session,
														 @RequestParam(value="productMaintTypeId") String productMaintTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productMaintTypeId",productMaintTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductMaintType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductAssoc")
	public ResponseEntity<Object> createProductAssoc(HttpSession session,
													 @RequestParam(value="fromDate") Timestamp fromDate,
													 @RequestParam(value="productId") String productId,
													 @RequestParam(value="productIdTo") String productIdTo,
													 @RequestParam(value="productAssocTypeId") String productAssocTypeId,
													 @RequestParam(value="reason", required=false) String reason,
													 @RequestParam(value="scrapFactor", required=false) BigDecimal scrapFactor,
													 @RequestParam(value="quantity", required=false) BigDecimal quantity,
													 @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId,
													 @RequestParam(value="sequenceNum", required=false) Long sequenceNum,
													 @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod,
													 @RequestParam(value="instruction", required=false) String instruction,
													 @RequestParam(value="routingWorkEffortId", required=false) String routingWorkEffortId,
													 @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("productIdTo",productIdTo);
		paramMap.put("productAssocTypeId",productAssocTypeId);
		paramMap.put("reason",reason);
		paramMap.put("scrapFactor",scrapFactor);
		paramMap.put("quantity",quantity);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("instruction",instruction);
		paramMap.put("routingWorkEffortId",routingWorkEffortId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSimpleTextContentForProductConfigItem")
	public ResponseEntity<Object> createSimpleTextContentForProductConfigItem(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="text") String text, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="confItemContentTypeId", required=false) String confItemContentTypeId, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("text",text);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentId",contentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("confItemContentTypeId",confItemContentTypeId);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSimpleTextContentForProductConfigItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCustomerDigitalDownloadProduct")
	public ResponseEntity<Object> createCustomerDigitalDownloadProduct(HttpSession session, @RequestParam(value="price") BigDecimal price, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productName") String productName, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("price",price);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("productName",productName);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCustomerDigitalDownloadProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductPromoContent")
	public ResponseEntity<Object> removeProductPromoContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productPromoContentTypeId") String productPromoContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productPromoContentTypeId",productPromoContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductPromoContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/multipleUploadProductImages")
	@PreAuthorize(HAS_ADMIN_AUTHORITY)
	public ResponseEntity<Object> multipleUploadProductImages(HttpSession session,
															  @RequestParam(value="productId", required = false) String productId,
															  @RequestParam(value="_additionalImageOne_fileName", required=false) String _additionalImageOne_fileName,
															  @RequestParam(value="additionalImageOne", required=false) MultipartFile additionalImageOne,
															  @RequestParam(value="_additionalImageTwo_fileName", required=false) String _additionalImageTwo_fileName,
															  @RequestParam(value="additionalImageTwo", required=false) MultipartFile additionalImageTwo,
															  @RequestParam(value="_additionalImageThree_fileName", required=false) String _additionalImageThree_fileName,
															  @RequestParam(value="additionalImageThree", required=false) MultipartFile additionalImageThree,
															  @RequestParam(value="_additionalImageFour_fileName", required=false) String _additionalImageFour_fileName,
															  @RequestParam(value="additionalImageFour", required=false) MultipartFile additionalImageFour,
															  @RequestParam(value="_additionalImageFive_fileName", required=false) String _additionalImageFive_fileName,
															  @RequestParam(value="additionalImageFive", required=false) MultipartFile additionalImageFive,
															  @RequestParam(value="_additionalImageSix_fileName", required=false) String _additionalImageSix_fileName,
															  @RequestParam(value="additionalImageSix", required=false) MultipartFile additionalImageSix,
															  @RequestParam(value="_additionalImageSeven_fileName", required=false) String _additionalImageSeven_fileName,
															  @RequestParam(value="additionalImageSeven", required=false) MultipartFile additionalImageSeven,
															  @RequestParam(value="_additionalImageEight_fileName", required=false) String _additionalImageEight_fileName,
															  @RequestParam(value="additionalImageEight", required=false) MultipartFile additionalImageEight,
															  @RequestParam(value="_additionalImageNine_fileName", required=false) String _additionalImageNine_fileName,
															  @RequestParam(value="additionalImageNine", required=false) MultipartFile additionalImageNine,
															  @RequestParam(value="_additionalImageTen_fileName", required=false) String _additionalImageTen_fileName,
															  @RequestParam(value="additionalImageTen", required=false) MultipartFile additionalImageTen,
															  @RequestParam(value="imageResize", required=false) String imageResize)
			throws IOException {



		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		if(additionalImageOne!=null){
			if(_additionalImageOne_fileName == null){
				String[] fileName = additionalImageOne.getOriginalFilename().split("/");
				paramMap.put("_additionalImageOne_fileName",fileName[fileName.length-1]);
			}else{
				paramMap.put("_additionalImageOne_fileName",_additionalImageOne_fileName);
			}
			paramMap.put("_additionalImageOne_contentType",additionalImageOne.getContentType());
			paramMap.put("additionalImageOne", ByteBuffer.wrap(additionalImageOne.getBytes()));
		}
		if(additionalImageTwo!=null){
			if(_additionalImageTwo_fileName == null){
				String[] fileName = additionalImageTwo.getOriginalFilename().split("/");
				paramMap.put("_additionalImageTwo_fileName",fileName[fileName.length-1]);
			}else{
				paramMap.put("_additionalImageTwo_fileName",_additionalImageTwo_fileName);
			}
			paramMap.put("_additionalImageTwo_contentType",additionalImageTwo.getContentType());
			paramMap.put("additionalImageTwo",ByteBuffer.wrap(additionalImageTwo.getBytes()));
		}
		if(additionalImageThree!=null){
			if(_additionalImageThree_fileName == null){
				String[] fileName = additionalImageThree.getOriginalFilename().split("/");
				paramMap.put("_additionalImageThree_fileName",fileName[fileName.length-1]);
			}else{
				paramMap.put("_additionalImageThree_fileName",_additionalImageThree_fileName);
			}
			paramMap.put("_additionalImageThree_contentType",additionalImageThree.getContentType());
			paramMap.put("additionalImageThree",ByteBuffer.wrap(additionalImageThree.getBytes()));
		}
		if(additionalImageFour!=null){
			if(_additionalImageFour_fileName == null){
				String[] fileName = additionalImageFour.getOriginalFilename().split("/");
				paramMap.put("_additionalImageFour_fileName",fileName[fileName.length-1]);
			}else{
				paramMap.put("_additionalImageFour_fileName",_additionalImageFour_fileName);
			}
			paramMap.put("_additionalImageFour_contentType",additionalImageFour.getContentType());
			paramMap.put("additionalImageFour",ByteBuffer.wrap(additionalImageFour.getBytes()));
		}
		if(additionalImageFive!=null){
			if(_additionalImageFive_fileName == null){
				String[] fileName = additionalImageFive.getOriginalFilename().split("/");
				paramMap.put("_additionalImageFive_fileName",fileName[fileName.length-1]);
			}else{
				paramMap.put("_additionalImageFive_fileName",_additionalImageFive_fileName);
			}
			paramMap.put("_additionalImageFive_contentType",additionalImageFive.getContentType());
			paramMap.put("additionalImageFive",ByteBuffer.wrap(additionalImageFive.getBytes()));
		}
		if(additionalImageSix!=null){
			if(_additionalImageSix_fileName == null){
				String[] fileName = additionalImageSix.getOriginalFilename().split("/");
				paramMap.put("_additionalImageSix_fileName",fileName[fileName.length-1]);
			}else{
				paramMap.put("_additionalImageSix_fileName",_additionalImageSix_fileName);
			}
			paramMap.put("_additionalImageSix_contentType",additionalImageSix.getContentType());
			paramMap.put("additionalImageSix",ByteBuffer.wrap(additionalImageSix.getBytes()));
		}
		if(additionalImageSeven!=null){
			if(_additionalImageSeven_fileName == null){
				String[] fileName = additionalImageSeven.getOriginalFilename().split("/");
				paramMap.put("_additionalImageSeven_fileName",fileName[fileName.length-1]);
			}else{
				paramMap.put("_additionalImageSeven_fileName",_additionalImageSeven_fileName);
			}
			paramMap.put("_additionalImageSeven_contentType",additionalImageSeven.getContentType());
			paramMap.put("additionalImageSeven",ByteBuffer.wrap(additionalImageSeven.getBytes()));
		}
		if(additionalImageEight!=null){
			if(_additionalImageEight_fileName == null){
				String[] fileName = additionalImageEight.getOriginalFilename().split("/");
				paramMap.put("_additionalImageEight_fileName",fileName[fileName.length-1]);
			}else{
				paramMap.put("_additionalImageEight_fileName",_additionalImageEight_fileName);
			}
			paramMap.put("_additionalImageEight_contentType",additionalImageEight.getContentType());
			paramMap.put("additionalImageEight",ByteBuffer.wrap(additionalImageEight.getBytes()));
		}
		if(additionalImageNine!=null){
			if(_additionalImageNine_fileName == null){
				String[] fileName = additionalImageNine.getOriginalFilename().split("/");
				paramMap.put("_additionalImageNine_fileName",fileName[fileName.length-1]);
			}else{
				paramMap.put("_additionalImageNine_fileName",_additionalImageNine_fileName);
			}
			paramMap.put("_additionalImageNine_contentType",additionalImageNine.getContentType());
			paramMap.put("additionalImageNine",ByteBuffer.wrap(additionalImageNine.getBytes()));
		}
		if(additionalImageTen!=null){
			if(_additionalImageTen_fileName == null){
				String[] fileName = additionalImageTen.getOriginalFilename().split("/");
				paramMap.put("_additionalImageTen_fileName",fileName[fileName.length-1]);
			}else{
				paramMap.put("_additionalImageTen_fileName",_additionalImageTen_fileName);
			}
			paramMap.put("_additionalImageTen_contentType",additionalImageTen.getContentType());
			paramMap.put("additionalImageTen",ByteBuffer.wrap(additionalImageTen.getBytes()));
		}

		paramMap.put("imageResize",imageResize);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("multipleUploadProductImages", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductType")
	public ResponseEntity<Object> createProductType(HttpSession session, @RequestParam(value="isPhysical", required=false) String isPhysical, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="isDigital", required=false) String isDigital, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="productTypeId", required=false) String productTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("isPhysical",isPhysical);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("isDigital",isDigital);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/safeAddProductToCategory")
	public ResponseEntity<Object> safeAddProductToCategory(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="productId") String productId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("productId",productId);
		paramMap.put("comments",comments);
		paramMap.put("quantity",quantity);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("safeAddProductToCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyToProduct")
	public ResponseEntity<Object> updatePartyToProduct(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productId") String productId, @RequestParam(value="partyId") String partyId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("productId",productId);
		paramMap.put("partyId",partyId);
		paramMap.put("comments",comments);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyToProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductMaint")
	public ResponseEntity<Object> updateProductMaint(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productMaintSeqId") String productMaintSeqId, @RequestParam(value="intervalUomId", required=false) String intervalUomId, @RequestParam(value="maintTemplateWorkEffortId", required=false) String maintTemplateWorkEffortId, @RequestParam(value="maintName", required=false) String maintName, @RequestParam(value="intervalQuantity", required=false) BigDecimal intervalQuantity, @RequestParam(value="productMaintTypeId", required=false) String productMaintTypeId, @RequestParam(value="intervalMeterTypeId", required=false) String intervalMeterTypeId, @RequestParam(value="repeatCount", required=false) Long repeatCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productMaintSeqId",productMaintSeqId);
		paramMap.put("intervalUomId",intervalUomId);
		paramMap.put("maintTemplateWorkEffortId",maintTemplateWorkEffortId);
		paramMap.put("maintName",maintName);
		paramMap.put("intervalQuantity",intervalQuantity);
		paramMap.put("productMaintTypeId",productMaintTypeId);
		paramMap.put("intervalMeterTypeId",intervalMeterTypeId);
		paramMap.put("repeatCount",repeatCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductMaint", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addCustomerDigitalDownloadProductFile")
	public ResponseEntity<Object> addCustomerDigitalDownloadProductFile(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addCustomerDigitalDownloadProductFile", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductMeterType")
	public ResponseEntity<Object> updateProductMeterType(HttpSession session, @RequestParam(value="productMeterTypeId") String productMeterTypeId, @RequestParam(value="defaultUomId", required=false) String defaultUomId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productMeterTypeId",productMeterTypeId);
		paramMap.put("defaultUomId",defaultUomId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductMeterType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProdCatalogInvFacility")
	public ResponseEntity<Object> createProdCatalogInvFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProdCatalogInvFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setProductReviewStatus")
	public ResponseEntity<Object> setProductReviewStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="productReviewId") String productReviewId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("productReviewId",productReviewId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setProductReviewStatus", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustomerDigitalDownloadProduct")
	public ResponseEntity<Object> deleteCustomerDigitalDownloadProduct(HttpSession session, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteCustomerDigitalDownloadProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductCategoryGlAccount")
	public ResponseEntity<Object> deleteProductCategoryGlAccount(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductCategoryGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPaymentMethodType")
	public ResponseEntity<Object> deleteProductPaymentMethodType(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="productPricePurposeId") String productPricePurposeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("productPricePurposeId",productPricePurposeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPaymentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductCategoryType")
	public ResponseEntity<Object> updateProductCategoryType(HttpSession session, @RequestParam(value="productCategoryTypeId") String productCategoryTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryTypeId",productCategoryTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductCategoryType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/prodCatalogToPartyPermissionCheck")
	public ResponseEntity<Object> prodCatalogToPartyPermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("prodCatalogToPartyPermissionCheck", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/safeAddProductCategoryToCategory")
	public ResponseEntity<Object> safeAddProductCategoryToCategory(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="parentProductCategoryId") String parentProductCategoryId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("parentProductCategoryId",parentProductCategoryId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("safeAddProductCategoryToCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductGeo")
	public ResponseEntity<Object> deleteProductGeo(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="geoId") String geoId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("geoId",geoId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductGeo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductCategoryLink")
	public ResponseEntity<Object> deleteProductCategoryLink(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="linkSeqId") String linkSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("linkSeqId",linkSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductCategoryLink", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductContent")
	public ResponseEntity<Object> updateProductContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("contentId",contentId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("useTime",useTime);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCategoryContent")
	public ResponseEntity<Object> updateCategoryContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="prodCatContentTypeId") String prodCatContentTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="contentId") String contentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="useDaysLimit", required=false) BigDecimal useDaysLimit, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("contentId",contentId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("useDaysLimit",useDaysLimit);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCategoryContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductConfig")
	public ResponseEntity<Object> createProductConfig(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="productId") String productId, @RequestParam(value="sequenceNum") Long sequenceNum, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="defaultConfigOptionId", required=false) String defaultConfigOptionId, @RequestParam(value="configTypeId", required=false) String configTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="isMandatory", required=false) String isMandatory, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("productId",productId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("fromDate",fromDate);
		paramMap.put("longDescription",longDescription);
		paramMap.put("defaultConfigOptionId",defaultConfigOptionId);
		paramMap.put("configTypeId",configTypeId);
		paramMap.put("description",description);
		paramMap.put("isMandatory",isMandatory);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductConfig", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductQuickAdminName")
	public ResponseEntity<Object> updateProductQuickAdminName(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="originGeoId", required=false) String originGeoId, @RequestParam(value="detailScreen", required=false) String detailScreen, @RequestParam(value="requireInventory", required=false) String requireInventory, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="mediumImageUrl", required=false) String mediumImageUrl, @RequestParam(value="productName", required=false) String productName, @RequestParam(value="introductionDate", required=false) Timestamp introductionDate, @RequestParam(value="shippingHeight", required=false) BigDecimal shippingHeight, @RequestParam(value="originalImageUrl", required=false) String originalImageUrl, @RequestParam(value="inShippingBox", required=false) String inShippingBox, @RequestParam(value="detailImageUrl", required=false) String detailImageUrl, @RequestParam(value="supportDiscontinuationDate", required=false) Timestamp supportDiscontinuationDate, @RequestParam(value="productWidth", required=false) BigDecimal productWidth, @RequestParam(value="includeInPromotions", required=false) String includeInPromotions, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="billOfMaterialLevel", required=false) Long billOfMaterialLevel, @RequestParam(value="lotIdFilledIn", required=false) String lotIdFilledIn, @RequestParam(value="productRating", required=false) BigDecimal productRating, @RequestParam(value="manufacturerPartyId", required=false) String manufacturerPartyId, @RequestParam(value="brandName", required=false) String brandName, @RequestParam(value="requireAmount", required=false) String requireAmount, @RequestParam(value="smallImageUrl", required=false) String smallImageUrl, @RequestParam(value="taxable", required=false) String taxable, @RequestParam(value="primaryProductCategoryId", required=false) String primaryProductCategoryId, @RequestParam(value="salesDiscontinuationDate", required=false) Timestamp salesDiscontinuationDate, @RequestParam(value="salesDiscWhenNotAvail", required=false) String salesDiscWhenNotAvail, @RequestParam(value="returnable", required=false) String returnable, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="isVirtual", required=false) String isVirtual, @RequestParam(value="priceDetailText", required=false) String priceDetailText, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="widthUomId", required=false) String widthUomId, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="diameterUomId", required=false) String diameterUomId, @RequestParam(value="autoCreateKeywords", required=false) String autoCreateKeywords, @RequestParam(value="amountUomTypeId", required=false) String amountUomTypeId, @RequestParam(value="productDiameter", required=false) BigDecimal productDiameter, @RequestParam(value="ratingTypeEnum", required=false) String ratingTypeEnum, @RequestParam(value="description", required=false) String description, @RequestParam(value="chargeShipping", required=false) String chargeShipping, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="quantityIncluded", required=false) BigDecimal quantityIncluded, @RequestParam(value="heightUomId", required=false) String heightUomId, @RequestParam(value="internalName", required=false) String internalName, @RequestParam(value="virtualVariantMethodEnum", required=false) String virtualVariantMethodEnum, @RequestParam(value="shippingWeight", required=false) BigDecimal shippingWeight, @RequestParam(value="shippingWidth", required=false) BigDecimal shippingWidth, @RequestParam(value="shippingDepth", required=false) BigDecimal shippingDepth, @RequestParam(value="reservMaxPersons", required=false) BigDecimal reservMaxPersons, @RequestParam(value="fixedAmount", required=false) BigDecimal fixedAmount, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId, @RequestParam(value="piecesIncluded", required=false) Long piecesIncluded, @RequestParam(value="productDepth", required=false) BigDecimal productDepth, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="releaseDate", required=false) Timestamp releaseDate, @RequestParam(value="productHeight", required=false) BigDecimal productHeight, @RequestParam(value="defaultShipmentBoxTypeId", required=false) String defaultShipmentBoxTypeId, @RequestParam(value="orderDecimalQuantity", required=false) String orderDecimalQuantity, @RequestParam(value="inventoryMessage", required=false) String inventoryMessage, @RequestParam(value="productWeight", required=false) BigDecimal productWeight, @RequestParam(value="depthUomId", required=false) String depthUomId, @RequestParam(value="productTypeId", required=false) String productTypeId, @RequestParam(value="isVariant", required=false) String isVariant, @RequestParam(value="largeImageUrl", required=false) String largeImageUrl, @RequestParam(value="requirementMethodEnumId", required=false) String requirementMethodEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("originGeoId",originGeoId);
		paramMap.put("detailScreen",detailScreen);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("mediumImageUrl",mediumImageUrl);
		paramMap.put("productName",productName);
		paramMap.put("introductionDate",introductionDate);
		paramMap.put("shippingHeight",shippingHeight);
		paramMap.put("originalImageUrl",originalImageUrl);
		paramMap.put("inShippingBox",inShippingBox);
		paramMap.put("detailImageUrl",detailImageUrl);
		paramMap.put("supportDiscontinuationDate",supportDiscontinuationDate);
		paramMap.put("productWidth",productWidth);
		paramMap.put("includeInPromotions",includeInPromotions);
		paramMap.put("configId",configId);
		paramMap.put("billOfMaterialLevel",billOfMaterialLevel);
		paramMap.put("lotIdFilledIn",lotIdFilledIn);
		paramMap.put("productRating",productRating);
		paramMap.put("manufacturerPartyId",manufacturerPartyId);
		paramMap.put("brandName",brandName);
		paramMap.put("requireAmount",requireAmount);
		paramMap.put("smallImageUrl",smallImageUrl);
		paramMap.put("taxable",taxable);
		paramMap.put("primaryProductCategoryId",primaryProductCategoryId);
		paramMap.put("salesDiscontinuationDate",salesDiscontinuationDate);
		paramMap.put("salesDiscWhenNotAvail",salesDiscWhenNotAvail);
		paramMap.put("returnable",returnable);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("isVirtual",isVirtual);
		paramMap.put("priceDetailText",priceDetailText);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("widthUomId",widthUomId);
		paramMap.put("longDescription",longDescription);
		paramMap.put("diameterUomId",diameterUomId);
		paramMap.put("autoCreateKeywords",autoCreateKeywords);
		paramMap.put("amountUomTypeId",amountUomTypeId);
		paramMap.put("productDiameter",productDiameter);
		paramMap.put("ratingTypeEnum",ratingTypeEnum);
		paramMap.put("description",description);
		paramMap.put("chargeShipping",chargeShipping);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("quantityIncluded",quantityIncluded);
		paramMap.put("heightUomId",heightUomId);
		paramMap.put("internalName",internalName);
		paramMap.put("virtualVariantMethodEnum",virtualVariantMethodEnum);
		paramMap.put("shippingWeight",shippingWeight);
		paramMap.put("shippingWidth",shippingWidth);
		paramMap.put("shippingDepth",shippingDepth);
		paramMap.put("reservMaxPersons",reservMaxPersons);
		paramMap.put("fixedAmount",fixedAmount);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("piecesIncluded",piecesIncluded);
		paramMap.put("productDepth",productDepth);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("releaseDate",releaseDate);
		paramMap.put("productHeight",productHeight);
		paramMap.put("defaultShipmentBoxTypeId",defaultShipmentBoxTypeId);
		paramMap.put("orderDecimalQuantity",orderDecimalQuantity);
		paramMap.put("inventoryMessage",inventoryMessage);
		paramMap.put("productWeight",productWeight);
		paramMap.put("depthUomId",depthUomId);
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("isVariant",isVariant);
		paramMap.put("largeImageUrl",largeImageUrl);
		paramMap.put("requirementMethodEnumId",requirementMethodEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductQuickAdminName", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductContentForImageManagement")
	public ResponseEntity<Object> removeProductContentForImageManagement(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductContentForImageManagement", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addRejectedReasonImageManagement")
	public ResponseEntity<Object> addRejectedReasonImageManagement(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addRejectedReasonImageManagement", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSupplierProductFeature")
	public ResponseEntity<Object> createSupplierProductFeature(HttpSession session, @RequestParam(value="productFeatureId") String productFeatureId, @RequestParam(value="partyId") String partyId, @RequestParam(value="idCode", required=false) String idCode, @RequestParam(value="description", required=false) String description, @RequestParam(value="uomId", required=false) String uomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("partyId",partyId);
		paramMap.put("idCode",idCode);
		paramMap.put("description",description);
		paramMap.put("uomId",uomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSupplierProductFeature", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeCategoryContent")
	public ResponseEntity<Object> removeCategoryContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="prodCatContentTypeId") String prodCatContentTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCategoryContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductGroupOrder")
	public ResponseEntity<Object> createProductGroupOrder(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="jobId", required=false) String jobId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="reqOrderQty", required=false) BigDecimal reqOrderQty, @RequestParam(value="soldOrderQty", required=false) BigDecimal soldOrderQty, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("jobId",jobId);
		paramMap.put("productId",productId);
		paramMap.put("statusId",statusId);
		paramMap.put("reqOrderQty",reqOrderQty);
		paramMap.put("soldOrderQty",soldOrderQty);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductGroupOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProdCatalogToParty")
	public ResponseEntity<Object> updateProdCatalogToParty(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProdCatalogToParty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/productGenericPermission")
	public ResponseEntity<Object> productGenericPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productGenericPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/resizeImageOfProduct")
	public ResponseEntity<Object> resizeImageOfProduct(HttpSession session, @RequestParam(value="dataResourceName") String dataResourceName, @RequestParam(value="productId") String productId, @RequestParam(value="resizeWidth") String resizeWidth) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("productId",productId);
		paramMap.put("resizeWidth",resizeWidth);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("resizeImageOfProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addProductToCategories")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
	public ResponseEntity<Object> addProductToCategories(HttpSession session,
                                                         @RequestParam String productId,
														 @RequestBody List<String> categoryIds){


		List<ProductCategory> categories = new LinkedList<>();
		for(int i = 0; i < categoryIds.size(); i++){
			ProductCategory category = new ProductCategory();
			category.setProductCategoryId(categoryIds.get(i));
			categories.add(category);
		}

		try {
			Product product = new Product(productId, new FindProductsBy(UtilMisc.toMap("productId", productId)));
			product.addToCategories(session, categories);
		} catch (ServiceAuthException e) {
			return unauthorized();
		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
            return badRequest();
		}

		return successful();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removePartyFromCategory")
	public ResponseEntity<Object> removePartyFromCategory(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePartyFromCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductAssoc")
	public ResponseEntity<Object> deleteProductAssoc(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="productIdTo") String productIdTo, @RequestParam(value="productAssocTypeId") String productAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("productIdTo",productIdTo);
		paramMap.put("productAssocTypeId",productAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductReview")
	public ResponseEntity<Object> updateProductReview(HttpSession session, @RequestParam(value="productReviewId") String productReviewId, @RequestParam(value="userLoginId", required=false) String userLoginId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="postedDateTime", required=false) Timestamp postedDateTime, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="postedAnonymous", required=false) String postedAnonymous, @RequestParam(value="productRating", required=false) BigDecimal productRating, @RequestParam(value="productReview", required=false) String productReview) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productReviewId",productReviewId);
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("productId",productId);
		paramMap.put("statusId",statusId);
		paramMap.put("postedDateTime",postedDateTime);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("postedAnonymous",postedAnonymous);
		paramMap.put("productRating",productRating);
		paramMap.put("productReview",productReview);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductReview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductPrice")
	public ResponseEntity<Object> deleteProductPrice(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="productId") String productId, @RequestParam(value="productPricePurposeId") String productPricePurposeId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="productPriceTypeId") String productPriceTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("productId",productId);
		paramMap.put("productPricePurposeId",productPricePurposeId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("productPriceTypeId",productPriceTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductPrice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSupplierProductFeature")
	public ResponseEntity<Object> updateSupplierProductFeature(HttpSession session, @RequestParam(value="productFeatureId") String productFeatureId, @RequestParam(value="partyId") String partyId, @RequestParam(value="idCode", required=false) String idCode, @RequestParam(value="description", required=false) String description, @RequestParam(value="uomId", required=false) String uomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("partyId",partyId);
		paramMap.put("idCode",idCode);
		paramMap.put("description",description);
		paramMap.put("uomId",uomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSupplierProductFeature", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteWebAnalyticsConfig")
	public ResponseEntity<Object> deleteWebAnalyticsConfig(HttpSession session, @RequestParam(value="webAnalyticsTypeId") String webAnalyticsTypeId, @RequestParam(value="webSiteId") String webSiteId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webAnalyticsTypeId",webAnalyticsTypeId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteWebAnalyticsConfig", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getSupplierProductFeatures")
	public ResponseEntity<Object> getSupplierProductFeatures(HttpSession session, @RequestParam(value="productFeatureId") String productFeatureId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getSupplierProductFeatures", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/findProductById")
	public ResponseEntity<Object> findProductById(HttpSession session, @RequestParam(value="idToFind") String idToFind, @RequestParam(value="searchAllId", required=false) String searchAllId, @RequestParam(value="goodIdentificationTypeId", required=false) String goodIdentificationTypeId, @RequestParam(value="searchProductFirst", required=false) String searchProductFirst) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("idToFind",idToFind);
		paramMap.put("searchAllId",searchAllId);
		paramMap.put("goodIdentificationTypeId",goodIdentificationTypeId);
		paramMap.put("searchProductFirst",searchProductFirst);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findProductById", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductCategoryTypeAttr")
	public ResponseEntity<Object> updateProductCategoryTypeAttr(HttpSession session, @RequestParam(value="productCategoryTypeId") String productCategoryTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryTypeId",productCategoryTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductCategoryTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductCategoryContentType")
	public ResponseEntity<Object> updateProductCategoryContentType(HttpSession session, @RequestParam(value="prodCatContentTypeId") String prodCatContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductCategoryContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductCategoryType")
	public ResponseEntity<Object> createProductCategoryType(HttpSession session, @RequestParam(value="productCategoryTypeId", required=false) String productCategoryTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryTypeId",productCategoryTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductCategoryType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getAllExistingVariants")
	public ResponseEntity<Object> getAllExistingVariants(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productFeatureAppls") List productFeatureAppls) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productFeatureAppls",productFeatureAppls);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getAllExistingVariants", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductGeo")
	public ResponseEntity<Object> updateProductGeo(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="geoId") String geoId, @RequestParam(value="description", required=false) String description, @RequestParam(value="productGeoEnumId", required=false) String productGeoEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("geoId",geoId);
		paramMap.put("description",description);
		paramMap.put("productGeoEnumId",productGeoEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductGeo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createMissingCategoryAndProductAltUrls")
	public ResponseEntity<Object> createMissingCategoryAndProductAltUrls(HttpSession session, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="product", required=false) String product, @RequestParam(value="category", required=false) String category) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("product",product);
		paramMap.put("category",category);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createMissingCategoryAndProductAltUrls", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProdCatalogCategoryType")
	public ResponseEntity<Object> deleteProdCatalogCategoryType(HttpSession session, @RequestParam(value="prodCatalogCategoryTypeId") String prodCatalogCategoryTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatalogCategoryTypeId",prodCatalogCategoryTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProdCatalogCategoryType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createJobForProductGroupOrder")
	public ResponseEntity<Object> createJobForProductGroupOrder(HttpSession session, @RequestParam(value="groupOrderId") String groupOrderId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="jobId", required=false) String jobId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="reqOrderQty", required=false) BigDecimal reqOrderQty, @RequestParam(value="soldOrderQty", required=false) BigDecimal soldOrderQty, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupOrderId",groupOrderId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("jobId",jobId);
		paramMap.put("productId",productId);
		paramMap.put("statusId",statusId);
		paramMap.put("reqOrderQty",reqOrderQty);
		paramMap.put("soldOrderQty",soldOrderQty);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createJobForProductGroupOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createNewImageThumbnail")
	public ResponseEntity<Object> createNewImageThumbnail(HttpSession session, @RequestParam(value="dataResourceName") String dataResourceName, @RequestParam(value="sizeWidth") String sizeWidth, @RequestParam(value="productId") String productId, @RequestParam(value="drObjectInfo") String drObjectInfo, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("sizeWidth",sizeWidth);
		paramMap.put("productId",productId);
		paramMap.put("drObjectInfo",drObjectInfo);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createNewImageThumbnail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProdCatalogInvFacility")
	public ResponseEntity<Object> updateProdCatalogInvFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProdCatalogInvFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductKeywords")
	public ResponseEntity<Object> deleteProductKeywords(HttpSession session, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductKeywords", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductContentType")
	public ResponseEntity<Object> deleteProductContentType(HttpSession session, @RequestParam(value="productContentTypeId") String productContentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductCategoryToProdCatalog")
	public ResponseEntity<Object> updateProductCategoryToProdCatalog(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="prodCatalogCategoryTypeId") String prodCatalogCategoryTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("prodCatalogCategoryTypeId",prodCatalogCategoryTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductCategoryToProdCatalog", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeImageBySize")
	public ResponseEntity<Object> removeImageBySize(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="mapKey") String mapKey) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("mapKey",mapKey);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeImageBySize", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProdCatalog")
	public ResponseEntity<Object> deleteProdCatalog(HttpSession session, @RequestParam(value="prodCatalogId") String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProdCatalog", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSimpleTextContentForAlternateLocale")
	public ResponseEntity<Object> createSimpleTextContentForAlternateLocale(HttpSession session, @RequestParam(value="localeString") String localeString, @RequestParam(value="mainContentId") String mainContentId, @RequestParam(value="text") String text, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("localeString",localeString);
		paramMap.put("mainContentId",mainContentId);
		paramMap.put("text",text);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("contentName",contentName);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSimpleTextContentForAlternateLocale", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSupplierProduct")
	public ResponseEntity<Object> createSupplierProduct(HttpSession session, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="productId") String productId, @RequestParam(value="availableFromDate") Timestamp availableFromDate, @RequestParam(value="minimumOrderQuantity") BigDecimal minimumOrderQuantity, @RequestParam(value="partyId") String partyId, @RequestParam(value="supplierProductId") String supplierProductId, @RequestParam(value="lastPrice") BigDecimal lastPrice, @RequestParam(value="supplierProductName", required=false) String supplierProductName, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="canDropShip", required=false) String canDropShip, @RequestParam(value="supplierRatingTypeId", required=false) String supplierRatingTypeId, @RequestParam(value="agreementItemSeqId", required=false) String agreementItemSeqId, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="supplierPrefOrderId", required=false) String supplierPrefOrderId, @RequestParam(value="unitsIncluded", required=false) BigDecimal unitsIncluded, @RequestParam(value="orderQtyIncrements", required=false) BigDecimal orderQtyIncrements, @RequestParam(value="shippingPrice", required=false) BigDecimal shippingPrice, @RequestParam(value="agreementId", required=false) String agreementId, @RequestParam(value="availableThruDate", required=false) Timestamp availableThruDate, @RequestParam(value="standardLeadTimeDays", required=false) BigDecimal standardLeadTimeDays) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("productId",productId);
		paramMap.put("availableFromDate",availableFromDate);
		paramMap.put("minimumOrderQuantity",minimumOrderQuantity);
		paramMap.put("partyId",partyId);
		paramMap.put("supplierProductId",supplierProductId);
		paramMap.put("lastPrice",lastPrice);
		paramMap.put("supplierProductName",supplierProductName);
		paramMap.put("comments",comments);
		paramMap.put("canDropShip",canDropShip);
		paramMap.put("supplierRatingTypeId",supplierRatingTypeId);
		paramMap.put("agreementItemSeqId",agreementItemSeqId);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("supplierPrefOrderId",supplierPrefOrderId);
		paramMap.put("unitsIncluded",unitsIncluded);
		paramMap.put("orderQtyIncrements",orderQtyIncrements);
		paramMap.put("shippingPrice",shippingPrice);
		paramMap.put("agreementId",agreementId);
		paramMap.put("availableThruDate",availableThruDate);
		paramMap.put("standardLeadTimeDays",standardLeadTimeDays);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSupplierProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductTypeAttr")
	public ResponseEntity<Object> createProductTypeAttr(HttpSession session, @RequestParam(value="productTypeId") String productTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeSupplierProduct")
	public ResponseEntity<Object> removeSupplierProduct(HttpSession session, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="productId") String productId, @RequestParam(value="availableFromDate") Timestamp availableFromDate, @RequestParam(value="minimumOrderQuantity") BigDecimal minimumOrderQuantity, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("productId",productId);
		paramMap.put("availableFromDate",availableFromDate);
		paramMap.put("minimumOrderQuantity",minimumOrderQuantity);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeSupplierProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductInCategory")
	public ResponseEntity<Object> createProductInCategory(HttpSession session, @RequestParam(value="productFeatureIdByType") java.util.Map productFeatureIdByType, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="productFeatureSelectableByType") java.util.Map productFeatureSelectableByType, @RequestParam(value="originGeoId", required=false) String originGeoId, @RequestParam(value="detailScreen", required=false) String detailScreen, @RequestParam(value="requireInventory", required=false) String requireInventory, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="mediumImageUrl", required=false) String mediumImageUrl, @RequestParam(value="productName", required=false) String productName, @RequestParam(value="introductionDate", required=false) Timestamp introductionDate, @RequestParam(value="shippingHeight", required=false) BigDecimal shippingHeight, @RequestParam(value="originalImageUrl", required=false) String originalImageUrl, @RequestParam(value="inShippingBox", required=false) String inShippingBox, @RequestParam(value="detailImageUrl", required=false) String detailImageUrl, @RequestParam(value="supportDiscontinuationDate", required=false) Timestamp supportDiscontinuationDate, @RequestParam(value="productWidth", required=false) BigDecimal productWidth, @RequestParam(value="includeInPromotions", required=false) String includeInPromotions, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="billOfMaterialLevel", required=false) Long billOfMaterialLevel, @RequestParam(value="lotIdFilledIn", required=false) String lotIdFilledIn, @RequestParam(value="productRating", required=false) BigDecimal productRating, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="manufacturerPartyId", required=false) String manufacturerPartyId, @RequestParam(value="brandName", required=false) String brandName, @RequestParam(value="requireAmount", required=false) String requireAmount, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="smallImageUrl", required=false) String smallImageUrl, @RequestParam(value="taxable", required=false) String taxable, @RequestParam(value="primaryProductCategoryId", required=false) String primaryProductCategoryId, @RequestParam(value="salesDiscontinuationDate", required=false) Timestamp salesDiscontinuationDate, @RequestParam(value="salesDiscWhenNotAvail", required=false) String salesDiscWhenNotAvail, @RequestParam(value="returnable", required=false) String returnable, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="isVirtual", required=false) String isVirtual, @RequestParam(value="priceDetailText", required=false) String priceDetailText, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="widthUomId", required=false) String widthUomId, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="diameterUomId", required=false) String diameterUomId, @RequestParam(value="autoCreateKeywords", required=false) String autoCreateKeywords, @RequestParam(value="amountUomTypeId", required=false) String amountUomTypeId, @RequestParam(value="productDiameter", required=false) BigDecimal productDiameter, @RequestParam(value="ratingTypeEnum", required=false) String ratingTypeEnum, @RequestParam(value="description", required=false) String description, @RequestParam(value="chargeShipping", required=false) String chargeShipping, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="quantityIncluded", required=false) BigDecimal quantityIncluded, @RequestParam(value="heightUomId", required=false) String heightUomId, @RequestParam(value="internalName", required=false) String internalName, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="virtualVariantMethodEnum", required=false) String virtualVariantMethodEnum, @RequestParam(value="shippingWeight", required=false) BigDecimal shippingWeight, @RequestParam(value="shippingWidth", required=false) BigDecimal shippingWidth, @RequestParam(value="shippingDepth", required=false) BigDecimal shippingDepth, @RequestParam(value="reservMaxPersons", required=false) BigDecimal reservMaxPersons, @RequestParam(value="fixedAmount", required=false) BigDecimal fixedAmount, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId, @RequestParam(value="piecesIncluded", required=false) Long piecesIncluded, @RequestParam(value="productDepth", required=false) BigDecimal productDepth, @RequestParam(value="defaultPrice", required=false) BigDecimal defaultPrice, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="releaseDate", required=false) Timestamp releaseDate, @RequestParam(value="productHeight", required=false) BigDecimal productHeight, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="defaultShipmentBoxTypeId", required=false) String defaultShipmentBoxTypeId, @RequestParam(value="orderDecimalQuantity", required=false) String orderDecimalQuantity, @RequestParam(value="inventoryMessage", required=false) String inventoryMessage, @RequestParam(value="productWeight", required=false) BigDecimal productWeight, @RequestParam(value="depthUomId", required=false) String depthUomId, @RequestParam(value="productTypeId", required=false) String productTypeId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="isVariant", required=false) String isVariant, @RequestParam(value="largeImageUrl", required=false) String largeImageUrl, @RequestParam(value="requirementMethodEnumId", required=false) String requirementMethodEnumId, @RequestParam(value="averageCost", required=false) BigDecimal averageCost) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productFeatureIdByType",productFeatureIdByType);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("productFeatureSelectableByType",productFeatureSelectableByType);
		paramMap.put("originGeoId",originGeoId);
		paramMap.put("detailScreen",detailScreen);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("mediumImageUrl",mediumImageUrl);
		paramMap.put("productName",productName);
		paramMap.put("introductionDate",introductionDate);
		paramMap.put("shippingHeight",shippingHeight);
		paramMap.put("originalImageUrl",originalImageUrl);
		paramMap.put("inShippingBox",inShippingBox);
		paramMap.put("detailImageUrl",detailImageUrl);
		paramMap.put("supportDiscontinuationDate",supportDiscontinuationDate);
		paramMap.put("productWidth",productWidth);
		paramMap.put("includeInPromotions",includeInPromotions);
		paramMap.put("configId",configId);
		paramMap.put("billOfMaterialLevel",billOfMaterialLevel);
		paramMap.put("lotIdFilledIn",lotIdFilledIn);
		paramMap.put("productRating",productRating);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("manufacturerPartyId",manufacturerPartyId);
		paramMap.put("brandName",brandName);
		paramMap.put("requireAmount",requireAmount);
		paramMap.put("productId",productId);
		paramMap.put("smallImageUrl",smallImageUrl);
		paramMap.put("taxable",taxable);
		paramMap.put("primaryProductCategoryId",primaryProductCategoryId);
		paramMap.put("salesDiscontinuationDate",salesDiscontinuationDate);
		paramMap.put("salesDiscWhenNotAvail",salesDiscWhenNotAvail);
		paramMap.put("returnable",returnable);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("isVirtual",isVirtual);
		paramMap.put("priceDetailText",priceDetailText);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("widthUomId",widthUomId);
		paramMap.put("longDescription",longDescription);
		paramMap.put("diameterUomId",diameterUomId);
		paramMap.put("autoCreateKeywords",autoCreateKeywords);
		paramMap.put("amountUomTypeId",amountUomTypeId);
		paramMap.put("productDiameter",productDiameter);
		paramMap.put("ratingTypeEnum",ratingTypeEnum);
		paramMap.put("description",description);
		paramMap.put("chargeShipping",chargeShipping);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("quantityIncluded",quantityIncluded);
		paramMap.put("heightUomId",heightUomId);
		paramMap.put("internalName",internalName);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("virtualVariantMethodEnum",virtualVariantMethodEnum);
		paramMap.put("shippingWeight",shippingWeight);
		paramMap.put("shippingWidth",shippingWidth);
		paramMap.put("shippingDepth",shippingDepth);
		paramMap.put("reservMaxPersons",reservMaxPersons);
		paramMap.put("fixedAmount",fixedAmount);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("piecesIncluded",piecesIncluded);
		paramMap.put("productDepth",productDepth);
		paramMap.put("defaultPrice",defaultPrice);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("releaseDate",releaseDate);
		paramMap.put("productHeight",productHeight);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("defaultShipmentBoxTypeId",defaultShipmentBoxTypeId);
		paramMap.put("orderDecimalQuantity",orderDecimalQuantity);
		paramMap.put("inventoryMessage",inventoryMessage);
		paramMap.put("productWeight",productWeight);
		paramMap.put("depthUomId",depthUomId);
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("isVariant",isVariant);
		paramMap.put("largeImageUrl",largeImageUrl);
		paramMap.put("requirementMethodEnumId",requirementMethodEnumId);
		paramMap.put("averageCost",averageCost);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductInCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/countProductView")
	public ResponseEntity<Object> countProductView(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="weight", required=false) Long weight) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("weight",weight);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("countProductView", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductContentType")
	public ResponseEntity<Object> updateProductContentType(HttpSession session, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductConfigItem")
	public ResponseEntity<Object> createProductConfigItem(HttpSession session, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="imageUrl", required=false) String imageUrl, @RequestParam(value="configItemName", required=false) String configItemName, @RequestParam(value="configItemTypeId", required=false) String configItemTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("longDescription",longDescription);
		paramMap.put("imageUrl",imageUrl);
		paramMap.put("configItemName",configItemName);
		paramMap.put("configItemTypeId",configItemTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductConfigItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductContent")
	public ResponseEntity<Object> createProductContent(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentId",contentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("useTime",useTime);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSimpleTextContentForCategory")
	public ResponseEntity<Object> createSimpleTextContentForCategory(HttpSession session, @RequestParam(value="prodCatContentTypeId") String prodCatContentTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="text") String text, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="useDaysLimit", required=false) BigDecimal useDaysLimit, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("text",text);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentId",contentId);
		paramMap.put("useDaysLimit",useDaysLimit);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSimpleTextContentForCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSimpleTextContentForCategory")
	public ResponseEntity<Object> updateSimpleTextContentForCategory(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="prodCatContentTypeId") String prodCatContentTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="contentId") String contentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="useDaysLimit", required=false) BigDecimal useDaysLimit, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="text", required=false) String text, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="textDataResourceId", required=false) String textDataResourceId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("contentId",contentId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("useDaysLimit",useDaysLimit);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("text",text);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("textDataResourceId",textDataResourceId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSimpleTextContentForCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductAttribute")
	public ResponseEntity<Object> createProductAttribute(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue, @RequestParam(value="attrType", required=false) Long attrType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("attrType",attrType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPromoContent")
	public ResponseEntity<Object> createProductPromoContent(HttpSession session, @RequestParam(value="productPromoContentTypeId") String productPromoContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoContentTypeId",productPromoContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPromoContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/saveProductPriceChange")
	public ResponseEntity<Object> saveProductPriceChange(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="productId") String productId, @RequestParam(value="productPricePurposeId") String productPricePurposeId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="productPriceTypeId") String productPriceTypeId, @RequestParam(value="taxInPrice", required=false) String taxInPrice, @RequestParam(value="termUomId", required=false) String termUomId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="oldPrice", required=false) BigDecimal oldPrice, @RequestParam(value="priceWithTax", required=false) BigDecimal priceWithTax, @RequestParam(value="taxPercentage", required=false) BigDecimal taxPercentage, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="customPriceCalcService", required=false) String customPriceCalcService, @RequestParam(value="price", required=false) BigDecimal price, @RequestParam(value="priceWithoutTax", required=false) BigDecimal priceWithoutTax, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="taxAmount", required=false) BigDecimal taxAmount, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("productId",productId);
		paramMap.put("productPricePurposeId",productPricePurposeId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("productPriceTypeId",productPriceTypeId);
		paramMap.put("taxInPrice",taxInPrice);
		paramMap.put("termUomId",termUomId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("oldPrice",oldPrice);
		paramMap.put("priceWithTax",priceWithTax);
		paramMap.put("taxPercentage",taxPercentage);
		paramMap.put("thruDate",thruDate);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("customPriceCalcService",customPriceCalcService);
		paramMap.put("price",price);
		paramMap.put("priceWithoutTax",priceWithoutTax);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("taxAmount",taxAmount);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("saveProductPriceChange", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/setImageDetail")
	public ResponseEntity<Object> setImageDetail(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="description", required=false) String description, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="drIsPublic", required=false) String drIsPublic) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("contentId",contentId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("useTime",useTime);
		paramMap.put("description",description);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("thruDate",thruDate);
		paramMap.put("drIsPublic",drIsPublic);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setImageDetail", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductGeo")
	public ResponseEntity<Object> createProductGeo(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="geoId") String geoId, @RequestParam(value="description", required=false) String description, @RequestParam(value="productGeoEnumId", required=false) String productGeoEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("geoId",geoId);
		paramMap.put("description",description);
		paramMap.put("productGeoEnumId",productGeoEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductGeo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProdCatalogCategoryType")
	public ResponseEntity<Object> createProdCatalogCategoryType(HttpSession session, @RequestParam(value="prodCatalogCategoryTypeId", required=false) String prodCatalogCategoryTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatalogCategoryTypeId",prodCatalogCategoryTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProdCatalogCategoryType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addImageForProductPromo")
	public ResponseEntity<Object> addImageForProductPromo(HttpSession session, @RequestParam(value="productPromoContentTypeId") String productPromoContentTypeId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productPromoContentTypeId",productPromoContentTypeId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("contentId",contentId);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addImageForProductPromo", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductMeterType")
	public ResponseEntity<Object> deleteProductMeterType(HttpSession session, @RequestParam(value="productMeterTypeId") String productMeterTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productMeterTypeId",productMeterTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductMeterType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getVariantCombinations")
	public ResponseEntity<Object> getVariantCombinations(HttpSession session, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getVariantCombinations", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductConfigOption")
	public ResponseEntity<Object> updateProductConfigOption(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="configOptionId") String configOptionId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="configOptionName", required=false) String configOptionName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("configOptionId",configOptionId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("configOptionName",configOptionName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductConfigOption", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPrice")
	public ResponseEntity<Object> updateProductPrice(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="productId") String productId, @RequestParam(value="price") BigDecimal price, @RequestParam(value="productPricePurposeId") String productPricePurposeId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="productPriceTypeId") String productPriceTypeId, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="taxInPrice", required=false) String taxInPrice, @RequestParam(value="termUomId", required=false) String termUomId, @RequestParam(value="customPriceCalcService", required=false) String customPriceCalcService, @RequestParam(value="taxPercentage", required=false) BigDecimal taxPercentage, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("productId",productId);
		paramMap.put("price",price);
		paramMap.put("productPricePurposeId",productPricePurposeId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("productPriceTypeId",productPriceTypeId);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("taxInPrice",taxInPrice);
		paramMap.put("termUomId",termUomId);
		paramMap.put("customPriceCalcService",customPriceCalcService);
		paramMap.put("taxPercentage",taxPercentage);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPrice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGoodIdentification")
	public ResponseEntity<Object> deleteGoodIdentification(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="goodIdentificationTypeId") String goodIdentificationTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("goodIdentificationTypeId",goodIdentificationTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGoodIdentification", paramMap);
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

	@PostMapping("/addProductToCategory")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
	public ResponseEntity<Object> addProductToCategory(HttpSession session,
                                                       @RequestParam String productId,
                                                       @RequestParam String categoryId) throws GenericServiceException {
		

		try {
		    Product product = new Product();
		    product.setProductId(productId);
		    ProductCategory category = new ProductCategory();
			category.setProductCategoryId(categoryId);
	        product.addToCategory(session, category);
		} catch (ServiceAuthException e) {
			return unauthorized();
		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
            return badRequest();
		}

        return successful();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductGlAccount")
	public ResponseEntity<Object> updateProductGlAccount(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/AddProductToBestSellCategory")
	public ResponseEntity<Object> AddProductToBestSellCategory(HttpSession session, @RequestParam(value="week") Long week, @RequestParam(value="year") Long year, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="prodCatalogId") String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("week",week);
		paramMap.put("year",year);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("AddProductToBestSellCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductMeter")
	public ResponseEntity<Object> createProductMeter(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productMeterTypeId") String productMeterTypeId, @RequestParam(value="meterName", required=false) String meterName, @RequestParam(value="meterUomId", required=false) String meterUomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productMeterTypeId",productMeterTypeId);
		paramMap.put("meterName",meterName);
		paramMap.put("meterUomId",meterUomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductMeter", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateMarketInterest")
	public ResponseEntity<Object> updateMarketInterest(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="partyClassificationGroupId") String partyClassificationGroupId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("partyClassificationGroupId",partyClassificationGroupId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateMarketInterest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateDownloadContentForProduct")
	public ResponseEntity<Object> updateDownloadContentForProduct(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="file", required=false) String file, @RequestParam(value="fileDataResourceId", required=false) String fileDataResourceId, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("contentId",contentId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("file",file);
		paramMap.put("fileDataResourceId",fileDataResourceId);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("useTime",useTime);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDownloadContentForProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductCategoryAttribute")
	public ResponseEntity<Object> updateProductCategoryAttribute(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductCategoryAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/cancleOrderItemGroupOrder")
	public ResponseEntity<Object> cancleOrderItemGroupOrder(HttpSession session, @RequestParam(value="orderId") String orderId, @RequestParam(value="orderItemSeqId", required=false) String orderItemSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("orderItemSeqId",orderItemSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("cancleOrderItemGroupOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductMeter")
	public ResponseEntity<Object> updateProductMeter(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productMeterTypeId") String productMeterTypeId, @RequestParam(value="meterName", required=false) String meterName, @RequestParam(value="meterUomId", required=false) String meterUomId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productMeterTypeId",productMeterTypeId);
		paramMap.put("meterName",meterName);
		paramMap.put("meterUomId",meterUomId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductMeter", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPrice")
	public ResponseEntity<Object> createProductPrice(HttpSession session, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="productId") String productId, @RequestParam(value="price") BigDecimal price, @RequestParam(value="productPricePurposeId") String productPricePurposeId, @RequestParam(value="productStoreGroupId") String productStoreGroupId, @RequestParam(value="productPriceTypeId") String productPriceTypeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="taxAuthPartyId", required=false) String taxAuthPartyId, @RequestParam(value="taxInPrice", required=false) String taxInPrice, @RequestParam(value="termUomId", required=false) String termUomId, @RequestParam(value="customPriceCalcService", required=false) String customPriceCalcService, @RequestParam(value="taxPercentage", required=false) BigDecimal taxPercentage, @RequestParam(value="taxAuthGeoId", required=false) String taxAuthGeoId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("productId",productId);
		paramMap.put("price",price);
		paramMap.put("productPricePurposeId",productPricePurposeId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("productPriceTypeId",productPriceTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("taxAuthPartyId",taxAuthPartyId);
		paramMap.put("taxInPrice",taxInPrice);
		paramMap.put("termUomId",termUomId);
		paramMap.put("customPriceCalcService",customPriceCalcService);
		paramMap.put("taxPercentage",taxPercentage);
		paramMap.put("taxAuthGeoId",taxAuthGeoId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPrice", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductKeyword")
	public ResponseEntity<Object> createProductKeyword(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="keywordTypeId") String keywordTypeId, @RequestParam(value="keyword") String keyword, @RequestParam(value="relevancyWeight", required=false) Long relevancyWeight, @RequestParam(value="statusId", required=false) String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("keywordTypeId",keywordTypeId);
		paramMap.put("keyword",keyword);
		paramMap.put("relevancyWeight",relevancyWeight);
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductKeyword", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeCustomerDigitalDownloadProductFile")
	public ResponseEntity<Object> removeCustomerDigitalDownloadProductFile(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("contentId",contentId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCustomerDigitalDownloadProductFile", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductAssocType")
	public ResponseEntity<Object> updateProductAssocType(HttpSession session, @RequestParam(value="productAssocTypeId") String productAssocTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productAssocTypeId",productAssocTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductMaintType")
	public ResponseEntity<Object> createProductMaintType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="productMaintTypeId", required=false) String productMaintTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("description",description);
		paramMap.put("productMaintTypeId",productMaintTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductMaintType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/uploadProductAdditionalViewImages")
	public ResponseEntity<Object> uploadProductAdditionalViewImages(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="additionalImageFour", required=false) MultipartFile additionalImageFour, @RequestParam(value="additionalImageOne", required=false) MultipartFile additionalImageOne, @RequestParam(value="_additionalImageOne_fileName", required=false) String _additionalImageOne_fileName, @RequestParam(value="_additionalImageOne_contentType", required=false) String _additionalImageOne_contentType, @RequestParam(value="_additionalImageThree_contentType", required=false) String _additionalImageThree_contentType, @RequestParam(value="_additionalImageThree_fileName", required=false) String _additionalImageThree_fileName, @RequestParam(value="additionalImageTwo", required=false) MultipartFile additionalImageTwo, @RequestParam(value="_additionalImageFour_fileName", required=false) String _additionalImageFour_fileName, @RequestParam(value="_additionalImageFour_contentType", required=false) String _additionalImageFour_contentType, @RequestParam(value="_additionalImageTwo_fileName", required=false) String _additionalImageTwo_fileName, @RequestParam(value="additionalImageThree", required=false) MultipartFile additionalImageThree, @RequestParam(value="_additionalImageTwo_contentType", required=false) String _additionalImageTwo_contentType) throws IOException {



		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("additionalImageFour", ByteBuffer.wrap(additionalImageFour.getBytes()));
		paramMap.put("additionalImageOne",ByteBuffer.wrap(additionalImageOne.getBytes()));
		paramMap.put("_additionalImageOne_fileName",_additionalImageOne_fileName);
		paramMap.put("_additionalImageOne_contentType",_additionalImageOne_contentType);
		paramMap.put("_additionalImageThree_contentType",_additionalImageThree_contentType);
		paramMap.put("_additionalImageThree_fileName",_additionalImageThree_fileName);
		paramMap.put("additionalImageTwo",ByteBuffer.wrap(additionalImageTwo.getBytes()));
		paramMap.put("_additionalImageFour_fileName",_additionalImageFour_fileName);
		paramMap.put("_additionalImageFour_contentType",_additionalImageFour_contentType);
		paramMap.put("_additionalImageTwo_fileName",_additionalImageTwo_fileName);
		paramMap.put("additionalImageThree",ByteBuffer.wrap(additionalImageThree.getBytes()));
		paramMap.put("_additionalImageTwo_contentType",_additionalImageTwo_contentType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("uploadProductAdditionalViewImages", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductIfAvailableFromShipment")
	public ResponseEntity<Object> updateProductIfAvailableFromShipment(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductIfAvailableFromShipment", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProdCatalogCategoryType")
	public ResponseEntity<Object> updateProdCatalogCategoryType(HttpSession session, @RequestParam(value="prodCatalogCategoryTypeId") String prodCatalogCategoryTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatalogCategoryTypeId",prodCatalogCategoryTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProdCatalogCategoryType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductCategory")
	public ResponseEntity<Object> createProductCategory(HttpSession session, @RequestParam(value="productCategoryTypeId") String productCategoryTypeId, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="linkOneImageUrl", required=false) String linkOneImageUrl, @RequestParam(value="detailScreen", required=false) String detailScreen, @RequestParam(value="primaryParentCategoryId", required=false) String primaryParentCategoryId, @RequestParam(value="categoryImageUrl", required=false) String categoryImageUrl, @RequestParam(value="description", required=false) String description, @RequestParam(value="showInSelect", required=false) String showInSelect, @RequestParam(value="linkTwoImageUrl", required=false) String linkTwoImageUrl, @RequestParam(value="categoryName", required=false) String categoryName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryTypeId",productCategoryTypeId);
		paramMap.put("longDescription",longDescription);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("linkOneImageUrl",linkOneImageUrl);
		paramMap.put("detailScreen",detailScreen);
		paramMap.put("primaryParentCategoryId",primaryParentCategoryId);
		paramMap.put("categoryImageUrl",categoryImageUrl);
		paramMap.put("description",description);
		paramMap.put("showInSelect",showInSelect);
		paramMap.put("linkTwoImageUrl",linkTwoImageUrl);
		paramMap.put("categoryName",categoryName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductAttribute")
	public ResponseEntity<Object> updateProductAttribute(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue, @RequestParam(value="attrType", required=false) Long attrType) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("attrType",attrType);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductConfigOption")
	public ResponseEntity<Object> createProductConfigOption(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="configOptionName", required=false) String configOptionName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("configOptionName",configOptionName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductConfigOption", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductCategoryGlAccount")
	public ResponseEntity<Object> updateProductCategoryGlAccount(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductCategoryGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductContentType")
	public ResponseEntity<Object> createProductContentType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="productContentTypeId", required=false) String productContentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCommunicationEventProduct")
	public ResponseEntity<Object> createCommunicationEventProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCommunicationEventProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentSEOForCategory")
	public ResponseEntity<Object> updateContentSEOForCategory(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="title", required=false) String title, @RequestParam(value="metaDescription", required=false) String metaDescription, @RequestParam(value="metaKeyword", required=false) String metaKeyword) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("title",title);
		paramMap.put("metaDescription",metaDescription);
		paramMap.put("metaKeyword",metaKeyword);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentSEOForCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/catalogPermissionCheck")
	public ResponseEntity<Object> catalogPermissionCheck(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("catalogPermissionCheck", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addProductCategoryToCategory")
	public ResponseEntity<Object> addProductCategoryToCategory(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="parentProductCategoryId") String parentProductCategoryId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("parentProductCategoryId",parentProductCategoryId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addProductCategoryToCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductType")
	public ResponseEntity<Object> deleteProductType(HttpSession session, @RequestParam(value="productTypeId") String productTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPaymentMethodType")
	public ResponseEntity<Object> updateProductPaymentMethodType(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="productPricePurposeId") String productPricePurposeId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("productPricePurposeId",productPricePurposeId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPaymentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductGroupOrder")
	public ResponseEntity<Object> updateProductGroupOrder(HttpSession session, @RequestParam(value="groupOrderId") String groupOrderId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="jobId", required=false) String jobId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="reqOrderQty", required=false) BigDecimal reqOrderQty, @RequestParam(value="soldOrderQty", required=false) BigDecimal soldOrderQty, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupOrderId",groupOrderId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("jobId",jobId);
		paramMap.put("productId",productId);
		paramMap.put("statusId",statusId);
		paramMap.put("reqOrderQty",reqOrderQty);
		paramMap.put("soldOrderQty",soldOrderQty);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductGroupOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProdCatalog")
	public ResponseEntity<Object> updateProdCatalog(HttpSession session, @RequestParam(value="catalogName") String catalogName, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="headerLogo", required=false) String headerLogo, @RequestParam(value="styleSheet", required=false) String styleSheet, @RequestParam(value="templatePathPrefix", required=false) String templatePathPrefix, @RequestParam(value="useQuickAdd", required=false) String useQuickAdd, @RequestParam(value="contentPathPrefix", required=false) String contentPathPrefix, @RequestParam(value="viewAllowPermReqd", required=false) String viewAllowPermReqd, @RequestParam(value="purchaseAllowPermReqd", required=false) String purchaseAllowPermReqd) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("catalogName",catalogName);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("headerLogo",headerLogo);
		paramMap.put("styleSheet",styleSheet);
		paramMap.put("templatePathPrefix",templatePathPrefix);
		paramMap.put("useQuickAdd",useQuickAdd);
		paramMap.put("contentPathPrefix",contentPathPrefix);
		paramMap.put("viewAllowPermReqd",viewAllowPermReqd);
		paramMap.put("purchaseAllowPermReqd",purchaseAllowPermReqd);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProdCatalog", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductCategoryType")
	public ResponseEntity<Object> deleteProductCategoryType(HttpSession session, @RequestParam(value="productCategoryTypeId") String productCategoryTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryTypeId",productCategoryTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductCategoryType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addPartyToCategory")
	public ResponseEntity<Object> addPartyToCategory(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("comments",comments);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addPartyToCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/discontinueProductSales")
	public ResponseEntity<Object> discontinueProductSales(HttpSession session, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("discontinueProductSales", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductConfigProduct")
	public ResponseEntity<Object> createProductConfigProduct(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="productId") String productId, @RequestParam(value="configOptionId") String configOptionId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="sequenceNum", required=false) Long sequenceNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("productId",productId);
		paramMap.put("configOptionId",configOptionId);
		paramMap.put("quantity",quantity);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductConfigProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductCategoryContentType")
	public ResponseEntity<Object> createProductCategoryContentType(HttpSession session, @RequestParam(value="prodCatContentTypeId", required=false) String prodCatContentTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductCategoryContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addProductCategoryToCategories")
	public ResponseEntity<Object> addProductCategoryToCategories(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="categories") Object categories, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("categories",categories);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addProductCategoryToCategories", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createEmailContentForProduct")
	public ResponseEntity<Object> createEmailContentForProduct(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="subject") String subject, @RequestParam(value="plainBody") String plainBody, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="htmlBody", required=false) String htmlBody, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("subject",subject);
		paramMap.put("plainBody",plainBody);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentId",contentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("useTime",useTime);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("htmlBody",htmlBody);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createEmailContentForProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductConfigItemContent")
	public ResponseEntity<Object> createProductConfigItemContent(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="confItemContentTypeId") String confItemContentTypeId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("confItemContentTypeId",confItemContentTypeId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentId",contentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductConfigItemContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWebAnalyticsConfig")
	public ResponseEntity<Object> createWebAnalyticsConfig(HttpSession session, @RequestParam(value="webAnalyticsTypeId") String webAnalyticsTypeId, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="webAnalyticsCode", required=false) String webAnalyticsCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webAnalyticsTypeId",webAnalyticsTypeId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("webAnalyticsCode",webAnalyticsCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWebAnalyticsConfig", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/resizeImages")
	public ResponseEntity<Object> resizeImages(HttpSession session, @RequestParam(value="resizeOption") String resizeOption, @RequestParam(value="productId") String productId, @RequestParam(value="size") String size) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("resizeOption",resizeOption);
		paramMap.put("productId",productId);
		paramMap.put("size",size);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("resizeImages", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductConfigOption")
	public ResponseEntity<Object> deleteProductConfigOption(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="configOptionId") String configOptionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("configOptionId",configOptionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductConfigOption", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/copyToProductVariants")
	public ResponseEntity<Object> copyToProductVariants(HttpSession session, @RequestParam(value="virtualProductId") String virtualProductId, @RequestParam(value="removeBefore", required=false) String removeBefore, @RequestParam(value="duplicateContent", required=false) String duplicateContent, @RequestParam(value="duplicateAttributes", required=false) String duplicateAttributes, @RequestParam(value="duplicateProduct", required=false) String duplicateProduct, @RequestParam(value="duplicateCategoryMembers", required=false) String duplicateCategoryMembers, @RequestParam(value="duplicatePrices", required=false) String duplicatePrices, @RequestParam(value="duplicateLocations", required=false) String duplicateLocations, @RequestParam(value="duplicateFacilities", required=false) String duplicateFacilities, @RequestParam(value="duplicateIDs", required=false) String duplicateIDs) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("virtualProductId",virtualProductId);
		paramMap.put("removeBefore",removeBefore);
		paramMap.put("duplicateContent",duplicateContent);
		paramMap.put("duplicateAttributes",duplicateAttributes);
		paramMap.put("duplicateProduct",duplicateProduct);
		paramMap.put("duplicateCategoryMembers",duplicateCategoryMembers);
		paramMap.put("duplicatePrices",duplicatePrices);
		paramMap.put("duplicateLocations",duplicateLocations);
		paramMap.put("duplicateFacilities",duplicateFacilities);
		paramMap.put("duplicateIDs",duplicateIDs);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyToProductVariants", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateStatusImageManagement")
	public ResponseEntity<Object> updateStatusImageManagement(HttpSession session, @RequestParam(value="contentId") String contentId, @RequestParam(value="checkStatusId", required=false) String checkStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("checkStatusId",checkStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateStatusImageManagement", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductConfigItemContent")
	public ResponseEntity<Object> updateProductConfigItemContent(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="confItemContentTypeId") String confItemContentTypeId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contentId") String contentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("confItemContentTypeId",confItemContentTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentId",contentId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductConfigItemContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/FindCategoryChild")
	public ResponseEntity<Object> FindCategoryChild(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="week") Long week, @RequestParam(value="primaryProductCategoryId") String primaryProductCategoryId, @RequestParam(value="year") Long year, @RequestParam(value="productStoreId") String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("week",week);
		paramMap.put("primaryProductCategoryId",primaryProductCategoryId);
		paramMap.put("year",year);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("FindCategoryChild", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/interfaceProduct")
	public ResponseEntity<Object> interfaceProduct(HttpSession session, @RequestParam(value="originGeoId", required=false) String originGeoId, @RequestParam(value="detailScreen", required=false) String detailScreen, @RequestParam(value="requireInventory", required=false) String requireInventory, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="mediumImageUrl", required=false) String mediumImageUrl, @RequestParam(value="productName", required=false) String productName, @RequestParam(value="introductionDate", required=false) Timestamp introductionDate, @RequestParam(value="shippingHeight", required=false) BigDecimal shippingHeight, @RequestParam(value="originalImageUrl", required=false) String originalImageUrl, @RequestParam(value="inShippingBox", required=false) String inShippingBox, @RequestParam(value="detailImageUrl", required=false) String detailImageUrl, @RequestParam(value="supportDiscontinuationDate", required=false) Timestamp supportDiscontinuationDate, @RequestParam(value="productWidth", required=false) BigDecimal productWidth, @RequestParam(value="includeInPromotions", required=false) String includeInPromotions, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="billOfMaterialLevel", required=false) Long billOfMaterialLevel, @RequestParam(value="lotIdFilledIn", required=false) String lotIdFilledIn, @RequestParam(value="productRating", required=false) BigDecimal productRating, @RequestParam(value="manufacturerPartyId", required=false) String manufacturerPartyId, @RequestParam(value="brandName", required=false) String brandName, @RequestParam(value="requireAmount", required=false) String requireAmount, @RequestParam(value="smallImageUrl", required=false) String smallImageUrl, @RequestParam(value="taxable", required=false) String taxable, @RequestParam(value="primaryProductCategoryId", required=false) String primaryProductCategoryId, @RequestParam(value="salesDiscontinuationDate", required=false) Timestamp salesDiscontinuationDate, @RequestParam(value="salesDiscWhenNotAvail", required=false) String salesDiscWhenNotAvail, @RequestParam(value="returnable", required=false) String returnable, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="isVirtual", required=false) String isVirtual, @RequestParam(value="priceDetailText", required=false) String priceDetailText, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="widthUomId", required=false) String widthUomId, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="diameterUomId", required=false) String diameterUomId, @RequestParam(value="autoCreateKeywords", required=false) String autoCreateKeywords, @RequestParam(value="amountUomTypeId", required=false) String amountUomTypeId, @RequestParam(value="productDiameter", required=false) BigDecimal productDiameter, @RequestParam(value="ratingTypeEnum", required=false) String ratingTypeEnum, @RequestParam(value="description", required=false) String description, @RequestParam(value="chargeShipping", required=false) String chargeShipping, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="quantityIncluded", required=false) BigDecimal quantityIncluded, @RequestParam(value="heightUomId", required=false) String heightUomId, @RequestParam(value="internalName", required=false) String internalName, @RequestParam(value="virtualVariantMethodEnum", required=false) String virtualVariantMethodEnum, @RequestParam(value="shippingWeight", required=false) BigDecimal shippingWeight, @RequestParam(value="shippingWidth", required=false) BigDecimal shippingWidth, @RequestParam(value="shippingDepth", required=false) BigDecimal shippingDepth, @RequestParam(value="reservMaxPersons", required=false) BigDecimal reservMaxPersons, @RequestParam(value="fixedAmount", required=false) BigDecimal fixedAmount, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId, @RequestParam(value="piecesIncluded", required=false) Long piecesIncluded, @RequestParam(value="productDepth", required=false) BigDecimal productDepth, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="releaseDate", required=false) Timestamp releaseDate, @RequestParam(value="productHeight", required=false) BigDecimal productHeight, @RequestParam(value="defaultShipmentBoxTypeId", required=false) String defaultShipmentBoxTypeId, @RequestParam(value="orderDecimalQuantity", required=false) String orderDecimalQuantity, @RequestParam(value="inventoryMessage", required=false) String inventoryMessage, @RequestParam(value="productWeight", required=false) BigDecimal productWeight, @RequestParam(value="depthUomId", required=false) String depthUomId, @RequestParam(value="productTypeId", required=false) String productTypeId, @RequestParam(value="isVariant", required=false) String isVariant, @RequestParam(value="largeImageUrl", required=false) String largeImageUrl, @RequestParam(value="requirementMethodEnumId", required=false) String requirementMethodEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("originGeoId",originGeoId);
		paramMap.put("detailScreen",detailScreen);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("mediumImageUrl",mediumImageUrl);
		paramMap.put("productName",productName);
		paramMap.put("introductionDate",introductionDate);
		paramMap.put("shippingHeight",shippingHeight);
		paramMap.put("originalImageUrl",originalImageUrl);
		paramMap.put("inShippingBox",inShippingBox);
		paramMap.put("detailImageUrl",detailImageUrl);
		paramMap.put("supportDiscontinuationDate",supportDiscontinuationDate);
		paramMap.put("productWidth",productWidth);
		paramMap.put("includeInPromotions",includeInPromotions);
		paramMap.put("configId",configId);
		paramMap.put("billOfMaterialLevel",billOfMaterialLevel);
		paramMap.put("lotIdFilledIn",lotIdFilledIn);
		paramMap.put("productRating",productRating);
		paramMap.put("manufacturerPartyId",manufacturerPartyId);
		paramMap.put("brandName",brandName);
		paramMap.put("requireAmount",requireAmount);
		paramMap.put("smallImageUrl",smallImageUrl);
		paramMap.put("taxable",taxable);
		paramMap.put("primaryProductCategoryId",primaryProductCategoryId);
		paramMap.put("salesDiscontinuationDate",salesDiscontinuationDate);
		paramMap.put("salesDiscWhenNotAvail",salesDiscWhenNotAvail);
		paramMap.put("returnable",returnable);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("isVirtual",isVirtual);
		paramMap.put("priceDetailText",priceDetailText);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("widthUomId",widthUomId);
		paramMap.put("longDescription",longDescription);
		paramMap.put("diameterUomId",diameterUomId);
		paramMap.put("autoCreateKeywords",autoCreateKeywords);
		paramMap.put("amountUomTypeId",amountUomTypeId);
		paramMap.put("productDiameter",productDiameter);
		paramMap.put("ratingTypeEnum",ratingTypeEnum);
		paramMap.put("description",description);
		paramMap.put("chargeShipping",chargeShipping);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("quantityIncluded",quantityIncluded);
		paramMap.put("heightUomId",heightUomId);
		paramMap.put("internalName",internalName);
		paramMap.put("virtualVariantMethodEnum",virtualVariantMethodEnum);
		paramMap.put("shippingWeight",shippingWeight);
		paramMap.put("shippingWidth",shippingWidth);
		paramMap.put("shippingDepth",shippingDepth);
		paramMap.put("reservMaxPersons",reservMaxPersons);
		paramMap.put("fixedAmount",fixedAmount);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("piecesIncluded",piecesIncluded);
		paramMap.put("productDepth",productDepth);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("releaseDate",releaseDate);
		paramMap.put("productHeight",productHeight);
		paramMap.put("defaultShipmentBoxTypeId",defaultShipmentBoxTypeId);
		paramMap.put("orderDecimalQuantity",orderDecimalQuantity);
		paramMap.put("inventoryMessage",inventoryMessage);
		paramMap.put("productWeight",productWeight);
		paramMap.put("depthUomId",depthUomId);
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("isVariant",isVariant);
		paramMap.put("largeImageUrl",largeImageUrl);
		paramMap.put("requirementMethodEnumId",requirementMethodEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("interfaceProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeImageFileForImageManagement")
	public ResponseEntity<Object> removeImageFileForImageManagement(HttpSession session, @RequestParam(value="dataResourceName") String dataResourceName, @RequestParam(value="productId") String productId, @RequestParam(value="contentId") String contentId, @RequestParam(value="objectInfo") String objectInfo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceName",dataResourceName);
		paramMap.put("productId",productId);
		paramMap.put("contentId",contentId);
		paramMap.put("objectInfo",objectInfo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeImageFileForImageManagement", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductCategoryContentType")
	public ResponseEntity<Object> deleteProductCategoryContentType(HttpSession session, @RequestParam(value="prodCatContentTypeId") String prodCatContentTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductCategoryContentType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createCategoryContent")
	public ResponseEntity<Object> createCategoryContent(HttpSession session, @RequestParam(value="prodCatContentTypeId") String prodCatContentTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="useDaysLimit", required=false) BigDecimal useDaysLimit, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentId",contentId);
		paramMap.put("useDaysLimit",useDaysLimit);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createCategoryContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductAssoc")
	public ResponseEntity<Object> updateProductAssoc(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="productIdTo") String productIdTo, @RequestParam(value="productAssocTypeId") String productAssocTypeId, @RequestParam(value="reason", required=false) String reason, @RequestParam(value="scrapFactor", required=false) BigDecimal scrapFactor, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="recurrenceInfoId", required=false) String recurrenceInfoId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="estimateCalcMethod", required=false) String estimateCalcMethod, @RequestParam(value="instruction", required=false) String instruction, @RequestParam(value="routingWorkEffortId", required=false) String routingWorkEffortId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("productIdTo",productIdTo);
		paramMap.put("productAssocTypeId",productAssocTypeId);
		paramMap.put("reason",reason);
		paramMap.put("scrapFactor",scrapFactor);
		paramMap.put("quantity",quantity);
		paramMap.put("recurrenceInfoId",recurrenceInfoId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("estimateCalcMethod",estimateCalcMethod);
		paramMap.put("instruction",instruction);
		paramMap.put("routingWorkEffortId",routingWorkEffortId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductAssoc", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProduct")
	public ResponseEntity<Object> createProduct(HttpSession session, @RequestParam(value="internalName") String internalName, @RequestParam(value="productTypeId") String productTypeId, @RequestParam(value="originGeoId", required=false) String originGeoId, @RequestParam(value="detailScreen", required=false) String detailScreen, @RequestParam(value="requireInventory", required=false) String requireInventory, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="mediumImageUrl", required=false) String mediumImageUrl, @RequestParam(value="productName", required=false) String productName, @RequestParam(value="introductionDate", required=false) Timestamp introductionDate, @RequestParam(value="shippingHeight", required=false) BigDecimal shippingHeight, @RequestParam(value="originalImageUrl", required=false) String originalImageUrl, @RequestParam(value="inShippingBox", required=false) String inShippingBox, @RequestParam(value="detailImageUrl", required=false) String detailImageUrl, @RequestParam(value="supportDiscontinuationDate", required=false) Timestamp supportDiscontinuationDate, @RequestParam(value="productWidth", required=false) BigDecimal productWidth, @RequestParam(value="includeInPromotions", required=false) String includeInPromotions, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="billOfMaterialLevel", required=false) Long billOfMaterialLevel, @RequestParam(value="lotIdFilledIn", required=false) String lotIdFilledIn, @RequestParam(value="productRating", required=false) BigDecimal productRating, @RequestParam(value="manufacturerPartyId", required=false) String manufacturerPartyId, @RequestParam(value="brandName", required=false) String brandName, @RequestParam(value="requireAmount", required=false) String requireAmount, @RequestParam(value="smallImageUrl", required=false) String smallImageUrl, @RequestParam(value="taxable", required=false) String taxable, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="primaryProductCategoryId", required=false) String primaryProductCategoryId, @RequestParam(value="salesDiscontinuationDate", required=false) Timestamp salesDiscontinuationDate, @RequestParam(value="salesDiscWhenNotAvail", required=false) String salesDiscWhenNotAvail, @RequestParam(value="returnable", required=false) String returnable, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="isVirtual", required=false) String isVirtual, @RequestParam(value="priceDetailText", required=false) String priceDetailText, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="widthUomId", required=false) String widthUomId, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="diameterUomId", required=false) String diameterUomId, @RequestParam(value="autoCreateKeywords", required=false) String autoCreateKeywords, @RequestParam(value="amountUomTypeId", required=false) String amountUomTypeId, @RequestParam(value="productDiameter", required=false) BigDecimal productDiameter, @RequestParam(value="ratingTypeEnum", required=false) String ratingTypeEnum, @RequestParam(value="description", required=false) String description, @RequestParam(value="chargeShipping", required=false) String chargeShipping, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="quantityIncluded", required=false) BigDecimal quantityIncluded, @RequestParam(value="heightUomId", required=false) String heightUomId, @RequestParam(value="virtualVariantMethodEnum", required=false) String virtualVariantMethodEnum, @RequestParam(value="shippingWeight", required=false) BigDecimal shippingWeight, @RequestParam(value="shippingWidth", required=false) BigDecimal shippingWidth, @RequestParam(value="shippingDepth", required=false) BigDecimal shippingDepth, @RequestParam(value="reservMaxPersons", required=false) BigDecimal reservMaxPersons, @RequestParam(value="fixedAmount", required=false) BigDecimal fixedAmount, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId, @RequestParam(value="piecesIncluded", required=false) Long piecesIncluded, @RequestParam(value="productDepth", required=false) BigDecimal productDepth, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="releaseDate", required=false) Timestamp releaseDate, @RequestParam(value="productHeight", required=false) BigDecimal productHeight, @RequestParam(value="defaultShipmentBoxTypeId", required=false) String defaultShipmentBoxTypeId, @RequestParam(value="orderDecimalQuantity", required=false) String orderDecimalQuantity, @RequestParam(value="inventoryMessage", required=false) String inventoryMessage, @RequestParam(value="productWeight", required=false) BigDecimal productWeight, @RequestParam(value="depthUomId", required=false) String depthUomId, @RequestParam(value="isVariant", required=false) String isVariant, @RequestParam(value="largeImageUrl", required=false) String largeImageUrl, @RequestParam(value="requirementMethodEnumId", required=false) String requirementMethodEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("internalName",internalName);
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("originGeoId",originGeoId);
		paramMap.put("detailScreen",detailScreen);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("mediumImageUrl",mediumImageUrl);
		paramMap.put("productName",productName);
		paramMap.put("introductionDate",introductionDate);
		paramMap.put("shippingHeight",shippingHeight);
		paramMap.put("originalImageUrl",originalImageUrl);
		paramMap.put("inShippingBox",inShippingBox);
		paramMap.put("detailImageUrl",detailImageUrl);
		paramMap.put("supportDiscontinuationDate",supportDiscontinuationDate);
		paramMap.put("productWidth",productWidth);
		paramMap.put("includeInPromotions",includeInPromotions);
		paramMap.put("configId",configId);
		paramMap.put("billOfMaterialLevel",billOfMaterialLevel);
		paramMap.put("lotIdFilledIn",lotIdFilledIn);
		paramMap.put("productRating",productRating);
		paramMap.put("manufacturerPartyId",manufacturerPartyId);
		paramMap.put("brandName",brandName);
		paramMap.put("requireAmount",requireAmount);
		paramMap.put("smallImageUrl",smallImageUrl);
		paramMap.put("taxable",taxable);
		paramMap.put("productId",productId);
		paramMap.put("primaryProductCategoryId",primaryProductCategoryId);
		paramMap.put("salesDiscontinuationDate",salesDiscontinuationDate);
		paramMap.put("salesDiscWhenNotAvail",salesDiscWhenNotAvail);
		paramMap.put("returnable",returnable);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("isVirtual",isVirtual);
		paramMap.put("priceDetailText",priceDetailText);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("widthUomId",widthUomId);
		paramMap.put("longDescription",longDescription);
		paramMap.put("diameterUomId",diameterUomId);
		paramMap.put("autoCreateKeywords",autoCreateKeywords);
		paramMap.put("amountUomTypeId",amountUomTypeId);
		paramMap.put("productDiameter",productDiameter);
		paramMap.put("ratingTypeEnum",ratingTypeEnum);
		paramMap.put("description",description);
		paramMap.put("chargeShipping",chargeShipping);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("quantityIncluded",quantityIncluded);
		paramMap.put("heightUomId",heightUomId);
		paramMap.put("virtualVariantMethodEnum",virtualVariantMethodEnum);
		paramMap.put("shippingWeight",shippingWeight);
		paramMap.put("shippingWidth",shippingWidth);
		paramMap.put("shippingDepth",shippingDepth);
		paramMap.put("reservMaxPersons",reservMaxPersons);
		paramMap.put("fixedAmount",fixedAmount);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("piecesIncluded",piecesIncluded);
		paramMap.put("productDepth",productDepth);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("releaseDate",releaseDate);
		paramMap.put("productHeight",productHeight);
		paramMap.put("defaultShipmentBoxTypeId",defaultShipmentBoxTypeId);
		paramMap.put("orderDecimalQuantity",orderDecimalQuantity);
		paramMap.put("inventoryMessage",inventoryMessage);
		paramMap.put("productWeight",productWeight);
		paramMap.put("depthUomId",depthUomId);
		paramMap.put("isVariant",isVariant);
		paramMap.put("largeImageUrl",largeImageUrl);
		paramMap.put("requirementMethodEnumId",requirementMethodEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSalesAgreement")
	public ResponseEntity<Object> createSalesAgreement(HttpSession session, @RequestParam(value="agreementId") String agreementId, @RequestParam(value="agreementItemSeqId") String agreementItemSeqId, @RequestParam(value="roleTypeIdTo", required=false) String roleTypeIdTo, @RequestParam(value="partyIdFrom", required=false) String partyIdFrom, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="agreementDate", required=false) Timestamp agreementDate, @RequestParam(value="agreementTypeId", required=false) String agreementTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="roleTypeIdFrom", required=false) String roleTypeIdFrom, @RequestParam(value="agreementItemTypeId", required=false) String agreementItemTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="textData", required=false) String textData, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="agreementText", required=false) String agreementText, @RequestParam(value="agreementImage", required=false) Object agreementImage, @RequestParam(value="price", required=false) BigDecimal price, @RequestParam(value="partyIdTo", required=false) String partyIdTo) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("agreementId",agreementId);
		paramMap.put("agreementItemSeqId",agreementItemSeqId);
		paramMap.put("roleTypeIdTo",roleTypeIdTo);
		paramMap.put("partyIdFrom",partyIdFrom);
		paramMap.put("productId",productId);
		paramMap.put("agreementDate",agreementDate);
		paramMap.put("agreementTypeId",agreementTypeId);
		paramMap.put("description",description);
		paramMap.put("roleTypeIdFrom",roleTypeIdFrom);
		paramMap.put("agreementItemTypeId",agreementItemTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("textData",textData);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("agreementText",agreementText);
		paramMap.put("agreementImage",agreementImage);
		paramMap.put("price",price);
		paramMap.put("partyIdTo",partyIdTo);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSalesAgreement", paramMap);
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

	@PostMapping("/removeProductFromCategory")
	@PreAuthorize(HAS_ADMIN_AUTHORITY)
	public ResponseEntity<Object> removeProductFromCategory(HttpSession session,
															@RequestParam(value="productId") String productId,
															@RequestParam(value="productCategoryId") String productCategoryId) throws Exception {
		
		Product product = new Product(productId, new FindProductsBy(UtilMisc.toMap("productId", productId)));
		ProductCategory category = new ProductCategory(productCategoryId,
													   new FindProductCategorysBy(UtilMisc.toMap("productCategoryId", productCategoryId)));

		List<ProductCategoryMember> members =
			productCategoryMemberController.findProductCategoryMembersBy(UtilMisc.toMap("productCategoryId", productCategoryId,
																						"productId", productId)).getBody();

		try {
			for (ProductCategoryMember member : members){
				product.removeFromCategory(session, category, member.getFromDate());
			}
		} catch (ServiceAuthException e) {
			return unauthorized();
		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			return badRequest();
		}
		return successful();
	}

	@PostMapping("/removeProductFromCategories")
	@PreAuthorize(HAS_ADMIN_AUTHORITY)
	public ResponseEntity removeProductFromCategories(HttpSession session,
													  @RequestParam("productId") String productId,
													  @RequestBody List<String> productCategoryIds) throws Exception {
		try{

			for(String categoryId : productCategoryIds)
				removeProductFromCategory(session, productId, categoryId);

		} catch (ServiceAuthException e) {
			return unauthorized();
		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			return badRequest();
		}
		return successful();

	}

	@PostMapping("/updatePartyToCategory")
	public ResponseEntity<Object> updatePartyToCategory(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="partyId") String partyId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("partyId",partyId);
		paramMap.put("comments",comments);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyToCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/imageCrop")
	public ResponseEntity<Object> imageCrop(HttpSession session, @RequestParam(value="imageName") String imageName, @RequestParam(value="productId") String productId, @RequestParam(value="imageY") String imageY, @RequestParam(value="imageW") String imageW, @RequestParam(value="imageX") String imageX, @RequestParam(value="imageH") String imageH) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("imageName",imageName);
		paramMap.put("productId",productId);
		paramMap.put("imageY",imageY);
		paramMap.put("imageW",imageW);
		paramMap.put("imageX",imageX);
		paramMap.put("imageH",imageH);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("imageCrop", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeImageContentApproval")
	public ResponseEntity<Object> removeImageContentApproval(HttpSession session, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeImageContentApproval", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductConfigProduct")
	public ResponseEntity<Object> updateProductConfigProduct(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="productId") String productId, @RequestParam(value="configOptionId") String configOptionId, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="sequenceNum", required=false) Long sequenceNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("productId",productId);
		paramMap.put("configOptionId",configOptionId);
		paramMap.put("quantity",quantity);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductConfigProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateGoodIdentificationType")
	public ResponseEntity<Object> updateGoodIdentificationType(HttpSession session, @RequestParam(value="goodIdentificationTypeId") String goodIdentificationTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("goodIdentificationTypeId",goodIdentificationTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGoodIdentificationType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductAssocType")
	public ResponseEntity<Object> deleteProductAssocType(HttpSession session, @RequestParam(value="productAssocTypeId") String productAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productAssocTypeId",productAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createVendorProduct")
	public ResponseEntity<Object> createVendorProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="vendorPartyId") String vendorPartyId, @RequestParam(value="productStoreGroupId") String productStoreGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("vendorPartyId",vendorPartyId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createVendorProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/duplicateProduct")
	public ResponseEntity<Object> duplicateProduct(HttpSession session, @RequestParam(value="oldProductId") String oldProductId, @RequestParam(value="productId") String productId, @RequestParam(value="duplicateAssocs", required=false) String duplicateAssocs, @RequestParam(value="removeCategoryMembers", required=false) String removeCategoryMembers, @RequestParam(value="duplicateCategoryMembers", required=false) String duplicateCategoryMembers, @RequestParam(value="duplicatePrices", required=false) String duplicatePrices, @RequestParam(value="newProductName", required=false) String newProductName, @RequestParam(value="removeFeatureAppls", required=false) String removeFeatureAppls, @RequestParam(value="duplicateIDs", required=false) String duplicateIDs, @RequestParam(value="removePrices", required=false) String removePrices, @RequestParam(value="newDescription", required=false) String newDescription, @RequestParam(value="removeContent", required=false) String removeContent, @RequestParam(value="newLongDescription", required=false) String newLongDescription, @RequestParam(value="duplicateContent", required=false) String duplicateContent, @RequestParam(value="duplicateAttributes", required=false) String duplicateAttributes, @RequestParam(value="duplicateInventoryItems", required=false) String duplicateInventoryItems, @RequestParam(value="duplicateFeatureAppls", required=false) String duplicateFeatureAppls, @RequestParam(value="removeInventoryItems", required=false) String removeInventoryItems, @RequestParam(value="removeIDs", required=false) String removeIDs, @RequestParam(value="removeAttributes", required=false) String removeAttributes, @RequestParam(value="newInternalName", required=false) String newInternalName, @RequestParam(value="removeAssocs", required=false) String removeAssocs) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("oldProductId",oldProductId);
		paramMap.put("productId",productId);
		paramMap.put("duplicateAssocs",duplicateAssocs);
		paramMap.put("removeCategoryMembers",removeCategoryMembers);
		paramMap.put("duplicateCategoryMembers",duplicateCategoryMembers);
		paramMap.put("duplicatePrices",duplicatePrices);
		paramMap.put("newProductName",newProductName);
		paramMap.put("removeFeatureAppls",removeFeatureAppls);
		paramMap.put("duplicateIDs",duplicateIDs);
		paramMap.put("removePrices",removePrices);
		paramMap.put("newDescription",newDescription);
		paramMap.put("removeContent",removeContent);
		paramMap.put("newLongDescription",newLongDescription);
		paramMap.put("duplicateContent",duplicateContent);
		paramMap.put("duplicateAttributes",duplicateAttributes);
		paramMap.put("duplicateInventoryItems",duplicateInventoryItems);
		paramMap.put("duplicateFeatureAppls",duplicateFeatureAppls);
		paramMap.put("removeInventoryItems",removeInventoryItems);
		paramMap.put("removeIDs",removeIDs);
		paramMap.put("removeAttributes",removeAttributes);
		paramMap.put("newInternalName",newInternalName);
		paramMap.put("removeAssocs",removeAssocs);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("duplicateProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/duplicateProductCategory")
	public ResponseEntity<Object> duplicateProductCategory(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="oldProductCategoryId") String oldProductCategoryId, @RequestParam(value="duplicateContent", required=false) String duplicateContent, @RequestParam(value="duplicateChildRollup", required=false) String duplicateChildRollup, @RequestParam(value="duplicateFeatureGroups", required=false) String duplicateFeatureGroups, @RequestParam(value="duplicateAttributes", required=false) String duplicateAttributes, @RequestParam(value="duplicateRoles", required=false) String duplicateRoles, @RequestParam(value="duplicateParentRollup", required=false) String duplicateParentRollup, @RequestParam(value="duplicateFeatureCategories", required=false) String duplicateFeatureCategories, @RequestParam(value="duplicateMembers", required=false) String duplicateMembers, @RequestParam(value="duplicateCatalogs", required=false) String duplicateCatalogs) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("oldProductCategoryId",oldProductCategoryId);
		paramMap.put("duplicateContent",duplicateContent);
		paramMap.put("duplicateChildRollup",duplicateChildRollup);
		paramMap.put("duplicateFeatureGroups",duplicateFeatureGroups);
		paramMap.put("duplicateAttributes",duplicateAttributes);
		paramMap.put("duplicateRoles",duplicateRoles);
		paramMap.put("duplicateParentRollup",duplicateParentRollup);
		paramMap.put("duplicateFeatureCategories",duplicateFeatureCategories);
		paramMap.put("duplicateMembers",duplicateMembers);
		paramMap.put("duplicateCatalogs",duplicateCatalogs);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("duplicateProductCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSupplierProduct")
	public ResponseEntity<Object> updateSupplierProduct(HttpSession session, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="productId") String productId, @RequestParam(value="availableFromDate") Timestamp availableFromDate, @RequestParam(value="minimumOrderQuantity") BigDecimal minimumOrderQuantity, @RequestParam(value="partyId") String partyId, @RequestParam(value="supplierProductName", required=false) String supplierProductName, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="canDropShip", required=false) String canDropShip, @RequestParam(value="supplierRatingTypeId", required=false) String supplierRatingTypeId, @RequestParam(value="agreementItemSeqId", required=false) String agreementItemSeqId, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="supplierProductId", required=false) String supplierProductId, @RequestParam(value="supplierPrefOrderId", required=false) String supplierPrefOrderId, @RequestParam(value="unitsIncluded", required=false) BigDecimal unitsIncluded, @RequestParam(value="orderQtyIncrements", required=false) BigDecimal orderQtyIncrements, @RequestParam(value="shippingPrice", required=false) BigDecimal shippingPrice, @RequestParam(value="agreementId", required=false) String agreementId, @RequestParam(value="availableThruDate", required=false) Timestamp availableThruDate, @RequestParam(value="standardLeadTimeDays", required=false) BigDecimal standardLeadTimeDays, @RequestParam(value="lastPrice", required=false) BigDecimal lastPrice) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("productId",productId);
		paramMap.put("availableFromDate",availableFromDate);
		paramMap.put("minimumOrderQuantity",minimumOrderQuantity);
		paramMap.put("partyId",partyId);
		paramMap.put("supplierProductName",supplierProductName);
		paramMap.put("comments",comments);
		paramMap.put("canDropShip",canDropShip);
		paramMap.put("supplierRatingTypeId",supplierRatingTypeId);
		paramMap.put("agreementItemSeqId",agreementItemSeqId);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("supplierProductId",supplierProductId);
		paramMap.put("supplierPrefOrderId",supplierPrefOrderId);
		paramMap.put("unitsIncluded",unitsIncluded);
		paramMap.put("orderQtyIncrements",orderQtyIncrements);
		paramMap.put("shippingPrice",shippingPrice);
		paramMap.put("agreementId",agreementId);
		paramMap.put("availableThruDate",availableThruDate);
		paramMap.put("standardLeadTimeDays",standardLeadTimeDays);
		paramMap.put("lastPrice",lastPrice);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSupplierProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/imageRotate")
	public ResponseEntity<Object> imageRotate(HttpSession session, @RequestParam(value="imageName") String imageName, @RequestParam(value="productId") String productId, @RequestParam(value="angle") String angle) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("imageName",imageName);
		paramMap.put("productId",productId);
		paramMap.put("angle",angle);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("imageRotate", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addProductCategoryToProdCatalog")
	public ResponseEntity<Object> addProductCategoryToProdCatalog(HttpSession session, @RequestParam(value="prodCatalogCategoryTypeId") String prodCatalogCategoryTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="prodCatalogId") String prodCatalogId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatalogCategoryTypeId",prodCatalogCategoryTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addProductCategoryToProdCatalog", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductCategoryLink")
	public ResponseEntity<Object> createProductCategoryLink(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="linkInfo", required=false) String linkInfo, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="detailText", required=false) String detailText, @RequestParam(value="titleText", required=false) String titleText, @RequestParam(value="imageUrl", required=false) String imageUrl, @RequestParam(value="imageTwoUrl", required=false) String imageTwoUrl, @RequestParam(value="linkTypeEnumId", required=false) String linkTypeEnumId, @RequestParam(value="linkSeqId", required=false) String linkSeqId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="detailSubScreen", required=false) String detailSubScreen) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("linkInfo",linkInfo);
		paramMap.put("comments",comments);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("detailText",detailText);
		paramMap.put("titleText",titleText);
		paramMap.put("imageUrl",imageUrl);
		paramMap.put("imageTwoUrl",imageTwoUrl);
		paramMap.put("linkTypeEnumId",linkTypeEnumId);
		paramMap.put("linkSeqId",linkSeqId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("detailSubScreen",detailSubScreen);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductCategoryLink", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/productCategoryGenericPermission")
	public ResponseEntity<Object> productCategoryGenericPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productCategoryGenericPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductAssocType")
	public ResponseEntity<Object> createProductAssocType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="productAssocTypeId", required=false) String productAssocTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("productAssocTypeId",productAssocTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductAssocType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGoodIdentificationType")
	public ResponseEntity<Object> deleteGoodIdentificationType(HttpSession session, @RequestParam(value="goodIdentificationTypeId") String goodIdentificationTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("goodIdentificationTypeId",goodIdentificationTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGoodIdentificationType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductMeterType")
	public ResponseEntity<Object> createProductMeterType(HttpSession session, @RequestParam(value="defaultUomId", required=false) String defaultUomId, @RequestParam(value="productMeterTypeId", required=false) String productMeterTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("defaultUomId",defaultUomId);
		paramMap.put("productMeterTypeId",productMeterTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductMeterType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/forceIndexProductKeywords")
	public ResponseEntity<Object> forceIndexProductKeywords(HttpSession session, @RequestParam(value="productId") String productId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("forceIndexProductKeywords", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createSimpleTextContentForProduct")
	public ResponseEntity<Object> createSimpleTextContentForProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="text") String text, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("text",text);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentId",contentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("useTime",useTime);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSimpleTextContentForProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeSupplierProductFeature")
	public ResponseEntity<Object> removeSupplierProductFeature(HttpSession session, @RequestParam(value="productFeatureId") String productFeatureId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeSupplierProductFeature", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createMarketInterest")
	public ResponseEntity<Object> createMarketInterest(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="partyClassificationGroupId") String partyClassificationGroupId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("partyClassificationGroupId",partyClassificationGroupId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createMarketInterest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductPromoContent")
	public ResponseEntity<Object> updateProductPromoContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productPromoContentTypeId") String productPromoContentTypeId, @RequestParam(value="contentId") String contentId, @RequestParam(value="productPromoId") String productPromoId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productPromoContentTypeId",productPromoContentTypeId);
		paramMap.put("contentId",contentId);
		paramMap.put("productPromoId",productPromoId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductPromoContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateContentSEOForProduct")
	public ResponseEntity<Object> updateContentSEOForProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="title", required=false) String title, @RequestParam(value="metaDescription", required=false) String metaDescription, @RequestParam(value="metaKeyword", required=false) String metaKeyword) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("title",title);
		paramMap.put("metaDescription",metaDescription);
		paramMap.put("metaKeyword",metaKeyword);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateContentSEOForProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSimpleTextContentForProductConfigItem")
	public ResponseEntity<Object> updateSimpleTextContentForProductConfigItem(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="confItemContentTypeId") String confItemContentTypeId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contentId") String contentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="text", required=false) String text, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="textDataResourceId", required=false) String textDataResourceId, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("confItemContentTypeId",confItemContentTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentId",contentId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("text",text);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("textDataResourceId",textDataResourceId);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSimpleTextContentForProductConfigItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createRelatedUrlContentForCategory")
	public ResponseEntity<Object> createRelatedUrlContentForCategory(HttpSession session, @RequestParam(value="prodCatContentTypeId") String prodCatContentTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="description") String description, @RequestParam(value="title") String title, @RequestParam(value="url") String url, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="useDaysLimit", required=false) BigDecimal useDaysLimit, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("description",description);
		paramMap.put("title",title);
		paramMap.put("url",url);
		paramMap.put("fromDate",fromDate);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("localeString",localeString);
		paramMap.put("contentId",contentId);
		paramMap.put("useDaysLimit",useDaysLimit);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createRelatedUrlContentForCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductConfigItemContent")
	public ResponseEntity<Object> removeProductConfigItemContent(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="confItemContentTypeId") String confItemContentTypeId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="contentId") String contentId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("confItemContentTypeId",confItemContentTypeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("contentId",contentId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductConfigItemContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductCategoryGlAccount")
	public ResponseEntity<Object> createProductCategoryGlAccount(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductCategoryGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductTypeAttr")
	public ResponseEntity<Object> updateProductTypeAttr(HttpSession session, @RequestParam(value="productTypeId") String productTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductConfig")
	public ResponseEntity<Object> deleteProductConfig(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="sequenceNum") Long sequenceNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductConfig", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductGroupOrder")
	public ResponseEntity<Object> deleteProductGroupOrder(HttpSession session, @RequestParam(value="groupOrderId") String groupOrderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupOrderId",groupOrderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductGroupOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductCategory")
	public ResponseEntity<Object> updateProductCategory(HttpSession session, @RequestParam(value="productCategoryTypeId") String productCategoryTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="linkOneImageUrl", required=false) String linkOneImageUrl, @RequestParam(value="detailScreen", required=false) String detailScreen, @RequestParam(value="primaryParentCategoryId", required=false) String primaryParentCategoryId, @RequestParam(value="categoryImageUrl", required=false) String categoryImageUrl, @RequestParam(value="description", required=false) String description, @RequestParam(value="showInSelect", required=false) String showInSelect, @RequestParam(value="linkTwoImageUrl", required=false) String linkTwoImageUrl, @RequestParam(value="categoryName", required=false) String categoryName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryTypeId",productCategoryTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("longDescription",longDescription);
		paramMap.put("linkOneImageUrl",linkOneImageUrl);
		paramMap.put("detailScreen",detailScreen);
		paramMap.put("primaryParentCategoryId",primaryParentCategoryId);
		paramMap.put("categoryImageUrl",categoryImageUrl);
		paramMap.put("description",description);
		paramMap.put("showInSelect",showInSelect);
		paramMap.put("linkTwoImageUrl",linkTwoImageUrl);
		paramMap.put("categoryName",categoryName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductCategoryFromProdCatalog")
	public ResponseEntity<Object> removeProductCategoryFromProdCatalog(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="prodCatalogCategoryTypeId") String prodCatalogCategoryTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="prodCatalogId") String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("prodCatalogCategoryTypeId",prodCatalogCategoryTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductCategoryFromProdCatalog", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductKeyword")
	public ResponseEntity<Object> updateProductKeyword(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="keywordTypeId") String keywordTypeId, @RequestParam(value="keyword") String keyword, @RequestParam(value="relevancyWeight", required=false) Long relevancyWeight, @RequestParam(value="statusId", required=false) String statusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("keywordTypeId",keywordTypeId);
		paramMap.put("keyword",keyword);
		paramMap.put("relevancyWeight",relevancyWeight);
		paramMap.put("statusId",statusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductKeyword", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductMaint")
	public ResponseEntity<Object> createProductMaint(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="intervalUomId", required=false) String intervalUomId, @RequestParam(value="maintTemplateWorkEffortId", required=false) String maintTemplateWorkEffortId, @RequestParam(value="maintName", required=false) String maintName, @RequestParam(value="intervalQuantity", required=false) BigDecimal intervalQuantity, @RequestParam(value="productMaintTypeId", required=false) String productMaintTypeId, @RequestParam(value="intervalMeterTypeId", required=false) String intervalMeterTypeId, @RequestParam(value="repeatCount", required=false) Long repeatCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("intervalUomId",intervalUomId);
		paramMap.put("maintTemplateWorkEffortId",maintTemplateWorkEffortId);
		paramMap.put("maintName",maintName);
		paramMap.put("intervalQuantity",intervalQuantity);
		paramMap.put("productMaintTypeId",productMaintTypeId);
		paramMap.put("intervalMeterTypeId",intervalMeterTypeId);
		paramMap.put("repeatCount",repeatCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductMaint", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductType")
	public ResponseEntity<Object> updateProductType(HttpSession session, @RequestParam(value="productTypeId") String productTypeId, @RequestParam(value="isPhysical", required=false) String isPhysical, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="isDigital", required=false) String isDigital, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("isPhysical",isPhysical);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("isDigital",isDigital);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateDownloadContentForCategory")
	public ResponseEntity<Object> updateDownloadContentForCategory(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="prodCatContentTypeId") String prodCatContentTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="contentId") String contentId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="imageData", required=false) java.nio.ByteBuffer imageData, @RequestParam(value="useDaysLimit", required=false) BigDecimal useDaysLimit, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="fileDataResourceId", required=false) String fileDataResourceId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="_imageData_fileName", required=false) String _imageData_fileName, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="_imageData_contentType", required=false) String _imageData_contentType, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("contentId",contentId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("imageData",imageData);
		paramMap.put("useDaysLimit",useDaysLimit);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("fileDataResourceId",fileDataResourceId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("_imageData_fileName",_imageData_fileName);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("_imageData_contentType",_imageData_contentType);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateDownloadContentForCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductQuickAdminShipping")
	public ResponseEntity<Object> updateProductQuickAdminShipping(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="originGeoId", required=false) String originGeoId, @RequestParam(value="detailScreen", required=false) String detailScreen, @RequestParam(value="requireInventory", required=false) String requireInventory, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="mediumImageUrl", required=false) String mediumImageUrl, @RequestParam(value="productName", required=false) String productName, @RequestParam(value="introductionDate", required=false) Timestamp introductionDate, @RequestParam(value="shippingHeight", required=false) BigDecimal shippingHeight, @RequestParam(value="originalImageUrl", required=false) String originalImageUrl, @RequestParam(value="inShippingBox", required=false) String inShippingBox, @RequestParam(value="detailImageUrl", required=false) String detailImageUrl, @RequestParam(value="supportDiscontinuationDate", required=false) Timestamp supportDiscontinuationDate, @RequestParam(value="productWidth", required=false) BigDecimal productWidth, @RequestParam(value="includeInPromotions", required=false) String includeInPromotions, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="billOfMaterialLevel", required=false) Long billOfMaterialLevel, @RequestParam(value="lotIdFilledIn", required=false) String lotIdFilledIn, @RequestParam(value="productRating", required=false) BigDecimal productRating, @RequestParam(value="manufacturerPartyId", required=false) String manufacturerPartyId, @RequestParam(value="brandName", required=false) String brandName, @RequestParam(value="requireAmount", required=false) String requireAmount, @RequestParam(value="smallImageUrl", required=false) String smallImageUrl, @RequestParam(value="taxable", required=false) String taxable, @RequestParam(value="primaryProductCategoryId", required=false) String primaryProductCategoryId, @RequestParam(value="salesDiscontinuationDate", required=false) Timestamp salesDiscontinuationDate, @RequestParam(value="salesDiscWhenNotAvail", required=false) String salesDiscWhenNotAvail, @RequestParam(value="returnable", required=false) String returnable, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="isVirtual", required=false) String isVirtual, @RequestParam(value="priceDetailText", required=false) String priceDetailText, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="widthUomId", required=false) String widthUomId, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="diameterUomId", required=false) String diameterUomId, @RequestParam(value="autoCreateKeywords", required=false) String autoCreateKeywords, @RequestParam(value="amountUomTypeId", required=false) String amountUomTypeId, @RequestParam(value="productDiameter", required=false) BigDecimal productDiameter, @RequestParam(value="ratingTypeEnum", required=false) String ratingTypeEnum, @RequestParam(value="description", required=false) String description, @RequestParam(value="chargeShipping", required=false) String chargeShipping, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="quantityIncluded", required=false) BigDecimal quantityIncluded, @RequestParam(value="heightUomId", required=false) String heightUomId, @RequestParam(value="internalName", required=false) String internalName, @RequestParam(value="virtualVariantMethodEnum", required=false) String virtualVariantMethodEnum, @RequestParam(value="shippingWeight", required=false) BigDecimal shippingWeight, @RequestParam(value="shippingWidth", required=false) BigDecimal shippingWidth, @RequestParam(value="shippingDepth", required=false) BigDecimal shippingDepth, @RequestParam(value="reservMaxPersons", required=false) BigDecimal reservMaxPersons, @RequestParam(value="fixedAmount", required=false) BigDecimal fixedAmount, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId, @RequestParam(value="piecesIncluded", required=false) Long piecesIncluded, @RequestParam(value="productDepth", required=false) BigDecimal productDepth, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="releaseDate", required=false) Timestamp releaseDate, @RequestParam(value="productHeight", required=false) BigDecimal productHeight, @RequestParam(value="defaultShipmentBoxTypeId", required=false) String defaultShipmentBoxTypeId, @RequestParam(value="orderDecimalQuantity", required=false) String orderDecimalQuantity, @RequestParam(value="inventoryMessage", required=false) String inventoryMessage, @RequestParam(value="productWeight", required=false) BigDecimal productWeight, @RequestParam(value="depthUomId", required=false) String depthUomId, @RequestParam(value="productTypeId", required=false) String productTypeId, @RequestParam(value="isVariant", required=false) String isVariant, @RequestParam(value="largeImageUrl", required=false) String largeImageUrl, @RequestParam(value="requirementMethodEnumId", required=false) String requirementMethodEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("originGeoId",originGeoId);
		paramMap.put("detailScreen",detailScreen);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("mediumImageUrl",mediumImageUrl);
		paramMap.put("productName",productName);
		paramMap.put("introductionDate",introductionDate);
		paramMap.put("shippingHeight",shippingHeight);
		paramMap.put("originalImageUrl",originalImageUrl);
		paramMap.put("inShippingBox",inShippingBox);
		paramMap.put("detailImageUrl",detailImageUrl);
		paramMap.put("supportDiscontinuationDate",supportDiscontinuationDate);
		paramMap.put("productWidth",productWidth);
		paramMap.put("includeInPromotions",includeInPromotions);
		paramMap.put("configId",configId);
		paramMap.put("billOfMaterialLevel",billOfMaterialLevel);
		paramMap.put("lotIdFilledIn",lotIdFilledIn);
		paramMap.put("productRating",productRating);
		paramMap.put("manufacturerPartyId",manufacturerPartyId);
		paramMap.put("brandName",brandName);
		paramMap.put("requireAmount",requireAmount);
		paramMap.put("smallImageUrl",smallImageUrl);
		paramMap.put("taxable",taxable);
		paramMap.put("primaryProductCategoryId",primaryProductCategoryId);
		paramMap.put("salesDiscontinuationDate",salesDiscontinuationDate);
		paramMap.put("salesDiscWhenNotAvail",salesDiscWhenNotAvail);
		paramMap.put("returnable",returnable);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("isVirtual",isVirtual);
		paramMap.put("priceDetailText",priceDetailText);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("widthUomId",widthUomId);
		paramMap.put("longDescription",longDescription);
		paramMap.put("diameterUomId",diameterUomId);
		paramMap.put("autoCreateKeywords",autoCreateKeywords);
		paramMap.put("amountUomTypeId",amountUomTypeId);
		paramMap.put("productDiameter",productDiameter);
		paramMap.put("ratingTypeEnum",ratingTypeEnum);
		paramMap.put("description",description);
		paramMap.put("chargeShipping",chargeShipping);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("quantityIncluded",quantityIncluded);
		paramMap.put("heightUomId",heightUomId);
		paramMap.put("internalName",internalName);
		paramMap.put("virtualVariantMethodEnum",virtualVariantMethodEnum);
		paramMap.put("shippingWeight",shippingWeight);
		paramMap.put("shippingWidth",shippingWidth);
		paramMap.put("shippingDepth",shippingDepth);
		paramMap.put("reservMaxPersons",reservMaxPersons);
		paramMap.put("fixedAmount",fixedAmount);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("piecesIncluded",piecesIncluded);
		paramMap.put("productDepth",productDepth);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("releaseDate",releaseDate);
		paramMap.put("productHeight",productHeight);
		paramMap.put("defaultShipmentBoxTypeId",defaultShipmentBoxTypeId);
		paramMap.put("orderDecimalQuantity",orderDecimalQuantity);
		paramMap.put("inventoryMessage",inventoryMessage);
		paramMap.put("productWeight",productWeight);
		paramMap.put("depthUomId",depthUomId);
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("isVariant",isVariant);
		paramMap.put("largeImageUrl",largeImageUrl);
		paramMap.put("requirementMethodEnumId",requirementMethodEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductQuickAdminShipping", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createGoodIdentification")
	public ResponseEntity<Object> createGoodIdentification(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="goodIdentificationTypeId") String goodIdentificationTypeId, @RequestParam(value="idValue") String idValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("goodIdentificationTypeId",goodIdentificationTypeId);
		paramMap.put("idValue",idValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGoodIdentification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getCategoryVariantProducts")
	public ResponseEntity<Object> getCategoryVariantProducts(HttpSession session, @RequestParam(value="productFeatures") java.util.List productFeatures, @RequestParam(value="productCategoryId") String productCategoryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productFeatures",productFeatures);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getCategoryVariantProducts", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createImageContentApproval")
	public ResponseEntity<Object> createImageContentApproval(HttpSession session, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createImageContentApproval", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductCategoryLink")
	public ResponseEntity<Object> updateProductCategoryLink(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="linkSeqId") String linkSeqId, @RequestParam(value="linkInfo", required=false) String linkInfo, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="detailText", required=false) String detailText, @RequestParam(value="titleText", required=false) String titleText, @RequestParam(value="imageUrl", required=false) String imageUrl, @RequestParam(value="imageTwoUrl", required=false) String imageTwoUrl, @RequestParam(value="linkTypeEnumId", required=false) String linkTypeEnumId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="detailSubScreen", required=false) String detailSubScreen) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("linkSeqId",linkSeqId);
		paramMap.put("linkInfo",linkInfo);
		paramMap.put("comments",comments);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("detailText",detailText);
		paramMap.put("titleText",titleText);
		paramMap.put("imageUrl",imageUrl);
		paramMap.put("imageTwoUrl",imageTwoUrl);
		paramMap.put("linkTypeEnumId",linkTypeEnumId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("detailSubScreen",detailSubScreen);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductCategoryLink", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/duplicateCategoryEntities")
	public ResponseEntity<Object> duplicateCategoryEntities(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="entityName") String entityName, @RequestParam(value="productCategoryIdTo") String productCategoryIdTo, @RequestParam(value="validDate", required=false) Timestamp validDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("entityName",entityName);
		paramMap.put("productCategoryIdTo",productCategoryIdTo);
		paramMap.put("validDate",validDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("duplicateCategoryEntities", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/expireProdCatalogInvFacility")
	public ResponseEntity<Object> expireProdCatalogInvFacility(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="facilityId") String facilityId, @RequestParam(value="prodCatalogId") String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("facilityId",facilityId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("expireProdCatalogInvFacility", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductPaymentMethodType")
	public ResponseEntity<Object> createProductPaymentMethodType(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="paymentMethodTypeId") String paymentMethodTypeId, @RequestParam(value="productPricePurposeId") String productPricePurposeId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("paymentMethodTypeId",paymentMethodTypeId);
		paramMap.put("productPricePurposeId",productPricePurposeId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductPaymentMethodType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/quickCreateVirtualWithVariants")
	public ResponseEntity<Object> quickCreateVirtualWithVariants(HttpSession session, @RequestParam(value="variantProductIdsBag") String variantProductIdsBag, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="productFeatureIdTwo", required=false) String productFeatureIdTwo, @RequestParam(value="productFeatureIdThree", required=false) String productFeatureIdThree, @RequestParam(value="productFeatureIdOne", required=false) String productFeatureIdOne) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("variantProductIdsBag",variantProductIdsBag);
		paramMap.put("productId",productId);
		paramMap.put("productFeatureIdTwo",productFeatureIdTwo);
		paramMap.put("productFeatureIdThree",productFeatureIdThree);
		paramMap.put("productFeatureIdOne",productFeatureIdOne);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickCreateVirtualWithVariants", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductMaintType")
	public ResponseEntity<Object> updateProductMaintType(HttpSession session, @RequestParam(value="productMaintTypeId") String productMaintTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productMaintTypeId",productMaintTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductMaintType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateEmailContentForProduct")
	public ResponseEntity<Object> updateEmailContentForProduct(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="subject", required=false) String subject, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="plainBody", required=false) String plainBody, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="plainBodyDataResourceId", required=false) String plainBodyDataResourceId, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="htmlBodyDataResourceId", required=false) String htmlBodyDataResourceId, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="subjectDataResourceId", required=false) String subjectDataResourceId, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="htmlBody", required=false) String htmlBody, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("contentId",contentId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("subject",subject);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("plainBody",plainBody);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("plainBodyDataResourceId",plainBodyDataResourceId);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("htmlBodyDataResourceId",htmlBodyDataResourceId);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("useTime",useTime);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("subjectDataResourceId",subjectDataResourceId);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("htmlBody",htmlBody);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateEmailContentForProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateSimpleTextContentForProduct")
	public ResponseEntity<Object> updateSimpleTextContentForProduct(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="text", required=false) String text, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="textDataResourceId", required=false) String textDataResourceId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("contentId",contentId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("useTime",useTime);
		paramMap.put("text",text);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("textDataResourceId",textDataResourceId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSimpleTextContentForProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addImageFrame")
	public ResponseEntity<Object> addImageFrame(HttpSession session, @RequestParam(value="imageWidth") String imageWidth, @RequestParam(value="imageName") String imageName, @RequestParam(value="productId") String productId, @RequestParam(value="frameDataResourceId") String frameDataResourceId, @RequestParam(value="frameContentId") String frameContentId, @RequestParam(value="imageHeight") String imageHeight) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("imageWidth",imageWidth);
		paramMap.put("imageName",imageName);
		paramMap.put("productId",productId);
		paramMap.put("frameDataResourceId",frameDataResourceId);
		paramMap.put("frameContentId",frameContentId);
		paramMap.put("imageHeight",imageHeight);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addImageFrame", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProduct")
	public ResponseEntity<Object> updateProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="originGeoId", required=false) String originGeoId, @RequestParam(value="detailScreen", required=false) String detailScreen, @RequestParam(value="requireInventory", required=false) String requireInventory, @RequestParam(value="quantityUomId", required=false) String quantityUomId, @RequestParam(value="mediumImageUrl", required=false) String mediumImageUrl, @RequestParam(value="productName", required=false) String productName, @RequestParam(value="introductionDate", required=false) Timestamp introductionDate, @RequestParam(value="shippingHeight", required=false) BigDecimal shippingHeight, @RequestParam(value="originalImageUrl", required=false) String originalImageUrl, @RequestParam(value="inShippingBox", required=false) String inShippingBox, @RequestParam(value="detailImageUrl", required=false) String detailImageUrl, @RequestParam(value="supportDiscontinuationDate", required=false) Timestamp supportDiscontinuationDate, @RequestParam(value="productWidth", required=false) BigDecimal productWidth, @RequestParam(value="includeInPromotions", required=false) String includeInPromotions, @RequestParam(value="configId", required=false) String configId, @RequestParam(value="billOfMaterialLevel", required=false) Long billOfMaterialLevel, @RequestParam(value="lotIdFilledIn", required=false) String lotIdFilledIn, @RequestParam(value="productRating", required=false) BigDecimal productRating, @RequestParam(value="manufacturerPartyId", required=false) String manufacturerPartyId, @RequestParam(value="brandName", required=false) String brandName, @RequestParam(value="requireAmount", required=false) String requireAmount, @RequestParam(value="smallImageUrl", required=false) String smallImageUrl, @RequestParam(value="taxable", required=false) String taxable, @RequestParam(value="primaryProductCategoryId", required=false) String primaryProductCategoryId, @RequestParam(value="salesDiscontinuationDate", required=false) Timestamp salesDiscontinuationDate, @RequestParam(value="salesDiscWhenNotAvail", required=false) String salesDiscWhenNotAvail, @RequestParam(value="returnable", required=false) String returnable, @RequestParam(value="weightUomId", required=false) String weightUomId, @RequestParam(value="isVirtual", required=false) String isVirtual, @RequestParam(value="priceDetailText", required=false) String priceDetailText, @RequestParam(value="reserv2ndPPPerc", required=false) BigDecimal reserv2ndPPPerc, @RequestParam(value="widthUomId", required=false) String widthUomId, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="diameterUomId", required=false) String diameterUomId, @RequestParam(value="autoCreateKeywords", required=false) String autoCreateKeywords, @RequestParam(value="amountUomTypeId", required=false) String amountUomTypeId, @RequestParam(value="productDiameter", required=false) BigDecimal productDiameter, @RequestParam(value="ratingTypeEnum", required=false) String ratingTypeEnum, @RequestParam(value="description", required=false) String description, @RequestParam(value="chargeShipping", required=false) String chargeShipping, @RequestParam(value="reservNthPPPerc", required=false) BigDecimal reservNthPPPerc, @RequestParam(value="quantityIncluded", required=false) BigDecimal quantityIncluded, @RequestParam(value="heightUomId", required=false) String heightUomId, @RequestParam(value="internalName", required=false) String internalName, @RequestParam(value="virtualVariantMethodEnum", required=false) String virtualVariantMethodEnum, @RequestParam(value="shippingWeight", required=false) BigDecimal shippingWeight, @RequestParam(value="shippingWidth", required=false) BigDecimal shippingWidth, @RequestParam(value="shippingDepth", required=false) BigDecimal shippingDepth, @RequestParam(value="reservMaxPersons", required=false) BigDecimal reservMaxPersons, @RequestParam(value="fixedAmount", required=false) BigDecimal fixedAmount, @RequestParam(value="inventoryItemTypeId", required=false) String inventoryItemTypeId, @RequestParam(value="piecesIncluded", required=false) Long piecesIncluded, @RequestParam(value="productDepth", required=false) BigDecimal productDepth, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="releaseDate", required=false) Timestamp releaseDate, @RequestParam(value="productHeight", required=false) BigDecimal productHeight, @RequestParam(value="defaultShipmentBoxTypeId", required=false) String defaultShipmentBoxTypeId, @RequestParam(value="orderDecimalQuantity", required=false) String orderDecimalQuantity, @RequestParam(value="inventoryMessage", required=false) String inventoryMessage, @RequestParam(value="productWeight", required=false) BigDecimal productWeight, @RequestParam(value="depthUomId", required=false) String depthUomId, @RequestParam(value="productTypeId", required=false) String productTypeId, @RequestParam(value="isVariant", required=false) String isVariant, @RequestParam(value="largeImageUrl", required=false) String largeImageUrl, @RequestParam(value="requirementMethodEnumId", required=false) String requirementMethodEnumId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("originGeoId",originGeoId);
		paramMap.put("detailScreen",detailScreen);
		paramMap.put("requireInventory",requireInventory);
		paramMap.put("quantityUomId",quantityUomId);
		paramMap.put("mediumImageUrl",mediumImageUrl);
		paramMap.put("productName",productName);
		paramMap.put("introductionDate",introductionDate);
		paramMap.put("shippingHeight",shippingHeight);
		paramMap.put("originalImageUrl",originalImageUrl);
		paramMap.put("inShippingBox",inShippingBox);
		paramMap.put("detailImageUrl",detailImageUrl);
		paramMap.put("supportDiscontinuationDate",supportDiscontinuationDate);
		paramMap.put("productWidth",productWidth);
		paramMap.put("includeInPromotions",includeInPromotions);
		paramMap.put("configId",configId);
		paramMap.put("billOfMaterialLevel",billOfMaterialLevel);
		paramMap.put("lotIdFilledIn",lotIdFilledIn);
		paramMap.put("productRating",productRating);
		paramMap.put("manufacturerPartyId",manufacturerPartyId);
		paramMap.put("brandName",brandName);
		paramMap.put("requireAmount",requireAmount);
		paramMap.put("smallImageUrl",smallImageUrl);
		paramMap.put("taxable",taxable);
		paramMap.put("primaryProductCategoryId",primaryProductCategoryId);
		paramMap.put("salesDiscontinuationDate",salesDiscontinuationDate);
		paramMap.put("salesDiscWhenNotAvail",salesDiscWhenNotAvail);
		paramMap.put("returnable",returnable);
		paramMap.put("weightUomId",weightUomId);
		paramMap.put("isVirtual",isVirtual);
		paramMap.put("priceDetailText",priceDetailText);
		paramMap.put("reserv2ndPPPerc",reserv2ndPPPerc);
		paramMap.put("widthUomId",widthUomId);
		paramMap.put("longDescription",longDescription);
		paramMap.put("diameterUomId",diameterUomId);
		paramMap.put("autoCreateKeywords",autoCreateKeywords);
		paramMap.put("amountUomTypeId",amountUomTypeId);
		paramMap.put("productDiameter",productDiameter);
		paramMap.put("ratingTypeEnum",ratingTypeEnum);
		paramMap.put("description",description);
		paramMap.put("chargeShipping",chargeShipping);
		paramMap.put("reservNthPPPerc",reservNthPPPerc);
		paramMap.put("quantityIncluded",quantityIncluded);
		paramMap.put("heightUomId",heightUomId);
		paramMap.put("internalName",internalName);
		paramMap.put("virtualVariantMethodEnum",virtualVariantMethodEnum);
		paramMap.put("shippingWeight",shippingWeight);
		paramMap.put("shippingWidth",shippingWidth);
		paramMap.put("shippingDepth",shippingDepth);
		paramMap.put("reservMaxPersons",reservMaxPersons);
		paramMap.put("fixedAmount",fixedAmount);
		paramMap.put("inventoryItemTypeId",inventoryItemTypeId);
		paramMap.put("piecesIncluded",piecesIncluded);
		paramMap.put("productDepth",productDepth);
		paramMap.put("facilityId",facilityId);
		paramMap.put("comments",comments);
		paramMap.put("releaseDate",releaseDate);
		paramMap.put("productHeight",productHeight);
		paramMap.put("defaultShipmentBoxTypeId",defaultShipmentBoxTypeId);
		paramMap.put("orderDecimalQuantity",orderDecimalQuantity);
		paramMap.put("inventoryMessage",inventoryMessage);
		paramMap.put("productWeight",productWeight);
		paramMap.put("depthUomId",depthUomId);
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("isVariant",isVariant);
		paramMap.put("largeImageUrl",largeImageUrl);
		paramMap.put("requirementMethodEnumId",requirementMethodEnumId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductCategoryTypeAttr")
	public ResponseEntity<Object> createProductCategoryTypeAttr(HttpSession session, @RequestParam(value="productCategoryTypeId") String productCategoryTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryTypeId",productCategoryTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductCategoryTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/productPriceGenericPermission")
	public ResponseEntity<Object> productPriceGenericPermission(HttpSession session, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productPriceGenericPermission", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removePartyFromProduct")
	public ResponseEntity<Object> removePartyFromProduct(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productId") String productId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("productId",productId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removePartyFromProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductConfigProduct")
	public ResponseEntity<Object> deleteProductConfigProduct(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="productId") String productId, @RequestParam(value="configOptionId") String configOptionId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("productId",productId);
		paramMap.put("configOptionId",configOptionId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductConfigProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkOrderItemForProductGroupOrder")
	public ResponseEntity<Object> checkOrderItemForProductGroupOrder(HttpSession session, @RequestParam(value="orderId") String orderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId",orderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkOrderItemForProductGroupOrder", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductContentAndImageFile")
	public ResponseEntity<Object> removeProductContentAndImageFile(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("contentId",contentId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("useTime",useTime);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductContentAndImageFile", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/quickAddVariant")
	public ResponseEntity<Object> quickAddVariant(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productVariantId") String productVariantId, @RequestParam(value="productFeatureIds") String productFeatureIds, @RequestParam(value="sequenceNum", required=false) Long sequenceNum) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productVariantId",productVariantId);
		paramMap.put("productFeatureIds",productFeatureIds);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickAddVariant", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductGlAccount")
	public ResponseEntity<Object> createProductGlAccount(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteVendorProduct")
	public ResponseEntity<Object> deleteVendorProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="vendorPartyId") String vendorPartyId, @RequestParam(value="productStoreGroupId") String productStoreGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("vendorPartyId",vendorPartyId);
		paramMap.put("productStoreGroupId",productStoreGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteVendorProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteMarketInterest")
	public ResponseEntity<Object> deleteMarketInterest(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="partyClassificationGroupId") String partyClassificationGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("partyClassificationGroupId",partyClassificationGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteMarketInterest", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/renameImage")
	public ResponseEntity<Object> renameImage(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="drDataResourceName") String drDataResourceName, @RequestParam(value="contentId") String contentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("drDataResourceName",drDataResourceName);
		paramMap.put("contentId",contentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("renameImage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductCategoryToCategory")
	public ResponseEntity<Object> updateProductCategoryToCategory(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="parentProductCategoryId") String parentProductCategoryId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("parentProductCategoryId",parentProductCategoryId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductCategoryToCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductConfigItem")
	public ResponseEntity<Object> updateProductConfigItem(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="imageUrl", required=false) String imageUrl, @RequestParam(value="configItemName", required=false) String configItemName, @RequestParam(value="configItemTypeId", required=false) String configItemTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("longDescription",longDescription);
		paramMap.put("imageUrl",imageUrl);
		paramMap.put("configItemName",configItemName);
		paramMap.put("configItemTypeId",configItemTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductConfigItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWebAnalyticsType")
	public ResponseEntity<Object> updateWebAnalyticsType(HttpSession session, @RequestParam(value="webAnalyticsTypeId") String webAnalyticsTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webAnalyticsTypeId",webAnalyticsTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWebAnalyticsType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductTypeAttr")
	public ResponseEntity<Object> deleteProductTypeAttr(HttpSession session, @RequestParam(value="productTypeId") String productTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productTypeId",productTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductMeter")
	public ResponseEntity<Object> deleteProductMeter(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productMeterTypeId") String productMeterTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productMeterTypeId",productMeterTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductMeter", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductCategoryAttribute")
	public ResponseEntity<Object> createProductCategoryAttribute(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductCategoryAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createDownloadContentForProduct")
	public ResponseEntity<Object> createDownloadContentForProduct(HttpSession session, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="imageData", required=false) java.nio.ByteBuffer imageData, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="_imageData_fileName", required=false) String _imageData_fileName, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="_imageData_contentType", required=false) String _imageData_contentType, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("imageData",imageData);
		paramMap.put("contentId",contentId);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("useTime",useTime);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("productId",productId);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("_imageData_fileName",_imageData_fileName);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("_imageData_contentType",_imageData_contentType);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDownloadContentForProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateRelatedUrlContentForCategory")
	public ResponseEntity<Object> updateRelatedUrlContentForCategory(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="prodCatContentTypeId") String prodCatContentTypeId, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="contentId") String contentId, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="useDaysLimit", required=false) BigDecimal useDaysLimit, @RequestParam(value="description", required=false) String description, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="title", required=false) String title, @RequestParam(value="url", required=false) String url, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("contentId",contentId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("localeString",localeString);
		paramMap.put("useDaysLimit",useDaysLimit);
		paramMap.put("description",description);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("title",title);
		paramMap.put("url",url);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateRelatedUrlContentForCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkCategoryPermissionWithViewPurchaseAllow")
	public ResponseEntity<Object> checkCategoryPermissionWithViewPurchaseAllow(HttpSession session, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="primaryPermission", required=false) String primaryPermission, @RequestParam(value="altPermission", required=false) String altPermission, @RequestParam(value="resourceDescription", required=false) String resourceDescription, @RequestParam(value="mainAction", required=false) String mainAction) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("primaryPermission",primaryPermission);
		paramMap.put("altPermission",altPermission);
		paramMap.put("resourceDescription",resourceDescription);
		paramMap.put("mainAction",mainAction);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkCategoryPermissionWithViewPurchaseAllow", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/loadBestSellingCategory")
	public ResponseEntity<Object> loadBestSellingCategory(HttpSession session, @RequestParam(value="productStoreId") String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("loadBestSellingCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductToCategory")
	public ResponseEntity<Object> updateProductToCategory(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="productId") String productId, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="quantity", required=false) BigDecimal quantity, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("productId",productId);
		paramMap.put("comments",comments);
		paramMap.put("quantity",quantity);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductToCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/RemoveProductFromBestSellCategory")
	public ResponseEntity<Object> RemoveProductFromBestSellCategory(HttpSession session, @RequestParam(value="prodCatalogId") String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("RemoveProductFromBestSellCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductConfig")
	public ResponseEntity<Object> updateProductConfig(HttpSession session, @RequestParam(value="configItemId") String configItemId, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="sequenceNum") Long sequenceNum, @RequestParam(value="longDescription", required=false) String longDescription, @RequestParam(value="defaultConfigOptionId", required=false) String defaultConfigOptionId, @RequestParam(value="configTypeId", required=false) String configTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="isMandatory", required=false) String isMandatory, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("longDescription",longDescription);
		paramMap.put("defaultConfigOptionId",defaultConfigOptionId);
		paramMap.put("configTypeId",configTypeId);
		paramMap.put("description",description);
		paramMap.put("isMandatory",isMandatory);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductConfig", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductCategoryAttribute")
	public ResponseEntity<Object> deleteProductCategoryAttribute(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductCategoryAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductAttribute")
	public ResponseEntity<Object> deleteProductAttribute(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductAttribute", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addAdditionalViewForProduct")
	public ResponseEntity<Object> addAdditionalViewForProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("contentId",contentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("useTime",useTime);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addAdditionalViewForProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/productImportFromSpreadsheet")
	public ResponseEntity<Object> productImportFromSpreadsheet(HttpSession session, @RequestParam(value="dirName", required=false) java.lang.String dirName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dirName",dirName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("productImportFromSpreadsheet", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateGoodIdentification")
	public ResponseEntity<Object> updateGoodIdentification(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="goodIdentificationTypeId") String goodIdentificationTypeId, @RequestParam(value="idValue") String idValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("goodIdentificationTypeId",goodIdentificationTypeId);
		paramMap.put("idValue",idValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGoodIdentification", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProdCatalogFromParty")
	public ResponseEntity<Object> removeProdCatalogFromParty(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="partyId") String partyId, @RequestParam(value="prodCatalogId") String prodCatalogId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("partyId",partyId);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProdCatalogFromParty", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductReview")
	public ResponseEntity<Object> createProductReview(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productStoreId") String productStoreId, @RequestParam(value="productRating") BigDecimal productRating, @RequestParam(value="userLoginId", required=false) String userLoginId, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="postedDateTime", required=false) Timestamp postedDateTime, @RequestParam(value="postedAnonymous", required=false) String postedAnonymous, @RequestParam(value="productReview", required=false) String productReview) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("productRating",productRating);
		paramMap.put("userLoginId",userLoginId);
		paramMap.put("statusId",statusId);
		paramMap.put("postedDateTime",postedDateTime);
		paramMap.put("postedAnonymous",postedAnonymous);
		paramMap.put("productReview",productReview);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductReview", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/replaceImageToExistImage")
	public ResponseEntity<Object> replaceImageToExistImage(HttpSession session, @RequestParam(value="dataResourceNameExist") String dataResourceNameExist, @RequestParam(value="productId") String productId, @RequestParam(value="contentIdExist") String contentIdExist, @RequestParam(value="contentIdReplace") String contentIdReplace, @RequestParam(value="dataResourceNameReplace") String dataResourceNameReplace) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceNameExist",dataResourceNameExist);
		paramMap.put("productId",productId);
		paramMap.put("contentIdExist",contentIdExist);
		paramMap.put("contentIdReplace",contentIdReplace);
		paramMap.put("dataResourceNameReplace",dataResourceNameReplace);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("replaceImageToExistImage", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateWebAnalyticsConfig")
	public ResponseEntity<Object> updateWebAnalyticsConfig(HttpSession session, @RequestParam(value="webAnalyticsTypeId") String webAnalyticsTypeId, @RequestParam(value="webSiteId") String webSiteId, @RequestParam(value="webAnalyticsCode", required=false) String webAnalyticsCode) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webAnalyticsTypeId",webAnalyticsTypeId);
		paramMap.put("webSiteId",webSiteId);
		paramMap.put("webAnalyticsCode",webAnalyticsCode);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateWebAnalyticsConfig", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeCommunicationEventProduct")
	public ResponseEntity<Object> removeCommunicationEventProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="communicationEventId") String communicationEventId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("communicationEventId",communicationEventId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeCommunicationEventProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createWebAnalyticsType")
	public ResponseEntity<Object> createWebAnalyticsType(HttpSession session, @RequestParam(value="webAnalyticsTypeId") String webAnalyticsTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("webAnalyticsTypeId",webAnalyticsTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createWebAnalyticsType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addMultipleuploadForProduct")
	public ResponseEntity<Object> addMultipleuploadForProduct(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="imageResize", required=false) String imageResize, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="_uploadedFile_contentType", required=false) String _uploadedFile_contentType, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="uploadedFile", required=false) java.nio.ByteBuffer uploadedFile, @RequestParam(value="_uploadedFile_fileName", required=false) String _uploadedFile_fileName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("contentId",contentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("imageResize",imageResize);
		paramMap.put("thruDate",thruDate);
		paramMap.put("fromDate",fromDate);
		paramMap.put("_uploadedFile_contentType",_uploadedFile_contentType);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("useTime",useTime);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("_uploadedFile_fileName",_uploadedFile_fileName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addMultipleuploadForProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/FindBestSellingProduct")
	public ResponseEntity<Object> FindBestSellingProduct(HttpSession session, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="week") Long week, @RequestParam(value="primaryProductCategoryId") String primaryProductCategoryId, @RequestParam(value="year") Long year, @RequestParam(value="productStoreId") String productStoreId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("week",week);
		paramMap.put("primaryProductCategoryId",primaryProductCategoryId);
		paramMap.put("year",year);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("FindBestSellingProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createDownloadContentForCategory")
	public ResponseEntity<Object> createDownloadContentForCategory(HttpSession session, @RequestParam(value="prodCatContentTypeId") String prodCatContentTypeId, @RequestParam(value="ownerContentId", required=false) String ownerContentId, @RequestParam(value="imageData", required=false) java.nio.ByteBuffer imageData, @RequestParam(value="contentId", required=false) String contentId, @RequestParam(value="useDaysLimit", required=false) BigDecimal useDaysLimit, @RequestParam(value="contentTypeId", required=false) String contentTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="mimeTypeId", required=false) String mimeTypeId, @RequestParam(value="childLeafCount", required=false) Long childLeafCount, @RequestParam(value="privilegeEnumId", required=false) String privilegeEnumId, @RequestParam(value="dataSourceId", required=false) String dataSourceId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="dataResourceId", required=false) String dataResourceId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="dataResourceTypeId", required=false) String dataResourceTypeId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="templateDataResourceId", required=false) String templateDataResourceId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="instanceOfContentId", required=false) String instanceOfContentId, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="serviceName", required=false) String serviceName, @RequestParam(value="customMethodId", required=false) String customMethodId, @RequestParam(value="characterSetId", required=false) String characterSetId, @RequestParam(value="_imageData_fileName", required=false) String _imageData_fileName, @RequestParam(value="thruDate", required=false) Timestamp thruDate, @RequestParam(value="contentName", required=false) Long contentName, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="localeString", required=false) String localeString, @RequestParam(value="_imageData_contentType", required=false) String _imageData_contentType, @RequestParam(value="decoratorContentId", required=false) String decoratorContentId, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="childBranchCount", required=false) Long childBranchCount) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("prodCatContentTypeId",prodCatContentTypeId);
		paramMap.put("ownerContentId",ownerContentId);
		paramMap.put("imageData",imageData);
		paramMap.put("contentId",contentId);
		paramMap.put("useDaysLimit",useDaysLimit);
		paramMap.put("contentTypeId",contentTypeId);
		paramMap.put("description",description);
		paramMap.put("mimeTypeId",mimeTypeId);
		paramMap.put("childLeafCount",childLeafCount);
		paramMap.put("privilegeEnumId",privilegeEnumId);
		paramMap.put("dataSourceId",dataSourceId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("dataResourceTypeId",dataResourceTypeId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("templateDataResourceId",templateDataResourceId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("instanceOfContentId",instanceOfContentId);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("serviceName",serviceName);
		paramMap.put("customMethodId",customMethodId);
		paramMap.put("characterSetId",characterSetId);
		paramMap.put("_imageData_fileName",_imageData_fileName);
		paramMap.put("thruDate",thruDate);
		paramMap.put("contentName",contentName);
		paramMap.put("fromDate",fromDate);
		paramMap.put("createdDate",createdDate);
		paramMap.put("statusId",statusId);
		paramMap.put("localeString",localeString);
		paramMap.put("_imageData_contentType",_imageData_contentType);
		paramMap.put("decoratorContentId",decoratorContentId);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("childBranchCount",childBranchCount);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createDownloadContentForCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductCategoryFromCategory")
	public ResponseEntity<Object> removeProductCategoryFromCategory(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productCategoryId") String productCategoryId, @RequestParam(value="parentProductCategoryId") String parentProductCategoryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("parentProductCategoryId",parentProductCategoryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductCategoryFromCategory", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductGlAccount")
	public ResponseEntity<Object> deleteProductGlAccount(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductGlAccount", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/getProductFeaturesByType")
	public ResponseEntity<Object> getProductFeaturesByType(HttpSession session, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="productFeatureApplTypeId", required=false) String productFeatureApplTypeId, @RequestParam(value="productFeatureCategoryId", required=false) String productFeatureCategoryId, @RequestParam(value="productFeatureGroupId", required=false) String productFeatureGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productFeatureApplTypeId",productFeatureApplTypeId);
		paramMap.put("productFeatureCategoryId",productFeatureCategoryId);
		paramMap.put("productFeatureGroupId",productFeatureGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getProductFeaturesByType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductCategoryTypeAttr")
	public ResponseEntity<Object> deleteProductCategoryTypeAttr(HttpSession session, @RequestParam(value="productCategoryTypeId") String productCategoryTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productCategoryTypeId",productCategoryTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductCategoryTypeAttr", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createGoodIdentificationType")
	public ResponseEntity<Object> createGoodIdentificationType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="goodIdentificationTypeId", required=false) String goodIdentificationTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("goodIdentificationTypeId",goodIdentificationTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGoodIdentificationType", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductFeatureDataResource")
	public ResponseEntity<Object> removeProductFeatureDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="productFeatureId") String productFeatureId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductFeatureDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductKeyword")
	public ResponseEntity<Object> deleteProductKeyword(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="keywordTypeId") String keywordTypeId, @RequestParam(value="keyword") String keyword) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("keywordTypeId",keywordTypeId);
		paramMap.put("keyword",keyword);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductKeyword", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/updateCustomerDigitalDownloadProduct")
	public ResponseEntity<Object> updateCustomerDigitalDownloadProduct(HttpSession session, @RequestParam(value="currencyUomId") String currencyUomId, @RequestParam(value="productId") String productId, @RequestParam(value="availableFromDate") Timestamp availableFromDate, @RequestParam(value="minimumOrderQuantity") BigDecimal minimumOrderQuantity, @RequestParam(value="price", required=false) BigDecimal price, @RequestParam(value="description", required=false) String description, @RequestParam(value="productName", required=false) String productName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("productId",productId);
		paramMap.put("availableFromDate",availableFromDate);
		paramMap.put("minimumOrderQuantity",minimumOrderQuantity);
		paramMap.put("price",price);
		paramMap.put("description",description);
		paramMap.put("productName",productName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateCustomerDigitalDownloadProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/removeProductContent")
	public ResponseEntity<Object> removeProductContent(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="productId") String productId, @RequestParam(value="contentId") String contentId, @RequestParam(value="productContentTypeId") String productContentTypeId, @RequestParam(value="purchaseFromDate", required=false) Timestamp purchaseFromDate, @RequestParam(value="useTimeUomId", required=false) String useTimeUomId, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="useRoleTypeId", required=false) String useRoleTypeId, @RequestParam(value="useTime", required=false) Long useTime, @RequestParam(value="purchaseThruDate", required=false) Timestamp purchaseThruDate, @RequestParam(value="useCountLimit", required=false) Long useCountLimit, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("productId",productId);
		paramMap.put("contentId",contentId);
		paramMap.put("productContentTypeId",productContentTypeId);
		paramMap.put("purchaseFromDate",purchaseFromDate);
		paramMap.put("useTimeUomId",useTimeUomId);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("useRoleTypeId",useRoleTypeId);
		paramMap.put("useTime",useTime);
		paramMap.put("purchaseThruDate",purchaseThruDate);
		paramMap.put("useCountLimit",useCountLimit);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeProductContent", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductConfigItem")
	public ResponseEntity<Object> deleteProductConfigItem(HttpSession session, @RequestParam(value="configItemId") String configItemId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("configItemId",configItemId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductConfigItem", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/checkProductGroupOrderExpired")
	public ResponseEntity<Object> checkProductGroupOrderExpired(HttpSession session, @RequestParam(value="groupOrderId") String groupOrderId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupOrderId",groupOrderId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("checkProductGroupOrderExpired", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProductFeatureDataResource")
	public ResponseEntity<Object> createProductFeatureDataResource(HttpSession session, @RequestParam(value="dataResourceId") String dataResourceId, @RequestParam(value="productFeatureId") String productFeatureId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("dataResourceId",dataResourceId);
		paramMap.put("productFeatureId",productFeatureId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductFeatureDataResource", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/uploadFrame")
	public ResponseEntity<Object> uploadFrame(HttpSession session, @RequestParam(value="uploadedFile") java.nio.ByteBuffer uploadedFile) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uploadedFile",uploadedFile);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("uploadFrame", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductMaint")
	public ResponseEntity<Object> deleteProductMaint(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productMaintSeqId") String productMaintSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productMaintSeqId",productMaintSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductMaint", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/createProdCatalog")
	public ResponseEntity<Object> createProdCatalog(HttpSession session, @RequestParam(value="catalogName") String catalogName, @RequestParam(value="headerLogo", required=false) String headerLogo, @RequestParam(value="styleSheet", required=false) String styleSheet, @RequestParam(value="templatePathPrefix", required=false) String templatePathPrefix, @RequestParam(value="useQuickAdd", required=false) String useQuickAdd, @RequestParam(value="contentPathPrefix", required=false) String contentPathPrefix, @RequestParam(value="viewAllowPermReqd", required=false) String viewAllowPermReqd, @RequestParam(value="prodCatalogId", required=false) String prodCatalogId, @RequestParam(value="purchaseAllowPermReqd", required=false) String purchaseAllowPermReqd) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("catalogName",catalogName);
		paramMap.put("headerLogo",headerLogo);
		paramMap.put("styleSheet",styleSheet);
		paramMap.put("templatePathPrefix",templatePathPrefix);
		paramMap.put("useQuickAdd",useQuickAdd);
		paramMap.put("contentPathPrefix",contentPathPrefix);
		paramMap.put("viewAllowPermReqd",viewAllowPermReqd);
		paramMap.put("prodCatalogId",prodCatalogId);
		paramMap.put("purchaseAllowPermReqd",purchaseAllowPermReqd);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProdCatalog", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/addPartyToProduct")
	public ResponseEntity<Object> addPartyToProduct(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="productId") String productId, @RequestParam(value="partyId") String partyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="comments", required=false) String comments, @RequestParam(value="sequenceNum", required=false) Long sequenceNum, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("productId",productId);
		paramMap.put("partyId",partyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("comments",comments);
		paramMap.put("sequenceNum",sequenceNum);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("addPartyToProduct", paramMap);
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

	@RequestMapping(method = RequestMethod.POST, value = "/indexProductKeywords")
	public ResponseEntity<Object> indexProductKeywords(HttpSession session, @RequestParam(value="productId") String productId, @RequestParam(value="productInstance", required=false) org.apache.ofbiz.entity.GenericValue productInstance) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId",productId);
		paramMap.put("productInstance",productInstance);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("indexProductKeywords", paramMap);
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
