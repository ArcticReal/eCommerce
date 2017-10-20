
package com.skytala.eCommerce.domain.accounting.relations.payment.query.gatewayCyberSource;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.gatewayCyberSource.PaymentGatewayCyberSourceFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayCyberSource.PaymentGatewayCyberSourceMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayCyberSource.PaymentGatewayCyberSource;


public class FindAllPaymentGatewayCyberSources extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGatewayCyberSource> returnVal = new ArrayList<PaymentGatewayCyberSource>();
try{
List<GenericValue> results = delegator.findAll("PaymentGatewayCyberSource", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGatewayCyberSourceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGatewayCyberSourceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
