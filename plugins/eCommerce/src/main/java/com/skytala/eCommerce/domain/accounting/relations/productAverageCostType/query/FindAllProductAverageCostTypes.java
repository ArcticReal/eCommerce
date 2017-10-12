
package com.skytala.eCommerce.domain.accounting.relations.productAverageCostType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCostType.event.ProductAverageCostTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCostType.mapper.ProductAverageCostTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.productAverageCostType.model.ProductAverageCostType;


public class FindAllProductAverageCostTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductAverageCostType> returnVal = new ArrayList<ProductAverageCostType>();
try{
List<GenericValue> results = delegator.findAll("ProductAverageCostType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductAverageCostTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductAverageCostTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
