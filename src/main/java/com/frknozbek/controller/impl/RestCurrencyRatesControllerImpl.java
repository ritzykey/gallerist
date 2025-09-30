package com.frknozbek.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frknozbek.controller.IRestCurrencyRatesController;
import com.frknozbek.controller.RestBaseController;
import com.frknozbek.controller.RootEntity;
import com.frknozbek.dto.CurrencyRatesResponse;
import com.frknozbek.service.ICurrencyRatesService;

@RestController
@RequestMapping("/rest/api/currency-rates")
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesController {

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @GetMapping
    @Override
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return ok(currencyRatesService.getCurrenyRatesResponse(startDate, endDate));
    }

}
