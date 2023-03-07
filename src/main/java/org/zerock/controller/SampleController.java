package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {

    //단순 문자열 반환
    @GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
    public String getText() {
        log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);

        return "안녕하세요";
    }

    //객체 반환
    @GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SampleVO getSample() {
        return new SampleVO(112, "스타", "로드");
    }

    //@GetMapping이나 @RequestMapping의 produces 속성은 반드시 지정해야하는 것은 아니므로 생략 가능.
    @GetMapping(value = "/getSample2")
    public SampleVO getSample2() {
        return new SampleVO(113, "로켓", "라쿤");
    }

    //컬렉션 타입의 객체 반환
    @GetMapping(value = "/getList")
    public List<SampleVO> getList() {
        return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "First", i + " Last")).collect(Collectors.toList());
    }

    //Map을 이용
    @GetMapping(value = "/getMap")
    public Map<String, SampleVO> getMap() {
        Map<String, SampleVO> map = new HashMap<>();
        map.put("First", new SampleVO(111, "그루트", "주니어"));
        return map;
    }

    //ResponseEntity 타입: 데이터 + HTTP헤더의 상태메시지
    // /sample/check.json?height=140&weight=60
    @GetMapping(value = "/check", params = {"height", "weight"})
    public ResponseEntity<SampleVO> check(Double height, Double weight) {
        SampleVO vo = new SampleVO(0, "" + height, "" + weight);

        ResponseEntity<SampleVO> result = null;

        if (height < 150) {
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        } else {
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        }

        return result;
    }

}
