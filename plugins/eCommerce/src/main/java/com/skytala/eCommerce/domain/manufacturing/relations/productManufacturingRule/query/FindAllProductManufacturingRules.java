
package com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.event.ProductManufacturingRuleFound;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.mapper.ProductManufacturingRuleMapper;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.model.ProductManufacturingRule;


public class FindAllProductManufacturingRules extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductManufacturingRule> returnVal = new ArrayList<ProductManufacturingRule>();
try{
List<GenericValue> results = delegator.findAll("ProductManufacturingRule", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductManufacturingRuleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductManufacturingRuleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
