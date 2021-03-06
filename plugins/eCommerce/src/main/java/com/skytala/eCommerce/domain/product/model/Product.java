package com.skytala.eCommerce.domain.product.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;

import com.skytala.eCommerce.domain.product.event.ProductFound;
import com.skytala.eCommerce.domain.product.mapper.ProductMapper;
import com.skytala.eCommerce.domain.product.query.FindProductsBy;
import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Scheduler;
import com.skytala.eCommerce.framework.util.TimestampUtil;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ServiceAuthException;
import org.apache.ofbiz.service.ServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

public class Product implements Serializable{

    private static final long serialVersionUID = 1L;
    private String productId;
    private String productTypeId;
    private String primaryProductCategoryId;
    private String manufacturerPartyId;
    private String facilityId;
    private Timestamp introductionDate;
    private Timestamp releaseDate;
    private Timestamp supportDiscontinuationDate;
    private Timestamp salesDiscontinuationDate;
    private Boolean salesDiscWhenNotAvail;
    private String internalName;
    private String brandName;
    private String comments;
    private String productName;
    private String description;
    private String longDescription;
    private String priceDetailText;
    private String smallImageUrl;
    private String mediumImageUrl;
    private String largeImageUrl;
    private String detailImageUrl;
    private String originalImageUrl;
    private String detailScreen;
    private String inventoryMessage;
    private String inventoryItemTypeId;
    private Boolean requireInventory;
    private String quantityUomId;
    private BigDecimal quantityIncluded;
    private Long piecesIncluded;
    private Boolean requireAmount;
    private BigDecimal fixedAmount;
    private String amountUomTypeId;
    private String weightUomId;
    private BigDecimal shippingWeight;
    private BigDecimal productWeight;
    private String heightUomId;
    private BigDecimal productHeight;
    private BigDecimal shippingHeight;
    private String widthUomId;
    private BigDecimal productWidth;
    private BigDecimal shippingWidth;
    private String depthUomId;
    private BigDecimal productDepth;
    private BigDecimal shippingDepth;
    private String diameterUomId;
    private BigDecimal productDiameter;
    private BigDecimal productRating;
    private String ratingTypeEnum;
    private Boolean returnable;
    private Boolean taxable;
    private Boolean chargeShipping;
    private Boolean autoCreateKeywords;
    private Boolean includeInPromotions;
    private Boolean isVirtual;
    private Boolean isVariant;
    private String virtualVariantMethodEnum;
    private String originGeoId;
    private String requirementMethodEnumId;
    private Long billOfMaterialLevel;
    private BigDecimal reservMaxPersons;
    private BigDecimal reserv2ndPPPerc;
    private BigDecimal reservNthPPPerc;
    private String configId;
    private Timestamp createdDate;
    private String createdByUserLogin;
    private Timestamp lastModifiedDate;
    private String lastModifiedByUserLogin;
    private Boolean inShippingBox;
    private String defaultShipmentBoxTypeId;
    private String lotIdFilledIn;

    private Boolean orderDecimalQuantity;

    private Boolean equalsDatabaseRecord;
    private final FindProductsBy queryToLoadRest;

    public Product(String productId, FindProductsBy queryToLoadRest) {
        this.productId = productId;
        this.queryToLoadRest = queryToLoadRest;
        equalsDatabaseRecord = false;
    }

    public Product() {
        queryToLoadRest = null;
        equalsDatabaseRecord = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != null ? !productId.equals(product.productId) : product.productId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return productId != null ? productId.hashCode() : 0;
    }

    public Boolean getVirtual() {
        if(!equalsDatabaseRecord)
            lazyLoad();

        return isVirtual;
    }

    public void setVirtual(Boolean virtual) {
        isVirtual = virtual;
    }

    public Boolean getVariant() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return isVariant;
    }

    public void setVariant(Boolean variant) {
        isVariant = variant;
    }

    public String getProductId() {

        return productId;
    }

    public void setProductId(String  productId) {
        this.productId = productId;
    }

    public String getProductTypeId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return productTypeId;
    }

    public void setProductTypeId(String  productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getPrimaryProductCategoryId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return primaryProductCategoryId;
    }

    public void setPrimaryProductCategoryId(String  primaryProductCategoryId) {
        this.primaryProductCategoryId = primaryProductCategoryId;
    }

    public String getManufacturerPartyId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return manufacturerPartyId;
    }

    public void setManufacturerPartyId(String  manufacturerPartyId) {
        this.manufacturerPartyId = manufacturerPartyId;
    }

    public String getFacilityId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return facilityId;
    }

    public void setFacilityId(String  facilityId) {
        this.facilityId = facilityId;
    }

    public Timestamp getIntroductionDate() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return introductionDate;
    }

    public void setIntroductionDate(Timestamp  introductionDate) {
        this.introductionDate = introductionDate;
    }

    public Timestamp getReleaseDate() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return releaseDate;
    }

    public void setReleaseDate(Timestamp  releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Timestamp getSupportDiscontinuationDate() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return supportDiscontinuationDate;
    }

    public void setSupportDiscontinuationDate(Timestamp  supportDiscontinuationDate) {
        this.supportDiscontinuationDate = supportDiscontinuationDate;
    }

    public Timestamp getSalesDiscontinuationDate() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return salesDiscontinuationDate;
    }

    public void setSalesDiscontinuationDate(Timestamp  salesDiscontinuationDate) {
        this.salesDiscontinuationDate = salesDiscontinuationDate;
    }

    public Boolean getSalesDiscWhenNotAvail() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return salesDiscWhenNotAvail;
    }

    public void setSalesDiscWhenNotAvail(Boolean  salesDiscWhenNotAvail) {
        this.salesDiscWhenNotAvail = salesDiscWhenNotAvail;
    }

    public String getInternalName() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return internalName;
    }

    public void setInternalName(String  internalName) {
        this.internalName = internalName;
    }

    public String getBrandName() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return brandName;
    }

    public void setBrandName(String  brandName) {
        this.brandName = brandName;
    }

    public String getComments() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return comments;
    }

    public void setComments(String  comments) {
        this.comments = comments;
    }

    public String getProductName() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return productName;
    }

    public void setProductName(String  productName) {
        this.productName = productName;
    }

    public String getDescription() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return description;
    }

    public void setDescription(String  description) {
        this.description = description;
    }

    public String getLongDescription() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return longDescription;
    }

    public void setLongDescription(String  longDescription) {
        this.longDescription = longDescription;
    }

    public String getPriceDetailText() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return priceDetailText;
    }

    public void setPriceDetailText(String  priceDetailText) {
        this.priceDetailText = priceDetailText;
    }

    public String getSmallImageUrl() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return smallImageUrl;
    }

    public void setSmallImageUrl(String  smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getMediumImageUrl() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return mediumImageUrl;
    }

    public void setMediumImageUrl(String  mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }

    public String getLargeImageUrl() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return largeImageUrl;
    }

    public void setLargeImageUrl(String  largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public String getDetailImageUrl() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return detailImageUrl;
    }

    public void setDetailImageUrl(String  detailImageUrl) {
        this.detailImageUrl = detailImageUrl;
    }

    public String getOriginalImageUrl() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return originalImageUrl;
    }

    public void setOriginalImageUrl(String  originalImageUrl) {
        this.originalImageUrl = originalImageUrl;
    }

    public String getDetailScreen() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return detailScreen;
    }

    public void setDetailScreen(String  detailScreen) {
        this.detailScreen = detailScreen;
    }

    public String getInventoryMessage() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return inventoryMessage;
    }

    public void setInventoryMessage(String  inventoryMessage) {
        this.inventoryMessage = inventoryMessage;
    }

    public String getInventoryItemTypeId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return inventoryItemTypeId;
    }

    public void setInventoryItemTypeId(String  inventoryItemTypeId) {
        this.inventoryItemTypeId = inventoryItemTypeId;
    }

    public Boolean getRequireInventory() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return requireInventory;
    }

    public void setRequireInventory(Boolean  requireInventory) {
        this.requireInventory = requireInventory;
    }

    public String getQuantityUomId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return quantityUomId;
    }

    public void setQuantityUomId(String  quantityUomId) {
        this.quantityUomId = quantityUomId;
    }

    public BigDecimal getQuantityIncluded() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return quantityIncluded;
    }

    public void setQuantityIncluded(BigDecimal  quantityIncluded) {
        this.quantityIncluded = quantityIncluded;
    }

    public Long getPiecesIncluded() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return piecesIncluded;
    }

    public void setPiecesIncluded(Long  piecesIncluded) {
        this.piecesIncluded = piecesIncluded;
    }

    public Boolean getRequireAmount() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return requireAmount;
    }

    public void setRequireAmount(Boolean  requireAmount) {
        this.requireAmount = requireAmount;
    }

    public BigDecimal getFixedAmount() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return fixedAmount;
    }

    public void setFixedAmount(BigDecimal  fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public String getAmountUomTypeId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return amountUomTypeId;
    }

    public void setAmountUomTypeId(String  amountUomTypeId) {
        this.amountUomTypeId = amountUomTypeId;
    }

    public String getWeightUomId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return weightUomId;
    }

    public void setWeightUomId(String  weightUomId) {
        this.weightUomId = weightUomId;
    }

    public BigDecimal getShippingWeight() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return shippingWeight;
    }

    public void setShippingWeight(BigDecimal  shippingWeight) {
        this.shippingWeight = shippingWeight;
    }

    public BigDecimal getProductWeight() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return productWeight;
    }

    public void setProductWeight(BigDecimal  productWeight) {
        this.productWeight = productWeight;
    }

    public String getHeightUomId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return heightUomId;
    }

    public void setHeightUomId(String  heightUomId) {
        this.heightUomId = heightUomId;
    }

    public BigDecimal getProductHeight() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return productHeight;
    }

    public void setProductHeight(BigDecimal  productHeight) {
        this.productHeight = productHeight;
    }

    public BigDecimal getShippingHeight() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return shippingHeight;
    }

    public void setShippingHeight(BigDecimal  shippingHeight) {
        this.shippingHeight = shippingHeight;
    }

    public String getWidthUomId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return widthUomId;
    }

    public void setWidthUomId(String  widthUomId) {
        this.widthUomId = widthUomId;
    }

    public BigDecimal getProductWidth() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return productWidth;
    }

    public void setProductWidth(BigDecimal  productWidth) {
        this.productWidth = productWidth;
    }

    public BigDecimal getShippingWidth() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return shippingWidth;
    }

    public void setShippingWidth(BigDecimal  shippingWidth) {
        this.shippingWidth = shippingWidth;
    }

    public String getDepthUomId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return depthUomId;
    }

    public void setDepthUomId(String  depthUomId) {
        this.depthUomId = depthUomId;
    }

    public BigDecimal getProductDepth() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return productDepth;
    }

    public void setProductDepth(BigDecimal  productDepth) {
        this.productDepth = productDepth;
    }

    public BigDecimal getShippingDepth() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return shippingDepth;
    }

    public void setShippingDepth(BigDecimal  shippingDepth) {
        this.shippingDepth = shippingDepth;
    }

    public String getDiameterUomId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return diameterUomId;
    }

    public void setDiameterUomId(String  diameterUomId) {
        this.diameterUomId = diameterUomId;
    }

    public BigDecimal getProductDiameter() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return productDiameter;
    }

    public void setProductDiameter(BigDecimal  productDiameter) {
        this.productDiameter = productDiameter;
    }

    public BigDecimal getProductRating() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return productRating;
    }

    public void setProductRating(BigDecimal  productRating) {
        this.productRating = productRating;
    }

    public String getRatingTypeEnum() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return ratingTypeEnum;
    }

    public void setRatingTypeEnum(String  ratingTypeEnum) {
        this.ratingTypeEnum = ratingTypeEnum;
    }

    public Boolean getReturnable() {
        if(!equalsDatabaseRecord)
            lazyLoad();

        return returnable;
    }

    public void setReturnable(Boolean  returnable) {
        this.returnable = returnable;
    }

    public Boolean getTaxable() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return taxable;
    }

    public void setTaxable(Boolean  taxable) {
        this.taxable = taxable;
    }

    public Boolean getChargeShipping() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return chargeShipping;
    }

    public void setChargeShipping(Boolean  chargeShipping) {
        this.chargeShipping = chargeShipping;
    }

    public Boolean getAutoCreateKeywords() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return autoCreateKeywords;
    }

    public void setAutoCreateKeywords(Boolean  autoCreateKeywords) {
        this.autoCreateKeywords = autoCreateKeywords;
    }

    public Boolean getIncludeInPromotions() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return includeInPromotions;
    }

    public void setIncludeInPromotions(Boolean  includeInPromotions) {
        this.includeInPromotions = includeInPromotions;
    }

    public Boolean getIsVirtual() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return isVirtual;
    }

    public void setIsVirtual(Boolean  isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Boolean getIsVariant() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return isVariant;
    }

    public void setIsVariant(Boolean  isVariant) {
        this.isVariant = isVariant;
    }

    public String getVirtualVariantMethodEnum() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return virtualVariantMethodEnum;
    }

    public void setVirtualVariantMethodEnum(String  virtualVariantMethodEnum) {
        this.virtualVariantMethodEnum = virtualVariantMethodEnum;
    }

    public String getOriginGeoId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return originGeoId;
    }

    public void setOriginGeoId(String  originGeoId) {
        this.originGeoId = originGeoId;
    }

    public String getRequirementMethodEnumId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return requirementMethodEnumId;
    }

    public void setRequirementMethodEnumId(String  requirementMethodEnumId) {
        this.requirementMethodEnumId = requirementMethodEnumId;
    }

    public Long getBillOfMaterialLevel() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return billOfMaterialLevel;
    }

    public void setBillOfMaterialLevel(Long  billOfMaterialLevel) {
        this.billOfMaterialLevel = billOfMaterialLevel;
    }

    public BigDecimal getReservMaxPersons() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return reservMaxPersons;
    }

    public void setReservMaxPersons(BigDecimal  reservMaxPersons) {
        this.reservMaxPersons = reservMaxPersons;
    }

    public BigDecimal getReserv2ndPPPerc() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return reserv2ndPPPerc;
    }

    public void setReserv2ndPPPerc(BigDecimal  reserv2ndPPPerc) {
        this.reserv2ndPPPerc = reserv2ndPPPerc;
    }

    public BigDecimal getReservNthPPPerc() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return reservNthPPPerc;
    }

    public void setReservNthPPPerc(BigDecimal  reservNthPPPerc) {
        this.reservNthPPPerc = reservNthPPPerc;
    }

    public String getConfigId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return configId;
    }

    public void setConfigId(String  configId) {
        this.configId = configId;
    }

    public Timestamp getCreatedDate() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return createdDate;
    }

    public void setCreatedDate(Timestamp  createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByUserLogin() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return createdByUserLogin;
    }

    public void setCreatedByUserLogin(String  createdByUserLogin) {
        this.createdByUserLogin = createdByUserLogin;
    }

    public Timestamp getLastModifiedDate() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp  lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedByUserLogin() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return lastModifiedByUserLogin;
    }

    public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
        this.lastModifiedByUserLogin = lastModifiedByUserLogin;
    }

    public Boolean getInShippingBox() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return inShippingBox;
    }

    public void setInShippingBox(Boolean  inShippingBox) {
        this.inShippingBox = inShippingBox;
    }

    public String getDefaultShipmentBoxTypeId() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return defaultShipmentBoxTypeId;
    }

    public void setDefaultShipmentBoxTypeId(String  defaultShipmentBoxTypeId) {
        this.defaultShipmentBoxTypeId = defaultShipmentBoxTypeId;
    }

    public String getLotIdFilledIn() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return lotIdFilledIn;
    }

    public void setLotIdFilledIn(String  lotIdFilledIn) {
        this.lotIdFilledIn = lotIdFilledIn;
    }

    public Boolean getOrderDecimalQuantity() {
        if(!equalsDatabaseRecord)
            lazyLoad();
        return orderDecimalQuantity;
    }

    public void setOrderDecimalQuantity(Boolean  orderDecimalQuantity) {
        this.orderDecimalQuantity = orderDecimalQuantity;
    }


    private void lazyLoad(){
        Product product = null;
        try {
            List<Product> products = ((ProductFound)Scheduler.execute(queryToLoadRest).data()).getProducts();
            if(products.size()==1)
                product = products.get(0);
            else
                throw new RecordNotFoundException(Product.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //TODO: set all thingies
        Debug.log("Lazy load not yet implemented");
        equalsDatabaseRecord = false;
    }

    public Map<String, Object> mapAttributeField() {
        return ProductMapper.map(this);
    }


    public void addToCategory(HttpSession session, ProductCategory category) throws GenericServiceException {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productCategoryId",category.getProductCategoryId());
        paramMap.put("productId",productId);
        paramMap.put("fromDate", TimestampUtil.currentTime());
        paramMap.put("userLogin", session.getAttribute("userLogin"));

        Map<String, Object> result = new HashMap<>();
        LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");


        result = dispatcher.runSync("addProductToCategory", paramMap);

        if(result.get("responseMessage").equals("error"))
            throw new IllegalArgumentException("Ofbiz was not able to process the data");


    }

    public void addToCategories(HttpSession session, List<ProductCategory> categories) throws GenericServiceException {

        for (int i = 0;i < categories.size(); i++){

            addToCategory(session, categories.get(i));
        }


    }

    public void removeFromCategory(HttpSession session, ProductCategory category, Timestamp fromDate) throws GenericServiceException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromDate", fromDate);
        paramMap.put("productCategoryId", category.getProductCategoryId());
        paramMap.put("productId",productId);
        paramMap.put("userLogin", session.getAttribute("userLogin"));

        Map<String, Object> result = new HashMap<>();
        LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");

        result = dispatcher.runSync("removeProductFromCategory", paramMap);

        if(result.get("responseMessage").equals("error")) {
            throw new IllegalArgumentException("Ofbiz was not able to process the data");
        }

    }

}
