package com.xiaojiuwo.services;

import com.xiaojiuwo.Application;
import com.xiaojiuwo.models.Point;
import com.xiaojiuwo.models.Station;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Created by liuhaibao on 15/11/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)

@ImportResource("classpath:config/applicationContext.xml")
public class StationsServiceTest {

    @Autowired
    StationsService stationsService;


    @Test
    public void test_get_all_stations() throws Exception {

        List<Station> stations = stationsService.getAllStations();
        assertNotNull(stations);
        assertThat(stations.size(), greaterThan(16));

    }


    @Test
    public void test_get_all_staitons_location() throws Exception {

        List<Station> stations = stationsService.getAllStationsAndLoaction();
        assertNotNull(stations);
        assertThat(stations.size(), greaterThan(16));

    }


    @Test
    public void test_get_staiton_points() throws Exception {

        List<Station> stations = stationsService.getAllStationsAndLoaction();
        List<Point> points = stationsService.findStationPointsById(stations.get(0).getId());
        assertNotNull(points);
        //assertThat(stations.size(), greaterThan(16));

    }
}
