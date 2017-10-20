
package com.skytala.eCommerce.domain.product.relations.product.query.content;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.content.ProductContentFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.content.ProductContentMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.content.ProductContent;


public class FindAllProductContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductContent> returnVal = new ArrayList<ProductContent>();
try{
List<GenericValue> results = delegator.findAll("ProductContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
