package com.skytala.eCommerce.domain.product.relations.productStoreGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productStoreGroup.event.ProductStoreGroupUpdated;
import com.skytala.eCommerce.domain.product.relations.productStoreGroup.model.ProductStoreGroup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductStoreGroup extends Command {

private ProductStoreGroup elementToBeUpdated;

public UpdateProductStoreGroup(ProductStoreGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductStoreGroup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductStoreGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductStoreGroup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductStoreGroup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductStoreGroup.class);
}
success = false;
}
Event resultingEvent = new ProductStoreGroupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
