package com.skytala.eCommerce.domain.party.relations.party.command.identificationType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.identificationType.PartyIdentificationTypeAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.identificationType.PartyIdentificationTypeMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.identificationType.PartyIdentificationType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyIdentificationType extends Command {

private PartyIdentificationType elementToBeAdded;
public AddPartyIdentificationType(PartyIdentificationType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyIdentificationType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPartyIdentificationTypeId(delegator.getNextSeqId("PartyIdentificationType"));
GenericValue newValue = delegator.makeValue("PartyIdentificationType", elementToBeAdded.mapAttributeField());
addedElement = PartyIdentificationTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyIdentificationTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
