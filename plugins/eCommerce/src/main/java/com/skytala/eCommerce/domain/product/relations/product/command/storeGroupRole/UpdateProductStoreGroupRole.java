package com.skytala.eCommerce.domain.product.relations.product.command.storeGroupRole;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRole.ProductStoreGroupRoleUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRole.ProductStoreGroupRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductStoreGroupRole extends Command {

private ProductStoreGroupRole elementToBeUpdated;

public UpdateProductStoreGroupRole(ProductStoreGroupRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductStoreGroupRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductStoreGroupRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductStoreGroupRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductStoreGroupRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductStoreGroupRole.class);
}
success = false;
}
Event resultingEvent = new ProductStoreGroupRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
