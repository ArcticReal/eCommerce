
package com.skytala.eCommerce.domain.product.relations.product.query.promoCodeParty;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.promoCodeParty.ProductPromoCodePartyFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.promoCodeParty.ProductPromoCodePartyMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeParty.ProductPromoCodeParty;


public class FindAllProductPromoCodePartys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromoCodeParty> returnVal = new ArrayList<ProductPromoCodeParty>();
try{
List<GenericValue> results = delegator.findAll("ProductPromoCodeParty", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPromoCodePartyMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPromoCodePartyFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
