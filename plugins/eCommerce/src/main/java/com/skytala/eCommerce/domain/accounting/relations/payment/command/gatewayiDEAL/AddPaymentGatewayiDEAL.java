package com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayiDEAL;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayiDEAL.PaymentGatewayiDEALAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayiDEAL.PaymentGatewayiDEALMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayiDEAL.PaymentGatewayiDEAL;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayiDEAL extends Command {

private PaymentGatewayiDEAL elementToBeAdded;
public AddPaymentGatewayiDEAL(PaymentGatewayiDEAL elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayiDEAL addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGatewayiDEAL", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayiDEALMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayiDEALAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
