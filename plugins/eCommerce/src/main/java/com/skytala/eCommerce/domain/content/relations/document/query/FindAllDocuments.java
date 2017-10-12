
package com.skytala.eCommerce.domain.content.relations.document.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.document.event.DocumentFound;
import com.skytala.eCommerce.domain.content.relations.document.mapper.DocumentMapper;
import com.skytala.eCommerce.domain.content.relations.document.model.Document;


public class FindAllDocuments extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Document> returnVal = new ArrayList<Document>();
try{
List<GenericValue> results = delegator.findAll("Document", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DocumentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DocumentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
