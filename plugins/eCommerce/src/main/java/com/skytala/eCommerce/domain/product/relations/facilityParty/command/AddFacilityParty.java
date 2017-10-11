package com.skytala.eCommerce.domain.product.relations.facilityParty.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityParty.event.FacilityPartyAdded;
import com.skytala.eCommerce.domain.product.relations.facilityParty.mapper.FacilityPartyMapper;
import com.skytala.eCommerce.domain.product.relations.facilityParty.model.FacilityParty;
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
