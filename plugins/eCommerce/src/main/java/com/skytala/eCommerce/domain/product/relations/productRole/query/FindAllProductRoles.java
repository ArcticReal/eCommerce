
package com.skytala.eCommerce.domain.product.relations.productRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productRole.event.ProductRoleFound;
import com.skytala.eCommerce.domain.product.relations.productRole.mapper.ProductRoleMapper;
import com.skytala.eCommerce.domain.product.relations.productRole.model.ProductRole;


public class FindAllProductRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductRole> returnVal = new ArrayList<ProductRole>();
try{
List<GenericValue> results = delegator.findAll("ProductRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
