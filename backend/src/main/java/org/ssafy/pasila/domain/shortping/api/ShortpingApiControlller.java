package org.ssafy.pasila.domain.shortping.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.shortping.dto.request.ShortpingRequestDto;
import org.ssafy.pasila.domain.shortping.dto.response.LivelogResponseDto;
import org.ssafy.pasila.domain.shortping.dto.response.RecommendLivelogResponseDto;
import org.ssafy.pasila.domain.shortping.dto.response.ShortpingResponseDto;
import org.ssafy.pasila.domain.shortping.service.LivelogService;
import org.ssafy.pasila.domain.shortping.service.ShortpingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Shortping", description = "Shortping API")
public class ShortpingApiControlller {

    private final ShortpingService shortpingService;

    private final LivelogService livelogService;

    @Operation(summary = "Create Shortping", description = "숏핑을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    @PostMapping(value = "/api/shortping", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiCommonResponse<?> shortpingList(@RequestPart(value = "video", required = false) MultipartFile video, @RequestPart ShortpingRequestDto shortpingRequest) {
        shortpingService.saveShortping(shortpingRequest, video);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), "");
    }

    @Operation(summary = "Get Shortping Detail", description = "Id에 해당하는 숏핑 상세 정보를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShortpingResponseDto.class))})
    })
    @GetMapping("/api/shortping/{id}")
    public ApiCommonResponse<?> shortpingDetail(@PathVariable String id) {

        ShortpingResponseDto shortpingResponse = shortpingService.getShortpingById(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), shortpingResponse);

    }

    @Operation(summary = "Get Highlight List", description = "Live ID에 해당하는 하이라이트 리스트를 가져옵니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RecommendLivelogResponseDto.class)))})
    })
    @GetMapping("/api/shortping/highlight")
    public ApiCommonResponse<?> getLivelogList(@RequestParam(value = "live_id") String liveId) {

        List<LivelogResponseDto> livelogResponseDtoList = livelogService.getLivelogListByLiveId(liveId);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), livelogResponseDtoList);

    }

    @Operation(summary = "Delete Shortping", description = "Id에 해당하는 숏핑을 삭제합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/api/shortping/{id}")
    public ApiCommonResponse<?> getHighlight(@PathVariable String id) {

        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), "success");

    }

}
