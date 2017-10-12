package com.skytala.eCommerce.domain.accounting.relations.deductionType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.deductionType.event.DeductionTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.deductionType.mapper.DeductionTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.deductionType.model.DeductionType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDeductionType extends Command {

private DeductionType elementToBeAdded;
public AddDeductionType(DeductionType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DeductionType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDeductionTypeId(delegator.getNextSeqId("DeductionType"));
GenericValue newValue = delegator.makeValue("DeductionType", elementToBeAdded.mapAttributeField());
addedElement = DeductionTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DeductionTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
