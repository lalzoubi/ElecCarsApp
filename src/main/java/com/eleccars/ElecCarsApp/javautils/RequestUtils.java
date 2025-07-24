package com.eleccars.ElecCarsApp.javautils;

import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;

public class RequestUtils {

    // دالة تجيب الـ IP الحقيقي للمستخدم
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    // دالة تجيب الـ User-Agent
    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    // دالة تحدد نوع الجهاز
    public static String getDeviceType(HttpServletRequest request) {
        String userAgent = getUserAgent(request).toLowerCase();
        if (userAgent.contains("mobi")) {
            return "Mobile";
        } else if (userAgent.contains("tablet") || userAgent.contains("ipad")) {
            return "Tablet";
        } else {
            return "Desktop";
        }
    }

    public static String getDeviceDetails(HttpServletRequest request) {
        String userAgentString = getUserAgent(request);
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);

        OperatingSystem os = userAgent.getOperatingSystem(); // نظام التشغيل
        DeviceType deviceType = os.getDeviceType(); // نوع الجهاز
        String browser = userAgent.getBrowser().getName(); // المتصفح

        return "DeviceType: " + deviceType + ", OS: " + os.getName() + ", Browser: " + browser;
    }
}
