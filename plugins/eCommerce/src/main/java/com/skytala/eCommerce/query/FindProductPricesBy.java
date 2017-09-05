package com.skytala.eCommerce.query;

import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.condition.EntityCondition;
import org.apache.ofbiz.entity.condition.EntityJoinOperator;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductPrice;
import com.skytala.eCommerce.entity.ProductPriceMapper;
import com.skytala.eCommerce.event.ProductPricesFound;

public class FindProductPricesBy implements Query {

	String productId;
	String productPriceTypeId;

	public FindProductPricesBy(String productId, String productPriceTypeId) {
		this.productId = productId;
		this.productPriceTypeId = productPriceTypeId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductPriceTypeId() {
		return productPriceTypeId;
	}

	public void setProductPriceTypeId(String productPriceTypeId) {
		this.productPriceTypeId = productPriceTypeId;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		List<ProductPrice> foundProductPrices = new LinkedList<>();

		try {

			EntityCondition cond = EntityCondition.makeCondition("productId", productId);
			EntityCondition cond2 = EntityCondition.makeCondition("productPriceTypeId", productPriceTypeId);

			List<GenericValue> values = delegator.findList("ProductPrice", EntityCondition.makeCondition(cond, EntityJoinOperator.AND, cond2), null, null, null, false);
			
			for (int i = 0; i < values.size(); i++) {
				foundProductPrices.add(ProductPriceMapper.map(values.get(i)));
			}

		} catch (GenericEntityException e) {
			e.printStackTrace();
		}

		Broker.instance().publish(new ProductPricesFound(foundProductPrices));

	}

}
