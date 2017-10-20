
package com.skytala.eCommerce.domain.product.relations.product.query.storeRole;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.storeRole.ProductStoreRoleFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.storeRole.ProductStoreRoleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.storeRole.ProductStoreRole;


public class FindAllProductStoreRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreRole> returnVal = new ArrayList<ProductStoreRole>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
