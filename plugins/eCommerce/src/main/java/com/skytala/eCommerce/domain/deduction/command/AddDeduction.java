package com.skytala.eCommerce.domain.deduction.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.deduction.event.DeductionAdded;
import com.skytala.eCommerce.domain.deduction.mapper.DeductionMapper;
import com.skytala.eCommerce.domain.deduction.model.Deduction;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDeduction extends Command {

private Deduction elementToBeAdded;
public AddDeduction(Deduction elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Deduction addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDeductionId(delegator.getNextSeqId("Deduction"));
GenericValue newValue = delegator.makeValue("Deduction", elementToBeAdded.mapAttributeField());
addedElement = DeductionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DeductionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
