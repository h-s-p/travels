package com.baizhi.travels.service;

import com.baizhi.travels.dao.ProvinceDAO;
import com.baizhi.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceDAO provinceDAO;

    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        int start = (page - 1) * rows;
        return provinceDAO.findByPage(start, rows);
    }

    @Override
    public void delete(Province id) {
        provinceDAO.delete(id);
    }

    @Override
    public void save(Province province) {
        province.setPlacecounts(String.valueOf(0));//景点个数为0
        provinceDAO.save(province);
    }

    @Override
    public Integer findTotals() {
        return provinceDAO.findTotals();
    }
}
