package com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.event.PartyAcctgPreferenceAdded;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.mapper.PartyAcctgPreferenceMapper;
import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.model.PartyAcctgPreference;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyAcctgPreference extends Command {

private PartyAcctgPreference elementToBeAdded;
public AddPartyAcctgPreference(PartyAcctgPreference elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyAcctgPreference addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyAcctgPreference", elementToBeAdded.mapAttributeField());
addedElement = PartyAcctgPreferenceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyAcctgPreferenceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
