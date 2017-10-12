package com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.event.AccommodationSpotAdded;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.mapper.AccommodationSpotMapper;
import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.model.AccommodationSpot;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAccommodationSpot extends Command {

private AccommodationSpot elementToBeAdded;
public AddAccommodationSpot(AccommodationSpot elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AccommodationSpot addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAccommodationSpotId(delegator.getNextSeqId("AccommodationSpot"));
GenericValue newValue = delegator.makeValue("AccommodationSpot", elementToBeAdded.mapAttributeField());
addedElement = AccommodationSpotMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AccommodationSpotAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
