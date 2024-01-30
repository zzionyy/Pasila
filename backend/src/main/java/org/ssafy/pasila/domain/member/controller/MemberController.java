package org.ssafy.pasila.domain.member.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.member.dto.request.PersonalInfoRequest;
import org.ssafy.pasila.domain.member.dto.response.ChannelResponse;
import org.ssafy.pasila.domain.member.dto.response.PersonalInfoResponse;
import org.ssafy.pasila.domain.member.repository.ChannelRepository;
import org.ssafy.pasila.domain.member.repository.PersonalInfoRepository;
import org.ssafy.pasila.domain.member.service.MemberService;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@Tag(name = "Member", description = "Member API")
public class MemberController {

    private final PersonalInfoRepository personalInfoRepository;
    private final ChannelRepository channelRepository;
    private final MemberService memberService;

    // 마이페이지(personal-info)

    // 회원 정보 조회 by id
    @Operation(summary = "get member by id", description = "마이페이지 - id로 회원 정보 조회")
    @GetMapping("/personal-info/{id}")
//    public ResponseEntity<?> getMember(@PathVariable("id") Long id) {
//        return response.handleSuccess(200, personalInfoRepository.findById(id));
//    }
    public Optional<PersonalInfoResponse> getMember(@PathVariable("id") Long id){
        return Optional.ofNullable(personalInfoRepository.findById(id));
    }

    // 회원 정보 수정
    @Operation(summary = "update member", description = "마이페이지 - 회원 정보 수정")
    @PutMapping("/member/{id}")
//    public ResponseEntity<?> updateMember(@PathVariable("id") Long id,
//                                          @RequestPart(value = "pr") PersonalInfoRequest request,
//                                          @RequestPart(value = "new_image", required = false) MultipartFile newImageName) {
//        try {
//            memberService.updateMember(id, request, newImageName);
//        } catch (Exception e) {
//            return response.exceptionHandler(500, e);
//        }
//        return response.handleSuccess(200, "success");
//    }
    public ResponseEntity<String> updateMember(@PathVariable("id") Long id,
                                               @RequestPart(value = "pr") PersonalInfoRequest request,
                                               @RequestPart(value = "new_image", required = false) MultipartFile newImageName) {
        try {
            memberService.updateMember(id, request, newImageName);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    // 채널(Channel)

    @Operation(summary = "get channel by id", description = "채널 - id로 채널 정보 조회")
    @GetMapping("/member/channel/{id}")
//    public ResponseEntity<?> getChannel(@PathVariable("id") Long id) {
//        return response.handleSuccess(200, channelRepository.findById(id));
//    }
    public Optional<ChannelResponse> getChannel(@PathVariable("id") Long id) {
        return Optional.ofNullable(channelRepository.findById(id));
    }
}
