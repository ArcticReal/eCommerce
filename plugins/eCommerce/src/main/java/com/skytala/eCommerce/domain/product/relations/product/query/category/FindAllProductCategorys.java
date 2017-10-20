
package com.skytala.eCommerce.domain.product.relations.product.query.category;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.category.ProductCategoryFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.category.ProductCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;


public class FindAllProductCategorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategory> returnVal = new ArrayList<ProductCategory>();
try{
List<GenericValue> results = delegator.findAll("ProductCategory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCategoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCategoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
