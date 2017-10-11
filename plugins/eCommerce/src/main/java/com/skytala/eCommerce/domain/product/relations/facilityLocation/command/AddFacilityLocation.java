package com.skytala.eCommerce.domain.product.relations.facilityLocation.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityLocation.event.FacilityLocationAdded;
import com.skytala.eCommerce.domain.product.relations.facilityLocation.mapper.FacilityLocationMapper;
import com.skytala.eCommerce.domain.product.relations.facilityLocation.model.FacilityLocation;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFacilityLocation extends Command {

private FacilityLocation elementToBeAdded;
public AddFacilityLocation(FacilityLocation elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FacilityLocation addedElement = null;
boolean success = false;
try {
elementToBeAdded.setLocationSeqId(delegator.getNextSeqId("FacilityLocation"));
GenericValue newValue = delegator.makeValue("FacilityLocation", elementToBeAdded.mapAttributeField());
addedElement = FacilityLocationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FacilityLocationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
