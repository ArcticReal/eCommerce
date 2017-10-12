package com.skytala.eCommerce.domain.humanres.relations.partySkill.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.event.PartySkillAdded;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.mapper.PartySkillMapper;
import com.skytala.eCommerce.domain.humanres.relations.partySkill.model.PartySkill;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartySkill extends Command {

private PartySkill elementToBeAdded;
public AddPartySkill(PartySkill elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartySkill addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartySkill", elementToBeAdded.mapAttributeField());
addedElement = PartySkillMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartySkillAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
