
package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.event.PaymentGatewaySecurePayFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.mapper.PaymentGatewaySecurePayMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySecurePay.model.PaymentGatewaySecurePay;


public class FindAllPaymentGatewaySecurePays extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewaySecurePay> returnVal = new ArrayList<PaymentGatewaySecurePay>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewaySecurePay", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewaySecurePayMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewaySecurePayFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
