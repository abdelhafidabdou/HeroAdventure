package fr.cleva.interview.adventurehero.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.cleva.interview.adventurehero.model.Card;
import fr.cleva.interview.adventurehero.model.Coordinate;
import fr.cleva.interview.adventurehero.model.MoveDetails;
import fr.cleva.interview.adventurehero.service.CardService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Arrays.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
class CardRestControllerTest {


    private MockMvc mvc;

     @InjectMocks
    private CardRestController CardRestController;


    @Mock
    private CardService cardService;









    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(CardRestController).build();
    }

    @Test
    void shouldeGetCard() throws Exception {

        Coordinate coordinate = new Coordinate(0,0);
        Coordinate coordinate1 = new Coordinate(1,1);
        Coordinate coordinate2 = new Coordinate(2,2);
     //
          when( cardService.readCard()).
                  thenReturn(
                         Optional.of( new Card(19,19, asList(coordinate,coordinate1,coordinate2)))
                  );
        mvc.perform(
                MockMvcRequestBuilders.get("/api/card")
        ).andDo(print()).andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.width", Matchers.is(notNullValue())))
                .andExpect(jsonPath("$.length", Matchers.is(notNullValue())))
                .andExpect(jsonPath("$.occupiedSquares").isArray())
                .andExpect(jsonPath("$.occupiedSquares", hasSize(3)))
                ;
    }

    @Test
    void shouldeMove() throws Exception {

        Coordinate init = new Coordinate(3,0);
        String steps = "SS";
        MoveDetails moveDetails = new MoveDetails(init,steps);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(moveDetails );

         when( cardService.move(any(MoveDetails.class))).thenReturn(Optional.of(new Coordinate(3,2)));
        mvc.perform(
                MockMvcRequestBuilders.post("/api/card/move").contentType(APPLICATION_JSON)
                .content(requestJson )
        ).andDo(print()).andExpect(status().isOk())

                .andExpect(jsonPath("$.x", Matchers.is(3)))
                .andExpect(jsonPath("$.y", Matchers.is(2)))      ;

    }




}



