package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.command;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.event.PaymentGatewayOrbitalDeleted;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.model.PaymentGatewayOrbital;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;

public class DeletePaymentGatewayOrbital extends Command {

private String toBeDeletedId;
public DeletePaymentGatewayOrbital(String toBeDeletedId){
this.toBeDeletedId = toBeDeletedId;
}

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success = false;

try{
int countRemoved = delegator.removeByAnd("PaymentGatewayOrbital", UtilMisc.toMap("paymentGatewayOrbitalId", toBeDeletedId));
if(countRemoved > 0) {
success = true;
}
else{
throw new RecordNotFoundException(PaymentGatewayOrbital.class);
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PaymentGatewayOrbital.class);
}
}
Event resultingEvent = new PaymentGatewayOrbitalDeleted(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;

}
public String getToBeDeletedId() {
return toBeDeletedId;
}
public void setToBeDeletedId(String toBeDeletedId) {
this.toBeDeletedId = toBeDeletedId;
}
}
