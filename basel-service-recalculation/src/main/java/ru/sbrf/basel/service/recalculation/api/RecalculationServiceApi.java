package ru.sbrf.basel.service.recalculation.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.sbrf.basel.service.recalculation.api.dto.BslFindRecalculationResultListRequest;
import ru.sbrf.basel.service.recalculation.api.dto.BslFindRecalculationResultListResponse;

@RequestMapping("/recalculation")
public interface RecalculationServiceApi
{

    @RequestMapping(path = "/findRecalculationResultList", method = RequestMethod.POST)
    public BslFindRecalculationResultListResponse findRecalculationResultList(@RequestBody BslFindRecalculationResultListRequest bslfindRecalculationResultListRequest);

}
