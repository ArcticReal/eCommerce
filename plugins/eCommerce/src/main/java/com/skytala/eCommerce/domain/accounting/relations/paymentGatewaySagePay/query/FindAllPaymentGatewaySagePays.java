
package com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.event.PaymentGatewaySagePayFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.mapper.PaymentGatewaySagePayMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentGatewaySagePay.model.PaymentGatewaySagePay;


public class FindAllPaymentGatewaySagePays extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewaySagePay> returnVal = new ArrayList<PaymentGatewaySagePay>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewaySagePay", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewaySagePayMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewaySagePayFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
