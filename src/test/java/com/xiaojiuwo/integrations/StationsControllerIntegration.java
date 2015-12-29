package com.xiaojiuwo.integrations;

import com.xiaojiuwo.Application;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by liuhaibao on 15/10/22.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class StationsControllerIntegration {

    @Value("${local.server.port}")
    private int port;

    private String base;
    private String stationPointsUrl;
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = "http://localhost:" + port + "/stations";
        template = new TestRestTemplate();
    }


    @Test
    public void stations() throws Exception {

        ResponseEntity<String> response = template.getForEntity(this.base, String.class);
        response.getStatusCode();
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void station_points() throws Exception {
        stationPointsUrl = this.base+ "/1"+"/points";

        ResponseEntity<String> response = template.getForEntity(stationPointsUrl, String.class);
        response.getStatusCode();
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
