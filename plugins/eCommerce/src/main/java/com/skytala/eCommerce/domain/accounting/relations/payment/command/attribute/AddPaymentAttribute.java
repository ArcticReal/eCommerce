package com.skytala.eCommerce.domain.accounting.relations.payment.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.attribute.PaymentAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.attribute.PaymentAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.attribute.PaymentAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentAttribute extends Command {

private PaymentAttribute elementToBeAdded;
public AddPaymentAttribute(PaymentAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentAttribute", elementToBeAdded.mapAttributeField());
addedElement = PaymentAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
