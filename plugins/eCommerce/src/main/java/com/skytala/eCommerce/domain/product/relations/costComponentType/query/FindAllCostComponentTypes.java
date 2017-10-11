
package com.skytala.eCommerce.domain.product.relations.costComponentType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.costComponentType.event.CostComponentTypeFound;
import com.skytala.eCommerce.domain.product.relations.costComponentType.mapper.CostComponentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.costComponentType.model.CostComponentType;


public class FindAllCostComponentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CostComponentType> returnVal = new ArrayList<CostComponentType>();
try{
List<GenericValue> results = delegator.findAll("CostComponentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CostComponentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CostComponentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
