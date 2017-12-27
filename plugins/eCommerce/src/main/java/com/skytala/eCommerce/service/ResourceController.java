package com.skytala.eCommerce.service;


import com.skytala.eCommerce.domain.content.event.ContentFound;
import com.skytala.eCommerce.domain.content.model.Content;
import com.skytala.eCommerce.domain.content.query.FindContentsBy;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.DataResourceFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.DataResource;
import com.skytala.eCommerce.domain.content.relations.dataResource.query.FindDataResourcesBy;
import com.skytala.eCommerce.domain.product.relations.product.event.content.ProductContentFound;
import com.skytala.eCommerce.domain.product.relations.product.model.content.ProductContent;
import com.skytala.eCommerce.domain.product.relations.product.query.content.FindProductContentsBy;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.framework.pubsub.Scheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resources")
public class ResourceController {


    @GetMapping("/productImageURLs/{productId}")
    public List getProductImageUrls(@PathVariable("productId")String productId) throws Exception {
        Map<String, String> filter = new HashMap<>();
        filter.put("productId",productId);
        FindProductContentsBy query = new FindProductContentsBy(filter);
        ProductContentFound resultingEvent = (ProductContentFound) Scheduler.execute(query).data();
        List<String> URLs = new LinkedList<>();
        for(ProductContent c : resultingEvent.getProductContents()){
            filter.clear();
            filter.put("contentId",c.getContentId());
            FindContentsBy contentQuery = new FindContentsBy(filter);
            List<Content> contents = ((ContentFound) Scheduler.execute(contentQuery).data()).getContents();
            if(contents != null && contents.size()==1){
                filter.clear();
                filter.put("dataResourceId", contents.get(0).getDataResourceId());
                FindDataResourcesBy resourceQuery = new FindDataResourcesBy(filter);
                DataResource resource = ((DataResourceFound) Scheduler.execute(resourceQuery).data()).getDataResources().get(0);
                URLs.add(resource.getObjectInfo());
            }else{
                System.err.println("You made some errors!!!\n\n\ncontents is either null or not the size of one");
            }
        }

        return URLs;
    }



}
