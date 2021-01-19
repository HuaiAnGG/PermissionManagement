package wiki.laona;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import wiki.laona.domain.Employee;

import java.io.IOException;

/**
 * @program: PermissionManagement
 * @description: 测试类
 * @author: HuaiAnGG
 * @create: 2021-01-19 17:08
 **/
public class test {
    @Test
    public void test() throws IOException {
        // ObjectMapper mapper = new ObjectMapper();
        // String str = "{'id':35,'username':%E8%80%81%E8%A1%B2,'password':123,'tel':1123123123,'email':971375353%40qq" +
        //         ".com,inputtime:2021-01-19,department.id:%E6%8A%80%E6%9C%AF%E9%83%A8,'admin':true,'role.rid':1,role" +
        //         ".rid:12,role.rid:10,role.rid:3,roles%5B0%5D.rid:1,roles%5B1%5D.rid:12,roles%5B2%5D" +
        //         ".rid:10,'roles%5B3%5D.rid':3}";
        // String json = mapper.writeValueAsString(str);
        // Employee employee = mapper.readValue(json, Employee.class);
        // System.out.println("employee : " + employee);
    }
}
