
package com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.event.ProductStoreGroupRoleFound;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.mapper.ProductStoreGroupRoleMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreGroupRole.model.ProductStoreGroupRole;


public class FindAllProductStoreGroupRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreGroupRole> returnVal = new ArrayList<ProductStoreGroupRole>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreGroupRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreGroupRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreGroupRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
