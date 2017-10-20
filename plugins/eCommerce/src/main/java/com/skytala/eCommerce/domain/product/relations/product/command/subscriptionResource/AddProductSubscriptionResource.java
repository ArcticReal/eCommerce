package com.skytala.eCommerce.domain.product.relations.product.command.subscriptionResource;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource.ProductSubscriptionResourceAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.subscriptionResource.ProductSubscriptionResourceMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.subscriptionResource.ProductSubscriptionResource;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductSubscriptionResource extends Command {

private ProductSubscriptionResource elementToBeAdded;
public AddProductSubscriptionResource(ProductSubscriptionResource elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ProductSubscriptionResource addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ProductSubscriptionResource", elementToBeAdded.mapAttributeField());
addedElement = ProductSubscriptionResourceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ProductSubscriptionResourceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
