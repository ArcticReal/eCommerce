package com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.event.PaymentMethodTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.mapper.PaymentMethodTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.model.PaymentMethodType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentMethodType extends Command {

private PaymentMethodType elementToBeAdded;
public AddPaymentMethodType(PaymentMethodType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentMethodType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentMethodTypeId(delegator.getNextSeqId("PaymentMethodType"));
GenericValue newValue = delegator.makeValue("PaymentMethodType", elementToBeAdded.mapAttributeField());
addedElement = PaymentMethodTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentMethodTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
