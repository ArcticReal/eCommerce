
package com.skytala.eCommerce.domain.order.relations.returnAdjustmentType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.returnAdjustmentType.event.ReturnAdjustmentTypeFound;
import com.skytala.eCommerce.domain.order.relations.returnAdjustmentType.mapper.ReturnAdjustmentTypeMapper;
import com.skytala.eCommerce.domain.order.relations.returnAdjustmentType.model.ReturnAdjustmentType;


public class FindAllReturnAdjustmentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnAdjustmentType> returnVal = new ArrayList<ReturnAdjustmentType>();
try{
List<GenericValue> results = delegator.findAll("ReturnAdjustmentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnAdjustmentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnAdjustmentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
