
package com.skytala.eCommerce.domain.product.relations.costComponent.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.costComponent.event.attribute.CostComponentAttributeFound;
import com.skytala.eCommerce.domain.product.relations.costComponent.mapper.attribute.CostComponentAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.costComponent.model.attribute.CostComponentAttribute;


public class FindAllCostComponentAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CostComponentAttribute> returnVal = new ArrayList<CostComponentAttribute>();
try{
List<GenericValue> results = delegator.findAll("CostComponentAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CostComponentAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CostComponentAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
