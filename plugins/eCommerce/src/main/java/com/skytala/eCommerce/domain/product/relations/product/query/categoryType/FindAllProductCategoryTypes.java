
package com.skytala.eCommerce.domain.product.relations.product.query.categoryType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryType.ProductCategoryTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryType.ProductCategoryTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryType.ProductCategoryType;


public class FindAllProductCategoryTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategoryType> returnVal = new ArrayList<ProductCategoryType>();
try{
List<GenericValue> results = delegator.findAll("ProductCategoryType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCategoryTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCategoryTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
