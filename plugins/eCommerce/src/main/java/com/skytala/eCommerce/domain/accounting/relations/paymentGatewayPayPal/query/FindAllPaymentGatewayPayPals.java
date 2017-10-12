
package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.event.PaymentGatewayPayPalFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.mapper.PaymentGatewayPayPalMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayPayPal.model.PaymentGatewayPayPal;


public class FindAllPaymentGatewayPayPals extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayPayPal> returnVal = new ArrayList<PaymentGatewayPayPal>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayPayPal", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayPayPalMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayPayPalFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
