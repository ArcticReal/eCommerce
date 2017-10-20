
package com.skytala.eCommerce.domain.accounting.relations.payment.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.attribute.PaymentAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.attribute.PaymentAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.attribute.PaymentAttribute;


public class FindAllPaymentAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentAttribute> returnVal = new ArrayList<PaymentAttribute>();
try{
List<GenericValue> results = delegator.findAll("PaymentAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
