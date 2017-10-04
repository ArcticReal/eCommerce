package com.skytala.eCommerce.domain.prodCatalog.model;
import java.util.Map;
import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.prodCatalog.mapper.ProdCatalogMapper;

public class ProdCatalog implements Serializable{

private static final long serialVersionUID = 1L;
private String prodCatalogId;
private String catalogName;
private Boolean useQuickAdd;
private String styleSheet;
private String headerLogo;
private String contentPathPrefix;
private String templatePathPrefix;
private Boolean viewAllowPermReqd;
private Boolean purchaseAllowPermReqd;

public String getProdCatalogId() {
return prodCatalogId;
}

public void setProdCatalogId(String  prodCatalogId) {
this.prodCatalogId = prodCatalogId;
}

public String getCatalogName() {
return catalogName;
}

public void setCatalogName(String  catalogName) {
this.catalogName = catalogName;
}

public Boolean getUseQuickAdd() {
return useQuickAdd;
}

public void setUseQuickAdd(Boolean  useQuickAdd) {
this.useQuickAdd = useQuickAdd;
}

public String getStyleSheet() {
return styleSheet;
}

public void setStyleSheet(String  styleSheet) {
this.styleSheet = styleSheet;
}

public String getHeaderLogo() {
return headerLogo;
}

public void setHeaderLogo(String  headerLogo) {
this.headerLogo = headerLogo;
}

public String getContentPathPrefix() {
return contentPathPrefix;
}

public void setContentPathPrefix(String  contentPathPrefix) {
this.contentPathPrefix = contentPathPrefix;
}

public String getTemplatePathPrefix() {
return templatePathPrefix;
}

public void setTemplatePathPrefix(String  templatePathPrefix) {
this.templatePathPrefix = templatePathPrefix;
}

public Boolean getViewAllowPermReqd() {
return viewAllowPermReqd;
}

public void setViewAllowPermReqd(Boolean  viewAllowPermReqd) {
this.viewAllowPermReqd = viewAllowPermReqd;
}

public Boolean getPurchaseAllowPermReqd() {
return purchaseAllowPermReqd;
}

public void setPurchaseAllowPermReqd(Boolean  purchaseAllowPermReqd) {
this.purchaseAllowPermReqd = purchaseAllowPermReqd;
}


public Map<String, Object> mapAttributeField() {
return ProdCatalogMapper.map(this);
}
}
