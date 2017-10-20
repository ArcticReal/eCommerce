package com.skytala.eCommerce.domain.accounting.relations.payment.command.gatewayRespMsg;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayRespMsg.PaymentGatewayRespMsgAdded;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayRespMsg.PaymentGatewayRespMsgMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayRespMsg.PaymentGatewayRespMsg;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPaymentGatewayRespMsg extends Command {

private PaymentGatewayRespMsg elementToBeAdded;
public AddPaymentGatewayRespMsg(PaymentGatewayRespMsg elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PaymentGatewayRespMsg addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPaymentGatewayRespMsgId(delegator.getNextSeqId("PaymentGatewayRespMsg"));
GenericValue newValue = delegator.makeValue("PaymentGatewayRespMsg", elementToBeAdded.mapAttributeField());
addedElement = PaymentGatewayRespMsgMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PaymentGatewayRespMsgAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}