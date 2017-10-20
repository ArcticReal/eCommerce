package com.skytala.eCommerce.domain.party.relations.party.command.carrierAccount;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.party.event.carrierAccount.PartyCarrierAccountAdded;
import com.skytala.eCommerce.domain.party.relations.party.mapper.carrierAccount.PartyCarrierAccountMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.carrierAccount.PartyCarrierAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyCarrierAccount extends Command {

private PartyCarrierAccount elementToBeAdded;
public AddPartyCarrierAccount(PartyCarrierAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyCarrierAccount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyCarrierAccount", elementToBeAdded.mapAttributeField());
addedElement = PartyCarrierAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyCarrierAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
