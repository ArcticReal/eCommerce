package com.skytala.eCommerce.domain.product.relations.productFacilityLocation.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.event.ProductFacilityLocationUpdated;
import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.model.ProductFacilityLocation;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProductFacilityLocation extends Command {

private ProductFacilityLocation elementToBeUpdated;

public UpdateProductFacilityLocation(ProductFacilityLocation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProductFacilityLocation getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProductFacilityLocation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProductFacilityLocation", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProductFacilityLocation.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProductFacilityLocation.class);
}
success = false;
}
Event resultingEvent = new ProductFacilityLocationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
