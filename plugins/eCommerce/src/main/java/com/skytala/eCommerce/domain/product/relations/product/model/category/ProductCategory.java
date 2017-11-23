package com.skytala.eCommerce.domain.product.relations.product.model.category;

import java.util.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;

import com.skytala.eCommerce.domain.product.mapper.ProductMapper;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.category.ProductCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryMember.ProductCategoryMemberMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryMember.ProductCategoryMember;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Scheduler;
import org.apache.ofbiz.entity.GenericEntity;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;

import javax.servlet.http.HttpSession;

public class ProductCategory implements Serializable{

    private static final long serialVersionUID = 1L;
    private String productCategoryId;
    private String productCategoryTypeId;
    private String primaryParentCategoryId;
    private String categoryName;
    private String description;
    private String longDescription;
    private String categoryImageUrl;
    private String linkOneImageUrl;
    private String linkTwoImageUrl;
    private String detailScreen;
    private Boolean showInSelect;

    private Boolean equalsDatabaseRecord;
    private final Query queryToGetData;

    /*
        Constructors

     */

    /*
        Constructor for Product Category that will be completely filled with the data from the DataBase.
        Please make sure to set the fields

     */
    public ProductCategory(){
        queryToGetData = null;
        equalsDatabaseRecord = true;
    }

    /*
     Constructor for Party Prototype that only has the ID.
     You need to specify a Query that will be able to find Parties from the Database for the queryToGetData parameter.

  */
    public ProductCategory(String productCategoryId, Query queryToGetData) {
        this.productCategoryId = productCategoryId;
        this.queryToGetData = queryToGetData;
        equalsDatabaseRecord = false;
    }


    public String getProductCategoryId() {
    return productCategoryId;
    }

    public void setProductCategoryId(String  productCategoryId) {
    this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryTypeId() {
    return productCategoryTypeId;
    }

    public void setProductCategoryTypeId(String  productCategoryTypeId) {
    this.productCategoryTypeId = productCategoryTypeId;
    }

    public String getPrimaryParentCategoryId() {
    return primaryParentCategoryId;
    }

    public void setPrimaryParentCategoryId(String  primaryParentCategoryId) {
    this.primaryParentCategoryId = primaryParentCategoryId;
    }

    public String getCategoryName() {
    return categoryName;
    }

    public void setCategoryName(String  categoryName) {
    this.categoryName = categoryName;
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

    public String getCategoryImageUrl() {
    return categoryImageUrl;
    }

    public void setCategoryImageUrl(String  categoryImageUrl) {
    this.categoryImageUrl = categoryImageUrl;
    }

    public String getLinkOneImageUrl() {
    return linkOneImageUrl;
    }

    public void setLinkOneImageUrl(String  linkOneImageUrl) {
    this.linkOneImageUrl = linkOneImageUrl;
    }

    public String getLinkTwoImageUrl() {
    return linkTwoImageUrl;
    }

    public void setLinkTwoImageUrl(String  linkTwoImageUrl) {
    this.linkTwoImageUrl = linkTwoImageUrl;
    }

    public String getDetailScreen() {
    return detailScreen;
    }

    public void setDetailScreen(String  detailScreen) {
    this.detailScreen = detailScreen;
    }

    public Boolean getShowInSelect() {
    return showInSelect;
    }

    public void setShowInSelect(Boolean  showInSelect) {
    this.showInSelect = showInSelect;
    }


    private void fill(Query queryToGetData) throws Exception {
        List<ProductCategory> categorys = ((ProductCategoryFound) Scheduler.execute(queryToGetData).data()).getProductCategorys();
        ProductCategory category = new ProductCategory();

        if(categorys != null && categorys.size()==1){
            category = categorys.get(0);
        }else{
            throw new Exception("No specific party found");
        }

        this.setProductCategoryId(category.getProductCategoryId());
        this.setProductCategoryTypeId(category.getProductCategoryTypeId());
        this.setPrimaryParentCategoryId(category.getPrimaryParentCategoryId());
        this.setCategoryName(category.getCategoryName());
        this.setDescription(category.getDescription());
        this.setLongDescription(category.getLongDescription());
        this.setCategoryImageUrl(category.getCategoryImageUrl());
        this.setLinkOneImageUrl(category.getLinkOneImageUrl());

    }

    public Map<String, Object> mapAttributeField() {
    return ProductCategoryMapper.map(this);
    }



    public List<ProductCategoryMember> getProductCategoryMembers(HttpSession session) throws GenericServiceException {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("categoryId", this.getProductCategoryId());
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");

        result = dispatcher.runSync("getProductCategoryMembers", paramMap);

		if(result.get("responseMessage").equals("error"))
			throw new IllegalArgumentException("Ofbiz was not able to process the data");

        Collection<GenericEntity> genericEntities = (Collection<GenericEntity>) result.get("categoryMembers");
        List<ProductCategoryMember> productCategoryMembers = new LinkedList<>();

        for (GenericEntity e : genericEntities){
            productCategoryMembers.add(ProductCategoryMemberMapper.map(e));
        }




        return productCategoryMembers;
    }
}
