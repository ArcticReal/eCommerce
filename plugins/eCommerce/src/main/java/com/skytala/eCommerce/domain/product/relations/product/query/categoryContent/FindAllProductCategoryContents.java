
package com.skytala.eCommerce.domain.product.relations.product.query.categoryContent;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryContent.ProductCategoryContentFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryContent.ProductCategoryContentMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryContent.ProductCategoryContent;


public class FindAllProductCategoryContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategoryContent> returnVal = new ArrayList<ProductCategoryContent>();
try{
List<GenericValue> results = delegator.findAll("ProductCategoryContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCategoryContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCategoryContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
