package com.mungwincore.transcore.utils;


import com.mungwincore.transcore.security.utils.SimpleHttpHeader;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

public class HttpUtils {
  private static String baseUrl;
  private static String lang;
  public static HttpHeaders createAuthHeader(String username, String password) {
    return new HttpHeaders(){{
      String auth = username + ":" + password;
      byte[] encodeAuth = Base64.encodeBase64(
        auth.getBytes(StandardCharsets.US_ASCII)
      );
      String authHeader = "Basic " + new String(encodeAuth);
      set("Authorization", authHeader);
    }};
  }

  public static SimpleHttpHeader createBasicAuthHeader(String username, String password) {
    String auth = username + ":" + password;
    byte[] encodeAuth = Base64.encodeBase64(
      auth.getBytes(StandardCharsets.US_ASCII)
    );
    String authHeader = "Basic " + new String(encodeAuth);
    return new SimpleHttpHeader("Authorization", authHeader);
  }

  public static String getBaseUrl() {
    return baseUrl;
  }

  public static void setBaseUrl(String protocol, String serverName, Integer port) {
    HttpUtils.baseUrl =  port == null || port == 0 ? protocol + "://" + serverName : protocol + "://" + serverName + ":" + port;;
  }
  public static void setBaseUrl(String protocol, String serverName) {
    HttpUtils.baseUrl = protocol + "://" + serverName;
  }

  public static String getLogoUrl() {
    return baseUrl + "/logo-mini.png";
  }
  public static void setLang(String lang) {
    HttpUtils.lang = lang;
  }
  public static String getLang(){return (lang == null ||  lang.isEmpty()) ? "en" : lang;}
  public static String getHeaderValue(HttpServletRequest request, String val) {
    Enumeration<?> e = request.getHeaderNames();
    if (e != null) {
      while (e.hasMoreElements()) {
        String curr = (String) e.nextElement();
        if (curr.equals(val)) {
          return request.getHeader(curr);
        }
      }
    }
    return null;
  }
}
