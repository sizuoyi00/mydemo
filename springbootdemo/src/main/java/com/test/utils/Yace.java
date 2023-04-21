package com.test.utils;

import org.sonarsource.scanner.api.internal.shaded.okhttp.*;

import java.io.IOException;

public class Yace {

    private static OkHttpClient client = new OkHttpClient().newBuilder()
            .build();

    public static void main(String[] args) {


        MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "{\"objectDataId\":\"6420446bb456990001799a05\",\"trigger_info\":{\"trigger_page\":\"Detail\"}}");
        Request request = new Request.Builder()
                .url("https://crm.ceshi112.com/FHH/EM1HNCRM/API/v1/object/object_7wEn8__c/action/button_neHT8__c?_fs_token=PcDbDpOuCpCjD6PZEIqqE34vBM5bDJ0jDJOmDpSvCcHXPMGp&traceId=E-E.84847.1000-1680081376740")
                .method("POST", body)
                .addHeader("authority", "crm.ceshi112.com")
                .addHeader("accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("accept-language", "zh-CN,zh-TW;0.9,en;0.8")
                .addHeader("cache-control", "no-cache")
                .addHeader("content-type", "application/json; charset=UTF-8")
                .addHeader("cookie", "guid=80383394-2baf-a4e9-c5f1-2184bd81e530; hy_data_2020_id=184c7cc648d10ce-0ed4d13fa84234-18525635-1296000-184c7cc648ef62; hy_data_2020_js_sdk=%7B%22distinct_id%22%3A%22184c7cc648d10ce-0ed4d13fa84234-18525635-1296000-184c7cc648ef62%22%2C%22site_id%22%3A478%2C%22user_company%22%3A409%2C%22props%22%3A%7B%7D%2C%22device_id%22%3A%22184c7cc648d10ce-0ed4d13fa84234-18525635-1296000-184c7cc648ef62%22%7D; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184ec4a59ad0-0eafbd7feef335-17525635-1296000-184ec4a59ae1060%22%2C%22%24device_id%22%3A%22184ec4a59ad0-0eafbd7feef335-17525635-1296000-184ec4a59ae1060%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; Hm_lvt_06d5233541e92feb3cc8980700b1efa6=1677482762,1678418333; mirrorId=0000; EPXId=46bfbb3b6c03480fb8ded0b5cf2eaacb; fs_token=PcDbDpOuCpCjD6PZEIqqE34vBM5bDJ0jDJOmDpSvCcHXPMGp; FSAuthX=0G60aEnKym40001TnAvAXJV7QgE8xvhw3PtYmkXhyZ7y9s5wDGAN37a7ydLVI7HuSTMyBCEUC5rha9AmkHgCLyuH3PGjuTwwz5u7PC4GXbkc2UvRJHC18NncyKHyHIWoJX4PyXKfwNV27nUY3ARF4VsVJJOkgGiPn0Sk199buV81WDMx63vc2stSdDXzhiNiygolzzmlhrNz4JXWDi9O3d68rBkXSldAyEIOpm7; FSAuthXC=0G60aEnKym40001TnAvAXJV7QgE8xvhw3PtYmkXhyZ7y9s5wDGAN37a7ydLVI7HuSTMyBCEUC5rha9AmkHgCLyuH3PGjuTwwz5u7PC4GXbkc2UvRJHC18NncyKHyHIWoJX4PyXKfwNV27nUY3ARF4VsVJJOkgGiPn0Sk199buV81WDMx63vc2stSdDXzhiNiygolzzmlhrNz4JXWDi9O3d68rBkXSldAyEIOpm7; lang=zh-CN; JSESSIONID=79C151D0CDA1F47A10631B831E4FB5F5")
                .addHeader("origin", "https://crm.ceshi112.com")
                .addHeader("pragma", "no-cache")
                .addHeader("referer", "https://crm.ceshi112.com/XV/UI/Home")
                .addHeader("sec-ch-ua", "\"Chromium\";v=\"110\", \"Not A(Brand\";v=\"24\", \"Google Chrome\";v=\"110\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"macOS\"")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .addHeader("x-trace-id", "84847_1000_1680079572319:294")
                .build();

        for (int i = 0; i < 200; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Response response = client.newCall(request).execute();
                    System.out.println(finalI + response.body().string());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

        }

        System.out.println("okkkk");
    }

}
