package com.skytala.eCommerce.domain.party.relations.communicationEventProduct.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.communicationEventProduct.event.CommunicationEventProductUpdated;
import com.skytala.eCommerce.domain.party.relations.communicationEventProduct.model.CommunicationEventProduct;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCommunicationEventProduct extends Command {

private CommunicationEventProduct elementToBeUpdated;

public UpdateCommunicationEventProduct(CommunicationEventProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CommunicationEventProduct getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CommunicationEventProduct elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CommunicationEventProduct", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CommunicationEventProduct.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CommunicationEventProduct.class);
}
success = false;
}
Event resultingEvent = new CommunicationEventProductUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}