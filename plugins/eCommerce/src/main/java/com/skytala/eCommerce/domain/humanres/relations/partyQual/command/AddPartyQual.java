package com.skytala.eCommerce.domain.humanres.relations.partyQual.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.event.PartyQualAdded;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.mapper.PartyQualMapper;
import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.PartyQual;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyQual extends Command {

private PartyQual elementToBeAdded;
public AddPartyQual(PartyQual elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyQual addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyQual", elementToBeAdded.mapAttributeField());
addedElement = PartyQualMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyQualAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
