
package com.skytala.eCommerce.domain.product.relations.product.query.categoryLink;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.categoryLink.ProductCategoryLinkFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.categoryLink.ProductCategoryLinkMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryLink.ProductCategoryLink;


public class FindAllProductCategoryLinks extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductCategoryLink> returnVal = new ArrayList<ProductCategoryLink>();
try{
List<GenericValue> results = delegator.findAll("ProductCategoryLink", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductCategoryLinkMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductCategoryLinkFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
