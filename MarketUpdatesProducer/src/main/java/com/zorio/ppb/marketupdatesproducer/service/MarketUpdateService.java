package com.zorio.ppb.marketupdatesproducer.service;

import com.zorio.ppb.marketupdatescore.MarketUpdate;

import java.util.concurrent.ExecutionException;

public interface MarketUpdateService {

    String updateMarket(MarketUpdate marketUpdate) throws ExecutionException, InterruptedException;

}
