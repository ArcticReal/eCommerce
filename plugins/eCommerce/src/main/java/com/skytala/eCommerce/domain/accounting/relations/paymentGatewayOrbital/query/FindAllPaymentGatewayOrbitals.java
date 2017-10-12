
package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.event.PaymentGatewayOrbitalFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.mapper.PaymentGatewayOrbitalMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayOrbital.model.PaymentGatewayOrbital;


public class FindAllPaymentGatewayOrbitals extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayOrbital> returnVal = new ArrayList<PaymentGatewayOrbital>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayOrbital", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayOrbitalMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayOrbitalFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
