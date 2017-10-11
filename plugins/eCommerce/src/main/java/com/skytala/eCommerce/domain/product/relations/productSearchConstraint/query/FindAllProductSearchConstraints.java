
package com.skytala.eCommerce.domain.product.relations.productSearchConstraint.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.event.ProductSearchConstraintFound;
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.mapper.ProductSearchConstraintMapper;
import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.model.ProductSearchConstraint;


public class FindAllProductSearchConstraints extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductSearchConstraint> returnVal = new ArrayList<ProductSearchConstraint>();
try{
List<GenericValue> results = delegator.findAll("ProductSearchConstraint", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductSearchConstraintMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductSearchConstraintFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
