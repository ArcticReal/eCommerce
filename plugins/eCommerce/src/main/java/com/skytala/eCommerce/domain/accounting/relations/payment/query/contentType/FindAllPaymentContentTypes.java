
package com.skytala.eCommerce.domain.accounting.relations.payment.query.contentType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.contentType.PaymentContentTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.contentType.PaymentContentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.contentType.PaymentContentType;


public class FindAllPaymentContentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentContentType> returnVal = new ArrayList<PaymentContentType>();
try{
List<GenericValue> results = delegator.findAll("PaymentContentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentContentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentContentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
