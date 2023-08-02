package com.example.annualleavemodule.service;

import com.example.annualleavemodule.bean.AnnualLeaveCreateRequest;
import com.example.annualleavemodule.bean.AnnualLeaveCreateResponse;
import com.example.annualleavemodule.entity.AnnualLeaveRequest;
import com.example.annualleavemodule.exception.AnnualLeaveLeftException;
import com.example.annualleavemodule.repository.IAnnualLeaveRepository;
import com.example.annualleavemodule.repository.IAnnualLeaveRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnnualLeaveServiceImplTest {

    @Mock
    private IAnnualLeaveRequestRepository iAnnualLeaveRequestRepository;

    @Mock
    private IAnnualLeaveRepository iAnnualLeaveRepository;

    @InjectMocks
    private AnnualLeaveServiceImpl annualLeaveService;

    @Test
    public void testCreateAnnualLeaveRequest_ValidDate_ReturnsResponse() throws AnnualLeaveLeftException {
        // Arrange
        AnnualLeaveCreateRequest request = new AnnualLeaveCreateRequest();
        request.setStartDate(LocalDate.of(2023, 7, 20));
        request.setEndDate(LocalDate.of(2023, 7, 30));
        Long employeeId = 123L;

        when(iAnnualLeaveRepository.getAnnualLeaveLeft(anyLong())).thenReturn(15);


        AnnualLeaveCreateResponse response = annualLeaveService.createAnnualLeaveRequest(request, employeeId);


        assertNotNull(response);

    }

    @Test
    public void testCreateAnnualLeaveRequest_InvalidDate_ThrowsException() {
        AnnualLeaveCreateRequest request = new AnnualLeaveCreateRequest();
        request.setStartDate(LocalDate.of(2023, 7, 20));
        request.setEndDate(LocalDate.of(2023, 8, 20));
        Long employeeId = 123L;

        when(iAnnualLeaveRepository.getAnnualLeaveLeft(eq(employeeId))).thenReturn(15);


        assertThrows(AnnualLeaveLeftException.class, () -> {
            annualLeaveService.createAnnualLeaveRequest(request, employeeId);
        });
    }

    @Test
    public void testGetPendingRequests_ReturnsList() {

        List<AnnualLeaveRequest> expectedRequests = new ArrayList<>();


        when(iAnnualLeaveRequestRepository.getPendingRequests()).thenReturn(expectedRequests);


        List<AnnualLeaveRequest> actualRequests = annualLeaveService.getPendingRequests();


        assertNotNull(actualRequests);
        assertEquals(expectedRequests.size(), actualRequests.size());

    }
}