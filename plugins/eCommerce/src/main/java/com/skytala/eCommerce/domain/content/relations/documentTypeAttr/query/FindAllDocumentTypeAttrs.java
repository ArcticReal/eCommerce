
package com.skytala.eCommerce.domain.content.relations.documentTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.documentTypeAttr.event.DocumentTypeAttrFound;
import com.skytala.eCommerce.domain.content.relations.documentTypeAttr.mapper.DocumentTypeAttrMapper;
import com.skytala.eCommerce.domain.content.relations.documentTypeAttr.model.DocumentTypeAttr;


public class FindAllDocumentTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DocumentTypeAttr> returnVal = new ArrayList<DocumentTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("DocumentTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DocumentTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DocumentTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
