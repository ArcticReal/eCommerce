
package com.skytala.eCommerce.domain.product.relations.product.query.categoryRollup;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryRollup.ProductCategoryRollupFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryRollup.ProductCategoryRollupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryRollup.ProductCategoryRollup;


public class FindAllProductCategoryRollups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategoryRollup> returnVal = new ArrayList<ProductCategoryRollup>();
try{
List<GenericValue> results = delegator.findAll("ProductCategoryRollup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCategoryRollupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCategoryRollupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
