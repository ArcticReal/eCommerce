
package com.skytala.eCommerce.domain.accounting.relations.payment.query.groupType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.payment.event.groupType.PaymentGroupTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.payment.mapper.groupType.PaymentGroupTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.groupType.PaymentGroupType;


public class FindAllPaymentGroupTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentGroupType> returnVal = new ArrayList<PaymentGroupType>();
try{
List<GenericValue> results = delegator.findAll("PaymentGroupType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentGroupTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentGroupTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
