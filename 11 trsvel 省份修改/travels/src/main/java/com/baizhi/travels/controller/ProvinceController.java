package com.baizhi.travels.controller;

import com.baizhi.travels.entity.Province;
import com.baizhi.travels.entity.Result;
import com.baizhi.travels.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController

@RequestMapping("province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;


    /*修改省份信息*/
    @PostMapping("update")
    public Result update(@RequestBody Province province) {
        Result result = new Result();

        try {
            provinceService.update(province);
            result.setMsg("省份信息修改成功");

        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }


    /*查询一个省份信息*/
    @GetMapping("findOne")
    public Province findOne(String id) {
        return provinceService.findOne(id);
    }

    /*删除省份信息*/
    @GetMapping("delete")
    public Result delete(String id) {
        Result result = new Result();
        try {
            provinceService.delete(id);
            result.setMsg("删除省份信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("删除省份信息失败!!!");
        }
        return result;
    }

    /*保存省份信息*/
    @PostMapping("save")
    public Result save(@RequestBody Province province) {
        Result result = new Result();
        try {
            provinceService.save(province);
            result.setMsg("省份信息保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("省份信息保存失败，请重新保存");
        }
        return result;
    }


    @GetMapping("findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;
        HashMap<String, Object> map = new HashMap<>();
        //分页处理
        List<Province> provinces = provinceService.findByPage(page, rows);
        //计算总页数
        Integer totals = provinceService.findTotals();
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("provinces", provinces);
        map.put("totals", totals);
        map.put("totalPage", totalPage);
        map.put("page", page);
        return map;
    }
}
