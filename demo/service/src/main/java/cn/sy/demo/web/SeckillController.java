package cn.sy.demo.web;

import cn.sy.demo.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "seckill")
@Slf4j
public class SeckillController {

    @Autowired
    SeckillService seckillService;


    @GetMapping
    @RequestMapping("{goodsId}")
    public Object seckill(@PathVariable String goodsId, String userId) {
        log.info("秒杀开始");
        long start = System.currentTimeMillis();
        userId = UUID.randomUUID().toString();
        String success = seckillService.seckill(goodsId, userId);
        long end = System.currentTimeMillis();
        log.info("秒杀结束，耗费时间" + (end - start));
        return success;
    }


    @GetMapping
    @RequestMapping("reset/{goodsId}")
    public Object seckillReset(@PathVariable String goodsId) {
        String success = seckillService.reset(goodsId);
        return success;
    }
}
