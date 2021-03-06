
package com.skytala.eCommerce.domain.product.relations.product.query.categoryContentType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContentType.ProductCategoryContentTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryContentType.ProductCategoryContentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryContentType.ProductCategoryContentType;


public class FindAllProductCategoryContentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategoryContentType> returnVal = new ArrayList<ProductCategoryContentType>();
try{
List<GenericValue> results = delegator.findAll("ProductCategoryContentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCategoryContentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCategoryContentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
