package com.stclub.web.controller.common;

import com.stclub.activity.domain.ActActivity;
import com.stclub.activity.service.IActActivityService;
import com.stclub.club.domain.CluCorporation;
import com.stclub.club.service.ICluCorporationService;
import com.stclub.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author ljx
 * @Date 2022/4/1 23:16
 **/
@Component("timeTask")
public class TimeTask {

    @Autowired
    private ICluCorporationService iCluCorporationService;
    @Autowired
    private IActActivityService iActActivityService;

    public AjaxResult getNewNumber(){
        List<CluCorporation> newNumber = iCluCorporationService.getNewNumber();
        iCluCorporationService.updateBatchById(newNumber);
        List<ActActivity> list = iActActivityService.list();
        list.forEach(actActivity -> {
            if(actActivity.getStartTime().before(new Date()) && actActivity.getEndTime().after(new Date()))
                actActivity.setStatus(2);
            if (actActivity.getEndTime().before(new Date()))
                actActivity.setStatus(3);
        });
        boolean b = iActActivityService.updateBatchById(list);
        return AjaxResult.success();
    }


}
