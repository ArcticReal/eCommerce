
package com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayWorldPay;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayWorldPay.PaymentGatewayWorldPayFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayWorldPay.PaymentGatewayWorldPayMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayWorldPay.PaymentGatewayWorldPay;


public class FindAllPaymentGatewayWorldPays extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayWorldPay> returnVal = new ArrayList<PaymentGatewayWorldPay>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayWorldPay", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayWorldPayMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayWorldPayFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
