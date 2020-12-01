package cn.sabercon.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * JwtToken 操作的工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Jwt {

    /**
     * 加密密钥, 可通过配置文件配置
     */
    private static String secret;

    static {
        // 设置好 jwt 加密密钥
        ContextHolder.addCallBack(() -> secret = Assert.notNull(ContextHolder.getProperty("sabercon.jwt-key"),
                "the property sabercon.jwt-key must not be null"));
    }

    /**
     * 根据负载生成 token
     */
    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 根据用户 id 生成 token
     *
     * @param expiredDate 过期时间
     */
    public static String generateTokenById(Long id, Date expiredDate) {
        var claims = Jwts.claims().setId(id.toString()).setExpiration(expiredDate);
        return generateToken(claims);
    }

    /**
     * 从 token 中获取负载
     */
    private static Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 从 token 中获取登录用户 id，过期或失败时返回 null
     */
    public static Long getIdFromToken(String token) {
        var claims = getClaimsFromToken(token);
        return claims.getExpiration().after(new Date()) ? Long.valueOf(claims.getId()) : null;
    }
}
