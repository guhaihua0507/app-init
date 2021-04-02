package com.ghh.sample.service.impl;

import com.ghh.sample.base.AbstractBaseEntityService;
import com.ghh.sample.base.AppBaseMapper;
import com.ghh.sample.mapper.UserMapper;
import com.ghh.sample.model.entity.User;
import com.ghh.sample.service.UserService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl extends AbstractBaseEntityService<User> implements UserService  {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public AppBaseMapper<User> getEntityMapper() {
        return userMapper;
    }

    @Override
    @Transactional
    public void createUser(User user) {
//        userMapper.insert(user);
    }

    @Override
    public List<User> listUsers() {
//        return userMapper.selectAll();
        return userMapper.getAllUser();
    }

    @Override
    public InputStream genExcel() throws IOException {
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", i);
            map.put("name", "name_" + i);
            map.put("gender", RandomUtils.nextInt(0, 2));
            data.add(map);
        }

        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet();
            int rowIndex = 0;
            int colIndex = 0;
            XSSFRow titleRow = sheet.createRow(rowIndex++);
            titleRow.createCell(colIndex++).setCellValue("序号");
            titleRow.createCell(colIndex++).setCellValue("名称");
            titleRow.createCell(colIndex++).setCellValue("性别");

            XSSFRow row;
            XSSFCell cell;
            for (int i = 0; i < data.size(); i++) {
                // Create row
                row = sheet.createRow(rowIndex++);
                Map<String, Object> map = data.get(i);
                int j = 0;
                cell = row.createCell(j++, CellType.STRING);
                cell.setCellValue(String.valueOf(map.get("id")));
                cell = row.createCell(j++);
                cell.setCellValue(String.valueOf(map.get("name")));
                cell = row.createCell(j++);
                cell.setCellValue(String.valueOf(map.get("gender")));
            }

            return exportAsInputStream(wb);

        }
    }

    @Override
    public String getUserName(String id) {
        System.out.println("running get user Service");
        return "User-" + id;
    }

    private InputStream exportAsInputStream(Workbook wb) {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            wb.write(out);
            InputStream in = new ByteArrayInputStream(out.toByteArray());
            return in;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeQuietly(out);
        }
    }

    private void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception ignore){}
        }
    }
}
