
package com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewaySagePay;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewaySagePay.PaymentGatewaySagePayFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewaySagePay.PaymentGatewaySagePayMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewaySagePay.PaymentGatewaySagePay;


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
