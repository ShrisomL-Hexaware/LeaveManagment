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
                                 1000, 0, "2014-11-17");
    Employee e101 = new Employee(2001, "Anushree Beohar", 8871676607L, "AnushreeB@hexaware.com", "HEXAVARSITY",
                                 1000, 0, "2014-11-17");
    assertEquals(e100.hashCode(), new Employee(2001, "Anushree Beohar", 8871676607L, "AnushreeB@hexaware.com",
                                               "HEXAVARSITY", 1000, 0, "2014-11-17").hashCode());
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
    assertEquals("2014-11-17", e100.getEmpDoj());
    e101.setEmpDoj("2014-11-17");
  }

  /**
   *Test the applyForLeave method of the employee class.
   */
  public final void testApplyForLeave() {

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

