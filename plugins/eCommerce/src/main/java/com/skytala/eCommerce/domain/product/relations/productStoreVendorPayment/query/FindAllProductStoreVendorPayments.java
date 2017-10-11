
package com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.event.ProductStoreVendorPaymentFound;
import com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.mapper.ProductStoreVendorPaymentMapper;
import com.skytala.eCommerce.domain.product.relations.productStoreVendorPayment.model.ProductStoreVendorPayment;


public class FindAllProductStoreVendorPayments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ProductStoreVendorPayment> returnVal = new ArrayList<ProductStoreVendorPayment>();
try{
List<GenericValue> results = delegator.findAll("ProductStoreVendorPayment", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ProductStoreVendorPaymentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ProductStoreVendorPaymentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
