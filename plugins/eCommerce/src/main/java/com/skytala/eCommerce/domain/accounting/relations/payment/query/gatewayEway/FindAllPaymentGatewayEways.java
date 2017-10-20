
package com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayEway;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayEway.PaymentGatewayEwayFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayEway.PaymentGatewayEwayMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayEway.PaymentGatewayEway;


public class FindAllPaymentGatewayEways extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayEway> returnVal = new ArrayList<PaymentGatewayEway>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayEway", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayEwayMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayEwayFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
