package com.skytala.eCommerce.query;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.condition.EntityCondition;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.ProductPrice;
import com.skytala.eCommerce.entity.ProductPriceMapper;
import com.skytala.eCommerce.event.ProductPricesFound;

public class FindProductPricesById implements Query {

	private String productId;

	public FindProductPricesById(String productId) {
		this.setProductId(productId);
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		List<ProductPrice> foundProductPrices = new LinkedList<>();

		try {

			EntityCondition cond = EntityCondition.makeCondition("productId", productId);

			List<String> orderBy = new LinkedList<>();
			orderBy.add("fromDate DESC");

			List<GenericValue> values = delegator.findList("ProductPrice", cond, null, orderBy, null, false);

			Timestamp currentDate = new Timestamp(System.currentTimeMillis());
			currentDate.setNanos(0);

			for (int i = 0; i < values.size(); i++) {
				ProductPrice price = ProductPriceMapper.map(values.get(i));

				if (price.getThruDate() == null || price.getThruDate().after(currentDate)) {

					if (price.getFromDate().before(currentDate)) {
						foundProductPrices.add(price);
					}
				}

			}

		} catch (GenericEntityException e) {
			e.printStackTrace();
		}

		Broker.instance().publish(new ProductPricesFound(foundProductPrices));
	}

}
