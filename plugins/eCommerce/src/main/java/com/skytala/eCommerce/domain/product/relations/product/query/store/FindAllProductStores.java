
package com.skytala.eCommerce.domain.product.relations.product.query.store;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.store.ProductStoreFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.store.ProductStoreMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.store.ProductStore;


public class FindAllProductStores extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStore> returnVal = new ArrayList<ProductStore>();
try{
List<GenericValue> results = delegator.findAll("ProductStore", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
