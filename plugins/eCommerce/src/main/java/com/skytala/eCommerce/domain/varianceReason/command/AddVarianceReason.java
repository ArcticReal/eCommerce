package com.skytala.eCommerce.domain.varianceReason.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.varianceReason.event.VarianceReasonAdded;
import com.skytala.eCommerce.domain.varianceReason.mapper.VarianceReasonMapper;
import com.skytala.eCommerce.domain.varianceReason.model.VarianceReason;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddVarianceReason extends Command {

private VarianceReason elementToBeAdded;
public AddVarianceReason(VarianceReason elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

VarianceReason addedElement = null;
boolean success = false;
try {
elementToBeAdded.setVarianceReasonId(delegator.getNextSeqId("VarianceReason"));
GenericValue newValue = delegator.makeValue("VarianceReason", elementToBeAdded.mapAttributeField());
addedElement = VarianceReasonMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new VarianceReasonAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
