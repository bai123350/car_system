package cn.itcast.flink.source.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleBean {
    private int batteryAlarm;
    private int carMode;
    private double minVoltageBattery;
    private int chargeStatus;
    private String vin;
}
