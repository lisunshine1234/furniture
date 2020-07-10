package com.lzy.furniture.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Subdivision;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;

public class IpWork {
    private static File database;
    private static DatabaseReader reader;

    static {
        try {
            database = ResourceUtils.getFile("classpath:static/admin/assets/Widget/GeoLite2/GeoLite2-City.mmdb");
            reader = new DatabaseReader.Builder(database).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIpCity(String ip) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            try {
                CityResponse response = reader.city(inetAddress);
                Subdivision subdivision = response.getMostSpecificSubdivision();

                return subdivision.getNames().get("zh-CN");
            } catch (Exception e) {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
