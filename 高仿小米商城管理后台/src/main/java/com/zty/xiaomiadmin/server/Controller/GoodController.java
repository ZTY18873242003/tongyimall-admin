package com.zty.xiaomiadmin.server.Controller;

import com.zty.xiaomiadmin.server.Dao.Good;
import com.zty.xiaomiadmin.server.Entity.Categor.CateResult;
import com.zty.xiaomiadmin.server.Entity.Good.*;
import com.zty.xiaomiadmin.server.Service.Good.GoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("Good")
public class GoodController {

    @Autowired
    private GoodServiceImp goodServiceImp;

    @GetMapping("/getGood")
    private GoodResult getGood(@RequestParam("pageIndex") int pageIndex,
                               @RequestParam("pageSize") int pageSize,
                               @RequestParam("name") String goodname
                               ) throws IOException {
        GoodResult goodResult = goodServiceImp.getGood(pageIndex,pageSize,goodname);
        return goodResult;
    }

    @GetMapping("/getGoodById")
    private GoodResult getGoodById(@RequestParam("category") int pageIndex,
                               @RequestParam("pageSize") int pageSize
    ) throws IOException {
        GoodResult goodResult = goodServiceImp.getGoodById(pageIndex,pageSize);
        return goodResult;
    }
    @RequestMapping("/getGoodByName")
    private GoodResult getGoodByName(@RequestParam("name") String goodname) throws IOException {
        GoodResult goodResult = goodServiceImp.getGoodByName(goodname);
        return goodResult;
    }

    @RequestMapping("/updGood")
    private GoodResult getGoodByName(@RequestParam("good_id") int goodid,
                                     @RequestParam("name") String goodname,
                                     @RequestParam("newhot") int newhot) throws IOException {
        GoodResult goodResult = goodServiceImp.updGood(goodid,goodname,newhot);
        return goodResult;
    }


    @GetMapping("/getGoodPicById")
    private GoodPicResult getGoodPicById(@RequestParam("category") int pageIndex,
                                   @RequestParam("pageSize") int pageSize
    ) throws IOException {
        GoodPicResult goodpicResult = goodServiceImp.getGoodPic(pageIndex,pageSize);
        return goodpicResult;
    }
    @RequestMapping("/getGoodPicByName")
    private GoodPicResult getGoodPicByName(@RequestParam("name") String goodname) throws IOException {
//        int categor = Integer.parseInt(category);
        GoodPicResult goodResult = goodServiceImp.getGoodPicByName(goodname);
        return goodResult;
    }


    @PostMapping("/pushGood")
    private GoodPushResult pushGood(@RequestBody good good) throws IOException {
        GoodPushResult goodPushResult = goodServiceImp.getGoodPush(good);
        return goodPushResult;
    }

    @PostMapping("/delGood")
    private void delGood(@RequestBody good good) throws IOException {
          goodServiceImp.delGood(good);
    }

    @PostMapping("/delMulGood")
    private void delMulGood(@RequestBody List<good> goods) throws IOException {
        goodServiceImp.delMulGood(goods);
    }

    @GetMapping("/getGoodPic")
    private GoodPicResult getGoodPic(@RequestParam("pageIndex") int pageIndex,
                                     @RequestParam("pageSize") int pageSize) throws IOException {
        GoodPicResult goodPicResult = goodServiceImp.getGoodPic(pageIndex,pageSize);
        return goodPicResult;
    }

    @PostMapping("/updGoodPic")
    private void upGoodPic(@RequestBody GoodPic goodPic) throws IOException {
        int goodid= goodPic.getGood_id();
        String icon= goodPic.getIcon();
        String imageHead = goodPic.getImageHead();
        String imageFoot = goodPic.getImageFoot();

        goodServiceImp.updGoodPic(goodid,icon,1);
        goodServiceImp.updGoodPic(goodid,imageHead,2);
        goodServiceImp.updGoodPic(goodid,imageFoot,3);
    }
}
