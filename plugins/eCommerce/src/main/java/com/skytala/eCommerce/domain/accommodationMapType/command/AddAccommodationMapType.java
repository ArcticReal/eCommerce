package com.skytala.eCommerce.domain.accommodationMapType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accommodationMapType.event.AccommodationMapTypeAdded;
import com.skytala.eCommerce.domain.accommodationMapType.mapper.AccommodationMapTypeMapper;
import com.skytala.eCommerce.domain.accommodationMapType.model.AccommodationMapType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAccommodationMapType extends Command {

private AccommodationMapType elementToBeAdded;
public AddAccommodationMapType(AccommodationMapType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AccommodationMapType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAccommodationMapTypeId(delegator.getNextSeqId("AccommodationMapType"));
GenericValue newValue = delegator.makeValue("AccommodationMapType", elementToBeAdded.mapAttributeField());
addedElement = AccommodationMapTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AccommodationMapTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
