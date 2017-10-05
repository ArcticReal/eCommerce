package com.skytala.eCommerce.domain.facility.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.facility.event.FacilityAdded;
import com.skytala.eCommerce.domain.facility.mapper.FacilityMapper;
import com.skytala.eCommerce.domain.facility.model.Facility;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacility extends Command {

private Facility elementToBeAdded;
public AddFacility(Facility elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Facility addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFacilityId(delegator.getNextSeqId("Facility"));
GenericValue newValue = delegator.makeValue("Facility", elementToBeAdded.mapAttributeField());
addedElement = FacilityMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
