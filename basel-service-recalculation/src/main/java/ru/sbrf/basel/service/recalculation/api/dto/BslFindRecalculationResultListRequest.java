package ru.sbrf.basel.service.recalculation.api.dto;

import java.util.Date;

import ru.sbrf.basel.service.api.common.dto.FindPageRequestDto;

public class BslFindRecalculationResultListRequest
{

    private String calculationDetail;

    private Date dateFrom;

    private Date dateTo;

    private FindPageRequestDto pageRequest = new FindPageRequestDto();

    public String getCalculationDetail()
    {
        return calculationDetail;
    }

    public void setCalculationDetail(String calculationDetail)
    {
        this.calculationDetail = calculationDetail;
    }

    public Date getDateFrom()
    {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom)
    {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo()
    {
        return dateTo;
    }

    public void setDateTo(Date dateTo)
    {
        this.dateTo = dateTo;
    }

    public FindPageRequestDto getPageRequest()
    {
        return pageRequest;
    }

    public void setPageRequest(FindPageRequestDto pageRequest)
    {
        this.pageRequest = pageRequest;
    }

}
