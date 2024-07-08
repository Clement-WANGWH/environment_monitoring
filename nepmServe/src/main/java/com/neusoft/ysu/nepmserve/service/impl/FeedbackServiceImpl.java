package com.neusoft.ysu.nepmserve.service.impl;

import com.neusoft.ysu.nepmserve.entity.Statistics;
import com.neusoft.ysu.nepmserve.entity.User;
import com.neusoft.ysu.nepmserve.entity.vo.FeedbackVo;
import com.neusoft.ysu.nepmserve.entity.vo.fVo;
import com.neusoft.ysu.nepmserve.mapper.StatisticsMapper;
import com.neusoft.ysu.nepmserve.mapper.UserMapper;
import com.neusoft.ysu.nepmserve.util.common.CommonResult;
import com.neusoft.ysu.nepmserve.entity.Feedback;
import com.neusoft.ysu.nepmserve.mapper.FeedbackMapper;
import com.neusoft.ysu.nepmserve.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王郝浠
 * @since 2023-08-25
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    StatisticsMapper statisticsMapper;

    @Override
    public CommonResult findAll() {
//        List<Feedback> list=mapper.selectList(null);
        List<Feedback> list= feedbackMapper.selectAll();
        CommonResult result = new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findAllByPage(int current, int size) {
        int start=(current-1)*size;
        List<Feedback> list= feedbackMapper.selectAllByPage(start,size);
        List<Feedback> getTotal= feedbackMapper.selectList(null);
        int total=getTotal.size();
        CommonResult result=new CommonResult(String.valueOf(total),list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findBySql(FeedbackVo feedbackVo) {
        List<Feedback> list= feedbackMapper.selectBySql(feedbackVo);
        int total=list.size();
        CommonResult result=new CommonResult(String.valueOf(total),list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findBySid(int sid) {
//        QueryWrapper wrapper=new QueryWrapper();
//        wrapper.eq("sId",sid);
//        List<Feedback> list = mapper.selectList(wrapper);
        List<Feedback> list= feedbackMapper.selectBySid(sid);
        for (int i=0;i<list.size();i++){
            Statistics statistics=statisticsMapper.selectByFid(list.get(i).getId());
            list.get(i).setStatistics(statistics);
        }
        CommonResult result = new CommonResult(list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findBySidByPage(fVo fVo) {
        List<Feedback> list=feedbackMapper.selectBySidByPage(fVo);
        for (int i=0;i<list.size();i++){
            Statistics statistics=statisticsMapper.selectByFid(list.get(i).getId());
            list.get(i).setStatistics(statistics);
        }
        int sid=fVo.getsId();
        List<Feedback> getTotal=feedbackMapper.selectBySid(sid);
        int total=getTotal.size();
        CommonResult result=new CommonResult(String.valueOf(total),list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult findByGid(int gid) {
        List<Feedback> list= feedbackMapper.selectByGid(gid);
        int nums=list.size();
        CommonResult result = new CommonResult(String.valueOf(nums),list);
        if(list==null||list.size()==0){
            result.setNotFound();
        }
        return result;
    }

    @Override
    public CommonResult insert(Feedback feedback) {
        feedback.setId(null);
        int tId=feedback.gettId();
        List<User> grids=userMapper.selectGridmanByTid(tId);
        if (grids.size()!=0&&grids!=null) {
            feedback.setgId(grids.get(0).getId());
            feedback.setAssignDate(feedback.getfDate());
            feedback.setAssignTime(feedback.getfTime());
            feedback.setState(1);
            grids.get(0).setState(1);
            int j=userMapper.updateById(grids.get(0));
            if (j>0){
                System.out.println("修改网格员状态成功！！！");
            }
        }
        int i = feedbackMapper.insert(feedback);
        CommonResult result = new CommonResult(feedback);
        if(i<=0){
            result.setNotInserted();
        }
        return result;
    }

    @Override
    public CommonResult deleteById(int id) {
        int i = feedbackMapper.deleteById(id);
        CommonResult result = new CommonResult(i);
        if(i<=0){
            result.setNotDelete();
        }
        return result;
    }

    @Override
    public CommonResult update(Feedback feedback) {
        int i= feedbackMapper.updateById(feedback);
        CommonResult result=new CommonResult(i);
        if(i<=0){
            result.setNotUpdate();
        }
        return result;
    }
}
