package com.skytala.eCommerce.domain.party.relations.party.command.classification;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.party.event.classification.PartyClassificationUpdated;
import com.skytala.eCommerce.domain.party.relations.party.model.classification.PartyClassification;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyClassification extends Command {

private PartyClassification elementToBeUpdated;

public UpdatePartyClassification(PartyClassification elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyClassification getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyClassification elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyClassification", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyClassification.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyClassification.class);
}
success = false;
}
Event resultingEvent = new PartyClassificationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
