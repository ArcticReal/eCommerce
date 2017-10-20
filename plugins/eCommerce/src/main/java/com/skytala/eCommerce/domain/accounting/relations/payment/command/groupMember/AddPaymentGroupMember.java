package com.skytala.eCommerce.domain.accounting.relations.payment.command.groupMember;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupMember.PaymentGroupMemberAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.groupMember.PaymentGroupMemberMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupMember.PaymentGroupMember;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGroupMember extends Command {

private PaymentGroupMember elementToBeAdded;
public AddPaymentGroupMember(PaymentGroupMember elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGroupMember addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGroupMember", elementToBeAdded.mapAttributeField());
addedElement = PaymentGroupMemberMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGroupMemberAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
