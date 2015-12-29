package com.xiaojiuwo.services;

import com.xiaojiuwo.Application;
import com.xiaojiuwo.models.Area;
import com.xiaojiuwo.models.Point;
import com.xiaojiuwo.models.Station;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.comparesEqualTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by liuhaibao on 15/11/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@ImportResource("classpath:config/applicationContext.xml")
@Transactional
public class AreasServiceTest {

    @Autowired
    AreasService areasService;

    @Autowired
    PointsService pointsService;

    private long stationId = 11111111;
    private String label = "test";

    @Test
    @Transactional
    public void test_get_areas() throws Exception {


        List<Area> areas = areasService.findStationAreas(stationId);
        Area testArea = areas.get(0);
        assertEquals(label,testArea.getLabel());
        assertThat(areas.size(), greaterThan(0));

    }

    @Test
    @Transactional
    public void test_get_area_points() throws Exception {


        List<Area> areas = areasService.findStationAreas(stationId);
        Area testArea = areas.get(0);

        Point p = new Point();

        p.setPointableId(testArea.getId());
        p.setPointableType("Area");
        p.setLantitude(12.34);
        p.setLongitude(232.56565656);

        Point p1 = new Point();
        p1.setPointableId(testArea.getId());
        p1.setPointableType("Area");
        p1.setLantitude(56.12121);
        p1.setLongitude(123.797989);
        pointsService.save(p);
        pointsService.save(p1);

        List<Point> points = areasService.findAreaPoint(testArea.getId());
        assertEquals(points.size(),2);

        pointsService.deleteByPointableId(testArea.getId());


    }

    @Before
    public void setUp(){
        Area a = new Area();
        a.setLabel(label);
        a.setStationId(stationId);
        int result = areasService.save(a);
    }


    /**
     * not used , because we have transaction rollback
     */
    public void tearDown(){
        List<Area> areas = areasService.findStationAreas(stationId);
        for(Area area : areas){
            int result = areasService.delete(area.getId());
            assertThat(result, greaterThan(0));
        }
    }
}
