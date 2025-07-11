package com.zorio.ppb.marketupdatesproducer.rest;

import com.zorio.ppb.marketupdatescore.MarketUpdate;
import com.zorio.ppb.marketupdatesproducer.service.MarketUpdateService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/market_update")
public class MarketUpdateController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    MarketUpdateService marketUpdateService;

    public MarketUpdateController(MarketUpdateService marketUpdateService) { this.marketUpdateService = marketUpdateService; }

    @PostMapping()
    public ResponseEntity<String> updateMarket(@Valid @RequestBody MarketUpdate marketUpdate) {

        String id;

        try {

            id = this.marketUpdateService.updateMarket(marketUpdate);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully updated market update with ID " + id);

        } catch (Exception e) { logger.error(e.getMessage()); }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

}
