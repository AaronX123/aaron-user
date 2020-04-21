package aaron.user.service.biz.dao;

import aaron.user.service.biz.service.RoleResourceService;
import aaron.user.service.pojo.model.Role;
import aaron.user.service.pojo.model.RoleResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyouming
 * @version 1.0
 * @since 2020-04-19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleForResourceTest {
    @Autowired
    RoleResourceService roleResourceService;



    @Test
    public void test(){
        List<RoleResource> roleResources = new ArrayList<>();
        RoleResource roleResource = new RoleResource();
        roleResource.setId(11L);
        roleResource.setResourceId(620569932341710848L);
        roleResource.setRoleId(619184575100489728L);
        roleResource.setType((byte) 0);
        roleResources.add(roleResource);
        RoleResource roleResource2 = new RoleResource(12L,619184575100489728L, 623584747691708416L, (byte) 0);
        RoleResource roleResource3 = new RoleResource(4L, 619184575100489728L, 623573822066659328L, (byte)0);
        RoleResource roleResource4 = new RoleResource(5L, 619184575100489728L, 620572889640603648L, (byte) 0);
        roleResources.add(roleResource2);
        roleResources.add(roleResource3);
        roleResources.add(roleResource4);
        roleResourceService.saveBatch(roleResources);
    }

    @Test
    public void test2(){
        List<Role> roleList = new ArrayList<>();
        Role role = new Role();
        role.setId(701206612983156736L);
        roleList.add(role);
        roleResourceService.removeByRoleList(roleList);
    }
}
