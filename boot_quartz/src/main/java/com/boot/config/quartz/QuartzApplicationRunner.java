package com.boot.config.quartz;

import com.boot.business.quartz.service.QuartzService;
import com.boot.business.quartz.vo.QuartzVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName QuartzApplicationRunner.java
 * @Author Xuhui
 * @Date 2022/8/2 10:51
 * @Description
 */
@Component
@Slf4j
public class QuartzApplicationRunner implements ApplicationRunner {

    @Autowired
    private QuartzService quartzService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("APPLICATION RUNNER...");
        QuartzVo params = new QuartzVo();
        params.setDeleteFlag("00");
        params.setQuartzJobStatus("1");
        List<QuartzVo> quartzList = quartzService.getQuartzList(params);
        if (CollectionUtils.isNotEmpty(quartzList)) {
            quartzList.forEach(quartzVo -> {
                try {
                    quartzService.startJob(quartzVo);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
