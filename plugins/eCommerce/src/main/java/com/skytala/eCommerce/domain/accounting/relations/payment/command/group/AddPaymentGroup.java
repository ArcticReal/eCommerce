package com.skytala.eCommerce.domain.accounting.relations.payment.command.group;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.group.PaymentGroupAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.group.PaymentGroupMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.group.PaymentGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGroup extends Command {

private PaymentGroup elementToBeAdded;
public AddPaymentGroup(PaymentGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGroup addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentGroupId(delegator.getNextSeqId("PaymentGroup"));
GenericValue newValue = delegator.makeValue("PaymentGroup", elementToBeAdded.mapAttributeField());
addedElement = PaymentGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
