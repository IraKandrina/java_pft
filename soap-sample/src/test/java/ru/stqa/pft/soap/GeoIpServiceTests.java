package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.77.106.172");
        assertEquals(geoIp, "<GeoIP><Country>RU</Country><State>70</State></GeoIP>");
    }
}
