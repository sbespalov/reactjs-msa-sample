package ru.sbrf.basel.service.recalculation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.sbrf.basel.service.api.common.dto.FindPageRequestDto;
import ru.sbrf.basel.service.api.common.dto.FindPageResponseDto;
import ru.sbrf.basel.service.recalculation.api.RecalculationServiceApi;
import ru.sbrf.basel.service.recalculation.api.dto.BslFindRecalculationResultListRequest;
import ru.sbrf.basel.service.recalculation.api.dto.BslFindRecalculationResultListResponse;
import ru.sbrf.basel.service.recalculation.domain.RecalculationResultDto;
import ru.sbrf.basel.service.recalculation.service.RecalculationService;

@RestController
public class RecalculationController implements RecalculationServiceApi
{

    @Autowired
    private RecalculationService recalculationService;

    @CrossOrigin
    @Override
    public BslFindRecalculationResultListResponse findRecalculationResultList(BslFindRecalculationResultListRequest bslfindRecalculationResultListRequest)
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        BslFindRecalculationResultListResponse result = new BslFindRecalculationResultListResponse();

        List<RecalculationResultDto> recalculationResultList = recalculationService.findRecalculationResultList();
        result.setRecalculationResultList(recalculationResultList);

        FindPageResponseDto pageResponse = new FindPageResponseDto();
        result.setPageResponse(pageResponse);

        FindPageRequestDto pageRequest = bslfindRecalculationResultListRequest.getPageRequest();

        pageResponse.setPageNumber(pageRequest.getPageNumber());
        pageResponse.setPageSize(pageRequest.getPageSize());
        pageResponse.setTotalCount(1000L);

        return result;
    }

}
