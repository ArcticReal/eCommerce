
package com.skytala.eCommerce.domain.shipment.relations.shippingDocument.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.event.ShippingDocumentFound;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.mapper.ShippingDocumentMapper;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.model.ShippingDocument;


public class FindAllShippingDocuments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ShippingDocument> returnVal = new ArrayList<ShippingDocument>();
try{
List<GenericValue> results = delegator.findAll("ShippingDocument", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ShippingDocumentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ShippingDocumentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
