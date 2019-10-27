package org.test.springsandbox.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.springsandbox.service.ManyThingsService;
import org.test.springsandbox.web.dto.ManyThingsDTO;

@Slf4j
@RestController
@RequestMapping("/english")
@RequiredArgsConstructor
public class ManyThingsController {

    private final ManyThingsService manyThingsService;

    @PostMapping("/manythings")
    public void downloadFromManyThings(@RequestBody ManyThingsDTO pageDTO) {
        manyThingsService.downloadAll(pageDTO.getPageUrl(), pageDTO.getArticle());
    }
}
