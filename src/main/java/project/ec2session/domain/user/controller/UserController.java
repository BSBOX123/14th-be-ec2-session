package project.ec2session.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.ec2session.common.auth.CustomUserDetails;
import project.ec2session.domain.user.dto.UserReq;
import project.ec2session.domain.user.dto.UserRes;
import project.ec2session.domain.user.service.UserService;

@Tag(name = "2. 유저 관리 API (User)", description = "회원 정보 조회 및 수정을 담당하는 컨트롤러입니다.")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "내 정보 조회", description = "현재 헤더에 입력된 인증 토큰 정보를 기반으로 로그인된 유저 본인의 상세 정보를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(userService.readById(userDetails.getUserId()));
    }

    @Operation(summary = "전체 회원 목록 조회", description = "시스템에 가입된 모든 유저 목록을 배열 데이터로 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.readAll());
    }

    @Operation(summary = "내 정보(닉네임) 수정", description = "현재 로그인한 유저의 닉네임 정보를 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "수정 완료")
    @PutMapping
    public ResponseEntity<?> updateUserInfo(@Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails,
                                            @RequestBody @Valid UserReq.UpdateInfo request) {
        userService.update(userDetails.getUserId(), request);
        return ResponseEntity.ok("요청 성공");
    }
}