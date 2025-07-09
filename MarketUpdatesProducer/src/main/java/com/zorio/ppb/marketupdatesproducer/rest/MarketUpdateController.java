package com.zorio.ppb.marketupdatesproducer.rest;

import com.zorio.ppb.marketupdatescore.MarketUpdate;
import com.zorio.ppb.marketupdatesproducer.service.MarketUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event/update")
public class MarketUpdateController {

    MarketUpdateService marketUpdateService;

    public MarketUpdateController(MarketUpdateService marketUpdateService) { this.marketUpdateService = marketUpdateService; }

    @PostMapping()
    public ResponseEntity<String> updateMarket(@RequestBody MarketUpdate marketUpdate) {

        String id;

        try { id = this.marketUpdateService.updateMarket(marketUpdate); }
        catch (Exception e) { throw new RuntimeException(e); }

        return ResponseEntity.status(HttpStatus.OK).body("Successfully updated market update with ID " + id);

    }

}
