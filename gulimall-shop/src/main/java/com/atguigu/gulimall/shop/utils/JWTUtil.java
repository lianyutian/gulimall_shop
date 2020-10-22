package com.atguigu.gulimall.shop.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author lm
 * @since 2020-10-15
 */
@Slf4j
public class JWTUtil {
    /**
     * 秘钥
     */
    private static String SECRETKEY;

    /**
     * 访问令牌过期时间
     */
    private static Duration ACCESS_TOKEN_EXPIRE_TIME;

    /**
     * 刷新令牌过期时间
     */
    private static Duration REFRESH_TOKEN_EXPIRE_TIME;

    /**
     * 刷新令牌过期应用时间
     */
    private static Duration REFRESH_TOKEN_EXPIRE_APP_TIME;

    public static void setTokenConfig(TokenConfig tokenConfig) {
        SECRETKEY = tokenConfig.getSecretKey();
        ACCESS_TOKEN_EXPIRE_TIME = tokenConfig.getAccessTokenExpireTime();
        REFRESH_TOKEN_EXPIRE_TIME = tokenConfig.getRefreshTokenExpireTime();
        REFRESH_TOKEN_EXPIRE_APP_TIME = tokenConfig.getRefreshTokenExpireAppTime();
    }

    /**
     * 生成token header.payload.sing
     */
    public static String getToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, ACCESS_TOKEN_EXPIRE_TIME.toMillis(), SECRETKEY);
    }

    /**
     * 生成token
     *
     * @param subject 代表这个jwt所面向的用户
     * @param claims jwt的载荷
     * @param ttlMillis 有效时间
     * @param secretKey 秘钥
     * @return token
     */
    private static String generateToken(String subject, Map<String, Object> claims, long ttlMillis, String secretKey) {
        JwtBuilder builder = Jwts.builder();
        // 设置header
        builder.setHeaderParam("typ", "JWT");
        if (!CollectionUtils.isEmpty(claims)) {
            builder.setClaims(claims);
        }
        if (!StringUtils.isEmpty(subject)) {
            builder.setSubject(subject);
        }
        long nowMillis = System.currentTimeMillis();
        // 设置token签发时间
        builder.setIssuedAt(new Date(nowMillis));
        if (ttlMillis >= 0) {
            // 设置token过期时间
            builder.setExpiration(new Date(nowMillis + ttlMillis));
        }
        // 加密算法
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        // 设置签名
        builder.signWith(algorithm, secretKey);
        return builder.compact(); // 开始压缩为xxxxx.yyyyy.zzzzz 格式的jwt token
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(token)).build().verify(token);
    }

    /**
     * 获取用户ID
     *
     * @param accessToken token信息
     * @return 用户ID
     */
    public static String getUserId(String accessToken) {
        String userId = null;
        try {
            Claims claims = getClaimsFromToken(accessToken);
            userId = claims.getSubject();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return userId;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token token
     * @return Claims
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRETKEY)).parseClaimsJws(token).getBody();
        } catch (Exception exception) {
            if (exception instanceof ClaimJwtException) {
                claims = ((ClaimJwtException) exception).getClaims();
            }
        }
        return claims;
    }

    /**
     * 判断token是否过期
     *
     * @param token token信息
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return true;
        }
    }

    /**
     * 校验令牌
     *
     * @param token token信息
     * @return 是否校验通过
     */
    public static boolean validateToken(String token) {
        Claims claimsFromToken = getClaimsFromToken(token);
        return (null != claimsFromToken && !isTokenExpired(token));
    }
}
