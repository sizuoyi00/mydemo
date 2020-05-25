package cn.sy.demo.service.impl;

import cn.sy.demo.constant.MQConstant;
import cn.sy.demo.constant.exception.BusinessException;
import cn.sy.demo.mapper.CouponMapper;
import cn.sy.demo.mapper.MemberCouponMapper;
import cn.sy.demo.model.Coupon;
import cn.sy.demo.model.MemberCoupon;
import cn.sy.demo.service.SeckillService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private MemberCouponMapper memberCouponMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired

    private static final ConcurrentHashMap<String, Boolean> localOverMap = new ConcurrentHashMap<>();

    @Override
    public String seckill(String goodsId, String userId) {
        log.info("入参：" + userId);

        //防重复下单

        RLock lock = redissonClient.getLock("seckill_" + goodsId + "_" + userId);
        try {
            lock.tryLock(100, TimeUnit.MICROSECONDS);
            //内存标记，减少redis访问
            Boolean over = localOverMap.get(goodsId);
            if (over != null && over) {
                log.info("走内存了");
                return "false";
            }
            long stock = stringRedisTemplate.opsForValue().decrement("goods_" + goodsId);
            if (stock < 0) {
                log.info("没库存了" + userId);
                localOverMap.put(goodsId, true);
                return "false";
            }

            amqpTemplate.convertAndSend(MQConstant.EXCHANGE_TEST, MQConstant.KEY_SECKILL_COUPON,
                    goodsId + "_" + userId);

            return "success";
        } catch (Exception e) {
            log.error("报错了", e);
            throw new BusinessException("false");
        } finally {
            lock.unlock();
        }

    }

    @Override
    @Transactional
    @DS("local")
    public String reset(String goodsId) {
        int goods = Integer.parseInt(goodsId);
        localOverMap.remove(goodsId);
        Coupon dbCoupon = couponMapper.selectById(goods);
        stringRedisTemplate.opsForValue().set("goods_" + goodsId, String.valueOf(dbCoupon.getCreateNum()));
        Coupon coupon = new Coupon();
        coupon.setReceivedNum(0);
        couponMapper.update(coupon, Wrappers.<Coupon>lambdaUpdate()
                .eq(Coupon::getId, goods));
        memberCouponMapper.delete(Wrappers.<MemberCoupon>lambdaUpdate()
                .eq(MemberCoupon::getCouponId, goods));
        return "success";
    }


    @Override
    @Transactional
    @DS("local")
    public void receiveSeckillCoupon(String goodsId, String userId) {
        MemberCoupon memberCoupon = new MemberCoupon();
        memberCoupon.setCouponId(Integer.parseInt(goodsId));
        memberCoupon.setMemberId(userId);
        memberCoupon.setReceiveNum(1);
        memberCoupon.setUsedNum(0);

        try {
            memberCouponMapper.insert(memberCoupon);
        } catch (DuplicateKeyException e) {
            throw new BusinessException("用户已有该券了, " + memberCoupon);

        }

        // 乐观锁保底
        long updateCount = couponMapper.updateReceiveNum(Integer.parseInt(goodsId));
        if (updateCount < 1) {
            throw new BusinessException("该券被抢光了, " + goodsId);
        }
    }
}
