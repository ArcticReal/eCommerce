
package com.skytala.eCommerce.domain.product.relations.product.query.keyword;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.keyword.ProductKeywordFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.keyword.ProductKeywordMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.keyword.ProductKeyword;


public class FindAllProductKeywords extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductKeyword> returnVal = new ArrayList<ProductKeyword>();
try{
List<GenericValue> results = delegator.findAll("ProductKeyword", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductKeywordMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductKeywordFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
