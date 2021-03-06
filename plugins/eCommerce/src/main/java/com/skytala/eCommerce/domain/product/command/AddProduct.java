package com.skytala.eCommerce.domain.product.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.event.ProductAdded;
import com.skytala.eCommerce.domain.product.mapper.ProductMapper;
import com.skytala.eCommerce.domain.product.model.Product;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProduct extends Command {

	private Product elementToBeAdded;

	public AddProduct(Product elementToBeAdded) {
		this.elementToBeAdded = elementToBeAdded;
	}

	@Override
	public Event execute() {

		Delegator delegator = DelegatorFactory.getDelegator("default");

		Product addedElement = null;
		boolean success = false;
		try {
			elementToBeAdded.setProductId(delegator.getNextSeqId("Product"));
			elementToBeAdded.setAutoCreateKeywords(false);
			GenericValue newValue = delegator.makeValue("Product", elementToBeAdded.mapAttributeField());
			addedElement = ProductMapper.map(delegator.create(newValue));
			success = true;
		} catch (GenericEntityException e) {
			e.printStackTrace();
			addedElement = null;
		}

		Event resultingEvent = new ProductAdded(addedElement, success);
		Broker.instance().publish(resultingEvent);
		return resultingEvent;
	}
}
