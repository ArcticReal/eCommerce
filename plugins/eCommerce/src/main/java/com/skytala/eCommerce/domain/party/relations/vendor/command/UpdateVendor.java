package com.skytala.eCommerce.domain.party.relations.vendor.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.vendor.event.VendorUpdated;
import com.skytala.eCommerce.domain.party.relations.vendor.model.Vendor;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateVendor extends Command {

private Vendor elementToBeUpdated;

public UpdateVendor(Vendor elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Vendor getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Vendor elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Vendor", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Vendor.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Vendor.class);
}
success = false;
}
Event resultingEvent = new VendorUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
