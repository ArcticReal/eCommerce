package com.skytala.eCommerce.domain.product.relations.productStoreGroupType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupType.event.ProductStoreGroupTypeUpdated;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupType.model.ProductStoreGroupType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductStoreGroupType extends Command {

private ProductStoreGroupType elementToBeUpdated;

public UpdateProductStoreGroupType(ProductStoreGroupType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductStoreGroupType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductStoreGroupType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductStoreGroupType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductStoreGroupType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductStoreGroupType.class);
}
success = false;
}
Event resultingEvent = new ProductStoreGroupTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
