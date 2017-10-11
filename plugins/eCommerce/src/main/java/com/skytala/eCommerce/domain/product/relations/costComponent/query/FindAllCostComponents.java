
package com.skytala.eCommerce.domain.product.relations.costComponent.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.CostComponentFound;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.CostComponentMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.CostComponent;


public class FindAllCostComponents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CostComponent> returnVal = new ArrayList<CostComponent>();
try{
List<GenericValue> results = delegator.findAll("CostComponent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CostComponentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CostComponentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
