
package com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.event.PaymentBudgetAllocationFound;
import com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.mapper.PaymentBudgetAllocationMapper;
import com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.model.PaymentBudgetAllocation;


public class FindAllPaymentBudgetAllocations extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PaymentBudgetAllocation> returnVal = new ArrayList<PaymentBudgetAllocation>();
try{
List<GenericValue> results = delegator.findAll("PaymentBudgetAllocation", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PaymentBudgetAllocationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PaymentBudgetAllocationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
