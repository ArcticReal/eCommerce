package com.skytala.eCommerce.domain.party.relations.party.command.contentType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.party.event.contentType.PartyContentTypeUpdated;
import com.skytala.eCommerce.domain.party.relations.party.model.contentType.PartyContentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyContentType extends Command {

private PartyContentType elementToBeUpdated;

public UpdatePartyContentType(PartyContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyContentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyContentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyContentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyContentType.class);
}
success = false;
}
Event resultingEvent = new PartyContentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
