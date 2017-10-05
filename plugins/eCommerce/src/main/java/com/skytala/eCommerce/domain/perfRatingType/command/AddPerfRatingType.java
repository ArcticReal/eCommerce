package com.skytala.eCommerce.domain.perfRatingType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.perfRatingType.event.PerfRatingTypeAdded;
import com.skytala.eCommerce.domain.perfRatingType.mapper.PerfRatingTypeMapper;
import com.skytala.eCommerce.domain.perfRatingType.model.PerfRatingType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPerfRatingType extends Command {

private PerfRatingType elementToBeAdded;
public AddPerfRatingType(PerfRatingType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PerfRatingType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPerfRatingTypeId(delegator.getNextSeqId("PerfRatingType"));
GenericValue newValue = delegator.makeValue("PerfRatingType", elementToBeAdded.mapAttributeField());
addedElement = PerfRatingTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PerfRatingTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
