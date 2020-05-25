package cn.sy.demo.service;

public interface SeckillService {

    String seckill(String goodsId, String userId);

    String reset(String goodsId);

    void receiveSeckillCoupon(String goodsId, String userId);
}
