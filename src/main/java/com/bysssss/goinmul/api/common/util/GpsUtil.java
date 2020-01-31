package com.bysssss.goinmul.api.common.util;

public class GpsUtil {
	public static Double distanceKm(double srcLat, double srcLon, double tarLat, double tarLon) {
        Double distance = Math.sin(GpsUtil.deg2rad(srcLat)) * Math.sin(GpsUtil.deg2rad(tarLat)) + Math.cos(GpsUtil.deg2rad(srcLat)) * Math.cos(GpsUtil.deg2rad(tarLat)) * Math.cos(GpsUtil.deg2rad(srcLon - tarLon));
        distance = Math.acos(distance);
        distance = GpsUtil.rad2deg(distance);
        distance = distance * 60 * 1.1515;
        distance = distance * 1.609344; // Km
        
        return distance;
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
