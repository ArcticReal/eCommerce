
package com.skytala.eCommerce.domain.accounting.relations.paymentContent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.paymentContent.event.PaymentContentFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentContent.mapper.PaymentContentMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentContent.model.PaymentContent;


public class FindAllPaymentContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentContent> returnVal = new ArrayList<PaymentContent>();
try{
List<GenericValue> results = delegator.findAll("PaymentContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
