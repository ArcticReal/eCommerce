package com.skytala.eCommerce.domain.humanres.relations.partyQual.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type.PartyQualTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.type.PartyQualType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyQualType extends Command {

private PartyQualType elementToBeUpdated;

public UpdatePartyQualType(PartyQualType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyQualType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyQualType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyQualType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyQualType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyQualType.class);
}
success = false;
}
Event resultingEvent = new PartyQualTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
