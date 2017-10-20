
package com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayPayflowPro;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayPayflowPro.PaymentGatewayPayflowProFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayPayflowPro.PaymentGatewayPayflowProMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayPayflowPro.PaymentGatewayPayflowPro;


public class FindAllPaymentGatewayPayflowPros extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayPayflowPro> returnVal = new ArrayList<PaymentGatewayPayflowPro>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayPayflowPro", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayPayflowProMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayPayflowProFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
