package test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Main {


    private static String secret = "]ytfguARBG/*- [\1641242.73757*-+hfiuhoixbreszkbvhgszkbdx";
    private static final String issuer = "Fouad";
    private static Algorithm algorithm;
    private static JWTVerifier verifier;

    public static void init() {
        algorithm = Algorithm.HMAC256(secret);
        verifier = JWT.require(algorithm)
                // specify any specific claim validations
                .withIssuer(issuer)
                // reusable verifier instance
                .build();
    }

    public static String generateToken(int userId) {
        String token;
        try {
            token = JWT.create()
                    .withIssuer(issuer)
                    .withClaim("userid", userId)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
        return token;
    }


    public static DecodedJWT verify(String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return null; // s invalid token
        }
    }

    public static void main(String[] args) {
        init();

        String t = generateToken(54);
        System.out.println("token is " + t);


        var jwt = verify(t);
        int id = jwt.getClaim("userid").asInt();

        System.out.println("userid = " + id);
    }
}