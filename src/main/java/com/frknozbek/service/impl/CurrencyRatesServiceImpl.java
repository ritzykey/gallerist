package com.frknozbek.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.frknozbek.dto.CurrencyRatesResponse;
import com.frknozbek.exception.BaseException;
import com.frknozbek.exception.ErrorMessage;
import com.frknozbek.exception.MessageType;
import com.frknozbek.service.ICurrencyRatesService;


@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {

    // Dotenv dotenv = Dotenv.load();

    @Value("${evds2.key}")
    private String evds2Key;

    @Override
    public CurrencyRatesResponse getCurrenyRatesResponse(String startDate, String endDate) {
        String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A.YTL";
        String type = "json";

        String endpoint = rootURL.concat("series=" + series);

        endpoint = endpoint.concat("&startDate=" + startDate);
        endpoint = endpoint.concat("&endDate=" + endDate);
        endpoint = endpoint.concat("&type=" + type);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key",evds2Key);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                    new ParameterizedTypeReference<CurrencyRatesResponse>() {
                    });

            if (response.getStatusCode().is2xxSuccessful()) {

                return response.getBody();
            }

        } catch (Exception e) {
            throw new BaseException(
                    new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, endpoint.concat("\n" + e.getMessage())));

        }

        throw new BaseException(
                new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, endpoint));

    }

}

// https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A.YTL&startDate=30-09-2025&endDate=30-09-2025&type=json