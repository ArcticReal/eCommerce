package com.skytala.eCommerce.domain.accounting.relations.rateAmount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.event.RateAmountAdded;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.mapper.RateAmountMapper;
import com.skytala.eCommerce.domain.accounting.relations.rateAmount.model.RateAmount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRateAmount extends Command {

private RateAmount elementToBeAdded;
public AddRateAmount(RateAmount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

RateAmount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("RateAmount", elementToBeAdded.mapAttributeField());
addedElement = RateAmountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RateAmountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
