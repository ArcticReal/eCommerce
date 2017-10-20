package com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayPayflowPro;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayflowPro.PaymentGatewayPayflowProAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayPayflowPro.PaymentGatewayPayflowProMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayflowPro.PaymentGatewayPayflowPro;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayPayflowPro extends Command {

private PaymentGatewayPayflowPro elementToBeAdded;
public AddPaymentGatewayPayflowPro(PaymentGatewayPayflowPro elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayPayflowPro addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGatewayPayflowPro", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayPayflowProMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayPayflowProAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
