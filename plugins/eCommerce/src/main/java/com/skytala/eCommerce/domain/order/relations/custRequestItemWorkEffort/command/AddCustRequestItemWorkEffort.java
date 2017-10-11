package com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.event.CustRequestItemWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.mapper.CustRequestItemWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestItemWorkEffort.model.CustRequestItemWorkEffort;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestItemWorkEffort extends Command {

private CustRequestItemWorkEffort elementToBeAdded;
public AddCustRequestItemWorkEffort(CustRequestItemWorkEffort elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestItemWorkEffort addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCustRequestItemSeqId(delegator.getNextSeqId("CustRequestItemWorkEffort"));
GenericValue newValue = delegator.makeValue("CustRequestItemWorkEffort", elementToBeAdded.mapAttributeField());
addedElement = CustRequestItemWorkEffortMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestItemWorkEffortAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
