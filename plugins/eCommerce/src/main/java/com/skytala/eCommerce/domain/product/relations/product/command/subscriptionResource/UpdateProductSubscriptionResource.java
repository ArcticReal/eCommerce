package com.skytala.eCommerce.domain.product.relations.product.command.subscriptionResource;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.subscriptionResource.ProductSubscriptionResourceUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.subscriptionResource.ProductSubscriptionResource;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductSubscriptionResource extends Command {

private ProductSubscriptionResource elementToBeUpdated;

public UpdateProductSubscriptionResource(ProductSubscriptionResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductSubscriptionResource getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductSubscriptionResource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductSubscriptionResource", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductSubscriptionResource.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductSubscriptionResource.class);
}
success = false;
}
Event resultingEvent = new ProductSubscriptionResourceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
