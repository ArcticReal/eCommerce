package com.skytala.eCommerce.domain.picklistBin.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.picklistBin.event.PicklistBinUpdated;
import com.skytala.eCommerce.domain.picklistBin.model.PicklistBin;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePicklistBin extends Command {

private PicklistBin elementToBeUpdated;

public UpdatePicklistBin(PicklistBin elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PicklistBin getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PicklistBin elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PicklistBin", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PicklistBin.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PicklistBin.class);
}
success = false;
}
Event resultingEvent = new PicklistBinUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
