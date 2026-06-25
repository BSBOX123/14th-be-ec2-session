package project.ec2session.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import project.ec2session.domain.user.entity.User;

@Schema(description = "유저 정보 응답 객체")
public record UserRes(
        @Schema(description = "유저 고유 고유 PK ID", example = "1")
        Long userId,
        @Schema(description = "유저 로그인 아이디", example = "likelion123")
        String username,
        @Schema(description = "유저 닉네임", example = "아기사자")
        String nickname
) {
    public static UserRes from(User user) {
        return new UserRes(user.getId(), user.getUsername(), user.getNickname());
    }
}