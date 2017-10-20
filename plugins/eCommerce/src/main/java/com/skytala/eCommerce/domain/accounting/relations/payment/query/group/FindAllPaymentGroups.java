
package com.skytala.eCommerce.domain.accounting.relations.payment.query.group;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.group.PaymentGroupFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.group.PaymentGroupMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.group.PaymentGroup;


public class FindAllPaymentGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGroup> returnVal = new ArrayList<PaymentGroup>();
try{
List<GenericValue> results = delegator.findAll("PaymentGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
