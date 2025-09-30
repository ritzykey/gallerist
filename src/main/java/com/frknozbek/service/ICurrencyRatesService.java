package com.frknozbek.service;

import com.frknozbek.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

    public CurrencyRatesResponse getCurrenyRatesResponse(String startDate, String endDate);

}
