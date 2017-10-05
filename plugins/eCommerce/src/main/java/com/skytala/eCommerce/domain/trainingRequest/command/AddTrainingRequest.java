package com.skytala.eCommerce.domain.trainingRequest.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.trainingRequest.event.TrainingRequestAdded;
import com.skytala.eCommerce.domain.trainingRequest.mapper.TrainingRequestMapper;
import com.skytala.eCommerce.domain.trainingRequest.model.TrainingRequest;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTrainingRequest extends Command {

private TrainingRequest elementToBeAdded;
public AddTrainingRequest(TrainingRequest elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TrainingRequest addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTrainingRequestId(delegator.getNextSeqId("TrainingRequest"));
GenericValue newValue = delegator.makeValue("TrainingRequest", elementToBeAdded.mapAttributeField());
addedElement = TrainingRequestMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TrainingRequestAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
