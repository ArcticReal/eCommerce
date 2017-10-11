
package com.skytala.eCommerce.domain.product.relations.productAssoc.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productAssoc.event.ProductAssocFound;
import com.skytala.eCommerce.domain.product.relations.productAssoc.mapper.ProductAssocMapper;
import com.skytala.eCommerce.domain.product.relations.productAssoc.model.ProductAssoc;


public class FindAllProductAssocs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductAssoc> returnVal = new ArrayList<ProductAssoc>();
try{
List<GenericValue> results = delegator.findAll("ProductAssoc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductAssocMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductAssocFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
