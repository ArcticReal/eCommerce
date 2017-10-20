
package com.skytala.eCommerce.domain.product.relations.product.query.promoUse;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.promoUse.ProductPromoUseFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoUse.ProductPromoUseMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoUse.ProductPromoUse;


public class FindAllProductPromoUses extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromoUse> returnVal = new ArrayList<ProductPromoUse>();
try{
List<GenericValue> results = delegator.findAll("ProductPromoUse", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPromoUseMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPromoUseFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
