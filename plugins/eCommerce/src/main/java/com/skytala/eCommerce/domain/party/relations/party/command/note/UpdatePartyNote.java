package com.skytala.eCommerce.domain.party.relations.party.command.note;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.party.relations.party.event.note.PartyNoteUpdated;
import com.skytala.eCommerce.domain.party.relations.party.model.note.PartyNote;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePartyNote extends Command {

private PartyNote elementToBeUpdated;

public UpdatePartyNote(PartyNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PartyNote getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PartyNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PartyNote", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PartyNote.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PartyNote.class);
}
success = false;
}
Event resultingEvent = new PartyNoteUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
