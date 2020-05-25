package cn.sy.demo.api.controller;

import cn.sy.demo.service.SeckillService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SeckillServiceTest extends BaseTest {

    @Autowired
    private SeckillService seckillService;

    @Test
    public void test() {

        seckillService.receiveSeckillCoupon("1", "2");

    }

}
