package com.zty.xiaomiadmin.server.Controller;

import com.zty.xiaomiadmin.server.Entity.Suggest.SuggBigResult;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggFootResult;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggestBig;
import com.zty.xiaomiadmin.server.Entity.Suggest.SuggestFoot;
import com.zty.xiaomiadmin.server.Service.Suggest.SuggServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("Suggest")
public class SuggestController {

    @Autowired
    private SuggServiceImp suggServiceImp;

    @PostMapping("/getSugFoot")
    public SuggFootResult getSugFoot() throws IOException {
        SuggFootResult suggestFootResult = new SuggFootResult();
        suggestFootResult.setStatus(200);
        List<SuggestFoot> sugFoot = suggServiceImp.getSugFoot();
        int sugFootCount = suggServiceImp.getSugFootCount();
        suggestFootResult.setPageTotal(sugFootCount);
        suggestFootResult.setList(sugFoot);
        return  suggestFootResult;
    }

    @PostMapping("/pushSuggFoot")
    public SuggFootResult pushSuggFoot(@RequestBody SuggestFoot suggestFoot) throws IOException {
        SuggFootResult suggFootResult = suggServiceImp.getSuggestFootById(suggestFoot.getGoodid(),suggestFoot.getImgUrl());
        return suggFootResult;
    }

    @PostMapping("/updSuggFoot")
    public SuggFootResult updSuggFoot(@RequestBody SuggestFoot suggestFoot) throws IOException {
        SuggFootResult updSuggestFootById = suggServiceImp.updSuggestFootByName(suggestFoot.getName(),suggestFoot.getImgUrl());
        return updSuggestFootById;
    }

    @PostMapping("/delSuggFoot")
    public void delSuggFoot(@RequestBody SuggestFoot suggestFoot) throws IOException {
        suggServiceImp.delSuggestFoot(suggestFoot);
    }



    @GetMapping("/getSugBig")
    public SuggBigResult getSugBig(@RequestParam("kind") int kind) throws IOException {
        SuggBigResult suggestBigResult = new SuggBigResult();
        suggestBigResult.setStatus(200);
        List<SuggestBig> sugBig = suggServiceImp.getSugBig(kind);
        int sugBigCount = suggServiceImp.getSugBigCount();
        suggestBigResult.setPageTotal(sugBigCount);
        suggestBigResult.setList(sugBig);
        return  suggestBigResult;
    }

    @GetMapping("/pushSuggBig")
    public SuggBigResult pushSuggBig(@RequestParam("goodid") int goodid,
                                     @RequestParam("kind") int kind) throws IOException {
        SuggBigResult suggBigResult = suggServiceImp.pushSuggBig(goodid,kind);
        return suggBigResult;
    }

    @PostMapping("/delSugBig")
    public void delSuggBig(@RequestBody SuggestBig suggestBig) throws IOException {
        String goodname = suggestBig.getName();
        suggServiceImp.delSuggestBig(goodname);
    }

}
