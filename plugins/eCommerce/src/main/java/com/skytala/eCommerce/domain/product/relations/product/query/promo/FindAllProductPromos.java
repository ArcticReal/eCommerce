
package com.skytala.eCommerce.domain.product.relations.product.query.promo;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.promo.ProductPromoFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promo.ProductPromoMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromo;


public class FindAllProductPromos extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromo> returnVal = new ArrayList<ProductPromo>();
try{
List<GenericValue> results = delegator.findAll("ProductPromo", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPromoMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPromoFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
