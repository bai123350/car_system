package cn.itcast.flink.source.test;

import cn.itcast.flink.source.test.entity.VehicleBean;
import org.json.JSONObject;

/**
 * 解析json字符串
 */
public class JsonParse {
    public static void main(String[] args) {
        String json = "{\"batteryAlarm\": 0,\"carMode\": 1,\"minVoltageBattery\": 3.89,\"chargeStatus\": 1,\"vin\": \"LS5A3CJC0JF890971\",\"nevChargeSystemTemperatureDtoList\": [{\"probeTemperatures\": [25, 23, 24, 21, 24, 21, 23, 21, 23, 21, 24, 21, 24, 21, 25, 21],\"chargeTemperatureProbeNum\": 16,\"childSystemNum\": 1}]}";
        JSONObject jsonObject = new JSONObject(json);
        //3.解析字符串
        VehicleBean vehicleBean = new VehicleBean(
                jsonObject.getInt("batteryAlarm"),
                jsonObject.getInt("carMode"),
                jsonObject.getDouble("minVoltageBattery"),
                jsonObject.getInt("chargeStatus"),
                jsonObject.getString("vin")
        );
        System.out.println(vehicleBean.toString());


    }
}
