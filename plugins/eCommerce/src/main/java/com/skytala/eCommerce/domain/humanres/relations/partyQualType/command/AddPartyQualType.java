package com.skytala.eCommerce.domain.humanres.relations.partyQualType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.partyQualType.event.PartyQualTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.partyQualType.mapper.PartyQualTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyQualType.model.PartyQualType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyQualType extends Command {

private PartyQualType elementToBeAdded;
public AddPartyQualType(PartyQualType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyQualType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPartyQualTypeId(delegator.getNextSeqId("PartyQualType"));
GenericValue newValue = delegator.makeValue("PartyQualType", elementToBeAdded.mapAttributeField());
addedElement = PartyQualTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyQualTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
