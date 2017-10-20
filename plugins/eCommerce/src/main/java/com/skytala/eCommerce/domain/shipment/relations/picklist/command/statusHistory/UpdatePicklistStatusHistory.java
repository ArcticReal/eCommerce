package com.skytala.eCommerce.domain.shipment.relations.picklist.command.statusHistory;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.statusHistory.PicklistStatusHistoryUpdated;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.statusHistory.PicklistStatusHistory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePicklistStatusHistory extends Command {

private PicklistStatusHistory elementToBeUpdated;

public UpdatePicklistStatusHistory(PicklistStatusHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PicklistStatusHistory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PicklistStatusHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PicklistStatusHistory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PicklistStatusHistory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PicklistStatusHistory.class);
}
success = false;
}
Event resultingEvent = new PicklistStatusHistoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
