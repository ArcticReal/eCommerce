
package com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.event.PaymentGatewayClearCommerceFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.mapper.PaymentGatewayClearCommerceMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewayClearCommerce.model.PaymentGatewayClearCommerce;


public class FindAllPaymentGatewayClearCommerces extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayClearCommerce> returnVal = new ArrayList<PaymentGatewayClearCommerce>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayClearCommerce", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayClearCommerceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayClearCommerceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
