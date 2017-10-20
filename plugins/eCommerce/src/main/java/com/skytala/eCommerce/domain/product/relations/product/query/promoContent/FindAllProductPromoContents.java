
package com.skytala.eCommerce.domain.product.relations.product.query.promoContent;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.promoContent.ProductPromoContentFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoContent.ProductPromoContentMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoContent.ProductPromoContent;


public class FindAllProductPromoContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromoContent> returnVal = new ArrayList<ProductPromoContent>();
try{
List<GenericValue> results = delegator.findAll("ProductPromoContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPromoContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPromoContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
