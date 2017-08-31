package com.skytala.eCommerce.command;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;

import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.control.Command;
import com.skytala.eCommerce.event.ProductDeleted;

public class DeleteProduct implements Command{

	private String toBeDeletedId;
	
	public DeleteProduct(String toBeDeletedId) {
		this.setToBeDeletedId(toBeDeletedId);
	}
	
	@Override
	public void execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");
		
		boolean success = false;
		
		try {
			int countRemoved = delegator.removeByAnd("Product", UtilMisc.toMap("productId", toBeDeletedId));
			if(countRemoved > 0) {
				success = true;
			}
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		
		Broker.instance().publish(new ProductDeleted(success));
		
	}

	public String getToBeDeletedId() {
		return toBeDeletedId;
	}

	public void setToBeDeletedId(String toBeDeletedId) {
		this.toBeDeletedId = toBeDeletedId;
	}

}
