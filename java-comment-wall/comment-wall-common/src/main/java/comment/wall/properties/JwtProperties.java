package comment.wall.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "comment.wall.jwt")
public class JwtProperties {
	private String secretKey;
	private long ttl;
	private String tokenName;
}
