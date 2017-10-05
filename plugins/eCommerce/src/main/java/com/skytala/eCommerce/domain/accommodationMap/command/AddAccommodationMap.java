package com.skytala.eCommerce.domain.accommodationMap.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accommodationMap.event.AccommodationMapAdded;
import com.skytala.eCommerce.domain.accommodationMap.mapper.AccommodationMapMapper;
import com.skytala.eCommerce.domain.accommodationMap.model.AccommodationMap;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAccommodationMap extends Command {

private AccommodationMap elementToBeAdded;
public AddAccommodationMap(AccommodationMap elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AccommodationMap addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAccommodationMapId(delegator.getNextSeqId("AccommodationMap"));
GenericValue newValue = delegator.makeValue("AccommodationMap", elementToBeAdded.mapAttributeField());
addedElement = AccommodationMapMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AccommodationMapAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
