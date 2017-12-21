package com.hexaware.ftp01.model;

import com.hexaware.ftp01.persistence.LeaveDetailsDAO;

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
import java.text.SimpleDateFormat;
import java.text.ParseException;

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
    SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
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
  }


  /**
   * tests that empty leave details list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllEmpty(@Mocked final LeaveDetailsDAO dao) {
    new Expectations() {
      {
        dao.list(2000); result = new ArrayList<LeaveDetails>();
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    LeaveDetails[] ls = LeaveDetails.listLeaveDetails(2000);
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
        dao.list(2000); result = ls;
      }
    };
    new MockUp<LeaveDetails>() {
      @Mock
      LeaveDetailsDAO dao() {
        return dao;
      }
    };
    LeaveDetails[] ls = LeaveDetails.listLeaveDetails(2000);
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
}
