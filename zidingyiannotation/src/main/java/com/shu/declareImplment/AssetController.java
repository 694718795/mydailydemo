package com.shu.declareImplment;

import com.shu.aopimplement.Po;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-21 16:50
 */
@RequestMapping
@RestController
public class AssetController {


    @PostMapping("/ipanno")
    public @ResponseBody AssetInfo asynctest(@Valid @RequestBody AssetInfo assetInfo) {
        System.out.println(assetInfo);
        return assetInfo;
    }
}
