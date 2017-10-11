package com.skytala.eCommerce.domain.party.relations.partyNote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.partyNote.event.PartyNoteAdded;
import com.skytala.eCommerce.domain.party.relations.partyNote.mapper.PartyNoteMapper;
import com.skytala.eCommerce.domain.party.relations.partyNote.model.PartyNote;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyNote extends Command {

private PartyNote elementToBeAdded;
public AddPartyNote(PartyNote elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyNote addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyNote", elementToBeAdded.mapAttributeField());
addedElement = PartyNoteMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyNoteAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
