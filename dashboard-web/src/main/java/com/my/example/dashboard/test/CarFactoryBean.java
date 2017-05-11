package com.my.example.dashboard.test;

import org.springframework.beans.factory.FactoryBean;

/**
 * Date:17/3/29
 * Time:下午5:36
 *
 * @author yongquan.wen
 */
public class CarFactoryBean implements FactoryBean<Car> {

    private String carInfo;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] info = carInfo.split(",");
        car.setBrand(info[0]);
        car.setMaxSpeed(Integer.parseInt(info[1]));
        car.setPrice(Integer.parseInt(info[2]));
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
}
