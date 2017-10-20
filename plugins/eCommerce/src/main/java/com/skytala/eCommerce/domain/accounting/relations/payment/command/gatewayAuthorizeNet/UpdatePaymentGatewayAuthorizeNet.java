package com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayAuthorizeNet;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayAuthorizeNet.PaymentGatewayAuthorizeNetUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayAuthorizeNet.PaymentGatewayAuthorizeNet;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGatewayAuthorizeNet extends Command {

private PaymentGatewayAuthorizeNet elementToBeUpdated;

public UpdatePaymentGatewayAuthorizeNet(PaymentGatewayAuthorizeNet elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGatewayAuthorizeNet getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGatewayAuthorizeNet elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGatewayAuthorizeNet", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGatewayAuthorizeNet.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGatewayAuthorizeNet.class);
}
success = false;
}
Event resultingEvent = new PaymentGatewayAuthorizeNetUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
