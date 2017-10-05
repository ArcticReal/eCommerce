
package com.skytala.eCommerce.domain.paymentGatewayConfigType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.paymentGatewayConfigType.event.PaymentGatewayConfigTypeFound;
import com.skytala.eCommerce.domain.paymentGatewayConfigType.mapper.PaymentGatewayConfigTypeMapper;
import com.skytala.eCommerce.domain.paymentGatewayConfigType.model.PaymentGatewayConfigType;


public class FindAllPaymentGatewayConfigTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayConfigType> returnVal = new ArrayList<PaymentGatewayConfigType>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayConfigType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayConfigTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayConfigTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
