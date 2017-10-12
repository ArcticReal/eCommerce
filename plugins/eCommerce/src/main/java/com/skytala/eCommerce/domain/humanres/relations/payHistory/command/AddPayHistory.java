package com.skytala.eCommerce.domain.humanres.relations.payHistory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.event.PayHistoryAdded;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.mapper.PayHistoryMapper;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.model.PayHistory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPayHistory extends Command {

private PayHistory elementToBeAdded;
public AddPayHistory(PayHistory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PayHistory addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PayHistory", elementToBeAdded.mapAttributeField());
addedElement = PayHistoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PayHistoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
