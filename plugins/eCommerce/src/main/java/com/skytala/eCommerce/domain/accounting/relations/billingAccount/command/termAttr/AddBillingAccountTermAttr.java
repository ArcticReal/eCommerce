package com.skytala.eCommerce.domain.accounting.relations.billingAccount.command.termAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.termAttr.BillingAccountTermAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.termAttr.BillingAccountTermAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.termAttr.BillingAccountTermAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBillingAccountTermAttr extends Command {

private BillingAccountTermAttr elementToBeAdded;
public AddBillingAccountTermAttr(BillingAccountTermAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BillingAccountTermAttr addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("BillingAccountTermAttr"));
GenericValue newValue = delegator.makeValue("BillingAccountTermAttr", elementToBeAdded.mapAttributeField());
addedElement = BillingAccountTermAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BillingAccountTermAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
