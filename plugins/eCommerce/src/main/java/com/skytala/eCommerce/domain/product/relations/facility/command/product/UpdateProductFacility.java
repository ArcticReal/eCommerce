package com.skytala.eCommerce.domain.product.relations.facility.command.product;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.facility.event.product.ProductFacilityUpdated;
import com.skytala.eCommerce.domain.product.relations.facility.model.product.ProductFacility;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFacility extends Command {

private ProductFacility elementToBeUpdated;

public UpdateProductFacility(ProductFacility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFacility getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFacility elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFacility", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFacility.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFacility.class);
}
success = false;
}
Event resultingEvent = new ProductFacilityUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
