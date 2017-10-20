package com.skytala.eCommerce.domain.accounting.relations.payment.command.groupMember;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupMember.PaymentGroupMemberUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupMember.PaymentGroupMember;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGroupMember extends Command {

private PaymentGroupMember elementToBeUpdated;

public UpdatePaymentGroupMember(PaymentGroupMember elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGroupMember getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGroupMember elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGroupMember", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGroupMember.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGroupMember.class);
}
success = false;
}
Event resultingEvent = new PaymentGroupMemberUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
