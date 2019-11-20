package com.example.springvue;

import com.example.springvue.dao.*;
import com.example.springvue.pojo.bo.CategoryBO;
import com.example.springvue.pojo.bo.GoodBO;
import com.example.springvue.pojo.bo.PropertyBO;
import com.example.springvue.pojo.bo.UserBO;
import com.example.springvue.pojo.dto.AdminDTO;
import com.example.springvue.pojo.dto.CategoryDTO;
import com.example.springvue.pojo.dto.GoodCategoryDTO;
import com.example.springvue.pojo.dto.UserDTO;
import com.example.springvue.pojo.vo.AdminVO;
import com.example.springvue.pojo.vo.CategoryVO;
import com.example.springvue.pojo.vo.PropertyVO;
import com.example.springvue.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringvueApplicationTests {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * 查询属性
     */
    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private GoodMapper goodMapper;

    @Test
    public void insertAdmin() {
        List<UserBO> userBOS = userMapper.getUserList();
        for (UserBO userBO : userBOS) {
            System.out.println(userBO);
            System.out.println(DateUtil.createTimeVO(userBO.getCreateDate()));
        }
    }

    @Test
    public void dateNow() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        System.out.println("格式化输出：" + sdf.format(d));

        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println("Asia/Shanghai:" + sdf.format(d));

        System.out.println("格式化输出：" + sdf.format(d) + '\n' + "Asia/Shanghai:" + sdf.format(d));
    }

    @Test
    public void updateUserStatus() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setStatus(true);
        System.out.println(userMapper.updateUserStatusById(userDTO));
    }

    /**
     * 根据用户名和用户状态动态查询
     */
    @Test
    public void getUserListByParams() {
        UserDTO userDTO = new UserDTO();
        userDTO.setStatus(true);
        userDTO.setName("151");
        System.out.println(userMapper.getUserListByParams(userDTO).size());
    }

    @Test
    public void getAdminByNameAndPassword() {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setName("admin");
        adminDTO.setPassword("admin");
        AdminVO adminVO = adminMapper.getAdminByNameAndPassword(adminDTO);
        System.out.println(adminVO);
    }

    @Test
    public void getParentCategoryNameList() {
        List<CategoryBO> categoryBOS = categoryMapper.getCategoryList();
        List<CategoryVO> categoryVOS = new ArrayList<>();
        System.out.println(categoryBOS);
        for (CategoryBO categoryBO : categoryBOS) {
            CategoryVO categoryVO = new CategoryVO(categoryBO);
            categoryVOS.add(categoryVO);
        }
        System.out.println(categoryVOS);
    }

    /**
     * 修改分类状态
     */
    @Test
    public void updateCategoryStatus() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setLastModifyTime(DateUtil.createTime());
        categoryDTO.setLastModifyBy(1L);
        categoryDTO.setLastModifierName("admin");
        categoryDTO.setStatus(true);
        categoryMapper.updateCategoryStatus(categoryDTO);
    }

    /**
     * 分类动态查询
     */
    @Test
    public void getCategoryListByParams() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setStatus(false);
        List<CategoryBO> categoryBOS = categoryMapper.getCategoryListByParams(categoryDTO);
        if (categoryBOS.size() > 0) {
            List<CategoryVO> categoryVOS = new ArrayList<>();
            for (CategoryBO categoryBO : categoryBOS) {
                CategoryVO categoryVO = new CategoryVO(categoryBO);
                categoryVOS.add(categoryVO);
            }
            System.out.println(categoryVOS);
        }
    }

    @Test
    public void selectProperty() {
        List<PropertyBO> propertyBOS = propertyMapper.getPropertyList();
        List<PropertyVO> propertyVOS = new ArrayList<>();
        for (PropertyBO propertyBO : propertyBOS) {
            PropertyVO propertyVO = new PropertyVO(propertyBO);
            propertyVOS.add(propertyVO);
        }
        System.out.println(propertyVOS);
    }

    @Test
    public void updateGoodStatus() {
        GoodCategoryDTO goodCategoryDTO = new GoodCategoryDTO();
        List<GoodBO> goodBOS = goodMapper.getGoodListByParams(goodCategoryDTO);
        System.out.println(goodBOS);
    }
}
