
package com.skytala.eCommerce.domain.order.relations.returnAdjustment.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.event.ReturnAdjustmentFound;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.mapper.ReturnAdjustmentMapper;
import com.skytala.eCommerce.domain.order.relations.returnAdjustment.model.ReturnAdjustment;


public class FindAllReturnAdjustments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnAdjustment> returnVal = new ArrayList<ReturnAdjustment>();
try{
List<GenericValue> results = delegator.findAll("ReturnAdjustment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnAdjustmentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnAdjustmentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
