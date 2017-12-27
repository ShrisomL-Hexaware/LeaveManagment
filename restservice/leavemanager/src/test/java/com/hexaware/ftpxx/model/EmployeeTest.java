package com.hexaware.ftp01.model;

import com.hexaware.ftp01.persistence.EmployeeDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;

import java.util.ArrayList;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Test class for Employee.
 */
@RunWith(JMockit.class)
public class EmployeeTest {

  /**
   * setup method.
   */
  @Before
  public void initInput() {

  }

  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testEmployee() {
    Employee e100 = new Employee(2001, "Anushree Beohar", 8871676607L, "AnushreeB@hexaware.com", "HEXAVARSITY",
                                 1000, 0, "2014/11/17");
    Employee e101 = new Employee(2001, "Anushree Beohar", 8871676607L, "AnushreeB@hexaware.com", "HEXAVARSITY",
                                 1000, 0, "2014/11/17");
    assertEquals(e100.hashCode(), new Employee(2001, "Anushree Beohar", 8871676607L, "AnushreeB@hexaware.com",
                                               "HEXAVARSITY", 1000, 0, "2014/11/17").hashCode());
    assertEquals(2001, e100.getEmpId());
    e101.setEmpId(2001);
    assertEquals("Anushree Beohar", e100.getEmpName());
    e101.setEmpName("Anushree Beohar");
    assertEquals(8871676607L, e100.getEmpPhone());
    e101.setEmpPhone(8871676607L);
    assertEquals("AnushreeB@hexaware.com", e100.getEmpEmail());
    e101.setEmpEmail("AnushreeB@hexaware.com");
    assertEquals("HEXAVARSITY", e100.getEmpDept());
    e101.setEmpDept("HEXAVARSITY");
    assertEquals(1000, e100.getEmpManagerId());
    e101.setEmpManagerId(1000);
    assertEquals(0, e100.getEmpLeaveBalance());
    e101.setEmpLeaveBalance(0);
    assertEquals("2014/11/17", e100.getEmpDoj());
    e101.setEmpDoj("2014/11/17");
  }

  /**
   *Test the applyForLeave method of the employee class.
   * @param dao mocking the dao class.
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testApplyForLeave(@Mocked final EmployeeDAO dao)throws ParseException {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    final Date dt1 = sf.parse("2017/12/26");
    final Date dt2 = sf.parse("2017/12/28");
    final Date dt3 = sf.parse("2017/12/23");
   // final String dt4 = "2017/12/26";
   // final String dt5 = "2017/12/28";
    new Expectations() {
      {
        dao.updateLeaveBalance(1, 2001);
        dao.updateLeaveBalance(0, 1000);
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee e100 = new Employee(2001, "Anushree Beohar", 8871676607L, "AnushreeB@hexaware.com", "HEXAVARSITY",
                                 1000, 3, "2014/12/17");
    Employee e101 = new Employee(1000, "Shrisom Laha", 8961260400L, "ShrisomL@hexaware.com", "HEXAVARSITY",
                                 0, 2, "2014/12/17");
    String s = "Leave application forwarded to manager";
    String s1 = "Leave is applied Boss!!";
    //String s2 = "Number of days for leave cannot be less than 1 day!!";
    //String s3 = "Sorry, end date is before start date.";
    //String s4 = "Enter correct Number of days for leave!";
    //String s5 = "You dont have expected leave balance.!!";

    String str = e100.applyForLeave(LeaveType.EL, 2, "sick", dt1, dt2);
    String str1 = e101.applyForLeave(LeaveType.EL, 2, "Wedding", dt1, dt2);
    //String str2 = e100.applyForLeave(LeaveType.EL, 0, "sick", dt4, dt5);
    //String str3 = e101.applyForLeave(LeaveType.EL, 2, "Wedding", dt5, dt4);

    //String str4 = e100.applyForLeave(LeaveType.EL, 3, "sick", dt4, dt5);
    //String str5 = e101.applyForLeave(LeaveType.EL, 3, "Wedding", dt5, dt4);
    assertEquals(s, str);
    assertEquals(s1, str1);
    //assertEquals(s2, str2);
    //assertEquals(s3, str3);
    //assertEquals(s4, str4);
    //assertEquals(s5, str5);
  }

  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllEmpty(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        dao.list(); result = new ArrayList<Employee>();
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = Employee.listAll();
    assertEquals(0, es.length);
  }

  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllSome(@Mocked final EmployeeDAO dao) {
    new Expectations() {
      {
        ArrayList<Employee> es = new ArrayList<Employee>();
        es.add(new Employee(1));
        es.add(new Employee(10));
        es.add(new Employee(100));
        dao.list(); result = es;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee[] es = Employee.listAll();
    assertEquals(3, es.length);
    assertEquals(new Employee(1), es[0]);
    assertEquals(new Employee(10), es[1]);
    assertEquals(new Employee(100), es[2]);
  }

    /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListById(@Mocked final EmployeeDAO dao) {
    final Employee e100 = new Employee(100);
    new Expectations() {
      {
        dao.find(100); result = e100;
        dao.find(-1); result = null;
      }
    };
    new MockUp<Employee>() {
      @Mock
      EmployeeDAO dao() {
        return dao;
      }
    };
    Employee e = Employee.listById(100);
    assertEquals(e100, e);

    e = Employee.listById(-1);
    assertNull(e);
  }
}

