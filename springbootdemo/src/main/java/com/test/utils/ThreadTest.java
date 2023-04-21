package com.test.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.HashSet;
import java.util.concurrent.*;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

        String aa = "rokae2022,dongxin001,756789_sandbox,gloshine,szwx888,gdyhljzm,shizaiai,aidite,gdsdtc,glzrsp2022,glylt66,gzch888,0,aidite,gdsdtc,aidite,bamatea,bjywjyjs,gdsdtc,ipro2022,demo103,nuohao2022,aidite,eureka,gdsdtc,0,aidite,bamatea,gdsdtc,genecast,absen2022,aidite,ept2022,gdsdtc,genecast,shizaiai,germanmedtech,aidite,bamatea,fjlbxxfood,0,aidite,gdsdtc,genecast,ipro2022,0,aidite,bjywjyjs,gdsdtc,genecast,0,aidite,gdsdtc,glzrsp2022,ipro2022,0,aidite,gdsdtc,genecast,ipro2022,772678_sandbox,aidite,bamatea,fczn88,gdsdtc,772678_sandbox,aidite,cwbiotech,gdsdtc,glzrsp2022,zhlg888,zngmy88,qdglkj,772678_sandbox,L,cyagen,gdyhljzm,szwx888,rainbow2021,60080,techase,yzyznjk2022,whketai,jshpbyq,ptytsp,fsxkdq888,sdzgdzswb,mingyue2021,donview2,gdyhljzm,jshpbyq,ldyswz,octavia,alphaess01,rainbow2021,ldyswz,jnkwmy,rejoin,hopefound,hzbsylyxgs,ldyswz,hopefound,rainbow2021,rokae2022,xgsdhrd888,vision2022,gdyhljzm,rejoin,rainbow2021,gzdxkj,qiyiyun,0,rainbow2021,ldyswz,alphaess01,rainbow2021,lysrwl,ldyswz,lkkj123,ascend2022,shzm168,zjkxhy,hst001,whketai,rainbow2021,rejoin,cdnlight2021,haipurui001,fsxkdq888,zbtianjun,qlhx888,jnkwmy,whketai,allystar,alphaess01,alphaess01,brandsh,gzdxkj,hopefound,cdnlight2021,hopefound,whketai,alphaess01,longquanguandao,allystar,glzrsp2022,gdyhljzm,ptytsp,jcyk2022,jtzyjt,trsnyzb88,njypkj,szwx888,longquanguandao,huitaitc888,rainbow2021,paladi,qiyiyun,ldyswz,qlhx888,hopefound,dongxin001,ldyswz,gdyhljzm,rejoin,rejoin,ftexpress,hopefound,ptytsp,ldyswz,cwbiotech,dongxin001,rainbow2021,ldyswz,cyagen,lysrwl,jtzyjt,gdyhljzm,dongxin001,rainbow2021,rainbow2021,dhtx123,ftexpress,ldyswz,zbtianjun,gdyhljzm,szssgf88,maptz888,ychz888,shizaiai,ftexpress,genomics,sinohealth66,huayun001,shizaiai,jtzyjt,efort2021,60080,455694,772678_sandbox,lysrwl,0,772678_sandbox,aidite,bjszlt123,szssgf88,efort2021,cyagen,zngmy88,0,zhengxiongcrm,cyagen,shizaiai,szwx888,661932,zhengxiongcrm,liansn,hxxwhzx,szwx888,759416,zhengxiongcrm,fxxq888,kelvfalv,szwx888,liansn,sfgd168,shizaiai,szwx888,zhengxiongcrm,0,szwx888,sfgd168,759232_sandbox,772678_sandbox,aidite,zzxldhg,gloshine,mccain01,gonsin888,cwbiotech,sjdq123,pratic,yjyp888,transfarchem001,rejoin,zzxldhg,feymer,cwbiotech,shhyxx88,glylt66,575302,742418,gdgkky,rejoin,mccain01,zzxldhg,helios,zhlg888,cdnlight2021,mcsm88,shhyxx88,feymer,anbotong2021,shsx888,guooxinyiyao,zzxldhg,helios,shhyxx88,cdnlight2021,575302,rejoin,gdgkky,sjdq123,rundamedical,ipro2022,zzxldhg,qiyiyun,demo103,cdnlight2021,alphaess01,helios,tjwl2021,wuzang2021,tokancrm,shzhbzjs,zzxldhg,cdnlight2021,glylt66,575302,gdgkky,rejoin,sjdq123,rundamedical,ipro2022,jtzyjt,zzxldhg,haohuo01,cdnlight2021,575252,575302,tjwl2021,rejoin,gdgkky,glylt66,rundamedical,zzxldhg,575302,rejoin,gdgkky,sjdq123,rundamedical,ipro2022,jtzyjt,zjlymy,zsyc888,zzxldhg,qiyiyun,zyjs2022,alphaess01,mrsy8888,575302,tjwl2021,575252,rejoin,rundamedical,zzxldhg,575302,glylt66,gdgkky,rejoin,sjdq123,rundamedical,transfarchem001,ipro2022,jtzyjt,zzxldhg,pinzhong2019,rundamedical,cyagen,shuita2021,glylt66,cdnlight2021,575302,tjwl2021,rejoin,zzxldhg,575302,glylt66,rejoin,gdgkky,rundamedical,sjdq123,ipro2022,jtzyjt,transfarchem001,zzxldhg,harveststar,zjhcsx,alphaess01,575302,tjwl2021,gdgkky,rejoin,maptz888,jtzyjt,zzxldhg,syyst2020,575302,zjhcsx,alphaess01,rejoin,gdgkky,rundamedical,sjdq123,glylt66,zzxldhg,photonpay,tjwl2021,767982_sandbox,zjsexpress,768774_sandbox,575302,gdgkky,glzrsp2022,rejoin,zzxldhg,transfarchem001,721787,zjsexpress,767982_sandbox,glzrsp2022,575302,768774_sandbox,yjyp888,glylt66,zzxldhg,transfarchem001,721787,sinontech2022,gdsbkj,gdmbsswkj2022,zihaiguo2021,767982_sandbox,zjsexpress,glylt66,zzxldhg,jintiansuye,721787,transfarchem001,qlhx888,ord521,cornley,sh21cake,cyagen,jnkwmy,zzxldhg,721787,transfarchem001,661932,cornley,jintiansuye,cyagen,qlhx888,zjsexpress,fourseas1971,zzxldhg,721787,zsyc888,cyagen,qlhx888,daoqum2022,lydukang,zhxgcrm,cornley,zngmy88,zzxldhg,721787,zsyc888,sdwy888,yjyp888,cyagen,lydukang,qlhx888,cornley,gzg7888,zzxldhg,721787,zsyc888,nnhhsw,jinhong,zhxgcrm,cyagen,gzg7888,szssgf88,ord521,zzxldhg,721787,nnhhsw,cyagen,fczn88,zsyc888,zhxgcrm,gzg7888,cornley,alphaess01,zzxldhg,721787,cornley,fczn88,gzg7888,cyagen,zngmy88,glylt66,qlhx888,zhxgcrm,zzxldhg,721787,alphaess01,gdsbkj,zjhcsx,gzg7888,cornley,740316,raytocrm,tjwl2021,zzxldhg,721787,zsyc888,cornley,ord521,767982_sandbox,smooredv,raytocrm,rundamedical,jinhong,zzxldhg,772516_sandbox,zsyc888,721787,cyagen,cornley,cwbiotech,742327,zhxgcrm,737863,zzxldhg,721787,zsyc888,brandsh,zcmade,rainbow2021,sfgd168,benewake2021,alphaess01,cyagen,zzxldhg,efort2021,721787,zsyc888,cyagen,zhxgcrm,genomics,jintiansuye,alphaess01,gzg7888,zzxldhg,721787,cyagen,cornley,cwbiotech,smartsh,zngmy88,xywew2021,ord521,rainbow2021,721787,zzxldhg,rainbow2021,zqzx2023,cyagen,zhxgcrm,cornley,trsnyzb88,zngmy88,740429,efort2021,zzxldhg,rainbow2021,721787,zqzx2023,cyagen,cyt88888,cornley,jintiansuye,zhxgcrm,zzxldhg,721787,qlhx888,cyagen,zhxgcrm,jintiansuye,inovgroup757453175c,gzlgjt,zngmy88,cwbiotech,721787,cyagen,zzxldhg,jintiansuye,740361,zhxgcrm,meitsing,ord521,rejoin,dhtx123,721787,fjlbxxfood,cyagen,zhxgcrm,qlhx888,meitsing,jintiansuye,cwbiotech,hzxabb,zngmy88,eureka,mkfe168,721787,zzxldhg,cyagen,737608,sanpangdan2018,rainbow2021,hzxabb,smooredv,eureka,721787,mingyue2021,cyagen,smartsh,gdsbkj,742234,donview2,alphaess01,740279,maptz888,smartsh,721787,ord521,740639,cyagen,740625,740573,737650,dongxin001,smartsh,yjh2022,mingyue2021,721787,742414,737901,737885,737953,740742,753178,smartsh,ord521,737883,721787,741349,741486,740386,742404,742534,shwalraven,sophonix,740227,smartsh,721787,alphaess01,741967,ftexpress,742433,740219,737644,721787,740927,742201,740280,742548,742257,742133,740202,737837,cwbiotech,jnkwmy,721787,helios,yywhjt,741456,741765,737601,740947,741476,742121,zhengxiongcrm,yywhjt,jnkwmy,helios,721787,741461,737601,741011,737994,742572,zhengxiongcrm,velux2019,yywhjt,genomics,ftexpress,jnkwmy,yingtongkeji,vastec,vstecscrm,helios,zhengxiongcrm,genomics,helios,velux2019,vastec,721787,741996,741499,lamarzocco,740434,cxcm2020,mingyue2021,mccain01,helios,zjyonghe,741996,hst001,741939,mdzykj,721787";
        HashSet<String> strings = Sets.newHashSet(aa.split(","));
        System.out.println(strings);

        ThreadFactory workerFactory = new ThreadFactoryBuilder()
                .setNameFormat("APLTask-%d")
                .setDaemon(true)
                .build();

        ThreadPoolExecutor debugExecutor = new ThreadPoolExecutor(5, 5, 30L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), workerFactory);
        debugExecutor.prestartAllCoreThreads();


        for (int i = 0; i < 100; i++) {
            debugExecutor.submit(() -> System.out.println("rrrrrrrrrrrr" + Thread.currentThread().getName()));

            Thread.sleep(10L);

        }

    }
}
