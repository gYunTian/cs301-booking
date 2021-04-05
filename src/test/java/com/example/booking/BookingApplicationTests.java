package com.example.booking;

import com.example.booking.model.Booking;
import com.example.booking.model.Customer;
import com.example.booking.repository.BookingRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
class BookingApplicationTests {

	@Autowired
    private MockMvc mvc;

	@MockBean 
	private BookingRepository bookingRepository;
	
	@Autowired
	private JacksonTester<Booking> jsonBooking;

	@Test
	public void retrieveByBookingReferenceCode() throws Exception{
		// given
		given(bookingRepository.findByBookingReferenceCode("ffcb5307-32c4-4c10-963a-1324d05886a3"))
				.willReturn(new Booking("ffcb5307-32c4-4c10-963a-1324d05886a3", 3, "20/7/2020", "28/7/2020", 2, 0, "-", "King roomType", 12.2 ,new Customer("Mike", "Kyle", "Mr","kyle@gmail.com")));
	
		String bookingReferenceCode = "ffcb5307-32c4-4c10-963a-1324d05886a3";

		// when
		MockHttpServletResponse response = mvc.perform(
			get("/booking/" + bookingReferenceCode)
					.accept(MediaType.APPLICATION_JSON))
			.andReturn().getResponse();
	
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(
			jsonBooking.write(new Booking("ffcb5307-32c4-4c10-963a-1324d05886a3", 3, "20/7/2020", "28/7/2020", 2, 0, "-", "King roomType", 12.2 ,new Customer("Mike", "Kyle", "Mr","kyle@gmail.com"))).getJson()
		);
	}

	@Test
	public void canCreateBooking() throws Exception {
		// when
		MockHttpServletResponse response = mvc.perform(
			post("/booking/").contentType(MediaType.APPLICATION_JSON).content(
					jsonBooking.write(new Booking(3, "20/7/2020", "28/7/2020", 2, 0, "-", "King roomType", 12.2 ,new Customer("Mike", "Kyle", "Mr","kyle@gmail.com"))).getJson()
			)).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void canDeleteBooking() throws Exception {
		// given
		given(bookingRepository.deleteBooking("758a207e-8934-463c-b579-1263964cdea1"))
				.willReturn("Booking Cancelled!");

		// when
		String bookingReferenceCode = "758a207e-8934-463c-b579-1263964cdea1";

		MockHttpServletResponse response = mvc.perform(
			delete("/booking/" + bookingReferenceCode).contentType(MediaType.APPLICATION_JSON)
			).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo("Booking Cancelled!");
		
	}

}
