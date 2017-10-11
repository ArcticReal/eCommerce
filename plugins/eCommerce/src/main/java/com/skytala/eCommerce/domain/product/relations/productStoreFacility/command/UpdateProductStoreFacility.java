package com.skytala.eCommerce.domain.product.relations.productStoreFacility.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productStoreFacility.event.ProductStoreFacilityUpdated;
import com.skytala.eCommerce.domain.product.relations.productStoreFacility.model.ProductStoreFacility;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductStoreFacility extends Command {

private ProductStoreFacility elementToBeUpdated;

public UpdateProductStoreFacility(ProductStoreFacility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductStoreFacility getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductStoreFacility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductStoreFacility", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductStoreFacility.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductStoreFacility.class);
}
success = false;
}
Event resultingEvent = new ProductStoreFacilityUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
