package com.skytala.eCommerce.domain.accounting.relations.accommodationClass.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.event.AccommodationClassAdded;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.mapper.AccommodationClassMapper;
import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.model.AccommodationClass;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAccommodationClass extends Command {

private AccommodationClass elementToBeAdded;
public AddAccommodationClass(AccommodationClass elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AccommodationClass addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAccommodationClassId(delegator.getNextSeqId("AccommodationClass"));
GenericValue newValue = delegator.makeValue("AccommodationClass", elementToBeAdded.mapAttributeField());
addedElement = AccommodationClassMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AccommodationClassAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
