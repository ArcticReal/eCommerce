
package com.skytala.eCommerce.domain.product.relations.product.query.storeGroup;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.storeGroup.ProductStoreGroupFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeGroup.ProductStoreGroupMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeGroup.ProductStoreGroup;


public class FindAllProductStoreGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreGroup> returnVal = new ArrayList<ProductStoreGroup>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
