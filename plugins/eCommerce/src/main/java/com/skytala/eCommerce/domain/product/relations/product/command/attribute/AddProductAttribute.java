package com.skytala.eCommerce.domain.product.relations.product.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.event.attribute.ProductAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.product.mapper.attribute.ProductAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.product.model.attribute.ProductAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddProductAttribute extends Command {

    private ProductAttribute elementToBeAdded;
    public AddProductAttribute(ProductAttribute elementToBeAdded){
        this.elementToBeAdded = elementToBeAdded;
    }

    @Override
    public Event execute(){


        Delegator delegator = DelegatorFactory.getDelegator("default");

        ProductAttribute addedElement = null;
        boolean success = false;
        try {

            GenericValue newValue = delegator.makeValue("ProductAttribute", elementToBeAdded.mapAttributeField());
            addedElement = ProductAttributeMapper.map(delegator.create(newValue));
            success = true;
        } catch(GenericEntityException e) {


            e.printStackTrace();
            addedElement = null;
        }

        Event resultingEvent = new ProductAttributeAdded(addedElement, success);
        Broker.instance().publish(resultingEvent);
        return resultingEvent;
    }
}
