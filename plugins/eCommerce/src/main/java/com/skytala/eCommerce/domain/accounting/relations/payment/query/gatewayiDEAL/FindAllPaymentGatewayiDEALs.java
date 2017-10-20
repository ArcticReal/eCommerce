
package com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayiDEAL;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayiDEAL.PaymentGatewayiDEALFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayiDEAL.PaymentGatewayiDEALMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayiDEAL.PaymentGatewayiDEAL;


public class FindAllPaymentGatewayiDEALs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayiDEAL> returnVal = new ArrayList<PaymentGatewayiDEAL>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayiDEAL", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayiDEALMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayiDEALFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
