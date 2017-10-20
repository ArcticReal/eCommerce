
package com.skytala.eCommerce.domain.product.relations.costComponent.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.type.CostComponentTypeFound;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.type.CostComponentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.type.CostComponentType;


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
