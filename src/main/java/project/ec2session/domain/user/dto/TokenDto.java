package project.ec2session.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인증 토큰 반환 객체")
public record TokenDto(
        @Schema(description = "JWT 액세스 토큰 값")
        String accessToken
) {
    public static TokenDto of(String accessToken) {
        return new TokenDto(accessToken);
    }
}