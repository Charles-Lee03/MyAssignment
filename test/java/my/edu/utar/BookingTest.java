package my.edu.utar;

import static org.junit.Assert.*;

import org.junit.Test;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static junitparams.JUnitParamsRunner.$;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class BookingTest {

	@Test
	@Parameters({
		"non-member, false, 0, 1, 1, 0, 0, 0, 0", // zero room requested
		"non-member, false, 0, 1, 1, 1, 0, 0, 1", // 1 room requested - standard room available
		"non-member, false, 0, 1, 0, 1, 0, 0, 0"// 1 room requested - standard room not available
		})
	public void testSetBookingMockito(String member_type, boolean excl_reward, int vipAvailable, int deluxeAvailable, int standardAvailable, int noOfRooms, int expectedVIP, int expectedDeluxe, int expectedStandard) {
		//create 3 interface objects - 2 stubs, 1 mock
		RoomFunctionality rfMock = mock(NewRoom.class);
		UserFunctionality uMock = mock(NewUser.class);
		WaitingListFunctionality wlMock = mock(NewWaitingList.class);
		
		//fixed input values
		when(rfMock.getVip()).thenReturn(vipAvailable);
		when(rfMock.getDeluxe()).thenReturn(deluxeAvailable);
		when(rfMock.getStandard()).thenReturn(standardAvailable);
		
		when(uMock.getMember_type()).thenReturn(member_type);
		when(uMock.getExcl_reward()).thenReturn(excl_reward);
		
		//calling MUT
		NewBooking book = new NewBooking(rfMock, uMock, wlMock);
		Room roomsbooked = book.setBooking(noOfRooms);
		
		//test for booking correct amount of rooms type
		assertEquals(expectedVIP,roomsbooked.getVip());
		assertEquals(expectedDeluxe,roomsbooked.getDeluxe());
		assertEquals(expectedStandard,roomsbooked.getStandard());
	}

	@Test
	@Parameters({"non-member, 0, 1, 0, 1" // 1 room requested - standard room not available - add to waiting list
		})
	public void testSetBookingWaitingListMockito(String member_type, int vipAvailable, int deluxeAvailable, int standardAvailable, int noOfRooms){
		//create 3 interface objects - 2 stubs, 1 mock
		RoomFunctionality rfMock = mock(NewRoom.class);
		UserFunctionality uMock = mock(NewUser.class);
		WaitingListFunctionality wlMock = mock(NewWaitingList.class);
		
		//fixed input values
		when(rfMock.getVip()).thenReturn(vipAvailable);
		when(rfMock.getDeluxe()).thenReturn(deluxeAvailable);
		when(rfMock.getStandard()).thenReturn(standardAvailable);
		when(uMock.getMember_type()).thenReturn(member_type);
		
		//calling MUT
		NewBooking book = new NewBooking(rfMock, uMock, wlMock);
		book.setBooking(noOfRooms);
		UserFunctionality aUser = book.getCurrentUser();
		
		//verify connection
		verify(wlMock).addWaiting(aUser);
		}
}
