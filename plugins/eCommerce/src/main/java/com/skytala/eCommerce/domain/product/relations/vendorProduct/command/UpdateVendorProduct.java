package com.skytala.eCommerce.domain.product.relations.vendorProduct.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.vendorProduct.event.VendorProductUpdated;
import com.skytala.eCommerce.domain.product.relations.vendorProduct.model.VendorProduct;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateVendorProduct extends Command {

private VendorProduct elementToBeUpdated;

public UpdateVendorProduct(VendorProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public VendorProduct getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(VendorProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("VendorProduct", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(VendorProduct.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(VendorProduct.class);
}
success = false;
}
Event resultingEvent = new VendorProductUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
