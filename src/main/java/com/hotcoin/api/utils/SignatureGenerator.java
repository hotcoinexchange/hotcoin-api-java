package com.hotcoin.api.utils;

import com.hotcoin.api.config.APIConfiguration;
import com.hotcoin.api.constant.ApiConstants;
import com.hotcoin.api.exceptions.HotcoinApiException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Signature Generator
 * 签名生成工具类
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class SignatureGenerator {

    private static final String ACCESS_KEY_ID = "AccessKeyId";

    private static final String SIGNATURE_METHOD = "SignatureMethod";
    private static final String HMACSHA_256 = "HmacSHA256";
    private static final String SIGNATURE_VERSION = "SignatureVersion";
    private static final String SIGNATURE_VERSION_2 = "2";
    private static final String TIMESTAMP = "Timestamp";
    private static final String SIGNATURE = "Signature";

    private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private static final ZoneId ZONE_GMT = ZoneId.of("Z");

    /**
     * 生成签名
     *
     * @param config
     * @param method
     * @param uri
     * @param builder
     */
    public static void createSignature(APIConfiguration config, String method,
                                       String uri, UrlBuilder builder) {
        StringBuilder sb = new StringBuilder(1024);

        if (StringUtils.isBlank(config.getAccessKey()) || StringUtils.isBlank(config.getSecretKey())) {
            throw new HotcoinApiException(HotcoinApiException.APIKEY_ERROR,
                    "API key and secret key are required");
        }

        sb.append(method.toUpperCase()).append(ApiConstants.LINE_BREAK)
                .append(ApiConstants.SIGN_HOST.toLowerCase()).append(ApiConstants.LINE_BREAK)
                .append(uri).append(ApiConstants.LINE_BREAK);

        builder.putToUrl(ACCESS_KEY_ID, config.getAccessKey())
                .putToUrl(SIGNATURE_METHOD, HMACSHA_256)
                .putToUrl(SIGNATURE_VERSION, SIGNATURE_VERSION_2)
                .putToUrl(TIMESTAMP, gmtUTCTime());

        sb.append(builder.buildSignature());
        byte[] hmacSha256;
        try {
            SecretKeySpec secKey = new SecretKeySpec(config.getSecretKey().getBytes(StandardCharsets.UTF_8), HMACSHA_256);
            Mac mac = Mac.getInstance(HMACSHA_256);
            mac.init(secKey);
            hmacSha256 = mac.doFinal(sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new HotcoinApiException(HotcoinApiException.RUNTIME_ERROR,
                    "[Signature] No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new HotcoinApiException(HotcoinApiException.RUNTIME_ERROR,
                    "[Signature] Invalid key: " + e.getMessage());
        }
        // 替换
        builder.putToUrl(SIGNATURE, Base64.encodeBase64String(hmacSha256).replace(ApiConstants.LINE_BREAK, ApiConstants.EMPTY));
    }

    /**
     * 生成UTC时间
     *
     * @return
     */
    private static String gmtUTCTime() {
        return Instant.now().atZone(ZONE_GMT).format(DT_FORMAT);
    }

}
