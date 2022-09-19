package cn.itcast.flink.source.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePlusBean {
    private int batteryAlarm;
    private int carMode;
    private double minVoltageBattery;
    private int chargeStatus;
    private String vin;
    private List<Integer> probeTemperatures;
    private int chargeTemperatureProbeNum;
    private int childSystemNum;
}
