package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayRespMsg.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayRespMsg.event.PaymentGatewayRespMsgUpdated;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayRespMsg.model.PaymentGatewayRespMsg;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGatewayRespMsg extends Command {

private PaymentGatewayRespMsg elementToBeUpdated;

public UpdatePaymentGatewayRespMsg(PaymentGatewayRespMsg elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGatewayRespMsg getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGatewayRespMsg elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGatewayRespMsg", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGatewayRespMsg.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGatewayRespMsg.class);
}
success = false;
}
Event resultingEvent = new PaymentGatewayRespMsgUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
