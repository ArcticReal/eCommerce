package com.skytala.eCommerce.domain.order.relations.communicationEventOrder.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.event.CommunicationEventOrderUpdated;
import com.skytala.eCommerce.domain.order.relations.communicationEventOrder.model.CommunicationEventOrder;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCommunicationEventOrder extends Command {

private CommunicationEventOrder elementToBeUpdated;

public UpdateCommunicationEventOrder(CommunicationEventOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CommunicationEventOrder getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CommunicationEventOrder elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CommunicationEventOrder", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CommunicationEventOrder.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CommunicationEventOrder.class);
}
success = false;
}
Event resultingEvent = new CommunicationEventOrderUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
