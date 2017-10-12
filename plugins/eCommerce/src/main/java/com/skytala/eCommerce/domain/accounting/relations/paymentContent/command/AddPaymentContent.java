package com.skytala.eCommerce.domain.accounting.relations.paymentContent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentContent.event.PaymentContentAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentContent.mapper.PaymentContentMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentContent.model.PaymentContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentContent extends Command {

private PaymentContent elementToBeAdded;
public AddPaymentContent(PaymentContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentContent", elementToBeAdded.mapAttributeField());
addedElement = PaymentContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
