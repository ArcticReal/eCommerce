
package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.event.PaymentGatewayResponseFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.mapper.PaymentGatewayResponseMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayResponse.model.PaymentGatewayResponse;


public class FindAllPaymentGatewayResponses extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayResponse> returnVal = new ArrayList<PaymentGatewayResponse>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayResponse", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayResponseMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayResponseFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
