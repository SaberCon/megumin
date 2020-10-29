package cn.sabercon.main.util;

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
public class JwtUtils {

    /**
     * 加密密钥
     */
    private static final String SECRET = "SaberCon";

    /**
     * 根据负载生成 token
     */
    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
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
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    /**
     * 从 token 中获取登录用户 id，失败时返回 null
     */
    public static String getIdFromToken(String token) {
        try {
            return getClaimsFromToken(token).getId();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断 token 是否有效
     */
    public static boolean isTokenValid(String token) {
        try {
            return getClaimsFromToken(token).getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
