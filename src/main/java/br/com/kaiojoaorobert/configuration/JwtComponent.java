package br.com.kaiojoaorobert.configuration;

import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtComponent {

	@Value("${jwt.secretkey}")
	private String jwtSecretkey;

	public UUID getUser(HttpServletRequest request) {
		try {
			var authorization = request.getHeader("Authorization");
			var token = authorization.replace("Bearer", "").trim();
			return UUID.fromString(getClaimFromToken(token, Claims::getSubject));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parser().setSigningKey(jwtSecretkey).parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}
}