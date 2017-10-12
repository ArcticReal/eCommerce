package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayEway.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayEway.event.PaymentGatewayEwayAdded;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayEway.mapper.PaymentGatewayEwayMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayEway.model.PaymentGatewayEway;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayEway extends Command {

private PaymentGatewayEway elementToBeAdded;
public AddPaymentGatewayEway(PaymentGatewayEway elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayEway addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PaymentGatewayEway", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayEwayMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayEwayAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
