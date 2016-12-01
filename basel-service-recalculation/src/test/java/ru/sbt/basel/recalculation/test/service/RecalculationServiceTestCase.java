package ru.sbt.basel.recalculation.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sbt.basel.service.recalculation.service.RecalculationService;

import ru.sbt.basel.recalculation.test.AccountServiceApplicationTest;

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
