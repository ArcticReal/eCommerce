
package com.skytala.eCommerce.domain.documentType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.documentType.event.DocumentTypeFound;
import com.skytala.eCommerce.domain.documentType.mapper.DocumentTypeMapper;
import com.skytala.eCommerce.domain.documentType.model.DocumentType;


public class FindAllDocumentTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DocumentType> returnVal = new ArrayList<DocumentType>();
try{
List<GenericValue> results = delegator.findAll("DocumentType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DocumentTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DocumentTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
