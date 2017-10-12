package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.event.PaymentGatewayResponseAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.mapper.PaymentGatewayResponseMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.model.PaymentGatewayResponse;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayResponse extends Command {

private PaymentGatewayResponse elementToBeAdded;
public AddPaymentGatewayResponse(PaymentGatewayResponse elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayResponse addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentGatewayResponseId(delegator.getNextSeqId("PaymentGatewayResponse"));
GenericValue newValue = delegator.makeValue("PaymentGatewayResponse", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayResponseMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayResponseAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
