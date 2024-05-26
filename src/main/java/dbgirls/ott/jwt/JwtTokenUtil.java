package dbgirls.ott.jwt;

import dbgirls.ott.dto.JwtDto;

import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; //30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; //7일

    // JWT Token 발급
    public static JwtDto createToken(String id, String pw) {
        // Claim = Jwt Token에 들어갈 정보

        Claims claims = Jwts.claims();
        claims.put("id", id);

        String accessToken =  Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, pw)
                .compact();

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, pw)
                .compact();

        System.out.println("==================");
        System.out.println("accessToken  :  " + accessToken);
        System.out.println("refreshToken :  " + refreshToken);
        System.out.println("==================");

        return JwtDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
