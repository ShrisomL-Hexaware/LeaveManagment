package com.hexaware.ftp01.model;


import com.hexaware.ftp01.persistence.LeaveDetailsDAO;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
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
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Test class for LeaveDetails.
 */
@RunWith(JMockit.class)
public class LeaveDetailsTest {

  /**
   * setup method.
   */
  @Before
  public void initInput() {

  }

  /**
   * Tests the equals/hashcode methods of the leave details class.
   * @throws ParseException to handle exception.
   */
  @Test
  public final void testLeaveDetails()throws ParseException {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails l100 = new LeaveDetails(5, LeaveType.EL, sf.parse("2017/12/20"), sf.parse("2017/12/23"), 4,
                                         LeaveStatus.APPROVED, "trip", sf.parse("2017/12/18"), "enjoy", 2000);
    LeaveDetails l101 = new LeaveDetails(5, LeaveType.EL, sf.parse("2017/12/20"), sf.parse("2017/12/23"), 4,
                                         LeaveStatus.APPROVED, "trip", sf.parse("2017/12/18"), "enjoy", 2000);
    assertEquals(l100.hashCode(), new LeaveDetails(5, LeaveType.EL, sf.parse("2017/12/20"), sf.parse("2017/12/23"), 4,
                                                   LeaveStatus.APPROVED, "trip", sf.parse("2017/12/18"),
                                                   "enjoy", 2000).hashCode());
    assertEquals(5, l100.getLeaveId());
    l101.setLeaveId(5);
    assertEquals(LeaveType.EL, l100.getLeaveType());
    l101.setLeaveType(LeaveType.EL);
    assertEquals(sf.parse("2017/12/20"), l100.getStartDate());
    l101.setStartDate(sf.parse("2017/12/20"));
    assertEquals(sf.parse("2017/12/23"), l100.getEndDate());
    l101.setEndDate(sf.parse("2017/12/23"));
    assertEquals(4, l100.getNumberOfDays());
    l101.setNumberOfDays(4);
    assertEquals(LeaveStatus.APPROVED, l100.getLeaveStatus());
    l101.setLeaveStatus(LeaveStatus.APPROVED);
    assertEquals("trip", l100.getLeaveReason());
    l101.setLeaveReason("trip");
    assertEquals(sf.parse("2017/12/18"), l100.getLeaveAppliedOn());
    l101.setLeaveAppliedOn(sf.parse("2017/12/18"));
    assertEquals("enjoy", l100.getManagerComments());
    l101.setManagerComments("enjoy");
    assertEquals(2000, l100.getEmpId());
    l101.setEmpId(2000);

    Date sdt = sf.parse("2017/12/20");
    Date edt = sf.parse("2017/12/23");
    Date adt = sf.parse("2017/12/18");
    String ts = ("leave id :" + 5 + " " + "leave type :" + LeaveType.EL + " " + "start date :" +  sf.format(sdt)
              + " " + "end date :" + sf.format(edt) + " " + "number of days :" + 4 + " " + "leave status :"
              + LeaveStatus.APPROVED + " " +  "leave reason : " + "trip" + " " + "leave applied on :" + sf.format(adt)
              + " " + " managerComments :" + "enjoy" + " " + "empId :" + 2000);
    String s = l100.toString();
    assertEquals(s, ts);
  }


  /**
   * tests that empty leave details list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllEmpty(@Mocked final LeaveDetailsDAO dao) {
    new Expectations() {
      {
        dao.listHistory(2000); result = new ArrayList<LeaveDetails>();
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    LeaveDetails[] ls = LeaveDetails.listLeaveDetailsById(2000);
    assertEquals(0, ls.length);
  }

  /**
   * Tests that a list with some leave details is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllSome(@Mocked final LeaveDetailsDAO dao) {
    new Expectations() {
      {
        ArrayList<LeaveDetails> ls = new ArrayList<LeaveDetails>();
        ls.add(new LeaveDetails(1));
        ls.add(new LeaveDetails(10));
        ls.add(new LeaveDetails(100));
        dao.listHistory(2000); result = ls;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    LeaveDetails[] ls = LeaveDetails.listLeaveDetailsById(2000);
    assertEquals(3, ls.length);
    assertEquals(new LeaveDetails(1), ls[0]);
    assertEquals(new LeaveDetails(10), ls[1]);
    assertEquals(new LeaveDetails(100), ls[2]);
  }

  /**
   * Tests that a fetch of a specific leave details works correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListById(@Mocked final LeaveDetailsDAO dao) {
    final LeaveDetails l100 = new LeaveDetails(5);
    new Expectations() {
      {
        dao.find(5); result = l100;
        dao.find(-1); result = null;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    LeaveDetails l = LeaveDetails.listById(5);
    assertEquals(l100, l);

    l = LeaveDetails.listById(-1);
    assertNull(l);
  }
  /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testlistPendingApplication(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    new Expectations() {
      {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        dao.finds(2001);
        ArrayList<LeaveDetails> ld = new ArrayList<LeaveDetails>();
        ld.add(new LeaveDetails(9, LeaveType.EL, sf.parse("2017/12/28"), sf.parse("2017/12/02"),
                                3, LeaveStatus.PENDING, "TRIP", sf.parse("2017/12/21"),
                                "NULL", 3001));
        ld.add(new LeaveDetails(9, LeaveType.EL, sf.parse("2017/12/28"), sf.parse("2017/12/02"),
                                3, LeaveStatus.PENDING, "TRIP", sf.parse("2017/12/21"),
                                "NULL", 3001));
        result = ld;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails[] ld = LeaveDetails.listPendingApplication(2001);
    LeaveDetails[] ld1 = new LeaveDetails[2];
    ld1[0] = new LeaveDetails(9, LeaveType.EL, sf.parse("2017/12/28"), sf.parse("2017/12/02"),
                                3, LeaveStatus.PENDING, "TRIP", sf.parse("2017/12/21"),
                                "NULL", 3001);
    ld1[1] = new LeaveDetails(9, LeaveType.EL, sf.parse("2017/12/28"), sf.parse("2017/12/02"),
                                3, LeaveStatus.PENDING, "TRIP", sf.parse("2017/12/21"),
                                "NULL", 3001);
    assertArrayEquals(ld1, ld);
    System.out.println("Pending leave Test successful");
  }
  /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testlistLeaveDetails(@Mocked final LeaveDetailsDAO dao) throws ParseException {
    new Expectations() {
      {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        dao.listHistory(3001);
        ArrayList<LeaveDetails> ld = new ArrayList<LeaveDetails>();
        ld.add(new LeaveDetails(120, LeaveType.EL, sf.parse("2017/01/03"), sf.parse("2017/01/05"), 2,
                              LeaveStatus.APPROVED, "HIGH FEVER", sf.parse("2017/01/02"), "ACCEPTED", 3001));
        ld.add(new LeaveDetails(120, LeaveType.EL, sf.parse("2017/01/03"), sf.parse("2017/01/05"), 2,
                              LeaveStatus.APPROVED, "HIGH FEVER", sf.parse("2017/01/02"), "ACCEPTED", 3001));
        result = ld;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails[] ld = LeaveDetails.listLeaveDetailsById(3001);
    LeaveDetails[] ld1 = new LeaveDetails[2];
    ld1[0] = new LeaveDetails(120, LeaveType.EL, sf.parse("2017/01/03"), sf.parse("2017/01/05"), 2,
                                         LeaveStatus.APPROVED, "HIGH FEVER", sf.parse("2017/01/02"), "ACCEPTED", 3001);
    ld1[1] = new LeaveDetails(120, LeaveType.EL, sf.parse("2017/01/03"), sf.parse("2017/01/05"), 2,
                                         LeaveStatus.APPROVED, "HIGH FEVER", sf.parse("2017/01/02"), "ACCEPTED", 3001);
    assertArrayEquals(ld1, ld);
    System.out.println(" Testing of list_leave_details.");
  }

  /**
   * Tests that a fetch of a specific employee works correctly.
   * @param dao mocking the dao class
   * @throws ParseException to handle parse exception.
   */
  @Test
  public final void testapproveDeny(@Mocked final LeaveDetailsDAO dao)throws ParseException {
    new Expectations() {
      {
        dao.update(LeaveStatus.DENIED, 5, "enjoy");
        dao.update(LeaveStatus.APPROVED, 6, "wait for it");
        dao.increase(5);
        dao.decrease(6);
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    LeaveDetails l100 = new LeaveDetails(5, LeaveType.EL, sf.parse("2017/12/20"), sf.parse("2017/12/23"),
                                         4, LeaveStatus.APPROVED, "trip", sf.parse("2017/12/18"), "enjoy", 2000);
    LeaveDetails l101 = new LeaveDetails(6, LeaveType.EL, sf.parse("2017/12/26"), sf.parse("2017/12/28"),
                                         4, LeaveStatus.DENIED, "sick", sf.parse("2017/12/19"), "wait for it", 3001);
    LeaveDetails l102 = new LeaveDetails(7, LeaveType.EL, sf.parse("2017/12/27"), sf.parse("2017/12/28"),
                                         2, LeaveStatus.PENDING, "rest", sf.parse("2017/12/22"), "go ahead", 3001);
    String ld = l100.approveDeny(LeaveStatus.DENIED, 5, "enjoy");
    String ld1 = "Leave is denied for the Employee ID" + " " + 2000;
    assertEquals(ld1, ld);
    String ld2 = l101.approveDeny(LeaveStatus.APPROVED, 6, "wait for it");
    String ld3 = "Leave is approved for the Employee ID" + " " + 3001;
    assertEquals(ld2, ld3);
    String ld4 = l102.approveDeny(LeaveStatus.DENIED, 7, "go ahead");
    String ld5 = "Leave is denied for the Employee ID" + " " + 3001;
    assertEquals(ld5, ld4);
    String ld6 = l102.approveDeny(LeaveStatus.APPROVED, 7, "go ahead");
    String ld7 = "Leave is approved for the Employee ID" + " " + 3001;
    assertEquals(ld7, ld6);
    String ld8 = l101.approveDeny(LeaveStatus.PENDING, 6, "wait for it");
    String ld9 = "Leave is pending for the Employee ID" + " " + 3001;
    assertEquals(ld9, ld8);
    String ld10 = l100.approveDeny(LeaveStatus.PENDING, 5, "enjoy");
    String ld11 = "Leave is pending for the Employee ID" + " " + 2000;
    assertEquals(ld11, ld10);
  }
}

