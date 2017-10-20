package com.skytala.eCommerce.domain.product.model;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.product.mapper.ProductMapper;

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

public String getProductId() {
return productId;
}

public void setProductId(String  productId) {
this.productId = productId;
}

public String getProductTypeId() {
return productTypeId;
}

public void setProductTypeId(String  productTypeId) {
this.productTypeId = productTypeId;
}

public String getPrimaryProductCategoryId() {
return primaryProductCategoryId;
}

public void setPrimaryProductCategoryId(String  primaryProductCategoryId) {
this.primaryProductCategoryId = primaryProductCategoryId;
}

public String getManufacturerPartyId() {
return manufacturerPartyId;
}

public void setManufacturerPartyId(String  manufacturerPartyId) {
this.manufacturerPartyId = manufacturerPartyId;
}

public String getFacilityId() {
return facilityId;
}

public void setFacilityId(String  facilityId) {
this.facilityId = facilityId;
}

public Timestamp getIntroductionDate() {
return introductionDate;
}

public void setIntroductionDate(Timestamp  introductionDate) {
this.introductionDate = introductionDate;
}

public Timestamp getReleaseDate() {
return releaseDate;
}

public void setReleaseDate(Timestamp  releaseDate) {
this.releaseDate = releaseDate;
}

public Timestamp getSupportDiscontinuationDate() {
return supportDiscontinuationDate;
}

public void setSupportDiscontinuationDate(Timestamp  supportDiscontinuationDate) {
this.supportDiscontinuationDate = supportDiscontinuationDate;
}

public Timestamp getSalesDiscontinuationDate() {
return salesDiscontinuationDate;
}

public void setSalesDiscontinuationDate(Timestamp  salesDiscontinuationDate) {
this.salesDiscontinuationDate = salesDiscontinuationDate;
}

public Boolean getSalesDiscWhenNotAvail() {
return salesDiscWhenNotAvail;
}

public void setSalesDiscWhenNotAvail(Boolean  salesDiscWhenNotAvail) {
this.salesDiscWhenNotAvail = salesDiscWhenNotAvail;
}

public String getInternalName() {
return internalName;
}

public void setInternalName(String  internalName) {
this.internalName = internalName;
}

public String getBrandName() {
return brandName;
}

public void setBrandName(String  brandName) {
this.brandName = brandName;
}

public String getComments() {
return comments;
}

public void setComments(String  comments) {
this.comments = comments;
}

public String getProductName() {
return productName;
}

public void setProductName(String  productName) {
this.productName = productName;
}

public String getDescription() {
return description;
}

public void setDescription(String  description) {
this.description = description;
}

public String getLongDescription() {
return longDescription;
}

public void setLongDescription(String  longDescription) {
this.longDescription = longDescription;
}

public String getPriceDetailText() {
return priceDetailText;
}

public void setPriceDetailText(String  priceDetailText) {
this.priceDetailText = priceDetailText;
}

public String getSmallImageUrl() {
return smallImageUrl;
}

public void setSmallImageUrl(String  smallImageUrl) {
this.smallImageUrl = smallImageUrl;
}

public String getMediumImageUrl() {
return mediumImageUrl;
}

public void setMediumImageUrl(String  mediumImageUrl) {
this.mediumImageUrl = mediumImageUrl;
}

public String getLargeImageUrl() {
return largeImageUrl;
}

public void setLargeImageUrl(String  largeImageUrl) {
this.largeImageUrl = largeImageUrl;
}

public String getDetailImageUrl() {
return detailImageUrl;
}

public void setDetailImageUrl(String  detailImageUrl) {
this.detailImageUrl = detailImageUrl;
}

public String getOriginalImageUrl() {
return originalImageUrl;
}

public void setOriginalImageUrl(String  originalImageUrl) {
this.originalImageUrl = originalImageUrl;
}

public String getDetailScreen() {
return detailScreen;
}

public void setDetailScreen(String  detailScreen) {
this.detailScreen = detailScreen;
}

public String getInventoryMessage() {
return inventoryMessage;
}

public void setInventoryMessage(String  inventoryMessage) {
this.inventoryMessage = inventoryMessage;
}

public String getInventoryItemTypeId() {
return inventoryItemTypeId;
}

public void setInventoryItemTypeId(String  inventoryItemTypeId) {
this.inventoryItemTypeId = inventoryItemTypeId;
}

public Boolean getRequireInventory() {
return requireInventory;
}

public void setRequireInventory(Boolean  requireInventory) {
this.requireInventory = requireInventory;
}

public String getQuantityUomId() {
return quantityUomId;
}

public void setQuantityUomId(String  quantityUomId) {
this.quantityUomId = quantityUomId;
}

public BigDecimal getQuantityIncluded() {
return quantityIncluded;
}

public void setQuantityIncluded(BigDecimal  quantityIncluded) {
this.quantityIncluded = quantityIncluded;
}

public Long getPiecesIncluded() {
return piecesIncluded;
}

public void setPiecesIncluded(Long  piecesIncluded) {
this.piecesIncluded = piecesIncluded;
}

public Boolean getRequireAmount() {
return requireAmount;
}

public void setRequireAmount(Boolean  requireAmount) {
this.requireAmount = requireAmount;
}

public BigDecimal getFixedAmount() {
return fixedAmount;
}

public void setFixedAmount(BigDecimal  fixedAmount) {
this.fixedAmount = fixedAmount;
}

public String getAmountUomTypeId() {
return amountUomTypeId;
}

public void setAmountUomTypeId(String  amountUomTypeId) {
this.amountUomTypeId = amountUomTypeId;
}

public String getWeightUomId() {
return weightUomId;
}

public void setWeightUomId(String  weightUomId) {
this.weightUomId = weightUomId;
}

public BigDecimal getShippingWeight() {
return shippingWeight;
}

public void setShippingWeight(BigDecimal  shippingWeight) {
this.shippingWeight = shippingWeight;
}

public BigDecimal getProductWeight() {
return productWeight;
}

public void setProductWeight(BigDecimal  productWeight) {
this.productWeight = productWeight;
}

public String getHeightUomId() {
return heightUomId;
}

public void setHeightUomId(String  heightUomId) {
this.heightUomId = heightUomId;
}

public BigDecimal getProductHeight() {
return productHeight;
}

public void setProductHeight(BigDecimal  productHeight) {
this.productHeight = productHeight;
}

public BigDecimal getShippingHeight() {
return shippingHeight;
}

public void setShippingHeight(BigDecimal  shippingHeight) {
this.shippingHeight = shippingHeight;
}

public String getWidthUomId() {
return widthUomId;
}

public void setWidthUomId(String  widthUomId) {
this.widthUomId = widthUomId;
}

public BigDecimal getProductWidth() {
return productWidth;
}

public void setProductWidth(BigDecimal  productWidth) {
this.productWidth = productWidth;
}

public BigDecimal getShippingWidth() {
return shippingWidth;
}

public void setShippingWidth(BigDecimal  shippingWidth) {
this.shippingWidth = shippingWidth;
}

public String getDepthUomId() {
return depthUomId;
}

public void setDepthUomId(String  depthUomId) {
this.depthUomId = depthUomId;
}

public BigDecimal getProductDepth() {
return productDepth;
}

public void setProductDepth(BigDecimal  productDepth) {
this.productDepth = productDepth;
}

public BigDecimal getShippingDepth() {
return shippingDepth;
}

public void setShippingDepth(BigDecimal  shippingDepth) {
this.shippingDepth = shippingDepth;
}

public String getDiameterUomId() {
return diameterUomId;
}

public void setDiameterUomId(String  diameterUomId) {
this.diameterUomId = diameterUomId;
}

public BigDecimal getProductDiameter() {
return productDiameter;
}

public void setProductDiameter(BigDecimal  productDiameter) {
this.productDiameter = productDiameter;
}

public BigDecimal getProductRating() {
return productRating;
}

public void setProductRating(BigDecimal  productRating) {
this.productRating = productRating;
}

public String getRatingTypeEnum() {
return ratingTypeEnum;
}

public void setRatingTypeEnum(String  ratingTypeEnum) {
this.ratingTypeEnum = ratingTypeEnum;
}

public Boolean getReturnable() {
return returnable;
}

public void setReturnable(Boolean  returnable) {
this.returnable = returnable;
}

public Boolean getTaxable() {
return taxable;
}

public void setTaxable(Boolean  taxable) {
this.taxable = taxable;
}

public Boolean getChargeShipping() {
return chargeShipping;
}

public void setChargeShipping(Boolean  chargeShipping) {
this.chargeShipping = chargeShipping;
}

public Boolean getAutoCreateKeywords() {
return autoCreateKeywords;
}

public void setAutoCreateKeywords(Boolean  autoCreateKeywords) {
this.autoCreateKeywords = autoCreateKeywords;
}

public Boolean getIncludeInPromotions() {
return includeInPromotions;
}

public void setIncludeInPromotions(Boolean  includeInPromotions) {
this.includeInPromotions = includeInPromotions;
}

public Boolean getIsVirtual() {
return isVirtual;
}

public void setIsVirtual(Boolean  isVirtual) {
this.isVirtual = isVirtual;
}

public Boolean getIsVariant() {
return isVariant;
}

public void setIsVariant(Boolean  isVariant) {
this.isVariant = isVariant;
}

public String getVirtualVariantMethodEnum() {
return virtualVariantMethodEnum;
}

public void setVirtualVariantMethodEnum(String  virtualVariantMethodEnum) {
this.virtualVariantMethodEnum = virtualVariantMethodEnum;
}

public String getOriginGeoId() {
return originGeoId;
}

public void setOriginGeoId(String  originGeoId) {
this.originGeoId = originGeoId;
}

public String getRequirementMethodEnumId() {
return requirementMethodEnumId;
}

public void setRequirementMethodEnumId(String  requirementMethodEnumId) {
this.requirementMethodEnumId = requirementMethodEnumId;
}

public Long getBillOfMaterialLevel() {
return billOfMaterialLevel;
}

public void setBillOfMaterialLevel(Long  billOfMaterialLevel) {
this.billOfMaterialLevel = billOfMaterialLevel;
}

public BigDecimal getReservMaxPersons() {
return reservMaxPersons;
}

public void setReservMaxPersons(BigDecimal  reservMaxPersons) {
this.reservMaxPersons = reservMaxPersons;
}

public BigDecimal getReserv2ndPPPerc() {
return reserv2ndPPPerc;
}

public void setReserv2ndPPPerc(BigDecimal  reserv2ndPPPerc) {
this.reserv2ndPPPerc = reserv2ndPPPerc;
}

public BigDecimal getReservNthPPPerc() {
return reservNthPPPerc;
}

public void setReservNthPPPerc(BigDecimal  reservNthPPPerc) {
this.reservNthPPPerc = reservNthPPPerc;
}

public String getConfigId() {
return configId;
}

public void setConfigId(String  configId) {
this.configId = configId;
}

public Timestamp getCreatedDate() {
return createdDate;
}

public void setCreatedDate(Timestamp  createdDate) {
this.createdDate = createdDate;
}

public String getCreatedByUserLogin() {
return createdByUserLogin;
}

public void setCreatedByUserLogin(String  createdByUserLogin) {
this.createdByUserLogin = createdByUserLogin;
}

public Timestamp getLastModifiedDate() {
return lastModifiedDate;
}

public void setLastModifiedDate(Timestamp  lastModifiedDate) {
this.lastModifiedDate = lastModifiedDate;
}

public String getLastModifiedByUserLogin() {
return lastModifiedByUserLogin;
}

public void setLastModifiedByUserLogin(String  lastModifiedByUserLogin) {
this.lastModifiedByUserLogin = lastModifiedByUserLogin;
}

public Boolean getInShippingBox() {
return inShippingBox;
}

public void setInShippingBox(Boolean  inShippingBox) {
this.inShippingBox = inShippingBox;
}

public String getDefaultShipmentBoxTypeId() {
return defaultShipmentBoxTypeId;
}

public void setDefaultShipmentBoxTypeId(String  defaultShipmentBoxTypeId) {
this.defaultShipmentBoxTypeId = defaultShipmentBoxTypeId;
}

public String getLotIdFilledIn() {
return lotIdFilledIn;
}

public void setLotIdFilledIn(String  lotIdFilledIn) {
this.lotIdFilledIn = lotIdFilledIn;
}

public Boolean getOrderDecimalQuantity() {
return orderDecimalQuantity;
}

public void setOrderDecimalQuantity(Boolean  orderDecimalQuantity) {
this.orderDecimalQuantity = orderDecimalQuantity;
}


public Map<String, Object> mapAttributeField() {
return ProductMapper.map(this);
}
}
