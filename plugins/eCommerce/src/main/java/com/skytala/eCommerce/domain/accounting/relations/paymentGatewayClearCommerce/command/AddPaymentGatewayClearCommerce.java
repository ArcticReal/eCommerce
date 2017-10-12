package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.event.PaymentGatewayClearCommerceAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.mapper.PaymentGatewayClearCommerceMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.model.PaymentGatewayClearCommerce;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayClearCommerce extends Command {

private PaymentGatewayClearCommerce elementToBeAdded;
public AddPaymentGatewayClearCommerce(PaymentGatewayClearCommerce elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayClearCommerce addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGatewayClearCommerce", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayClearCommerceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayClearCommerceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
