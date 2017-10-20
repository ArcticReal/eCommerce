package com.skytala.eCommerce.domain.product.relations.facility.command.party;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facility.event.party.FacilityPartyAdded;
import com.skytala.eCommerce.domain.product.relations.facility.mapper.party.FacilityPartyMapper;
import com.skytala.eCommerce.domain.product.relations.facility.model.party.FacilityParty;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityParty extends Command {

private FacilityParty elementToBeAdded;
public AddFacilityParty(FacilityParty elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityParty addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FacilityParty", elementToBeAdded.mapAttributeField());
addedElement = FacilityPartyMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityPartyAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
