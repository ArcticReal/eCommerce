package com.skytala.eCommerce.domain.product.command;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;

import com.skytala.eCommerce.domain.product.event.ProductDeleted;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class DeleteProduct extends Command{

	private String toBeDeletedId;
	
	public DeleteProduct(String toBeDeletedId) {
		this.setToBeDeletedId(toBeDeletedId);
	}
	
	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		
		boolean success = false;
		
		try {
			int countRemoved = delegator.removeByAnd("Product", UtilMisc.toMap("productId", toBeDeletedId));
			if(countRemoved > 0) {
				success = true;
			}else {
				throw new RecordNotFoundException(Product.class);

			}
		} catch (GenericEntityException e) {
			System.err.println(e.getMessage());
			if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
				throw new RecordNotFoundException(Product.class);
			}
		}
		
		Event resultingEvent = new ProductDeleted(success);
		Broker.instance().publish(resultingEvent);
		return resultingEvent;
		
	}

	public String getToBeDeletedId() {
		return toBeDeletedId;
	}

	public void setToBeDeletedId(String toBeDeletedId) {
		this.toBeDeletedId = toBeDeletedId;
	}

}
