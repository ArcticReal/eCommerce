package com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.event.WorkEffortBillingAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.mapper.WorkEffortBillingMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffortBilling.model.WorkEffortBilling;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortBilling extends Command {

private WorkEffortBilling elementToBeAdded;
public AddWorkEffortBilling(WorkEffortBilling elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortBilling addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInvoiceItemSeqId(delegator.getNextSeqId("WorkEffortBilling"));
GenericValue newValue = delegator.makeValue("WorkEffortBilling", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortBillingMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortBillingAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
