package com.skytala.eCommerce.domain.humanres.relations.partyResume.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.event.PartyResumeAdded;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.mapper.PartyResumeMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyResume.model.PartyResume;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyResume extends Command {

private PartyResume elementToBeAdded;
public AddPartyResume(PartyResume elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyResume addedElement = null;
boolean success = false;
try {
elementToBeAdded.setResumeId(delegator.getNextSeqId("PartyResume"));
GenericValue newValue = delegator.makeValue("PartyResume", elementToBeAdded.mapAttributeField());
addedElement = PartyResumeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyResumeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
