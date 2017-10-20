
package com.skytala.eCommerce.domain.product.relations.product.query.priceRule;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.priceRule.ProductPriceRuleFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.priceRule.ProductPriceRuleMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.priceRule.ProductPriceRule;


public class FindAllProductPriceRules extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPriceRule> returnVal = new ArrayList<ProductPriceRule>();
try{
List<GenericValue> results = delegator.findAll("ProductPriceRule", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPriceRuleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPriceRuleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
