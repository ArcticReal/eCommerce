
package com.skytala.eCommerce.domain.accounting.relations.payment.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.type.PaymentTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.type.PaymentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.type.PaymentType;


public class FindAllPaymentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentType> returnVal = new ArrayList<PaymentType>();
try{
List<GenericValue> results = delegator.findAll("PaymentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
