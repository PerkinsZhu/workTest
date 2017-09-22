package com.zpj.jackson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.zpj.bean.Student;
import com.zpj.tool.printCollection.PrintCollection;

/***
 * @author Perkins Zhu
 * @date 2017年2月23日 下午2:07:27
 */
public class JsonParseTest {
	List<Student> lists = new ArrayList<Student>();
	@Before
	public void init() {
		for (int i = 0; i < 10; i++) {
			Student stu = new Student();
			stu.setId(i);
			stu.setName("name=="+i);
			lists.add(stu);
		}
	}

	ObjectMapper om = new ObjectMapper();
	@Test
	public void testJson() {
		try {
			Student stu = new Student();
			stu.setId(23);
			stu.setName("nameMING");
			String json = om.writeValueAsString(stu);
			System.out.println(json);
			Student stau = (Student) om.readValue(json, Student.class);
			System.out.println(stau.getName()+"--"+stau.getId());
			
			json = om.writeValueAsString(lists);
			System.out.println(json);
			List sts = (List) om.readValue(json, List.class);
			PrintCollection.printByIterator(sts);
			//******************************** json ---- list *************************************************			
			JavaType javaType=om.getTypeFactory().constructParametricType(ArrayList.class, Student.class);
			List<Student> stus = om.readValue(json, javaType);
			PrintCollection.printByIterator(stus);
//******************************** json ---- map *************************************************			
			HashMap<String ,Student> map = new HashMap<String,Student>();
			for (int i = 0; i < 10; i++) {
				Student sss = new Student();
				sss.setId(i);
				sss.setName("namemap-"+i);
				map.put(i+"", sss);
			}
			
			String mapJson = om.writeValueAsString(map);
			System.out.println(mapJson);
			JavaType javaTypeTwo=om.getTypeFactory().constructParametricType(HashMap.class,String.class, Student.class);
			Map<String,Student> stuMap = om.readValue(mapJson, javaTypeTwo);
			PrintCollection.printMap(stuMap);
			//*********************************************************************************			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testXMLTOJSON(){
		XmlMapper  cm = new XmlMapper();
	}

}