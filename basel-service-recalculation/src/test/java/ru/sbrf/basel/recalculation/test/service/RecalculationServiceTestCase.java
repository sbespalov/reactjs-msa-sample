package ru.sbrf.basel.recalculation.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.sbrf.basel.recalculation.test.AccountServiceApplicationTest;
import ru.sbrf.basel.service.recalculation.service.RecalculationService;

public class RecalculationServiceTestCase extends AccountServiceApplicationTest
{

    @Autowired
    private RecalculationService recalculationService;

    @Test
    public void test()
    {
        recalculationService.findRecalculationResultList();
    }

}
