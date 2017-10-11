
package com.skytala.eCommerce.domain.product.relations.productAssocType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productAssocType.event.ProductAssocTypeFound;
import com.skytala.eCommerce.domain.product.relations.productAssocType.mapper.ProductAssocTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productAssocType.model.ProductAssocType;


public class FindAllProductAssocTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductAssocType> returnVal = new ArrayList<ProductAssocType>();
try{
List<GenericValue> results = delegator.findAll("ProductAssocType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductAssocTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductAssocTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
