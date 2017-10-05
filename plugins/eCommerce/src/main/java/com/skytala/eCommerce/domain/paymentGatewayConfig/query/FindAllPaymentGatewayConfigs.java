
package com.skytala.eCommerce.domain.paymentGatewayConfig.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.paymentGatewayConfig.event.PaymentGatewayConfigFound;
import com.skytala.eCommerce.domain.paymentGatewayConfig.mapper.PaymentGatewayConfigMapper;
import com.skytala.eCommerce.domain.paymentGatewayConfig.model.PaymentGatewayConfig;


public class FindAllPaymentGatewayConfigs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayConfig> returnVal = new ArrayList<PaymentGatewayConfig>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayConfig", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayConfigMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayConfigFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
