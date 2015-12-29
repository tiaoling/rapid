package com.xiaojiuwo.integrations;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaojiuwo.Application;
import com.xiaojiuwo.models.*;
import com.xiaojiuwo.services.AreasService;
import com.xiaojiuwo.services.CommissionsService;
import com.xiaojiuwo.services.PointsService;
import com.xiaojiuwo.services.StationsService;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;

/**
 * Created by liuhaibao on 15/10/22.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class AreasControllerIntegration {

    @Value("${local.server.port}")
    private int port;

    private String base;
    private String areaPriceUrl;
    private RestTemplate template;


    private String label = "test";


    @Autowired
    AreasService areasService;

    @Autowired
    PointsService pointsService;

    @Autowired
    StationsService stationsService;

    @Autowired
    CommissionsService commissionsService;

    @Before
    public void setUp() throws Exception {
        this.base = "http://localhost:" + port + "/areas";
        template = new TestRestTemplate();
    }


    @Test
    public void no_station() throws Exception {
        areaPriceUrl = this.base+ "/price";
        long stationId = 11111111;
        String orderCode = "1212121";
        double lantitude = 23423.12;
        double longitude = 23.12;
        Map params = new HashMap();

        params.put("station_id", stationId);
        params.put("order_code",orderCode);
        params.put("lantitude",lantitude);
        params.put("longitude",longitude);
        String allUrl = areaPriceUrl + "?"+ "station_id=" + stationId +
                "&order_code=" + orderCode +
                "&lantitude=" + lantitude +
                "&longitude=" + longitude;
        ResponseEntity<String> response = template.getForEntity(allUrl, String.class);
        String result = response.getBody().toString();

        AreaResult areaResult = JSON.parseObject(result, AreaResult.class);

        assertEquals(1, areaResult.getStatus());
        assertThat(-1d, comparesEqualTo(areaResult.getPrice()));

        response.getStatusCode();
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }


    @Test
    public void no_areas() throws Exception {
        areaPriceUrl = this.base+ "/price";

        long stationId = createStation();
        //createArea(stationId);

        String orderCode = "1212121";
        double lantitude = 23423.12;
        double longitude = 23.12;

        String allUrl = areaPriceUrl + "?"+ "station_id=" + stationId +
                "&order_code=" + orderCode +
                "&lantitude=" + lantitude +
                "&longitude=" + longitude;
        ResponseEntity<String> response = template.getForEntity(allUrl, String.class);
        String result = response.getBody().toString();

        //remove
        removeStation(stationId);
        removeArea(stationId);

        //assert
        AreaResult areaResult = JSON.parseObject(result, AreaResult.class);
        assertEquals(2, areaResult.getStatus());
        assertThat(-1d, comparesEqualTo(areaResult.getPrice()));
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

    }

    @Test
    public void area_in() throws Exception {
        areaPriceUrl = this.base+ "/price";

        long stationId = createStation();
        long areaId = createArea(stationId,0);
        saveAreaPoints(areaId);

        String orderCode = "1212121";
        double lantitude = 39.753962;
        double longitude = 116.329222;

        String allUrl = areaPriceUrl + "?"+ "station_id=" + stationId +
                "&order_code=" + orderCode +
                "&lantitude=" + lantitude +
                "&longitude=" + longitude;
        ResponseEntity<String> response = template.getForEntity(allUrl, String.class);
        String result = response.getBody().toString();

        //remove
        removeStation(stationId);
        deleteAreaPoints(areaId);
        removeArea(stationId);

        //assert
        AreaResult areaResult = JSON.parseObject(result, AreaResult.class);

        assertEquals(0, areaResult.getStatus());
        assertThat(0.0d, comparesEqualTo(areaResult.getPrice()));
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));


    }


    @Test
    public void area_in_with_price() throws Exception {
        areaPriceUrl = this.base+ "/price";

        long stationId = createStation();
        Commission commission = createCommission();
        long areaId = createArea(stationId,commission.getId());
        saveAreaPoints(areaId);

        String orderCode = "1212121";
        double lantitude = 39.753962;
        double longitude = 116.329222;

        String allUrl = areaPriceUrl + "?"+ "station_id=" + stationId +
                "&order_code=" + orderCode +
                "&lantitude=" + lantitude +
                "&longitude=" + longitude;
        ResponseEntity<String> response = template.getForEntity(allUrl, String.class);
        String result = response.getBody().toString();

        //remove
        removeStation(stationId);
        deleteAreaPoints(areaId);
        removeArea(stationId);
        deleteCommission(commission.getId());

        //assert
        AreaResult areaResult = JSON.parseObject(result, AreaResult.class);
        assertEquals(0, areaResult.getStatus());
        assertThat(commission.getPrice(), comparesEqualTo(areaResult.getPrice()));
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));


    }

    private long createStation(){
        Station station = new Station();
        station.setName("aaaa");
        station.setAddress("adfadf");
        stationsService.save(station);
        return station.getId();
    }

    private void removeStation(long id){
        stationsService.delete(id);
    }

    private long createArea(long stationId, long commissionId){
        Area a = new Area();
        a.setLabel(label);
        a.setLatitude(39.7566120000);
        a.setLongitude(116.3512665000);
        a.setCommissionId(commissionId);
        a.setStationId(stationId);
        areasService.save(a);
        return a.getId();
    }

    private Commission createCommission(){
        Commission commission = new Commission();
        commission.setPrice(7.3);
        commission.setName("aa");

        commissionsService.save(commission);

        return commission;
    }

    private void createAdditionalArea(long stationId){

        Area a = new Area();
        a.setLabel(label);
        a.setLatitude(39.6125160000);
        a.setLongitude(116.3670855000);
        a.setStationId(stationId);
        int result = areasService.save(a);

        Area a1 = new Area();
        a1.setLabel(label);
        a1.setLatitude(39.7603335000);
        a1.setLongitude(116.3334395000);
        a1.setStationId(stationId);
        int result1 = areasService.save(a);
    }

    private void removeArea(long stationId){
        List<Area> areas = areasService.findStationAreas(stationId);
        for(Area area : areas){
            int result = areasService.delete(area.getId());
            MatcherAssert.assertThat(result, greaterThan(0));
        }
    }
    private void saveAreaPoints(long areaId){

        double lats[] = {39.811735,39.805638,39.800428,39.799097,39.787344,39.787344, 39.776476,
                39.775921,39.746081,39.746192,39.747412,39.74142,39.730323,39.728436, 39.727326,
                39.730684,39.728242, 39.722915,39.720251,39.717254,39.716699,39.714256,39.711592,
                39.71026,39.709594,39.709261,39.706596,39.706596,39.706263,39.704043,39.702155,39.701711,
                39.701156,39.701933,39.732321,39.732321,39.76006,39.76006,39.798654,39.798543,
                39.798543,39.800206,39.801536,39.800538,39.797656,39.798986,39.796991,39.797656,39.793553,
                39.807412,39.809296,39.812068,39.812068,39.811846,39.81107,39.81107,39.811957,39.812068};

        double lngs[] = {116.34929,116.373868,116.375161,116.384073,116.385797,
                116.397008,116.39787,116.418136,116.422736,116.423023,116.441852,
                116.442858,116.449757,116.42446,116.397583,116.449793,116.395176,
                116.383965,116.378072,116.374622,116.374766,116.368729,116.362837,116.355363,
                116.345158,116.337971,116.328054,116.323167,116.318712,116.306782,116.295284,
                116.279186,116.26855,116.25274,116.259316,116.259316,116.258885,116.258885,116.257735,
                116.285618,116.285187,116.290792,116.292086,116.297404,116.300854,116.308902,116.31034,
                116.311489,116.317957,116.341385,116.344116,116.349146,116.349146,
                116.348859,116.347134,116.347134,116.348859,116.349146};

        for(int i=0; i<lats.length; i++) {
            Point p = new Point();

            p.setPointableId(areaId);
            p.setPointableType("Area");
            p.setLantitude(lats[i]);
            p.setLongitude(lngs[i]);
            pointsService.save(p);
        }
    }

    public void deleteAreaPoints(long areaId){
        pointsService.deleteByPointableId(areaId);
    }

    public void deleteCommission(long id){
        commissionsService.delete(id);
    }
}
