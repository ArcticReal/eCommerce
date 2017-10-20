package com.skytala.eCommerce.domain.product.relations.product.command.storeGroupMember;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupMember.ProductStoreGroupMemberUpdated;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupMember.ProductStoreGroupMember;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductStoreGroupMember extends Command {

private ProductStoreGroupMember elementToBeUpdated;

public UpdateProductStoreGroupMember(ProductStoreGroupMember elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductStoreGroupMember getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductStoreGroupMember elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductStoreGroupMember", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductStoreGroupMember.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductStoreGroupMember.class);
}
success = false;
}
Event resultingEvent = new ProductStoreGroupMemberUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
