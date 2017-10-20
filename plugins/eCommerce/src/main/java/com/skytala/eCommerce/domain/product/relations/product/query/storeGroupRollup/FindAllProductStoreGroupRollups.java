
package com.skytala.eCommerce.domain.product.relations.product.query.storeGroupRollup;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroupRollup.ProductStoreGroupRollupFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroupRollup.ProductStoreGroupRollupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupRollup.ProductStoreGroupRollup;


public class FindAllProductStoreGroupRollups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreGroupRollup> returnVal = new ArrayList<ProductStoreGroupRollup>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreGroupRollup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreGroupRollupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreGroupRollupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
