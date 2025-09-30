package com.frknozbek.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRatesResponse {

    private Integer totalCount;

    private List<CurrencyRatesItems> items;

}

/* 
{
    "totalCount": 1,
    "items": [
        {
            "Tarih": "30-09-2025",
            "TP_DK_USD_A_YTL": "41.4984",
            "UNIXTIME": {
                "$numberLong": "1759179600"
            }
        }
    ]
}

 */
