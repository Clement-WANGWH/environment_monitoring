package com.neusoft.ysu.nepmserve.controller;


import com.neusoft.ysu.nepmserve.entity.vo.FeedbackVo;
import com.neusoft.ysu.nepmserve.entity.vo.fVo;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import com.neusoft.ysu.nepmserve.entity.Feedback;
import com.neusoft.ysu.nepmserve.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-25
 */
@CrossOrigin
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    IFeedbackService service;

    @GetMapping("/findAll")
    public CommonResult findAll(){
        return service.findAll();
    }

    //分页
    @GetMapping("/findAllByPage/{current}/{size}")
    public CommonResult findAllByPage(@PathVariable("current") int current,@PathVariable("size")int size){
        return service.findAllByPage(current,size);
    }

    //条件查询
    @PostMapping("/findBySql")
    public CommonResult findBySql(@RequestBody FeedbackVo feedbackVo){
        return service.findBySql(feedbackVo);
    }

    @GetMapping("/findBySid/{sid}")
    public CommonResult findBySid(@PathVariable("sid") int sid){
        return service.findBySid(sid);
    }

    @PostMapping("/findBySidByPage")
    public CommonResult findBySidByPage(@RequestBody fVo fVo){
        return service.findBySidByPage(fVo);
    }

    @GetMapping("/findByGid/{gid}")
    public CommonResult findByGid(@PathVariable("gid") int gid){
        return service.findByGid(gid);
    }

    @PutMapping("/add")
    public CommonResult insert(@RequestBody Feedback feedback){
        return service.insert(feedback);
    }

    @DeleteMapping("/deleteById/{id}")
    public CommonResult deleteById(@PathVariable("id") int id){
        return service.deleteById(id);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody Feedback feedback){
        return service.update(feedback);
    }
}
