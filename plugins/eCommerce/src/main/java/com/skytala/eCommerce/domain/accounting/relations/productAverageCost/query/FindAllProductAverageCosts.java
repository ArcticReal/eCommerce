
package com.skytala.eCommerce.domain.accounting.relations.productAverageCost.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.event.ProductAverageCostFound;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.mapper.ProductAverageCostMapper;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCost.model.ProductAverageCost;


public class FindAllProductAverageCosts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductAverageCost> returnVal = new ArrayList<ProductAverageCost>();
try{
List<GenericValue> results = delegator.findAll("ProductAverageCost", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductAverageCostMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductAverageCostFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
