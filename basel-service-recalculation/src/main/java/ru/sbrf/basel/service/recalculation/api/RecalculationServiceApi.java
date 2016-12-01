package ru.sbrf.basel.service.recalculation.api;

import ru.sbrf.basel.service.recalculation.api.dto.BslFindRecalculationResultListRequest;
import ru.sbrf.basel.service.recalculation.api.dto.BslFindRecalculationResultListResponse;

public interface RecalculationServiceApi
{

    public BslFindRecalculationResultListResponse findRecalculationResultList(
            BslFindRecalculationResultListRequest bslfindRecalculationResultListRequest);

}
