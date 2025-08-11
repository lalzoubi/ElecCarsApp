package com.eleccars.ElecCarsApp.controller.stationsControllers;


import com.eleccars.ElecCarsApp.dto.stationsDTOs.StationsInfoDto;
import com.eleccars.ElecCarsApp.model.stationsModels.StationInfo;
import com.eleccars.ElecCarsApp.service.stationsServices.StationsService;
import com.eleccars.ElecCarsApp.types.ApiCallResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/stations")
public class StationsController {

    @Autowired
    StationsService stationsService;


    @PostMapping("/registerStation")
    public ResponseEntity<Object> registerStation(@RequestBody StationInfo info) {
        stationsService.saveStationDetails(info);
        return ApiCallResponse.generateResponse(1, "تم تسجيل المحطة بنجاح", "Station created successfully", HttpStatus.OK, info);
    }

    @PostMapping("/updateStation")
    public ResponseEntity<Object> updateStation(@RequestBody StationInfo info) {
        stationsService.saveStationDetails(info);
        return ApiCallResponse.generateResponse(1, "تم تعديل المحطة بنجاح", "Station Updated successfully", HttpStatus.OK, info);
    }

    @DeleteMapping("/deleteStation")
    public ResponseEntity<Object> deleteStation(@RequestParam ("stationId") Long stationId) {
        stationsService.deleteStationById(stationId);
        return ApiCallResponse.generateResponse(1, "تم حذف المحطة بنجاح", "Station Deleted successfully", HttpStatus.OK, null);
    }

    @GetMapping("/reactiveStation")
    public ResponseEntity<Object> reactiveStation(@RequestParam ("stationId") Long stationId) {
        stationsService.reactiveStationById(stationId);
        return ApiCallResponse.generateResponse(1, "تم اعادة تفعيل المحطة بنجاح", "Station Reactivated successfully", HttpStatus.OK, null);
    }

    @GetMapping("/getStationById")
    public ResponseEntity<?> getStationById (@RequestParam ("stationId") Long stationId){
        StationsInfoDto stationInfoResponse = stationsService.findStationById(stationId);
        if (stationInfoResponse != null)
            return ApiCallResponse.generateResponse(1, "تم استرجاع البيانات بنجاح", "The Data retrieved successfully", HttpStatus.OK, stationInfoResponse);
        else
            return ApiCallResponse.generateResponse(0, "المحطة غير موجودة", "Station not found", HttpStatus.OK, stationInfoResponse);


    }

    @GetMapping("/getAllStations")
    public ResponseEntity<?> getAllStations (@RequestParam ("pageNum") int pageNum, @RequestParam ("pageSize") int pageSize
            , @RequestParam ("sortCol")String sortCol, @RequestParam ("isAsc") Boolean isAsc){
        Page<StationsInfoDto> stationInfoResponse = stationsService.getAllStations(pageNum, pageSize, sortCol, isAsc);
        if (stationInfoResponse != null)
            return ApiCallResponse.generateResponse(1, "تم استرجاع البيانات بنجاح", "The Data retrieved successfully", HttpStatus.OK, stationInfoResponse);
        else
            return ApiCallResponse.generateResponse(0, "لا توجد محطات مسجلة بعد", "There are no stations registered yet", HttpStatus.OK, stationInfoResponse);


    }

}
