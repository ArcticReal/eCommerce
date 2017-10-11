
package com.skytala.eCommerce.domain.product.relations.productPaymentMethodType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productPaymentMethodType.event.ProductPaymentMethodTypeFound;
import com.skytala.eCommerce.domain.product.relations.productPaymentMethodType.mapper.ProductPaymentMethodTypeMapper;
import com.skytala.eCommerce.domain.product.relations.productPaymentMethodType.model.ProductPaymentMethodType;


public class FindAllProductPaymentMethodTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductPaymentMethodType> returnVal = new ArrayList<ProductPaymentMethodType>();
try{
List<GenericValue> results = delegator.findAll("ProductPaymentMethodType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductPaymentMethodTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductPaymentMethodTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
