package com.zty.xiaomiadmin.server.Controller;

import com.zty.xiaomiadmin.server.Entity.Categor.CateResult;
import com.zty.xiaomiadmin.server.Entity.Categor.Category;
import com.zty.xiaomiadmin.server.Service.Category.CategServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategServiceImp categServiceImp;
    @GetMapping("/getCategory")
        private CateResult getCategory(@RequestParam("pageIndex") int pageIndex,
                                    @RequestParam("pageSize") int pageSize) throws IOException {
        CateResult cateResult = categServiceImp.getCategory(pageIndex, pageSize);
        return cateResult;
    }

    @PostMapping("/updateCategory")
    private void updateCategory(@RequestBody Category category) throws IOException {
        categServiceImp.updCategory(category.getName(),category.getCategoryId());
    }
}
