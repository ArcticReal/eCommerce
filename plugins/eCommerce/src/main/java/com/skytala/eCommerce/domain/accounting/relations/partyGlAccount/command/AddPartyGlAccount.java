package com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.event.PartyGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.mapper.PartyGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.model.PartyGlAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyGlAccount extends Command {

private PartyGlAccount elementToBeAdded;
public AddPartyGlAccount(PartyGlAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyGlAccount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyGlAccount", elementToBeAdded.mapAttributeField());
addedElement = PartyGlAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyGlAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
