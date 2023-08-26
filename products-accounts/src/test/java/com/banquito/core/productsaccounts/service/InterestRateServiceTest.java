package com.banquito.core.productsaccounts.service;

import com.banquito.core.productsaccounts.exception.CRUDException;
import com.banquito.core.productsaccounts.model.InterestRate;
import com.banquito.core.productsaccounts.repository.InterestRateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class InterestRateServiceTest {
    @Mock
    private InterestRateRepository repository;

    @InjectMocks
    private InterestRateService interestRateService;

    @DisplayName("listAllActives -- Success Scenario")
    @Test
    void Test_When_listAllActives_Success() {
        // Datos de prueba
        List<InterestRate> mockInterestRates = new ArrayList<>();
        InterestRate intere1=getMockInterestRate_1();
        InterestRate intere2=getMockInterestRate_2();
        mockInterestRates.add(intere1);
        mockInterestRates.add(intere2);

        // Configuración del comportamiento simulado del repositorio
        when(repository.findByState("ACT")).thenReturn(mockInterestRates);

        // Llamada al método del servicio
        List<InterestRate> result = interestRateService.listAllActives();

        // Verificación
        assertEquals(2, result.size());
        // Aquí podrías agregar más aserciones para verificar los valores devueltos en la lista resultante.

        // Verifica que el método repository.findByState("ACT") haya sido llamado una vez
        verify(repository, times(1)).findByState("ACT");
    }

    @DisplayName("listAllActives -- Failure Scenario")
    @Test
    void Test_When_listAllActives_Failure() {
        // Configuración del comportamiento simulado del repositorio para devolver una excepción
        when(repository.findByState("ACT")).thenThrow(new RuntimeException("Database connection error"));

        // Llamada al método del servicio que se espera que genere una excepción
        assertThrows(RuntimeException.class, () -> interestRateService.listAllActives());

        // Verifica que el método repository.findByState("ACT") haya sido llamado una vez
        verify(repository, times(1)).findByState("ACT");
    }

    @DisplayName("obtainById -- Success Scenario")
    @Test
    void Test_When_obtainById_Success() {
        // Datos de prueba
        InterestRate mockInterestRate = getMockInterestRate_1();


        // Configuración del comportamiento simulado del repositorio
        when(repository.findById(1)).thenReturn(Optional.of(mockInterestRate));

        // Llamada al método del servicio
        InterestRate result = interestRateService.obtainById(1);

        // Verificación
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Standard Rate", result.getName());
        assertEquals(BigDecimal.valueOf(0.03), result.getInterestRate());
        assertEquals("ACT", result.getState());
        assertEquals(new Date(), result.getStart());
        assertNull(result.getEnd());

        // Verifica que el método repository.findById(1) haya sido llamado una vez
        verify(repository, times(1)).findById(1);
    }

    @DisplayName("obtainById -- Success Scenario")
    @Test
    void Test_When_obtainById_Failure() {
    // Configuración del comportamiento simulado del repositorio para devolver Optional vacío
        when(repository.findById(1)).thenReturn(Optional.empty());

        // Llamada al método del servicio que se espera que genere una excepción
        assertThrows(CRUDException.class, () -> interestRateService.obtainById(1));

        // Verifica que el método repository.findById(1) haya sido llamado una vez
        verify(repository, times(1)).findById(1);
    }

    @DisplayName("create -- Success Scenario")
    @Test
    void Test_When_create_Success() {


    }

    @DisplayName("create -- Failure Scenario")
    @Test
    void Test_When_create_Failure() {


    }

    @DisplayName("update -- Failure Scenario")
    @Test
    void Test_When_update_Failure() {


    }

    @DisplayName("update -- Success Scenario")
    @Test
    void Test_When_update_Success() {


    }

    @DisplayName("inactivate -- Success Scenario")
    @Test
    void Test_When_inactivate_Success() {


    }

    @DisplayName("inactivate -- Failure Scenario")
    @Test
    void Test_When_inactivate_Failure() {


    }

    private InterestRate getMockInterestRate_1() {
        return InterestRate.builder()
                .id(1)
                .interestRate(BigDecimal.valueOf(0.03))
                .start(new Date())
                .end(null)
                .state("ACT").build();


    }

    private InterestRate getMockInterestRate_2() {
        return InterestRate.builder()
                .id(2)
                .interestRate(BigDecimal.valueOf(0.05))
                .start(new Date())
                .end(null)
                .state("ACT").build();
    }
}
