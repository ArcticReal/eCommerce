package com.skytala.eCommerce.domain.party.relations.party.command.profileDefault;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.profileDefault.PartyProfileDefaultAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.profileDefault.PartyProfileDefaultMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.profileDefault.PartyProfileDefault;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyProfileDefault extends Command {

private PartyProfileDefault elementToBeAdded;
public AddPartyProfileDefault(PartyProfileDefault elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyProfileDefault addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyProfileDefault", elementToBeAdded.mapAttributeField());
addedElement = PartyProfileDefaultMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyProfileDefaultAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
