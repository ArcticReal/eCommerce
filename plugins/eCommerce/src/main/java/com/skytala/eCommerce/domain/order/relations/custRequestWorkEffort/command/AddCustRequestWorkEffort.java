package com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.event.CustRequestWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.mapper.CustRequestWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestWorkEffort.model.CustRequestWorkEffort;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestWorkEffort extends Command {

private CustRequestWorkEffort elementToBeAdded;
public AddCustRequestWorkEffort(CustRequestWorkEffort elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestWorkEffort addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CustRequestWorkEffort", elementToBeAdded.mapAttributeField());
addedElement = CustRequestWorkEffortMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestWorkEffortAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
