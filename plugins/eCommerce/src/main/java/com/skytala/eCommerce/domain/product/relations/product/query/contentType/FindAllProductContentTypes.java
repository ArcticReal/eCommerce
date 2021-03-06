
package com.skytala.eCommerce.domain.product.relations.product.query.contentType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.product.event.contentType.ProductContentTypeFound;
import com.skytala.eCommerce.domain.product.relations.product.mapper.contentType.ProductContentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.contentType.ProductContentType;


public class FindAllProductContentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductContentType> returnVal = new ArrayList<ProductContentType>();
try{
List<GenericValue> results = delegator.findAll("ProductContentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductContentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductContentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
