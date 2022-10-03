package com.homebanking.homebanking;

import com.homebanking.homebanking.utils.CardUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class CardUtilsTest {

    @Test
    public void cardNumberIsCreated(){
        String cardNumber = CardUtils.getStringRandomNumber();
        assertThat(cardNumber,is(not(emptyOrNullString())));

    }

}
