package com.skytala.eCommerce.domain.party.relations.party.command.identification;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.identification.PartyIdentificationAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.identification.PartyIdentificationMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.identification.PartyIdentification;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyIdentification extends Command {

private PartyIdentification elementToBeAdded;
public AddPartyIdentification(PartyIdentification elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyIdentification addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyIdentification", elementToBeAdded.mapAttributeField());
addedElement = PartyIdentificationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyIdentificationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}