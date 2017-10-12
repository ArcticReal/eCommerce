package com.skytala.eCommerce.domain.content.relations.documentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.documentType.event.DocumentTypeAdded;
import com.skytala.eCommerce.domain.content.relations.documentType.mapper.DocumentTypeMapper;
import com.skytala.eCommerce.domain.content.relations.documentType.model.DocumentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDocumentType extends Command {

private DocumentType elementToBeAdded;
public AddDocumentType(DocumentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DocumentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDocumentTypeId(delegator.getNextSeqId("DocumentType"));
GenericValue newValue = delegator.makeValue("DocumentType", elementToBeAdded.mapAttributeField());
addedElement = DocumentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DocumentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
