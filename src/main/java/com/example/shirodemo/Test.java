package com.example.shirodemo;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.request.vopsp.VopGoodsCheckAreaLimitListRequest;
import com.jd.open.api.sdk.response.vopsp.VopGoodsCheckAreaLimitListResponse;

public class Test {
    public static void main(String[] args) throws Exception {
        String serverUrl = "http://vopman.jd.com/josAuth/callbackToken";
        String appKey = "DFEFCBB9269D294F19844FC9AE904F9B";
        String appSecret = "139d0b5e020f4e79a64dcb95870667f4";
        String accessToken = "youAccessToken";
        JdClient client = new DefaultJdClient(serverUrl, accessToken, appKey, appSecret);
        VopGoodsCheckAreaLimitListRequest req = new VopGoodsCheckAreaLimitListRequest();
        req.setSkuId("885987");
        req.setProvinceId(11L);
        req.setCityId(902L);
        req.setCountyId(32769L);
        req.setTownId(0L);
        VopGoodsCheckAreaLimitListResponse resp = client.execute(req);
        System.out.println(resp.getOpenRpcResult());
    }
}