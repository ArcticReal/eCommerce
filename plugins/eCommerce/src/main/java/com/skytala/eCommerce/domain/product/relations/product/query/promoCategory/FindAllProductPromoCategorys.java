
package com.skytala.eCommerce.domain.product.relations.product.query.promoCategory;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCategory.ProductPromoCategoryFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCategory.ProductPromoCategoryMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCategory.ProductPromoCategory;


public class FindAllProductPromoCategorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromoCategory> returnVal = new ArrayList<ProductPromoCategory>();
try{
List<GenericValue> results = delegator.findAll("ProductPromoCategory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPromoCategoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPromoCategoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
