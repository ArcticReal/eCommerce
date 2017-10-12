package com.skytala.eCommerce.domain.humanres.relations.salaryStep.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.event.SalaryStepAdded;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.mapper.SalaryStepMapper;
import com.skytala.eCommerce.domain.humanres.relations.salaryStep.model.SalaryStep;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSalaryStep extends Command {

private SalaryStep elementToBeAdded;
public AddSalaryStep(SalaryStep elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SalaryStep addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSalaryStepSeqId(delegator.getNextSeqId("SalaryStep"));
GenericValue newValue = delegator.makeValue("SalaryStep", elementToBeAdded.mapAttributeField());
addedElement = SalaryStepMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SalaryStepAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
