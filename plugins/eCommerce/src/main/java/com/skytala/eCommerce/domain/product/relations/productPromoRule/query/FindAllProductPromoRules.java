
package com.skytala.eCommerce.domain.product.relations.productPromoRule.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.event.ProductPromoRuleFound;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.mapper.ProductPromoRuleMapper;
import com.skytala.eCommerce.domain.product.relations.productPromoRule.model.ProductPromoRule;


public class FindAllProductPromoRules extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPromoRule> returnVal = new ArrayList<ProductPromoRule>();
try{
List<GenericValue> results = delegator.findAll("ProductPromoRule", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPromoRuleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPromoRuleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}