package com.skytala.eCommerce.domain.humanres.relations.perfRatingType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.event.PerfRatingTypeUpdated;
import com.skytala.eCommerce.domain.humanres.relations.perfRatingType.model.PerfRatingType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePerfRatingType extends Command {

private PerfRatingType elementToBeUpdated;

public UpdatePerfRatingType(PerfRatingType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PerfRatingType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PerfRatingType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PerfRatingType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PerfRatingType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PerfRatingType.class);
}
success = false;
}
Event resultingEvent = new PerfRatingTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
