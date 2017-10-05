package com.skytala.eCommerce.domain.trainingClassType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.trainingClassType.event.TrainingClassTypeAdded;
import com.skytala.eCommerce.domain.trainingClassType.mapper.TrainingClassTypeMapper;
import com.skytala.eCommerce.domain.trainingClassType.model.TrainingClassType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTrainingClassType extends Command {

private TrainingClassType elementToBeAdded;
public AddTrainingClassType(TrainingClassType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TrainingClassType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setTrainingClassTypeId(delegator.getNextSeqId("TrainingClassType"));
GenericValue newValue = delegator.makeValue("TrainingClassType", elementToBeAdded.mapAttributeField());
addedElement = TrainingClassTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TrainingClassTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
