package cn.itcast.flink.source.test;

import cn.itcast.flink.source.test.entity.VehiclePlusBean;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class JsonPlusParse {
    public static void main(String[] args) {
        String jsonstr = "{\"batteryAlarm\": 0,\"carMode\": 1,\"minVoltageBattery\": 3.89,\"chargeStatus\": 1,\"vin\": \"LS5A3CJC0JF890971\",\"nevChargeSystemTemperatureDtoList\": [{\"probeTemperatures\": [25, 23, 24, 21, 24, 21, 23, 21, 23, 21, 24, 21, 24, 21, 25, 21],\"chargeTemperatureProbeNum\": 16,\"childSystemNum\": 1}]}";

        //TODO 2）定义map对象，将json的所有属性和数据值解析后追加到map对象中
        HashMap<String,Object> resultmap = jsonstrTomap(jsonstr);

        //TODO 3）根据key获取map对象的value，如果存在key，则获取到value，如果不存在key则返回null，或者初始化一个默认值
        int batteryAlarm = Integer.parseInt(resultmap.getOrDefault("batteryAlarm", -1).toString());
        int carMode = Integer.parseInt(resultmap.getOrDefault("carMode", -1).toString());
        double minVoltageBattery =Double.parseDouble(resultmap.getOrDefault("minVoltageBattery", -1).toString());
        int chargeStatus = Integer.parseInt(resultmap.getOrDefault("chargeStatus", -1).toString());
        String vin = resultmap.getOrDefault("vin", -1).toString();
        String nevChargeSystemTemperatureDtoList = resultmap.getOrDefault("nevChargeSystemTemperatureDtoList", -1).toString();
        List<HashMap<String, Object>> jsonStrTolist = jsonstrTolist(nevChargeSystemTemperatureDtoList);
        int chargeTemperatureProbeNum = 0;
        int childSystemNum = 0;
        List<Integer> probeTemperaturelist = new ArrayList<>();
        //集合中存在数据
        if (jsonStrTolist.size() > 0) {
            HashMap<String, Object> HashMap = jsonStrTolist.get(0);
            String probeTemperatures = HashMap.getOrDefault("probeTemperatures", -1).toString();
            JSONArray probeTemperaturesArrays = new JSONArray(probeTemperatures);
            probeTemperaturesArrays.forEach(o -> {
                int value = Integer.parseInt(o.toString());
                probeTemperaturelist.add(value);
            });
            chargeTemperatureProbeNum = Integer.parseInt(HashMap.getOrDefault("chargeTemperatureProbeNum", -1).toString());
            childSystemNum = Integer.parseInt(HashMap.getOrDefault("childSystemNum", -1).toString());
        }

        VehiclePlusBean vehiclePlusBean = new VehiclePlusBean(batteryAlarm, carMode, minVoltageBattery, chargeStatus, vin, probeTemperaturelist, chargeTemperatureProbeNum, childSystemNum);
        System.out.println(vehiclePlusBean);


    }

    /**
     * 将json字符串转换成map对象
     * @param jsonstr
     */
    public static List<HashMap<String,Object>> jsonstrTolist(String jsonstr) {
        //定义jsonArray对象
        JSONArray jsonArray = new JSONArray(jsonstr);
        //创建需要返回的集合对象
        ArrayList<HashMap<String,Object>> resultlist = new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++) {
            HashMap<String, Object> jsonMap = jsonstrTomap(jsonArray.get(i).toString());
            resultlist.add(jsonMap);
        }
        return resultlist;
    }

    /**
     * 将json字符串转换成map对象
     * @param jsonstr
     * @return
     */
    public static HashMap<String, Object> jsonstrTomap(String jsonstr) {
        //定义jsonObject对象
        JSONObject jsonObject = new JSONObject(jsonstr);
        //创建需要返回的hashMap对象
        HashMap<String, Object> resultMap = new HashMap<String,Object>();
        //获取jsonObject对象的所有key集合
        Set<String> keySet = jsonObject.keySet();
        //遍历key集合
        for (String key : keySet) {
            Object value = jsonObject.get(key);
            resultMap.put(key,value);
        }
        return  resultMap;
    }
}
