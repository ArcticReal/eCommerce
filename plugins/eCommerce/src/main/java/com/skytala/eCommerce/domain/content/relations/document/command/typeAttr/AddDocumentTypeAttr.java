package com.skytala.eCommerce.domain.content.relations.document.command.typeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.document.event.typeAttr.DocumentTypeAttrAdded;
import com.skytala.eCommerce.domain.content.relations.document.mapper.typeAttr.DocumentTypeAttrMapper;
import com.skytala.eCommerce.domain.content.relations.document.model.typeAttr.DocumentTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDocumentTypeAttr extends Command {

private DocumentTypeAttr elementToBeAdded;
public AddDocumentTypeAttr(DocumentTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DocumentTypeAttr addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("DocumentTypeAttr"));
GenericValue newValue = delegator.makeValue("DocumentTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = DocumentTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DocumentTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
