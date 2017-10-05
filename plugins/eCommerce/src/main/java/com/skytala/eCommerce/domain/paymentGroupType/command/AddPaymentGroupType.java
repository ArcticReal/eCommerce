package com.skytala.eCommerce.domain.paymentGroupType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.paymentGroupType.event.PaymentGroupTypeAdded;
import com.skytala.eCommerce.domain.paymentGroupType.mapper.PaymentGroupTypeMapper;
import com.skytala.eCommerce.domain.paymentGroupType.model.PaymentGroupType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGroupType extends Command {

private PaymentGroupType elementToBeAdded;
public AddPaymentGroupType(PaymentGroupType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGroupType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentGroupTypeId(delegator.getNextSeqId("PaymentGroupType"));
GenericValue newValue = delegator.makeValue("PaymentGroupType", elementToBeAdded.mapAttributeField());
addedElement = PaymentGroupTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGroupTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
