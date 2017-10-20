
package com.skytala.eCommerce.domain.accounting.relations.payment.query.application;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.application.PaymentApplicationFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.application.PaymentApplicationMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.application.PaymentApplication;


public class FindAllPaymentApplications extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentApplication> returnVal = new ArrayList<PaymentApplication>();
try{
List<GenericValue> results = delegator.findAll("PaymentApplication", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentApplicationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentApplicationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
