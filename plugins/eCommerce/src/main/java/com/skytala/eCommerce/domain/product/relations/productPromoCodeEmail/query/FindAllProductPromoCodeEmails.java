
package com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.event.ProductPromoCodeEmailFound;
import com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.mapper.ProductPromoCodeEmailMapper;
import com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.model.ProductPromoCodeEmail;


public class FindAllProductPromoCodeEmails extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromoCodeEmail> returnVal = new ArrayList<ProductPromoCodeEmail>();
try{
List<GenericValue> results = delegator.findAll("ProductPromoCodeEmail", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPromoCodeEmailMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPromoCodeEmailFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
