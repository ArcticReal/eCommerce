package com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayCyberSource;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayCyberSource.PaymentGatewayCyberSourceUpdated;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayCyberSource.PaymentGatewayCyberSource;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePaymentGatewayCyberSource extends Command {

private PaymentGatewayCyberSource elementToBeUpdated;

public UpdatePaymentGatewayCyberSource(PaymentGatewayCyberSource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PaymentGatewayCyberSource getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PaymentGatewayCyberSource elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PaymentGatewayCyberSource", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PaymentGatewayCyberSource.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGatewayCyberSource.class);
}
success = false;
}
Event resultingEvent = new PaymentGatewayCyberSourceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
