package com.skytala.eCommerce.domain.paymentGatewayConfigType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.paymentGatewayConfigType.event.PaymentGatewayConfigTypeAdded;
import com.skytala.eCommerce.domain.paymentGatewayConfigType.mapper.PaymentGatewayConfigTypeMapper;
import com.skytala.eCommerce.domain.paymentGatewayConfigType.model.PaymentGatewayConfigType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayConfigType extends Command {

private PaymentGatewayConfigType elementToBeAdded;
public AddPaymentGatewayConfigType(PaymentGatewayConfigType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayConfigType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentGatewayConfigTypeId(delegator.getNextSeqId("PaymentGatewayConfigType"));
GenericValue newValue = delegator.makeValue("PaymentGatewayConfigType", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayConfigTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayConfigTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
