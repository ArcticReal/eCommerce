
package com.skytala.eCommerce.domain.order.relations.returnItemBilling.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.returnItemBilling.event.ReturnItemBillingFound;
import com.skytala.eCommerce.domain.order.relations.returnItemBilling.mapper.ReturnItemBillingMapper;
import com.skytala.eCommerce.domain.order.relations.returnItemBilling.model.ReturnItemBilling;


public class FindAllReturnItemBillings extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnItemBilling> returnVal = new ArrayList<ReturnItemBilling>();
try{
List<GenericValue> results = delegator.findAll("ReturnItemBilling", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnItemBillingMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnItemBillingFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
