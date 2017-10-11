package com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.event.ProductCalculatedInfoUpdated;
import com.skytala.eCommerce.domain.product.relations.productCalculatedInfo.model.ProductCalculatedInfo;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductCalculatedInfo extends Command {

private ProductCalculatedInfo elementToBeUpdated;

public UpdateProductCalculatedInfo(ProductCalculatedInfo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductCalculatedInfo getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductCalculatedInfo elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductCalculatedInfo", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductCalculatedInfo.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductCalculatedInfo.class);
}
success = false;
}
Event resultingEvent = new ProductCalculatedInfoUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
