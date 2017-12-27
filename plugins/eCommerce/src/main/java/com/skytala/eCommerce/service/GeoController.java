package com.skytala.eCommerce.service;

import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/geo")
public class GeoController {

    @GetMapping("/country")
    public ResponseEntity<List<GenericValue>> getAllCountryGeos() throws GenericEntityException {
        Map filter = UtilMisc.toMap("geoTypeId","COUNTRY");
        return successful(DelegatorFactory.getDelegator("default")
                                          .findByAnd("Geo", filter, null, false));
    }

	@GetMapping("/country/{geoId}")
    public ResponseEntity<List<GenericValue>> getCountry(@PathVariable String geoId) throws GenericEntityException {
        Map filter = UtilMisc.toMap("geoTypeId","COUNTRY", "geoId", geoId);
		
        return successful(DelegatorFactory.getDelegator("default")
                                          .findByAnd("Geo", filter, null, false));
    }

    @GetMapping("/{countryGeoId}/regions")
    public ResponseEntity<List<GenericValue>> getAllRegionsForCountry(@PathVariable String countryGeoId) throws GenericEntityException {
        Map filter = UtilMisc.toMap("geoId", countryGeoId, "geoAssocTypeId", "REGIONS");
        Delegator delegator = DelegatorFactory.getDelegator("default");
        List<GenericValue> geoAssocs = delegator.findByAnd("GeoAssoc", filter, null, false);
        List<GenericValue> geos = new LinkedList<>();
        for(GenericValue ge : geoAssocs){
            filter = UtilMisc.toMap("geoId", ge.get("geoIdTo"));

            geos.add(delegator.findOne("Geo", filter, false));
        }
        return successful(geos);
    }

}
