
package com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayAuthorizeNet;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayAuthorizeNet.PaymentGatewayAuthorizeNetFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayAuthorizeNet.PaymentGatewayAuthorizeNetMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayAuthorizeNet.PaymentGatewayAuthorizeNet;


public class FindAllPaymentGatewayAuthorizeNets extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayAuthorizeNet> returnVal = new ArrayList<PaymentGatewayAuthorizeNet>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayAuthorizeNet", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayAuthorizeNetMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayAuthorizeNetFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
