
package com.skytala.eCommerce.domain.product.relations.productCategoryRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productCategoryRole.event.ProductCategoryRoleFound;
import com.skytala.eCommerce.domain.product.relations.productCategoryRole.mapper.ProductCategoryRoleMapper;
import com.skytala.eCommerce.domain.product.relations.productCategoryRole.model.ProductCategoryRole;


public class FindAllProductCategoryRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategoryRole> returnVal = new ArrayList<ProductCategoryRole>();
try{
List<GenericValue> results = delegator.findAll("ProductCategoryRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCategoryRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCategoryRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
