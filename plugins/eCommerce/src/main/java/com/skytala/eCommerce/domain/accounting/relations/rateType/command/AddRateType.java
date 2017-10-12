package com.skytala.eCommerce.domain.accounting.relations.rateType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.rateType.event.RateTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.rateType.mapper.RateTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.rateType.model.RateType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRateType extends Command {

private RateType elementToBeAdded;
public AddRateType(RateType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

RateType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRateTypeId(delegator.getNextSeqId("RateType"));
GenericValue newValue = delegator.makeValue("RateType", elementToBeAdded.mapAttributeField());
addedElement = RateTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RateTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
